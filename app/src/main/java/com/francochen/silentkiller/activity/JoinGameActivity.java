package com.francochen.silentkiller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.francochen.silentkiller.R;

public class JoinGameActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_join);

        findViewById(R.id.enterButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
