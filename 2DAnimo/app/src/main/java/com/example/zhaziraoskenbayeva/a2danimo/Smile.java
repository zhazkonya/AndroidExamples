package com.example.zhaziraoskenbayeva.a2danimo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhaziraoskenbayeva on 24/10/17.
 */

public class Smile extends View {

    public Smile(Context context) {
        super(context);
    }

    public Smile(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //canvas.drawColor(Color.CYAN);

        //smileFace(canvas);

        //targetFirgue(canvas);

        drawBitmap(canvas);
    }

    private void drawBitmap(Canvas canvas) {
        Bitmap bit = BitmapFactory.decodeResource(getResources(), R.drawable.umb);
        Bitmap bit1 = Bitmap.createScaledBitmap(bit, 100,100,true);
        canvas.drawBitmap(bit1, 10,10,null);
    }

    private void targetFirgue(Canvas canvas) {

        Paint white = new Paint();
        white.setColor(Color.WHITE);
        Paint red = new Paint();
        red.setColor(Color.RED);

        int w = canvas.getWidth();
        int h = canvas.getHeight();

        for(int i = 0; i < 5; i++){
            canvas.drawOval(new RectF(
                    /*x1*/ w*i/10, h*i/10/*y1*/,
                    /*x2*/ w*(10-i)/10, h*(10-i)/10/*x2*/),
                    (i%2 == 0) ? red: white );
        }
    }

    private void smileFace(Canvas canvas) {
        Paint yellow = new Paint();
        yellow.setColor(Color.YELLOW);
        yellow.setStyle(Paint.Style.FILL_AND_STROKE);

        Paint red = new Paint();
        red.setColor(Color.RED);
        red.setStyle(Paint.Style.FILL_AND_STROKE);

        Paint blue = new Paint();
        blue.setColor(Color.BLUE);
        blue.setStyle(Paint.Style.FILL_AND_STROKE);

        Paint textPaint = new Paint();
        textPaint.setTextSize(40);
        textPaint.setColor(Color.BLUE);
        textPaint.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC));

        canvas.drawOval(new RectF(100,100, 400,400), yellow);
        canvas.drawOval(150, 150, 200,200, blue);
        canvas.drawOval(300, 150, 350,200, blue);
        canvas.drawRect(200, 300, 300, 320, red);

        canvas.drawText("Smile)))", 200, 500, textPaint);
    }

}
