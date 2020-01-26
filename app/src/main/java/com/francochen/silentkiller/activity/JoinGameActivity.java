package com.francochen.silentkiller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.francochen.silentkiller.R;
import com.francochen.silentkiller.Repository;
import com.francochen.silentkiller.model.Room;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinGameActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_join);

        findViewById(R.id.enterButton).setOnClickListener(view -> {
            String roomId = ((EditText) findViewById(R.id.roomCodeInput)).getText().toString();
            UUID uuid = UUID.randomUUID();

            Repository.setUUID(uuid);

            Repository.getService().joinRoom(uuid.toString(), roomId).enqueue(new Callback<JoinGameResponse>() {
                @Override
                public void onResponse(Call<JoinGameResponse> call, Response<JoinGameResponse> response) {
                    JoinGameResponse joinGameResponse = response.body();

                    System.out.println(response.raw().toString());

                    if (joinGameResponse.roomExists) {
                        Intent intent = new Intent(JoinGameActivity.this, LobbyActivity.class);

                        intent.putExtra("room_id", joinGameResponse.room.id);

                        startActivity(intent);
                    } else {
                        // TODO:
                    }
                }

                @Override
                public void onFailure(Call<JoinGameResponse> call, Throwable t) {

                }
            });

        });
    }

    public static class JoinGameResponse {
        @SerializedName("room_exists")
        boolean roomExists;
        Room room;
    }
}
