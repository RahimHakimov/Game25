package hakimov.app.game25;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Game extends AppCompatActivity {
    int currentValue;

    private void init() {
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

        for (int i = 0; i < 25; ++i) {
            TextView cur = textViews.get(i);
            cur.setText(String.valueOf(list.get(i)));
            int finalI = i;
            cur.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (currentValue == list.get(finalI)) {
                            if (currentValue != 25) {
                                currentValue++;
                                current.setText(String.valueOf(currentValue));
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
}
