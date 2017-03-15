package network.iut.org.flappydragon;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Keyvan on 15/03/2017.
 */

public class Animation {

    ArrayList<Bitmap> steps = new ArrayList<Bitmap>();
    private int duration = 0;
    private int framecount = 0;
    private int step = 0;
    private Bitmap currentFrame;

    public Animation() { }

    /**
     * Ajoute une frame à l'animation
     * @param image
     */
    public void addStep(Bitmap image) {

        steps.add(image);
        this.duration = steps.size();

    }

    /**
     * Passe à la prochaine frame d'animation
     * @return
     */
    public Bitmap next() {
        this.framecount++;

        if(framecount%4==0)
            step++;

        if(step==this.duration)
            step=0;

        return steps.get(step);
    }

}
