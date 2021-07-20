package invernadero;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;

public class Window_1 extends JFrame {

	static String Sucursal1;
	private JPanel contentPane;

    private final String user = "postgres";
    private final String password = "tomas";
	
	void refrescar() {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Serial");
		modelo.addColumn("Nombre");
		modelo.addColumn("Grupo");
		modelo.addColumn("Orden");
		modelo.addColumn("Familia");
		modelo.addColumn("Género");
		modelo.addColumn("Cantidad");
		table.setModel(modelo);
		String datos[] = new String[7];
		Statement at = null;
		String url = "jdbc:postgresql://localhost:5432/Invernadero";
		try {
			Connection base = DriverManager.getConnection(url, user, password);
			at = base.createStatement();
			ResultSet rs = at.executeQuery("SELECT * FROM Invernadero");
			while (rs.next()) {
				datos[0] = rs.getString("serial");
				datos[1] = rs.getString("nombre");
				datos[2] = rs.getString("grupo");
				datos[3] = rs.getString("orden");
				datos[4] = rs.getString("familia");
				datos[5] = rs.getString("genero");
				datos[6] = rs.getString("cantidad");
				modelo.addRow(datos);
			}
			JOptionPane.showMessageDialog(null, "Acepta para continuar");
			rs.close();
			at.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window_1 frame = new Window_1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Window_1() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1096, 444);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		display1 = new JTextArea();
		display1.setEditable(false);
		display1.setBounds(10, 98, 299, 263);
		contentPane.add(display1);
		
		text1 = new JTextArea();
		text1.setBounds(10, 372, 203, 22);
		contentPane.add(text1);
		
		send1 = new JButton("Enviar");
		send1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//code
				String s = text1.getText();
				if(s.equals("")) {
					return;
				}
				if(s.equals("/planta")) {
					DefaultTableModel modelo = new DefaultTableModel();
					modelo.addColumn("Serial");
					modelo.addColumn("Nombre");
					modelo.addColumn("Grupo");
					modelo.addColumn("Orden");
					modelo.addColumn("Familia");
					modelo.addColumn("Género");
					modelo.addColumn("Cantidad");
					table.setModel(modelo);
					String datos[] = new String[7];
					Statement at = null;
					String url = "jdbc:postgresql://localhost:5432/Invernadero2";
					try {
						Connection base = DriverManager.getConnection(url, user, password);
						at = base.createStatement();
						ResultSet rs = at.executeQuery("SELECT * FROM Invernadero2");
						while (rs.next()) {
							datos[0] = rs.getString("serial");
							datos[1] = rs.getString("nombre");
							datos[2] = rs.getString("grupo");
							datos[3] = rs.getString("orden");
							datos[4] = rs.getString("familia");
							datos[5] = rs.getString("genero");
							datos[6] = rs.getString("cantidad");
							modelo.addRow(datos);
						}
						rs.close();
						at.close();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}
					display1.append("\nLas plantas del invernadero de " + Window_2.Sucursal2 + "\nestán siendo mostradas en la pantalla.\nPara volver a las de su sucursal\npresione en el botón Refrescar." + "\n\n");
					text1.setText("");
					return;
				}
				display1.append(Sucursal1 + ": " + s + "\n");
				Window_2.sendText();
				text1.setText("");
				
			}
		});
		send1.setBounds(220, 372, 89, 22);
		contentPane.add(send1);
		
		label1 = new JLabel("Chat con el Invernadero de " + Window_2.Sucursal2);
		label1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label1.setBounds(10, 64, 203, 30);
		contentPane.add(label1);
		
		JButton clear = new JButton("Limpiar");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				display1.setText("");
			}
		});
		clear.setBounds(220, 68, 89, 22);
		contentPane.add(clear);
		
		lblNewLabel = new JLabel("Serial");
		lblNewLabel.setBounds(319, 92, 156, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(319, 117, 156, 14);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Grupo");
		lblNewLabel_2.setBounds(319, 142, 156, 14);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Orden");
		lblNewLabel_3.setBounds(319, 167, 156, 14);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Familia");
		lblNewLabel_4.setBounds(319, 192, 156, 14);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("G\u00E9nero");
		lblNewLabel_5.setBounds(319, 217, 156, 14);
		contentPane.add(lblNewLabel_5);
		
		textSerial = new JTextField();
		textSerial.setColumns(10);
		textSerial.setBounds(379, 89, 96, 20);
		contentPane.add(textSerial);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(379, 114, 96, 20);
		contentPane.add(textNombre);
		
		textOrden = new JTextField();
		textOrden.setColumns(10);
		textOrden.setBounds(379, 164, 96, 20);
		contentPane.add(textOrden);
		
		textFamilia = new JTextField();
		textFamilia.setColumns(10);
		textFamilia.setBounds(379, 189, 96, 20);
		contentPane.add(textFamilia);
		
		textGenero = new JTextField();
		textGenero.setColumns(10);
		textGenero.setBounds(379, 214, 96, 20);
		contentPane.add(textGenero);
		
		lblNewLabel_7 = new JLabel("Cantidad");
		lblNewLabel_7.setBounds(319, 242, 156, 14);
		contentPane.add(lblNewLabel_7);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Espermatofita", "Pteridofita"}));
		comboBox.setBounds(379, 138, 96, 22);
		contentPane.add(comboBox);
		
		spinner = new JSpinner();
		spinner.setBounds(379, 239, 96, 20);
		contentPane.add(spinner);
		
		btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				String urlDatabase = "jdbc:postgresql://localhost:5432/Invernadero";
				try {
					Class.forName("org.postgresql.Driver");
					con = DriverManager.getConnection(urlDatabase, user, password);
					Statement st = con.createStatement();
					String sql = "INSERT INTO Invernadero(serial, nombre, grupo, orden, familia, genero, cantidad)"
							+ "values ('" + textSerial.getText() + "', '" + textNombre.getText() + "', '" + comboBox.getSelectedItem() + "', '" + textOrden.getText() + "', '" + textFamilia.getText() + "', '" + textGenero.getText() + "', '" + spinner.getValue() + "')";
					
					ResultSet result = st.executeQuery(sql);
					con.close();
					st.close();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
				refrescar();
			}
		});
		btnNewButton.setBounds(319, 267, 156, 23);
		contentPane.add(btnNewButton);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				String urlDatabase = "jdbc:postgresql://localhost:5432/Invernadero";
				try {
					Class.forName("org.postgresql.Driver");
					con = DriverManager.getConnection(urlDatabase, user, password);
					Statement st = con.createStatement();
					String sql = "UPDATE Invernadero SET"
							+ " serial='" + textSerial.getText() + "', nombre='" + textNombre.getText() + "', grupo='" + comboBox.getSelectedItem() + "', orden='" + textOrden.getText() + "', familia='" + textFamilia.getText() + "', genero='" + textGenero.getText() + "', cantidad='" + spinner.getValue() + "' WHERE serial='" + textSerial.getText() + "'";
					
					ResultSet result = st.executeQuery(sql);
					con.close();
					st.close();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
				refrescar();
			}
		});
		btnModificar.setBounds(319, 293, 156, 23);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				String urlDatabase = "jdbc:postgresql://localhost:5432/Invernadero";
				try {
					Class.forName("org.postgresql.Driver");
					con = DriverManager.getConnection(urlDatabase, user, password);
					Statement st = con.createStatement();
					String sql = "DELETE FROM Invernadero WHERE serial='" + textSerial.getText() + "'";
					ResultSet result = st.executeQuery(sql);
					con.close();
					st.close();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
				refrescar();
			}
		});
		btnEliminar.setBounds(319, 319, 156, 23);
		contentPane.add(btnEliminar);
		
		btnModificar_1 = new JButton("Refrescar");
		btnModificar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refrescar();
			}
		});
		btnModificar_1.setBounds(319, 345, 156, 23);
		contentPane.add(btnModificar_1);
		
		btnModificar_1_1 = new JButton("Nuevo");
		btnModificar_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textSerial.setText("");
				textNombre.setText("");
				comboBox.setSelectedItem(null);
				textOrden.setText("");
				textFamilia.setText("");
				textGenero.setText("");
				spinner.setValue(0);
			}
		});
		btnModificar_1_1.setBounds(319, 371, 156, 23);
		contentPane.add(btnModificar_1_1);
		
		table = new JTable();
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		table.setEnabled(false);
		table.setSurrendersFocusOnKeystroke(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		table.setBounds(485, 107, 585, 287);
		contentPane.add(table);
		
		lblNewLabel_6 = new JLabel("Serial");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setBounds(485, 92, 86, 14);
		contentPane.add(lblNewLabel_6);
		
		lblNewLabel_8 = new JLabel("Nombre");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_8.setBounds(568, 92, 86, 14);
		contentPane.add(lblNewLabel_8);
		
		lblNewLabel_8_1 = new JLabel("Grupo");
		lblNewLabel_8_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_8_1.setBounds(652, 92, 86, 14);
		contentPane.add(lblNewLabel_8_1);
		
		lblNewLabel_8_2 = new JLabel("Orden");
		lblNewLabel_8_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_8_2.setBounds(734, 92, 86, 14);
		contentPane.add(lblNewLabel_8_2);
		
		lblNewLabel_8_3 = new JLabel("Familia");
		lblNewLabel_8_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_8_3.setBounds(818, 92, 86, 14);
		contentPane.add(lblNewLabel_8_3);
		
		lblNewLabel_8_4 = new JLabel("G\u00E9nero");
		lblNewLabel_8_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_8_4.setBounds(900, 92, 86, 14);
		contentPane.add(lblNewLabel_8_4);
		
		lblNewLabel_8_5 = new JLabel("Cantidad");
		lblNewLabel_8_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_8_5.setBounds(984, 92, 86, 14);
		contentPane.add(lblNewLabel_8_5);
		
		lblInvernadero = new JLabel("Invernadero 3.0");
		lblInvernadero.setHorizontalAlignment(SwingConstants.CENTER);
		lblInvernadero.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblInvernadero.setBounds(485, 0, 585, 49);
		contentPane.add(lblInvernadero);
		
		lblNewLabel_10 = new JLabel("Realizado por Tom\u00E1s Villasmil / Programaci\u00F3n Orientada a Objetos");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setBounds(485, 32, 585, 31);
		contentPane.add(lblNewLabel_10);
		
		lblChat = new JLabel("Mensajes");
		lblChat.setHorizontalAlignment(SwingConstants.CENTER);
		lblChat.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblChat.setBounds(10, 0, 299, 49);
		contentPane.add(lblChat);
		
		lblNewLabel_9 = new JLabel("Comunicaci\u00F3n entre sucursales");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(10, 32, 299, 31);
		contentPane.add(lblNewLabel_9);
		
		lblIngresoDeDatos = new JLabel("Ingreso de Datos");
		lblIngresoDeDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngresoDeDatos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIngresoDeDatos.setBounds(319, 0, 156, 49);
		contentPane.add(lblIngresoDeDatos);
		
		lblNewLabel_11 = new JLabel("Registre plantas a su sucursal");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setBounds(308, 32, 179, 31);
		contentPane.add(lblNewLabel_11);
	}

	public static void sendText() {
    	String s= Window_2.text2.getText();
    	if (s.equals("")) {
    		return;
    	}
    	display1.append(Window_2.Sucursal2 + ": " + s + "\n");
    }
	private javax.swing.JLabel label1;
    private static javax.swing.JTextArea display1;
    private javax.swing.JButton send1;
    public static javax.swing.JTextArea text1;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JLabel lblNewLabel_4;
    private JLabel lblNewLabel_5;
    private JTextField textSerial;
    private JTextField textNombre;
    private JTextField textOrden;
    private JTextField textFamilia;
    private JTextField textGenero;
    private JLabel lblNewLabel_7;
    private JComboBox comboBox;
    private JSpinner spinner;
    private JButton btnNewButton;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnModificar_1;
    private JButton btnModificar_1_1;
    private JTable table;
    private JLabel lblNewLabel_6;
    private JLabel lblNewLabel_8;
    private JLabel lblNewLabel_8_1;
    private JLabel lblNewLabel_8_2;
    private JLabel lblNewLabel_8_3;
    private JLabel lblNewLabel_8_4;
    private JLabel lblNewLabel_8_5;
    private JLabel lblInvernadero;
    private JLabel lblNewLabel_10;
    private JLabel lblChat;
    private JLabel lblNewLabel_9;
    private JLabel lblIngresoDeDatos;
    private JLabel lblNewLabel_11;
}
