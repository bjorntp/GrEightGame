package com.example.greightgame;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class GameActivity extends Activity implements SensorEventListener{


    private SensorManager sensorManager;
    private Sensor accSensor;
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        double xValue = sensorEvent.values[0];

        gameView.setCharacterVelocity( (int) (xValue * -8) );

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
