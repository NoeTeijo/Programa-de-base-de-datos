package com.mycompany.gestionsellos;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Kain
 */
    /**
     * Esta es la clase interna que representa un Sello con país, motivo y año de emisión.
     */
    public class Sello {

        private final String pais;
        private final String motivo;
        private final int anoEmision;

        /**
         * Constructor para la clase Sello.
         *
         * @param pais         El país del sello.
         * @param motivo       El motivo del sello.
         * @param anoEmision   El año de emisión del sello.
         */
        public Sello(String pais, String motivo, int anoEmision) {
            this.pais = pais;
            this.motivo = motivo;
            this.anoEmision = anoEmision;
        }
    
        // Getters y Setters para los parámetros anteriores

        /**
         * Obtiene el país del sello.
         *
         * @return El país del sello.
         */
        public String getPais() {
            return pais;
        }

        /**
         * Obtiene el motivo del sello.
         *
         * @return El motivo del sello.
         */
        public String getMotivo() {
            return motivo;
        }

        /**
         * Obtiene el año de emisión del sello.
         *
         * @return El año de emisión del sello.
         */
        public int getAnoEmision() {
            return anoEmision;
        }

        /**
         * Convierte el sello en una cadena de texto.
         *
         * @return Representación en texto del sello.
         */
        @Override
        public String toString() {
            return "País: " + pais + ", Motivo: " + motivo + ", Año de emisión: " + anoEmision;
        }
    }




