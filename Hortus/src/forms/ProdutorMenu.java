package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

// Classes do Hortus
import hortus.HortusException;
import hortus.Produtor;

import javax.swing.ListSelectionModel;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;

public class ProdutorMenu {

// ========================== PROPRIEDADES ============================
	private JFrame frame;
	private JLabel lblBemVindo;
	private JTable table;

	// produtor local
	private static Produtor produtor;
	private static String[][] tableData;

// ========================== MAIN ============================
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProdutorMenu window = new ProdutorMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

// ========================== CONSTRUTORES ============================

	/**
	 * Construtor 1: serve para debugar e executar o main()
	 */
	public ProdutorMenu() {

		initialize();
	}

	/**
	 * Construtor 2: recebe as inforamcoes do produtor que acaba de logar
	 * 
	 * @param produtor
	 * @throws HortusException
	 */
	public ProdutorMenu(Produtor produtor) throws HortusException {
		if (produtor == null)
			throw new HortusException("Erro ao carregar as informa��es do Produtor! Objeto vazio");

		this.produtor = produtorAtual;

		// Exibir formulario
		initialize();
		loadProdutorToForm();
		frame.setVisible(true);
	}

// ========================== METODOS ============================

	/**
	 * loads produtor content into screen
	 * 
	 * @param produtor
	 */
	public void loadProdutorToForm(Produtor produtor) {
		this.lblBemVindo.setText(this.lblBemVindo.getText() + " " + produtor.getNome());
	}

	public String[][] fetchData() {

		// DATA, NOME DO CONSUMIDOR, NOME DO PRODUTO, PRE�O DA COMPRA
		String[][] mockData = { { "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" } };

		return mockData;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 102, 255));
		frame.getContentPane().setLayout(null);

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
		btnSair.setBounds(1145, 0, 55, 46);
		frame.getContentPane().add(btnSair);
		btnSair.setForeground(Color.WHITE);
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnSair.setBackground(Color.RED);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.controlShadow);
		panel_1.setLayout(null);
		panel_1.setBounds(0, 0, 1200, 46);
		frame.getContentPane().add(panel_1);

		// TABLE
		String[] columnNames = { "ID", "NAME", "QUANTIDADE" };

		JSeparator separator = new JSeparator();
		separator.setBounds(804, 112, 396, 2);
		frame.getContentPane().add(separator);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(ProdutorMenu.class.getResource("/assets/profile.png")));
		lblNewLabel.setBounds(1135, 56, 55, 55);
		frame.getContentPane().add(lblNewLabel);

		lblBemVindo = new JLabel("Bem-vindo(a), ");
		lblBemVindo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBemVindo.setForeground(new Color(255, 255, 255));
		lblBemVindo.setBounds(804, 77, 321, 25);
		frame.getContentPane().add(lblBemVindo);

		JPanel panelHistoricoDeCompras = new JPanel();
		panelHistoricoDeCompras.setLayout(null);
		panelHistoricoDeCompras.setBackground(Color.WHITE);
		panelHistoricoDeCompras.setBounds(0, 46, 644, 676);
		frame.getContentPane().add(panelHistoricoDeCompras);

		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setBounds(0, 64, 644, 7);
		panelHistoricoDeCompras.add(separator_2_1);

		JTextPane lbltHistoricoDeVendas = new JTextPane();
		lbltHistoricoDeVendas.setText("Hist\u00F3rico de Vendas");
		lbltHistoricoDeVendas.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lbltHistoricoDeVendas.setEditable(false);
		lbltHistoricoDeVendas.setBounds(153, 0, 347, 61);
		panelHistoricoDeCompras.add(lbltHistoricoDeVendas);

		table = new JTable();
		table.getSelectionModel().addListSelectionListener(selectionEvent -> {
			if (!selectionEvent.getValueIsAdjusting() && selectionEvent.getSource().equals(table.getSelectionModel())) {
				// AQUI INVOCA A TELA DE DETALHES DA VENDA
				DetalhesCompraForm detalhesForm = new DetalhesCompraForm(null);

			}

		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setModel(new DefaultTableModel(fetchData(), new String[] { "Data", "Consumidor", "Nome", "Pre\u00E7o" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(1).setResizable(false);
		table.setBackground(Color.WHITE);

		JScrollPane scrollPane = new JScrollPane(table);

		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 81, 624, 585);
		panelHistoricoDeCompras.add(scrollPane);

		JTextPane txtpnDashboardDoProdutor = new JTextPane();
		txtpnDashboardDoProdutor.setText("Dashboard do Produtor");
		txtpnDashboardDoProdutor.setForeground(Color.WHITE);
		txtpnDashboardDoProdutor.setFont(new Font("Tahoma", Font.BOLD, 40));
		txtpnDashboardDoProdutor.setBackground(new Color(153, 102, 255));
		txtpnDashboardDoProdutor.setBounds(675, 122, 501, 73);
		frame.getContentPane().add(txtpnDashboardDoProdutor);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(685, 205, 475, 376);
		frame.getContentPane().add(panel);

		JButton btnAdicionarProduto = new JButton("Adicionar Produto");
		btnAdicionarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnAdicionarProduto.setForeground(Color.WHITE);
		btnAdicionarProduto.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnAdicionarProduto.setBackground(new Color(51, 204, 102));
		btnAdicionarProduto.setBounds(46, 56, 381, 86);
		panel.add(btnAdicionarProduto);

		JButton btnGerenciarEstoque = new JButton("Gerenciar Estoque");
		btnGerenciarEstoque.setForeground(Color.WHITE);
		btnGerenciarEstoque.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnGerenciarEstoque.setBackground(new Color(51, 204, 102));
		btnGerenciarEstoque.setBounds(46, 195, 381, 86);
		panel.add(btnGerenciarEstoque);
		frame.setBounds(100, 100, 1200, 725);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
	}
}
