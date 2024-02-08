/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.basedatos;

/**
 *
 * @author Kain
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BaseDatos extends JFrame {

    private JPanel panel;

    public BaseDatos() {
        initComponents();
        mostrarDatos();
    }

    private void initComponents() {
        setTitle("Mostrar Tabla");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        add(panel);

        // Agrega JLabels para mostrar los datos
        JLabel labelId = new JLabel("id:");
        JLabel labelTitulo = new JLabel("titulo:");
        JLabel labelAutor = new JLabel("autor:");
        JLabel labelTipo = new JLabel("tipo:");
        JLabel labelFecha = new JLabel("fecha_publicacion:");

        panel.add(labelId);
        panel.add(labelTitulo);
        panel.add(labelAutor);
        panel.add(labelTipo);
        panel.add(labelFecha);
    }

    private void mostrarDatos() {
        // Conexión a la base de datos
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/riot_games", "root", "");

            // Consulta SQL para obtener todos los registros
            String sql = "SELECT * FROM empleados";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            // Muestra los datos en JLabels
            while (resultSet.next()) {
                JLabel labelId = new JLabel("ID: " + resultSet.getInt("ID"));
                JLabel labelTitulo = new JLabel("DNI: " + resultSet.getString("DNI"));
                JLabel labelAutor = new JLabel("Nombre Completo: " + resultSet.getString("Nombre_Apellidos"));
                JLabel labelTipo = new JLabel("Fecha: " + resultSet.getString("Fecha_Empleo"));
                JLabel labelFecha = new JLabel("Posición: " + resultSet.getString("Posición"));
                panel.add(labelId);
                panel.add(labelTitulo);
                panel.add(labelAutor);
                panel.add(labelTipo);
                panel.add(labelFecha);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new BaseDatos().setVisible(true);
        });
    }
}