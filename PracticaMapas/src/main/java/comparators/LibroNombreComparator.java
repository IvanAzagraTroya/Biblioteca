package comparators;

import models.Libro;

import java.util.Comparator;

public class LibroNombreComparator implements Comparator<Libro> {

    @Override
    public int compare(Libro l1, Libro l2){
        return l1.getNombre().compareTo(l2.getNombre());
    }

}
