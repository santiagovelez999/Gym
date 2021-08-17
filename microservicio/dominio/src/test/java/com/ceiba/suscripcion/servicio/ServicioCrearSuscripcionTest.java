package com.ceiba.suscripcion.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.suscripcion.servicio.testdatabuilder.SuscripcionTestDataBuilder;
import org.junit.Test;

import java.math.BigDecimal;

public class ServicioCrearSuscripcionTest {

    @Test
    public void validarTipoSuscripcionTest(){
        SuscripcionTestDataBuilder suscripcionTestDataBuilder = new
                SuscripcionTestDataBuilder().conTipoSuscripcion("XXI");

        BasePrueba.assertThrows(() -> suscripcionTestDataBuilder.build(),
                ExcepcionValorInvalido.class,
                "El tipo de suscripción es invalido");
    }

    @Test
    public void validarValorSuscripcionTest(){
        SuscripcionTestDataBuilder suscripcionTestDataBuilder = new
                SuscripcionTestDataBuilder().conValorSuscripcion(new BigDecimal(80000));

        BasePrueba.assertThrows(() -> suscripcionTestDataBuilder.build(),
                ExcepcionValorInvalido.class,
                "El valor de la suscripción no es valido");
    }

    @Test
    public void validarRelacionTipoSuscripcionYValorSuscripcionTest(){
        SuscripcionTestDataBuilder suscripcionTestDataBuilder = new
                SuscripcionTestDataBuilder()
                .conTipoSuscripcion("XV")
                .conValorSuscripcion(new BigDecimal(70000));

        BasePrueba.assertThrows(() -> suscripcionTestDataBuilder.build(),
                ExcepcionValorInvalido.class,
                "El valor de la suscripción no es valido para el tipo de suscripción");
    }
}
