package gameoflife;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameOfLifeGiu {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOfLifeGiu window = new GameOfLifeGiu();
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
	public GameOfLifeGiu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 161, 161);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Size:");
		lblNewLabel.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 59, 35, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setText("64");
		textField.setBounds(55, 56, 80, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Game of Life");
		lblNewLabel_1.setFont(new Font("OCR A Extended", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 11, 125, 34);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Start new Game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GameOfLifeRun(Integer.parseInt(textField.getText()));
			}
		});
		btnNewButton.setBounds(10, 88, 125, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
