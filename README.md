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
