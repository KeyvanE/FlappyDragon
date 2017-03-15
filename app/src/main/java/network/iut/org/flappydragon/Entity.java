package network.iut.org.flappydragon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public interface Entity {

    Rect getHitbox();

    void draw(Canvas canvas);

    void move();

    void nextFrame();

    float getPosX();

    float getPosY();

    void destroy();

    void onCollision();

    boolean isRemovedOnCollision();
}
