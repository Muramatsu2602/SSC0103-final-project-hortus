package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;
import javax.swing.JInternalFrame;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class CadastroConsumidorForm {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroConsumidorForm window = new CadastroConsumidorForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CadastroConsumidorForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 102, 255));
		frame.getContentPane().setLayout(null);
		
		JButton btnSair = new JButton("x");
		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showMessageDialog(null, "Até mais!");
				System.exit(0);
			}
		});
		btnSair.setBounds(858, 0, 48, 46);
		frame.getContentPane().add(btnSair);
		btnSair.setForeground(Color.WHITE);
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnSair.setBackground(Color.RED);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 906, 46);
		frame.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(26, 81, 851, 530);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JTextPane lblCadastro = new JTextPane();
		lblCadastro.setText("Novo Consumidor");
		lblCadastro.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblCadastro.setBounds(253, 10, 376, 61);
		panel_1.add(lblCadastro);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 81, 831, 2);
		panel_1.add(separator);
		
		JButton btnCriarConta = new JButton("Criar Conta");
		btnCriarConta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCriarConta.setBackground(new Color(51, 204, 102));
		btnCriarConta.setBounds(684, 460, 142, 42);
		panel_1.add(btnCriarConta);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setColumns(10);
		textField.setBounds(10, 148, 286, 42);
		panel_1.add(textField);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(10, 112, 55, 25);
		panel_1.add(lblEmail);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.LEFT);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_1.setColumns(10);
		textField_1.setBounds(10, 255, 286, 42);
		panel_1.add(textField_1);
		
		JLabel lblEmail_1 = new JLabel("Email:");
		lblEmail_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail_1.setBounds(10, 219, 55, 25);
		panel_1.add(lblEmail_1);
		
		JLabel lblEmail_2 = new JLabel("Email:");
		lblEmail_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail_2.setBounds(10, 327, 55, 25);
		panel_1.add(lblEmail_2);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.LEFT);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_2.setColumns(10);
		textField_2.setBounds(10, 363, 286, 42);
		panel_1.add(textField_2);
		frame.setBounds(100, 100, 907, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
	}
}
