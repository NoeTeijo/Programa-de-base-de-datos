package com.mycompany.gestionsellos;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Esta es la clase principal del programa para gestionar sellos.
 * Permite agregar, mostrar y eliminar sellos, así como salir del programa.
 * Utiliza un menú interactivo.
 *
 * @author NTM
 * @version 2.0
 */
public class GestionSellos {
   
    // Establece un array de la clase Sello llamada sellos

    /**
     * Lista que almacena los sellos.
     */

    ArrayList<Sello> sellos;

    /**
     * Crea un nuevo array de sellos.
     */
    public GestionSellos() {
        sellos = new ArrayList<>();
    }

    /**
     * Agrega un nuevo sello con país, motivo y año de emisión proporcionados.
     *
     * @param pais       El país del sello.
     * @param motivo     El motivo del sello.
     * @param anoEmision El año de emisión del sello.
     */
    public void agregarSello(String pais, String motivo, int anoEmision) {
        Sello nuevoSello = new Sello(pais, motivo, anoEmision);
        sellos.add(nuevoSello);
        if (anoEmision < 0) {
        System.out.println("Error: El año de emisión no puede ser negativo.");
           return;
        } else if (anoEmision > 2024){
            System.out.println("Error: El año de emisión no puede ser mayor del actual.");
           return;
        }
        if (esSoloNumero(motivo)) {
            System.out.println("Error: El motivo no pueden contener solo números.");
            return;
        }
         if (contieneNumeros(pais)) {
        System.out.println("Error: El país no puede contener números.");
        return;
    }
        System.out.println("Sello agregado: " + nuevoSello);
    }
        //Valora si en la variable solo se usan numeros
        private boolean esSoloNumero(String str) {
        return str.matches("\\d+");
    }
        //Valora si la variable contiene numeros en cualquier lugar.
        private boolean contieneNumeros(String str) {
        return str.matches(".*\\d+.*");
        }
    /**
     * Muestra la lista de sellos almacenados.
     */
    public void mostrarSellos() {
        if (sellos.isEmpty()) {
            System.out.println("La lista de sellos está vacía.");
        } else {
            System.out.println("Lista de sellos:");
            for (Sello sello : sellos) {
                System.out.println(sello);
            }
        }
    }

    /**
     * Elimina un sello basado en el país proporcionado.
     *
     * @param pais El país del sello a eliminar.
     */
    public void eliminarSello(String pais) {
        boolean encontrado = false;
        for (Sello sello : sellos) {
            if (sello.getPais().equalsIgnoreCase(pais)) {
                sellos.remove(sello);
                System.out.println("Sello eliminado: " + sello);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Sello no encontrado.");
        }
    }

    /**
     * Este método inicia el programa y crea un menú interactivo.
     *
     * @param args Argumentos de la línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) {
        // Inicia un Scanner para el menú

        GestionSellos gestionSellos = new GestionSellos();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Agregar sello");
            System.out.println("2. Mostrar sellos");
            System.out.println("3. Eliminar sello");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt(); 

            // Lee la siguiente variable introducida
            scanner.nextLine(); 
            // Consumir el salto de línea después del número 
            
            // Switch con las funciones de las 5 opciones anteriores
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el país del sello: ");
                    String pais = scanner.nextLine();
                    System.out.print("Ingrese el motivo del sello: ");
                    String motivo = scanner.nextLine();
                    System.out.print("Ingrese el año de emisión del sello: ");
                    int ano = scanner.nextInt();
                    gestionSellos.agregarSello(pais, motivo, ano); // Llama al método agregarSello
                    break;
                case 2:
                    gestionSellos.mostrarSellos(); // Llama al método mostrarSellos
                    break;
                case 3:
                    System.out.print("Ingrese el país del sello a eliminar: ");
                    String paisEliminar = scanner.nextLine();
                    gestionSellos.eliminarSello(paisEliminar); // Llama al método eliminarSello
                    break;
                case 4:
                    System.out.println("Saliendo del programa.");
                    System.exit(0); // Acaba el funcionamiento del programa
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}
