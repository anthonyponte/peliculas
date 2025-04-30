package com.anthonyponte.peliculas.client;

import com.anthonyponte.peliculas.service.PeliculaService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PeliculaRetrofitClient {
    private static final String BASE_URL = "http://localhost:9091";
    private static Retrofit retrofit = null;

    public static PeliculaService getPeliculaService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(PeliculaService.class);
    }
}
