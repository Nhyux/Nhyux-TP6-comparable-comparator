package ar.edu.unlar.prog3.tp_comparable_comparator.comparator;

import ar.edu.unlar.prog3.tp_comparable_comparator.model.Estudiante;
import java.util.Comparator;

public class EstudiantePorMateriasAprobadasComparator implements Comparator<Estudiante> {
    @Override
    public int compare(Estudiante e1, Estudiante e2) {
        return Integer.compare(e1.getCantidadMateriasAprobadas(), e2.getCantidadMateriasAprobadas());
    }
}