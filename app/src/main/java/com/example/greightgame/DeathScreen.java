package com.example.greightgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DeathScreen extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_death_screen);

        Intent intent = getIntent();
        String score = intent.getStringExtra("score");

        textView = (TextView) findViewById(R.id.score);
        textView.setText(score);
    }

    protected void restart(View view){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

}