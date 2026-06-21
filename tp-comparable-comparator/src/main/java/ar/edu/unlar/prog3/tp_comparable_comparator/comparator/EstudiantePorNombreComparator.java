package ar.edu.unlar.prog3.tp_comparable_comparator.comparator;

import ar.edu.unlar.prog3.tp_comparable_comparator.model.Estudiante;
import java.util.Comparator;

public class EstudiantePorNombreComparator implements Comparator<Estudiante> {
    @Override
    public int compare(Estudiante e1, Estudiante e2) {
        return e1.getNombre().compareTo(e2.getNombre());
    }
}