package entities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import interfaces.Entity;
import network.iut.org.flappydragon.Animation;
import network.iut.org.flappydragon.GameView;
import network.iut.org.flappydragon.R;
import utility.Util;

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

    public Player(Context context, GameView view) {

        this.animation = new Animation();
        this.animation.addStep(Util.getScaledBitmapAlpha8(context, R.drawable.rocket1));
        this.animation.addStep(Util.getScaledBitmapAlpha8(context, R.drawable.rocket2));
        this.animation.addStep(Util.getScaledBitmapAlpha8(context, R.drawable.rocket3));
        this.animation.addStep(Util.getScaledBitmapAlpha8(context, R.drawable.rocket4));
        this.animation.addStep(Util.getScaledBitmapAlpha8(context, R.drawable.rocket5));
        this.animation.addStep(Util.getScaledBitmapAlpha8(context, R.drawable.rocket6));

        this.width = 100;
        this.height = 100;

        this.y = context.getResources().getDisplayMetrics().heightPixels / 2;	// Startposition in the middle of the screen
        this.x = context.getResources().getDisplayMetrics().widthPixels / 6;

        this.view = view;
        this.speedX = 0;

        this.hitbox = new Rect(this.x, this.y, (this.x+this.width*2), (this.y+this.height*2));
        this.hitboxPaint.setAlpha(0);

    }

    public void onTap() {
        this.speedY = getTabSpeed();
        this.y += getPosTabIncrease();
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
    public void destroy() {

    }

    @Override
    public void onCollision(Entity collider) {

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
