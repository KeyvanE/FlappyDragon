package network.iut.org.flappydragon;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Keyvan on 10/02/2017.
 */

public class Sound {
    private int trackID;
    private float volumeL;
    private float volumeR;
    private boolean loop;
    private MediaPlayer player;
    private Context context;

    public Sound(Context context, int trackID, float volumeL, float volumeR, boolean loop) {
        this.player = MediaPlayer.create(this.context, trackID);
    }
}
