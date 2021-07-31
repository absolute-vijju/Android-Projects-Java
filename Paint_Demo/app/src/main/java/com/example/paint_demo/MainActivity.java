package com.example.paint_demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.constraint.solver.widgets.Rectangle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyClass(MainActivity.this));
    }

    public class MyClass extends View {

        Paint p = new Paint();

        public MyClass(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(Color.WHITE);
            p.setStrokeWidth(10);
            p.setColor(Color.BLUE);
            canvas.drawLine(10, 10, 1000, 100, p);
            p.setColor(Color.MAGENTA);
            p.setStyle(Paint.Style.FILL_AND_STROKE);
            p.setStrokeWidth(3);
            canvas.drawRect(10,50,500,800,p);
        }
    }
}
