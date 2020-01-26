package com.francochen.silentkiller;

import java.util.UUID;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    private static final Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://10.0.2.2/")
            .build();

    private static GameService service = retrofit.create(GameService.class);

    private static String uuid;
    private static String name;

    public static GameService getService() {
        return service;
    }

    public static void setName(String name) {
        Repository.name = name;
    }

    public static void setUUID(UUID uuid) {
        Repository.uuid = uuid.toString();
    }

    public static String getUUID() {
        return uuid;
    }
}
