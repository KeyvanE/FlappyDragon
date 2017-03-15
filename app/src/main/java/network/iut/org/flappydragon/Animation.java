package network.iut.org.flappydragon;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by Keyvan on 15/03/2017.
 */

public class Animation {

    HashMap<Integer, Bitmap> steps = new HashMap<Integer, Bitmap>();
    private int frameCount = 0;
    private int duration = 0;

    public Animation(int duration) {
        this.duration = duration;
    }

    public void addStep(Integer frame, Bitmap image) {
        steps.put(frame, image);
    }

    public Bitmap next() {
        frameCount++;

        if(frameCount >= duration)
            frameCount = 0;

        Bitmap next = steps.get(frameCount);

        if(next != null)
            return next;
        else
            return steps.get(0);
    }

}
