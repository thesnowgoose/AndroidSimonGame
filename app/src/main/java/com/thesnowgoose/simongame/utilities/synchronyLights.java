package com.thesnowgoose.simongame.utilities;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by lcarrasco on 5/4/17.
 */
public class SynchronyLights extends AsyncTask {

    private HighLightButton listener;
    private BtnColors color;

    public SynchronyLights(Context ctx, BtnColors color) {
        this.color = color;
        if (ctx instanceof HighLightButton) {
            listener = (HighLightButton) ctx;
        } else {
            throw new ClassCastException(ctx.toString() + " must implement HighLightButton.");
        }
    }

    @Override
    protected Object doInBackground(Object[] params) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(Object result) {
        listener.onHighlightButtons(color);
    }

    public interface HighLightButton {
        void onHighlightButtons(BtnColors color);
    }
}
