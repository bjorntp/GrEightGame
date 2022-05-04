package com.example.greightgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class FenceSprite {
    private Bitmap image;
    private int y, x;
    private int yVelocity = 15;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public FenceSprite (Bitmap bmp) {
        image = Bitmap.createScaledBitmap(bmp, screenWidth,200,true);
        y = -100;
        x = 0;
    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);

    }

    public int getPosition() {
        return y;
    }

    public void reset(){
        y = -100;
    }

    public void update() {

        y += yVelocity;

    }
}
