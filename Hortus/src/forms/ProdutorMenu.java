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
import javax.swing.ListSelectionModel;

public class ProdutorMenu {

	private JFrame frame;
	private JTable tblListaPedidos;

	/**
	 * 
	 * @return
	 */
	public String[][] fetchData() {
		String[][] mockData = { { "101", "Amit", "670000" }, { "102", "Jai", "780000" },
				{ "101", "Sachin", "700000" } };

		return mockData;
	}

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

	/**
	 * Create the application.
	 */
	public ProdutorMenu() {
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
		panelHistoricoDeCompras.setBounds(0, 45, 426, 755);
		frame.getContentPane().add(panelHistoricoDeCompras);
		panelHistoricoDeCompras.setLayout(null);

		// TABLE
		String[] columnNames = { "ID", "NAME", "QUANTIDADE" };

		tblListaPedidos = new JTable(fetchData(), columnNames);
		tblListaPedidos.setBounds(10, 731, 406, -654);
		tblListaPedidos.setBorder(null);
		tblListaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblListaPedidos.setBackground(new Color(204, 204, 204));
		panelHistoricoDeCompras.add(tblListaPedidos);

		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setBounds(0, 64, 426, 2);
		panelHistoricoDeCompras.add(separator_2_1);

		JTextPane txtpnHistricoDeCompras = new JTextPane();
		txtpnHistricoDeCompras.setBounds(81, 10, 308, 61);
		txtpnHistricoDeCompras.setText("Lista de Pedidos");
		txtpnHistricoDeCompras.setFont(new Font("Tahoma", Font.PLAIN, 35));
		panelHistoricoDeCompras.add(txtpnHistricoDeCompras);

		// TABELA DE PEDIDOS
		String columns[] = { "ID", "NOME", "QUANTIDADE" };

		JTextPane txtpnDashboardDoConsumidor = new JTextPane();
		txtpnDashboardDoConsumidor.setForeground(new Color(255, 255, 255));
		txtpnDashboardDoConsumidor.setBackground(new Color(153, 102, 255));
		txtpnDashboardDoConsumidor.setBounds(446, 56, 314, 73);
		frame.getContentPane().add(txtpnDashboardDoConsumidor);
		txtpnDashboardDoConsumidor.setText("Dashboard");
		txtpnDashboardDoConsumidor.setFont(new Font("Tahoma", Font.BOLD, 55));

		JSeparator separator = new JSeparator();
		separator.setBounds(804, 112, 396, 2);
		frame.getContentPane().add(separator);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(ProdutorMenu.class.getResource("/assets/profile.png")));
		lblNewLabel.setBounds(1135, 56, 55, 55);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblBemVindo = new JLabel("Bem-vindo(a), ");
		lblBemVindo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBemVindo.setForeground(new Color(255, 255, 255));
		lblBemVindo.setBounds(804, 77, 321, 25);
		frame.getContentPane().add(lblBemVindo);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(456, 146, 711, 558);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnAdicionarProduto = new JButton("Adicionar Produto");
		btnAdicionarProduto.setBounds(166, 135, 381, 86);
		btnAdicionarProduto.setForeground(Color.WHITE);
		btnAdicionarProduto.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnAdicionarProduto.setBackground(new Color(51, 204, 102));
		panel.add(btnAdicionarProduto);

		JButton btnGerenciarEstoque = new JButton("Gerenciar Estoque");
		btnGerenciarEstoque.setForeground(Color.WHITE);
		btnGerenciarEstoque.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnGerenciarEstoque.setBackground(new Color(51, 204, 102));
		btnGerenciarEstoque.setBounds(166, 276, 381, 86);
		panel.add(btnGerenciarEstoque);
		frame.setBounds(100, 100, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
	}
}
