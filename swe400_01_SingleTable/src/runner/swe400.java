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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

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
		frmInvenotryItemManager.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				frmInvenotryItemManager.revalidate();
				frmInvenotryItemManager.repaint();
			}
		});
		frmInvenotryItemManager.setTitle("Inventory Item Manager");
		frmInvenotryItemManager.setBounds(100, 100, 550, 405);
		frmInvenotryItemManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInvenotryItemManager.getContentPane().setLayout(null);
		
		JButton button_searchByUPC = new JButton("Search By UPC");
		button_searchByUPC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				SearchByUPC.searchByUPCScreen();
			}
		});
		button_searchByUPC.setIcon(new ImageIcon("Pictures/search_icon2.png"));
		button_searchByUPC.setIgnoreRepaint(true);
		button_searchByUPC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_searchByUPC.setBounds(12, 12, 250, 155);
		frmInvenotryItemManager.getContentPane().add(button_searchByUPC);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				frmInvenotryItemManager.dispose();
			}
		});
		btnExit.setIcon(new ImageIcon("Pictures/exit_icon.png"));
		btnExit.setBounds(288, 211, 250, 155);
		frmInvenotryItemManager.getContentPane().add(btnExit);
		
		JButton btnUpdateItem = new JButton("Update Item");
		btnUpdateItem.setIcon(new ImageIcon("Pictures/update_item.png"));
		btnUpdateItem.setBounds(12, 211, 250, 155);
		frmInvenotryItemManager.getContentPane().add(btnUpdateItem);
		
		JButton button_AddItem = new JButton("Add Item");
		button_AddItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				AddItemToDB.addItem();
			}
		});
		button_AddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_AddItem.setIcon(new ImageIcon("Pictures/add_item_icon.png"));
		button_AddItem.setBounds(288, 12, 250, 155);
		frmInvenotryItemManager.getContentPane().add(button_AddItem);
		frmInvenotryItemManager.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{frmInvenotryItemManager.getContentPane(), btnExit, btnUpdateItem, button_AddItem, button_searchByUPC}));
	}
}
