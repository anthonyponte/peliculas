package com.anthonyponte.peliculas.service;

import java.util.List;

import com.anthonyponte.peliculas.dto.FavoritoDTO;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FavoritoService {
    @GET("/api/v1/favoritos")
    Call<List<FavoritoDTO>> listarFavoritosPorUsuarioId(@Query("usuarioId") String usuarioId);

    @POST("/api/v1/favoritos/{usuarioId}/{peliculaId}")
    Call<Void> guardarFavorito(@Path("usuarioId") String usuarioId, @Path("peliculaId") Long peliculaId);

    @DELETE("/api/v1/favoritos/{usuarioId}/{peliculaId}")
    Call<Void> eliminarFavorito(@Path("usuarioId") String usuarioId, @Path("peliculaId") Long peliculaId);
}
