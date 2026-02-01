### 1. Definición de API

**Concepto:** Contrato (definiciones, entradas, salidas) que permite la integración y comunicación entre dos o más componentes tecnológicos.

* **Función:** Encapsula la lógica ("el cómo") y expone solo la funcionalidad ("el qué").

---

### 2. Estilos Arquitectónicos (Categorías)

* **RESTful:** Basado en principios REST (Representational State Transfer). Estándar ligero, usa JSON.
* **SOAP:** Protocolo basado en **XML**. Esquemas rígidos y mayor peso.
* **GRPC:** Basado en **HTTP/2**. Alta eficiencia, ideal para comunicación interna entre microservicios (backend-to-backend).
* **GraphQL:** Lenguaje de consulta. El **cliente define** exactamente qué datos necesita recibir.
* **Asíncronas:** Basadas en **eventos/mensajería** (colas). Desacoplamiento total, no requieren respuesta inmediata (no necesariamente Web).

---

### 3. Principios Clave RESTful

1. **Recursos:** Todo es un objeto/recurso manipulable (ej. Entidad JPA).
2. **Cliente-Servidor:** Separación de responsabilidades.
3. **Stateless (Sin estado):** El servidor no guarda sesión entre peticiones; cada request contiene toda la información necesaria.
4. **Uso correcto de HTTP:** Utilizar los verbos para la acción semántica correcta (GET, POST, PUT, DELETE).
5. **Representaciones:** Se transfiere una representación del recurso (JSON/XML), no el objeto físico de la BD.
6. **Códigos de Estado:** Feedback estandarizado sobre el resultado de la petición.
7. **Cacheable:** Capacidad de almacenar respuestas temporales para mejorar rendimiento.
8. **Sistema por Capas:** Arquitectura estructurada (Datos, Negocio, Controlador).

---

### 4. Modelo de Madurez de Richardson

Escala para medir qué tan "RESTful" es una API.

#### **Nivel 0: The Swamp of POX (El pantano)**

* Uso de HTTP solo como túnel de transporte.
* No distingue recursos ni verbos (todo suele ser POST a una única URL).

#### **Nivel 1: Recursos (Resources)**

* **Concepto:** Divide la API en recursos (URI) individuales.
* **Nomenclatura URI:** Autodescriptiva, sustantivos en plural, minúsculas.
* *Estructura:* `protocolo://host:puerto/contexto/api/versión/recurso`
* *Ejemplo:* `http://localhost:8080/matricula/api/v1.0/estudiantes`


* **Versionamiento:** Máximo 2 dígitos (`v1.0`). Semántica: *CambiosMayores.CambiosMenores.Errores*.

#### **Nivel 2: Verbos HTTP y Códigos de Estado**

Se compone de 3 elementos obligatorios:

1. **Verbos Correctos:**
* `GET`: Consultar (Idempotente).
* `POST`: Insertar/Crear.
* `PUT`: Actualizar recurso completo (Reemplazo).
* `PATCH`: Actualizar parcial (Solo campos específicos).
* `DELETE`: Eliminar.


2. **Códigos de Estado:** Respuesta semántica según el resultado.
3. **Content-Type Explícito:** Definir `Consumes` (entrada) y `Produces` (salida) en cabeceras.

#### **Nivel 3: HATEOAS**

* **Definición:** *Hypermedia As The Engine Of Application State*.
* **Objetivo:** La API es navegable. La respuesta incluye **enlaces (links)** a otros recursos relacionados, no solo datos.
* **Beneficio:** Desacopla al cliente de la estructura de las URIs.

---

### 5. Guía de Códigos de Estado HTTP

Clasificación por rangos:

* **1xx (Informativos):** Petición recibida, procesando.
* `100`: Continue (todo correcto, continúe enviando).
* `102`: Processing (servidor procesando, sin respuesta aún).


* **2xx (Satisfactorios):** Éxito.
* `200 OK`: Petición exitosa (General).
* `201 Created`: Recurso creado exitosamente (POST).
* `204 No Content`: Éxito, pero sin cuerpo de respuesta (ej. DELETE).


* **3xx (Redirección):** El recurso se ha movido.

* **4xx (Error del Cliente):** Solicitud incorrecta.
* `400 Bad Request`: Error de sintaxis o validación.
* `401 Unauthorized`: Falta autenticación (no logueado).
* `403 Forbidden`: Autenticado pero sin permisos (rol insuficiente).
* `404 Not Found`: Recurso no encontrado.
* `405 Method Not Allowed`: Verbo HTTP incorrecto para esa URI.
* `408 Request Timeout`: Tiempo de espera agotado.
* `415 Unsupported Media Type`: Formato de body incorrecto (ej. enviar XML a endpoint JSON).


* **5xx (Error del Servidor):** Fallo interno.
* `500 Internal Server Error`: Error no controlado en código/BD.
* `503 Service Unavailable`: Servidor no listo o saturado.



---

### 6. Arquitectura y Microservicios (Java/Quarkus)

#### **MicroProfile**

Especificaciones para construir microservicios en Java Enterprise.

* **Quarkus:** Framework nativo de Kubernetes para Java (implementa MicroProfile).
* **Ventaja:** Compilación nativa (GraalVM) = Menor consumo de RAM y arranque instantáneo.

#### **DDD (Domain Driven Design)**

Metodología donde el software se divide y diseña según los **dominios funcionales** del negocio.

#### **Estructuración por Capas (Paquetes)**

1. **Interfaces (API):** Controladores, exposición de recursos (Endpoints/Paths).
2. **Application (Service):** Lógica de negocio y orquestación.
3. **Domain (Model):** Entidades del negocio (Mapeo JPA).
4. **Infrastructure (Repository):** Acceso a datos y conexión a BD.