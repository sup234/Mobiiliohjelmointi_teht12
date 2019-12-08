package com.example.teht12;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    GestureDetector gd;
    LinearLayout ll;
    TextView t1;
    TextView t2;
    Boolean isBlack = false;
    int maxVelocity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll = findViewById(R.id.layout1);
        t1 = findViewById(R.id.textview1);
        t2 = findViewById(R.id.textview2);

        gd = new GestureDetector(this, new MySimpleOnGestureListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gd.onTouchEvent(event);

        return true;
    }

    private class MySimpleOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            if (isBlack == false) {
                ll.setBackgroundColor(Color.BLACK);
                isBlack = true;
                t1.setTextColor(Color.WHITE);
                t2.setTextColor(Color.WHITE);
            }
            else {
                ll.setBackgroundColor(Color.WHITE);
                isBlack = false;
                t1.setTextColor(Color.BLACK);
                t2.setTextColor(Color.BLACK);
            }

            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {

            maxVelocity = ViewConfiguration.get(getApplicationContext()).getScaledMaximumFlingVelocity();
            float veloX = Math.abs(velocityX / (maxVelocity/255));
            float veloY = Math.abs(velocityY / (maxVelocity/255));

            if (velocityX > velocityY) {
                ll.setBackgroundColor(Color.rgb((int)veloX, 0, 0));
            }
            else {
                ll.setBackgroundColor(Color.rgb(0,0, (int)veloY));
            }
            t1.setText("X: " + Float.toString(veloX));
            t2.setText("Y: " + Float.toString(veloY));
            t1.setTextColor(Color.WHITE);
            t2.setTextColor(Color.WHITE);

            return true;

        }
    }
}
