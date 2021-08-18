create table usuario (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 clave varchar(45) not null,
 fecha_creacion datetime null,
 primary key (id)
);

create table suscripcion (
 idSuscripcion int(11) not null auto_increment,
 idCliente int(11) not null,
 valorSuscripcion decimal(19,2) not null,
 tipoSuscripcion varchar(30) not null,
 fechaRegistro datetime null,
 primary key (idSuscripcion)
);