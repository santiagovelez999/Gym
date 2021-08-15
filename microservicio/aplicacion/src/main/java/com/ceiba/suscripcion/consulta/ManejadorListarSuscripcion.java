package com.ceiba.suscripcion.consulta;

import com.ceiba.suscripcion.modelo.dto.DtoSuscripcion;
import com.ceiba.suscripcion.puerto.dao.DaoSuscripcion;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarSuscripcion {

    private DaoSuscripcion daoSuscripcion;

    public ManejadorListarSuscripcion(DaoSuscripcion daoSuscripcion){this.daoSuscripcion = daoSuscripcion;}

    public List<DtoSuscripcion> ejecutar(){return this.daoSuscripcion.listar();}
}
