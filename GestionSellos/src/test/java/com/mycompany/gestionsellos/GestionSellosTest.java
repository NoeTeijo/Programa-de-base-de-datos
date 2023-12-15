/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.gestionsellos;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.Duration;
import java.time.Year;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Kain
 */
public class GestionSellosTest {
    
    /**
     *
     */
    public GestionSellosTest() {
    }
    
    /**
     *
     */
    @BeforeAll
    public static void setUpClass() {
    }
    
    /**
     *
     */
    @AfterAll
    public static void tearDownClass() {
    }
    
    /**
     *
     */
    @BeforeEach
    public void setUp() {
    }
    
    /**
     *
     */
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of agregarSello method, of class GestionSellos.
     */
    @Test
    public void testAgregarSello() {
        GestionSellos gestionSellos = new GestionSellos();
        
        // Verificar que la lista esté vacía al principio
        assertTrue(gestionSellos.sellos.isEmpty(), "La lista de sellos debe estar vacía al principio.");

        // Agregar un sello
        gestionSellos.agregarSello("Zimbagwe", "Test1", 2025);

        // Verificar que la lista ahora contenga un sello
        assertFalse(gestionSellos.sellos.isEmpty(), "La lista de sellos no debe estar vacía después de agregar un sello.");
        // Verificar que el tamaño de la lista sea 1
        assertEquals(1, gestionSellos.sellos.size(), "El tamaño de la lista debería ser 1 después de agregar un sello.");

        // Agregar otro sello
        gestionSellos.agregarSello("Pais", "Motivo2", 2023);

        // Verificar que el tamaño de la lista ahora sea 2
        assertEquals(2, gestionSellos.sellos.size(), "El tamaño de la lista debería ser 2 después de agregar otro sello.");
    }

    /**
     * Test of mostrarSellos method, of class GestionSellos.
     */
    @Test
    public void testMostrarSellos() {
        // Guardar la salida estándar actual
        PrintStream originalOut = System.out;
        
        // Crear una instancia de GestionSellos
        GestionSellos gestionSellos = new GestionSellos();
        try {
            // Redirigir la salida estándar a un ByteArrayOutputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            // Llamar al método mostrarSellos  
            gestionSellos.mostrarSellos();

            
            // Verificar que la salida sea la esperada
            assertEquals("La lista de sellos está vacía." + System.lineSeparator(), outputStream.toString());
        } finally {
            // Restaurar la salida estándar original al finalizar la prueba
            System.setOut(originalOut);
        }
    }
    /**
     * Test of eliminarSello method, of class GestionSellos.
     */

    @Test
    public void testEliminarSello() {
        // Crear una instancia de GestionSellos
        GestionSellos gestionSellos = new GestionSellos();

        // Agregar sellos
        gestionSellos.agregarSello("Pais1", "Motivo1", 2022);
        gestionSellos.agregarSello("Pais2", "Motivo2", 2023);

        // Verificar que la lista contenga los dos sellos agregados
        assertEquals(2, gestionSellos.sellos.size(), "La lista debería contener dos sellos.");

        // Intentar eliminar un sello que existe
        gestionSellos.eliminarSello("Pais1");

        // Verificar que la lista ahora contenga solo un sello
        assertEquals(1, gestionSellos.sellos.size(), "La lista debería contener un sello después de eliminar uno.");

        // Intentar eliminar un sello que no existe
        gestionSellos.eliminarSello("PaisInexistente");

        // Verificar que la lista siga conteniendo solo un sello
        assertEquals(1, gestionSellos.sellos.size(), "La lista no debería cambiar si se intenta eliminar un sello inexistente.");
    }
    @Test
     public void testAgregarSelloRendimiento() {
        GestionSellos gestionSellos = new GestionSellos();

        // Establece un número grande de sellos a agregar
        int cantidadSellos = 10000;

        // Mide el tiempo que tarda en agregar un gran número de sellos
        assertTimeout(Duration.ofSeconds(5), () -> {
            for (int i = 0; i < cantidadSellos; i++) {
                gestionSellos.agregarSello("País" + i, "Motivo" + i, 2023);
            }
        });
    }
}

    
