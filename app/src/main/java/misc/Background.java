package misc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import common.Util;
import network.iut.org.flappydragon.GameView;
import network.iut.org.flappydragon.R;

public class Background {
    private GameView view;
    private int frameCounter = 0;

    private Bitmap sky;
    private Bitmap clouds;

    private int skyOffset = 0;
    private int cloudOffset = 0;
    private int skySpeed = 12;
    private int cloudSpeed = 6;

    public Background(Context context, GameView view) {
        sky = Util.getScaledBitmapAlpha8(context, R.drawable.layer1);
        clouds = Util.getScaledBitmapAlpha8(context, R.drawable.layer2);
        this.view = view;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(sky, -skyOffset, 0, null);
        canvas.drawBitmap(clouds, -cloudOffset, 0, null);
    }

    public void move() {
        nextFrame();

        this.skyOffset+=skySpeed;
        this.cloudOffset+=cloudSpeed;

        if(skyOffset >= (sky.getWidth()-view.getWidth()))
            this.skyOffset=0;
        if(cloudOffset >= (clouds.getWidth()-view.getWidth()))
            this.cloudOffset=0;
    }

    public void nextFrame() {
        this.frameCounter++;

        if(this.frameCounter%4 == 1)
            this.frameCounter = 0;
    }
}
