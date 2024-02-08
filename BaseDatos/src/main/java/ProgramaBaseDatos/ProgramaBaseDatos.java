/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProgramaBaseDatos;

/**
 *
 * @author Kain
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ProgramaBaseDatos extends JFrame {

    private JPanel panel;
    private JTable tabla;
    private JButton btnModificar;
    private JButton btnBorrar;
    private JButton btnExtraer;
    private JComboBox<String> comboTablas;

    private String usuario;
    private String contraseña;
    ImageIcon Logo = new ImageIcon("C:\\Users\\noech\\Pictures\\—Pngtree—business logo design free logo_915991.png");

    public ProgramaBaseDatos() {
        initComponents();
        pedirCredenciales();
        setIconImage(Logo.getImage()); 
    }

    private void initComponents() {
        setTitle("Gestor de Base de Datos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel = new JPanel(new BorderLayout());
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
                modificarRegistro();
            }
        });

        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarRegistro();
            }
        });

        btnExtraer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                extraerRegistro();
            }
        });

        // Combo box para seleccionar la tabla a mostrar
        comboTablas = new JComboBox<>();
        comboTablas.addItem("empleados");
        comboTablas.addItem("otra_tabla");
        comboTablas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDatos((String) comboTablas.getSelectedItem());
            }
        });

        // Agregar botones y combo box al panel
        JPanel panelBotones = new JPanel();
        panelBotones.add(comboTablas);
        panelBotones.add(btnModificar);
        panelBotones.add(btnBorrar);
        panelBotones.add(btnExtraer);
        panel.add(panelBotones, BorderLayout.SOUTH);
    }
private void pedirCredenciales() {
    boolean credencialesCorrectas = false;

    while (!credencialesCorrectas) {
        JTextField usuarioField = new JTextField();
        JPasswordField contraseñaField = new JPasswordField();
        Object[] message = {
                "Usuario:", usuarioField,
                "Contraseña:", contraseñaField
        };

        // Obtener el Logo como un ImageIcon
        ImageIcon iconoEscalado = new ImageIcon(Logo.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));

        // Crear el cuadro de diálogo con el Logo y el mensaje
        JOptionPane optionPane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION, iconoEscalado);
        JDialog dialog = optionPane.createDialog("Login");

        // Establecer el tamaño deseado para el diálogo
        dialog.setSize(500, 400);

        // Establecer el icono del diálogo
        dialog.setIconImage(Logo.getImage());

        // Mostrar el diálogo
        dialog.setVisible(true);

        // Obtener la opción seleccionada
        Object selectedValue = optionPane.getValue();

        if (selectedValue.equals(JOptionPane.OK_OPTION)) {
            usuario = usuarioField.getText();
            contraseña = new String(contraseñaField.getPassword());

            // Verificar las credenciales
            if (verificarCredenciales(usuario, contraseña)) {
                credencialesCorrectas = true;
                mostrarDatos((String) comboTablas.getSelectedItem());
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas. Por favor, inténtelo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.exit(0);
        }
    }
}

    private boolean verificarCredenciales(String usuario, String contraseña) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/riot_games", usuario, contraseña);
            return true; // Si la conexión se establece correctamente, las credenciales son válidas
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Si hay un error en la conexión, las credenciales son incorrectas
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }




private void mostrarDatos(String tablaSeleccionada) {
    // Conexión a la base de datos
    Connection conn = null;
    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/riot_games", usuario, contraseña);

        // Consulta SQL para obtener todos los registros de la tabla seleccionada
        String sql = "SELECT * FROM " + tablaSeleccionada;
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
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al conectar a la base de datos. Por favor, verifique sus credenciales.", "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


private void modificarRegistro() {
    int filaSeleccionada = tabla.getSelectedRow();
    if (filaSeleccionada != -1) {
        String tablaSeleccionada = (String) comboTablas.getSelectedItem();
        int id = (int) tabla.getValueAt(filaSeleccionada, 0); // Obtener el ID del registro seleccionado

        // Obtener los nuevos datos del usuario (puedes usar JOptionPane o crear un nuevo formulario)
        Map<String, String> nuevosDatos = obtenerNuevosDatos();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/riot_games", usuario, contraseña);

            // Construir la consulta SQL para actualizar el registro
            StringBuilder sqlBuilder = new StringBuilder("UPDATE ");
            sqlBuilder.append(tablaSeleccionada).append(" SET ");
            for (Map.Entry<String, String> entry : nuevosDatos.entrySet()) {
                sqlBuilder.append(entry.getKey()).append("=?, ");
            }
            sqlBuilder.delete(sqlBuilder.length() - 2, sqlBuilder.length()); // Eliminar la última coma y el espacio
            sqlBuilder.append(" WHERE ID=?");

            PreparedStatement statement = conn.prepareStatement(sqlBuilder.toString());
            int index = 1;
            for (Map.Entry<String, String> entry : nuevosDatos.entrySet()) {
                statement.setString(index++, entry.getValue());
            }
            statement.setInt(index, id);

            int filasActualizadas = statement.executeUpdate();

            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(this, "Registro actualizado correctamente");
                mostrarDatos(tablaSeleccionada); // Actualizar la tabla
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar el registro", "Error", JOptionPane.ERROR_MESSAGE);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione una fila para modificar", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void borrarRegistro() {
    int filaSeleccionada = tabla.getSelectedRow();
    if (filaSeleccionada != -1) {
        String tablaSeleccionada = (String) comboTablas.getSelectedItem();
        int id = (int) tabla.getValueAt(filaSeleccionada, 0); // Obtener el ID del registro seleccionado

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea borrar este registro?", "Confirmar borrado", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/riot_games", usuario, contraseña);

                // Construir la consulta SQL para eliminar el registro
                String sql = "DELETE FROM " + tablaSeleccionada + " WHERE ID=?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, id);
                int filasEliminadas = statement.executeUpdate();

                if (filasEliminadas > 0) {
                    JOptionPane.showMessageDialog(this, "Registro eliminado correctamente");
                    mostrarDatos(tablaSeleccionada); // Actualizar la tabla
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el registro", "Error", JOptionPane.ERROR_MESSAGE);
                }

                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione una fila para borrar", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void extraerRegistro() {
    int filaSeleccionada = tabla.getSelectedRow();
    if (filaSeleccionada != -1) {
        // Obtener los datos del registro seleccionado
        String tablaSeleccionada = (String) comboTablas.getSelectedItem();
        int id = (int) tabla.getValueAt(filaSeleccionada, 0);
        Map<String, Object> datosRegistro = obtenerDatosRegistro(tablaSeleccionada, id);

        // Mostrar los datos del registro en un cuadro de diálogo
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("ID: ").append(id).append("\n");
        for (Map.Entry<String, Object> entry : datosRegistro.entrySet()) {
            mensaje.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        JOptionPane.showMessageDialog(this, mensaje.toString(), "Registro seleccionado", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione una fila para extraer", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

// Método para obtener los nuevos datos del usuario
private Map<String, String> obtenerNuevosDatos() {
    // Aquí puedes implementar la lógica para obtener los nuevos datos del usuario, por ejemplo, usando JOptionPane o un nuevo formulario
    // Retorna un mapa donde la clave es el nombre de la columna y el valor es el nuevo valor
    // Por ejemplo:
    Map<String, String> nuevosDatos = new HashMap<>();
    nuevosDatos.put("Nombre_Apellidos", JOptionPane.showInputDialog(this, "Ingrese el nuevo nombre:"));
    nuevosDatos.put("Fecha_Empleo", JOptionPane.showInputDialog(this, "Ingrese la nueva fecha:"));
    nuevosDatos.put("Posición", JOptionPane.showInputDialog(this, "Ingrese la nueva posición:"));
    nuevosDatos.put("Localidad", JOptionPane.showInputDialog(this, "Ingrese la nueva localización:"));
    return nuevosDatos;
}

// Método para obtener los datos de un registro específico
private Map<String, Object> obtenerDatosRegistro(String tabla, int id) {
    Map<String, Object> datosRegistro = new HashMap<>();
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/riot_games", usuario, contraseña);
        String sql = "SELECT * FROM " + tabla + " WHERE ID=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        if (resultSet.next()) {
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                Object value = resultSet.getObject(i);
                datosRegistro.put(columnName, value);
            }
        }
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return datosRegistro;
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ProgramaBaseDatos().setVisible(true);
        });
    }
}
