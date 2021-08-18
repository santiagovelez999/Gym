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
        validarExistenciaPrevia(suscripcion);
        this.repositorioSuscripcion.actualizar(suscripcion);
    }

    private void validarExistenciaPrevia(Suscripcion suscripcion) {
        Integer numeroDias =  this.repositorioSuscripcion.existe(suscripcion.getIdCliente());
        if(numeroDias != null && numeroDias > 0){
            throw new ExcepcionDuplicidad(duplicidadSuscripcionActiva(suscripcion.getIdCliente(),
                    numeroDias));
        }
    }

    private static final String duplicidadSuscripcionActiva(Long idCliente, Integer diasFaltantes){
        String remplazarUsuario = EL_USUARIO_YA_TIENE_SUSCRIPCION_ACTIVA.
                replace("xxx",idCliente.toString());
        String remplazarDiasFaltantesSuscripcion = remplazarUsuario.
                replace("yyy", diasFaltantes.toString());
        return remplazarDiasFaltantesSuscripcion;
    }
}
