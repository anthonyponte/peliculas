package com.anthonyponte.peliculas.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.anthonyponte.peliculas.client.PeliculaRetrofitClient;
import com.anthonyponte.peliculas.dto.GeneroDTO;
import com.anthonyponte.peliculas.dto.PeliculaDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Controller
@RequestMapping("/peliculas")
public class PeliculaController {
    @GetMapping
    public DeferredResult<String> listarPeliculas(Model model) {
        DeferredResult<String> view = new DeferredResult<>();

        Call<List<PeliculaDTO>> call = PeliculaRetrofitClient.getPeliculaService().listarPeliculas();
        call.enqueue(new Callback<List<PeliculaDTO>>() {
            @Override
            public void onResponse(Call<List<PeliculaDTO>> call, Response<List<PeliculaDTO>> response) {
                if (response.isSuccessful()) {
                    List<PeliculaDTO> listPeliculas = response.body();
                    model.addAttribute("listPeliculas", listPeliculas);
                    view.setResult("peliculas");
                } else {
                    view.setErrorResult("error/500");
                }
            }

            @Override
            public void onFailure(Call<List<PeliculaDTO>> call, Throwable t) {
                view.setErrorResult("error/timeout");
            }
        });

        return view;
    }

    @RequestMapping("/nuevo")
    public String registrarPelicula(Model model) {
        model.addAttribute("pelicula", new PeliculaDTO());
        return "pelicula";
    }

    @GetMapping("/editar/{id}")
    public DeferredResult<String> obtenerPeliculaPorId(@PathVariable Long id, Model model) {
        DeferredResult<String> view = new DeferredResult<>();

        Call<PeliculaDTO> call = PeliculaRetrofitClient.getPeliculaService().obtenerPeliculaPorId(id);
        call.enqueue(new Callback<PeliculaDTO>() {
            @Override
            public void onResponse(Call<PeliculaDTO> call, Response<PeliculaDTO> response) {
                if (response.isSuccessful()) {
                    PeliculaDTO pelicula = response.body();
                    model.addAttribute("pelicula", pelicula);
                    view.setResult("pelicula");
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
    public DeferredResult<String> guardarPelicula(PeliculaDTO pelicula, BindingResult result, RedirectAttributes attr) {
        DeferredResult<String> view = new DeferredResult<>();

        if (result.hasErrors()) {
            view.setResult("pelicula");
            return view;
        }

        Callback<PeliculaDTO> callback = new Callback<PeliculaDTO>() {
            @Override
            public void onResponse(Call<PeliculaDTO> call, Response<PeliculaDTO> response) {
                if (response.isSuccessful()) {
                    PeliculaDTO p = response.body();
                    String mensaje = pelicula.getId() == null
                            ? "Se guardó la película '" + p.getTitulo() + "'"
                            : "Se actualizó la película '" + p.getId() + "'";
                    attr.addFlashAttribute("textAlertSuccess", mensaje);
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

        if (pelicula.getId() == null) {
            PeliculaRetrofitClient.getPeliculaService().crearPelicula(pelicula).enqueue(callback);
        } else {
            PeliculaRetrofitClient.getPeliculaService().actualizarPelicula(pelicula.getId(), pelicula)
                    .enqueue(callback);
        }

        return view;
    }

    @DeleteMapping("/eliminar/{id}")
    public DeferredResult<String> eliminarPelicula(@PathVariable Long id, RedirectAttributes attr) {
        DeferredResult<String> view = new DeferredResult<>();

        Call<Void> call = PeliculaRetrofitClient.getPeliculaService().eliminarPelicula(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    attr.addFlashAttribute("textAlertSuccess", "Se elimino la película " + id);
                    view.setResult("redirect:/peliculas");
                } else {
                    view.setErrorResult("error/500");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                view.setErrorResult("error/timeout");
            }
        });

        return view;
    }

    @ModelAttribute
    public void init(Model model) {
        try {
            Response<List<GeneroDTO>> response = PeliculaRetrofitClient.getGeneroService().listarGeneros().execute();
            if (response.isSuccessful()) {
                List<GeneroDTO> listGeneros = response.body();
                model.addAttribute("listGeneros", listGeneros);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
