package com.example.greightgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainGame extends AppCompatActivity implements SensorEventListener {

    ImageView character;
    FrameLayout characterContainer;
    private SensorManager sensorManager;
    private Sensor accSensor;
    float xValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        character = (ImageView) findViewById(R.id.imageCharacter);
        characterContainer = (FrameLayout) findViewById(R.id.containerCharacter);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void move(SensorEvent sense) {
        xValue = (float) sense.values[0]);
    }


}