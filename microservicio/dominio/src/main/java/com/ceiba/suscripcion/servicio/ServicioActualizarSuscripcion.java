package com.ceiba.suscripcion.servicio;
import com.ceiba.suscripcion.modelo.entidad.Suscripcion;
import com.ceiba.suscripcion.puerto.repositorio.RepositorioSuscripcion;

public class ServicioActualizarSuscripcion {
    private final RepositorioSuscripcion repositorioSuscripcion;

    public ServicioActualizarSuscripcion(RepositorioSuscripcion repositorioSuscripcion){
        this.repositorioSuscripcion = repositorioSuscripcion;
    }

    public void ejecutar(Suscripcion suscripcion){
        this.repositorioSuscripcion.actualizar(suscripcion);
    }
}
