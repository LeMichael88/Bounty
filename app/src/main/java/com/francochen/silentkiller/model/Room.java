package com.francochen.silentkiller.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Room {
    @SerializedName("room_id")
    public String id;
    private List<Player> players;

    public List<Player> getPlayers() {
        return players;
    }
}
