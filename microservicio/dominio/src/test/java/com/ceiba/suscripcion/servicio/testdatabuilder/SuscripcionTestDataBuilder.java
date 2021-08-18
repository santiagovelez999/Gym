package com.ceiba.suscripcion.servicio.testdatabuilder;

import com.ceiba.suscripcion.modelo.entidad.Suscripcion;
import com.ceiba.suscripcion.servicio.ServicioCrearSuscripcionTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SuscripcionTestDataBuilder {

    private Long idSuscripcion;
    private Long idCliente;
    private BigDecimal valorSuscripcion;
    private String tipoSuscripcion;
    private LocalDateTime fechaRegistro;

    public SuscripcionTestDataBuilder(){
        idCliente = 50L;
        valorSuscripcion = new BigDecimal(70000);
        tipoSuscripcion = "XXX";
        fechaRegistro = LocalDateTime.now();
    }

    public SuscripcionTestDataBuilder conIdCliente(Long idCliente){
        this.idCliente = idCliente;
        return this;
    }

    public SuscripcionTestDataBuilder conValorSuscripcion(BigDecimal valorSuscripcion){
        this.valorSuscripcion = valorSuscripcion;
        return this;
    }

    public SuscripcionTestDataBuilder conTipoSuscripcion(String tipoSuscripcion){
        this.tipoSuscripcion = tipoSuscripcion;
        return this;
    }

    public SuscripcionTestDataBuilder conFechaRegistro(LocalDateTime fechaRegistro){
        this.fechaRegistro = fechaRegistro;
        return this;
    }

    public Suscripcion build() {
        return new Suscripcion(idSuscripcion, idCliente, valorSuscripcion, tipoSuscripcion, fechaRegistro);
    }
}
