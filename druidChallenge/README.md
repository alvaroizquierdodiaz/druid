# Prueba técnica DRUID

Requisitos técnicos:

* JDK 1.8 o superior.
* Spring boot 2.x / Spring 5.x o superior
* JPA 2.x o superior
* JSF 2.3 o superior (en este caso se ha llegado a un acuerdo para hacer una API REST en vez de una app con JSF)

Ejercicios:

1. Proyecto web JSF (o API REST securizada con roles en este caso) que permita la creación, listado y borrado de usuarios.
   
La persistencia debe realizarse en base de datos (se permite base de datos en memoria) mediante un ORM JPA 2.x. Además, el proyecto se debe construir con Maven.
   
1.1. La información mínima del usuario será:
   * Email
   * Contraseña
   * Nombre y apellidos
   * Fecha de nacimiento

2. Añadir la validación de campos al proyecto:
   * El email debe cumplir el formato correcto
   * La contraseña debe tener una mayúscula, un número, un carácter especial y al menos 8 caracteres.
   * El nombre y apellidos debe contener caracteres correctos (ej. no se pueden introducir números)
   * La edad del usuario debe ser mayor de 14 años


3. Añadir filtros al listado de usuarios
   * Por email
   * Entre un rango de fechas de nacimiento

* Todos los ejercicios son obligatorios.
* El proyecto se debe subir a Github/Gitlab para poder ser descargado y testado.
* Es deseable realizar el desarrollo en git con un histórico correcto y no ver un solo commit con todo el código.
* Se debe proporcionar toda la documentación necesaria para la instalación / ejecución del proyecto, así como los requisitos necesarios para ello
 (puede estar dentro del repositorio o adjuntarse en el correo que se  indica en la próxima sección).
* No se aceptarán proyectos que no compilen, cuyas instrucciones de instalación/ejecución no sean claras o que no se puedan ejecutar porque acceden a recursos
  externos no accesibles.
* Se pueden usar más frameworks/librerías/tecnologías (ej. utilizar Joinfaces para JSF + Spring boot) a parte de los obligatorios, de forma totalmente libre.
* El proyecto será defendido en una entrevista posterior.

# Ejecutar la prueba

Hay que tener lo siguiente instalado:

- Maven
- JDK 11
- Lombok

Para poder ejecutar la aplicación hay que seguir los siguientes pasos:

- Acceder desde un terminal a la carpeta de druidChallenge
- Ejecutar el comando: mvn spring-boot:run
- Acceder a la URL: http://localhost:8080/swagger-ui/index.html
- Para poder probar los métodos del servicio, hay que darle al botón "Try it out" y poner los valores tal y como se indican en la documentación del servicio
- Luego pulsar en el botón "Execute"