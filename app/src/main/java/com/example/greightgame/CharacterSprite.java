package com.example.greightgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import java.util.ArrayList;

public class CharacterSprite {

    private Bitmap usedImage, defautltImage, defaultImageMirrored;
    private ArrayList<Bitmap> jumpImages;
    private int x, y;
    private int xVelocity = 0;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private boolean inTheAir;
    private int jumpCounter;
    private Matrix flipMatrix;

    public CharacterSprite (Bitmap bmp, int size) {
        defautltImage = Bitmap.createScaledBitmap(bmp,size ,size,true);
        flipMatrix = new Matrix();
        flipMatrix.preScale(-1.0f, 1.0f);
        defaultImageMirrored = Bitmap.createBitmap(bmp, 0,0, bmp.getWidth(), bmp.getHeight(), flipMatrix, true);
        defaultImageMirrored = Bitmap.createScaledBitmap(defaultImageMirrored ,size ,size,true);
        usedImage = defautltImage;
        jumpImages = new ArrayList<Bitmap>();
        for(int i = 30;i >= 0;i--) {
            jumpImages.add(Bitmap.createScaledBitmap(bmp,size + 2 * i,size + 2 * i,true));
        }
        x = (int) (screenWidth * 0.5) - 80;
        y = (int) (screenHeight * 0.8);
        inTheAir = false;
    }

    public void jump(){
        inTheAir = true;
        jumpCounter = 29;
    }

    public boolean isInTheAir() {
        return inTheAir;
    }

    public void draw(Canvas canvas) {
        if(inTheAir) {
            canvas.drawBitmap(jumpImages.get((int) Math.sqrt(jumpCounter * jumpCounter)), x, y, null);
            jumpCounter--;
            if(jumpCounter == -29){
                inTheAir = false;
            }
        } else {
            canvas.drawBitmap(usedImage, x, y, null);
        }
    }

    public int[] getPosition() {
        return new int[]{x, y};
    }

    public void setVelocity(int v) {
        xVelocity = v;
        if(v > 0) {
            usedImage = defautltImage;
        } else {
            usedImage = defaultImageMirrored;
        }
    }
    public void update() {

        if (!(x+xVelocity > screenWidth - defautltImage.getWidth()) && (x+xVelocity > 0) && !inTheAir) {
            x += xVelocity;
        }

    }


}
