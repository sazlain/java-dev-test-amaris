### Proyecto Java Spring Boot y React.js en un contenedor Docker
Este es un proyecto que combina una aplicación Java Spring Boot en el backend y una aplicación React.js en el frontend, todo empaquetado en un contenedor Docker. Este README proporciona instrucciones para ejecutar y utilizar el proyecto.

### Requisitos previos
Asegúrate de tener instalados los siguientes componentes en tu máquina:

- Docker: [Instrucciones de instalación de Docker](https://docs.docker.com/get-docker/)


### Configuración del proyecto
- Clona este repositorio en tu máquina local:
`$ git clone https://github.com/sazlain/java-dev-test-amaris.git`

- Navega hasta el directorio raíz del proyecto:
`$ cd java-dev-test-amaris`

- Contruir el proyecto maven:
`$ mvn clean install`

- Ejecucion del proyecto:
`$ docker-compose up -d`

### Uso de la aplicación
La aplicación consiste en un frontend React.js que interactúa con un backend Spring Boot a través de una API REST. Puedes utilizar la aplicación para realizar operaciones de consulta de empleados.





## Caracteristicas del Backend

- Java version: 11
- SpringBoot version: 2.7.11

## Endpoints disponibles

+ GET: http://localhost:8080/api/employee?id={employeeId}
    + Descripción: Enpoint para consutar la informacion de un empleado filtrado por su id
+ GET: http://localhost:8080/api/employees
    + Descripción: Enpoint para consutar lista de empleados registrados

###Caracteristicas del frontend
- ReactJs version: 18.2.0
- Url [http://localhost](http://localhost)


## Autor
##### Azlain Saavedra
