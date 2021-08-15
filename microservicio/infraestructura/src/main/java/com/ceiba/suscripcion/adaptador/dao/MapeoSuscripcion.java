package com.ceiba.suscripcion.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.suscripcion.modelo.dto.DtoSuscripcion;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoSuscripcion implements RowMapper<DtoSuscripcion>, MapperResult {

    @Override
    public DtoSuscripcion mapRow(ResultSet resultSet, int rowNum) throws SQLException {
         Long idSuscripcion = resultSet.getLong("idSuscripcion");
         Long idCliente = resultSet.getLong("idCliente");
         BigDecimal valorSuscripcion = resultSet.getBigDecimal("valorSuscripcion");
         String tipoSuscripcion = resultSet.getString("tipoSuscripcion");
         LocalDateTime fechaRegistro = extraerLocalDateTime(resultSet, "fechaRegistro");

         return new DtoSuscripcion(idSuscripcion, idCliente, valorSuscripcion,
                    tipoSuscripcion, fechaRegistro);
    }
}
