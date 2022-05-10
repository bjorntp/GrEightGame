package com.example.greightgame;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;


public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    public MainThread thread;
    public CharacterSprite characterSprite;
    private FenceSprite fence;
    private ArrayList<ObstacleSprite> obstacles;
    private int scoreCounter;
    private int lastFence;
    private Paint scoreStyle;
    private Typeface font;
    private int sizeOfObstacles;
    private int sizeOfCharacter;
    private Context context;
    private Vibrator vibrator;
    private MediaPlayer sound;

    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public GameView(Context context, Vibrator vibrator) {
        super(context);
        sizeOfObstacles = 150;
        sizeOfCharacter = 150;
        getHolder().addCallback(this);
        this.vibrator = vibrator;

        thread = new MainThread(getHolder(), this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        characterSprite = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.wizard), sizeOfCharacter);
        fence = new FenceSprite(BitmapFactory.decodeResource(getResources(), R.drawable.long_fence));
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


        sound = MediaPlayer.create(getContext(), R.raw.foamrubber);
        sound.start();
        sound.setLooping(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {    }

    public void setCharacterVelocity(int v) { characterSprite.setVelocity(v); }

    public void setCharacterJump() { if(!characterSprite.isInTheAir()) {characterSprite.jump();} }

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
        fence.update();
        for(ObstacleSprite o: obstacles) {
            o.update();
            checkCollision(o);
        }
        checkFenceCollision(fence);
        scoreCounter++;
        lastFence++;

       if(obstacles.size() < 5 && scoreCounter % 100 == 0) {
            if(scoreCounter % 400 == 0) {
                obstacles.add(new ObstacleSprite(BitmapFactory.decodeResource(getResources(), R.drawable.stone), sizeOfObstacles));
            } else {
                obstacles.add(new ObstacleSprite(BitmapFactory.decodeResource(getResources(), R.drawable.cactus), sizeOfObstacles));
            }
        }
       if(Math.random() < 0.01 && lastFence > 500) {
           fence.reset();
           lastFence = 0;
       }
    }

    public void checkCollision(ObstacleSprite o) {

        int cX = characterSprite.getPosition()[0];
        int cY = characterSprite.getPosition()[1];
        int oX = o.getPosition()[0];
        int oY = o.getPosition()[1];

        if (cX < oX + sizeOfObstacles - 50 &&
            cX + sizeOfObstacles - 50 > oX &&
            cY < oY + sizeOfObstacles - 50 &&
            cY + sizeOfObstacles - 50 > oY) {
                death();
        }
    }

    public void checkFenceCollision(FenceSprite f) {

        int cY = characterSprite.getPosition()[1];
        int fY = f.getPosition();

        if(cY < fY + sizeOfObstacles &&
                cY + sizeOfObstacles > fY && !characterSprite.isInTheAir()){
            death();
        }

    }

    public void death(){
        thread.setRunning(false);
        vibrator.vibrate(500);
        sound.stop();
        Intent intent = new Intent(getContext(), DeathScreen.class);
        String message = Integer.toString(scoreCounter);
        intent.putExtra("score", message);
        getContext().startActivity(intent);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(canvas!=null) {
            canvas.drawRGB(234,210,168);
            canvas.drawText("SCORE: " + String.valueOf(scoreCounter), screenWidth / 2, (float) (screenHeight * 0.1), scoreStyle);
            fence.draw(canvas);
            characterSprite.draw(canvas);
            for(ObstacleSprite o: obstacles) {
                o.draw(canvas);
            }
        }
    }

}
