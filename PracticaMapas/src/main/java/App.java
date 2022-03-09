import comparators.LibroNombreComparator;
import controllers.LibroController;
import exceptions.LibroException;
import models.Libro;
import utils.Console;

import java.util.List;

public class App {
    private static final LibroController controller= LibroController.getInstance();

    public static void main(String[] args){
        System.out.println("Gestión de la biblioteca");
        System.out.println("------------------------");
        // bucle infinito a la espera de inputs
        do{
            System.out.println();
            System.out.println("1. Introducir nuevo libro");
            System.out.println("2. Eliminar libro");
            System.out.println("3. Actualizar libro");
            System.out.println("4. Mostrar catálogo");
            System.out.println("5. Salir");
            System.out.println();
            String opcion = Console.getString("Elige una opción [1-5]: ");

            // Expresión regular para validar la opción
            var regex = "[1-5]";
            if(opcion.matches(regex)){
                switch(opcion){
                    case "1":
                        crearLibro();
                        break;
                    case "2":
                        eliminarLibro();
                        break;
                    case "3":
                        actualizarLibro();
                        break;
                    case "4":
                        mostrarLibros();
                        break;
                    case "5":
                        salir();
                        break;
                }
            }
        }while(true);
    }

    private static void salir(){
        System.out.println(" Cerrando programa... ");
        System.exit(0);
    }

    private static void mostrarLibros(){
        System.out.println("Mostrar libros");
        List<Libro> libros = controller.getAllLibros();
        libros.sort(new LibroNombreComparator());
        System.out.println("Hay "+libros.size()+ " libros en la biblioteca");
        for(Libro libro : libros){
            System.out.println(libro);
        }
    }

    private static void actualizarLibro(){
        System.out.println("Actualizar libro: ");
        String nombre = Console.getString("Nombre del libro: ");
        System.out.println("Introduce los nuevos datos o deje en blanco para mantener los actuales");

        try{
            var existe = controller.getLibroByNombre(nombre);

            String nuevoNombre = Console.getString("Nuevo título (anterior: "+existe.getNombre()+"): ");
            nuevoNombre = (nuevoNombre.isEmpty()) ? existe.getNombre() : nuevoNombre;

            var update = existe.clone().nombre(nuevoNombre);//.precio(nuevoPrecio);

            var res = controller.updateLibro(existe.getNombre(), update);
            System.out.println("Libro actualizado");
            System.out.println(res);
        }catch(Exception e){
            System.out.println("Error al actualizar el libro: "+e.getMessage());
        }
    }

    private static void eliminarLibro(){
        System.out.println("Eliminar libro");
        String nombre = Console.getString("Nombre dle libro");
        try{
            var res = controller.deleteLibro(nombre);
            System.out.println("Libro eliminado correctamente");
            System.out.println(res);
        }catch(Exception e){
            System.out.println("Error al eliminar el libro: "+e.getMessage());
        }
    }

    private static void crearLibro(){
        System.out.println("Insertar libro");
        System.out.println("Los datos no pueden estar vacíos");
        String nombre = Console.getString("Título del libro: ");
        String isbn = Console.getString("Isbn: ");
        String idioma = Console.getString("Idioma: ");
        float precio = Console.getFloat("Introduzca el precio del libro: ");

        Libro libro = new Libro()
                .nombre(nombre)
                .isbn(isbn)
                .idioma(idioma)
                .precio(precio);
        try{
            var res = controller.crearLibro(libro);
            System.out.println("Libro creado correctamente: ");
            System.out.println(res);
        }catch (Exception e) {
            System.out.println("Error al crear: "+e.getMessage());
        }
    }
}
