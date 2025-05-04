package com.anthonyponte.peliculas.service;

import java.util.List;

import com.anthonyponte.peliculas.dto.FavoritoDTO;
import com.anthonyponte.peliculas.dto.PeliculaDTO;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FavoritoService {
    @GET("/api/v1/peliculas/favoritos/{usuarioId}")
    Call<List<FavoritoDTO>> listarFavoritosPorUsuarioId(@Path("usuarioId") String usuarioId);

    @POST("/api/v1/peliculas/favoritos/{usuarioId}/{peliculaId}")
    Call<PeliculaDTO> guardarFavorito(@Path("usuarioId") String usuarioId, @Path("peliculaId") Long peliculaId);

    @DELETE("/api/v1/peliculas/favoritos/{usuarioId}/{peliculaId}")
    Call<PeliculaDTO> eliminarFavorito(@Path("usuarioId") String usuarioId, @Path("peliculaId") Long peliculaId);
}
