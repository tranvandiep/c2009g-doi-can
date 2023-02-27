package com.gokisoft.c2009g.lesson07;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadAsyncTask extends AsyncTask<String, Integer, String> {
    boolean isLive = true;
    Activity mActivity = null;

    public DownloadAsyncTask(Activity activity) {
        mActivity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
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
                    publishProgress(percent);
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
        Log.d(DownloadService.class.getName(), "Stop Thread");

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(Integer... percent) {
        super.onProgressUpdate(percent);

        Intent intent = new Intent();
        intent.setAction(DownloadActivity.ACTION_COUNTER);
        intent.putExtra("count", percent);
        mActivity.sendBroadcast(intent);
    }
}
