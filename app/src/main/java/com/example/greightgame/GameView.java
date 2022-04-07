package com.example.greightgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;


public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    public CharacterSprite characterSprite;
    private ArrayList<ObstacleSprite> obstacles;
    private int scoreCounter;
    private Paint scoreStyle;
    private Typeface font;

    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        characterSprite = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.wizard_mindre));
        obstacles = new ArrayList<>();
        scoreCounter = 0;
        scoreStyle = new Paint();
        scoreStyle.setColor(Color.BLACK);
        scoreStyle.setTextSize(50f);
        scoreStyle.setTextAlign(Paint.Align.CENTER);
        font = ResourcesCompat.getFont(getContext(), R.font.roboto_bold);
        scoreStyle.setTypeface(font);


        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {    }

    public void setCharacterVelocity(int v) { characterSprite.setVelocity(v); }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update(){
        characterSprite.update();
        for(ObstacleSprite o: obstacles) {
            o.update();
            checkCollision(o);
        }
        scoreCounter++;
        Log.d("Score: ", String.valueOf(scoreCounter));
        if(obstacles.size() < 6 && scoreCounter % 200 == 0) {
            if(scoreCounter % 400 == 0) {
                obstacles.add(new ObstacleSprite(BitmapFactory.decodeResource(getResources(), R.drawable.stone_mindre)));
            } else {
                obstacles.add(new ObstacleSprite(BitmapFactory.decodeResource(getResources(), R.drawable.cactus_mindre)));
            }
        }

    }

    public void checkCollision(ObstacleSprite o) {

        int cX = characterSprite.getPosition()[0];
        int cY = characterSprite.getPosition()[1];
        int oX = o.getPosition()[0];
        int oY = o.getPosition()[1];

        if (cX < oX + 80 &&
            cX + 80 > oX &&
            cY < oY + 80 &&
            cY + 80 > oY) {
            surfaceDestroyed(thread.surfaceHolder);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(canvas!=null) {
            canvas.drawRGB(234,210,168);
            characterSprite.draw(canvas);
            canvas.drawText("SCORE: " + String.valueOf(scoreCounter), screenWidth / 2, (float) (screenHeight * 0.1), scoreStyle);
            for(ObstacleSprite o: obstacles) {
                o.draw(canvas);
            }
        }
    }

}
