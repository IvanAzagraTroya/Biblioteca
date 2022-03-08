package models;


import java.util.Objects;

/**
 * Modelo. Clase que representa los libros de una biblioteca
 * Clase POJO
 * */
public class Libro {
    private static int numLibro = 0;
    private final int id;
    private String nombre, isbn, idioma;
    private float precio;

    public Libro(){
        this.id = ++numLibro;
    }

    public Libro(String nombre, String isbn, String idioma, float precio){
        this.id = ++numLibro;
        this.nombre = nombre;
        this.isbn = isbn;
        this.idioma = idioma;
        this.precio = precio;
    }

    // Para la clonación
    private Libro(int id, String nombre, String isbn, String idioma, float precio){
        this.id = id;
        this.nombre = nombre;
        this.isbn = isbn;
        this.idioma = idioma;
        this.precio = precio;
    }

    // Getters
    public int getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public String getIsbn(){
        return isbn;
    }

    public String getIdioma(){
        return idioma;
    }

    public Float getPrecio(){
        return precio;
    }

    // Setters

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setIsbn(String isbn){
        this.isbn = isbn;
    }

    public void setIdioma(String idioma){
        this.idioma = idioma;
    }

    public void setPrecio(float precio){
        this.precio = precio;
    }

    // Metodos de Libro

    public Libro nombre(String nombre){
        this.nombre = nombre;
        return this;
    }

    public Libro isbn(String isbn){
        this.isbn = isbn;
        return this;
    }

    public Libro idioma(String idioma){
        this.idioma = idioma;
        return this;
    }

    public Libro precio(float precio){
        this.precio = precio;
        return this;
    }

    @Override
    public String toString(){
        return "Libro{ "+
                "id: "+id+
                "nombre: "+nombre+
                "isbn: "+isbn+
                "idioma: "+idioma+
                "precio: "+precio+
                "}";
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Libro)) return false;
        Libro libro = (Libro) o;
        return id == libro.id && nombre.equals(libro.nombre) && isbn.equals(libro.isbn) && idioma.equals(libro.idioma);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, nombre, isbn, idioma, precio);
    }

    @Override
    public Libro clone(){
        return new Libro(this.id, this.nombre, this.isbn, this.idioma, this.precio);
    }
}
