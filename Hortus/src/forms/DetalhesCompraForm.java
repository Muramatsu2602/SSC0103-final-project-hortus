package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import hortus.Compra;
import hortus.Endereco;
import hortus.Produto;
import hortus.Produtor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class DetalhesCompraForm {
	// ========================== PROPRIEDADES ============================

	// componentes
	private JFrame frame;
	private JTextArea txtDetalhes;

	// ========================== METODOS ============================

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetalhesCompraForm window = new DetalhesCompraForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// ========================== CONSTRUTORES ============================

	/**
	 * Create the application.
	 */
	public DetalhesCompraForm() {
		initialize();
	}

	public DetalhesCompraForm(Compra compra) {

		initialize();
		String strProdutos = new String();
		Produtor produtor = compra.getProdutor();
		Endereco end = compra.getEndereco();
		strProdutos += "                                                          NFC-e\n";
		strProdutos += "              PRODUTOR RURAL " + produtor.getNome().toUpperCase() + "      CCIR: " + produtor.getCcir() + "\n";
		strProdutos += "                     " + end.getEndRua() + ", " + end.getEndNum() + ", " + end.getEndBairro() + "\n";
		strProdutos += "-----------------------------------------------------------------------------------\n";
		strProdutos += "                                Documento Auxiliar da Nota Fiscal\n";
		strProdutos += "                                      de Consumidor Eletronica\n";
		strProdutos += "-----------------------------------------------------------------------------------\n";
		strProdutos += "ITEM           CODIGO               DESCRICAO\n";
		strProdutos += "        QTD          UN X VL.ITEM(R$)                                        TOT.ITEM(R$)\n";
		strProdutos += "-----------------------------------------------------------------------------------\n";
		Map<Produto, Double> produtos = compra.getListaProdutos();
		int i = 1;
		for(Map.Entry<Produto, Double> prod: produtos.entrySet())
		{
			strProdutos += "0".repeat(3 - String.valueOf(i).length()) + i + "               ";
			strProdutos += prod.getKey().getIdProduto() + " ".repeat(27 - String.valueOf(prod.getKey().getIdProduto()).length());
			strProdutos += prod.getKey().getNomeProduto() + "\n";
			System.out.println(12 - String.valueOf(prod.getValue().intValue()).length());
			if (String.valueOf(prod.getValue().intValue()).length() != 1)
				strProdutos += String.format("        %.3f" + " ".repeat(9), prod.getValue());
			else
				strProdutos += String.format("        %.3f" + " ".repeat(12 - String.valueOf(prod.getValue().intValue()).length()), prod.getValue());
			strProdutos += String.format("UN X %.2f" + " ".repeat(67), prod.getKey().getPrecoProduto());
			strProdutos += String.format("%.2f\n", prod.getKey().getPrecoProduto() * prod.getValue());
			i++;
		}
		strProdutos += "-----------------------------------------------------------------------------------\n";
		strProdutos += "Qtde. Total de Itens" + " ".repeat(84 - String.valueOf(i).length()) + i + "\n";
		strProdutos += "Valor Total R$" + " ".repeat(88 - String.valueOf(compra.getValorFinal().intValue()).length()) + String.format("%.2f\n", compra.getValorFinal());
		strProdutos += "FORMA DE PAGAMENTO                                                       VALOR PAGO\n";
		strProdutos += "Outros" + " ".repeat(101 - String.valueOf(compra.getValorFinal().intValue()).length()) + String.format("%.2f\n", compra.getValorFinal());
		this.txtDetalhes.setText(strProdutos);
		this.frame.setVisible(true);
	}

	// ========================== GUI ============================

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 102, 255));
		frame.setBounds(100, 100, 636, 565);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setAlwaysOnTop(true);

		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(SystemColor.controlShadow);
		panel_1.setBounds(0, 0, 636, 46);
		frame.getContentPane().add(panel_1);

		JButton btnSair = new JButton("x");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnSair.setBounds(581, 0, 55, 46);
		panel_1.add(btnSair);
		btnSair.setForeground(Color.WHITE);
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnSair.setBackground(Color.RED);

		JLabel lblNewLabel = new JLabel("Detalhes Da Compra");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(38, 9, 197, 26);
		panel_1.add(lblNewLabel);

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(255, 248, 220));
		panelPrincipal.setBounds(39, 69, 551, 465);
		frame.getContentPane().add(panelPrincipal);
		panelPrincipal.setLayout(null);

		txtDetalhes = new JTextArea();
		txtDetalhes.setBackground(new Color(255, 248, 220));
		txtDetalhes.setEditable(false);
		txtDetalhes.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtDetalhes.setBounds(10, 10, 531, 445);

		JScrollPane scrollPane = new JScrollPane(txtDetalhes);
		scrollPane.setBounds(10, 10, 531, 445);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelPrincipal.add(scrollPane);

	}
}
