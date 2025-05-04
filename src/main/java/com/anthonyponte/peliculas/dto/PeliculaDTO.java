package com.anthonyponte.peliculas.dto;

import java.io.Serializable;

public class PeliculaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String titulo;
    private String director;
    private Long generoId;
    private String generoDescripcion;
    private Integer duracion;
    private String fechaEstreno;
    private boolean favorito;

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

    public Long getGeneroId() {
        return generoId;
    }

    public void setGeneroId(Long generoId) {
        this.generoId = generoId;
    }

    public String getGeneroDescripcion() {
        return generoDescripcion;
    }

    public void setGeneroDescripcion(String generoDescripcion) {
        this.generoDescripcion = generoDescripcion;
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

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    @Override
    public String toString() {
        return "PeliculaDTO [id=" + id + ", titulo=" + titulo + ", director=" + director + ", generoId=" + generoId
                + ", generoDescripcion=" + generoDescripcion + ", duracion=" + duracion + ", fechaEstreno="
                + fechaEstreno + ", favorito=" + favorito + "]";
    }
}
