package com.pointnote.multithreadpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void create(View view) {
        NumberListWriter writer = new NumberListWriter(this);
        writer.execute();
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
