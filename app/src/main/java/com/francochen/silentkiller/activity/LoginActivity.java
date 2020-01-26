package com.francochen.silentkiller.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.francochen.silentkiller.R;

import androidx.core.content.ContextCompat;

public class LoginActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addClickListeners();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE}, 1);
        }
    }

    public void addClickListeners() {
        findViewById(R.id.joinButton).setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, JoinGameActivity.class)));

        findViewById(R.id.createButton).setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, OwnerJoinGameActivity.class)));
    }
}
