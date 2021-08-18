package com.ceiba.suscripcion.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.suscripcion.modelo.entidad.Suscripcion;
import com.ceiba.suscripcion.puerto.repositorio.RepositorioSuscripcion;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;


import static java.time.temporal.ChronoUnit.DAYS;

@Repository
public class RepositorioSuscripcionMysql implements RepositorioSuscripcion {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="suscripcion", value="crear")
    private static String sqlCrear;

    @SqlStatement(namespace="suscripcion", value="existe")
    private static String sqlExiste;

    @SqlStatement(namespace="suscripcion", value="actualizar")
    private static String sqlActualizar;


    public RepositorioSuscripcionMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate){
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }


    @Override
    public Long crear(Suscripcion suscripcion) {
        return this.customNamedParameterJdbcTemplate.crear(suscripcion, sqlCrear);
    }

    @Override
    public void actualizar(Suscripcion suscripcion) {
        this.customNamedParameterJdbcTemplate.actualizar(suscripcion, sqlActualizar);
    }

    @Override
    public Integer existe(Long idCliente) {
        String fechaRecienteYTipoSuscripcion = "";
        try {
            MapSqlParameterSource paramSource = new MapSqlParameterSource();
            paramSource.addValue("idCliente", idCliente);
            fechaRecienteYTipoSuscripcion =  this.customNamedParameterJdbcTemplate.
                    getNamedParameterJdbcTemplate().queryForObject(sqlExiste,paramSource, String.class);
            return fechaRecienteYTipoSuscripcion != null?prepararFecha(fechaRecienteYTipoSuscripcion):null;
        }catch (RuntimeException e){
            Logger.getLogger(e.getMessage()).info("ERROR");
            return null;
        }
    }

    private Integer prepararFecha(String fechaRecienteYTipoSuscripcion){
        String separador = "/";
        String[] arregloDeValores = fechaRecienteYTipoSuscripcion.split(separador);
        String fecha = arregloDeValores[0].trim();
        String tipoServicio = arregloDeValores[1].trim();
        return !fecha.isEmpty()?convertirFechaADias(fecha, tipoServicio):null;
    }

    private Integer convertirFechaADias(String fecha, String tipoServicio){
        try{
            int diasMensuales = 30;
            int diasQuincenales = 15;
            String tipoServicioMensual = "XXX";
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            fecha = validarNanoSegundosFecha(fecha);
            LocalDateTime fechaReciente = LocalDateTime.parse(fecha, formato);
            LocalDateTime fechaConSumaDias= fechaReciente.plusDays(tipoServicio.equals(tipoServicioMensual) ?diasMensuales:diasQuincenales);
            Long dias = DAYS.between(LocalDateTime.now(), fechaConSumaDias);
            return dias == null ? null : Math.toIntExact(dias);
        }catch (RuntimeException  e){
            Logger.getLogger(e.getMessage()).info("ERROR");
            return null;
        }
    }

    private String validarNanoSegundosFecha(String fecha){
        int cantidadDigitosPermitidosFecha = 19;
        if(fecha.length() > cantidadDigitosPermitidosFecha){
            return fecha.substring(0, cantidadDigitosPermitidosFecha);
        }else{
            return fecha;
        }
    }
}
