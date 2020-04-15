## Ejercicio 2

Considerar el ejercicio N°2 del practico de máquina sobre Comunicación en SDyP, con las siguientes posibles operaciones para un usuario Cliente:
- RegistrarVenta
- RegistrarDevolucion
- ConsultarDisponibilidad
- ConsultarVentasRealizadas

Tener en cuenta que existe una disponibilidad limitada de entradas para la venta, de acuerdo a cada una de las ubicaciones posibles (Platea, General y Preferencial).
Pensar las distintas posibilidades de solapamientos de operaciones entre varios vendedores operando con un único Servidor.

*a) ¿Se generaría algún problema? Explique y de ejemplos.*

Si, se puede generar un problema en el caso que dos personas quieran adquirir el último lugar de un sector al mismo tiempo. Un problema menor sería que se consulte la disponibilidad al mismo tiempo que se realiza la compra de un lugar. 

*b) ¿Qué solución implementaría para permitir un correcto funcionamiento del sistema? Explique con sus palabras.*

Estos problemas se pueden solucionar parcialmente con la implementación de una reserva durante la compra como sucede con algunas empresas de viajes, es decir, ni bien se elije el sector se reserva dicha localidad de manera temporal hasta que se realice la compra, se cancele la operación o se supere un tiempo límite de reserva. Este método no es infalible pero reduce dramáticamente las posibilidades de la llegada de dos o más confirmaciones de compra al mismo tiempo.  
