package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;

public class CadastroProdutorForm {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroProdutorForm window = new CadastroProdutorForm();
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
	public CadastroProdutorForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 102, 255));
		frame.getContentPane().setLayout(null);
		
		JButton btnSair = new JButton("x");
		btnSair.setForeground(Color.WHITE);
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnSair.setBackground(Color.RED);
		btnSair.setBounds(857, 0, 48, 46);
		frame.getContentPane().add(btnSair);
		
		JPanel panel = new JPanel();
		panel.setBounds(-1, 0, 906, 46);
		frame.getContentPane().add(panel);
		frame.setBounds(100, 100, 905, 653);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
	}

}
