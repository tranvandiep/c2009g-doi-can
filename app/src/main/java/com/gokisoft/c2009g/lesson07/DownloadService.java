package com.gokisoft.c2009g.lesson07;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadService extends Service {
    public DownloadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(DownloadService.class.getName(), "onBind ...");
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(DownloadService.class.getName(), "onCreate ...");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(DownloadService.class.getName(), "onStartCommand ...");
        //Example 01
//        if(myHandler == null) {
//            myHandler = new Handler();
//            interval();
//        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                downloadFile();
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }

    boolean isLive = true;
    void downloadFile() {
        int count;
        int percent = 0;
        try {
            URL url = new URL(DownloadActivity.URL_YOUTUBE);
            URLConnection connection = url.openConnection();
            connection.connect();

            // this will be useful so that you can show a tipical 0-100%
            // progress bar
            int lenghtOfFile = connection.getContentLength();

            // download the file
            InputStream input = new BufferedInputStream(url.openStream(),
                    8192);

            // Output stream
//            OutputStream output = new FileOutputStream(Environment
//                    .getExternalStorageDirectory().toString()
//                    + "/youtube.mp4");

            byte data[] = new byte[1024];

            long total = 0;

            while ((count = input.read(data)) != -1) {
                if(!isLive) break;
                total += count;
                int newPercent = (int) ((total * 100) / lenghtOfFile);
                Log.d(DownloadService.class.getName(), "" + newPercent);
                // publishing the progress....
                // After this onProgressUpdate will be called
//                publishProgress("" + (int) ((total * 100) / lenghtOfFile));
                if(percent != newPercent) {
                    percent = newPercent;
                    Intent intent = new Intent();
                    intent.setAction(DownloadActivity.ACTION_COUNTER);
                    intent.putExtra("count", percent);
                    sendBroadcast(intent);
                }

                // writing data to file
//                output.write(data, 0, count);
            }

            // flushing output
//            output.flush();

            // closing streams
//            output.close();
            input.close();

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }
    }

    //Example 01
//    Handler myHandler = null;
//    int count = 0;

    //Example 01
//    void interval() {
//        if(myHandler == null) return;
//        myHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent();
//                intent.setAction(DownloadActivity.ACTION_COUNTER);
//                intent.putExtra("count", ++count);
//                sendBroadcast(intent);
//
//                interval();
//            }
//        }, 1000);
//    }

    @Override
    public void onDestroy() {
        Log.d(DownloadService.class.getName(), "onDestroy ...");
        isLive = false;
        //Example 01
//        myHandler = null;
        super.onDestroy();
    }
}