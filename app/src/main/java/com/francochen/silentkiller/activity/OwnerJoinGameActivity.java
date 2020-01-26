package com.francochen.silentkiller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.francochen.silentkiller.CreateRoomResponse;
import com.francochen.silentkiller.R;
import com.francochen.silentkiller.Repository;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OwnerJoinGameActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_owner_join);

        findViewById(R.id.enterButton).setOnClickListener(view -> {
            UUID uuid = UUID.randomUUID();
            String playerName = ((EditText) findViewById(R.id.nameInput)).getText().toString();

            Repository.setUUID(uuid);
            Repository.setName(playerName);

            Repository.getService().createRoom(uuid.toString(), playerName).enqueue(new Callback<CreateRoomResponse>() {
                @Override
                public void onResponse(Call<CreateRoomResponse> call, Response<CreateRoomResponse> response) {
                    CreateRoomResponse createRoomResponse = response.body();
                    Intent intent = new Intent(OwnerJoinGameActivity.this, LobbyOwnerActivity.class);

                    intent.putExtra("room_id", createRoomResponse.roomId);

                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(Call<CreateRoomResponse> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        });
    }
}
