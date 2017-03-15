package entities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

import network.iut.org.flappydragon.Animation;
import interfaces.Entity;
import network.iut.org.flappydragon.GameView;
import network.iut.org.flappydragon.R;
import utility.Sound;
import network.iut.org.flappydragon.SoundManager;
import utility.Util;

import static network.iut.org.flappydragon.SoundManager.TRACK_COIN;

/**
 * Created by Keyvan on 13/03/2017.
 */

public class Coin implements Entity {

    private Sound pickupSound;
    private Random random = new Random();

    private Animation animation;
    private GameView view;
    private Context context;
    private Rect hitbox;
    private Paint hitboxPaint = new Paint();
    private int height;
    private int width;
    private int posX = 0;
    private int posY = 0;
    private int speedX;
    private int speedY;
    private int framecount;
    private int speedboostInterval = 100;

    public Coin(Context context, GameView view) {
        this.context = context;
        this.view = view;
        this.pickupSound = new Sound(context, TRACK_COIN);
        this.animation = new Animation();
        this.animation.addStep(Util.getScaledBitmapAlpha8(context, R.drawable.coin));
        this.width = 50;
        this.height = 50;
        this.speedX = -10;
        this.posX = context.getResources().getDisplayMetrics().widthPixels;
        this.posY = random.nextInt(context.getResources().getDisplayMetrics().heightPixels);
        this.hitbox = new Rect(this.posX, this.posY, (this.posX+this.width*2), (this.posY+this.height*2));
        this.hitboxPaint.setAlpha(0);
    }

    @Override
    public Rect getHitbox() {
        return this.hitbox;
    }

    public void draw(Canvas canvas) {
        if(this.posX <= -50) {
            resetPosition();
        }

        this.hitbox.set(this.posX, this.posY, (this.posX+this.width*2), (this.posY+this.height*2));
        canvas.drawRect(this.hitbox, this.hitboxPaint);
        canvas.drawBitmap(this.animation.next(), this.posX, this.posY, null);

    }

    public void resetPosition() {
        this.posX = context.getResources().getDisplayMetrics().widthPixels;
        this.posY = random.nextInt(context.getResources().getDisplayMetrics().heightPixels);
    }

    public void move() {
        framecount++;
        updateSpeed();
        this.posX+=speedX;
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
    public void offsetTo(int offset) {

    }

    @Override
    public void destroy() {
    }

    @Override
    public void onCollision(Entity collider) {
        pickupSound.play();
        resetPosition();
    }

    @Override
    public boolean isRemovedOnCollision(Entity collider) {
        return false;
    }

    public void updateSpeed() {
        if(framecount%speedboostInterval==0)
            speedX--;
    }
}
