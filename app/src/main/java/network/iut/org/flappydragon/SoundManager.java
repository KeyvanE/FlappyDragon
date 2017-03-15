package network.iut.org.flappydragon;

import android.content.Context;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Keyvan on 10/02/2017.
 */

public class SoundManager {
    /** SOUNDS **/
    public static final int TRACK_MUSIC = R.raw.happy;
    public static final int TRACK_JUMP = R.raw.jump3;
    public static final int TRACK_COIN = R.raw.coin;
    private Context context;

    private HashMap<String, Sound> sounds;

    public SoundManager(Context context) {
        this.context = context;
        this.sounds = new HashMap<String, Sound>();
    }

    public Sound add(int trackID, String name) {
        Sound added = new Sound(this.context, trackID);
        this.sounds.put(name, added);
        return added;
    }

    public Sound get(String name) {
        return this.sounds.get(name);
    }
}
