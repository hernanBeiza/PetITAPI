# PetITAPI

Repositorio para almacenar los servicios REST o API para el proyecto PetIT.
Este proyecto está realizado usando IntelliJ y  SpringBoot

## Patrón de Diseño
El patrón de diseño usado en este desarrollo es MVC. Sin embargo, presenta una variable en dónde se integran nuevos componentes en las capas:

### Models
#### DTOS
- Estas clases son finalmente objetos de transporte de datos
- Su objetivo es compartir datos en la aplicación, transportar, compartir entre las distintas capas

#### Entities
- Estas clases están direcamente relacionadas con las entidades de la base de datos
- Son una repressentación de la base de datos fidedignamente
- Además se agregan las relaciones como una propiedad en la clase (revisar)

### Persistence
#### DAOS
El objetivo de esta capa es:
- Obtiene los datos usando el JPA

### Services
El objetivo de esta capa es:
- Obtiene los datos consultando a los DAOS

## Tecnologías
Las tecnologías ocupadas para este proyecto son:

- IntelliJ
- SpringBoot

## Cómo aportar
- Crear otra rama
- Programar
- Subir al repositorio =)

## Otros detalles
Si queda alguna duda, revisar el diagrama de arquitectura `EsquemaServicioDAO.png`