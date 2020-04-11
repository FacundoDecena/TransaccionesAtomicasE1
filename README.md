# Practico 5

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
