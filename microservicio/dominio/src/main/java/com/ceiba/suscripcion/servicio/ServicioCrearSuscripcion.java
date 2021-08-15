package com.ceiba.suscripcion.servicio;

import com.ceiba.suscripcion.modelo.entidad.Suscripcion;
import com.ceiba.suscripcion.puerto.repositorio.RepositorioSuscripcion;

public class ServicioCrearSuscripcion {

    private final RepositorioSuscripcion repositorioSuscripcion;

    public ServicioCrearSuscripcion(RepositorioSuscripcion repositorioSuscripcion){
        this.repositorioSuscripcion = repositorioSuscripcion;

    }

    public Long ejecutar(Suscripcion suscripcion){
        return this.repositorioSuscripcion.crear(suscripcion);
    }
}
