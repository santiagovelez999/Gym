package com.ceiba.suscripcion.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.suscripcion.modelo.entidad.Suscripcion;
import com.ceiba.suscripcion.puerto.repositorio.RepositorioSuscripcion;
import com.ceiba.usuario.modelo.entidad.Usuario;

import java.time.LocalDateTime;

public class ServicioCrearSuscripcion {

    private static String EL_USUARIO_YA_TIENE_SUSCRIPCION_ACTIVA = "El usuario xxx ya tiene una suscripción activa, aun tiene (yyy) días disponibles.";


    private final RepositorioSuscripcion repositorioSuscripcion;

    public ServicioCrearSuscripcion(RepositorioSuscripcion repositorioSuscripcion){
        this.repositorioSuscripcion = repositorioSuscripcion;

    }

    public Long ejecutar(Suscripcion suscripcion){
        validarExistenciaPrevia(suscripcion);
        return this.repositorioSuscripcion.crear(suscripcion);
    }

    private void validarExistenciaPrevia(Suscripcion suscripcion) {
        Integer numeroDias =  this.repositorioSuscripcion.existe(suscripcion.getIdCliente());
        if(numeroDias != null && numeroDias < 0){
            Integer numeroDiasPositivo = numeroDias.intValue() * +1;
            throw new ExcepcionDuplicidad(duplicidadSuscripcionActiva(suscripcion.getIdCliente(),
                    numeroDiasPositivo));
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
