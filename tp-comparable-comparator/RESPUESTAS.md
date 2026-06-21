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

## Parte 3

### Pregunta 4: Explicá con tus palabras qué es un overflow de enteros, por qué el "truco de la resta" lo provoca, qué parte del contrato de `Comparator` rompe, y por qué `Integer.compare()` no sufre este problema.

**Respuesta:** Un overflow de enteros ocurre cuando una operación aritmética produce un valor numérico que excede el límite máximo o mínimo que el tipo de dato (`int` de 32 bits en Java) puede representar, provocando que el valor "de la vuelta" y se transforme en un número con el signo opuesto. El "truco de la resta" provoca esto al restar un número negativo a uno extremadamente grande (ej. `Integer.MAX_VALUE - (-1)`), lo que resulta en un valor negativo inválido. Esto rompe el contrato de `Comparator` porque el método devuelve un signo erróneo, invirtiendo la relación lógica de orden. `Integer.compare()` no sufre este problema porque no realiza una resta; en su lugar, utiliza evaluaciones relacionales directas (`<`, `>`) para determinar cuál operando es mayor, retornando de forma segura `-1`, `0` o `1`.

## Parte 4 — Integración con Spring Boot

### Pregunta 5: ¿Qué patrón de diseño estás aplicando al usar un `Map<String, Comparator<T>>` en lugar de un `switch`? Explicá cómo se relaciona este patrón con el polimorfismo y por qué es preferible a la alternativa procedural.

**Respuesta:** Al mapear identificadores de texto a comparadores específicos se está aplicando el patrón de diseño **Strategy**. Este patrón encapsula algoritmos de ordenamiento intercambiables delegando el comportamiento en abstracciones polimórficas (las implementaciones de `Comparator`). Es preferible a una estructura procedural (`switch` o concatenación de `if-else`) porque desacopla el núcleo del servicio de la lógica condicional, garantizando el cumplimiento del principio Abierto/Cerrado (OCP). Agregar un nuevo criterio en el futuro requerirá añadir una nueva entrada al mapa en lugar de intervenir y modificar bloques lógicos estructurados existentes.
