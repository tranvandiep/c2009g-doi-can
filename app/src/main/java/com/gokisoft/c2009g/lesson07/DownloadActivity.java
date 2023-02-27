package com.gokisoft.c2009g.lesson07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.gokisoft.c2009g.R;

public class DownloadActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String ACTION_COUNTER = "ACTION_COUNTER";
    public static final String URL_YOUTUBE = "https://rr1---sn-5hne6nz6.googlevideo.com/videoplayback?expire=1677486204&ei=HBT8Y6rTMqKU_9EPifqxeA&ip=216.131.82.53&id=o-ALLt-zJYN8LMITR6uC30QoGTpoPaPQ_WvjkfPzjYv96v&itag=137&aitags=133%2C134%2C135%2C136%2C137%2C160%2C242%2C243%2C244%2C247%2C248%2C278&source=youtube&requiressl=yes&spc=H3gIhhrl0xTCihkDxwqz3ixMKoPMyaaKzAb0U-JwiFC1u9ROMw&vprv=1&mime=video%2Fmp4&ns=8oaL_B17_tXK4BaYA2ZyDSAL&gir=yes&clen=271094948&dur=5149.233&lmt=1642563164195949&keepalive=yes&fexp=24007246&c=WEB&txp=5316224&n=TljyeBM30Vk89Q&sparams=expire%2Cei%2Cip%2Cid%2Caitags%2Csource%2Crequiressl%2Cspc%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cdur%2Clmt&sig=AOq0QJ8wRgIhAJC7NunMR_DyJb0VmNtskNtwNvZ5JLoFSw45z3q5LHykAiEA0EFGZrg_quytsWQU7dwN727bKYVG-u83oiCWuooUr28%3D&redirect_counter=1&cm2rm=sn-ab5eld7z&req_id=852d9c1f5808a3ee&cms_redirect=yes&cmsv=e&mh=jL&mip=210.245.54.82&mm=34&mn=sn-5hne6nz6&ms=ltu&mt=1677463746&mv=D&mvi=1&pl=0&lsparams=mh,mip,mm,mn,ms,mv,mvi,pl&lsig=AG3C_xAwRQIgO2S-kkOcwdc8X__qeP8IhpsQLh5su26QRuXcTqKFqwECIQDUQ5AJ7ma4LvzQvWeKH8ytoA96Z4XJS4e6g10cTAdqEw%3D%3D";

    Button startBtn, stopBtn;
    SeekBar seekBar;
    TextView counterLabel;
    Intent intentService = null;
    DownloadThread myThread = null;
    DownloadAsyncTask asyncTask = null;

    BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            switch (action) {
                case ACTION_COUNTER:
                    int count = intent.getIntExtra("count", 0);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            counterLabel.setText(count+"%");
                            seekBar.setProgress(count);
                        }
                    });
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        startBtn = findViewById(R.id.ad_start_btn);
        stopBtn = findViewById(R.id.ad_stop_btn);
        seekBar = findViewById(R.id.ad_seekbar);
        counterLabel = findViewById(R.id.ad_counter);

        startBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);

        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_COUNTER);
        try {
            registerReceiver(myReceiver, filter);
        } catch(Exception e) {}
    }

    @Override
    public void finish() {
        try {
            unregisterReceiver(myReceiver);
        } catch (Exception e) {}
        super.finish();
    }

    @Override
    public void onClick(View view) {
        if(view.equals(startBtn)) {
//            if(intentService == null) {
//                intentService = new Intent(this, DownloadService.class);
//                startService(intentService);
//            }
//            if(myThread == null) {
//                myThread = new DownloadThread(this);
//                myThread.start();
//            }
            if(asyncTask == null) {
                asyncTask = new DownloadAsyncTask(this);
                asyncTask.execute();
            }
        } else if(view.equals(stopBtn)) {
//            if(intentService != null) {
//                stopService(intentService);
//                intentService = null;
//            }
//            if(myThread != null) {
//                myThread.isLive = false;
//                myThread = null;
//            }
            if(asyncTask != null) {
                asyncTask.isLive = false;
                asyncTask = null;
            }
        }
    }
}