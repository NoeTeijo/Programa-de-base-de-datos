/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.programabasedatos;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableColumnModel;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Kain
 */

public class ProgramaBaseDatos extends JFrame {

    private JPanel panel;
    private JTable tabla;
    private JButton btnModificar;
    private JButton btnBorrar;
    private JButton btnExtraer;
    private JButton btnAñadir;
    private JComboBox<String> comboTablas;
    private DefaultTableModel modelo;


    private String usuario;
    private String contraseña;
    ImageIcon Logo = new ImageIcon("C:\\Users\\noech\\Pictures\\—Pngtree—business logo design free logo_915991.png");

public ProgramaBaseDatos() {
    // Inicializa la tabla antes de llamar a initComponents()
    pedirCredenciales();
    tabla = new JTable();
    initComponents();
    setIconImage(Logo.getImage()); 
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

    
private void initComponents() {
    setTitle("Gestor de Base de Datos");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    panel = new JPanel(new BorderLayout());
    add(panel);

    // Crear el modelo de la tabla utilizando el método crearModeloTabla
    modelo = crearModeloTabla("empleados", 5);

    // Asignar el modelo a la tabla
    tabla.setModel(modelo);
    mostrarDatos((String) "empleados");

    // Agregar la tabla a un JScrollPane para permitir el desplazamiento
    JScrollPane scrollPane = new JScrollPane(tabla);
    panel.add(scrollPane, BorderLayout.CENTER);

        // Crear botones
        btnModificar = new JButton("Modificar");
        btnBorrar = new JButton("Borrar");
        btnExtraer = new JButton("Extraer");
        btnAñadir = new JButton("Añadir");

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
        comboTablas.addItem("ex_empleados");
        comboTablas.addItem("league_of_legends");
        comboTablas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            String tablaSeleccionada = (String) comboTablas.getSelectedItem();
        
            // Obtener el tamaño máximo de columnas según la tabla seleccionada
            int maxColumnas = obtenerTamañoMaximo(tablaSeleccionada);

            // Crear el modelo de tabla utilizando el método crearModeloTabla con el nombre de la tabla y el tamaño máximo de columnas
            modelo = crearModeloTabla(tablaSeleccionada, maxColumnas);

            // Actualizar la tabla con el nuevo modelo
            tabla.setModel(modelo);
            mostrarDatos((String) comboTablas.getSelectedItem());
        }
            
        });
        
        btnAñadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevoRegistro(tabla);
            }
        });

        
        
        // Agregar botones y combo box al panel
        JPanel panelBotones = new JPanel();
        panelBotones.add(comboTablas);
        panelBotones.add(btnModificar);
        panelBotones.add(btnBorrar);
        panelBotones.add(btnExtraer);
        panelBotones.add(btnAñadir);
        panel.add(panelBotones, BorderLayout.SOUTH);
    }

private int obtenerTamañoMaximo(String tablaSeleccionada) {
    switch (tablaSeleccionada) {
        case "empleados":
            return 7;
        case "ex_empleados":
            return 5;
        case "league_of_legends":
            return 8;
        default:
            return 0; // Valor predeterminado si no se reconoce la tabla seleccionada
    }
}

