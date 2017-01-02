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

	private JFrame frmInvenotryItemManager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					swe400 window = new swe400();
					window.frmInvenotryItemManager.setVisible(true);
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
		frmInvenotryItemManager = new JFrame();
		frmInvenotryItemManager.setTitle("Invenotry Item Manager");
		frmInvenotryItemManager.setBounds(100, 100, 635, 405);
		frmInvenotryItemManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInvenotryItemManager.getContentPane().setLayout(null);
		
		JButton button_searchByUPC = new JButton("Search By UPC");
		button_searchByUPC.setIcon(new ImageIcon("/home/drew/Documents/git/swe400_Team1/Pictures/search_icon2.png"));
		button_searchByUPC.setIgnoreRepaint(true);
		button_searchByUPC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_searchByUPC.setBounds(12, 12, 250, 155);
		frmInvenotryItemManager.getContentPane().add(button_searchByUPC);
		
		JButton button = new JButton("New button");
		button.setBounds(373, 211, 250, 155);
		frmInvenotryItemManager.getContentPane().add(button);
		
		JButton button_1 = new JButton("New button");
		button_1.setBounds(12, 211, 250, 155);
		frmInvenotryItemManager.getContentPane().add(button_1);
		
		JButton button_AddItem = new JButton("Add Item");
		button_AddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_AddItem.setIcon(new ImageIcon("/home/drew/Documents/git/swe400_Team1/Pictures/add_item_icon.png"));
		button_AddItem.setBounds(373, 12, 250, 155);
		frmInvenotryItemManager.getContentPane().add(button_AddItem);
	}
}
