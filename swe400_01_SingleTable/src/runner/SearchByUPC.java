package runner;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class SearchByUPC {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void searchByUPCScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchByUPC window = new SearchByUPC();
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
	public SearchByUPC() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
