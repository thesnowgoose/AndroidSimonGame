package com.thesnowgoose.simongame;

import android.content.Context;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.thesnowgoose.simongame.utilities.BtnColors;
import com.thesnowgoose.simongame.utilities.SynchronyLights;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static com.thesnowgoose.simongame.utilities.BtnColors.*;

public class MainActivity extends AppCompatActivity implements SynchronyLights.HighLightButton {

    final int oneSec = 500;

    private Button red_btn;
    private Button green_btn;
    private Button yellow_btn;
    private Button blue_btn;
    private List<BtnColors> colorBtns = new LinkedList<>();
    private List<BtnColors> playerColorBtns = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = this;
        red_btn = (Button) findViewById(R.id.btn_red);
        green_btn = (Button) findViewById(R.id.btn_green);
        yellow_btn = (Button) findViewById(R.id.btn_yellow);
        blue_btn = (Button) findViewById(R.id.btn_blue);

        final Button start_btn = (Button) findViewById(R.id.btn_start);
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                colorBtns.clear();
                playerColorBtns.clear();
                getRandomButton();
                for (BtnColors color: colorBtns) {
                    new SynchronyLights(context, color).execute();
                }

            }
        });
    }

    private void getRandomButton() {
        Random random = new Random();
        int randomNumber = random.nextInt(4);
        switch (randomNumber) {
            case 0:
//                Toast.makeText(this, "Red", Toast.LENGTH_SHORT).show();
                colorBtns.add(RED);
                break;
            case 1:
//                Toast.makeText(this, "Green", Toast.LENGTH_SHORT).show();
                colorBtns.add(GREEN);
                break;
            case 2:
//                Toast.makeText(this, "Yellow", Toast.LENGTH_SHORT).show();
                colorBtns.add(YELLOW);
                break;
            case 3:
//                Toast.makeText(this, "Blue", Toast.LENGTH_SHORT).show();
                colorBtns.add(BLUE);
                break;
        }
    }

    @Override
    public void onHighlightButtons(BtnColors color) {
        TransitionDrawable red_transition = (TransitionDrawable) red_btn.getBackground();
        TransitionDrawable green_transition = (TransitionDrawable) green_btn.getBackground();
        TransitionDrawable yellow_transition = (TransitionDrawable) yellow_btn.getBackground();
        TransitionDrawable blue_transition = (TransitionDrawable) blue_btn.getBackground();
        switch (color) {
            case RED:
                red_transition.startTransition(oneSec);
                red_transition.reverseTransition(oneSec);
                break;
            case GREEN:
                green_transition.startTransition(oneSec);
                green_transition.reverseTransition(oneSec);
                break;
            case YELLOW:
                yellow_transition.startTransition(oneSec);
                yellow_transition.reverseTransition(oneSec);
                break;
            case BLUE:
                blue_transition.startTransition(oneSec);
                blue_transition.reverseTransition(oneSec);
                break;
        }
    }
}
