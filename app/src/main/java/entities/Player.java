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
import network.iut.org.flappydragon.Util;

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

        this.animation = new Animation(100);
        this.animation.addStep(0, Util.getScaledBitmapAlpha8(context, R.drawable.sof1));
        this.animation.addStep(1, Util.getScaledBitmapAlpha8(context, R.drawable.sof2));
        this.animation.addStep(2, Util.getScaledBitmapAlpha8(context, R.drawable.sof3));

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

        // manage frames
/*        if(row != 3){
            // not dead
            if(speedY > getTabSpeed() / 3 && speedY < getMaxSpeed() * 1/3){
                row = 0;
            }else if(speedY > 0){
                row = 1;
            }else{
                row = 2;
            }
        }
*/
        this.x += speedX;
        this.y += speedY;
    }

    @Override
    public void nextFrame() {

    }

    @Override
    public float getPosX() {
        return 0;
    }

    @Override
    public float getPosY() {
        return 0;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void onCollision() {

    }

    @Override
    public boolean isRemovedOnCollision() {
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
