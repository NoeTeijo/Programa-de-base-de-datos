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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BaseDatos3 extends JFrame {

    private JPanel panel;
    private JTable tabla;
    private JButton btnModificar;
    private JButton btnBorrar;
    private JButton btnExtraer;

    public BaseDatos3() {
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

        // Crear botones
        btnModificar = new JButton("Modificar");
        btnBorrar = new JButton("Borrar");
        btnExtraer = new JButton("Extraer");

        // Agregar acciones a los botones
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para modificar el registro seleccionado
                int filaSeleccionada = tabla.getSelectedRow();
                if (filaSeleccionada != -1) {
                    // Obtener el id de la fila seleccionada
                    int id = (int) tabla.getValueAt(filaSeleccionada, 0);
                    modificarRegistro();
                    // Lógica para modificar el registro con el id proporcionado
                    // Implementa esta lógica según tus necesidades
                    System.out.println("Modificar registro con id: " + id);
                }
            }
        });

        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para borrar el registro seleccionado
                int filaSeleccionada = tabla.getSelectedRow();
                if (filaSeleccionada != -1) {
                    // Obtener el id de la fila seleccionada
                    int id = (int) tabla.getValueAt(filaSeleccionada, 0);
                    borrarRegistro();
                    // Lógica para borrar el registro con el id proporcionado
                    // Implementa esta lógica según tus necesidades
                    System.out.println("Borrar registro con id: " + id);
                }
            }
        });

        btnExtraer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para extraer el registro seleccionado
                int filaSeleccionada = tabla.getSelectedRow();
                if (filaSeleccionada != -1) {
                    // Obtener el id de la fila seleccionada
                    int id = (int) tabla.getValueAt(filaSeleccionada, 0);
                    extraerRegistro();
                    // Lógica para extraer el registro con el id proporcionado
                    // Implementa esta lógica según tus necesidades
                    System.out.println("Extraer registro con id: " + id);
                }
            }
        });

        // Agregar botones al panel
        panel.add(btnModificar);
        panel.add(btnBorrar);
        panel.add(btnExtraer);
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

    
    private void modificarRegistro() {
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "");

        // Obtener el índice seleccionado en la tabla
        int selectedRow = tabla.getSelectedRow();

        // Verificar si hay una fila seleccionada
        if (selectedRow != -1) {
            // Obtener los datos de la fila seleccionada
            int id = (int) tabla.getValueAt(selectedRow, 0);
            String nuevoTitulo = "El Padrino";  // Reemplazar con el nuevo título
            String nuevoAutor = "Francis Ford Copola";    // Reemplazar con el nuevo autor

            // Consulta SQL para modificar el registro
            String sql = "UPDATE biblioteca SET titulo = ?, autor = ? WHERE id = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, nuevoTitulo);
                statement.setString(2, nuevoAutor);
                statement.setInt(3, id);

                // Ejecutar la actualización
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Registro modificado exitosamente");
                    // Actualizar la tabla después de modificar
                    mostrarDatos();
                } else {
                    System.out.println("No se encontró el registro para modificar");
                }
            }
        } else {
            System.out.println("Por favor, seleccione una fila para modificar");
        }

        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private void borrarRegistro() {
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "");

        // Obtener el índice seleccionado en la tabla
        int selectedRow = tabla.getSelectedRow();

        // Verificar si hay una fila seleccionada
        if (selectedRow != -1) {
            // Obtener el ID de la fila seleccionada
            int id = (int) tabla.getValueAt(selectedRow, 0);

            // Consulta SQL para borrar el registro
            String sql = "DELETE FROM biblioteca WHERE id = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, id);

                // Ejecutar la eliminación
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Registro eliminado exitosamente");
                    // Actualizar la tabla después de borrar
                    mostrarDatos();
                } else {
                    System.out.println("No se encontró el registro para borrar");
                }
            }
        } else {
            System.out.println("Por favor, seleccione una fila para borrar");
        }

        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private void extraerRegistro() {
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/riot_games", "root", "");

        // Obtener el índice seleccionado en la tabla
        int selectedRow = tabla.getSelectedRow();

        // Verificar si hay una fila seleccionada
        if (selectedRow != -1) {
            // Obtener el ID de la fila seleccionada
            int id = (int) tabla.getValueAt(selectedRow, 0);

            // Consulta SQL para extraer el registro
            String sql = "SELECT * FROM empleados WHERE id = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, id);

                // Ejecutar la consulta
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    // Imprimir los datos del registro extraído
                   
                    int idExtraido = resultSet.getInt("ID");
                    String tituloExtraido = resultSet.getString("Nombre_Apellidos");
                    String autorExtraido = resultSet.getString("Fecha_Empleo");
                    String tipoExtraido = resultSet.getString("Posición");
                    String fechaPublicacionExtraida = resultSet.getString("Localidad");
                    
                    System.out.println("Registro extraído:");
                    System.out.println("ID: " + idExtraido);
                    System.out.println("Título: " + tituloExtraido);
                    System.out.println("Autor: " + autorExtraido);
                    System.out.println("Tipo: " + tipoExtraido);
                    System.out.println("Fecha de publicación: " + fechaPublicacionExtraida);
                } else {
                    System.out.println("No se encontró el registro para extraer");
                }
            }
        } else {
            System.out.println("Por favor, seleccione una fila para extraer");
        }

        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new BaseDatos3().
setVisible(true);
        });
    }
}
