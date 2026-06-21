# Trabajo Práctico: Comparable y Comparator

---

## Parte 1

### Pregunta 1: ¿Por qué `Collections.sort()` no compila cuando le pasamos una `List<Estudiante>`? ¿Qué contrato exige Java que nuestra clase no está cumpliendo?

**Respuesta:** `Collections.sort()` espera recibir una lista cuyos elementos implementen la interfaz genérica `Comparable<? super T>`. Esta interfaz define un orden natural mediante el método `compareTo()`. Como nuestra clase `Estudiante` es una entidad personalizada y no implementa esta interfaz, Java no sabe de manera explícita bajo qué criterio (legajo, promedio o edad) debe ordenar los objetos, produciendo un error en tiempo de compilación para resguardar la seguridad de tipos.

## Parte 2

### Pregunta 2: ¿Por qué elegiste el atributo `promedio` como orden natural? ¿Qué pasaría si mañana un nuevo requisito pide ordenar por `cantidadMateriasAprobadas`? ¿Modificarías `compareTo`? ¿Qué consecuencias tendría?

**Respuesta:** Se selecciona el promedio debido a que constituye el criterio por defecto y más habitual para evaluar el desempeño general de un alumno en el contexto del dominio universitario. Si se exigiera ordenar por materias aprobadas, modificar de forma directa el método `compareTo` afectaría a todas las colecciones que dependan de ese orden implícito a lo largo de la aplicación. Para evitar efectos secundarios indeseados se deben implementar comparadores alternativos mediante la interfaz `Comparator`.

### Pregunta 3: `Comparable` nos ata a un único criterio de ordenamiento. ¿Qué problemas de diseño introduce esto si nuestro sistema necesitara ordenar la misma lista de estudiantes de 4 formas distintas según el contexto? Relacioná tu respuesta con los principios de responsabilidad única (SRP) y abierto/cerrado (OCP).

**Respuesta:** Centralizar múltiples formas de ordenamiento dentro de la entidad quiebra el principio SRP, puesto que la clase acumularía diversas responsabilidades de comparación cambiantes. Del mismo modo, altera el principio OCP, dado que incorporar o variar los criterios nos obligaría a modificar constantemente el código de la clase `Estudiante` en lugar de extender las funcionalidades mediante módulos externos e independientes.
