package com.pointnote.multithreadpractice;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyjkenn on 5/28/16.
 */
public class NumberListReader extends AsyncTask<String, String, String> {

    List<String> numberList;
    InputStream inputStream;
    Activity guiActivity;

    public NumberListReader(Activity guiActivity) {
        super();
        this.guiActivity = guiActivity;
        numberList = new ArrayList<String>();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            FileInputStream inputStream = guiActivity.openFileInput("numbers.txt");
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                while ( (line = bufferedReader.readLine()) != null ) {
                    numberList.add(line);
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        Log.e("Exception", "Can not sleep: " + e.toString());
                    }
                }
                inputStream.close();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("Exception", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("Exception", "Can not read file: " + e.toString());
        }
        return "Completed";
    }

    @Override
    protected void onPostExecute(String result) {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(guiActivity, android.R.layout.simple_list_item_1, numberList);
        ListView listView = (ListView) guiActivity.findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onProgressUpdate(String... values) {

    }

}
