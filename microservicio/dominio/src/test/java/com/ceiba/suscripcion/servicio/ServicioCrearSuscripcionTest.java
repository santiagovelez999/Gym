package com.ceiba.suscripcion.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.suscripcion.modelo.entidad.Suscripcion;
import com.ceiba.suscripcion.puerto.repositorio.RepositorioSuscripcion;
import com.ceiba.suscripcion.servicio.testdatabuilder.SuscripcionTestDataBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ServicioCrearSuscripcionTest {

    private  BigDecimal VALOR_CON_DESCUENTO;
    private  BigDecimal VALOR_SIN_DESCUENTO;
    private  LocalDateTime FECHA_REGISTRO_CON_DESCUENTO;
    private  LocalDateTime FECHA_REGISTRO_SIN_DESCUENTO;
    private  String DESCUENTO;
    private  String SIN_DESCUENTO;

    @Before
    public void init() {
        VALOR_CON_DESCUENTO = new BigDecimal(65100);
        VALOR_SIN_DESCUENTO = new BigDecimal(70000);
        FECHA_REGISTRO_CON_DESCUENTO = LocalDateTime.
                of(2021,8,12,11,30,0);
        FECHA_REGISTRO_SIN_DESCUENTO = LocalDateTime.
                of(2021,8,13,11,30,0);
        DESCUENTO = "$4900";
        SIN_DESCUENTO = "$0";
    }


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

    @Test
    public void validarConDescuentoSuscripcionTest(){
        Suscripcion suscripcionTest = new
                SuscripcionTestDataBuilder().
                conTipoSuscripcion("XXX").
                conValorSuscripcion(new BigDecimal(70000)).
                conFechaRegistro(FECHA_REGISTRO_CON_DESCUENTO).build();
        Assert.assertEquals(VALOR_CON_DESCUENTO, suscripcionTest.getValorSuscripcion());
    }

    @Test
    public void validarSinDescuentoSuscripcionTest(){
        Suscripcion suscripcionTest = new
                SuscripcionTestDataBuilder().
                conTipoSuscripcion("XXX").
                conValorSuscripcion(new BigDecimal(70000)).
                conFechaRegistro(FECHA_REGISTRO_SIN_DESCUENTO).build();
        Assert.assertEquals(VALOR_SIN_DESCUENTO, suscripcionTest.getValorSuscripcion());
    }

    @Test
    public void calcularFechaDeVencimientoDeSuscripcionTest(){
        Suscripcion suscripcionTest = new
                SuscripcionTestDataBuilder().
                conTipoSuscripcion("XV").
                conValorSuscripcion(new BigDecimal(40000)).
                conFechaRegistro(FECHA_REGISTRO_CON_DESCUENTO).
                build();

        Assert.assertEquals("27/08/2021", suscripcionTest.calcularFechaVencimientoSuscripcion());
    }

    @Test
    public void mostrarDescuentoAplicadoPorSuscripcionTest(){
        Suscripcion suscripcionTest = new
                SuscripcionTestDataBuilder().
                conFechaRegistro(FECHA_REGISTRO_CON_DESCUENTO).
                conTipoSuscripcion("XXX").
                build();

        Assert.assertEquals(DESCUENTO, suscripcionTest.mostrarDescuento());
    }

    @Test
    public void mostrarDescuentoNoAplicadoPorSuscripcionTest(){
        Suscripcion suscripcionTest = new
                SuscripcionTestDataBuilder().
                conValorSuscripcion(new BigDecimal(40000)).
                conTipoSuscripcion("XV").
                build();

        Assert.assertEquals(SIN_DESCUENTO, suscripcionTest.mostrarDescuento());
    }


    @Test
    public void validarUsuarioExistenciaSuscripcionPreviaTest() {
        // arrange
        Suscripcion suscripcion = new SuscripcionTestDataBuilder().
                conFechaRegistro(FECHA_REGISTRO_CON_DESCUENTO).
                build();
        RepositorioSuscripcion repositorioSuscripcion = Mockito.mock(RepositorioSuscripcion.class);
        Mockito.when(repositorioSuscripcion.existe(Mockito.anyLong())).thenReturn(24);
        ServicioCrearSuscripcion servicioCrearSuscripcion = new ServicioCrearSuscripcion(repositorioSuscripcion);
        // act
        BasePrueba.assertThrows(() -> servicioCrearSuscripcion.ejecutar(suscripcion),
                ExcepcionDuplicidad.class,"El usuario 50 ya tiene una suscripción activa, aun tiene (24) días disponibles.");
    }
}
