package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.Hashtable;

import javax.swing.JSeparator;
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

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;

public class PerfilConsumidorForm {

	// ================================= PROPRIEDADES ==========================

	// Componentes
	private JFrame frame;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtCPF;
	private JPasswordField txtSenha;
	private JPasswordField txtConfirmaSenha;
	private JFormattedTextField txtTelefone;
	private JFormattedTextField txtNum;

	// Endereco
	private JComboBox<Object> cbEstado;
	private JTextField txtCidade;
	private JFormattedTextField txtCEP;
	private JTextField txtBairro;
	private JTextField txtRua;
	private JTextField txtComplemento;

	// dados
	private Consumidor consumidorLogado;
	private static Hashtable<String, Integer> siglaEstadoDict;

	// ================================= METODOS ==========================

	/**
	 * inicializa e preenche o dicionario de siglas de estado
	 * 
	 * @param sigla
	 * @return
	 */
	public void fillSiglaEstadoDict() {
		siglaEstadoDict = new Hashtable<String, Integer>();
		siglaEstadoDict.put("AC", 0);
		siglaEstadoDict.put("AL", 1);
		siglaEstadoDict.put("AP", 2);
		siglaEstadoDict.put("AM", 3);
		siglaEstadoDict.put("BA", 4);
		siglaEstadoDict.put("CE", 5);
		siglaEstadoDict.put("ES", 6);
		siglaEstadoDict.put("GO", 7);
		siglaEstadoDict.put("MA", 8);
		siglaEstadoDict.put("MT", 9);
		siglaEstadoDict.put("MS", 10);
		siglaEstadoDict.put("MG", 11);
		siglaEstadoDict.put("PA", 12);
		siglaEstadoDict.put("PB", 13);
		siglaEstadoDict.put("PR", 14);
		siglaEstadoDict.put("PE", 15);
		siglaEstadoDict.put("PI", 16);
		siglaEstadoDict.put("RJ", 17);
		siglaEstadoDict.put("RN", 18);
		siglaEstadoDict.put("RS", 19);
		siglaEstadoDict.put("RO", 20);
		siglaEstadoDict.put("RR", 21);
		siglaEstadoDict.put("SC", 22);
		siglaEstadoDict.put("SP", 23);
		siglaEstadoDict.put("SE", 24);
		siglaEstadoDict.put("TO", 25);
		siglaEstadoDict.put("DF", 25);
	}

	/**
	 * carrega os dados do usuario logado no formulario
	 */
	public void loadFormData() {

		// preenchendo o siglaEstado dicionario
		fillSiglaEstadoDict();

		// preenchendo os dados na tela
		txtNome.setText(consumidorLogado.getNome());
		txtCPF.setText(consumidorLogado.getCpf());
		txtTelefone.setText(consumidorLogado.getTelefone());
		txtEmail.setText(consumidorLogado.getEmail());
		txtNum.setText(consumidorLogado.getEndereco().getEndNum());
		cbEstado.setSelectedIndex(siglaEstadoDict.get(consumidorLogado.getEndereco().getEndEstado()));
		txtCidade.setText(consumidorLogado.getEndereco().getEndCidade());
		txtCEP.setText(consumidorLogado.getEndereco().getEndCEP());
		txtBairro.setText(consumidorLogado.getEndereco().getEndBairro());
		txtRua.setText(consumidorLogado.getEndereco().getEndRua());
		txtComplemento.setText(consumidorLogado.getEndereco().getEndComplemento());
	}

	/**
	 * limpa os campos apos dar submit
	 */
	public void cleanFields() {
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
	}

	/**
	 * salva as alterações feitas pelo usuario em seu perfil
	 */
	public void salvarConta() {
		String senha = txtSenha.getText();
		String confirmaSenha = txtConfirmaSenha.getText();

		// verificando se pelo menos um dos campos está vazio e/ou incompleto
		if (txtNome.getText().isBlank() || txtEmail.getText().isBlank() || txtCPF.getText().isBlank()
				|| txtTelefone.getText().isBlank() || txtRua.getText().isBlank() || txtNum.getText().isBlank()
				|| txtBairro.getText().isBlank() || txtCEP.getText().isBlank() || txtCidade.getText().isBlank()) {
			showMessageDialog(null, "Há campo(s) deixados em branco(s)");
			return;
		}

		// testando a se senha bate com confirmaSenha
		if (senha.equals(confirmaSenha)) {
			submitForm();
		} else {
			showMessageDialog(null, "Suas senhas não são iguais.");
		}
	}

