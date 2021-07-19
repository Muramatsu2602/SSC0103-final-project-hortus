package forms;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class PesquisarProdutoresForm {

	// Componentes
	private JFrame frame;
	private JPanel panel;
	private JButton btnSair;
	private JScrollPane scrollPane;
	private JTable table;

	public Object[][] fetchData() {

		// Backend
		// "ProdutorObject", "Descricao", "Nome", "Tipo de Produção", "Cidade"

		// MOCK DATA
		Endereco end1 = new Endereco("Jacinto Favoreto", "625", "Apto. 31", "Jardim Luftalla", "123132112",
				"São Carlos", "SP");
		Produtor produtor1 = new Produtor(1, "Gabriel", "06712148", "61991436969", end1, "gabriel@gmail.com", "123456",
				"1231231", 1, "De São Carlos, sô");
		Endereco end2 = new Endereco("Cesar Ricomi", "324", "Apto. 23", "Jardim Luftalla", "13213132", "Rio de Janeiro",
				"SP");
		Produtor produtor2 = new Produtor(1, "Joaoponeis", "04312127", "321313223", end2, "kenzo@gmail.com", "abcdef",
				"31231223", 2, "Preparando um Bauru pra todos");

		Object[][] MockData = new Object[][] {
				{ produtor1, produtor1.getDescricao(), produtor1.getNome(), produtor1.getTipoProdString(),
						produtor1.getEndereco().getEndCidade() },
				{ produtor2, produtor2.getDescricao(), produtor2.getNome(), produtor2.getTipoProdString(),
						produtor2.getEndereco().getEndCidade() }, };

		return MockData;
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
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
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
