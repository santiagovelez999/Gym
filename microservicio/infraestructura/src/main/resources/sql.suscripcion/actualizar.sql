update suscripcion
set idCliente = :idCliente,
    valorSuscripcion = :valorSuscripcion,
    tipoSuscripcion = :tipoSuscripcion,
    fechaRegistro = :fechaRegistro
where idSuscripcion = :idSuscripcion