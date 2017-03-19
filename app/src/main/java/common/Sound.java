package common;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;

public class Sound extends AsyncTask<Void, Void, Void> {
    private int trackID;
    private float volumeL = 1.0f;
    private float volumeR = 1.0f;
    private boolean loop = false;
    private MediaPlayer player;
    private Context context;

    public Sound(Context context, int trackID) {
        this.trackID = trackID;
        this.player = MediaPlayer.create(context, trackID);
        this.player.setVolume(volumeL, volumeR);
        this.player.setLooping(loop);
    }

    public void play() {
        if(this.player.isPlaying()) {
            this.player.seekTo(0);
        }
        this.player.start();
    }

    public void pause() {
        this.player.pause();
    }

    public void seekTo(int pos) {
        this.player.seekTo(pos);
    }

    public void stop() {
        this.player.stop();
    }

    public int getTrackID() {
        return trackID;
    }

    public void setTrackID(int trackID) {
        this.trackID = trackID;
        this.player.selectTrack(this.trackID);
    }

    public float getVolumeL() {
        return volumeL;
    }

    public void setVolumeL(float volumeL) {
        this.volumeL = volumeL;
        this.player.setVolume(this.volumeL, this.volumeR);
    }

    public float getVolumeR() {
        return volumeR;
    }

    public void setVolumeR(float volumeR) {
        this.volumeR = volumeR;
        this.player.setVolume(this.volumeL, this.volumeR);
    }

    public Sound setVolume(float volumeL, float volumeR) {
        this.volumeL = volumeL;
        this.volumeR = volumeR;
        this.player.setVolume(this.volumeL, this.volumeR);
        return this;
    }
    public boolean isLoop() {
        return loop;
    }

    public Sound setLoop(boolean loop) {
        this.loop = loop;
        this.player.setLooping(this.loop);
        return this;
    }

    @Override
    protected Void doInBackground(Void... params) {
        this.player.start();
        return null;
    }
}
