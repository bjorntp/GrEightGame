package com.example.greightgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class CharacterSprite {

    private Bitmap image;
    private int x, y;
    private int xVelocity = 0;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public CharacterSprite (Bitmap bmp, int size) {
        image = Bitmap.createScaledBitmap(bmp,size ,size,true);
        x = (int) (screenWidth * 0.5) - 80;
        y = (int) (screenHeight * 0.8);
    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);


    }

    public int[] getPosition() {
        return new int[]{x, y};
    }

    public void setVelocity(int v) {
        xVelocity = v;
    }
    public void update() {

        if (!(x+xVelocity > screenWidth - image.getWidth()) && (x+xVelocity > 0)) {
            x += xVelocity;
        }

    }


}
