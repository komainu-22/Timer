package jp.techacademy.akatsuki.hajime.timer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Timer mTimer;
    TextView mTimerText;

    //タイマー用の時間のための変数
    double mTimerSec = 0.0;

    Handler mHandler = new Handler();

    Button mStartButton;
    Button mPauseButton;
    Button mResetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTimerText = (TextView)findViewById(R.id.timer);
        mStartButton = (Button) findViewById(R.id.start_button);
        mPauseButton = (Button) findViewById(R.id.pause_button);
        mResetButton = (Button) findViewById(R.id.reset_button);

        mStartButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (mTimer == null) {
                    //タイマーの作成
                    mTimer = new Timer();
                    //タイマーの始動
                    mTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            mTimerSec += 0.1;

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mTimerText.setText(String.format("%.1f", mTimerSec));
                                }
                            });
                        }
                    }, 100, 100); //最初に始動させるまで　100ミリ秒、ループの間隔を　100ミリ秒　に設定
                }
            }
            });

        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimer != null){
                    mTimer.cancel();
                    mTimer = null;
                }
            }
        });

        mResetButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    mTimerSec = 0.0;
                    mTimerText.setText(String.format("%.1f",mTimerSec));

                    if (mTimer != null) {
                        mTimer.cancel();
                        mTimer = null;
                    }
                }
        });
    }
}
