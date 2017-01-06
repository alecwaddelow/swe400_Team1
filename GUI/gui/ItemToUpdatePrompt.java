package gui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import domain.*;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ItemToUpdatePrompt 
{

	private JFrame frame;
	private JComboBox comboBox_InventoryItem = new JComboBox();
	private JScrollPane scrollPane = new JScrollPane();
	JPanel panel_InventoryItem = new JPanel();
	JList list_InventoryItem = new JList();
	
	private static List<InventoryItem> inventoryItems = new ArrayList<InventoryItem>();

	/**
	 * Launch the application.
	 */
	public static void updateItemPrompt() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemToUpdatePrompt window = new ItemToUpdatePrompt();
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
	public ItemToUpdatePrompt() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 426, 464);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		comboBox_InventoryItem.setModel(new DefaultComboBoxModel(new String[] {"", "Nail", "Tool", "PowerTool", "StripNail"}));
		comboBox_InventoryItem.setSelectedIndex(0);
		comboBox_InventoryItem.setBounds(12, 12, 402, 33);
		frame.getContentPane().add(comboBox_InventoryItem);
		
		scrollPane.setBounds(12, 56, 392, 336);
		frame.getContentPane().add(scrollPane);
		
		panel_InventoryItem.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setViewportView(panel_InventoryItem);
		
		list_InventoryItem.setSelectedIndex(0);
		panel_InventoryItem.add(list_InventoryItem);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnSubmit.setBounds(176, 400, 117, 25);
		frame.getContentPane().add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCancel.setBounds(297, 400, 117, 25);
		frame.getContentPane().add(btnCancel);
	}
}
