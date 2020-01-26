package com.francochen.silentkiller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.francochen.silentkiller.R;
import com.francochen.silentkiller.Repository;
import com.francochen.silentkiller.model.Player;
import com.francochen.silentkiller.model.Room;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LobbyActivity extends Activity {
    private List<Player> players;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        String roomId = savedInstanceState.getString("room_id");

        ((TextView) findViewById(R.id.textView2)).setText(roomId);

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Repository.getService().getRoom(roomId).enqueue(new Callback<Room>() {
                    @Override
                    public void onResponse(Call<Room> call, Response<Room> response) {
                        Room room = response.body();

                        updatePlayers(room.getPlayers());
                    }

                    @Override
                    public void onFailure(Call<Room> call, Throwable t) {

                    }
                });
            }

        }, 0L, 1000L);
    }

    private void updateLobby() {
        ((GridView) findViewById(R.id.player_list)).removeAllViewsInLayout();

        for (Player player : players) {
            View playerCircle = getLayoutInflater().inflate(R.layout.lobby_player, null);

            ((TextView) playerCircle.findViewById(R.id.player_name)).setText(player.getName());
            ((GridView) findViewById(R.id.player_list)).addView(playerCircle, null);
        }
    }

    public void updatePlayers(List<Player> players) {
        if (this.players.equals(players)) {
            this.players = players;
            updateLobby();
        }
    }
}
