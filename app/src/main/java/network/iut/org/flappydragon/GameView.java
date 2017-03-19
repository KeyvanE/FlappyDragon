package network.iut.org.flappydragon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Timer;
import java.util.TimerTask;

import activities.GameActivity;
import activities.GameOver;
import entities.Player;
import interfaces.Entity;
import common.Sound;
import misc.Background;
import misc.Floor;

public class GameView extends SurfaceView implements Runnable {
    public static final long UPDATE_INTERVAL = 50; // = 20 FPS
    private SurfaceHolder holder;
    private boolean paused = true;
    public Timer timer = new Timer();
    private TimerTask timerTask;
    private Player player;
    private Background background;
    private Floor floor;
    public SoundManager soundManager;
    private EntityManager entityManager;
    private HUDManager HUDmanager;
    private Canvas canvas;
    private Context context;
    private Thread thread;

    private Sound backgroundMusic;
    private Sound jumpSound;

    public GameView(Context context) {
        super(context);
        context = context;
        soundManager = new SoundManager(context);
        entityManager = new EntityManager(context, this);
        HUDmanager = new HUDManager(context, this);

        player = new Player(context, this, entityManager);
        background = new Background(context, this);
        floor = new Floor(context, this);
        holder = getHolder();

        backgroundMusic = soundManager.get(SoundManager.TRACK_MUSIC).setVolume(0.8f, 0.8f).setLoop(true);
        jumpSound = soundManager.get(SoundManager.TRACK_JUMP);

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
            }
            this.player.onTap();
            jumpSound.play();
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

    private void pause() {
        this.paused = true;
        this.stopTimer();
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
            floor.move();
            player.move();
            entityManager.update();
            HUDmanager.update();

            draw();
    }

    private void draw() {
        while(!holder.getSurface().isValid()){
			/*wait*/
            try { Thread.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        Canvas canvas = holder.lockCanvas();
        if (canvas != null) {
            this.canvas = canvas;
            drawCanvas(canvas);
        }
        try {
            holder.unlockCanvasAndPost(canvas);
        } catch (IllegalStateException e) {
        }
    }

    private void drawCanvas(Canvas canvas) {
        if (paused) {
            canvas.drawText("PAUSED", canvas.getWidth() / 2, canvas.getHeight() / 2, new Paint());
        }

        checkForCollision();
        background.draw(canvas);
        floor.draw(canvas);
        player.draw(canvas);
        entityManager.draw(canvas);
        HUDmanager.draw(canvas);
    }

    public void gameOver() {
        this.pause();

//        int score = HUDmanager.SCORE.getScore();
//        int distance = HUDmanager.DISTANCE.getDistance();
        Intent intent = new Intent();
        intent.setClass(this.getContext(), GameOver.class);
        this.getContext().startActivity(intent);

        this.soundManager.stop();
        this.player.reset();
        this.entityManager.reset();
        this.HUDmanager.reset();
    }

    private void checkForCollision() {

        Entity victim = entityManager.getCollider(player);
        if(victim != null)
            entityManager.collide(player, victim);
    }

    public void updateScore(int add) {
        this.HUDmanager.SCORE.add(add);
    }

}
