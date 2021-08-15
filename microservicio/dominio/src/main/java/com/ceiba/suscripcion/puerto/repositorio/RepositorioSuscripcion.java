package com.ceiba.suscripcion.puerto.repositorio;

import com.ceiba.suscripcion.modelo.entidad.Suscripcion;
import com.ceiba.usuario.modelo.entidad.Usuario;

public interface RepositorioSuscripcion {

    /**
     * Permite crear una suscripcion
     * @param suscripcion
     * @return el id generado
     */
    Long crear(Suscripcion suscripcion);
}
