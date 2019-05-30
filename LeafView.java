package com.example.test.custom;

import android.content.Context;
import android.graphics.*;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.example.test.R;

public class LeafView extends View {
    private Paint paint;
    private Path path;
    private RectF topLeftOval;
    private RectF topRightOval;
    private RectF bottomLeftOval;
    private RectF bottomRightOval;

    private int topLeftCornerRadius = 100;
    private int topRightCornerRadius = 0;
    private int bottomLeftCornerRadius = 0;
    private int bottomRightCornerRadius = 100;

    public LeafView(Context context) {
        super(context);
        initialize();
    }

    public LeafView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public LeafView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LeafView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        topLeftOval = new RectF(0, 0, topLeftCornerRadius * 2, topLeftCornerRadius * 2);
        bottomLeftOval = new RectF(0, h - (bottomRightCornerRadius * 2 ), bottomLeftCornerRadius * 2, h);
        bottomRightOval = new RectF(w - (bottomRightCornerRadius * 2), h - (bottomRightCornerRadius * 2), w, h);
        topRightOval = new RectF(w - (bottomRightCornerRadius * 2), 0, w, topRightCornerRadius * 2);
    }

    void initialize () {
        path = new Path();
        paint = new Paint();

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(R.color.colorPrimaryDark));
        paint.setAntiAlias(true);
        topLeftOval = new RectF(0, 0, topLeftCornerRadius * 2, topLeftCornerRadius * 2);
        bottomLeftOval = new RectF(0, getHeight() - (bottomRightCornerRadius * 2 ), bottomLeftCornerRadius * 2, getHeight());
        bottomRightOval = new RectF(getWidth() - (bottomRightCornerRadius * 2), getHeight() - (bottomRightCornerRadius * 2), getWidth(), getHeight());
        topRightOval = new RectF(getWidth() - (bottomRightCornerRadius * 2), 0, getWidth(), topRightCornerRadius * 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.moveTo(0f, topLeftCornerRadius);
        path.lineTo(0f, getHeight() - bottomLeftCornerRadius);
        if(bottomLeftCornerRadius != 0)
            path.arcTo(bottomLeftOval, -180, -90, false);
        path.lineTo(getWidth() - bottomRightCornerRadius, getHeight());
        if(bottomRightCornerRadius != 0)
            path.arcTo(bottomRightOval, 90, -90, false);
        path.lineTo(getWidth(), topRightCornerRadius);
        if(topRightCornerRadius != 0)
            path.arcTo(topRightOval, 0, -90, false);
        path.lineTo(topLeftCornerRadius, 0);
        if(topLeftCornerRadius != 0)
            path.arcTo(topLeftOval, -90, -90, false);
        path.close();
        canvas.drawPath(path, paint);
    }
}
