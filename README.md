# 🏥 Sistema de Gestión de Citas - Clínica Salud Integral

Sistema de gestión de citas médicas implementado con **Arquitectura de Microservicios** usando Spring Boot. Permite administrar de forma independiente pacientes, médicos y citas médicas.

---

## 📋 Descripción

La clínica "Salud Integral" requería digitalizar el proceso de programación de citas médicas. Este sistema resuelve la problemática mediante una solución basada en microservicios que gestiona de forma independiente cada proceso del negocio.

---

## 🏗️ Arquitectura

```
microservicios/
├── paciente-service/   → Puerto 8085 | BD: bd_paciente
├── medico-service/     → Puerto 8086 | BD: bd_medico
└── citas-service/      → Puerto 8087 | BD: bd_citas
```

Cada microservicio tiene su **propia base de datos** y es completamente independiente.

---

## 🛠️ Tecnologías

| Tecnología | Versión |
|---|---|
| Java | 26 |
| Spring Boot | 4.1.0 |
| Spring Data JPA | 4.1.0 |
| Hibernate | 7.4.1 |
| MySQL | 8+ |
| Maven | 3.x |

---

## 📦 Microservicios

### 👤 paciente-service (Puerto 8085)

Responsable de administrar la información de los pacientes.

**Base de datos:** `bd_paciente`

**Endpoints:**

| Método | URL | Descripción |
|---|---|---|
| GET | `/api/pacientes` | Listar todos los pacientes |
| GET | `/api/pacientes/{id}` | Buscar paciente por ID |
| POST | `/api/pacientes` | Registrar paciente |
| PUT | `/api/pacientes/{id}` | Actualizar paciente |
| DELETE | `/api/pacientes/{id}` | Eliminar paciente |

**Ejemplo POST `/api/pacientes`:**
```json
{
  "dni": "12345678",
  "nombres": "Juan",
  "apellidos": "Pérez Gómez",
  "fechaNacimiento": "1990-05-15",
  "sexo": "M",
  "telefono": "987654321",
  "direccion": "Av. Javier Prado 123, Lima",
  "correo": "juan@gmail.com",
  "estado": "A"
}
```

---

### 🩺 medico-service (Puerto 8086)

Responsable de administrar la información de los médicos y especialidades.

**Base de datos:** `bd_medico`  
**Tablas:** `especialidad`, `medico`, `horario_medico`

**Endpoints Especialidad:**

| Método | URL | Descripción |
|---|---|---|
| GET | `/api/especialidades` | Listar especialidades |
| GET | `/api/especialidades/{id}` | Buscar por ID |
| POST | `/api/especialidades` | Registrar especialidad |
| PUT | `/api/especialidades/{id}` | Actualizar especialidad |
| DELETE | `/api/especialidades/{id}` | Eliminar especialidad |

**Endpoints Médico:**

| Método | URL | Descripción |
|---|---|---|
| GET | `/api/medicos` | Listar médicos |
| GET | `/api/medicos/{id}` | Buscar médico por ID |
| POST | `/api/medicos` | Registrar médico |
| PUT | `/api/medicos/{id}` | Actualizar médico |
| DELETE | `/api/medicos/{id}` | Eliminar médico |

**Ejemplo POST `/api/especialidades`:**
```json
{
  "nombre": "Cardiología",
  "descripcion": "Especialidad del corazón",
  "estado": "A"
}
```

**Ejemplo POST `/api/medicos`:**
```json
{
  "cmp": "12345",
  "nombres": "Carlos",
  "apellidos": "López Ríos",
  "telefono": "987654321",
  "correo": "carlos@clinica.com",
  "estado": "A",
  "idEspecialidad": 1
}
```

---

### 📅 citas-service (Puerto 8087)

Responsable de gestionar las citas médicas y atenciones.

**Base de datos:** `bd_citas`  
**Tablas:** `cita`, `atencion`

**Estados posibles de una cita:**
- `PROGRAMADA` → estado inicial
- `CONFIRMADA`
- `ATENDIDA`
- `CANCELADA`

**Endpoints Citas:**

| Método | URL | Descripción |
|---|---|---|
| GET | `/api/citas` | Listar todas las citas |
| GET | `/api/citas/{id}` | Buscar cita por ID |
| POST | `/api/citas` | Registrar cita |
| PUT | `/api/citas/{id}/estado` | Actualizar estado de cita |
| DELETE | `/api/citas/{id}/cancelar` | Cancelar cita |

