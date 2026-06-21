package ar.edu.unlar.prog3.tp_comparable_comparator.controller;

import ar.edu.unlar.prog3.tp_comparable_comparator.model.Estudiante;
import ar.edu.unlar.prog3.tp_comparable_comparator.service.EstudianteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping
    public ResponseEntity<List<Estudiante>> getEstudiantes(
            @RequestParam(defaultValue = "promedio") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {
        
        List<Estudiante> lista = estudianteService.obtenerTodos();
        List<Estudiante> listaOrdenada = estudianteService.ordenar(lista, sortBy, order);
        return ResponseEntity.ok(listaOrdenada);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidSortCriteria(IllegalArgumentException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Criterio de ordenamiento no válido");
        body.put("criterioRecibido", ex.getMessage());
        body.put("criteriosAceptados", estudianteService.getCriteriosAceptados());
        return ResponseEntity.badRequest().body(body);
    }
}