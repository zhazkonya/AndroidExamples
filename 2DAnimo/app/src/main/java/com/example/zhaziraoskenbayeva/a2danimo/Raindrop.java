package com.example.zhaziraoskenbayeva.a2danimo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zhaziraoskenbayeva on 24/10/17.
 */

public class Raindrop extends View {
    int frames = 0;
    int xMax, yMax ;
    int ballRadius = 10;
    List<RectF> ballList = new ArrayList<RectF>();
    public Raindrop(Context context) {
        super(context);
    }


    public Raindrop(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        xMax = canvas.getWidth();
        yMax = canvas.getHeight();

        canvas.drawColor(Color.CYAN);

        update(canvas);

        postInvalidate();

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void update(Canvas canvas) {
        frames++;

        Paint magenta = new Paint();
        magenta.setColor(Color.MAGENTA);

        for(RectF ball : ballList){
            ball.set(ball.left, ball.top + 2*ballRadius,
                    ball.right, ball.bottom+2*ballRadius);
            canvas.drawOval(ball, magenta);
        }

        if(frames % 5 ==0){
            Random r = new Random();
            int newX = r.nextInt(xMax);
            int newY =0;

            RectF newBall = new RectF(newX, newY,
                    newX +2*ballRadius, newY + 2*ballRadius);
            canvas.drawOval(newBall, magenta);
            ballList.add(newBall);
        }

    }
}
