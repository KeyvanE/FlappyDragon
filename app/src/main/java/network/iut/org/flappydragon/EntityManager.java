package network.iut.org.flappydragon;

import android.content.Context;
import android.graphics.Canvas;

import java.util.ArrayList;

import entities.Coin;
import entities.DeathZone;
import entities.Ennemy;
import interfaces.Entity;

public class EntityManager {
    private GameView view;
    private Context context;
    private int frameCounter = 0;
    private boolean gameIsOver = false;

    private ArrayList<Entity> entities = new ArrayList<Entity>();

    public EntityManager(Context context, GameView view) {
        this.view = view;
        this.context = context;
        entities.add(new DeathZone(context, view, this));
    }

    public void draw(Canvas canvas) {
        if(!this.gameIsOver)
        {
            for(Entity el : entities) {
                el.draw(canvas);
            }
        }
    }

    public void update() {
        this.frameCounter++;

        if(this.frameCounter == 50)
            entities.add(new Coin(context, view, this));

        if(this.frameCounter == 100)
            entities.add(new Ennemy(context, view, this));

        if(this.frameCounter == 150)
            entities.add(new Coin(context, view, this));

        if(this.frameCounter == 200)
            entities.add(new Ennemy(context, view, this));

        if(this.frameCounter == 250)
            entities.add(new Coin(context, view, this));

        if(this.frameCounter == 300)
            entities.add(new Ennemy(context, view, this));

        if(this.frameCounter == 400)
            entities.add(new Ennemy(context, view, this));

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

    public void reset() {
        for(Entity el : entities)
            el.reset();
    }

}
