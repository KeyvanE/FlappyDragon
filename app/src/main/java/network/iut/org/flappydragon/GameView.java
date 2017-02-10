package network.iut.org.flappydragon;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Timer;
import java.util.TimerTask;

public class GameView extends SurfaceView implements Runnable {
    public static final long UPDATE_INTERVAL = 50; // = 20 FPS
    private SurfaceHolder holder;
    private boolean paused = true;
    private Timer timer = new Timer();
    private TimerTask timerTask;
    private Player player;
    private Background background;
    private SoundManager soundManager;

    private Sound backgroundMusic;
    private Sound jumpSound;

    public GameView(Context context) {
        super(context);
        player = new Player(context, this);
        background = new Background(context, this);
        holder = getHolder();

        backgroundMusic = new Sound(context, SoundManager.TRACK_MUSIC).setVolume(0.8f, 0.8f).setLoop(true);
        jumpSound = new Sound(context, SoundManager.TRACK_JUMP);
        //load sounds and music
//        soundManager = new SoundManager(context);
//        soundManager.add(SoundManager.TRACK_MUSIC, "music").setVolume(0.8f, 0.8f).setLoop(true);
//        soundManager.add(SoundManager.TRACK_JUMP, "jump").setVolume(1.0f, 1.0f).setLoop(false);

        new Thread(new Runnable() {
            @Override
            public void run() {
                GameView.this.run();
            }
        }).start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        performClick();
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(paused) {
                resume();
            } else {
                this.player.onTap();
                jumpSound.play();
            }
        }
        return true;
    }

    private void resume() {
        paused = false;
        startTimer();
        backgroundMusic.play();
    }

    private void startTimer() {
        Log.i("TIMER", "START TIMER");
        setUpTimerTask();
        timer = new Timer();
        timer.schedule(timerTask, UPDATE_INTERVAL, UPDATE_INTERVAL);
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
        if (timerTask != null) {
            timerTask.cancel();
        }
    }

    private void setUpTimerTask() {
        stopTimer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                GameView.this.run();
            }
        };
    }

    @Override
    public void run() {
        background.move();
        player.move();

        draw();
    }

    private void draw() {
        while(!holder.getSurface().isValid()){
			/*wait*/
            try { Thread.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        Canvas canvas = holder.lockCanvas();
        if (canvas != null) {
            drawCanvas(canvas);
        }
        holder.unlockCanvasAndPost(canvas);
    }

    private void drawCanvas(Canvas canvas) {
        background.draw(canvas);
        player.draw(canvas);
        if (paused) {
            canvas.drawText("PAUSED", canvas.getWidth() / 2, canvas.getHeight() / 2, new Paint());
        }
    }

}
