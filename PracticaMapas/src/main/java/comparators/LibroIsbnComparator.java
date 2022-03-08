package comparators;

import models.Libro;

import java.util.Comparator;

public class LibroIsbnComparator implements Comparator<Libro> {

    @Override
    public int compare(Libro l1, Libro l2){
        return l1.getIsbn().compareTo(l2.getIsbn());
    }

    @Override
    public Comparator<Libro> reversed(){
        return Comparator.super.reversed();
    }

}
