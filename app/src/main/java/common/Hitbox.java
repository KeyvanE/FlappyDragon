package common;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Hitbox {
    private int left;
    private int top;
    private int right;
    private int bottom;
    private Rect rect;
    private Paint paint;

    public Hitbox(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.paint = new Paint();
        this.paint.setAlpha(50);
        this.rect = new Rect(left, top, right, bottom);
    }

    public void set(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(this.rect, this.paint);
    }

    public Rect getRect() {
        return this.rect;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public boolean intersects(Hitbox hitbox) {
        return this.rect.intersect(hitbox.getRect());
    }

}