**Endpoints Atenciones:**

| Método | URL | Descripción |
|---|---|---|
| GET | `/api/atenciones` | Listar atenciones |
| GET | `/api/atenciones/{id}` | Buscar atención por ID |
| POST | `/api/atenciones` | Registrar atención |

**Ejemplo POST `/api/citas`:**
```json
{
  "fecha": "2026-06-15",
  "hora": "10:00:00",
  "idPaciente": 1,
  "idMedico": 1,
  "motivo": "Consulta general"
}
```

**Ejemplo PUT `/api/citas/1/estado`:**
```json
{
  "estado": "CONFIRMADA"
}
```

---

## 🗄️ Base de Datos

### bd_paciente
```sql
CREATE TABLE paciente (
    id_paciente INT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(8) UNIQUE,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE,
    sexo CHAR(1),
    telefono VARCHAR(15),
    direccion VARCHAR(200),
    correo VARCHAR(100),
    estado CHAR(1) DEFAULT 'A'
);
```

### bd_medico
```sql
CREATE TABLE especialidad (
    id_especialidad INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(200),
    estado CHAR(1) DEFAULT 'A'
);

CREATE TABLE medico (
    id_medico INT AUTO_INCREMENT PRIMARY KEY,
    cmp VARCHAR(20) UNIQUE,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    telefono VARCHAR(15),
    correo VARCHAR(100),
    id_especialidad INT NOT NULL,
    estado CHAR(1) DEFAULT 'A',
    FOREIGN KEY (id_especialidad) REFERENCES especialidad(id_especialidad)
);

CREATE TABLE horario_medico (
    id_horario INT AUTO_INCREMENT PRIMARY KEY,
    id_medico INT NOT NULL,
    dia_semana VARCHAR(15),
    hora_inicio TIME,
    hora_fin TIME,
    FOREIGN KEY (id_medico) REFERENCES medico(id_medico)
);
```

### bd_citas
```sql
CREATE TABLE cita (
    id_cita INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    id_paciente INT NOT NULL,
    id_medico INT NOT NULL,
    motivo VARCHAR(300),
    estado VARCHAR(20) DEFAULT 'PROGRAMADA'
);

CREATE TABLE atencion (
    id_atencion INT AUTO_INCREMENT PRIMARY KEY,
    id_cita INT UNIQUE,
    diagnostico TEXT,
    tratamiento TEXT,
    observaciones TEXT,
    fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_cita) REFERENCES cita(id_cita)
);
```

---

## ▶️ Cómo ejecutar

### Prerrequisitos
- Java 17+
- MySQL corriendo en localhost:3306
- Maven

### Pasos

1. Clona el repositorio:
```bash
git clone https://github.com/Asbelien/Microservicios.git
cd Microservicios
```

2. Crea las bases de datos en MySQL:
```sql
CREATE DATABASE bd_paciente;
CREATE DATABASE bd_medico;
CREATE DATABASE bd_citas;
```

3. Ejecuta cada servicio desde IntelliJ IDEA o con Maven:
```bash
cd paciente-service
./mvnw spring-boot:run

cd medico-service
./mvnw spring-boot:run

cd citas-service
./mvnw spring-boot:run
```

4. Verifica que los servicios estén corriendo:
- paciente-service → http://localhost:8085/api/pacientes
- medico-service → http://localhost:8086/api/medicos
- citas-service → http://localhost:8087/api/citas

---

## 📝 Conclusiones

1. La arquitectura de microservicios permite separar cada proceso del negocio en servicios independientes, lo que facilita el mantenimiento y escalabilidad del sistema.

2. Spring Boot simplifica enormemente la creación de microservicios al proveer configuración automática, servidor embebido (Tomcat) y herramientas como Spring Data JPA.

3. El uso de DTOs permite controlar qué datos se reciben y se envían en cada endpoint, además de aplicar validaciones con anotaciones como `@NotNull` y `@NotBlank`.

4. Al trabajar con múltiples bases de datos independientes, no es posible usar foreign keys entre bases de datos distintas. Cada servicio guarda solo el ID de referencia, lo cual es un patrón estándar en arquitecturas distribuidas.

5. El manejo centralizado de excepciones mediante `@RestControllerAdvice` permite retornar respuestas de error claras y consistentes en todos los endpoints, mejorando la experiencia del desarrollador.

---

## 👤 Autor

**Elienai Asbel Ramos Escarrache**  
TECSUP - Desarrollo de Aplicaciones Web  
2026
