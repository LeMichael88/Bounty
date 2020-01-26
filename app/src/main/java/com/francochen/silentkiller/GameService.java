package com.francochen.silentkiller;

import retrofit2.Call;
import retrofit2.http.Field;

public interface GameService {
    Call<String> createRoom(@Field("player_id") String playerId, @Field("room_id") String roomId);
    Call<String> joinRoom();
}
