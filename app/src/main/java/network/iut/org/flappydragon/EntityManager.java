package network.iut.org.flappydragon;

import android.content.Context;
import android.graphics.Canvas;

import java.util.ArrayList;

import entities.Coin;
import entities.TopTube;

public class EntityManager {
    private GameView view;
    private Context context;
    private int frameCounter = 0;

    private ArrayList<Float> listPosX = new ArrayList<Float>();
    private ArrayList<Float> listPosY = new ArrayList<Float>();

    private ArrayList<Entity> entities = new ArrayList<Entity>();

    public EntityManager(Context context, GameView view) {
        this.view = view;
        this.context = context;
    }

    public void draw(Canvas canvas) {
            for(Entity el : entities) {
                el.draw(canvas);
            }
    }

    public void update() {
        nextFrame();

        if(this.frameCounter == 50)
            entities.add(new Coin(context, view));

        if(this.frameCounter == 100)
            entities.add(new TopTube(context, view));

        if(this.frameCounter == 150)
            entities.add(new Coin(context, view));

        if(this.frameCounter == 250)
            entities.add(new Coin(context, view));

        if(this.frameCounter == 300)
            entities.add(new TopTube(context, view));

        for(Entity el : entities) {
            el.move();
        }
    }

    public void nextFrame() {
        this.frameCounter++;
    }

    public Entity getCollider(Entity entity) {
        Entity collider = null;
        for(Entity el : entities)
        {
            if(entity.getHitbox().intersect(el.getHitbox()))
                collider = el;
        }
        return collider;
    }

    public void destroy(Entity el) {
        el.onCollision();
        el.destroy();

        if(el.isRemovedOnCollision())
            entities.remove(el);
    }

}
