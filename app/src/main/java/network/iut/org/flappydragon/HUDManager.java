package network.iut.org.flappydragon;

import android.content.Context;
import android.graphics.Canvas;

import java.util.ArrayList;

import hud.Distance;
import hud.Score;
import interfaces.HUD;

public class HUDManager {
    private GameView view;
    private Context context;
    private int frameCounter = 0;
    private int distanceMultiplier = 1;

    /**
     * Common elements
     */
    public Score SCORE;
    public Distance DISTANCE;

    private ArrayList<HUD> elements = new ArrayList<HUD>();

    public HUDManager(Context context, GameView view) {
        this.view = view;
        this.context = context;
        SCORE = new Score(context, view);
        DISTANCE = new Distance(context, view);
        this.elements.add(SCORE);
        this.elements.add(DISTANCE);
    }

    public void draw(Canvas canvas) {
        for(HUD el : elements) {
            el.draw(canvas);
        }
    }

    public void update() {
        this.frameCounter++;

        if(frameCounter%20==0)
            DISTANCE.add(distanceMultiplier);

        if(frameCounter%400==0)
            distanceMultiplier++;
    }

    public void reset() {
        for(HUD hud : elements)
            hud.reset();
    }

}
