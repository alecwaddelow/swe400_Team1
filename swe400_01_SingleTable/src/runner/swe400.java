package runner;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class swe400 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					swe400 window = new swe400();
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
	public swe400() {
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
		
		JButton searchByUPC = new JButton("Search By UPC");
		searchByUPC.setIcon(new ImageIcon("/home/drew/Documents/git/swe400_Team1/Pictures/search_icon2.png"));
		searchByUPC.setIgnoreRepaint(true);
		searchByUPC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		searchByUPC.setBounds(12, 12, 192, 118);
		frame.getContentPane().add(searchByUPC);
		
		JButton button = new JButton("New button");
		button.setBounds(246, 142, 192, 118);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("New button");
		button_1.setBounds(12, 142, 192, 118);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("New button");
		button_2.setBounds(246, 12, 192, 118);
		frame.getContentPane().add(button_2);
	}
}
