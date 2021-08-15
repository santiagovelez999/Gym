create table suscripcion (
 idSuscripcion int(11) not null auto_increment,
 idCliente int(11) not null,
 valorSuscripcion decimal(19,2) not null,
 tipoSuscripcion varchar(30) not null,
 fechaRegistro datetime null,
 primary key (idSuscripcion)
);