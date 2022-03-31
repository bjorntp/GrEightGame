package com.example.greightgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
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

    private Animation animation;

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

        character.setAnimation(animate());

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        double zValue = sensorEvent.values[2];

        if (zValue > 9) {
            animation.cancel();
            animation.start();
        }



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void move(SensorEvent sense) {
        xValue = (float) sense.values[0];
    }

    private TranslateAnimation animate() {
        animation = new TranslateAnimation(0,40,0,0);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(-1);

        return (TranslateAnimation) animation;
    }


}