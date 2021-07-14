package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;

import hortus.Endereco;
import hortus.Produto;
import hortus.Produtor;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class CompraForm {

	private JFrame frame;
	private JButton btnNewButton;
	private JTable table_2;

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

	/**
	 * Create the application.
	 */
	public CompraForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Endereco end = new Endereco("Jacinto Favoreto", "625", "Apto. 31", "Jardim Luftalla", "123132112", "São Carlos", "SP");
		Produtor produtor = new Produtor(1, "Gabriel", "06712148", "61991436969", end, "gabriel@gmail.com", "123456", "1231231", 1, "De São Carlos, sô");
		
		Produto produto1 = new Produto(3, produtor, "Maça GOSTOSA", "Maça com gosto bom", 12.0, 5.99, 'k', "Maça, amor", true);
 		Produto produto2 = new Produto(4, produtor, "Banana MARAVILHOSA", "Macaco gosta banana", 50.0, 2.00, 'k', "Banana, macaco e potassio", false);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{produto1, produto1.getDescricao(), produto1.getNomeProduto(), produto1.isOrganico(), produto1.getQuantidade(), 0.0},
				{produto2, produto2.getDescricao(), produto2.getNomeProduto(), produto2.isOrganico(), produto2.getQuantidade(), 0.0},
			},
			new String[] {
				"ProdutoObject", "Descri\u00E7\u00E3o", "Nome", "Org\u00E2nico", "Quantidade Total", "Quantidade Desejada"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class, Object.class, Object.class, Double.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_2.setBounds(46, 78, 333, 32);
		table_2.setRowSelectionAllowed(true);
		table_2.removeColumn(table_2.getColumnModel().getColumn(0));
		table_2.removeColumn(table_2.getColumnModel().getColumn(0));
		frame.getContentPane().add(table_2);
		
		JButton btnPlusButton = new JButton("+");
		btnPlusButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnPlusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table_2.getSelectedRow();
				if (selectedRow != -1) {
					double novoDesejado = (Double) table_2.getValueAt(selectedRow, 3)+1.0;
					double quantAntiga = (Double) table_2.getValueAt(selectedRow, 2);
					if (quantAntiga != 0) {
						table_2.setValueAt(novoDesejado, selectedRow, 3);
						quantAntiga -= 1;
						table_2.setValueAt(quantAntiga, selectedRow, 2);
					}
				}
			}
		});
		btnPlusButton.setBounds(142, 190, 50, 50);
		frame.getContentPane().add(btnPlusButton);
		
		btnNewButton = new JButton("-");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table_2.getSelectedRow();
				if (selectedRow != -1) {
					double novoValor = (Double) table_2.getValueAt(selectedRow, 3)-1.0;
					double quantAntiga = (Double) table_2.getValueAt(selectedRow, 2);
					if (novoValor >= 0) {
						table_2.setValueAt(novoValor, selectedRow, 3);
						quantAntiga += 1;
						table_2.setValueAt(quantAntiga, selectedRow, 2);
					}
				}
			}
		});
		btnNewButton.setBounds(242, 190, 50, 50);
		frame.getContentPane().add(btnNewButton);
	
 		frame.setVisible(true);
	}
}
