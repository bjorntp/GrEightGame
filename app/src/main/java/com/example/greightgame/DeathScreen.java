package com.example.greightgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DeathScreen extends AppCompatActivity {

    TextView textView, highScoreValue;
    Button restartButton, homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_death_screen);

        Intent intent = getIntent();
        String score = intent.getStringExtra("score");

        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        if(Integer.parseInt(score) > prefs.getInt("highScore", 0)){
            editor.putInt("highScore", Integer.parseInt(score));
            editor.commit();
        }

        restartButton = (Button) findViewById(R.id.restartButton);
        restartButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                    restart(v);
                }
        });

        homeButton = (Button) findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                home(v);
            }
        });

        highScoreValue = (TextView) findViewById(R.id.highScoreValue);
        String highScore = Integer.toString(prefs.getInt("highScore",0));
        highScoreValue.setText(highScore);

        textView = (TextView) findViewById(R.id.score);
        textView.setText(score);
    }

    protected void restart(View view){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    protected void home(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}