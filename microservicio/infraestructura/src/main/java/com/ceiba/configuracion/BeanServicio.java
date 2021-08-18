package com.ceiba.configuracion;

import com.ceiba.suscripcion.puerto.repositorio.RepositorioSuscripcion;
import com.ceiba.suscripcion.servicio.ServicioActualizarSuscripcion;
import com.ceiba.suscripcion.servicio.ServicioCrearSuscripcion;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.ServicioActualizarUsuario;
import com.ceiba.usuario.servicio.ServicioCrearUsuario;
import com.ceiba.usuario.servicio.ServicioEliminarUsuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearUsuario servicioCrearUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioCrearUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioEliminarUsuario servicioEliminarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioEliminarUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioActualizarUsuario servicioActualizarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioActualizarUsuario(repositorioUsuario);
    }

    // -------------------- SUSCRIPCIÓN ------------- //

    @Bean
    public ServicioCrearSuscripcion ServicioCrearSuscripcion(RepositorioSuscripcion repositorioSuscripcion) {
        return new ServicioCrearSuscripcion(repositorioSuscripcion);
    }

    @Bean
    public ServicioActualizarSuscripcion ServicioActualizarSuscripcion(RepositorioSuscripcion repositorioSuscripcion) {
        return new ServicioActualizarSuscripcion(repositorioSuscripcion);
    }

}