private DefaultTableModel crearModeloTabla(String tablaSeleccionada, int maxColumnas) {
    // Crear un nuevo modelo de tabla en lugar de limpiar el existente
    DefaultTableModel modeloTabla = new DefaultTableModel();

    // Conexión a la base de datos
    Connection conn = null;
    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/riot_games", usuario, contraseña);

        // Consulta para obtener los metadatos de la tabla
        DatabaseMetaData metaData = conn.getMetaData();
        ResultSet rsColumns = metaData.getColumns(null, null, tablaSeleccionada, null);

        // Limpiar el modelo de tabla antes de agregar nuevas columnas
        modeloTabla.setColumnCount(0); // Esto elimina todas las columnas existentes

        // Limitar el número máximo de columnas a agregar
        int contadorColumnas = 0;

        // Agregar las columnas al nuevo modelo de tabla
        while (rsColumns.next() && contadorColumnas < maxColumnas) {
            String columnName = rsColumns.getString("COLUMN_NAME");
            modeloTabla.addColumn(columnName);
            System.out.println("Columna agregada: " + columnName); // Impresión para depurar
            contadorColumnas++;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Manejo de excepciones aquí
    } finally {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    return modeloTabla;
}


private void mostrarDatos(String tablaSeleccionada) {
    // Limpiar el modelo de la tabla antes de agregar nuevos datos
    modelo.setRowCount(0); // Esto limpia los datos existentes en el modelo

    // Conexión a la base de datos
    Connection conn = null;
    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/riot_games", usuario, contraseña);

        // Consulta SQL para obtener todos los registros de la tabla seleccionada
        String sql = "SELECT * FROM " + tablaSeleccionada;
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        // Obtener los metadatos del conjunto de resultados
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Agregar los datos a la tabla
        while (resultSet.next()) {
            Object[] rowData = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                try {
                    // Intenta obtener el valor de la columna como un objeto
                    Object value = resultSet.getObject(i);
                    // Verifica si el valor es una fecha cero y maneja el caso
                    if (value instanceof java.sql.Date && ((java.sql.Date) value).toLocalDate().isEqual(LocalDate.of(1, 1, 1))) {
                        // Si es una fecha cero, asigna un valor predeterminado
                        rowData[i - 1] = "Fecha inválida";
                    } else {
                        // Si no es una fecha cero, asigna el valor normal
                        rowData[i - 1] = value;
                    }
                } catch (SQLException e) {
                    // Maneja cualquier excepción al intentar obtener el valor de la columna
                    e.printStackTrace();
                    rowData[i - 1] = "Error";
                }
            }
            modelo.addRow(rowData); // Agrega la fila al modelo existente
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
private void nuevoRegistro(JTable tabla) {
    // Obtener los nombres de las columnas de la tabla actual
    TableColumnModel columnModel = tabla.getColumnModel();
    List<String> columnNames = new ArrayList<>();
    for (int i = 0; i < columnModel.getColumnCount(); i++) {
        columnNames.add(columnModel.getColumn(i).getHeaderValue().toString());
    }

    // Crear un mapa para almacenar los valores de las nuevas filas
    Map<String, String> nuevosValores = new HashMap<>();

    // Mostrar un cuadro de diálogo para que el usuario ingrese los valores para las nuevas filas
    JPanel panel = new JPanel(new GridLayout(0, 2));
    for (String columnName : columnNames) {
        panel.add(new JLabel(columnName + ":"));
        JTextField textField = new JTextField();
        panel.add(textField);
        // Agregar el valor ingresado al mapa
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                nuevosValores.put(columnName, textField.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                nuevosValores.put(columnName, textField.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                nuevosValores.put(columnName, textField.getText());
            }
        });
    }

    int result = JOptionPane.showConfirmDialog(null, panel, "Añadir nuevo registro", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
        // Obtener el nombre de la tabla seleccionada
        String tablaSeleccionada = (String) comboTablas.getSelectedItem(); 
        // Construir la consulta SQL para insertar los nuevos valores en la base de datos
        StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ");
        sqlBuilder.append(tablaSeleccionada).append(" (");
        for (String columnName : columnNames) {
            sqlBuilder.append(columnName).append(", ");
        }
        sqlBuilder.delete(sqlBuilder.length() - 2, sqlBuilder.length()); // Eliminar la última coma y el espacio
        sqlBuilder.append(") VALUES (");
        for (int i = 0; i < columnNames.size(); i++) {
            sqlBuilder.append("?, ");
        }
        sqlBuilder.delete(sqlBuilder.length() - 2, sqlBuilder.length()); // Eliminar la última coma y el espacio
        sqlBuilder.append(")");

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/riot_games", usuario, contraseña);
             PreparedStatement statement = conn.prepareStatement(sqlBuilder.toString())) {

            // Establecer los valores para los parámetros de la consulta
            int index = 1;
            for (String columnName : columnNames) {
                statement.setString(index++, nuevosValores.get(columnName));
            }

            // Ejecutar la consulta
            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas > 0) {
                JOptionPane.showMessageDialog(this, "Nuevo registro añadido correctamente");
                mostrarDatos(tablaSeleccionada);
            } else {
                JOptionPane.showMessageDialog(this, "Error al añadir el nuevo registro", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al conectar a la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

private void modificarRegistro() {
    int filaSeleccionada = tabla.getSelectedRow();
    if (filaSeleccionada != -1) {
        String tablaSeleccionada = (String) comboTablas.getSelectedItem();
        int id = (int) tabla.getValueAt(filaSeleccionada, 0); // Obtener el ID del registro seleccionado

        // Obtener los nombres de las columnas de la tabla actual
        TableColumnModel columnModel = tabla.getColumnModel();
        List<String> columnNames = new ArrayList<>();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnNames.add(columnModel.getColumn(i).getHeaderValue().toString());
        }

        // Obtener los nuevos datos del usuario
        Map<String, String> nuevosDatos = obtenerNuevosDatos(columnNames);

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

// Método para obtener los nuevos datos del usuario
private Map<String, String> obtenerNuevosDatos(List<String> columnNames) {
    Map<String, String> nuevosDatos = new HashMap<>();
    for (String columnName : columnNames) {
        nuevosDatos.put(columnName, JOptionPane.showInputDialog(this, "Ingrese el nuevo valor para " + columnName + ":"));
    }
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