	/**
	 * envia os dados do formulario para o banco
	 */
	@SuppressWarnings("deprecation")
	public void submitForm() {

		SGBD banco = new SGBD();

		// se o usuario deixou os campos de senha e alterar senha em branco, a senha
		// permanece a anterior
		String password = "";
		if (txtSenha.getText().isBlank() || txtConfirmaSenha.getText().isBlank()) {
			password = consumidorLogado.getSenha();
		} else {
			password = txtSenha.getText();
		}

		consumidorLogado.setNome(txtNome.getText());
		consumidorLogado.setEmail(txtEmail.getText());
		consumidorLogado.setCpf(txtCPF.getText());
		consumidorLogado.setTelefone(txtTelefone.getText());
		consumidorLogado.setSenha(password);
		
		// Atualizar o endereço do consumidor
		Endereco end = consumidorLogado.getEndereco();

		end.setEndRua(txtRua.getText());
		end.setEndBairro(txtBairro.getText());
		end.setEndComplemento(txtComplemento.getText());
		end.setEndCidade(txtCidade.getText());
		end.setEndEstado(cbEstado.getSelectedItem().toString());
		end.setEndCEP(txtCEP.getText());
		end.setEndNum(txtNum.getText());
		
		banco.atualizaConsumidor(consumidorLogado);

		// limpando os campos
		showMessageDialog(null, "Alterações de '" + txtNome.getText() + "' salvas com sucesso!");
		cleanFields();
		frame.dispose();
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(true);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PerfilConsumidorForm window = new PerfilConsumidorForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// ========================== CONSTRUTORES ==========================

	public PerfilConsumidorForm() throws ParseException {
		initialize();
	}

	public PerfilConsumidorForm(Consumidor consumidor) throws ParseException {

		this.consumidorLogado = consumidor;

		// carregar na tela os dados do consumidor
		initialize();
		loadFormData();
		frame.setVisible(true);
	}

	// ================================ GUI ==========================
	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws ParseException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
		panel_1.setBounds(26, 56, 851, 807);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		txtCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		txtCPF.setHorizontalAlignment(SwingConstants.LEFT);
		txtCPF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCPF.setColumns(10);
		txtCPF.setBounds(343, 148, 286, 42);
		panel_1.add(txtCPF);

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

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTelefone.setBounds(343, 219, 124, 25);
		panel_1.add(lblTelefone);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(323, 327, 528, 2);
		panel_1.add(separator_2);

		JButton btnSalvarAlteracoes = new JButton("Salvar Altera\u00E7\u00F5es");
		btnSalvarAlteracoes.setForeground(new Color(255, 255, 255));
		btnSalvarAlteracoes.setBounds(283, 738, 286, 45);
		panel_1.add(btnSalvarAlteracoes);
		btnSalvarAlteracoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvarConta();
			}
		});
		btnSalvarAlteracoes.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSalvarAlteracoes.setBackground(new Color(51, 204, 102));

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
		txtBairro.setBounds(343, 485, 286, 42);
		txtBairro.setDocument(new JTextFieldLimit(18));
		panel_1.add(txtBairro);

		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setHorizontalAlignment(SwingConstants.LEFT);
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBairro.setBounds(343, 449, 77, 25);
		panel_1.add(lblBairro);

		txtCEP = new JFormattedTextField(new MaskFormatter("#####-###"));
		txtCEP.setHorizontalAlignment(SwingConstants.LEFT);
		txtCEP.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCEP.setColumns(10);
		txtCEP.setBounds(652, 485, 142, 42);
		panel_1.add(txtCEP);

		JLabel lblCEP = new JLabel("CEP:");
		lblCEP.setHorizontalAlignment(SwingConstants.LEFT);
		lblCEP.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCEP.setBounds(652, 449, 77, 25);
		panel_1.add(lblCEP);

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setHorizontalAlignment(SwingConstants.LEFT);
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCidade.setBounds(343, 622, 77, 25);
		panel_1.add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.setHorizontalAlignment(SwingConstants.LEFT);
		txtCidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCidade.setColumns(10);
		txtCidade.setBounds(343, 658, 217, 42);
		txtCidade.setDocument(new JTextFieldLimit(28));
		panel_1.add(txtCidade);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.LEFT);
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEstado.setBounds(584, 622, 77, 25);
		panel_1.add(lblEstado);

		cbEstado = new JComboBox<Object>();
		cbEstado.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cbEstado.setModel(new DefaultComboBoxModel(
				new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR",
						"PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", "DF" }));
		cbEstado.setBounds(584, 661, 55, 34);
		panel_1.add(cbEstado);

		txtComplemento = new JTextField();
		txtComplemento.setHorizontalAlignment(SwingConstants.LEFT);
		txtComplemento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(343, 572, 451, 42);
		txtComplemento.setDocument(new JTextFieldLimit(28));
		panel_1.add(txtComplemento);

		JLabel lblComplemento = new JLabel("Complemento:");
		lblComplemento.setHorizontalAlignment(SwingConstants.LEFT);
		lblComplemento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblComplemento.setBounds(343, 537, 146, 25);
		panel_1.add(lblComplemento);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(323, 81, 528, 631);
		panel_1.add(separator_1);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(10, 710, 831, 2);
		panel_1.add(separator_3);

		JLabel lblNewLabel = new JLabel("Perfil do Consumidor");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 831, 61);
		panel_1.add(lblNewLabel);
		frame.setBounds(100, 100, 907, 884);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
	}
}
