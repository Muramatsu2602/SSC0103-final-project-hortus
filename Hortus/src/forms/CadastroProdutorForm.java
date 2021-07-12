package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CadastroProdutorForm {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroProdutorForm window = new CadastroProdutorForm();
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
	public CadastroProdutorForm() {
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
		btnSair.setForeground(Color.WHITE);
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnSair.setBackground(Color.RED);
		btnSair.setBounds(857, 0, 48, 46);
		frame.getContentPane().add(btnSair);
		
		JPanel panel = new JPanel();
		panel.setBounds(-1, 0, 906, 46);
		frame.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(26, 83, 851, 637);
		frame.getContentPane().add(panel_1);
		
		JTextPane txtpnNovoProdutor = new JTextPane();
		txtpnNovoProdutor.setText("Novo Produtor");
		txtpnNovoProdutor.setFont(new Font("Tahoma", Font.PLAIN, 45));
		txtpnNovoProdutor.setBounds(285, 10, 376, 61);
		panel_1.add(txtpnNovoProdutor);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 81, 831, 2);
		panel_1.add(separator);
		
		JButton btnCriarConta = new JButton("Criar Conta");
		btnCriarConta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCriarConta.setBackground(new Color(51, 204, 102));
		btnCriarConta.setBounds(699, 585, 142, 42);
		panel_1.add(btnCriarConta);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(394, 83, 354, 448);
		panel_1.add(separator_1);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome.setBounds(10, 107, 84, 25);
		panel_1.add(lblNome);
		
		JLabel lblNome_1 = new JLabel("*");
		lblNome_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome_1.setForeground(Color.RED);
		lblNome_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome_1.setBounds(73, 103, 77, 25);
		panel_1.add(lblNome_1);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setColumns(10);
		textField.setBounds(10, 138, 286, 42);
		panel_1.add(textField);
		frame.setBounds(100, 100, 905, 752);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
	}

}
