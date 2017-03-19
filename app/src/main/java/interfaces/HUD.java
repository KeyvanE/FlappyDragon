package interfaces;

import android.graphics.Canvas;
import android.graphics.Rect;

public interface HUD {

    void draw(Canvas canvas);

    int getPosX();

    int getPosY();

    void destroy();

    void reset();

}
