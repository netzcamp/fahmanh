package haqqi.anim.progressindicator;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.drawable.shapes.Shape;
import android.widget.ProgressBar;

/**
 * Created by haqqi on 5/20/15.
 */
public class BarShape extends Shape {

    private float thickness;
    private float maximumWidth;
    private int color;
    private int alpha;
    private Point centroid;

    private Paint barPaint;

    public BarShape(float thickness, float maximumWidth, int color) {
        this.centroid = new Point();
        this.thickness = thickness;
        this.maximumWidth = maximumWidth;
        this.color = color;


        setupPaint();
    }

    public BarShape(float thickness, float maximumWidth, int color, Point centroid) {
        this.centroid = centroid;
        this.thickness = thickness;
        this.maximumWidth = maximumWidth;
        this.color = color;

        setupPaint();
    }

    private void setupPaint() {
        barPaint = new Paint();
        barPaint.setColor(color);
        barPaint.setAntiAlias(true);
        barPaint.setStrokeJoin(Paint.Join.ROUND);
        barPaint.setStrokeCap(Paint.Cap.ROUND);
        barPaint.setAlpha(255);
    }

    public Point getCentroid() {
        return centroid;
    }

    public void setCentroid(float x, float y) {
        this.centroid.set((int) x, (int) y);
    }

    public void setCentroid(Point centroid) {
        this.centroid = centroid;
    }

    public float getThickness() {
        return thickness;
    }

    public void setThickness(float thickness) {
        this.thickness = thickness;
    }

    public float getMaximumWidth() {
        return maximumWidth;
    }

    public void setMaximumWidth(float maximumWidth) {
        this.maximumWidth = maximumWidth;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
        this.barPaint.setAlpha(alpha);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawRoundRect(
                new RectF(
                        centroid.x - maximumWidth / 2.0f,
                        centroid.y - thickness / 2.0f,
                        centroid.x + maximumWidth / 2.0f,
                        centroid.y + thickness / 2.0f
                ),
                thickness, thickness,
                barPaint
        );

    }


}
