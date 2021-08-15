package com.ceiba.suscripcion.consulta;

import com.ceiba.suscripcion.modelo.dto.DtoSuscripcion;
import com.ceiba.suscripcion.puerto.dao.DaoSuscripcion;

import java.util.List;

public class ManejadorListaSuscripcion {

    private DaoSuscripcion daoSuscripcion;

    public ManejadorListaSuscripcion(DaoSuscripcion daoSuscripcion){this.daoSuscripcion = daoSuscripcion;}

    public List<DtoSuscripcion> ejecutar(){return this.daoSuscripcion.listar();}
}
