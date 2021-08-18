package com.ceiba.suscripcion.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.suscripcion.modelo.entidad.Suscripcion;
import com.ceiba.suscripcion.puerto.repositorio.RepositorioSuscripcion;

import java.util.HashMap;

public class ServicioActualizarSuscripcion {

    private static String EL_USUARIO_YA_TIENE_SUSCRIPCION_ACTIVA = "El usuario xxx ya tiene una suscripción activa, aun tiene (yyy) días disponibles.";

    private final RepositorioSuscripcion repositorioSuscripcion;

    public ServicioActualizarSuscripcion(RepositorioSuscripcion repositorioSuscripcion){
        this.repositorioSuscripcion = repositorioSuscripcion;

    }

    public void ejecutar(Suscripcion suscripcion){
        this.repositorioSuscripcion.actualizar(suscripcion);
    }
}
