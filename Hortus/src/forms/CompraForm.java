package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import hortus.Compra;
import hortus.Consumidor;
import hortus.Endereco;
import hortus.HortusException;
import hortus.Produto;
import hortus.Produtor;
import hortus.SGBD;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class CompraForm {

	// COMPONENTES
	private JFrame frame;
	private JButton btnLess;
	private JTable tblProdutosLoja;
	private JPanel panel;
	private JButton btnSair;
	private JTable tblCarrinho;
	private JPanel panel_1;
	private JLabel lblCarrinho;
	private JLabel lblProdutosDisponveis;
	private JPanel panel_2;
	private JButton btnAdicionarNoCarrinho;
	private JButton btnFinalizarCompra;
	private JPanel panel_3;
	private JLabel lblNewLabel;
	private JLabel lblValorTotal;
	private JScrollPane scrollLoja;
	private JScrollPane scrollCarrinho;

	// DADOS
	private static Produtor produtor;
	private static Consumidor consumidor;
	private static Vector<Produto> produtosLoja;
	private int produtoSelecionado;
	private static Object[][] lojaData;
	private static Object[][] carrinhoData;
	private static Map<Produto, Double> produtosCarrinho;
	
	public String isOrganico(boolean organico) {
		if (organico)
			return "Sim";
		else
			return "Não";
	}

	public Object[][] fetchDataCarrinho() {

        // Backend
		SGBD banco = new SGBD();
		
		Object[][] tableData = new Object[produtosCarrinho.size()][];
		int i = 0;
		Produto produtoAux;

		// Nome, quantidade, preço unitario, total
		for(Map.Entry<Produto, Double> entry: produtosCarrinho.entrySet())
		{
			produtoAux = entry.getKey();
			tableData[i] = new Object[] { produtoAux.getNomeProduto(), entry.getValue(), produtoAux.getPrecoProduto(), (entry.getValue() * produtoAux.getPrecoProduto())};
			i++;
		}
		
		return tableData;   

		/*
        // MOCK DATA
        Endereco end = new Endereco("Jacinto Favoreto", "625", "Apto. 31", "Jardim Luftalla", "123132112", "São Carlos",
                "SP");
        Produtor produtor = new Produtor(1, "Gabriel", "06712148", "61991436969", end, "gabriel@gmail.com", "123456",
                "1231231", 1, "De São Carlos, sô");
        Produto produto1 = new Produto(3, produtor, "Maçã GOSTOSA", "Maçã com gosto bom", 12.0, 5.99, 'k', "Maçã, amor",
                true);
        Produto produto2 = new Produto(4, produtor, "Banana MARAVILHOSA", "Macaco gosta banana", 50.0, 2.00, 'k',
                "Banana, macaco e potassio", false);
        
        

        Object[][] MockData = new Object[][] {
                { produto1, produto1.getDescricao(), produto1.getNomeProduto(), isOrganico(produto1.isOrganico()),
                        produto1.getQuantidade(), 0.0 },
                { produto2, produto2.getDescricao(), produto2.getNomeProduto(), isOrganico(produto2.isOrganico()),
                        produto2.getQuantidade(), 0.0 }, };

        return MockData;
        */
    }

	/**
	 * carrega os dados dos produtos vendidos pelo Produtor em uma tabela
	 * 
	 * @return
	 */
	public Object[][] fetchDataProdutos() {

        // Backend
		SGBD banco = new SGBD();
		this.produtosLoja = banco.getProdutosProdutor(produtor.getId(), false);
		
		Object[][] tableData = new Object[produtosLoja.size()][];

		// DATA, NOME DO PRODUTOR, NOME DO PRODUTO, PREï¿½O DA COMPRA
		for (int i = 0; i < produtosLoja.size(); i++) {
			tableData[i] = new Object[] { produtosLoja.get(i), produtosLoja.get(i).getNomeProduto(), produtosLoja.get(i).getDescricao(),
					isOrganico(produtosLoja.get(i).isOrganico()), Double.toString(produtosLoja.get(i).getPrecoProduto()), Double.toString(produtosLoja.get(i).getQuantidade()),
					"" };
		}
		return tableData;   

		/*
        // MOCK DATA
        Endereco end = new Endereco("Jacinto Favoreto", "625", "Apto. 31", "Jardim Luftalla", "123132112", "São Carlos",
                "SP");
        Produtor produtor = new Produtor(1, "Gabriel", "06712148", "61991436969", end, "gabriel@gmail.com", "123456",
                "1231231", 1, "De São Carlos, sô");
        Produto produto1 = new Produto(3, produtor, "Maçã GOSTOSA", "Maçã com gosto bom", 12.0, 5.99, 'k', "Maçã, amor",
                true);
        Produto produto2 = new Produto(4, produtor, "Banana MARAVILHOSA", "Macaco gosta banana", 50.0, 2.00, 'k',
                "Banana, macaco e potassio", false);
        
        

        Object[][] MockData = new Object[][] {
                { produto1, produto1.getDescricao(), produto1.getNomeProduto(), isOrganico(produto1.isOrganico()),
                        produto1.getQuantidade(), 0.0 },
                { produto2, produto2.getDescricao(), produto2.getNomeProduto(), isOrganico(produto2.isOrganico()),
                        produto2.getQuantidade(), 0.0 }, };

        return MockData;
        */
    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompraForm window = new CompraForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CompraForm() {
		initialize();
	}
	/**
	 * Create the application.
	 */
	public CompraForm(Produtor produtor, Consumidor consumidor) {
		
		this.produtor = produtor;
		this.consumidor = consumidor;
		System.out.println(produtor.getId());
		
		initialize();
		produtosCarrinho = new HashMap<Produto, Double>();
		frame.setVisible(true);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 102, 255));
		frame.setBounds(100, 100, 1101, 749);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		tblProdutosLoja = new JTable();
		tblProdutosLoja.getSelectionModel().addListSelectionListener(selectionEvent -> {
			if (!selectionEvent.getValueIsAdjusting() && selectionEvent.getSource().equals(tblProdutosLoja.getSelectionModel())) {
				
				produtoSelecionado = tblProdutosLoja.getSelectedRow();

			}

		});
		
		tblProdutosLoja.setModel(new DefaultTableModel(fetchDataProdutos(),
			new String[] {
				"ProdutoObject", "Nome", "Descri\u00E7\u00E3o", "Org\u00E2nico", "Pre\u00E7o por unidade", "Quantidade Total", "Quantidade Desejada"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, String.class, Object.class, Object.class, Double.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, false, false, false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblProdutosLoja.getColumnModel().getColumn(0).setResizable(false);
		tblProdutosLoja.getColumnModel().getColumn(0).setPreferredWidth(0);
		tblProdutosLoja.getColumnModel().getColumn(0).setMinWidth(0);
		tblProdutosLoja.getColumnModel().getColumn(0).setMaxWidth(0);
		tblProdutosLoja.getColumnModel().getColumn(2).setResizable(false);
		tblProdutosLoja.setBounds(482, 125, 397, 525);
		tblProdutosLoja.setRowSelectionAllowed(true);
		tblProdutosLoja.getTableHeader().setReorderingAllowed(false);
		
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        for (int i = 0; i < tblProdutosLoja.getColumnCount(); i++)
        	tblProdutosLoja.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		

		JScrollPane scrollLoja = new JScrollPane(tblProdutosLoja);

		scrollLoja.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollLoja.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollLoja.setBounds(451, 125, 624, 523);
		frame.getContentPane().add(scrollLoja);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(451, 674, 624, 52);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JButton btnMore = new JButton("+");
		btnMore.setForeground(new Color(255, 255, 255));
		btnMore.setBackground(new Color(51, 204, 51));
		btnMore.setBounds(27, 10, 50, 35);
		panel_1.add(btnMore);
		btnMore.setFont(new Font("Tahoma", Font.PLAIN, 22));

		btnLess = new JButton("-");
		btnLess.setForeground(new Color(255, 255, 255));
		btnLess.setBackground(new Color(255, 0, 0));
		btnLess.setBounds(96, 10, 49, 35);
		panel_1.add(btnLess);
		btnLess.setFont(new Font("Tahoma", Font.BOLD, 22));

		btnAdicionarNoCarrinho = new JButton("Adicionar no Carrinho");
		btnAdicionarNoCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tblProdutosLoja.getSelectedRow();
				if (selectedRow != -1) {
					try {
						Produto p = (Produto) tblProdutosLoja.getModel().getValueAt(selectedRow, 0);
						Double qtdSelecionada = Double.parseDouble(tblProdutosLoja.getValueAt(selectedRow, 6).toString());
						if(qtdSelecionada <= 0) {
							JOptionPane.showMessageDialog(null, "Insira uma quantidade válida.");
						} else if(qtdSelecionada > p.getQuantidade()) {
							JOptionPane.showMessageDialog(null, "Quantidade selecionada acima da disponível.");
						} else {
							double precoTotal = qtdSelecionada * p.getPrecoProduto();
							produtosCarrinho.put(p, qtdSelecionada);
							// Tenho que converter do Map produtosCarrinho para o Object[][] carrinhoData para que a tabela do carrinho seja atualizada
							tblCarrinho.setModel(new DefaultTableModel(fetchDataCarrinho(), 
									new String[] { "Nome", "Quantidade", "Preço Unitário", "Total" }));
							
							Double valorTotal = 0.0;
							for(int i = 0; i < tblCarrinho.getRowCount(); i++) {
								valorTotal += Double.parseDouble(tblCarrinho.getValueAt(i, 3).toString());
							}
							
							
							lblValorTotal.setText(String.format("%.2f", valorTotal));
							
								
						}
					} catch(Exception err) {
						JOptionPane.showMessageDialog(null, "Insira uma quantidade válida.");
					}
				}
				else {
					// Aviso que tem que especificar um produto para adicionar ao carrinho
					JOptionPane.showMessageDialog(null, "Selecione um produto para adicionar.");
				}
			}
		});
		btnAdicionarNoCarrinho.setForeground(Color.WHITE);
		btnAdicionarNoCarrinho.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnAdicionarNoCarrinho.setBackground(new Color(51, 204, 255));
		btnAdicionarNoCarrinho.setBounds(395, 11, 219, 35);
		panel_1.add(btnAdicionarNoCarrinho);
		btnLess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tblProdutosLoja.getSelectedRow();
				if (selectedRow != -1) {
					double novoValor = (Double) tblProdutosLoja.getValueAt(selectedRow, 5) - 1.0;
					double quantAntiga = (Double) tblProdutosLoja.getValueAt(selectedRow, 4);
					if (novoValor >= 0) {
						tblProdutosLoja.setValueAt(novoValor, selectedRow, 5);
						quantAntiga += 1;
						tblProdutosLoja.setValueAt(quantAntiga, selectedRow, 4);
					}
				}
			}
		});
		btnMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tblProdutosLoja.getSelectedRow();
				if (selectedRow != -1) {
					double novoDesejado = (Double) tblProdutosLoja.getValueAt(selectedRow, 5) + 1.0;
					double quantAntiga = (Double) tblProdutosLoja.getValueAt(selectedRow, 4);
					if (quantAntiga != 0) {
						tblProdutosLoja.setValueAt(novoDesejado, selectedRow, 5);
						quantAntiga -= 1;
						tblProdutosLoja.setValueAt(quantAntiga, selectedRow, 4);
					}
				}
			}
		});

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(SystemColor.controlShadow);
		panel.setBounds(0, 0, 1101, 46);
		frame.getContentPane().add(panel);

		btnSair = new JButton("x");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(frame, "Deseja sair do Menu de Produtos?",
						"Close Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					frame.dispose();
				}
			}
		});
		btnSair.setBounds(1050, 0, 51, 46);
		panel.add(btnSair);
		btnSair.setForeground(Color.WHITE);
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnSair.setBackground(Color.RED);

		tblCarrinho = new JTable();
		tblCarrinho.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Nome", "Quantidade", "Preço Unitário", "Total" }));
		tblCarrinho.setRowSelectionAllowed(true);
		tblCarrinho.setBounds(23, 125, 397, 466);

		scrollCarrinho = new JScrollPane(tblCarrinho);
		scrollCarrinho.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollCarrinho.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollCarrinho.setBounds(23, 125, 397, 466);

		frame.getContentPane().add(scrollCarrinho);

		lblCarrinho = new JLabel("Carrinho");
		lblCarrinho.setIcon(new ImageIcon(CompraForm.class.getResource("/assets/carrinho.png")));
		lblCarrinho.setForeground(new Color(255, 255, 255));
		lblCarrinho.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarrinho.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblCarrinho.setBounds(23, 73, 397, 46);
		frame.getContentPane().add(lblCarrinho);

		lblProdutosDisponveis = new JLabel("Loja");
		lblProdutosDisponveis.setIcon(new ImageIcon(CompraForm.class.getResource("/assets/loja.png")));
		lblProdutosDisponveis.setForeground(new Color(255, 255, 255));
		lblProdutosDisponveis.setHorizontalAlignment(SwingConstants.CENTER);
		lblProdutosDisponveis.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblProdutosDisponveis.setBounds(451, 73, 624, 46);
		frame.getContentPane().add(lblProdutosDisponveis);

		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(23, 674, 397, 52);
		frame.getContentPane().add(panel_2);

		btnFinalizarCompra = new JButton("Finalizar Compra");
		btnFinalizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//int option = JOptionPane.showConfirmDialog(frame, "Deseja confirmar a compra?",
				//		"Confirmar compra", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				String descricao = JOptionPane.showInputDialog(frame, "Digite uma obervação para o produtor", "Confirmar compra");
				
				if(descricao != null) {
					Compra compra = new Compra(0, consumidor, produtor, produtosCarrinho, produtor.getEndereco(), descricao, null);
					SGBD banco = new SGBD();
					
					banco.insereCompra(compra);
					
					JOptionPane.showMessageDialog(null, "Compra realizada com sucesso.");
				}
				
				/*
				if (option == JOptionPane.YES_OPTION) {
					// Abrir 
				}
				*/
				
			}
		});
		btnFinalizarCompra.setForeground(Color.WHITE);
		btnFinalizarCompra.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnFinalizarCompra.setBackground(new Color(0, 204, 0));
		btnFinalizarCompra.setBounds(79, 10, 219, 35);
		panel_2.add(btnFinalizarCompra);

		panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(23, 601, 397, 49);
		frame.getContentPane().add(panel_3);

		lblNewLabel = new JLabel("TOTAL DA COMPRA: R$");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 199, 29);
		panel_3.add(lblNewLabel);
		
		lblValorTotal = new JLabel("0,00");
		lblValorTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblValorTotal.setBounds(210, 11, 177, 29);
		panel_3.add(lblValorTotal);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
}

