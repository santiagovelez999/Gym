package com.ceiba.suscripcion.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.suscripcion.modelo.entidad.Suscripcion;
import com.ceiba.suscripcion.puerto.repositorio.RepositorioSuscripcion;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class RepositorioSuscripcionMysql implements RepositorioSuscripcion {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="suscripcion", value="crear")
    private static String sqlCrear;

    @SqlStatement(namespace="suscripcion", value="existe")
    private static String sqlExiste;


    public RepositorioSuscripcionMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate){
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }


    @Override
    public Long crear(Suscripcion suscripcion) {
        return this.customNamedParameterJdbcTemplate.crear(suscripcion, sqlCrear);
    }

    @Override
    public Integer existe(Long idCliente) {
        Integer NumeroDeDiasFaltantes = null;
        try {
            MapSqlParameterSource paramSource = new MapSqlParameterSource();
            paramSource.addValue("idCliente", idCliente);
            NumeroDeDiasFaltantes =  this.customNamedParameterJdbcTemplate.
                    getNamedParameterJdbcTemplate().queryForObject(sqlExiste,paramSource,
                            Integer.class);
        }catch (EmptyResultDataAccessException e){
            NumeroDeDiasFaltantes = null;
        }
        return NumeroDeDiasFaltantes;

    }
}
