# Servicios REST y SpringBoot
## Contenido
1. [Introduccion](#introduccion)
2. [Configuracion](#configuracion)
3. [Parametros a usar](#parametros-get)
4. [Implementacion de Endpoints para Estudiantes](#implementacion-de-endpoints-para-estudiantes)
5. [Implementacion de Endpoints para Carreras](#implementacion-de-endpoints-para-carreras)
6. [Implementacion de Endpoints para Inscripciones](#implementacion-de-endpoints-para-inscripciones)
7. [Colaboradores](#colaboradores)
***
## Introduccion
_Este proyecto implementa una API REST desarrollada con Spring Boot para la gestión de inscripciones entre estudiantes y carreras universitarias.
El sistema permite administrar la relación muchos a muchos entre ambas entidades mediante una tabla intermedia_ `estudiante_carrera`_, que registra también datos adicionales como fecha de ingreso y egreso._

_A lo largo del desarrollo se incorporaron conceptos clave de la arquitectura de software, incluyendo el uso de JPA/Hibernate para la persistencia de datos, la definición de claves compuestas mediante_ `@Embeddable` _y_ `@EmbeddedId`_, y la implementación de operaciones CRUD completas desde los controladores hasta el repositorio._

_Además, se aplicaron buenas prácticas de diseño como la separación por capas (Controller, Service, Repository), el uso de transacciones con_ `@Transactional`.

**Objetivo Principal:**
_El objetivo principal del proyecto es desarrollar una API funcional, segura y mantenible que permita gestionar de manera eficiente la inscripción de estudiantes en distintas carreras universitarias._

_El sistema busca demostrar la correcta aplicación de los conceptos de mapeo objeto-relacional (ORM) y buenas prácticas en el desarrollo backend con Spring Boot, asegurando una arquitectura modular, escalable y fácil de adaptar a futuras extensiones._

**Formato de Datos:**
_Todos los datos intercambiados con la API se encuentran en formato JSON._

**Documentación:**
_Para una implementación exitosa, proporcionamos una documentación completa que detalla cada endpoint, los parámetros esperados, y ejemplos de solicitudes y respuestas._
***
## Configuracion
>[!IMPORTANT]
>* Ajustar credenciales en _application-prod.properties_

## Parametros GET
* **BY**: {accion}
    * byApellido
    * byNumLibreta
    * byGenero
    * byCarreraCiudad
    * byInscriptos
* **ORDER**: ASC/DESC
***

## Implementacion de Endpoints para Estudiantes

### 1. Obtener lista de estudiantes 

**Endpoint:** `.../estudiantes`

**Método:** `GET`

**Uso:** `http://.../estudiantes`

***
### 2. Obtener un estudiante por su ID

**Endpoint:** `.../estudiantes/:ID`

**Método:** `GET`

**Uso:** `http://.../estudiantes/4`

**Ejemplo de respuesta (JSON):**

```json
{
    "id": 4,
    "nombre": "Tomas",
    "apellido": "Martinez",
    "genero": "MASCULINO",
    "dni": 45577759,
    "ciudad": "Tres Arroyos",
    "fechaNacimiento": "2004-07-24",
    "numLibreta": 1
}
```
***
### 3. Eliminar un estudiante por su ID

**Endpoint:** `.../estudiantes/:ID`

**Método:** `DELETE`

**Uso:** `http://.../estudiantes/6`

***
### 4. Agregar un nuevo estudiante

**Endpoint:** `.../estudiantes`

**Método:** `POST`

**Uso:** `http://.../estudiantes`

**Ejemplo de body (JSON):**

```json
{
    "nombre": "Tomas",
    "apellido": "Martinez",
    "genero": "MASCULINO",
    "dni": 45577759,
    "ciudad": "Tres Arroyos",
    "numLibreta": 1,
    "fechaNacimiento": "2004-07-24"
}
```
***
### 5. Actualizar un estudiante por su ID

**Endpoint:** `.../estudiantes/:ID`

**Método:** `PUT`

**Uso:** `http://.../estudiantes/5`

**Ejemplo de body (JSON):**

```json
{
    "nombre": "Tomas",
    "apellido": "Martinez",
    "genero": "MASCULINO",
    "dni": 45577759,
    "ciudad": "Bahia Blanca",
    "numLibreta": 1,
    "fechaNacimiento": "2004-07-24"
}
```
***
### 6. Listar todos los estudiantes por apellido de manera ascendente o descendente

**Endpoint:** `.../estudiantes/:BY/:ORDER`

**Método:** `GET`

**Uso de ejemplo:** `http://.../estudiantes/byApellido/ASC`

***
### 7. Obtener un estudiante por su numero de libreta

**Endpoint:** `.../estudiantes/:BY/:ID`

**Método:** `GET`

**Uso de ejemplo:** `http://.../estudiantes/byNumLibreta/6`

***
### 8. Listar todos los estudiantes por tipo de genero

**Endpoint:** `.../estudiantes/:BY/:GENERO`

**Método:** `GET`

**Uso de ejemplo:** `http://.../estudiantes/byGenero/Femenino`

***
### 9. Listar todos los estudiantes por ID carrera y nombre de ciudad

**Endpoint:** `.../estudiantes/:BY/:ID?ciudad=CIUDAD`

**Método:** `GET`

**Uso de ejemplo:** `http://.../estudiantes/byCarreraCiudad/1?ciudad=tres arroyos`

***
## Implementacion de Endpoints para carreras

### 1. Obtener lista de carreras

**Endpoint:** `.../carreras`

**Método:** `GET`

**Uso:** `http://.../carreras`

***
### 2. Obtener una carrera por su ID

**Endpoint:** `.../carreras/:ID`

**Método:** `GET`

**Uso:** `http://.../carreras/1`

**Ejemplo de respuesta (JSON):**

```json
{
    "id": 1,
    "nombre": "Tecnicatura Universitaria en Desarrollo de Aplicaciones Informaticas"
}
```
***
### 3. Eliminar una carrera por su ID

**Endpoint:** `.../carreras/:ID`

**Método:** `DELETE`

**Uso:** `http://.../carreras/3`

***
### 4. Agregar una nueva carrera

**Endpoint:** `.../carreras`

**Método:** `POST`

**Uso:** `http://.../carreras`

**Ejemplo de body (JSON):**

```json
{
    "nombre": "Ingenieria"
}
```
***
### 5. Actualizar una carrera por su ID

**Endpoint:** `.../carreras/:ID`

**Método:** `PUT`

**Uso:** `http://.../carreras/4`

**Ejemplo de body (JSON):**

```json
{
    "nombre": "Ingenieria Industrial"
}
```
***
### 6. Listar todas las carreras por cantidad de inscriptos de manera ascendente o descendente

**Endpoint:** `.../carreras/:BY/:ORDER`

**Método:** `GET`

**Uso de ejemplo:** `http://.../carreras/byInscriptos/ASC`

***
## Implementacion de Endpoints para inscripciones

### 1. Obtener lista de inscripciones

**Endpoint:** `.../inscripciones`

**Método:** `GET`

**Uso:** `http://.../inscripciones`

***
### 2. Obtener una inscripcion por su ID

**Endpoint:** `.../inscripciones/:carreraID/:estudianteID`

**Método:** `GET`

**Uso:** `http://.../inscripciones/2/3

**Ejemplo de respuesta (JSON):**

```json
{
    "estudianteId": 3,
    "carreraId": 2,
    "fecha_ingreso": "2018-03-01",
    "fecha_egreso": null
}
```
***
### 3. Eliminar una inscripcion por su ID

**Endpoint:** `.../carreras/:carreraID/:estudianteID`

**Método:** `DELETE`

**Uso:** `http://.../carreras/2/3`

***
### 4. Agregar una nueva inscripcion

**Endpoint:** `.../inscripciones`

**Método:** `POST`

**Uso:** `http://.../inscripciones`

**Ejemplo de body (JSON):**

```json
{
    "estudianteId": 1,
    "carreraId": 4,
    "fecha_ingreso": "2024-05-15",
    "fecha_egreso": null
}
```
***
### 5. Actualizar una inscripcion

**Endpoint:** `.../inscripciones/`

**Método:** `PUT`

**Uso:** `http://.../inscripciones`

**Ejemplo de body (JSON):**

```json
{
    "estudianteId": 1,
    "carreraId": 4,
    "fecha_ingreso": "2024-05-15",
    "fecha_egreso": "2025-05-15"
}
```
***
### 6. Generar un reporte de las carreras, para cada carrera incluye información de los inscriptos y egresados por año. Se ordenan las carreras alfabéticamente, y se presentan los años de manera cronológica.

**Endpoint:** `.../inscripciones/:BY`

**Método:** `GET`

**Uso de ejemplo:** `http://.../inscripciones/reporte`

***

## Colaboradores

> * Langenheim, Geronimo V.
> * Lopez, Micaela N.
> * San Roman, Emanuel.
> * San Roman, Melanie.

