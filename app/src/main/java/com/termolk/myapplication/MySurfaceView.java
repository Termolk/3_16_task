package com.termolk.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    float r;
    float x, y;
    boolean started = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();
        r = 0;
        started = true;
        return super.onTouchEvent(event);
    }

    @Override
    public void run() {
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);

            while (true){
                Canvas canvas = getHolder().lockCanvas();
                canvas.drawColor(Color.BLUE);
                if (started){
                    canvas.drawCircle(x, y, r, paint);
                }
                getHolder().unlockCanvasAndPost(canvas);
                r += 1;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    public MySurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

}
