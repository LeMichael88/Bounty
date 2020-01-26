package com.francochen.silentkiller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.francochen.silentkiller.R;
import com.francochen.silentkiller.model.Player;

import java.util.List;

public class LobbyActivity extends Activity {
    private List<Player> players;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void updateLobby() {
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
