package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import hortus.Endereco;
import hortus.Produtor;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;

public class PesquisarProdutoresForm {

	private JFrame frame;
	private JTable table;
	
	public Object[][] fetchData() {

		// Backend

		// MOCK DATA
		Endereco end1 = new Endereco("Jacinto Favoreto", "625", "Apto. 31", "Jardim Luftalla", "123132112", "São Carlos",
				"SP");
		Produtor produtor1 = new Produtor(1, "Gabriel", "06712148", "61991436969", end1, "gabriel@gmail.com", "123456",
				"1231231", 1, "De São Carlos, sô");
		Endereco end2 = new Endereco("Cesar Ricomi", "324", "Apto. 23", "Jardim Luftalla", "13213132", "Rio de Janeiro",
				"SP");
		Produtor produtor2 = new Produtor(1, "Kenzo", "04312127", "321313223", end2, "kenzo@gmail.com", "abcdef",
				"31231223", 2, "Preparando um Bauru pra todos");
		
		Object[][] MockData = new Object[][] {
				{ produtor1, produtor1.getDescricao(), produtor1.getNome(), produtor1.getTipoProdString(),
						produtor1.getEndereco().getEndCidade()},
				{ produtor2, produtor2.getDescricao(), produtor2.getNome(), produtor2.getTipoProdString(),
							produtor2.getEndereco().getEndCidade()}, };

		return MockData;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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
		frame.setBounds(100, 100, 743, 518);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		table = new JTable() {
			public String getToolTipText(MouseEvent e) {
				String tip = null;
		        java.awt.Point p = e.getPoint();
		        int rowIndex = rowAtPoint(p);
		        if (rowIndex >= 0)
			        tip = table.getModel().getValueAt(rowIndex,1).toString();
				return tip;
			}
		};
		table.setBounds(51, 89, 634, 390);
		frame.getContentPane().add(table);
		
		table.setModel(new DefaultTableModel(fetchData(), new String[] { "ProdutorObject", "Descri\u00E7\u00E3o",
				"Nome", "Tipo de Produção", "Cidade" }) {
			Class[] columnTypes = new Class[] { Object.class, String.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.removeColumn(table.getColumnModel().getColumn(0));
		table.removeColumn(table.getColumnModel().getColumn(0));
		table.setRowSelectionAllowed(true);
		
		JLabel lblNewLabel = new JLabel("Pesquisar Produtor");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblNewLabel.setBounds(80, 23, 590, 49);
		frame.getContentPane().add(lblNewLabel);
	}
}
