package com.pointnote.multithreadpractice;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void create(View view) {
        Context context = getApplicationContext();
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
            }
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public void load(View view) {
        NumberListReader reader = new NumberListReader(this);
        reader.execute();


    }

    public void clear(View view) {
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(null);
    }
}
