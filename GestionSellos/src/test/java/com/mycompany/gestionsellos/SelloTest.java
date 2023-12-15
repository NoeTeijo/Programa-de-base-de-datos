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
public class SelloTest {
    
    public SelloTest() {
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
     * Test of getPais method, of class Sello.
     */
    @Test
    public void testGetPais() {
        // Crea una instancia de Sello con valores específicos
        Sello instance = new Sello("2", "Motivo de prueba", 2023);

        // Verifica que el método getPais devuelve el valor correcto
        assertEquals("2", instance.getPais());
    }

    /**
     * Test of getMotivo method, of class Sello.
     */
    @Test
    void testGetMotivo() {
        // Crea una instancia de Sello con valores específicos
        Sello instance = new Sello("País de prueba", "2", 2023);

        // Verifica que el método getMotivo devuelve el valor correcto
        assertEquals("2", instance.getMotivo());
    }

    /**
     * Test of getAnoEmision method, of class Sello.
     */
    @Test
    void testGetAnoEmision() {
        // Crea una instancia de Sello con valores específicos
        Sello instance = new Sello("País de prueba", "Motivo de prueba", 2023);

        // Verifica que el método getAnoEmision devuelve el valor correcto
        assertEquals(2023, instance.getAnoEmision());
    }

    /**
     * Test of toString method, of class Sello.
     */
    @Test
    void testToString() {
        // Crea una instancia de Sello con valores específicos
        Sello instance = new Sello ("País de prueba", "Motivo de prueba", 2023);

        // Verifica que el método toString devuelve la representación correcta
        String expected = "País: País de prueba, Motivo: Motivo de prueba, Año de emisión: 2023";
        assertEquals(expected, instance.toString());
    }
}