package ar.edu.unlar.prog3.tp_comparable_comparator.service;

import ar.edu.unlar.prog3.tp_comparable_comparator.model.Estudiante;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EstudianteService {

    private final List<Estudiante> estudiantesDb = new ArrayList<>();
    private final Map<String, Comparator<Estudiante>> estrategias = new HashMap<>();

    @PostConstruct
    public void init() {
        estudiantesDb.add(new Estudiante("LU-2024-001", "Martín Quiroga", 8.5, 22, 18));
        estudiantesDb.add(new Estudiante("LU-2024-002", "Valeria Díaz", 8.5, 20, 15));
        estudiantesDb.add(new Estudiante("LU-2024-003", "Facundo Castro", 7.2, 24, 22));
        estudiantesDb.add(new Estudiante("LU-2024-004", "Camila Torres", 9.1, 21, 24));
        estudiantesDb.add(new Estudiante("LU-2024-005", "Lucas González", 9.1, 23, 24));
        estudiantesDb.add(new Estudiante("LU-2024-006", "Agustina López", 6.8, 19, 10));
        estudiantesDb.add(new Estudiante("LU-2024-007", "Nahuel Herrera", 7.5, 22, 14));
        estudiantesDb.add(new Estudiante("LU-2024-008", "Florencia Ríos", 8.9, 25, 20));
        estudiantesDb.add(new Estudiante("LU-2024-009", "Tomás Sosa", 6.5, 20, 12));
        estudiantesDb.add(new Estudiante("LU-2024-010", "Lucía Fernández", 7.8, 21, 16));

        estrategias.put("promedio", Comparator.comparing(Estudiante::getPromedio).thenComparing(Estudiante::getLegajo));
        estrategias.put("edad", Comparator.comparing(Estudiante::getEdad).thenComparing(Estudiante::getLegajo));
        estrategias.put("nombre", Comparator.comparing(Estudiante::getNombre).thenComparing(Estudiante::getLegajo));
        estrategias.put("materiasAprobadas", Comparator.comparing(Estudiante::getCantidadMateriasAprobadas).thenComparing(Estudiante::getLegajo));
        estrategias.put("legajo", Comparator.comparing(Estudiante::getLegajo));
    }

    public List<Estudiante> ordenar(List<Estudiante> lista, String sortBy, String order) {
        Comparator<Estudiante> comp = estrategias.get(sortBy);
        if (comp == null) {
            throw new IllegalArgumentException(sortBy);
        }
        if ("desc".equalsIgnoreCase(order)) {
            comp = comp.reversed();
        }
        List<Estudiante> resultado = new ArrayList<>(lista);
        resultado.sort(comp);
        return resultado;
    }

    public List<Estudiante> obtenerTodos() {
        return new ArrayList<>(estudiantesDb);
    }

    public List<String> getCriteriosAceptados() {
        return new ArrayList<>(estrategias.keySet());
    }
}