package com.francochen.silentkiller.activity;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.francochen.silentkiller.R;

import java.util.ArrayList;

public class WinLoseActivity extends AppCompatActivity {

    //vars
    private ArrayList<String> mName = new ArrayList<>();
    private ArrayList<String> mTime = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_lose);

        initInfo();
    }

    private void initInfo(){

        mName.add("Kevin");
        mTime.add("1 min ago");

        mName.add("Kevin");
        mTime.add("1 min ago");

        mName.add("Kevin");
        mTime.add("1 min ago");

        mName.add("Kevin");
        mTime.add("1 min ago");

        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mName, mTime);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}


