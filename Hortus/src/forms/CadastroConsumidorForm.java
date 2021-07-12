package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.JSeparator;
import javax.swing.JInternalFrame;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField$AbstractFormatter;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CadastroConsumidorForm {

	private JFrame frame;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtCPF;
	private JPasswordField txtSenha;
	private JPasswordField txtConfirmaSenha;
	JFormattedTextField txtTelefone;

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
	 * 
	 * @throws ParseException
	 */
	public CadastroConsumidorForm() throws ParseException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws ParseException
	 */
	private void initialize() throws ParseException {
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
		panel_1.setBounds(26, 81, 851, 592);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		txtCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		txtCPF.setHorizontalAlignment(SwingConstants.LEFT);
		txtCPF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCPF.setColumns(10);
		txtCPF.setBounds(343, 148, 286, 42);
		panel_1.add(txtCPF);

		JTextPane lblCadastro = new JTextPane();
		lblCadastro.setText("Novo Consumidor");
		lblCadastro.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblCadastro.setBounds(253, 10, 376, 61);
		panel_1.add(lblCadastro);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 81, 831, 2);
		panel_1.add(separator);

		txtNome = new JTextField();
		txtNome.setHorizontalAlignment(SwingConstants.LEFT);
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNome.setColumns(10);
		txtNome.setBounds(10, 148, 286, 42);
		panel_1.add(txtNome);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome.setBounds(10, 117, 84, 25);
		panel_1.add(lblNome);

		txtEmail = new JTextField();
		txtEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String email = txtEmail.getText();
				if (!email.contains("@")) {
					showMessageDialog(null, "E-mail inserido está incorreto!");
				}
			}
		});
		txtEmail.setHorizontalAlignment(SwingConstants.LEFT);
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtEmail.setColumns(10);
		txtEmail.setBounds(10, 255, 286, 42);
		panel_1.add(txtEmail);

		JLabel lblEmail_1 = new JLabel("Email:");
		lblEmail_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail_1.setBounds(10, 219, 55, 25);
		panel_1.add(lblEmail_1);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setHorizontalAlignment(SwingConstants.LEFT);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSenha.setBounds(10, 327, 77, 25);
		panel_1.add(lblSenha);

		JLabel lblConfirmaSenha = new JLabel("Confirma Senha:");
		lblConfirmaSenha.setHorizontalAlignment(SwingConstants.LEFT);
		lblConfirmaSenha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblConfirmaSenha.setBounds(10, 425, 154, 25);
		panel_1.add(lblConfirmaSenha);

		JLabel lblCPF = new JLabel("CPF:");
		lblCPF.setHorizontalAlignment(SwingConstants.LEFT);
		lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCPF.setBounds(343, 112, 77, 25);
		panel_1.add(lblCPF);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(10, 362, 286, 42);
		panel_1.add(txtSenha);

		txtConfirmaSenha = new JPasswordField();
		txtConfirmaSenha.setBounds(10, 460, 286, 42);
		panel_1.add(txtConfirmaSenha);

		txtTelefone = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
		txtTelefone.setHorizontalAlignment(SwingConstants.LEFT);
		txtTelefone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(343, 255, 286, 42);
		panel_1.add(txtTelefone);

		JLabel lblNome_1 = new JLabel("*");
		lblNome_1.setForeground(Color.RED);
		lblNome_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome_1.setBounds(73, 113, 77, 25);
		panel_1.add(lblNome_1);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(323, 81, 354, 501);
		panel_1.add(separator_1);

		JLabel lblNome_1_1 = new JLabel("*");
		lblNome_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome_1_1.setForeground(Color.RED);
		lblNome_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome_1_1.setBounds(73, 220, 77, 25);
		panel_1.add(lblNome_1_1);

		JLabel lblNome_1_2 = new JLabel("*");
		lblNome_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome_1_2.setForeground(Color.RED);
		lblNome_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome_1_2.setBounds(73, 327, 77, 25);
		panel_1.add(lblNome_1_2);

		JLabel lblNome_1_3 = new JLabel("*");
		lblNome_1_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome_1_3.setForeground(Color.RED);
		lblNome_1_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome_1_3.setBounds(164, 425, 77, 25);
		panel_1.add(lblNome_1_3);

		JLabel lblNome_1_4 = new JLabel("*");
		lblNome_1_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome_1_4.setForeground(Color.RED);
		lblNome_1_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome_1_4.setBounds(390, 113, 77, 25);
		panel_1.add(lblNome_1_4);

		JLabel lblNome_1_5 = new JLabel("*Campos obrigat\u00F3rios");
		lblNome_1_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome_1_5.setForeground(Color.RED);
		lblNome_1_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome_1_5.setBounds(343, 497, 217, 25);
		panel_1.add(lblNome_1_5);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTelefone.setBounds(343, 219, 124, 25);
		panel_1.add(lblTelefone);

		JLabel lblNome_1_6 = new JLabel("*");
		lblNome_1_6.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome_1_6.setForeground(Color.RED);
		lblNome_1_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome_1_6.setBounds(430, 219, 77, 25);
		panel_1.add(lblNome_1_6);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(323, 327, 528, 2);
		panel_1.add(separator_2);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setHorizontalAlignment(SwingConstants.LEFT);
		lblEndereo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEndereo.setBounds(333, 337, 174, 25);
		panel_1.add(lblEndereo);
		
		JLabel lblNome_1_2_1 = new JLabel("*");
		lblNome_1_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome_1_2_1.setForeground(Color.RED);
		lblNome_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome_1_2_1.setBounds(430, 337, 77, 25);
		panel_1.add(lblNome_1_2_1);
		
				JButton btnCriarConta = new JButton("Criar Conta");
				btnCriarConta.setBounds(699, 540, 142, 42);
				panel_1.add(btnCriarConta);
				btnCriarConta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						String senha = txtSenha.getText();
						String confirmaSenha = txtConfirmaSenha.getText();

						// verificando se pelo menos um dos campos está vazio e/ou incompleto
						if (txtNome.getText().isBlank() || txtEmail.getText().isBlank() || txtSenha.getText().isBlank()
								|| txtConfirmaSenha.getText().isBlank() || txtCPF.getText().isBlank()
								|| txtTelefone.getText().isBlank()) {

							showMessageDialog(null, "Há campo(s) vazio(s)");

							return;
						}

						// testando se senha bate com confirmaSenha
						if (senha.equals(confirmaSenha)) {
							// BACKEND GOES HERE
							showMessageDialog(null, "Cadastro de '" + txtNome.getText() + "' efetuado com sucesso!");
							
						} else {
							showMessageDialog(null, "Suas senhas não são iguais.");
						}
					}
				});
				btnCriarConta.setFont(new Font("Tahoma", Font.PLAIN, 20));
				btnCriarConta.setBackground(new Color(51, 204, 102));
				
				JFormattedTextField txtCPF_1 = new JFormattedTextField((AbstractFormatter) null);
				txtCPF_1.setHorizontalAlignment(SwingConstants.LEFT);
				txtCPF_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
				txtCPF_1.setColumns(10);
				txtCPF_1.setBounds(343, 408, 286, 42);
				panel_1.add(txtCPF_1);
				
				JLabel lblCPF_1 = new JLabel("CPF:");
				lblCPF_1.setHorizontalAlignment(SwingConstants.LEFT);
				lblCPF_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblCPF_1.setBounds(343, 372, 77, 25);
				panel_1.add(lblCPF_1);
		frame.setBounds(100, 100, 907, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
	}
}
