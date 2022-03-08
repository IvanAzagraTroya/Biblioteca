package repositories;

import models.Libro;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class LibroRepository extends TreeMap<String, Libro> implements ICRUDRepository<Libro, Integer> {
    /**
     * Busca el Libro por su nombre
     * @param nombre
     * @return el libro encontrado o null
     */
    public Libro findByNombre(String nombre) {
        return this.get(nombre);
    }

    /**
     * Busca un libro por su id
     * @param id
     * @return el libro o null
     */
    @Override
    public Libro findById(Integer id){
        for(Libro libro : this.values()) {
            if(libro.getId() == id){
                return libro;
            }
        }
        return null;
    }

    /**
     * devuelve una lista con los nombres de los libros
     * @return libros
     */
    @Override
    public List<Libro> findAll() {
        return new ArrayList<>(this.values());
    }

    @Override
    public Libro update(Libro libro){
        var libroEncontrado = this.get(libro.getNombre());
        if(libroEncontrado != null){
            this.put(libro.getNombre(), libro);
        }
        return null;
    }

    /**
     * Borra un libro
     * @param libro
     * @return el libro eliminado o null
     */
    @Override
    public Libro delete(Libro libro){
        var libroEncontrado = this.get(libro.getNombre());
        if(libroEncontrado != null){
            this.remove(libro.getNombre());
        }
        return null;
    }

    /**
     * Borra un libro por su id
      * @param id
     * @return el libro eliminado o null
     */
    @Override
    public Libro deleteById(Integer id){
        var libro = this.findById(id);
        if(libro != null){
            this.remove(libro.getNombre());
        }
        return null;
    }

    @Override
    public Libro insert(Libro libro){
        this.put(libro.getNombre(), libro);
        return libro;
    }
}
