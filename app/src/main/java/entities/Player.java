package entities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import interfaces.Entity;
import common.Animation;
import network.iut.org.flappydragon.EntityManager;
import network.iut.org.flappydragon.GameView;
import network.iut.org.flappydragon.R;
import common.Util;

public class Player implements Entity {
    /** Static bitmap to reduce memory usage. */
    public static Bitmap globalBitmap;
    private Paint hitboxPaint = new Paint();

    private Animation animation;
    private Rect hitbox;
    private int frameCount;
    private final int width;
    private final int height;
    private int x;
    private int y;
    private float speedX;
    private float speedY;
    private GameView view;
    private Context context;
    private EntityManager manager;

    public Player(Context context, GameView view, EntityManager manager) {

        this.context = context;
        this.view = view;
        this.manager = manager;

        this.animation = new Animation();
        this.animation.addStep(Util.getScaledBitmapAlpha8(context, R.drawable.frame1));
        this.animation.addStep(Util.getScaledBitmapAlpha8(context, R.drawable.frame2));
        this.animation.addStep(Util.getScaledBitmapAlpha8(context, R.drawable.frame3));
        this.animation.addStep(Util.getScaledBitmapAlpha8(context, R.drawable.frame4));
        this.animation.addStep(Util.getScaledBitmapAlpha8(context, R.drawable.frame5));
        this.animation.addStep(Util.getScaledBitmapAlpha8(context, R.drawable.frame6));
        this.animation.addStep(Util.getScaledBitmapAlpha8(context, R.drawable.frame7));
        this.animation.addStep(Util.getScaledBitmapAlpha8(context, R.drawable.frame8));

        this.width = 100;
        this.height = 100;

        this.resetPosition();

        this.speedX = 0;

        this.hitbox = new Rect(this.x, this.y, (this.x+this.width*2), (this.y+this.height*2));
        this.hitboxPaint.setAlpha(0);

    }

    public void onTap() {
        this.speedY = getTabSpeed();
        this.y += getPosTabIncrease();
    }
    private void resetPosition() {
        this.y = context.getResources().getDisplayMetrics().heightPixels / 2;	// Startposition in the middle of the screen
        this.x = context.getResources().getDisplayMetrics().widthPixels / 6;
    }

    private float getPosTabIncrease() {
        return - view.getHeight() / 100;
    }

    private float getTabSpeed() {
        return -view.getHeight() / 16f;
    }

    public void move() {

        if(speedY < 0){
            // The character is moving up
            speedY = speedY * 2 / 3 + getSpeedTimeDecrease() / 2;
        }else{
            // the character is moving down
            this.speedY += getSpeedTimeDecrease();
        }
        if(this.speedY > getMaxSpeed()){
            // speed limit
            this.speedY = getMaxSpeed();
        }

        this.x += speedX;
        this.y += speedY;
    }

    @Override
    public int getPosX() {
        return 0;
    }

    @Override
    public int getPosY() {
        return 0;
    }

    @Override
    public void offsetTo(int offset) {

    }

    @Override
    public void hide(Canvas canvas) {
        this.x = -99999;
        this.draw(canvas);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void reset() {
        this.resetPosition();
    }

    @Override
    public void onCollision(Entity collider) {
        if(collider.getClass().toString().indexOf("DeathZone") > -1)
            view.gameOver();
    }

    @Override
    public boolean isRemovedOnCollision(Entity collider) {
        return false;
    }

    private float getSpeedTimeDecrease() {
        return view.getHeight() / 250;
    }

    private float getMaxSpeed() {
        return view.getHeight() / 51.2f;
    }

    @Override
    public Rect getHitbox() {
        return this.hitbox;
    }

    public void draw(Canvas canvas) {
        frameCount++;
        canvas.drawBitmap(this.animation.next(), x, y , null);
        this.hitbox.set(this.x, this.y, (this.x+this.width*2), (this.y+this.height*2));
        canvas.drawRect(this.hitbox, this.hitboxPaint);
    }
}
