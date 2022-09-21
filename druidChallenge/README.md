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
   ▪ Email
   ▪ Contraseña
   ▪ Nombre y apellidos
   ▪ Fecha de nacimiento

2. Añadir la validación de campos al proyecto:
   ▪ El email debe cumplir el formato correcto
   ▪ La contraseña debe tener una mayúscula, un número, un carácter especial y al menos 8 caracteres.
   ▪ El nombre y apellidos debe contener caracteres correctos (ej. no se pueden introducir números)
   ▪ La edad del usuario debe ser mayor de 14 años

3. Añadir filtros al listado de usuarios
   ▪ Por email
   ▪ Entre un rango de fechas de nacimiento