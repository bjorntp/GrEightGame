package com.example.greightgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class ObstacleSprite {

    private Bitmap image;
    private int x, y;
    private int yVelocity = 15;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public ObstacleSprite (Bitmap bmp) {
        image = bmp;
        x = (int) (Math.random() * screenWidth);
        y = -80;
    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);

    }

    public int[] getPosition() {
        return new int[]{x, y};
    }

    public void update() {

        y += yVelocity;

        if(y > screenHeight) {
            y = -80;
            x = (int) (Math.random() * (screenWidth - 80));
        }


    }


}

