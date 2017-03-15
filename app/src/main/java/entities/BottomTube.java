package entities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

import interfaces.Entity;
import network.iut.org.flappydragon.Animation;
import network.iut.org.flappydragon.GameView;
import network.iut.org.flappydragon.SoundManager;

/**
 * Created by Keyvan on 13/03/2017.
 */

public class BottomTube implements Entity {

    public SoundManager soundManager;
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
    private int offset = 0;
    private float speedX;
    private float speedY;

    public BottomTube(Context context, GameView view) {
        this.context = context;
        this.view = view;
        this.width = 150;
        this.height = (context.getResources().getDisplayMetrics().heightPixels/2)-300;
        this.speedX = -10;
        this.posX = context.getResources().getDisplayMetrics().widthPixels+150;
        this.posY = 0;
        this.hitbox = new Rect(this.posX, this.posY, (this.posX+this.width), (this.posY+this.height));
        this.hitboxPaint.setAlpha(50);
    }

    @Override
    public Rect getHitbox() {
        return this.hitbox;
    }

    public void draw(Canvas canvas) {
        if(this.posX <= -this.width) {
            resetPosition();
        }

        this.hitbox.set(this.posX, this.posY, (this.posX+this.width), (this.posY+this.height));
        canvas.drawRect(this.hitbox, this.hitboxPaint);

    }

    public void resetPosition() {
        this.posX = context.getResources().getDisplayMetrics().widthPixels;
        this.posY = 0;
    }

    public void move() {
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
        this.posX += offset;
    }

    @Override
    public void destroy() {
    }

    @Override
    public void onCollision(Entity collider) {
        this.view.gameOver();
    }

    @Override
    public boolean isRemovedOnCollision(Entity collider) {
        return false;
    }
}
