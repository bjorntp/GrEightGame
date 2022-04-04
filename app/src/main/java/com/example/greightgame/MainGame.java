package com.example.greightgame;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainGame extends AppCompatActivity implements SensorEventListener {

    ImageView character;
    FrameLayout characterContainer;
    private SensorManager sensorManager;
    private Sensor accSensor;
    float xValue;
    int width;
    //DisplayMetrics displayMetrics = new DisplayMetrics();
    //int height = displayMetrics.heightPixels;
    //int width = displayMetrics.widthPixels; // vrf blir den 0??



   /* WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
    final DisplayMetrics displayMetrics = new DisplayMetrics();
    wm.getDefaultDisplay().getMetrics(displayMetrics);
    int height = displayMetrics.heightPixels;
    int width = displayMetrics.widthPixels;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        character = (ImageView) findViewById(R.id.imageCharacter);
        //characterContainer = (FrameLayout) findViewById(R.id.containerCharacter);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // register accelerometer sensor
        sensorManager.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_NORMAL);

        width = Resources.getSystem().getDisplayMetrics().widthPixels;
        Log.d("width", Float.toString(width));
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        double xValue = sensorEvent.values[0];
        animate(character, 500 * xValue);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void move(SensorEvent sense) {
        xValue = (float) sense.values[0];
    }


    private void animate(View view, double dx) {
        ObjectAnimator animation = ObjectAnimator.ofFloat(view, "translationX", (float) (view.getX() - dx));
        animation.setDuration(400);
        animation.start();
        Log.d("pos: ", Float.toString(view.getX()));

        // set window boundaries
        if (view.getX() > width) {
            animation.reverse();
        }



    }


}