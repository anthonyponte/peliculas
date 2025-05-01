package com.anthonyponte.peliculas.dto;

import java.io.Serializable;

public class GeneroDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
