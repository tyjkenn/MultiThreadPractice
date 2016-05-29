package com.pointnote.multithreadpractice;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Writes to a file. Designed to run on a background thread.
 * Intentionally slowed down to test the progress bar.
 *
 * Created by tyjkenn on 5/28/16.
 */
public class NumberListWriter extends AsyncTask<String, String, String> {

    Activity guiActivity;
    int progress = 0;

    public NumberListWriter(Activity guiActivity) {
        super();
        this.guiActivity = guiActivity;
    }

    @Override
    protected String doInBackground(String... params) {
        Context context = guiActivity.getApplicationContext();
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                    context.openFileOutput("numbers.txt", Context.MODE_PRIVATE));
            for (int i = 1; i <= 10; i++) {
                outputStreamWriter.write("" + i + "\n");
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    Log.e("Exception", "Can not sleep: " + e.toString());
                }
                progress += 10;
                publishProgress();
            }
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
        return "Finished";
    }

    @Override
    protected void onPostExecute(String result) {
        ProgressBar bar = (ProgressBar) guiActivity.findViewById(R.id.progressBar);
        bar.setProgress(0);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        ProgressBar bar = (ProgressBar) guiActivity.findViewById(R.id.progressBar);
        bar.setProgress(progress);
    }
}
