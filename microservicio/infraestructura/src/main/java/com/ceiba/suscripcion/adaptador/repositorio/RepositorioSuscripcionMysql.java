package com.ceiba.suscripcion.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.suscripcion.modelo.entidad.Suscripcion;
import com.ceiba.suscripcion.puerto.repositorio.RepositorioSuscripcion;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioSuscripcionMysql implements RepositorioSuscripcion {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="suscripcion", value="crear")
    private static String sqlCrear;


    public RepositorioSuscripcionMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate){
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }


    @Override
    public Long crear(Suscripcion suscripcion) {
        return this.customNamedParameterJdbcTemplate.crear(suscripcion, sqlCrear);
    }
}
