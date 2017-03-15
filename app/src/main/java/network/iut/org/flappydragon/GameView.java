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

import static network.iut.org.flappydragon.SoundManager.TRACK_COIN;

public class GameView extends SurfaceView implements Runnable {
    public static final long UPDATE_INTERVAL = 50; // = 20 FPS
    private SurfaceHolder holder;
    private boolean paused = true;
    public Timer timer = new Timer();
    private TimerTask timerTask;
    private Player player;
    private Background background;
    public SoundManager soundManager;
    private EntityManager entityManager;
    private Canvas canvas;

    private Sound backgroundMusic;
    private Sound jumpSound;

    public GameView(Context context) {
        super(context);
        player = new Player(context, this);
        background = new Background(context, this);
        entityManager = new EntityManager(context, this);

        holder = getHolder();

        backgroundMusic = new Sound(context, SoundManager.TRACK_MUSIC).setVolume(0.8f, 0.8f).setLoop(true);
        jumpSound = new Sound(context, SoundManager.TRACK_JUMP);

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
        entityManager.update();

        draw();
    }

    private void draw() {
        while(!holder.getSurface().isValid()){
			/*wait*/
            try { Thread.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        Canvas canvas = holder.lockCanvas();
        this.canvas = canvas;
        if (canvas != null) {
            drawCanvas(canvas);
        }
        try {
            holder.unlockCanvasAndPost(canvas);
        } catch (IllegalStateException e) {
        }
    }

    private void drawCanvas(Canvas canvas) {
        checkForCollision();
        background.draw(canvas);
        player.draw(canvas);
        entityManager.draw(canvas);

        entityManager.getCollider(player);
        if (paused) {
            canvas.drawText("PAUSED", canvas.getWidth() / 2, canvas.getHeight() / 2, new Paint());
        }
    }

    public void gameOver() {
        canvas.drawText("GAME OVER", canvas.getWidth() / 2, canvas.getHeight() / 2, new Paint());
    }

    private void checkForCollision() {
        Entity collider = entityManager.getCollider(player);
        if(collider != null)
            entityManager.destroy(collider);
    }

}
