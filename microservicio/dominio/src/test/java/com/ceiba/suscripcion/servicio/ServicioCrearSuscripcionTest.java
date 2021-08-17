package com.ceiba.suscripcion.servicio;

import com.ceiba.suscripcion.modelo.entidad.Suscripcion;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ServicioCrearSuscripcionTest {

    private Long idSuscripcion;
    private Long idCliente;
    private BigDecimal valorSuscripcion;
    private String tipoSuscripcion;
    private LocalDateTime fechaRegistro;

    public ServicioCrearSuscripcionTest(){
          idCliente = 1L;
          valorSuscripcion = new BigDecimal(70000);
          tipoSuscripcion = "XXX";
          fechaRegistro = LocalDateTime.now();
    }

    public ServicioCrearSuscripcionTest conIdCliente(Long idCliente){
        this.idCliente = idCliente;
        return this;
    }

    public ServicioCrearSuscripcionTest conValorSuscripcion(BigDecimal valorSuscripcion){
        this.valorSuscripcion = valorSuscripcion;
        return this;
    }

    public ServicioCrearSuscripcionTest conTipoSuscripcion(String tipoSuscripcion){
        this.tipoSuscripcion = tipoSuscripcion;
        return this;
    }

    public ServicioCrearSuscripcionTest conFechaRegistro(LocalDateTime fechaRegistro){
        this.fechaRegistro = fechaRegistro;
        return this;
    }

    public Suscripcion build() {
        return new Suscripcion(idSuscripcion, idCliente, valorSuscripcion, tipoSuscripcion, fechaRegistro);
    }
}
