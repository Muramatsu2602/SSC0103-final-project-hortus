package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JTextPane;

import hortus.Compra;
import hortus.Consumidor;
import hortus.HortusException;
import hortus.Produtor;
import hortus.SGBD;

import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConsumidorMenu {

	// COMPONENTS
	private JFrame frame;
	private JScrollPane scrollPane;
	private JTable table;

	// DATA
	private static Consumidor consumidor;
	private static String[][] tableData;

	/**
	 * 
	 * @return
	 */
	public String[][] fetchData() {

		// DATA, NOME DO PRODUTOR, NOME DO PRODUTO, PRE�O DA COMPRA
		String[][] mockData = { { "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/2019", "Joao Da Silva", "Rucula", "R$200" },
				{ "25/08/9998", "Joao Da Silva", "Rucula", "R$200" } };

		return mockData;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsumidorMenu window = new ConsumidorMenu();
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
	public ConsumidorMenu() {
		initialize();
	}

	public ConsumidorMenu(Consumidor consumidorAtual) throws HortusException {

		if (consumidorAtual == null)
			throw new HortusException("Erro ao carregar as informa��es do Produtor! Objeto vazio");

		consumidor = consumidorAtual;

		// Carregar informacoes do usuario nos componentes desta tela

		// Exibir formulario
		initialize();
		loadConsumidorToForm();

		frame.setVisible(true);
	}

	/**
	 * loads consumidor content into screen
	 * 
	 * @param produtor
	 */
	public void loadConsumidorToForm() {
		SGBD banco = new SGBD();
		// Agora, buscar todas as informa��es necess�rias para a tela de
		// consumidor

		// 1. Hist�rico de compras
		Vector<Compra> compras = banco.getComprasByConsumidor(consumidor.getId());

		for (int i = 0; i < compras.size(); i++) {
			System.out
					.println("COMPRA: " + compras.get(i).getIdCompra() + " na data: " + compras.get(i).getDataCompra());
		}
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

		JPanel panelHistoricoDeCompras = new JPanel();
		panelHistoricoDeCompras.setBackground(new Color(255, 255, 255));
		panelHistoricoDeCompras.setBounds(0, 45, 644, 676);
		frame.getContentPane().add(panelHistoricoDeCompras);
		panelHistoricoDeCompras.setLayout(null);

		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setBounds(0, 64, 644, 7);
		panelHistoricoDeCompras.add(separator_2_1);

		JTextPane txtpnHistricoDeCompras = new JTextPane();
		txtpnHistricoDeCompras.setEditable(false);
		txtpnHistricoDeCompras.setBounds(153, 0, 347, 61);
		txtpnHistricoDeCompras.setText("Hist\u00F3rico de Compras");
		txtpnHistricoDeCompras.setFont(new Font("Tahoma", Font.PLAIN, 35));
		panelHistoricoDeCompras.add(txtpnHistricoDeCompras);

		table = new JTable();
		table.getSelectionModel().addListSelectionListener(selectionEvent -> {
			if (!selectionEvent.getValueIsAdjusting() && selectionEvent.getSource().equals(table.getSelectionModel())) {
				// AQUI INVOCA A TELA DE DETALHES DA COMPRA
				DetalhesCompraForm detalhesForm = new DetalhesCompraForm(null);

			}

		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setModel(new DefaultTableModel(fetchData(), new String[] { "Data", "Produtor", "Nome", "Pre\u00E7o" }) {
			boolean[] columnEditables = new boolean[] { true, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(1).setResizable(false);
		table.setBackground(Color.WHITE);

		scrollPane = new JScrollPane(table);

		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 81, 624, 585);
		panelHistoricoDeCompras.add(scrollPane);

		JTextPane txtpnDashboardDoConsumidor = new JTextPane();
		txtpnDashboardDoConsumidor.setForeground(new Color(255, 255, 255));
		txtpnDashboardDoConsumidor.setBackground(new Color(153, 102, 255));
		txtpnDashboardDoConsumidor.setBounds(770, 139, 314, 73);
		frame.getContentPane().add(txtpnDashboardDoConsumidor);
		txtpnDashboardDoConsumidor.setText("Dashboard");
		txtpnDashboardDoConsumidor.setFont(new Font("Tahoma", Font.BOLD, 55));

		JSeparator separator = new JSeparator();
		separator.setBounds(804, 112, 396, 2);
		frame.getContentPane().add(separator);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(ConsumidorMenu.class.getResource("/assets/profile.png")));
		lblNewLabel.setBounds(1135, 56, 55, 55);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblBemVindo = new JLabel("Bem-vindo(a), ");
		lblBemVindo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBemVindo.setForeground(new Color(255, 255, 255));
		lblBemVindo.setBounds(804, 77, 321, 25);
		frame.getContentPane().add(lblBemVindo);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(689, 222, 475, 376);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnPesquisarProdutor = new JButton("Pesquisar Produtores");
		btnPesquisarProdutor.setForeground(Color.WHITE);
		btnPesquisarProdutor.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnPesquisarProdutor.setBackground(new Color(51, 204, 102));
		btnPesquisarProdutor.setBounds(46, 138, 381, 86);
		panel.add(btnPesquisarProdutor);

		frame.setBounds(100, 100, 1200, 721);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
	}
}
