package hakimov.app.game25;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Toast backToast;
    private long backPressedTime;
    SharedPreferences save;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        save = getSharedPreferences("Save", MODE_PRIVATE);

        final boolean isAnyRecord = save.getBoolean("isAnyRecord", false);
        final int Minutes_ = save.getInt("Minutes", Integer.MAX_VALUE);
        final int Seconds_ = save.getInt("Seconds", Integer.MAX_VALUE);
        final int MilliSeconds_ = save.getInt("MilliSeconds", Integer.MAX_VALUE);

        TextView score = findViewById(R.id.high_score);
        if (!isAnyRecord) {
            score.setText(getResources().getString(R.string.record) + " --:--:--");
        } else {
            score.setText(String.format(getResources().getString(R.string.record) + " %02d:%02d:%02d", Minutes_, Seconds_, MilliSeconds_));
        }

        TextView buttonStart = (TextView) findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this, Game.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), getResources().getString(R.string.close), Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

}