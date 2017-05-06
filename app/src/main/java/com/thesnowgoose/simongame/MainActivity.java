package com.thesnowgoose.simongame;

import android.content.Context;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.thesnowgoose.simongame.utilities.BtnColors;
import com.thesnowgoose.simongame.utilities.SynchronyLights;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static com.thesnowgoose.simongame.utilities.BtnColors.*;

public class MainActivity extends AppCompatActivity
        implements SynchronyLights.HighLightButton,
                   View.OnClickListener {

    private final int halfSec = 500;
    private int score = 0;
    private int index = 0;
    private boolean gameStarted = false;

    private TransitionDrawable red_transition;
    private TransitionDrawable green_transition;
    private TransitionDrawable yellow_transition;
    private TransitionDrawable blue_transition;

    private List<BtnColors> colorBtns = new LinkedList<>();
    private List<BtnColors> playerColorBtns = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = this;
        Button red_btn = (Button) findViewById(R.id.btn_red);
        Button green_btn = (Button) findViewById(R.id.btn_green);
        Button yellow_btn = (Button) findViewById(R.id.btn_yellow);
        Button blue_btn = (Button) findViewById(R.id.btn_blue);

        red_btn.setOnClickListener(this);
        green_btn.setOnClickListener(this);
        yellow_btn.setOnClickListener(this);
        blue_btn.setOnClickListener(this);

        red_transition = (TransitionDrawable) red_btn.getBackground();
        green_transition = (TransitionDrawable) green_btn.getBackground();
        yellow_transition = (TransitionDrawable) yellow_btn.getBackground();
        blue_transition = (TransitionDrawable) blue_btn.getBackground();

        final Button start_btn = (Button) findViewById(R.id.btn_start);
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eraseGameData();
                gameStarted = true;
                getRandomButton();
                new SynchronyLights(context, colorBtns.get(0)).execute();
            }
        });
    }

    private void eraseGameData() {
        score = 0;
        index = 0;
        colorBtns.clear();
        playerColorBtns.clear();
        gameStarted = false;
    }

    private void getRandomButton() {
        Random random = new Random();
        int randomNumber = random.nextInt(4);
        switch (randomNumber) {
            case 0:
                colorBtns.add(RED);
                break;
            case 1:
                colorBtns.add(GREEN);
                break;
            case 2:
                colorBtns.add(YELLOW);
                break;
            case 3:
                colorBtns.add(BLUE);
                break;
        }
    }

    private void animateColoredButton(TransitionDrawable transition) {
        transition.startTransition(halfSec);
        transition.reverseTransition(halfSec);
    }

    @Override
    public void onHighlightButtons(BtnColors color) {
        switch (color) {
            case RED:
                animateColoredButton(red_transition);
                break;
            case GREEN:
                animateColoredButton(green_transition);
                break;
            case YELLOW:
                animateColoredButton(yellow_transition);
                break;
            case BLUE:
                animateColoredButton(blue_transition);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (gameStarted) {
            switch (v.getId()) {
                case R.id.btn_red:
                    animateColoredButton(red_transition);
                    playerColorBtns.add(RED);
                    break;
                case R.id.btn_green:
                    animateColoredButton(green_transition);
                    playerColorBtns.add(GREEN);
                    break;
                case R.id.btn_yellow:
                    animateColoredButton(yellow_transition);
                    playerColorBtns.add(YELLOW);
                    break;
                case R.id.btn_blue:
                    animateColoredButton(blue_transition);
                    playerColorBtns.add(BLUE);
                    break;
            }
        }

        if (!colorBtns.isEmpty() && !playerColorBtns.isEmpty())
            if (colorBtns.get(index) != playerColorBtns.get(index)) {
                Toast.makeText(this, "Perdiste, lograste " + score + " puntos", Toast.LENGTH_SHORT).show();
                eraseGameData();
            } else {
                index++;
            }

        if (gameStarted && colorBtns.size() == playerColorBtns.size()) {
            score++;
            index = 0;
            playerColorBtns.clear();
            getRandomButton();
            for (BtnColors color : colorBtns) {
                new SynchronyLights(this, color).execute();
            }
        }
    }
}
