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

import hortus.Consumidor;
import hortus.Endereco;
import hortus.SGBD;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField.AbstractFormatter;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;

public class CadastroConsumidorForm {

	private JFrame frame;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtCPF;
	private JPasswordField txtSenha;
	private JPasswordField txtConfirmaSenha;
	private JFormattedTextField txtTelefone;
	private JFormattedTextField txtNum;

	// Endereco
	private JComboBox cbEstado;
	private JTextField txtCidade;
	private JFormattedTextField txtCEP;
	private JTextField txtBairro;
	private JTextField txtRua;
	private JTextField txtComplemento;

	/**
	 * Métodos
	 */
	@SuppressWarnings("deprecation")
	public void submitForm() {

//		Consumidor consumidor = new Consumidor();

		showMessageDialog(null, "Cadastro de '" + txtNome.getText() + "' efetuado com sucesso!");
		
		SGBD banco = new SGBD();
		
		Endereco end = new Endereco(txtRua.getText(), txtNum.getText(), txtComplemento.getText(),
				txtBairro.getText(), txtCEP.getText(), txtCidade.getText(), cbEstado.getSelectedItem().toString());
		
		banco.insereEndereco(end);

		Consumidor consum = new Consumidor(1, txtNome.getText(), txtCPF.getText(),
				txtTelefone.getText(), end, txtEmail.getText(), txtSenha.getText());
		
		banco.insereConsumidor(consum);
		
		banco.atualizaUsuarioEndereco(end, consum.getId());

		// limpando os campos
		txtNome.setText("");
		txtCPF.setText("");
		txtTelefone.setText("");
		txtEmail.setText("");
		txtSenha.setText("");
		txtConfirmaSenha.setText("");
		txtNum.setText("");
		cbEstado.setSelectedIndex(0);
		txtCidade.setText("");
		txtCEP.setText("");
		txtBairro.setText("");
		txtRua.setText("");
		txtComplemento.setText("");
		
		frame.dispose();
	}

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

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(true);
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
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int option = JOptionPane.showConfirmDialog(frame, "Deseja sair do Cadastro de Consumidor?",
						"Close Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					frame.dispose();
				} 
			}
		});
		btnSair.setBounds(858, 0, 48, 46);
		frame.getContentPane().add(btnSair);
		btnSair.setForeground(Color.WHITE);
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnSair.setBackground(Color.RED);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlShadow);
		panel.setBounds(0, 0, 906, 46);
		frame.getContentPane().add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(26, 56, 851, 750);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		txtCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		txtCPF.setHorizontalAlignment(SwingConstants.LEFT);
		txtCPF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCPF.setColumns(10);
		txtCPF.setBounds(343, 148, 286, 42);
		panel_1.add(txtCPF);

		JTextPane lblCadastro = new JTextPane();
		lblCadastro.setEditable(false);
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
		txtNome.setDocument(new JTextFieldLimit(48));
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
		txtEmail.setDocument(new JTextFieldLimit(48));
		panel_1.add(txtEmail);

		JLabel lblEmail_1 = new JLabel("Email:");
		lblEmail_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail_1.setBounds(-5, 228, 95, 25);
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
		txtSenha.setDocument(new JTextFieldLimit(48));
		panel_1.add(txtSenha);

		txtConfirmaSenha = new JPasswordField();
		txtConfirmaSenha.setBounds(10, 460, 286, 42);
		txtConfirmaSenha.setDocument(new JTextFieldLimit(48));
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

		JLabel lblNome_1_1 = new JLabel("*");
		lblNome_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome_1_1.setForeground(Color.RED);
		lblNome_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome_1_1.setBounds(73, 224, 77, 25);
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
		lblNome_1_5.setBounds(24, 707, 217, 25);
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

		JButton btnCriarConta = new JButton("Criar Conta");
		btnCriarConta.setForeground(new Color(255, 255, 255));
		btnCriarConta.setBounds(662, 698, 179, 42);
		panel_1.add(btnCriarConta);
		btnCriarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String senha = txtSenha.getText();
				String confirmaSenha = txtConfirmaSenha.getText();

				// verificando se pelo menos um dos campos está vazio e/ou incompleto
				if (txtNome.getText().isBlank() || txtEmail.getText().isBlank() || txtSenha.getText().isBlank()
						|| txtConfirmaSenha.getText().isBlank() || txtCPF.getText().isBlank()
						|| txtTelefone.getText().isBlank() || txtRua.getText().isBlank() || txtNum.getText().isBlank()
						|| txtComplemento.getText().isBlank() || txtBairro.getText().isBlank()
						|| txtCEP.getText().isBlank() || txtCidade.getText().isBlank()) {
					showMessageDialog(null, "Há campo(s) vazio(s)");
					return;
				}

				// testando se senha bate com confirmaSenha
				if (senha.equals(confirmaSenha)) {
					// BACKEND GOES HERE
					submitForm();
				} else {
					showMessageDialog(null, "Suas senhas não são iguais.");
				}
			}
		});
		btnCriarConta.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCriarConta.setBackground(new Color(51, 204, 102));

		txtRua = new JTextField();
		txtRua.setHorizontalAlignment(SwingConstants.LEFT);
		txtRua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtRua.setColumns(10);
		txtRua.setBounds(343, 383, 286, 42);
		txtRua.setDocument(new JTextFieldLimit(48));
		panel_1.add(txtRua);

		JLabel lblRua = new JLabel("Rua:");
		lblRua.setHorizontalAlignment(SwingConstants.LEFT);
		lblRua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRua.setBounds(343, 347, 77, 25);
		panel_1.add(lblRua);

		JLabel lblNumero = new JLabel("Num:");
		lblNumero.setHorizontalAlignment(SwingConstants.LEFT);
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNumero.setBounds(652, 347, 77, 25);
		panel_1.add(lblNumero);

		txtNum = new JFormattedTextField((Object) null);
		txtNum.setHorizontalAlignment(SwingConstants.LEFT);
		txtNum.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNum.setColumns(10);
		txtNum.setBounds(652, 383, 60, 42);
		txtNum.setDocument(new JTextFieldLimit(10));
		panel_1.add(txtNum);

		txtBairro = new JTextField();
		txtBairro.setHorizontalAlignment(SwingConstants.LEFT);
		txtBairro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtBairro.setColumns(10);
		txtBairro.setBounds(343, 492, 286, 42);
		txtBairro.setDocument(new JTextFieldLimit(18));
		panel_1.add(txtBairro);

		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setHorizontalAlignment(SwingConstants.LEFT);
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBairro.setBounds(343, 456, 77, 25);
		panel_1.add(lblBairro);

		txtCEP = new JFormattedTextField(new MaskFormatter("#####-###"));
		txtCEP.setHorizontalAlignment(SwingConstants.LEFT);
		txtCEP.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCEP.setColumns(10);
		txtCEP.setBounds(652, 492, 142, 42);
		panel_1.add(txtCEP);

		JLabel lblCEP = new JLabel("CEP:");
		lblCEP.setHorizontalAlignment(SwingConstants.LEFT);
		lblCEP.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCEP.setBounds(652, 456, 77, 25);
		panel_1.add(lblCEP);

		JLabel lblNome_1_3_1 = new JLabel("*");
		lblNome_1_3_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome_1_3_1.setForeground(Color.RED);
		lblNome_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome_1_3_1.setBounds(390, 348, 77, 25);
		panel_1.add(lblNome_1_3_1);

		JLabel lblNome_1_3_2 = new JLabel("*");
		lblNome_1_3_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome_1_3_2.setForeground(Color.RED);
		lblNome_1_3_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome_1_3_2.setBounds(412, 456, 77, 25);
		panel_1.add(lblNome_1_3_2);

		JLabel lblNome_1_3_3 = new JLabel("*");
		lblNome_1_3_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome_1_3_3.setForeground(Color.RED);
		lblNome_1_3_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome_1_3_3.setBounds(699, 337, 77, 25);
		panel_1.add(lblNome_1_3_3);

		JLabel lblNome_1_3_4 = new JLabel("*");
		lblNome_1_3_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome_1_3_4.setForeground(Color.RED);
		lblNome_1_3_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome_1_3_4.setBounds(699, 456, 77, 25);
		panel_1.add(lblNome_1_3_4);

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setHorizontalAlignment(SwingConstants.LEFT);
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCidade.setBounds(343, 640, 77, 25);
		panel_1.add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.setHorizontalAlignment(SwingConstants.LEFT);
		txtCidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCidade.setColumns(10);
		txtCidade.setBounds(343, 676, 217, 42);
		txtCidade.setDocument(new JTextFieldLimit(28));
		panel_1.add(txtCidade);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.LEFT);
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEstado.setBounds(584, 640, 77, 25);
		panel_1.add(lblEstado);

		cbEstado = new JComboBox();
		cbEstado.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cbEstado.setModel(new DefaultComboBoxModel(new String[] {"AC", "AL", "AP", "AM", "BA", "CE", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", "DF"}));
		cbEstado.setBounds(584, 679, 55, 34);
		panel_1.add(cbEstado);

		JLabel lblNome_1_3_2_1 = new JLabel("*");
		lblNome_1_3_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome_1_3_2_1.setForeground(Color.RED);
		lblNome_1_3_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome_1_3_2_1.setBounds(412, 640, 77, 25);
		panel_1.add(lblNome_1_3_2_1);

		JLabel lblNome_1_3_2_2 = new JLabel("*");
		lblNome_1_3_2_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome_1_3_2_2.setForeground(Color.RED);
		lblNome_1_3_2_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome_1_3_2_2.setBounds(652, 640, 77, 25);
		panel_1.add(lblNome_1_3_2_2);

		txtComplemento = new JTextField();
		txtComplemento.setHorizontalAlignment(SwingConstants.LEFT);
		txtComplemento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(343, 588, 451, 42);
		txtComplemento.setDocument(new JTextFieldLimit(28));
		panel_1.add(txtComplemento);

		JLabel lblComplemento = new JLabel("Complemento:");
		lblComplemento.setHorizontalAlignment(SwingConstants.LEFT);
		lblComplemento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblComplemento.setBounds(343, 553, 146, 25);
		panel_1.add(lblComplemento);

		JLabel lblNome_1_3_2_3 = new JLabel("*");
		lblNome_1_3_2_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome_1_3_2_3.setForeground(Color.RED);
		lblNome_1_3_2_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome_1_3_2_3.setBounds(483, 553, 77, 25);
		panel_1.add(lblNome_1_3_2_3);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(323, 81, 528, 669);
		panel_1.add(separator_1);
		frame.setBounds(100, 100, 907, 817);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
	}

}
