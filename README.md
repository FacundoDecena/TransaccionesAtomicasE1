# Practico 5

### Id de Cuentas
1. 000-0
1. 000-1
1. 000-2
1. 000-3
1. 000-4
1. 000-5
1. 000-6
1. 000-7
1. 000-8
1. 000-9

### Para ejecutar desde la misma computadora:

* Desde /build ejecutar:
    * ```bash
        java Server
        ```
    * ```bash
        java Client
        ```

### Para ejecutar desde distintas computadoras:

#### Servidor
* Desde /build ejecutar:
    * ```bash
        java -Djava.security.policy=no.policy Server
        ```
#### Cliente
* Desde /build ejecutar:
    * ```bash
        java -Djava.security.policy=no.policy Client [host] [puerto]
        ```
        Por defecto tiene localhost y 3001.

<br>

## Para forzar equivalencia secuencial

Se asumirá que para cada caso se ejecuta todo desde 0.

1. Ejecutar Servidor

1. Ejecutar Cliente en una terminal
1. Ejecutar Cliente en otra terminal
1. En la primer terminal seleccionar la opcion "2 - Ingresar dinero"
1. Ingresar el numero de cuenta 000-0
1. Ingresar el monto a depositar, por ejemplo 500
1. Al hacer enter, el programa se detendra en un punto inseguro, en este momento nos pasamos a la segunda terminal con un cliente
1. Seleccionamos "2 - Ingresar dinero"
1. Ingresar el numero de cuenta 000-1
1. Ingresar el monto a depositar, por ejemplo 500
1. Terminamos la ejecucion en esta terminal volvemos a la primer terminal
1. Terminamos la operacion.

#### En el servidor ocurre lo siguiente
1. Establece coneccion con el primer cliente

1. Establece coneccion con el segundo cliente
1. Lee el balance de la cuenta 000-0 $0
1. Lee el balance de la cuenta 000-1 $0
1. Escribe el nuevo balance de la cuenta 000-0 $500
1. Escribe el nuevo balance de la cuenta 000-1 $500

<br><br><br><br><br><br><br><br><br><br>

## Para forzar actualizaciones perdidas

1. Ejecutar Servidor
1. Ejecutar Cliente en una terminal
1. Ejecutar Cliente en otra terminal
1. En la primer terminal seleccionar la opcion "2 - Ingresar dinero"
1. Ingresar el numero de cuenta 000-0
1. Ingresar el monto a depositar, por ejemplo 500

1. Al oprimir **enter**, el programa se detendra en un punto inseguro, en este momento nos pasamos a la segunda terminal con un cliente

1. Seleccionamos "2 - Ingresar dinero"
1. Ingresar el numero de cuenta 000-0
1. Ingresar el monto a depositar, por ejemplo 500
1. Terminamos la ejecucion en esta terminal volvemos a la primer terminal
1. Terminamos la operacion.

#### En el servidor ocurre lo siguiente
1. Establece coneccion con el primer cliente

1. Establece coneccion con el segundo cliente
1. Lee el balance de la cuenta 000-0 $0
1. Lee el balance de la cuenta 000-0 $0
1. Escribe el nuevo balance de la cuenta 000-0 $500
1. Escribe el nuevo balance de la cuenta 000-0 $500
1. Se perdieron los primeros $500

<br><br><br><br><br><br><br><br><br><br>

## Para forzar recuperaciones inconsistentes.

1. Ejecutar Servidor
1. Ejecutar Cliente en una terminal
1. Ejecutar Cliente en otra terminal
1. En la primer terminal seleccionar la opcion "2 - Ingresar dinero"
1. Ingresar el numero de cuenta 000-0
1. Ingresar el monto a depositar, por ejemplo 500
1. terminamos la operacion

1. Volvemos a ejecutar Cliente en esta terminal

1. En una terminal cliente seleccionamos "4 - Transferencia de dinero"

1. Ingresar el numero de cuenta 000-0
1. Ingresar el numero de cuenta 000-1

1. Ingresar el monto a tranferir, por ejemplo 500, menor o igual que el depositado en los pasos anteriores.

1. Al oprimir **enter**, el programa se detendra en un punto inseguro, en este momento nos pasamos a la otra terminal con un cliente

1. seleccionamos la opcion "5 - Ver total del dinero"
1. Terminamos la operacion.

1. Volvemos a la terminal anterior
1. Terminamos la ejecucion


#### En el servidor ocurre lo siguiente
1. Establece coneccion con el primer cliente

1. Establece coneccion con el segundo cliente

1. Lee el balance de la cuenta 000-0 $0

1. Escribe el nuevo balance de la cuenta 000-0 $500

1. Establece una nueva coneccion con el cliente

1. Lee el balance de la cuenta 000-0 $500

1. Escribe el nuevo balance de la cuenta 000-0 $0

1. Realiza la operacion de verificar cuanto dinero hay en el banco $0

1. Lee el balance de la cuenta 000-1 $0

1. Escribe el nuevo balance de la cuenta 000-0 $500


### Observacion:

Se encuentra implementada una forma para evitar que estos errores sucedan. Para probarlo decomente del codigo del servidor las lineas 46, 61 y 62 y del cliente las lineas 66 y 153, vuelva a compilar los archivos y repita los pasos mencionados anteriormente.