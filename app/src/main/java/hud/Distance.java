package hud;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import interfaces.HUD;
import network.iut.org.flappydragon.GameView;
import network.iut.org.flappydragon.R;
import common.Util;

/**
 * Created by Keyvan on 13/03/2017.
 */

public class Distance implements HUD {

    private GameView view;
    private Context context;
    private Bitmap coinIcon;

    private int height;
    private int width;
    private int posX = 0;
    private int posY = 0;
    private Paint paint = new Paint();
    private Rect bounds = new Rect();
    private int framecount;
    private int distance = 0;

    public Distance(Context context, GameView view) {
        this.context = context;
        this.view = view;
        this.width = 50;
        this.height = 50;
        this.posX = context.getResources().getDisplayMetrics().widthPixels-this.width;
        this.posY = 0;
        float textSize = paint.getTextSize();
        paint.setTextSize(textSize * 5);
        paint.setARGB(255, 255, 255, 255);
        this.coinIcon = Util.getScaledBitmapAlpha8(context, R.drawable.coin2);
    }

    public void draw(Canvas canvas) {
        canvas.drawText(distance+"m", 20, 170, paint);
    }

    public void add(int add) {
        this.distance+= add;
    }

    @Override
    public int getPosX() {
        return this.posX;
    }

    @Override
    public int getPosY() {
        return this.posY;
    }


    @Override
    public void destroy() {
    }

    @Override
    public void reset() {
        this.distance = 0;
    }

    public int getDistance() {
        return this.distance;
    }
}
