package controllers;

import repositories.LibroRepository;
import models.Libro;
import exceptions.LibroException;

import java.util.List;

public class LibroController {
    private static LibroController instance;
    private final LibroRepository libroRepository;

    private LibroController(LibroRepository libroRepository){
        this.libroRepository = libroRepository;
        initRepositoryData();
    }

    /**
     * Singleton
     * @return instancia de LibroController
     */
    public static LibroController getInstance(){
        if(instance == null){
            instance = new LibroController(new LibroRepository());
        }
        return instance;
    }

    /**
     * Inicialización con datos de prueba
     */

    private void initRepositoryData(){
        this.libroRepository.insert(new Libro("Cervantes", "00001", "Español", 19.99f));
        this.libroRepository.insert(new Libro("Libro1", "00002", "Español", 12.99f));
        this.libroRepository.insert(new Libro("Libro2", "00003", "Español", 5.99f));
        this.libroRepository.insert(new Libro("Libro3", "00004", "Español", 9.99f));

    }

    public Libro crearLibro(Libro libro) throws LibroException{
        checkLibroData(libro);
        // comprobación de existencia
        var existe = libroRepository.findByNombre(libro.getNombre());
        if(existe == null){
            libroRepository.insert(libro);
            return libro;
        }
        throw new LibroException("Ya existe el libro con isbn: "+libro.getIsbn());
    }

    /**
     * Comprueba los datos del libro
     *
     * @param libro Libro a comprobar
     * @throws LibroException si los datos son erróneos
     */
    private void checkLibroData(Libro libro) throws LibroException{
        if(libro.getNombre() == null || libro.getNombre().trim().isEmpty()){
            throw new LibroException("El nombre no puede estar vacío");
        }
        if(libro.getIsbn() == null || libro.getIsbn().trim().isEmpty()){
            throw new LibroException("El isbn no puede estar en blanco");
        }
        if(libro.getIdioma() == null || libro.getIdioma().trim().isEmpty()){
            throw new LibroException("El idioma no puede estar vacío");
        }
        if(libro.getPrecio() == 0.0f || libro.getPrecio() < 0.0f){
            throw new LibroException("El precio no puede ser o o inferior a este valor");
        }
    }

    /**
     * Busca un libro con el nombre introducido por parámetro
     * @param nombre del libro
     * @return libro
     * @throws LibroException si no existe el libro introducido
     */
    public Libro getLibroByNombre(String nombre) throws LibroException{
        var libro = libroRepository.findByNombre(nombre);
        if(libro != null){
            return libro;
        }
        throw new LibroException("No existe el libro con nombre: " +nombre);
    }

    /**
     * Busca un libro con un isbn determinado
     * @param id
     * @return libro
     * @throws LibroException si no existe el libro con ese isbn
     */
    public Libro getLibroById(int id) throws LibroException{
        var libro = libroRepository.findById(id);
        if(libro != null){
            return libro;
        }
        throw new LibroException("No se haya el isbn introducido");
    }

    /**
     * Devuelve todos los libros
     * @return libros
     */
    public List<Libro> getAllLibros(){
        return libroRepository.findAll();
    }

    public Libro updateLibro(String nombre, Libro libro) throws LibroException{
        // comprobación de datos
        checkLibroData(libro);
        //Comprueba si no está repetido
        var repe = libroRepository.findByNombre(libro.getNombre());
        if(repe == null || repe.getIsbn() == libro.getIsbn()){
            libroRepository.update(libro);
            return libro;
        }else{
            throw new LibroException("Ya existe un libro con el isbn: "+libro.getIsbn());
        }
    }


    public Libro deleteLibro(String nombre) throws LibroException{
        var libro = libroRepository.findByNombre(nombre);
        if(libro != null){
            libroRepository.delete(libro);
            return libro;
        }else{
            throw new LibroException("No existe un libro con el nombre: "+nombre);
        }
    }
}
