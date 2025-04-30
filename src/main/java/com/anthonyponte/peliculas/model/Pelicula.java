package com.anthonyponte.peliculas.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Pelicula implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String titulo;
    private String director;
    private String genero;
    private Integer duracion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String fechaEstreno;

    public Pelicula() {
    }

    public Pelicula(Long id, String titulo, String director, String genero, Integer duracion, String fechaEstreno) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.genero = genero;
        this.duracion = duracion;
        this.fechaEstreno = fechaEstreno;
    }

    public Pelicula(String titulo, String director, String genero, Integer duracion, String fechaEstreno) {
        this.titulo = titulo;
        this.director = director;
        this.genero = genero;
        this.duracion = duracion;
        this.fechaEstreno = fechaEstreno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(String fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    @Override
    public String toString() {
        return "Pelicula [id=" + id + ", titulo=" + titulo + ", director=" + director + ", genero=" + genero
                + ", duracion=" + duracion + ", fechaEstreno=" + fechaEstreno + "]";
    }
}
