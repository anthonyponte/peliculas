package com.anthonyponte.peliculas.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.anthonyponte.peliculas.client.PeliculaRetrofitClient;
import com.anthonyponte.peliculas.model.Pelicula;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Controller
@RequestMapping("/peliculas")
public class PeliculaController {

    @GetMapping
    public DeferredResult<String> consultar(Model model) {
        DeferredResult<String> view = new DeferredResult<>();

        Call<List<Pelicula>> call = PeliculaRetrofitClient.getPeliculaService().listarTodos();
        call.enqueue(new Callback<List<Pelicula>>() {
            @Override
            public void onResponse(Call<List<Pelicula>> call, Response<List<Pelicula>> response) {
                if (response.isSuccessful()) {
                    List<Pelicula> listPelicula = response.body();
                    model.addAttribute("listPelicula", listPelicula);
                    view.setResult("peliculas");
                } else {
                    view.setErrorResult("error/500");
                }
            }

            @Override
            public void onFailure(Call<List<Pelicula>> call, Throwable t) {
                view.setErrorResult("error/timeout");
            }
        });

        return view;
    }

    @RequestMapping("/nuevo")
    public String registrar(Model model) {
        model.addAttribute("pelicula", new Pelicula());
        return "pelicula";
    }

    @GetMapping("/editar/{id}")
    public DeferredResult<String> editar(@PathVariable("id") Long id, Model model) {
        DeferredResult<String> view = new DeferredResult<>();

        Call<Pelicula> call = PeliculaRetrofitClient.getPeliculaService().obtenerPorId(id);
        call.enqueue(new Callback<Pelicula>() {
            @Override
            public void onResponse(Call<Pelicula> call, Response<Pelicula> response) {
                if (response.isSuccessful()) {
                    Pelicula pelicula = response.body();
                    model.addAttribute("pelicula", pelicula);
                    view.setResult("pelicula");
                } else {
                    view.setErrorResult("error/500");
                }
            }

            @Override
            public void onFailure(Call<Pelicula> call, Throwable t) {
                view.setErrorResult("error/timeout");
            }
        });

        return view;
    }

    @PostMapping("/guardar")
    public DeferredResult<String> guardar(Pelicula pelicula, BindingResult result, RedirectAttributes attr) {
        System.out.println(pelicula);
        DeferredResult<String> view = new DeferredResult<>();

        if (result.hasErrors()) {
            view.setResult("pelicula");
            return view;
        }

        Callback<Pelicula> callback = new Callback<Pelicula>() {
            @Override
            public void onResponse(Call<Pelicula> call, Response<Pelicula> response) {
                if (response.isSuccessful()) {
                    Pelicula p = response.body();
                    String mensaje = pelicula.getId() == null
                            ? "Se guardó el pelicula '" + p.getTitulo() + "'"
                            : "Se actualizó el pelicula '" + p.getId() + "'";
                    attr.addFlashAttribute("textAlertSuccess", mensaje);
                    view.setResult("redirect:/peliculas");
                } else {
                    view.setErrorResult("error/500");
                }
            }

            @Override
            public void onFailure(Call<Pelicula> call, Throwable t) {
                view.setErrorResult("error/timeout");
            }
        };

        if (pelicula.getId() == null) {
            PeliculaRetrofitClient.getPeliculaService().crear(pelicula).enqueue(callback);
        } else {
            PeliculaRetrofitClient.getPeliculaService().actualizar(pelicula.getId(), pelicula).enqueue(callback);
        }

        return view;
    }

    @GetMapping("/eliminar/{id}")
    public DeferredResult<String> eliminar(@PathVariable("id") Long id, RedirectAttributes attr) {
        DeferredResult<String> view = new DeferredResult<>();

        Call<Void> call = PeliculaRetrofitClient.getPeliculaService().eliminar(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    attr.addFlashAttribute("textAlertSuccess", "Se elimino el pelicula " + id);
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
}
