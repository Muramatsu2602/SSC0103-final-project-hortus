package forms;

import java.awt.EventQueue;

import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import java.awt.Font;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import forms.ConsumidorMenu;
import hortus.Produtor;
import hortus.SGBD;
import hortus.Consumidor;
import hortus.HortusException;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.SystemColor;
import java.awt.Canvas;
import java.awt.Button;
import java.awt.Scrollbar;

public class LoginForm {

	private JFrame frmLogin;
	private JTextField txtEmail;
	private JPasswordField txtSenha;
	private JCheckBox ckSouProdutor;

	/**
	 * Métodos
	 */
	@SuppressWarnings("deprecation")
	public void submitForm() {
		
		SGBD banco = new SGBD();  
		
		if(ckSouProdutor.isSelected())
		{
			Produtor produtorLogado;
			// Login como produtor
			try{
				produtorLogado = banco.loginProdutor(txtEmail.getText(), txtSenha.getText());
				
				// Ver se o produtorLogado não é nulo
				if(produtorLogado != null)
				{
					System.out.println("ID logado: "+produtorLogado.getId());
	 				new ProdutorMenu(produtorLogado);
					// showMessageDialog(null, "Login de '" + txtEmail.getText() + "' efetuado com sucesso!");
	 				frmLogin.dispose();
				}
	 		} catch(HortusException err)
	 		{
	 			System.out.println(err.getMessage());
	 		}
			
			// Depois de fazer login, ir para o Form de ProdutorMenu
			
		}
		else
		{
			// Login como Consumidor
			Consumidor consumidorLogado = null;
			try{
				consumidorLogado = banco.loginConsumidor(txtEmail.getText(), txtSenha.getText());
				
				// Ver se o consumidorLogado não é nulo
				if(consumidorLogado != null)
				{
					System.out.println("ID logado: "+consumidorLogado.getId());
	 				new ConsumidorMenu(consumidorLogado);
					//showMessageDialog(null, "Login de '" + txtEmail.getText() + "' efetuado com sucesso!");
	 				frmLogin.dispose();
				}
	 		} catch(HortusException err)
	 		{
	 			showMessageDialog(null, err.getMessage());
	 		}
			
			// Depois de fazer login, ir para o Form de Pro
			//ConsumidorMenu
		}
		
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm window = new LoginForm();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.getContentPane().setBackground(new Color(153, 102, 255));
		frmLogin.getContentPane().setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.controlShadow);
		panel_1.setBounds(0, 0, 1000, 39);
		frmLogin.getContentPane().add(panel_1);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(622, 0, 378, 600);
		frmLogin.getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnSair = new JButton("x");
		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int option = JOptionPane.showConfirmDialog(frmLogin, "Deseja sair do sistema Hortus?",
						"Close Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});  
		btnSair.setBounds(336, 0, 42, 40);
		panel.add(btnSair);
		btnSair.setBackground(new Color(255, 0, 0));
		btnSair.setForeground(new Color(255, 255, 255));
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 17));

		JTextPane txtpnLogin = new JTextPane();
		txtpnLogin.setBounds(131, 53, 128, 61);
		txtpnLogin.setFont(new Font("Tahoma", Font.PLAIN, 45));
		txtpnLogin.setText("Login");
		panel.add(txtpnLogin);

		txtEmail = new JTextField();
		txtEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
//				String email = txtEmail.getText();
//				if (!email.contains("@")) {
//					showMessageDialog(null, "E-mail inserido está incorreto!");
//				}
			}
		});
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtEmail.setBounds(45, 209, 286, 42);
		txtEmail.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(txtEmail);
		txtEmail.setColumns(10);

		txtSenha = new JPasswordField();
		txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSenha.setBounds(45, 310, 286, 42);
		panel.add(txtSenha);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(45, 173, 85, 25);
		panel.add(lblEmail);
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(45, 275, 62, 25);
		panel.add(lblSenha);
		lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JButton btnLogin = new JButton("Entrar");
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// verificando se pelo menos um dos campos está vazio e/ou incompleto
				if (txtSenha.getText().isBlank() || txtEmail.getText().isBlank()) {
					showMessageDialog(null, "Há campo(s) vazio(s)");
					return;
				}

				submitForm();
			}
		});
		btnLogin.setBackground(new Color(51, 204, 102));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLogin.setBounds(117, 416, 142, 42);
		panel.add(btnLogin);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 124, 358, 2);
		panel.add(separator);

		JButton btnNovoConsumidor = new JButton("Novo Consumidor");
		btnNovoConsumidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// this.dispose();//to close the current jframe

				try {
					CadastroConsumidorForm consumidor = new CadastroConsumidorForm();
					consumidor.setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNovoConsumidor.setForeground(new Color(255, 255, 255));
		btnNovoConsumidor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNovoConsumidor.setBackground(new Color(153, 102, 255));
		btnNovoConsumidor.setBounds(93, 514, 199, 33);
		panel.add(btnNovoConsumidor);

		JButton btnNovoProdutor = new JButton("Novo Produtor");
		btnNovoProdutor.setForeground(new Color(255, 255, 255));
		btnNovoProdutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// this.dispose();//to close the current jframe

				try {
					CadastroProdutorForm produtor = new CadastroProdutorForm();
					produtor.setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNovoProdutor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNovoProdutor.setBackground(new Color(153, 102, 255));
		btnNovoProdutor.setBounds(103, 557, 176, 33);
		panel.add(btnNovoProdutor);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 491, 378, 2);
		panel.add(separator_1);

		ckSouProdutor = new JCheckBox("Sou Produtor Rural");
		ckSouProdutor.setBackground(Color.WHITE);
		ckSouProdutor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ckSouProdutor.setBounds(45, 368, 176, 21);
		panel.add(ckSouProdutor);

		JTextPane txtpnDesignedAnd = new JTextPane();
		txtpnDesignedAnd.setForeground(new Color(255, 255, 255));
		txtpnDesignedAnd.setBackground(new Color(153, 102, 255));
		txtpnDesignedAnd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnDesignedAnd.setText("Designed and programmed with love by USP students ;(");
		txtpnDesignedAnd.setBounds(10, 571, 372, 19);
		frmLogin.getContentPane().add(txtpnDesignedAnd);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(LoginForm.class.getResource("/assets/logo-login.png")));
		lblNewLabel.setBounds(125, 119, 357, 292);
		frmLogin.getContentPane().add(lblNewLabel);

		JTextArea txtrHortus = new JTextArea();
		txtrHortus.setForeground(new Color(255, 255, 255));
		txtrHortus.setBackground(new Color(153, 102, 255));
		txtrHortus.setFont(new Font("Tahoma", Font.BOLD, 53));
		txtrHortus.setText("Hortus");
		txtrHortus.setBounds(222, 408, 210, 60);
		frmLogin.getContentPane().add(txtrHortus);

		JTextArea txtrDaHorta = new JTextArea();
		txtrDaHorta.setForeground(new Color(255, 255, 255));
		txtrDaHorta.setBackground(new Color(153, 102, 255));
		txtrDaHorta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtrDaHorta.setText("Da horta \u00E0 mesa, sem terceiros");
		txtrDaHorta.setBounds(171, 475, 331, 39);
		frmLogin.getContentPane().add(txtrDaHorta);
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 1000, 600);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.setUndecorated(true);
		frmLogin.setLocationRelativeTo(null);
	}
}
