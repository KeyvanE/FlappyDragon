package misc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import common.Util;
import network.iut.org.flappydragon.GameView;
import network.iut.org.flappydragon.R;

public class Floor {
    private GameView view;
    private Context context;
    private int frameCounter = 0;

    private Bitmap floor;
    private int posX = 0;
    private int speedX = -10;
    private int height = 100;
    private int width = 1477;
    private int framecount = 0;
    private int speedboostInterval = 100;

    public Floor(Context context, GameView view) {
        floor = Util.getScaledBitmapAlpha8(context, R.drawable.floor);
        this.view = view;
        this.context = context;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(floor, posX, canvas.getHeight()-150, null);
    }

    public void move() {
        framecount++;

        posX+=speedX;

        if((posX*-1)>width)
            posX = 0;

        if(framecount%speedboostInterval==0)
            speedX--;
    }
}
