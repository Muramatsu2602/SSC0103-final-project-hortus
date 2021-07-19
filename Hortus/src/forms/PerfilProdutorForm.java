package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.text.ParseException;
import java.util.Hashtable;

import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import hortus.Consumidor;
import hortus.Endereco;
import hortus.Produtor;
import hortus.SGBD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.DefaultComboBoxModel;

public class PerfilProdutorForm {

	// ================================= PROPRIEDADES ==========================

	// Componentes
	private JFrame frame;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JFormattedTextField txtCNPJ;
	private JPasswordField txtSenha;
	private JPasswordField txtConfirmaSenha;
	private JFormattedTextField txtTelefone;
	private JFormattedTextField txtCCIR;
	private JComboBox cbTipoProducao;
	private JComboBox cbEstado;
	private JTextArea txtDescricao;

	// Endereco
	private JFormattedTextField txtNum;
	private JFormattedTextField txtCEP;
	private JTextField txtRua;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtComplemento;

	// dados
	private Produtor produtorLogado;
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
	 * limpa os campos apos dar submit
	 */
	public void cleanFields() {
		txtNome.setText("");
		txtEmail.setText("");
		txtCNPJ.setText("");
		txtSenha.setText("");
		txtConfirmaSenha.setText("");
		txtNum.setText("");
		cbTipoProducao.setSelectedIndex(0);
		cbEstado.setSelectedIndex(0);
		txtCidade.setText("");
		txtCEP.setText("");
		txtBairro.setText("");
		txtRua.setText("");
		txtComplemento.setText("");
		txtTelefone.setText("");
		txtCCIR.setText("");
		txtDescricao.setText("");
	}

	/**
	 * carrega os dados do usuario logado no formulario
	 */
	public void loadFormData() {

		// preenchendo o siglaEstado dicionario
		fillSiglaEstadoDict();

		// preenchendo os dados na tela
		txtNome.setText(produtorLogado.getNome());
		txtEmail.setText(produtorLogado.getEmail());
		txtCNPJ.setText(produtorLogado.getCpf());
		txtNum.setText(produtorLogado.getTelefone());
		txtCCIR.setText(produtorLogado.getCcir());
		cbTipoProducao.setSelectedIndex(produtorLogado.getTipoProd());
		txtNum.setText(produtorLogado.getEndereco().getEndNum());
		cbEstado.setSelectedIndex(siglaEstadoDict.get(produtorLogado.getEndereco().getEndEstado()));
		txtCidade.setText(produtorLogado.getEndereco().getEndCidade());
		txtCEP.setText(produtorLogado.getEndereco().getEndCEP());
		txtBairro.setText(produtorLogado.getEndereco().getEndBairro());
		txtRua.setText(produtorLogado.getEndereco().getEndRua());
		txtComplemento.setText(produtorLogado.getEndereco().getEndComplemento());
	}

	/**
	 * salva as alterações feitas pelo usuario em seu perfil
	 */
	public void salvarConta() {

		String senha = txtSenha.getText();
		String confirmaSenha = txtConfirmaSenha.getText();

		// verificando se pelo menos um dos campos está vazio e/ou incompleto
		if (txtNome.getText().isBlank() || txtEmail.getText().isBlank() || txtCNPJ.getText().isBlank()
				|| txtTelefone.getText().isBlank() || txtCCIR.getText().isBlank() || txtRua.getText().isBlank()
				|| txtNum.getText().isBlank() || txtBairro.getText().isBlank() || txtCEP.getText().isBlank()
				|| txtCidade.getText().isBlank()) {
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
			password = produtorLogado.getSenha();
		} else {
			password = txtSenha.getText();
		}

		produtorLogado.setNome(txtNome.getText());
		produtorLogado.setEmail(txtEmail.getText());
		produtorLogado.setCpf(txtCNPJ.getText());
		produtorLogado.setTelefone(txtTelefone.getText());
		produtorLogado.setSenha(password);
		produtorLogado.setTipoProd(cbTipoProducao.getSelectedIndex());
		produtorLogado.setDescricao(txtDescricao.getText());
		produtorLogado.setCcir(txtCCIR.getText());
		
		// Atualizar o endereço do consumidor
		Endereco end = produtorLogado.getEndereco();

		end.setEndRua(txtRua.getText());
		end.setEndBairro(txtBairro.getText());
		end.setEndComplemento(txtComplemento.getText());
		end.setEndCidade(txtCidade.getText());
		end.setEndEstado(cbEstado.getSelectedItem().toString());
		end.setEndCEP(txtCEP.getText());
		end.setEndNum(txtNum.getText());
		
		banco.atualizaProdutor(produtorLogado);

		// limpando os campos e fechando a tela
		showMessageDialog(null, "Alterações de '" + txtNome.getText() + "' efetuado com sucesso!");
		cleanFields();
		frame.dispose();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PerfilProdutorForm window = new PerfilProdutorForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}

