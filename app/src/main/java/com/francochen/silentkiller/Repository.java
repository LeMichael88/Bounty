package com.francochen.silentkiller;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    private final Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("localhost:5000")
            .build();

    private GameService service = retrofit.create(GameService.class);

    public Repository() {

    }

    public GameService getService() {
        return service;
    }


}
