package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

import hortus.HortusException;
import hortus.Produto;
import hortus.Produtor;
import hortus.SGBD;

import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;


public class EstoqueProdutorForm {

	// Componentes
	private JFrame frame;
	private JTable table;
	private static JTextField txtNome;
	private static JCheckBox cbOrganico;
	private static JFormattedTextField txtQuantidade;
	private static JComboBox<Object> cbUnidade;
	private static JFormattedTextField txtPreco;
	private static JTextArea txtIngredientes;
	private static JTextArea txtDescricao;

	// Dados
	private static Produto produto;
	private static Produtor produtor;
	private static Object[][] tableData;
	private static Vector<Produto> produtos;
	private static Integer lastSelectedRow;

	/**
	 * Metodos
	 */
	@SuppressWarnings("serial")
	public void salvarProduto(int selectedRowIndex)
	{
		int option = JOptionPane.showConfirmDialog(frame, "Deseja mesmo alterar o produto atual?", "Confirmação de alteração.",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (option == JOptionPane.YES_OPTION) {
			// Atualizar o produto
			SGBD banco = new SGBD();
			System.out.println("Atualiza o da linha "+lastSelectedRow+"\nNome: "+produtos.get(lastSelectedRow).getNomeProduto()+"\nID: "+produtos.get(lastSelectedRow).getIdProduto());
			
			Produto produto = produtos.get(lastSelectedRow);
			produto.setNomeProduto(txtNome.getText());
			produto.setQuantidade(Double.parseDouble(txtQuantidade.getText().replace(',', '.')));
			produto.setPrecoProduto(Double.parseDouble(txtPreco.getText().replace(',', '.')));
			System.out.println("Unidade: "+cbUnidade.getSelectedIndex());
			produto.setUnidade((char)cbUnidade.getSelectedIndex());
			produto.setIngredientes(txtIngredientes.getText());
			produto.setDescricao(txtDescricao.getText());
			produto.setOrganico(cbOrganico.isSelected());
			
			banco.atualizaProduto(produtos.get(lastSelectedRow));
			
			// Atualizar a tabela com o produto atualizado
			table.setModel(new DefaultTableModel(fetchData(),
					new String[] {
						"Nome", "Organico", "Unidade", "Pre\u00E7o", "Quantidade", "Descricao"
					}
				) {
					Class[] columnTypes = new Class[] {
						Object.class, Boolean.class, Object.class, Object.class, Object.class, Object.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
					boolean[] columnEditables = new boolean[] {
						false, false, false, false, false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
			
			// Produto atualizado com sucesso
			showMessageDialog(null, "Produto '" + txtNome.getText() + "' atualizado com sucesso!");
		}
	}
	
	public void cleanFields() {
        txtNome.setText("");
        txtPreco.setText("0");
        txtQuantidade.setText("0");
        cbUnidade.setSelectedIndex(0);
        cbOrganico.setSelected(false);
        txtDescricao.setText("");
        txtIngredientes.setText("");
    }
	
	@SuppressWarnings("serial")
	public void apagarProduto(int selectedRowIndex)
	{
		int option = JOptionPane.showConfirmDialog(frame, "Deseja mesmo apagar o produto atual?", "Confirmação de remoção.",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (option == JOptionPane.YES_OPTION) {
			// Apagar o produto
			SGBD banco = new SGBD();
			Produto produto = produtos.get(lastSelectedRow);
			
			produto.setExcluido(true);
			banco.atualizaProduto(produto);
			
			// Atualizar a tabela com os produtos não excluidos
			table.setModel(new DefaultTableModel(fetchData(),
					new String[] {
						"Nome", "Organico", "Unidade", "Pre\u00E7o", "Quantidade", "Descricao"
					}
				) {
					Class[] columnTypes = new Class[] {
						Object.class, Boolean.class, Object.class, Object.class, Object.class, Object.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
					boolean[] columnEditables = new boolean[] {
						false, false, false, false, false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
			
			// Produto removido com sucesso
			showMessageDialog(null, "Produto '" + txtNome.getText() + "' removido com sucesso!");
			cleanFields();
		}
	}
	
	public static String getUnidadeString(int unidade)
	{
		switch(unidade)
		{
			case 0:
				return "Arroba";
			case 1:
				return "Grama";
			case 2:
				return "Metro";
			case 3:
				return "Quilograma";
			case 4:
				return "Unitario";
		}	
		return null;
	}
	
	public static Object[][] fetchData() {
		
		SGBD banco = new SGBD();
		// Query para pegar todos os produtor do produtor
		
		produtos = banco.getProdutosProdutor(produtor.getId(), false);
		
		tableData = new Object[produtos.size()][];
		
		for (int i = 0; i < produtos.size(); i++) {
			tableData[i] = new Object[] { produtos.get(i).getNomeProduto(),
					produtos.get(i).isOrganico(), getUnidadeString(produtos.get(i).getUnidade()),
					produtos.get(i).getPrecoProduto(), produtos.get(i).getQuantidade(), produtos.get(i).getDescricao()};
		}
		return tableData;
	}

	/**
	 * @param selectedRowIndex
	 */
	public static void loadProductIntoForm(int selectedRowIndex) {
		if(selectedRowIndex != -1)
		{
			lastSelectedRow = selectedRowIndex;
			
			produto = produtos.get(selectedRowIndex); 
			
					
			txtNome.setText(produto.getNomeProduto());
			cbOrganico.setSelected(produto.isOrganico());
			txtQuantidade.setText(Double.toString(produto.getQuantidade()));
			txtPreco.setText(Double.toString(produto.getPrecoProduto()));
			
			cbUnidade.setSelectedIndex(produto.getUnidade());
			txtIngredientes.setText(null);
			txtIngredientes.append(produto.getIngredientes());
			txtDescricao.setText(null);
			txtDescricao.append(produto.getDescricao());
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EstoqueProdutorForm window = new EstoqueProdutorForm(null);
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
	public EstoqueProdutorForm(Produtor produtorAtual) throws HortusException {
		if (produtorAtual == null)
			throw new HortusException("Erro ao carregar as informacoes do Produtor! Objeto vazio");
		
		this.produtor = produtorAtual;

		// Exibir formulario
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		// Formatters
		DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
		df.setGroupingUsed(true);
		df.setDecimalSeparatorAlwaysShown(true);
		NumberFormatter formatter1 = new NumberFormatter(df); // create the formatter
		NumberFormatter formatter2 = new NumberFormatter(); // create the formatter
		formatter2.setAllowsInvalid(false); // must specify that invalid chars are not allowed

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 102, 255));
		frame.getContentPane().setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(SystemColor.controlShadow);
		panel_1.setBounds(0, 0, 1422, 46);
		frame.getContentPane().add(panel_1);

		JButton btnSair = new JButton("x");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(frame, "Deseja sair do Estoque?", "Close Confirmation",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					frame.dispose();
				}
			}
		});
		btnSair.setForeground(Color.WHITE);
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnSair.setBackground(Color.RED);
		btnSair.setBounds(1367, 0, 55, 46);
		panel_1.add(btnSair);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(26, 143, 820, 686);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		table.setModel(new DefaultTableModel(fetchData(),
			new String[] {
				"Nome", "Organico", "Unidade", "Pre\u00E7o", "Quantidade", "Descricao"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Boolean.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getSelectionModel().addListSelectionListener(selectionEvent -> {
			if (!selectionEvent.getValueIsAdjusting() && selectionEvent.getSource().equals(table.getSelectionModel())) {
				// AQUI PREENCHEMOS OS DETALHES DO PRODUTO SELECIONADO NO FORM NA DIREITA
				loadProductIntoForm(table.getSelectedRow());
			}

		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCellSelectionEnabled(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setBounds(10, 81, 624, 585);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 10, 800, 666);
		panel.add(scrollPane);

		JLabel lblNewLabel = new JLabel("Gerenciar Estoque");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setBounds(26, 66, 1386, 67);
		frame.getContentPane().add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(879, 773, 533, 56);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		// SALVAR
		JButton btnSalvarAlteracoes = new JButton("SALVAR");
		btnSalvarAlteracoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvarProduto(table.getSelectedRow());
			}
		});
		btnSalvarAlteracoes.setBounds(31, 10, 153, 35);
		btnSalvarAlteracoes.setForeground(Color.WHITE);
		btnSalvarAlteracoes.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnSalvarAlteracoes.setBackground(new Color(51, 204, 51));
		panel_2.add(btnSalvarAlteracoes);

		// APAGAR
		JButton btnApagarProdutoSelecionado = new JButton("APAGAR");
		btnApagarProdutoSelecionado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apagarProduto(table.getSelectedRow());
			}
		});
		btnApagarProdutoSelecionado.setBounds(329, 10, 183, 35);
		btnApagarProdutoSelecionado.setForeground(Color.WHITE);
		btnApagarProdutoSelecionado.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnApagarProdutoSelecionado.setBackground(Color.RED);
		panel_2.add(btnApagarProdutoSelecionado);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBounds(879, 143, 533, 606);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JTextPane txtpnDetalhesDoProduto = new JTextPane();
		txtpnDetalhesDoProduto.setBounds(135, 5, 282, 37);
		txtpnDetalhesDoProduto.setText("Detalhes do Produto");
		txtpnDetalhesDoProduto.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel_3.add(txtpnDetalhesDoProduto);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 46, 513, 13);
		panel_3.add(separator);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome.setBounds(10, 73, 84, 25);
		panel_3.add(lblNome);

		txtNome = new JTextField();
		txtNome.setHorizontalAlignment(SwingConstants.LEFT);
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNome.setColumns(10);
		txtNome.setBounds(10, 104, 286, 42);
		panel_3.add(txtNome);

		JLabel lbl = new JLabel("Quantidade:");
		lbl.setHorizontalAlignment(SwingConstants.LEFT);
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl.setBounds(10, 170, 140, 25);
		panel_3.add(lbl);

		cbOrganico = new JCheckBox("\u00C9 Org\u00E2nico");
		cbOrganico.setBackground(new Color(255, 255, 255));
		cbOrganico.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cbOrganico.setBounds(326, 104, 193, 42);
		panel_3.add(cbOrganico);

		txtQuantidade = new JFormattedTextField(formatter1);
		txtQuantidade.setHorizontalAlignment(SwingConstants.LEFT);
		txtQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtQuantidade.setColumns(10);
		txtQuantidade.setBounds(10, 203, 106, 42);
		panel_3.add(txtQuantidade);

		cbUnidade = new JComboBox<Object>();
		cbUnidade.setModel(
				new DefaultComboBoxModel<Object>(new String[] { "Arroba", "Grama", "Metro", "Quilograma", "Unit\u00E1rio" }));
		cbUnidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cbUnidade.setBounds(309, 211, 150, 34);
		panel_3.add(cbUnidade);

		JLabel lblUnidade = new JLabel("Unidade:");
		lblUnidade.setHorizontalAlignment(SwingConstants.LEFT);
		lblUnidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUnidade.setBounds(309, 172, 124, 25);
		panel_3.add(lblUnidade);

		txtPreco = new JFormattedTextField(formatter1);
		txtPreco.setToolTipText("Pre\u00E7o na unidade selecionada");
		txtPreco.setHorizontalAlignment(SwingConstants.LEFT);
		txtPreco.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPreco.setColumns(10);
		txtPreco.setBounds(135, 203, 150, 42);
		panel_3.add(txtPreco);

		JLabel lblPreco = new JLabel("Pre\u00E7o:");
		lblPreco.setHorizontalAlignment(SwingConstants.LEFT);
		lblPreco.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPreco.setBounds(135, 167, 77, 25);
		panel_3.add(lblPreco);

		txtIngredientes = new JTextArea();
		txtIngredientes.setLineWrap(true);
		txtIngredientes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtIngredientes.setBackground(SystemColor.controlHighlight);
		txtIngredientes.setBounds(10, 308, 202, 288);
		panel_3.add(txtIngredientes);

		JLabel lblIngredientes = new JLabel("Ingredientes:");
		lblIngredientes.setHorizontalAlignment(SwingConstants.LEFT);
		lblIngredientes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIngredientes.setBounds(10, 268, 150, 25);
		panel_3.add(lblIngredientes);

		txtDescricao = new JTextArea();
		txtDescricao.setLineWrap(true);
		txtDescricao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDescricao.setBackground(SystemColor.controlHighlight);
		txtDescricao.setBounds(246, 308, 277, 288);
		panel_3.add(txtDescricao);

		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
		lblDescricao.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescricao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDescricao.setBounds(246, 268, 277, 25);
		panel_3.add(lblDescricao);
		frame.setBounds(100, 100, 1422, 839);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}
