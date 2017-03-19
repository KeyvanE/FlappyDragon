package network.iut.org.flappydragon;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

import common.Sound;

/**
 * Created by Keyvan on 10/02/2017.
 */

public class SoundManager {
    /** SOUNDS **/
    public static final int TRACK_MUSIC = R.raw.happy;
    public static final int TRACK_JUMP = R.raw.jump;
    public static final int TRACK_COIN = R.raw.coin;
    private Context context;

    private ArrayList<Sound> sounds;

    public SoundManager(Context context) {
        this.context = context;
        this.sounds = new ArrayList<Sound>();
    }

    public Sound get(int trackID) {
        Sound sound = new Sound(context, trackID);
        this.sounds.add(sound);
        return sound;
    }

    public void stop() {
        for(Sound sound : sounds) {
            sound.seekTo(0);
            sound.pause();
        }
    }
}
