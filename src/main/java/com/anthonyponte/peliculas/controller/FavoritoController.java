package com.anthonyponte.peliculas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.anthonyponte.peliculas.client.FavoritoRetrofitClient;
import com.anthonyponte.peliculas.client.PeliculaRetrofitClient;
import com.anthonyponte.peliculas.dto.PeliculaDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Controller
@RequestMapping("/peliculas/favoritos")
public class FavoritoController {
    @GetMapping("/{id}")
    public DeferredResult<String> obtenerPeliculaPorId(@PathVariable Long id, Model model) {
        DeferredResult<String> view = new DeferredResult<>();

        Call<PeliculaDTO> call = PeliculaRetrofitClient.getPeliculaService().obtenerPeliculaPorId(id);
        call.enqueue(new Callback<PeliculaDTO>() {
            @Override
            public void onResponse(Call<PeliculaDTO> call, Response<PeliculaDTO> response) {
                if (response.isSuccessful()) {
                    PeliculaDTO pelicula = response.body();
                    model.addAttribute("pelicula", pelicula);
                    view.setResult("favorito");
                } else {
                    view.setErrorResult("error/500");
                }
            }

            @Override
            public void onFailure(Call<PeliculaDTO> call, Throwable t) {
                view.setErrorResult("error/timeout");
            }
        });

        return view;
    }

    @PostMapping("/guardar")
    public DeferredResult<String> guardarPeliculaFavorito(PeliculaDTO pelicula, BindingResult result,
            RedirectAttributes attr) {
        DeferredResult<String> view = new DeferredResult<>();

        if (result.hasErrors()) {
            view.setResult("pelicula");
            return view;
        }

        Callback<PeliculaDTO> callback = new Callback<PeliculaDTO>() {
            @Override
            public void onResponse(Call<PeliculaDTO> call, Response<PeliculaDTO> response) {
                if (response.isSuccessful()) {
                    view.setResult("redirect:/peliculas");
                } else {
                    view.setErrorResult("error/500");
                }
            }

            @Override
            public void onFailure(Call<PeliculaDTO> call, Throwable t) {
                view.setErrorResult("error/timeout");
            }
        };

        if (pelicula.isFavorito()) {
            FavoritoRetrofitClient.getFavoritoService().guardarFavorito("aponte",
                    pelicula.getId()).enqueue(callback);
        } else {
            FavoritoRetrofitClient.getFavoritoService().eliminarFavorito("aponte",
                    pelicula.getId()).enqueue(callback);
        }

        return view;
    }
}
