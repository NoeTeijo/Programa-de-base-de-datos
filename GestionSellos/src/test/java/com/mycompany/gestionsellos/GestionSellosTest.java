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
    
    public GestionSellosTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of agregarSello method, of class GestionSellos.
     */
    @Test
    public void testAgregarSello() {
        System.out.println("agregarSello");
        String pais = "";
        String motivo = "";
        int anoEmision = 0;
        GestionSellos instance = new GestionSellos();
        instance.agregarSello(pais, motivo, anoEmision);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
