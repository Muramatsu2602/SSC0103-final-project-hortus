package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;

public class EstoqueProdutorForm {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EstoqueProdutorForm window = new EstoqueProdutorForm();
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
	public EstoqueProdutorForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 800, 666);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setBounds(0, 0, 1, 1);
		panel.add(table);
		
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
		
		JButton btnSalvarAlteracoes = new JButton("SALVAR");
		btnSalvarAlteracoes.setBounds(31, 10, 153, 35);
		btnSalvarAlteracoes.setForeground(Color.WHITE);
		btnSalvarAlteracoes.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnSalvarAlteracoes.setBackground(new Color(51, 204, 51));
		panel_2.add(btnSalvarAlteracoes);
		
		JButton btnApagarProdutoSelecionado = new JButton("APAGAR");
		btnApagarProdutoSelecionado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		txtpnDetalhesDoProduto.setBounds(135, 5, 282, 43);
		txtpnDetalhesDoProduto.setText("Detalhes do Produto");
		txtpnDetalhesDoProduto.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel_3.add(txtpnDetalhesDoProduto);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 58, 513, 13);
		panel_3.add(separator);
		frame.setBounds(100, 100, 1422, 839);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
	}

}
