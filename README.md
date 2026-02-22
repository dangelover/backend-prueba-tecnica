# Prueba Técnica – API de Productos (Spring WebFlux)

## 📌 Descripción

API REST desarrollada con **Spring Boot 3 + WebFlux** bajo principios de **Arquitectura Hexagonal (Ports & Adapters)**.

La aplicación permite gestionar productos mediante operaciones CRUD utilizando programación reactiva y persistencia no bloqueante con **R2DBC** y base de datos **H2**.

---

## 🏗 Arquitectura

El proyecto sigue el enfoque de **Arquitectura Hexagonal**, separando responsabilidades en:

### Domain
- Modelo de negocio (`Product`)
- Puertos de entrada (`ProductUseCase`)
- Puertos de salida (`ProductRepositoryPort`)
- Excepciones de dominio

### Application
- Implementación de casos de uso (`ProductService`)

### Infrastructure
- Adaptadores de persistencia (R2DBC)
- Configuración de Beans
- Manejador global de excepciones

Esta estructura permite:

- Separación clara de responsabilidades
- Independencia del framework
- Mayor facilidad de pruebas
- Código mantenible y escalable

---

## 🚀 Tecnologías utilizadas

- Java 17
- Spring Boot 3
- Spring WebFlux
- Spring Data R2DBC
- H2 Database
- Mockito
- Reactor Test
- Gradle

---

## ▶ Cómo ejecutar el proyecto

### 1️⃣ Clonar el repositorio

```bash
git clone https://github.com/dangelover/backend-prueba-tecnica.git
cd backend-prueba-tecnica
```

### 2️⃣ Ejecutar la aplicación

En Linux / Mac:

```bash
./gradlew bootRun
```

En Windows:

```bash
gradlew.bat bootRun
```

La aplicación se iniciará en:

```
http://localhost:8080
```

---

## 🧪 Ejecutar pruebas

```bash
./gradlew test
```

Se incluyen pruebas unitarias para:

- `findById` (caso exitoso)
- `findById` (caso error)
- `update` (flujo reactivo completo)

Las pruebas utilizan:

- Mockito para mockeo
- StepVerifier para validación reactiva

---

## 📡 Endpoints disponibles

### Obtener todos los productos
```
GET /products
```

### Obtener producto por ID
```
GET /products/{id}
```

### Crear producto
```
POST /products
```

### Actualizar producto
```
PUT /products/{id}
```

### Eliminar producto
```
DELETE /products/{id}
```

---

## ⚠ Manejo de errores

La aplicación implementa un `GlobalExceptionHandler` que maneja:

- Producto no encontrado (`ProductNotFoundException`)
- Errores generales del sistema

Las respuestas se devuelven en formato JSON con códigos HTTP apropiados.

---

## 🧠 Consideraciones técnicas

- Se utiliza programación reactiva para evitar bloqueos.
- La persistencia se realiza mediante R2DBC (no JDBC).
- La arquitectura permite cambiar la base de datos sin afectar el dominio.
- Las pruebas unitarias validan flujos reactivos y lógica de negocio.

---

## 📌 Notas finales

Este proyecto fue desarrollado como parte de una prueba técnica, priorizando:

- Buenas prácticas
- Separación de responsabilidades
- Código limpio
- Cobertura básica de pruebas
- Manejo adecuado de errores