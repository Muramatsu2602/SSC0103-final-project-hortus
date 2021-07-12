package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField$AbstractFormatter;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JCheckBox;

public class CadastroProduto {

	private JFrame frame;
	private final JPanel panel_1 = new JPanel();
	private JTextField txtNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroProduto window = new CadastroProduto();
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
	public CadastroProduto() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(123, 104, 238));
		frame.getContentPane().setLayout(null);

		JButton btnSair = new JButton("x");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(frame, "Deseja sair do cadastro de Produto?",
						"Close Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		btnSair.setForeground(Color.WHITE);
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnSair.setBackground(Color.RED);
		btnSair.setBounds(902, 0, 48, 46);
		frame.getContentPane().add(btnSair);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 950, 46);
		frame.getContentPane().add(panel);
		panel_1.setBounds(49, 75, 854, 570);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		
		JLabel lblNome_1_4_3 = new JLabel("*");
		lblNome_1_4_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome_1_4_3.setForeground(Color.RED);
		lblNome_1_4_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome_1_4_3.setBounds(576, 220, 77, 25);
		panel_1.add(lblNome_1_4_3);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblQuantidade.setBounds(527, 113, 133, 25);
		panel_1.add(lblQuantidade);
		JFormattedTextField txtQuantidade = new JFormattedTextField(formatter);
		txtQuantidade.setHorizontalAlignment(SwingConstants.LEFT);
		txtQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtQuantidade.setColumns(10);
		txtQuantidade.setBounds(527, 148, 106, 42);
		panel_1.add(txtQuantidade);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(333, 255, 518, 2);
		panel_1.add(separator_2);
		
		JComboBox cbUnidade = new JComboBox();
		cbUnidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cbUnidade.setBounds(670, 151, 150, 34);
		panel_1.add(cbUnidade);
		
		JLabel lblNome_1_8 = new JLabel("*");
		lblNome_1_8.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome_1_8.setForeground(Color.RED);
		lblNome_1_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome_1_8.setBounds(440, 284, 48, 25);
		panel_1.add(lblNome_1_8);
		
		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
		lblDescricao.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescricao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDescricao.setBounds(343, 284, 358, 25);
		panel_1.add(lblDescricao);
		
		JTextArea txtDescricao = new JTextArea();
		txtDescricao.setText("Escreva aqui os detalhes de seu produto...");
		txtDescricao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDescricao.setBackground(SystemColor.controlHighlight);
		txtDescricao.setBounds(343, 324, 498, 163);
		panel_1.add(txtDescricao);
		
		JCheckBox ckOrganico = new JCheckBox("\u00C9 um Produto Org\u00E2nico");
		ckOrganico.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ckOrganico.setBackground(Color.WHITE);
		ckOrganico.setBounds(343, 214, 259, 34);
		panel_1.add(ckOrganico);
		
		JFormattedTextField txtPreco = new JFormattedTextField((AbstractFormatter) null);
		txtPreco.setHorizontalAlignment(SwingConstants.LEFT);
		txtPreco.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPreco.setColumns(10);
		txtPreco.setBounds(343, 148, 150, 42);
		panel_1.add(txtPreco);
		
		JTextPane lblCadastro = new JTextPane();
		lblCadastro.setText("Novo Produto");
		lblCadastro.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblCadastro.setBounds(288, 10, 376, 61);
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
		
		JLabel lblPreco = new JLabel("Pre\u00E7o:");
		lblPreco.setHorizontalAlignment(SwingConstants.LEFT);
		lblPreco.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPreco.setBounds(343, 112, 77, 25);
		panel_1.add(lblPreco);
		
		JLabel lblNome_1 = new JLabel("*");
		lblNome_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome_1.setForeground(Color.RED);
		lblNome_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome_1.setBounds(73, 113, 77, 25);
		panel_1.add(lblNome_1);
		
		JLabel lblNome_1_4 = new JLabel("*");
		lblNome_1_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome_1_4.setForeground(Color.RED);
		lblNome_1_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome_1_4.setBounds(407, 113, 77, 25);
		panel_1.add(lblNome_1_4);
		
		JLabel lblNome_1_5 = new JLabel("*Campos obrigat\u00F3rios");
		lblNome_1_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome_1_5.setForeground(Color.RED);
		lblNome_1_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome_1_5.setBounds(342, 534, 217, 25);
		panel_1.add(lblNome_1_5);
		
		JLabel lblUnidade = new JLabel("Unidade:");
		lblUnidade.setHorizontalAlignment(SwingConstants.LEFT);
		lblUnidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUnidade.setBounds(670, 112, 124, 25);
		panel_1.add(lblUnidade);
		
		JButton btnCriarConta = new JButton("Cadastrar Produto");
		btnCriarConta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCriarConta.setBackground(new Color(51, 204, 102));
		btnCriarConta.setBounds(608, 525, 236, 42);
		panel_1.add(btnCriarConta);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(333, 81, 528, 486);
		panel_1.add(separator_1);
		
		NumberFormatter formatter = new NumberFormatter(); //create the formatter
		formatter.setAllowsInvalid(false);
		
		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setBounds(0, 307, 333, 2);
		panel_1.add(separator_2_1);
		
		JLabel lblIngredientes = new JLabel("Ingredientes:");
		lblIngredientes.setHorizontalAlignment(SwingConstants.LEFT);
		lblIngredientes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIngredientes.setBounds(10, 329, 358, 25);
		panel_1.add(lblIngredientes);
		
		JTextArea txtrInsiraCadaIngrediente = new JTextArea();
		txtrInsiraCadaIngrediente.setText("Insira cada ingrediente, um por linha");
		txtrInsiraCadaIngrediente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtrInsiraCadaIngrediente.setBackground(SystemColor.controlHighlight);
		txtrInsiraCadaIngrediente.setBounds(10, 364, 286, 195);
		panel_1.add(txtrInsiraCadaIngrediente);
		
		JLabel lblNome_1_8_2 = new JLabel("*");
		lblNome_1_8_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome_1_8_2.setForeground(Color.RED);
		lblNome_1_8_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome_1_8_2.setBounds(135, 329, 48, 25);
		panel_1.add(lblNome_1_8_2);
		
		JLabel lblNome_1_4_1 = new JLabel("*");
		lblNome_1_4_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome_1_4_1.setForeground(Color.RED);
		lblNome_1_4_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome_1_4_1.setBounds(637, 113, 77, 25);
		panel_1.add(lblNome_1_4_1);
		
		JLabel lblNome_1_4_2 = new JLabel("*");
		lblNome_1_4_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome_1_4_2.setForeground(Color.RED);
		lblNome_1_4_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome_1_4_2.setBounds(764, 113, 77, 25);
		panel_1.add(lblNome_1_4_2);
		frame.setBounds(100, 100, 950, 675);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
	}
}
