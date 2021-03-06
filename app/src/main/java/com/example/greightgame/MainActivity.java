package com.example.greightgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void startGame(View view) {
        Intent gameStart = new Intent(this, GameActivity.class);
        startActivity(gameStart);
    }

    public void howToPlay(View view){
        Intent howTo = new Intent(this, HowToPlay.class);
        startActivity(howTo);
    }

}