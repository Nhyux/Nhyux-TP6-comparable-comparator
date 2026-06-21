package ar.edu.unlar.prog3.tp_comparable_comparator;

import ar.edu.unlar.prog3.tp_comparable_comparator.model.Estudiante;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PruebaOrdenamiento {
    public static void main(String[] args) {
        List<Estudiante> estudiantes = new ArrayList<>();
        estudiantes.add(new Estudiante("LU-2024-001", "Martín Quiroga", 8.5, 22, 18));
        estudiantes.add(new Estudiante("LU-2024-002", "Valeria Díaz", 8.5, 20, 15));

        // ❌ Descomentá la línea de abajo para ver el error de compilación:
        // Collections.sort(estudiantes);
    }
}