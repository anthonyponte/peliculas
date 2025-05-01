package com.anthonyponte.peliculas.service;

import java.util.List;

import com.anthonyponte.peliculas.dto.GeneroDTO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GeneroService {
    @GET("/api/v1/peliculas/generos")
    Call<List<GeneroDTO>> listarGeneros();
}
