package network.iut.org.flappydragon;

import android.content.Context;
import android.graphics.Canvas;

import java.util.ArrayList;

import entities.BottomTube;
import entities.Coin;
import entities.TopTube;
import interfaces.Entity;

public class EntityManager {
    private GameView view;
    private Context context;
    private int frameCounter = 0;

    /**
     * Common elements
     */
    TopTube TOP_TUBE_1;
    TopTube TOP_TUBE_2;
    BottomTube BOTTOM_TUBE_1;
    BottomTube BOTTOM_TUBE_2;

    private ArrayList<Entity> entities = new ArrayList<Entity>();

    public EntityManager(Context context, GameView view) {
        this.view = view;
        this.context = context;

        TOP_TUBE_1 = new TopTube(context, view);
        TOP_TUBE_2 = new TopTube(context, view);
        BOTTOM_TUBE_1 = new BottomTube(context, view);
        BOTTOM_TUBE_2 = new BottomTube(context, view);
    }

    public void draw(Canvas canvas) {
        for(Entity el : entities) {
            el.draw(canvas);
        }
    }

    public void update() {
        this.frameCounter++;

        if(this.frameCounter == 50)
            entities.add(new Coin(context, view));

        if(this.frameCounter == 100) {
            //make space between first and second tube
            TOP_TUBE_2.offsetTo(530);
            entities.add(TOP_TUBE_1);
            entities.add(TOP_TUBE_2);
            BOTTOM_TUBE_2.offsetTo(530);
            entities.add(BOTTOM_TUBE_1);
            entities.add(BOTTOM_TUBE_2);
        }

        if(this.frameCounter == 150)
            entities.add(new Coin(context, view));

        if(this.frameCounter == 250)
            entities.add(new Coin(context, view));

        for(Entity el : entities) {
            el.move();
        }
    }

    /**
     * Retourne l'entité qui est en collision avec l'entité passée en paramètre
     * @param entity
     * @return
     */
    public Entity getCollider(Entity entity) {
        Entity collider = null;
        for(Entity el : entities)
        {
            if(entity.getHitbox().intersect(el.getHitbox()))
                collider = el;
        }
        return collider;
    }

    /**
     * Détruit une entité (visuellement ou complétement, selon sa configuration interne)
     *
     */
    public void collide(Entity culprit, Entity victim) {

        victim.onCollision(culprit);
        culprit.onCollision(victim);

        if(victim.isRemovedOnCollision(culprit))
            entities.remove(victim);

        if(culprit.isRemovedOnCollision(victim))
            entities.remove(victim);
    }

}
