package com.ceiba.suscripcion.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.suscripcion.modelo.entidad.Suscripcion;
import com.ceiba.suscripcion.puerto.repositorio.RepositorioSuscripcion;

import java.util.HashMap;
import java.util.Map;

public class ServicioCrearSuscripcion {

    private static String elUsuarioYaTieneUnaSuscripcion = "El usuario xxx ya tiene una suscripción activa, aun tiene (yyy) días disponibles.";

    private final RepositorioSuscripcion repositorioSuscripcion;

    public ServicioCrearSuscripcion(RepositorioSuscripcion repositorioSuscripcion){
        this.repositorioSuscripcion = repositorioSuscripcion;

    }

    public Map<String, String> ejecutar(Suscripcion suscripcion){
        validarExistenciaPrevia(suscripcion);
        this.repositorioSuscripcion.crear(suscripcion);
        return armarRespuesta(suscripcion);
    }

    private void validarExistenciaPrevia(Suscripcion suscripcion) {
        Integer numeroDias =  this.repositorioSuscripcion.existe(suscripcion.getIdCliente());
        if(numeroDias != null && numeroDias > 0){
            throw new ExcepcionDuplicidad(duplicidadSuscripcionActiva(suscripcion.getIdCliente(),
                    numeroDias));
        }
    }

    private static final String duplicidadSuscripcionActiva(Long idCliente, Integer diasFaltantes){
        String remplazarUsuario = elUsuarioYaTieneUnaSuscripcion.
                replace("xxx",idCliente.toString());
         return remplazarUsuario.replace("yyy", diasFaltantes.toString());
    }

    private Map<String, String> armarRespuesta(Suscripcion suscripcion){
        Map<String, String> respuestaGuardado = new HashMap<>();
        respuestaGuardado.put("descuento", suscripcion.mostrarDescuento());
        respuestaGuardado.put("fechaDeVencimientoDeLaSuscripcion", suscripcion.calcularFechaVencimientoSuscripcion());
        return respuestaGuardado;
    }
}
