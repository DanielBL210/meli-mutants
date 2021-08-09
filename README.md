# MELI Mutants
#### Hecha por Daniel Botero Londoño
meli-mutants es una aplicacion que permite analizar ADN de humanos 
y ofrece estadisticas sobre los resultados a traves de los siguientes servicio

* **/mutant:** Permite analizar el ADN de un humano verificando si es mutante o no. Recibe como parametro un vector con el ADN del humano. Retorna un HttpsStatus 200 si es mutante y un HttpsStatus 403 si NO es mutante.
* **/stats:** Este servicio permite saber el numero de ADN analizados, cuantos de los ADN analizados pertenecen a mutantes y cual es la proporcion de mutantes con relacion al total.

## Servicios expuestos

A continuacion se listan las URLs de los servicios expuestos 

* **/mutants:** POST http://melimutantsapp-env.eba-uxtpmamb.us-east-2.elasticbeanstalk.com/mutant

* **/stats:** GET http://melimutantsapp-env.eba-uxtpmamb.us-east-2.elasticbeanstalk.com/stats


Se pueden consumir utilizando una herramienta como Postman.

## Swagger UI

Para conocer mejor como esta implementados estos servicios se pueden dirigir
al swagger expuesto en la siguiente URL.  

http://melimutantsapp-env.eba-uxtpmamb.us-east-2.elasticbeanstalk.com/swagger-ui-mutants.html

## Ejemplos

Para consumir el servicio "/mutants" se debe enviar un objeto similar al del siguiente ejemplo:

```json
{
    "dna":["ATGCGA","CAAAAC","ATATTT","AGACGG","ACACCA","TCACTG"]
}
```

Para consumir el servicio "/stats" solo se necesita consumir la url expuesta