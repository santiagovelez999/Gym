package com.ceiba.suscripcion.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.suscripcion.modelo.dto.DtoSuscripcion;
import com.ceiba.suscripcion.puerto.dao.DaoSuscripcion;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoSuscripcionMysql implements DaoSuscripcion {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="suscripcion", value="listar")
    private static String sqlListar;

    public DaoSuscripcionMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate){
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoSuscripcion> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoSuscripcion());
    }
}
