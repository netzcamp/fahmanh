package haqqi.anim.progressindicator;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Animatable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

/**
 * Created by haqqi on 5/20/15.
 */
public class StackedBarIndicator extends View {

    ObjectAnimator animator;

    private static final long FRAME_DURATION = 1000/60;
    private static final long ANIMATION_DURATION = 1000;

    private Paint drawPaint;
    private BarShape barShape;
    private Interpolator mInterpolator;

    private boolean isRunning;

    private float timeFrame;
    private int color;

    private float mStartTime = 0;

    private float initThickness = 20, initMaximumWidth = 120;

    public StackedBarIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);

        mInterpolator = new AccelerateDecelerateInterpolator();
        timeFrame = 0.0f;
        isRunning = false;

        barShape = new BarShape(initThickness, initMaximumWidth, Color.parseColor("#343fa7"), new Point((int) (getMeasuredHeight() / 2.0f), (int) (getMeasuredHeight() / 2.0f)));
        runAnimationSample();
    }

    public float getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(float timeFrame) {
        this.timeFrame = timeFrame;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.postInvalidateOnAnimation();
        } else {
            this.invalidate();
        }
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    //    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        //Get the width measurement
//        int widthSize = MeasureUtils.getMeasurement(widthMeasureSpec, getDesiredWidth());
//
//        //Get the height measurement
//        int heightSize = MeasureUtils.getMeasurement(heightMeasureSpec, getDesiredHeight());
//
//        //MUST call this to store the measurements
//        setMeasuredDimension(widthSize, heightSize);
//    }

    @Override
    protected void onDraw(Canvas canvas) {


        for (int i = 0; i < 4; i++) {
            timeFrame += 0.25 * i;

            float newCenterY = positionEvaluator(timeFrame, getMeasuredHeight() / 2.0f, getMeasuredHeight() / 2.0f + 30);
            float newAlpha = alphaEvaluator(timeFrame, 0, 255);
            float scalingFactor = scaleEvaluator(timeFrame);

            barShape.setMaximumWidth(initMaximumWidth * scalingFactor);
            barShape.setThickness(initThickness * scalingFactor);
            barShape.setCentroid(getMeasuredWidth() / 2.0f, newCenterY);
            barShape.setAlpha((int) newAlpha);
            barShape.draw(canvas, drawPaint);

        }
    }

    private float positionEvaluator(float fraction, float start, float end) {
        return (float) (start + (end - start) * -1 * Math.sin(fraction * 2 * Math.PI));
    }

    private float alphaEvaluator(float fraction, float start, float end) {
        return (float) ((end - start) * ((Math.cos(fraction * 2 * Math.PI) + 1)/2.0));
    }

    private float scaleEvaluator(float fraction) {
        return (float) (Math.cos(fraction * 2 * Math.PI)/4.0 + 0.75);
    }

    private void runAnimationSample()
    {
//        new Thread(new Runnable() {
//            @Override
//            public void run()
//            {
//                while(true)
//                {
//
//                    if (mStartTime >= 1)
//                        mStartTime = 0;
//                    else
//                        mStartTime += 0.01;
//                    timeFrame = mInterpolator.getInterpolation(mStartTime);
//
//                    postInvalidate();
//
//                    try
//                    {
//                        Thread.sleep(10);
//                    }
//                    catch(Exception ex) {
//
//                    }
//                }
//            }
//        }).start();

        final ObjectAnimator animator = ObjectAnimator.ofFloat(this, "timeFrame", 0f, 1f);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(1000).setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
    }



//    private final Runnable mUpdater = new Runnable() {
//        @Override
//        public void run() {
//            long now = AnimationUtils.currentAnimationTimeMillis();
//            long duration = now - mStartTime;
//            if (duration >= ANIMATION_DURATION) {
//                mStartTime = now;
//                timeFrame = 0;
//            } else {
//                float fraction = duration / (float) ANIMATION_DURATION;
//                timeFrame = mInterpolator.getInterpolation(fraction);
//            }
//
//            inv
//            scheduleSelf(mUpdater, SystemClock.uptimeMillis() + FRAME_DURATION);
//            invalidateSelf();
//        }
//    };
}
