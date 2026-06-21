package ar.edu.unlar.prog3.tp_comparable_comparator;

import ar.edu.unlar.prog3.tp_comparable_comparator.model.Estudiante;
import ar.edu.unlar.prog3.tp_comparable_comparator.comparator.EstudiantePorMateriasAprobadasComparator;
import ar.edu.unlar.prog3.tp_comparable_comparator.comparator.EstudiantePorNombreComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PruebaOrdenamiento {
    public static void main(String[] args) {
        List<Estudiante> estudiantes = new ArrayList<>();
        estudiantes.add(new Estudiante("LU-2024-001", "Martín Quiroga", 8.5, 22, 18));
        estudiantes.add(new Estudiante("LU-2024-002", "Valeria Díaz", 8.5, 20, 15));
        estudiantes.add(new Estudiante("LU-2024-003", "Facundo Castro", 7.2, 24, 22));
        estudiantes.add(new Estudiante("LU-2024-004", "Camila Torres", 9.1, 21, 24));
        estudiantes.add(new Estudiante("LU-2024-005", "Lucas González", 9.1, 23, 24));
        estudiantes.add(new Estudiante("LU-2024-006", "Agustina López", 6.8, 19, 10));
        estudiantes.add(new Estudiante("LU-2024-007", "Nahuel Herrera", 7.5, 22, 14));
        estudiantes.add(new Estudiante("LU-2024-008", "Florencia Ríos", 8.9, 25, 20));
        estudiantes.add(new Estudiante("LU-2024-009", "Tomás Sosa", 6.5, 20, 12));
        estudiantes.add(new Estudiante("LU-2024-010", "Lucía Fernández", 7.8, 21, 16));

        System.out.println("--- EJERCICIO 4: COMPARATORS CLÁSICOS ---");
        estudiantes.sort(new EstudiantePorMateriasAprobadasComparator());
        System.out.println("\nPor Materias Aprobadas (Asc):");
        estudiantes.forEach(System.out::println);

        estudiantes.sort(new EstudiantePorNombreComparator());
        System.out.println("\nPor Nombre Alfabetico:");
        estudiantes.forEach(System.out::println);

        System.out.println("\n--- EJERCICIO 5: COMPARATORS MODERNOS---");
        Comparator<Estudiante> porEdadLambda = (e1, e2) -> Integer.compare(e1.getEdad(), e2.getEdad());
        estudiantes.sort(porEdadLambda);
        System.out.println("\nPor Edad (Lambda):");
        estudiantes.forEach(System.out::println);

        Comparator<Estudiante> porMateriasMethodRef = Comparator.comparing(Estudiante::getCantidadMateriasAprobadas);
        estudiantes.sort(porMateriasMethodRef);

        Comparator<Estudiante> compuesto = Comparator.comparing(Estudiante::getPromedio).reversed()
                .thenComparing(Estudiante::getNombre);
        estudiantes.sort(compuesto);
        System.out.println("\nCompuesto (Promedio Desc, Nombre Asc en empates):");
        estudiantes.forEach(System.out::println);

        Comparator<Estudiante> promedioAscendente = Comparator.comparing(Estudiante::getPromedio);
        estudiantes.sort(promedioAscendente);
        System.out.println("\nPromedio Inverso (Ascendente):");
        estudiantes.forEach(System.out::println);

        System.out.println("\n--- EJERCICIO 6: ANTI-PATRÓN DE LA RESTA ---");
        List<Estudiante> estudiantesOverflow = new ArrayList<>();
        estudiantesOverflow.add(new Estudiante("LU-X", "Estudiante Max", 10.0, Integer.MAX_VALUE, 20));
        estudiantesOverflow.add(new Estudiante("LU-Y", "Estudiante Negativo", 10.0, -1, 20));

        Comparator<Estudiante> restaTramposa = (e1, e2) -> e1.getEdad() - e2.getEdad();
        estudiantesOverflow.sort(restaTramposa);
        System.out.println("\nResultado con Resta Tramposa (Incorrecto por Overflow):");
        estudiantesOverflow.forEach(System.out::println);

        Comparator<Estudiante> correctorSuficiente = (e1, e2) -> Integer.compare(e1.getEdad(), e2.getEdad());
        estudiantesOverflow.sort(correctorSuficiente);
        System.out.println("\nResultado con Integer.compare() (Correcto):");
        estudiantesOverflow.forEach(System.out::println);
    }
}