package com.ceiba.suscripcion.puerto.repositorio;

import com.ceiba.suscripcion.modelo.entidad.Suscripcion;
import com.ceiba.usuario.modelo.entidad.Usuario;

import java.time.LocalDateTime;

public interface RepositorioSuscripcion {

    /**
     * Permite crear una suscripcion
     * @param suscripcion
     * @return el id generado
     */
    Long crear(Suscripcion suscripcion);

    /**
     * Permite validar si existe un usuario con una suscripcion activa
     * @param idCliente
     * @return si existe o no
     */
    Integer existe(Long idCliente);
}
