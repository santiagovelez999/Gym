select DATEDIFF(fechaRegistro, now())
from suscripcion where idCliente = :idCliente and fechaRegistro < now();