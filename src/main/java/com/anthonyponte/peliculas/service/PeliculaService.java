package com.anthonyponte.peliculas.service;

import java.util.List;

import com.anthonyponte.peliculas.model.Pelicula;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PeliculaService {
    @POST("/api/v1/peliculas")
    Call<Pelicula> crear(@Body Pelicula pelicula);

    @GET("/api/v1/peliculas")
    Call<List<Pelicula>> listarTodos();

    @GET("/api/v1/peliculas/{id}")
    Call<Pelicula> obtenerPorId(@Path("id") Long id);

    @GET("/api/v1/peliculas")
    Call<Pelicula> obtenerPorTituloYGenero(
            @Query("titulo") String titulo,
            @Query("genero") String genero);

    @PUT("/api/v1/peliculas/{id}")
    Call<Pelicula> actualizar(@Path("id") Long id, @Body Pelicula pelicula);

    @DELETE("/api/v1/peliculas/{id}")
    Call<Void> eliminar(@Path("id") Long id);
}
