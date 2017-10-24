package com.example.zhaziraoskenbayeva.a2danimo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhaziraoskenbayeva on 24/10/17.
 */

public class Ball extends View {
    int xMin =0, yMin=0, xMax, yMax;
    int ballRadius = 10;
    int ballX = 0, ballY =0;
    int xSpeed = 10, ySpeed = 10;

    public Ball(Context context) {
        super(context);
    }

    public Ball(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        xMax = canvas.getWidth();
        yMax = canvas.getHeight();

        canvas.drawColor(Color.CYAN);

        Paint magenta = new Paint();
        magenta.setColor(Color.MAGENTA);

        canvas.drawOval(new RectF(
                ballX, ballY,
                ballX + 2*ballRadius, ballY + 2*ballRadius),
                magenta);

        update(canvas);

        postInvalidate();

        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void update(Canvas canvas) {
        ballX += xSpeed;
        ballY += ySpeed;
        if(ballX + 2*ballRadius > xMax){
            //right
            xSpeed = -xSpeed;
            ballX = xMax - 2*ballRadius;
        }else if(ballX < 0){
            //left
            xSpeed = -xSpeed;
            ballX = xMin;
        }else if(ballY+2*ballRadius > yMax){
            //bottom
            ySpeed = -ySpeed;
            ballY = yMax - 2*ballRadius;
        }else if(ballY < 0){
            //top
            ySpeed = -ySpeed;
            ballY = yMin;
        }
    }
}
