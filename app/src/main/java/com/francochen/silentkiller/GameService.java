package com.francochen.silentkiller;

import com.francochen.silentkiller.activity.JoinGameActivity;
import com.francochen.silentkiller.model.Room;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GameService {
    @POST("create_room")
    @FormUrlEncoded
    Call<CreateRoomResponse> createRoom(@Field("player_id") String playerId, @Field("player_name") String playerName);

    @POST("join_room")
    @FormUrlEncoded
    Call<JoinGameActivity.JoinGameResponse> joinRoom(@Field("player_id") String playerId, @Field("room_id") String roomId);

    @POST("get_room")
    @FormUrlEncoded
    Call<Room> getRoom(@Field("room_id") String roomId);
}
