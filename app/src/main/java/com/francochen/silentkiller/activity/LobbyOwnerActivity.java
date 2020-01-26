package com.francochen.silentkiller.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.francochen.silentkiller.R;

import androidx.appcompat.app.AppCompatActivity;

public class LobbyOwnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby_owner);

        Bundle extras = getIntent().getExtras();

        ((TextView) findViewById(R.id.textView2)).setText(extras.getString("room_id"));
    }
}