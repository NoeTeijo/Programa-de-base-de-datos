/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.basedatos.Hola;

/**
 *
 * @author Kain
 */
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BaseDatos2 extends JFrame {

    private JPanel panel;
    private JTable tabla;

    public BaseDatos2() {
        initComponents();
        mostrarDatos();
    }

    private void initComponents() {
        setTitle("Mostrar Tabla");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        add(panel);

        // Crear el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Fecha");
        modelo.addColumn("Posición");
        modelo.addColumn("Localización");

        // Crear la tabla con el modelo
        tabla = new JTable(modelo);

        // Agregar la tabla a un JScrollPane para permitir el desplazamiento
        JScrollPane scrollPane = new JScrollPane(tabla);
        panel.add(scrollPane, BorderLayout.CENTER);
    }

    private void mostrarDatos() {
        // Conexión a la base de datos
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/riot_games", "root", "");

            // Consulta SQL para obtener todos los registros
            String sql = "SELECT * FROM empleados";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            // Agregar los datos a la tabla
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos

            while (resultSet.next()) {
                modelo.addRow(new Object[]{
                    resultSet.getInt("ID"),
                    resultSet.getString("Nombre_Apellidos"),
                    resultSet.getString("Fecha_Empleo"),
                    resultSet.getString("Posición"),
                        resultSet.getString("Localidad")
                });
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new BaseDatos2().setVisible(true);
        });
}
}