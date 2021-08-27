package com.ceiba.suscripcion.servicio;

import com.ceiba.suscripcion.puerto.repositorio.RepositorioSuscripcion;


public class ServicioEliminarSuscripcion {

    private final RepositorioSuscripcion repositorioSuscripcion;

    public ServicioEliminarSuscripcion(RepositorioSuscripcion repositorioSuscripcion) {
        this.repositorioSuscripcion = repositorioSuscripcion;
    }

    public void ejecutar(Long id) {
        this.repositorioSuscripcion.eliminar(id);
    }
}
