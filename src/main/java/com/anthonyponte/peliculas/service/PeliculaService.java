package com.anthonyponte.peliculas.service;

import java.util.List;

import com.anthonyponte.peliculas.dto.PeliculaDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PeliculaService {
    @POST("/api/v1/peliculas")
    Call<PeliculaDTO> crear(@Body PeliculaDTO dto);

    @GET("/api/v1/peliculas")
    Call<List<PeliculaDTO>> listarPeliculas();

    @GET("/api/v1/peliculas/{id}")
    Call<PeliculaDTO> obtenerPorId(@Path("id") Long id);

    @PUT("/api/v1/peliculas/{id}")
    Call<PeliculaDTO> actualizar(@Path("id") Long id, @Body PeliculaDTO dto);

    @DELETE("/api/v1/peliculas/{id}")
    Call<Void> eliminar(@Path("id") Long id);
}
