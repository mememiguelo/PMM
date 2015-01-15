package com.example.mati.killthemall;

/**
 * Created by mati on 13/01/15.
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;



public class Sprite {
    private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 5;
    private int x = 0;
    private int y = 100;
    private int xSpeed = 5;
    private GameView gameView;
    private Bitmap bmp;
    private int currentFrame = 0;
    private int width;
    private int height;
    int srcX;
    int srcY=0;
    boolean terminado = false;

    public Sprite(GameView gameView, Bitmap bmp) {
        this.gameView = gameView;
        this.bmp = bmp;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;

    }



    private void update() {
        if (x > gameView.getWidth() - width - xSpeed) {
            xSpeed = -5;
        }
        if (x + xSpeed < 0) {
            xSpeed = 5;
        }
        x = x + xSpeed;
        currentFrame = ++currentFrame % BMP_COLUMNS;
    }



    public void onDraw(Canvas canvas) {

        update();
        srcX = currentFrame * width;
        //srcY = 0;

        System.out.println(height + " - " + srcY + " /// " + srcX);
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height );
        Rect dst = new Rect(x, y, x + width, y + height);
        if(srcX > 255){
            srcY+=height;
            if(srcY==256){
                srcY=0;
                terminado=true;
            }
        }else{
            System.out.println("ola");
        }
        if(!terminado){
            canvas.drawBitmap(bmp, src, dst, null);
        }




    }

}