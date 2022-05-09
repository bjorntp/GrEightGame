package com.example.greightgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

public class CharacterSprite {

    private Bitmap defautltImage;
    private ArrayList<Bitmap> jumpImages;
    private int x, y;
    private int xVelocity = 0;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private boolean inTheAir;
    private int jumpCounter;

    public CharacterSprite (Bitmap bmp, int size) {
        defautltImage = Bitmap.createScaledBitmap(bmp,size ,size,true);
        jumpImages = new ArrayList<Bitmap>();
        for(int i = 50;i >= 0;i--) {
            jumpImages.add(Bitmap.createScaledBitmap(bmp,size + 2 * i,size + 2 * i,true));
        }
        x = (int) (screenWidth * 0.5) - 80;
        y = (int) (screenHeight * 0.8);
        inTheAir = false;
    }

    public void jump(){
        inTheAir = true;
        jumpCounter = 49;
    }

    public boolean isInTheAir() {
        return inTheAir;
    }

    public void draw(Canvas canvas) {
        if(inTheAir) {
            canvas.drawBitmap(jumpImages.get((int) Math.sqrt(jumpCounter * jumpCounter)), x, y, null);
            jumpCounter--;
            if(jumpCounter == -49){
                inTheAir = false;
            }
        } else {
            canvas.drawBitmap(defautltImage, x, y, null);
        }
    }

    public int[] getPosition() {
        return new int[]{x, y};
    }

    public void setVelocity(int v) {
        xVelocity = v;
    }
    public void update() {

        if (!(x+xVelocity > screenWidth - defautltImage.getWidth()) && (x+xVelocity > 0) && !inTheAir) {
            x += xVelocity;
        }

    }


}
