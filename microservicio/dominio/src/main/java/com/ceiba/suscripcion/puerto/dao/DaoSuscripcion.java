package com.ceiba.suscripcion.puerto.dao;

import com.ceiba.suscripcion.modelo.dto.DtoSuscripcion;
import java.util.List;

public interface DaoSuscripcion {

    /**
     * Permite listar Suscripción
     * @return las Suscripciones
     */
    List<DtoSuscripcion> listar();
}
