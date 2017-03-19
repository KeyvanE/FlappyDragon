package entities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

import common.Animation;
import common.Sound;
import common.Util;
import interfaces.Entity;
import network.iut.org.flappydragon.EntityManager;
import network.iut.org.flappydragon.GameView;
import network.iut.org.flappydragon.R;

import static network.iut.org.flappydragon.SoundManager.TRACK_COIN;

/**
 * Created by Keyvan on 13/03/2017.
 */

public class DeathZone implements Entity {

    private Random random = new Random();

    private Animation animation;
    private GameView view;
    private Context context;
    private EntityManager manager;
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

    public DeathZone(Context context, GameView view, EntityManager manager) {
        this.context = context;
        this.view = view;
        this.manager = manager;

        this.animation = new Animation();
        this.width = 1920;
        this.height = 150;
        this.speedX = 0;
        this.posX = 0;
        this.posY = context.getResources().getDisplayMetrics().heightPixels-150;
        this.hitbox = new Rect(this.posX, this.posY, (this.posX+this.width), (this.posY+this.height));
        this.hitboxPaint.setAlpha(50);
    }

    @Override
    public Rect getHitbox() {
        return this.hitbox;
    }

    public void draw(Canvas canvas) {

        this.hitbox.set(this.posX, this.posY, (this.posX+this.width), (this.posY+this.height));
        canvas.drawRect(this.hitbox, this.hitboxPaint);
    }

    @Override
    public void move() {

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
    public void reset() {

    }

    @Override
    public void hide(Canvas canvas) {

    }

    @Override
    public void onCollision(Entity collider) {

    }

    @Override
    public boolean isRemovedOnCollision(Entity collider) {
        return false;
    }

}
