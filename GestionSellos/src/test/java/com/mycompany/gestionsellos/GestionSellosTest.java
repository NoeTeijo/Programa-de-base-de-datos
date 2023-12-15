/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.gestionsellos;

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
        gestionSellos.agregarSello("Pais1", "Motivo1", 2022);

        // Verificar que la lista ahora contenga un sello
        assertFalse(gestionSellos.sellos.isEmpty(), "La lista de sellos no debe estar vacía después de agregar un sello.");
        // Verificar que el tamaño de la lista sea 1
        assertEquals(1, gestionSellos.sellos.size(), "El tamaño de la lista debería ser 1 después de agregar un sello.");

        // Agregar otro sello
        gestionSellos.agregarSello("Pais2", "Motivo2", 2023);

        // Verificar que el tamaño de la lista ahora sea 2
        assertEquals(2, gestionSellos.sellos.size(), "El tamaño de la lista debería ser 2 después de agregar otro sello.");
    }

    /**
     * Test of mostrarSellos method, of class GestionSellos.
     */
    @Test
    public void testMostrarSellos() {
        System.out.println("mostrarSellos");
        GestionSellos instance = new GestionSellos();
        instance.mostrarSellos();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarSello method, of class GestionSellos.
     */
    @Test
    public void testEliminarSello() {
        System.out.println("eliminarSello");
        String pais = "";
        GestionSellos instance = new GestionSellos();
        instance.eliminarSello(pais);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class GestionSellos.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        GestionSellos.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
