package com.anthonyponte.peliculas.client;

import com.anthonyponte.peliculas.service.FavoritoService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavoritoRetrofitClient {
    private static final String BASE_URL = "http://anthonyponte.com:9094";
    private static Retrofit retrofit = null;

    public static FavoritoService getFavoritoService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(FavoritoService.class);
    }
}
