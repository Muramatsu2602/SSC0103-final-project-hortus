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
import hortus.Produto;

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
		Map<Produto, Double> produtos = compra.getListaProdutos();
		for(Map.Entry<Produto, Double> prod: produtos.entrySet())
		{
			strProdutos += prod.getKey().getNomeProduto() + "\n\n";
			strProdutos += String.format("Quantidade: %.3f\n", prod.getValue());
			strProdutos += String.format("Preço: %.2f X %.3f = %.2f\n", prod.getKey().getPrecoProduto(), prod.getValue(),
					prod.getKey().getPrecoProduto() * prod.getValue());
			strProdutos += prod.getKey().getDescricao() + "\n";
			strProdutos += "===================================================================\n\n";
		}
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
		panelPrincipal.setBackground(Color.WHITE);
		panelPrincipal.setBounds(39, 69, 551, 465);
		frame.getContentPane().add(panelPrincipal);
		panelPrincipal.setLayout(null);

		txtDetalhes = new JTextArea();
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
