package forms;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
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

import hortus.Consumidor;
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
	private static Consumidor consumidor;
	private static Vector<Produtor> produtores;
	private static Object[][] tableData;

	// Metodos
	public static Hashtable<Integer, String> fillTipoProdDictionary() {
		Hashtable<Integer, String> tipoProdDict = new Hashtable<Integer, String>();

		tipoProdDict.put(0, "Apicultura");
		tipoProdDict.put(1, "Avicultura");
		tipoProdDict.put(2, "Bovinos");
		tipoProdDict.put(3, "Caprinos");
		tipoProdDict.put(4, "Cogumelos");
		tipoProdDict.put(5, "Condimentos");
		tipoProdDict.put(6, "Conservas");
		tipoProdDict.put(7, "Graos");
		tipoProdDict.put(8, "HortiFruiti");
		tipoProdDict.put(9, "Laticinios");
		tipoProdDict.put(10, "Ovinos");
		tipoProdDict.put(11, "Outros");

		return tipoProdDict;
	}

	public Object[][] fetchData() {

		// Backend
		// "ProdutorObject", "Nome", "CCIR", "Tipo de Produção", "Cidade"

		SGBD banco = new SGBD();
		// Querry para pegar todos os produtor do produtor
		produtores = new Vector<Produtor>();
		produtores = banco.getAllProdutores();

		// dicionario para tipos de producao
		tableData = new Object[produtores.size()][];
		Hashtable<Integer, String> tipoProdDict = fillTipoProdDictionary();

		// dicionario para os tipos de producao

		for (int i = 0; i < produtores.size(); i++) {
			tableData[i] = new Object[] { null, produtores.get(i).getNome(), produtores.get(i).getCcir(),
					tipoProdDict.get(produtores.get(i).getTipoProd()), produtores.get(i).getEndereco().getEndCidade() };
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
	
	public PesquisarProdutoresForm(Consumidor consumidor) {
		initialize();
		this.consumidor = consumidor;
		frame.setVisible(true);
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
		table.setModel(new DefaultTableModel(fetchData(),
				new String[] { "ProdutorObject", "Nome", "CCIR", "Tipo de Produ\u00E7\u00E3o", "Cidade" }) {
			boolean[] columnEditables = new boolean[] { true, false, false, false, false };

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
				CompraForm compraForm = new CompraForm(produtores.get(table.getSelectedRow()), consumidor);
				//compraForm.setVisible(true);
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
