package interfaces;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public interface Entity {

    Rect getHitbox();

    void draw(Canvas canvas);

    void move();

    int getPosX();

    int getPosY();

    void offsetTo(int offset);

    void hide(Canvas canvas);

    void destroy();

    void reset();

    void onCollision(Entity collider);

    boolean isRemovedOnCollision(Entity collider);

}
