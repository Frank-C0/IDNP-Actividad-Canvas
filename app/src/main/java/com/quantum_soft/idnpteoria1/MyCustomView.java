package com.quantum_soft.idnpteoria1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

public class MyCustomView extends View {
    private Paint paint;
    private boolean isTouched;

    public MyCustomView(Context context) {
        super(context);
        init();
    }

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        isTouched = false;
        setClickable(true);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        // Get the dimensions of the view
        int width = getWidth();
        int height = getHeight();

        // widthLine is the width of the lines
        float widthLine = 20f;

        // Draw the canvas border
        paint.setStrokeWidth(1);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0, 0, width, height, paint);

        // if the view is touched, draw a yellow arc, else draw the happy face
        if(isTouched){
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.YELLOW);
            canvas.drawArc(0+widthLine, 0+widthLine, width-widthLine,height-widthLine, 0,270, true, paint);
        }else {
            // Draw the mouth
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(widthLine);
            canvas.drawArc(width / 3f, height / 3f + 100, width / 3f * 2, height / 3f * 2 + 100, 0, 180, true, paint);

            // Draw the eyes
            paint.setStrokeWidth(widthLine);
            paint.setColor(Color.BLACK);
            canvas.drawCircle(width / 2f, height / 2f, width / 2f - widthLine, paint);

            // draw the face contour
            paint.setStrokeWidth(widthLine);
            paint.setColor(Color.RED);
            canvas.drawCircle(width / 3f, height / 3f, 50f, paint);
            canvas.drawCircle(width / 3f * 2, height / 3f, 50f, paint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isTouched = !isTouched;
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}