	// ========================== CONSTRUTORES ==========================

	/**
	 * Create the application.
	 * 
	 * @throws ParseException
	 */
	public PerfilProdutorForm() throws ParseException {
		initialize();
	}
	
	public PerfilProdutorForm(Produtor produtor) throws ParseException {

		this.produtorLogado = produtor;

		// carregar na tela os dados do consumidor
		loadFormData();
		initialize();
		frame.setVisible(true);
	}

	// ================================ GUI ==========================
	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws ParseException
	 */
	private void initialize() throws ParseException {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 102, 255));
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(26, 83, 1081, 838);
		frame.getContentPane().add(panel);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(Color.WHITE);
		panel_1_1.setBounds(0, 0, 1091, 750);
		panel.add(panel_1_1);

		txtDescricao = new JTextArea();
		txtDescricao.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtDescricao.setText("");
			}
		});
		txtDescricao.setBackground(SystemColor.controlHighlight);
		txtDescricao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDescricao.setText("Escreva aqui os detalhes de sua produ\u00E7\u00E3o...");
		txtDescricao.setBounds(652, 224, 412, 103);
		txtDescricao.setDocument(new JTextFieldLimit(198));
		txtDescricao.setLineWrap(true);
		panel_1_1.add(txtDescricao);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(0, 337, 324, 2);
		panel_1_1.add(separator_4);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(323, 345, 647, -18);
		panel_1_1.add(separator_3);

		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setBounds(323, 327, 741, 0);
		panel_1_1.add(separator_2_1);

		txtCNPJ = new JFormattedTextField(new MaskFormatter("##.###.###/####-##"));
		txtCNPJ.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String CNPJ = txtEmail.getText();
				if (CNPJ.length() < 19) {
					showMessageDialog(null, "O CNPJ inserido está incompleto!");
				}
			}
		});
		txtCNPJ.setHorizontalAlignment(SwingConstants.LEFT);
		txtCNPJ.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCNPJ.setColumns(10);
		txtCNPJ.setBounds(343, 148, 286, 42);
		panel_1_1.add(txtCNPJ);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 81, 1071, 2);
		panel_1_1.add(separator_2);

		txtNome = new JTextField();
		txtNome.setHorizontalAlignment(SwingConstants.LEFT);
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNome.setColumns(10);
		txtNome.setBounds(10, 148, 286, 42);
		txtNome.setDocument(new JTextFieldLimit(48));
		panel_1_1.add(txtNome);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome.setBounds(10, 117, 84, 25);
		panel_1_1.add(lblNome);

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
		panel_1_1.add(txtEmail);

		JLabel lblEmail_1 = new JLabel("Email:");
		lblEmail_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail_1.setBounds(0, 224, 84, 25);
		panel_1_1.add(lblEmail_1);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setHorizontalAlignment(SwingConstants.LEFT);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSenha.setBounds(10, 469, 77, 25);
		panel_1_1.add(lblSenha);

		JLabel lblConfirmaSenha = new JLabel("Confirma Senha:");
		lblConfirmaSenha.setHorizontalAlignment(SwingConstants.LEFT);
		lblConfirmaSenha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblConfirmaSenha.setBounds(10, 567, 154, 25);
		panel_1_1.add(lblConfirmaSenha);

		JLabel lblCNPJ = new JLabel("CNPJ:");
		lblCNPJ.setHorizontalAlignment(SwingConstants.LEFT);
		lblCNPJ.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCNPJ.setBounds(343, 113, 77, 25);
		panel_1_1.add(lblCNPJ);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(10, 504, 286, 42);
		txtSenha.setDocument(new JTextFieldLimit(48));
		panel_1_1.add(txtSenha);

		txtConfirmaSenha = new JPasswordField();
		txtConfirmaSenha.setBounds(10, 602, 286, 42);
		txtConfirmaSenha.setDocument(new JTextFieldLimit(48));
		panel_1_1.add(txtConfirmaSenha);

		txtTelefone = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
		txtTelefone.setHorizontalAlignment(SwingConstants.LEFT);
		txtTelefone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(343, 255, 286, 42);
		panel_1_1.add(txtTelefone);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTelefone.setBounds(343, 219, 124, 25);
		panel_1_1.add(lblTelefone);

		txtRua = new JTextField();
		txtRua.setHorizontalAlignment(SwingConstants.LEFT);
		txtRua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtRua.setColumns(10);
		txtRua.setBounds(343, 397, 587, 42);
		txtRua.setDocument(new JTextFieldLimit(48));
		panel_1_1.add(txtRua);

		JLabel lblRua = new JLabel("Rua:");
		lblRua.setHorizontalAlignment(SwingConstants.LEFT);
		lblRua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRua.setBounds(343, 361, 77, 25);
		panel_1_1.add(lblRua);

		JLabel lblNumero = new JLabel("Num:");
		lblNumero.setHorizontalAlignment(SwingConstants.LEFT);
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNumero.setBounds(957, 361, 77, 25);
		panel_1_1.add(lblNumero);

		txtNum = new JFormattedTextField((Object) null);
		txtNum.setHorizontalAlignment(SwingConstants.LEFT);
		txtNum.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNum.setColumns(10);
		txtNum.setBounds(957, 397, 60, 42);
		txtNum.setDocument(new JTextFieldLimit(8));
		panel_1_1.add(txtNum);

		txtBairro = new JTextField();
		txtBairro.setHorizontalAlignment(SwingConstants.LEFT);
		txtBairro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtBairro.setColumns(10);
		txtBairro.setBounds(343, 506, 515, 42);
		txtBairro.setDocument(new JTextFieldLimit(18));
		panel_1_1.add(txtBairro);

		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setHorizontalAlignment(SwingConstants.LEFT);
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBairro.setBounds(343, 470, 77, 25);
		panel_1_1.add(lblBairro);

		txtCEP = new JFormattedTextField(new MaskFormatter("#####-###"));
		txtCEP.setHorizontalAlignment(SwingConstants.LEFT);
		txtCEP.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCEP.setColumns(10);
		txtCEP.setBounds(875, 504, 142, 42);
		panel_1_1.add(txtCEP);

		JLabel lblCEP = new JLabel("CEP:");
		lblCEP.setHorizontalAlignment(SwingConstants.LEFT);
		lblCEP.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCEP.setBounds(875, 468, 77, 25);
		panel_1_1.add(lblCEP);

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setHorizontalAlignment(SwingConstants.LEFT);
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCidade.setBounds(343, 654, 77, 25);
		panel_1_1.add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.setHorizontalAlignment(SwingConstants.LEFT);
		txtCidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCidade.setColumns(10);
		txtCidade.setBounds(343, 690, 217, 42);
		txtCidade.setDocument(new JTextFieldLimit(28));
		panel_1_1.add(txtCidade);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.LEFT);
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEstado.setBounds(584, 654, 77, 25);
		panel_1_1.add(lblEstado);

		cbEstado = new JComboBox();
		cbEstado.setModel(new DefaultComboBoxModel(
				new String[] { "AL", "AP", "AM", "BA", "CE", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE",
						"PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", "DF" }));
		cbEstado.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cbEstado.setBounds(584, 693, 55, 34);
		panel_1_1.add(cbEstado);

		txtComplemento = new JTextField();
		txtComplemento.setHorizontalAlignment(SwingConstants.LEFT);
		txtComplemento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(343, 602, 515, 42);
		txtComplemento.setDocument(new JTextFieldLimit(28));
		panel_1_1.add(txtComplemento);

		JLabel lblComplemento = new JLabel("Complemento:");
		lblComplemento.setHorizontalAlignment(SwingConstants.LEFT);
		lblComplemento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblComplemento.setBounds(343, 567, 146, 25);
		panel_1_1.add(lblComplemento);

		txtCCIR = new JFormattedTextField(new MaskFormatter("###########"));
		txtCCIR.setHorizontalAlignment(SwingConstants.LEFT);
		txtCCIR.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCCIR.setColumns(10);
		txtCCIR.setBounds(10, 396, 286, 42);
		panel_1_1.add(txtCCIR);

		JLabel lblCCIR = new JLabel("CCIR:");
		lblCCIR.setHorizontalAlignment(SwingConstants.LEFT);
		lblCCIR.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCCIR.setBounds(10, 365, 77, 25);
		panel_1_1.add(lblCCIR);

		JLabel lblCNPJ_1 = new JLabel("Tipo de Produ\u00E7\u00E3o");
		lblCNPJ_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCNPJ_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCNPJ_1.setBounds(660, 117, 198, 25);
		panel_1_1.add(lblCNPJ_1);

		JSeparator separator_2_2 = new JSeparator();
		separator_2_2.setBounds(325, 337, 1071, 2);
		panel_1_1.add(separator_2_2);

		cbTipoProducao = new JComboBox();
		cbTipoProducao.setModel(new DefaultComboBoxModel(new String[] { "Apicultura", "Avicultura", "Bovinos",
				"Caprinos", "Cogumelos", "Condimentos", "Conservas", "Gr\u00E3os", "HortiFruiti", "Importados",
				"Latic\u00EDnios", "Ovinos", "Outros" }));
		cbTipoProducao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cbTipoProducao.setBounds(657, 148, 237, 34);
		panel_1_1.add(cbTipoProducao);

		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o");
		lblDescricao.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescricao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDescricao.setBounds(652, 198, 106, 25);
		panel_1_1.add(lblDescricao);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_1_1.setBounds(323, 81, 726, 669);
		panel_1_1.add(separator_1_1);

		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_1_1_1.setBounds(639, 81, 442, 257);
		panel_1_1.add(separator_1_1_1);

		JLabel lblNewLabel = new JLabel("Perfil do Produtor");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 1060, 63);
		panel_1_1.add(lblNewLabel);

		JTextPane txtpnNovoProdutor = new JTextPane();
		txtpnNovoProdutor.setText("Novo Produtor");
		txtpnNovoProdutor.setFont(new Font("Tahoma", Font.PLAIN, 45));
		txtpnNovoProdutor.setBounds(285, 10, 376, 61);
		panel.add(txtpnNovoProdutor);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 81, 831, 2);
		panel.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(394, 83, 354, 448);
		panel.add(separator_1);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(10, 749, 1071, 2);
		panel.add(separator_5);

		JButton btnCriarConta = new JButton("Salvar altera\u00E7\u00F5es");
		btnCriarConta.setBounds(377, 771, 347, 46);
		panel.add(btnCriarConta);
		btnCriarConta.setForeground(new Color(255, 255, 255));
		btnCriarConta.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				salvarConta();
			}
		});
		btnCriarConta.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCriarConta.setBackground(new Color(51, 204, 102));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.controlShadow);
		panel_1.setBounds(-1, 0, 1148, 46);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JButton btnSair = new JButton("x");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(frame, "Deseja sair do Cadastro de Produtor?",
						"Close Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					frame.dispose();
				}
			}
		});
		btnSair.setBounds(1093, 0, 55, 46);
		panel_1.add(btnSair);
		btnSair.setForeground(Color.WHITE);
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnSair.setBackground(Color.RED);
		frame.setBounds(100, 100, 1147, 942);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
	}
}
