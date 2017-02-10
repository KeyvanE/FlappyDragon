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

/**
 * Created by Keyvan on 10/02/2017.
 */

public class SoundManager extends AsyncTask<Void, Void, Void> {
    /** SOUNDS **/
    public static final int TRACK_MUSIC = R.raw.happy;
    public static final int TRACK_JUMP = R.raw.jump;

    private Context context;
    private GameView view;
    private SoundPool soundPool;
    private ArrayList<Sound> sounds;
    private int selectedTrack;
    private float volumeL;
    private float volumeR;
    private boolean loop;

    public SoundManager(Context context, GameView view) {
        this.context = context;
        this.view = view;
    }

    public void add(Sound sound) {
        this.sounds.add(sound);
    }

    public void load(int track, float volumeL, float volumeR, boolean loop) {
        this.selectedTrack = track;
        this.volumeL = volumeL;
        this.volumeR = volumeR;
        this.loop = loop;
    }

    public void play(int trackID) {

    }

    @Override
    protected Void doInBackground(Void... params) {
        MediaPlayer player = MediaPlayer.create(this.context, selectedTrack);
        player.setLooping(this.loop); // Set looping
        player.setVolume(this.volumeL, this.volumeR);
        player.start();

        return null;
    }
}
