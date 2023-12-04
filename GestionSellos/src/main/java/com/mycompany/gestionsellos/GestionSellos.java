/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestionsellos;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author NTM
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */



class Sello {

    private String pais;
    private String motivo;
    private int anoEmision;

    

    public Sello(String pais, String motivo, int anoEmision) {
    // Constructores
        this.pais = pais;
        this.motivo = motivo;
        this.anoEmision = anoEmision;
    }
    
    // Getters y Setters
    public String getPais() {
        return pais;
    }

    public String getMotivo() {
        return motivo;
    }

    public int getAnoEmision() {
        return anoEmision;
    }

    // Devuelve por pantalla las variables
    @Override
    public String toString() {
        return "País: " + pais + ", Motivo: " + motivo + ", Año de emisión: " + anoEmision;
    }
}

public class GestionSellos{
   
    // Establece un array de la clase Sello llamada sellos
    private ArrayList<Sello> sellos;

    public GestionSellos() {
        //Crea un nuevo array de sellos
        sellos = new ArrayList<>();
    }

    // Función que agrega un nuevo sello 
    public void agregarSello(String pais, String motivo, int anoEmision) {
        Sello nuevoSello = new Sello(pais, motivo, anoEmision);
        sellos.add(nuevoSello);
        System.out.println("Sello agregado: " + nuevoSello);
    }
    
    // Función que imprime por pantalla la lista de sellos o avisa si esta vacía
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
        
    // Función que elimina los sellos escogidos que estén en nuestra lista
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

    public static void main(String[] args) {
        // Inicia un Scanner para el menu.
        GestionSellos gestionSellos = new GestionSellos();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Agregar sello");
            System.out.println("2. Mostrar sellos");
            System.out.println("3. Eliminar sello");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt(); /*Lee la siguiente variable 
            introducida */
            scanner.nextLine(); 
            // Consumir el salto de línea después del número

            //Switch con las funciones de las 5 opciones anteriores
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el país del sello: ");
                    String pais = scanner.nextLine();
                    System.out.print("Ingrese el motivo del sello: ");
                    String motivo = scanner.nextLine();
                    System.out.print("Ingrese el año de emisión del sello: ");
                    int ano = scanner.nextInt();
                    gestionSellos.agregarSello(pais, motivo, ano); //Llama al metodo agregarSello
                    break;
                case 2:
                    gestionSellos.mostrarSellos(); //Llama al metodo mostrarSellos
                    break;
                case 3:
                    System.out.print("Ingrese el país del sello a eliminar: ");
                    String paisEliminar = scanner.nextLine();
                    gestionSellos.eliminarSello(paisEliminar); //Llama al metodo eliminarSello
                    break;
                case 4:
                    System.out.println("Saliendo del programa.");
                    System.exit(0); //Acaba el funcionamiento del programa
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}