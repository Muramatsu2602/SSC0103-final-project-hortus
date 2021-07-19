package forms;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import hortus.Endereco;
import hortus.Produtor;
import hortus.SGBD;

public class PesquisarProdutoresForm {

	// Componentes
	private JFrame frame;
	private JPanel panel;
	private JButton btnSair;
	private JScrollPane scrollPane;
	private JTable table;

	// Dados
	private static Vector<Produtor> produtores;
	private static Object[][] tableData;

	public Object[][] fetchData() {

		// Backend
		// "ProdutorObject", "Nome", "Descricao", "Tipo de Produção", "Cidade"

		SGBD banco = new SGBD();
		// Querry para pegar todos os produtor do produtor
		
		produtores = banco.getProdutores()

		tableData = new Object[produtores.size()][];

		// "Nome", "Organico", "Unidade", "Preco", "Quantidade"
		
		for (int i = 0; i < produtores.size(); i++) {
			tableData[i] = new Object[] { produtores.get(i).getNomeProduto(),
					produtos.get(i).isOrganico(), getUnidadeString(produtos.get(i).getUnidade()),
					produtos.get(i).getPrecoProduto(), produtos.get(i).getQuantidade(), produtos.get(i).getDescricao()};
		}
		return tableData;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					PesquisarProdutoresForm window = new PesquisarProdutoresForm();
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
	public PesquisarProdutoresForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 102, 255));
		frame.setBounds(100, 100, 743, 707);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setModel(new DefaultTableModel(fetchData(), new String[] { "ProdutorObject", "Nome",
				"Descri\u00E7\u00E3o", "Tipo de Produ\u00E7\u00E3o", "Cidade" }) {
			boolean[] columnEditables = new boolean[] { true, false, false, false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(3).setPreferredWidth(93);
		table.setBounds(24, 135, 696, 549);
		table.getSelectionModel().addListSelectionListener(selectionEvent -> {
			if (!selectionEvent.getValueIsAdjusting() && selectionEvent.getSource().equals(table.getSelectionModel())) {
				// AQUI INVOCA A TELA DE DETALHES DA COMPRA
				CompraForm compraForm = new CompraForm(produtores.get(table.getSelectedRow()));
				compraForm.setVisible(true);
			}
		});

		scrollPane = new JScrollPane(table);
		scrollPane.setToolTipText("clique em qualquer linha para acessar a loja do produtor escolhido");
		scrollPane.setBounds(24, 135, 696, 549);
		frame.getContentPane().add(scrollPane);

		JLabel lblPesquisarProdutores = new JLabel("Pesquisar Produtores");
		lblPesquisarProdutores.setForeground(Color.WHITE);
		lblPesquisarProdutores.setHorizontalAlignment(SwingConstants.CENTER);
		lblPesquisarProdutores.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblPesquisarProdutores.setBounds(24, 67, 696, 49);
		frame.getContentPane().add(lblPesquisarProdutores);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(SystemColor.controlShadow);
		panel.setBounds(0, 0, 743, 46);
		frame.getContentPane().add(panel);

		btnSair = new JButton("x");
		btnSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(frame, "Deseja sair da pesquisa de produtores??",
						"Close Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					frame.dispose();
				}
			}
		});
		btnSair.setForeground(Color.WHITE);
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnSair.setBackground(Color.RED);
		btnSair.setBounds(688, 0, 55, 46);
		panel.add(btnSair);
	}
}
