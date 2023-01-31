package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaEnvios extends JFrame {

	private JPanel contentPane;
	private JTextField txtOrigen;
	private JTextField txtDestino;
	private JTextField txtPeso;
	private final ButtonGroup grupoOrigen = new ButtonGroup();
	private final ButtonGroup grupoDestino = new ButtonGroup();
	private JComboBox comboBox;
	private JRadioButton rdbNacional_1;
	private JRadioButton rdbExtranjero;
	private JRadioButton rdbNacional;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEnvios frame = new VentanaEnvios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaEnvios() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[37.00][][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Calculadora de envíos");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.MAGENTA);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblNewLabel, "cell 0 0 4 1,grow");
		
		JLabel lblNewLabel_1 = new JLabel("Ciudad Origen:");
		contentPane.add(lblNewLabel_1, "cell 0 1,alignx trailing");
		
		txtOrigen = new JTextField();
		contentPane.add(txtOrigen, "cell 1 1 2 1,growx");
		txtOrigen.setColumns(10);
		
		rdbNacional = new JRadioButton("Nacional");
		rdbNacional.setSelected(true);
		grupoOrigen.add(rdbNacional);
		contentPane.add(rdbNacional, "cell 1 2");
		
		rdbExtranjero = new JRadioButton("Extranjero");
		grupoOrigen.add(rdbExtranjero);
		contentPane.add(rdbExtranjero, "cell 2 2");
		
		JLabel lblNewLabel_2 = new JLabel("Ciudad Destino:");
		contentPane.add(lblNewLabel_2, "cell 0 3,alignx trailing");
		
		txtDestino = new JTextField();
		contentPane.add(txtDestino, "cell 1 3 2 1,growx");
		txtDestino.setColumns(10);
		
		rdbNacional_1 = new JRadioButton("Nacional");
		rdbNacional_1.setSelected(true);
		grupoDestino.add(rdbNacional_1);
		contentPane.add(rdbNacional_1, "cell 1 4");
		
		JRadioButton rdbExtranjero_1 = new JRadioButton("Extranjero");
		grupoDestino.add(rdbExtranjero_1);
		contentPane.add(rdbExtranjero_1, "cell 2 4");
		
		JLabel lblNewLabel_3 = new JLabel("Tipos de Envío:");
		contentPane.add(lblNewLabel_3, "cell 0 5,alignx trailing");
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Paq 10 - Antes de las 10 h", "Paq 14 - Antes de las 14 h", "Paq 24 - Al día siguiente"}));
		contentPane.add(comboBox, "cell 1 5 2 1,growx");
		
		JLabel lblNewLabel_4 = new JLabel("Peso:");
		contentPane.add(lblNewLabel_4, "cell 0 6,alignx trailing");
		
		txtPeso = new JTextField();
		contentPane.add(txtPeso, "cell 1 6,growx");
		txtPeso.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Kg:");
		contentPane.add(lblNewLabel_5, "cell 2 6");
		
		JButton btnNewButton = new JButton("Calcular Precio:");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularEnvío();
			}
		});
		contentPane.add(btnNewButton, "cell 0 8 4 1,alignx center");
	}

	protected void calcularEnvío() {
		try {
			String origen = txtOrigen.getText();
			String destino = txtDestino.getText();
			String textoTipo = (String) comboBox.getSelectedItem();
			int tipo = comboBox.getSelectedIndex();
			int peso = Integer.parseInt(txtPeso.getText());
			
			if (origen==null || origen.isBlank() ||
				destino==null || destino.isBlank() ) {
				JOptionPane.showMessageDialog(this, 
						"Introduce las ciudades de origen y destino", 
						"Faltan datos", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (peso<1 || peso >40 ) {
				JOptionPane.showMessageDialog(this, 
						"El peso del paquete debe estar entre 1 y 40", 
						"Peso incorrecto", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			double precio=7;
			if (rdbNacional.isSelected() && rdbNacional_1.isSelected()) {
				precio=4;
			}
			
			if (textoTipo.equals("Paq 10 - Antes de las 10 h")) {
				precio=precio+5;
			} else if (textoTipo.equals("Paq 14 - Antes de las 14 h")) {
				precio=precio+2;
			}
			precio = precio + peso/10 * 3.5;
			
			JOptionPane.showMessageDialog(this, 
					"Origen: "+origen+"\nDestino: "+destino+
					"\nTipo: "+textoTipo+"\nPeso:"+peso+ "Kg\nImporte:"+precio+ " €", 
					"Cálculo", JOptionPane.INFORMATION_MESSAGE);
			
		}catch (NumberFormatException e ) {
			JOptionPane.showMessageDialog(this, 
					"Debe introducir un número correcto en el peso", 
					"Peso incorrecto", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		
	}

}
