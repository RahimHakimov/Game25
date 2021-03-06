package hakimov.app.game25;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import java.util.ArrayList;
import java.util.List;

public class Game extends AppCompatActivity {
    int currentValue;
    private boolean running;
    TextView stopwatch;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L;
    Handler handler;
    int Seconds, Minutes, MilliSeconds;

    @SuppressLint("SetTextI18n")
    private void init() {
        MillisecondTime = 0L;
        StartTime = 0L;
        TimeBuff = 0L;
        UpdateTime = 0L;
        Seconds = 0;
        Minutes = 0;
        MilliSeconds = 0;
        handler.removeCallbacks(runnable);
        stopwatch.setText(getResources().getString(R.string.score) + " 00:00:00");
        currentValue = 1;
        TextView current = findViewById(R.id.current);
        current.setText(String.valueOf(currentValue));
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 25; ++i) {
            list.add(i + 1);
        }
        java.util.Collections.shuffle(list);

        ArrayList<TextView> textViews = new ArrayList<TextView>();
        textViews.add(findViewById(R.id.textView1));
        textViews.add(findViewById(R.id.textView2));
        textViews.add(findViewById(R.id.textView3));
        textViews.add(findViewById(R.id.textView4));
        textViews.add(findViewById(R.id.textView5));
        textViews.add(findViewById(R.id.textView6));
        textViews.add(findViewById(R.id.textView7));
        textViews.add(findViewById(R.id.textView8));
        textViews.add(findViewById(R.id.textView9));
        textViews.add(findViewById(R.id.textView10));
        textViews.add(findViewById(R.id.textView11));
        textViews.add(findViewById(R.id.textView12));
        textViews.add(findViewById(R.id.textView13));
        textViews.add(findViewById(R.id.textView14));
        textViews.add(findViewById(R.id.textView15));
        textViews.add(findViewById(R.id.textView16));
        textViews.add(findViewById(R.id.textView17));
        textViews.add(findViewById(R.id.textView18));
        textViews.add(findViewById(R.id.textView19));
        textViews.add(findViewById(R.id.textView20));
        textViews.add(findViewById(R.id.textView21));
        textViews.add(findViewById(R.id.textView22));
        textViews.add(findViewById(R.id.textView23));
        textViews.add(findViewById(R.id.textView24));
        textViews.add(findViewById(R.id.textView25));

        running = false;

        for (int i = 0; i < 25; ++i) {
            TextView cur = textViews.get(i);
            cur.setText(String.valueOf(list.get(i)));
            int finalI = i;
            cur.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (!running && currentValue != 25) {
                            running = true;
                            StartTime = SystemClock.uptimeMillis();
                            handler.postDelayed(runnable, 0);
                        }

                        if (currentValue == list.get(finalI)) {
                            if (currentValue != 25) {
                                currentValue++;
                                current.setText(String.valueOf(currentValue));
                            } else {
                                running = false;
                                //SUCCESS
                                handler.removeCallbacks(runnable);
                            }
                        }
                    } catch (Exception e) {

                    }
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        handler = new Handler();
        stopwatch = findViewById(R.id.score);

        TextView button_back = (TextView) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Game.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });

        TextView buttonRestart = (TextView) findViewById(R.id.button_restart);
        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    init();
                } catch (Exception e) {

                }
            }
        });

        init();
    }

    public Runnable runnable = new Runnable() {

        @SuppressLint({"SetTextI18n", "DefaultLocale"})
        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 100);

            stopwatch.setText(getResources().getString(R.string.score) + String.format(" %02d:%02d:%02d", Minutes, Seconds, MilliSeconds));

            handler.postDelayed(this, 0);
        }

    };

    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(Game.this, MainActivity.class);
            startActivity(intent);
            finish();

        } catch (Exception e) {

        }
    }
}
