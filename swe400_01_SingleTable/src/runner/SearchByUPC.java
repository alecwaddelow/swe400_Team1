package runner;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import javax.swing.DefaultComboBoxModel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import domain.InventoryItem;
import domain.PowerTool;
import domain.StripNail;
import exceptions.ItemNotFoundException;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JScrollBar;
import java.awt.event.MouseMotionAdapter;

public class SearchByUPC {

	private JFrame frame;
	private JTextField textField;

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
		frame.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				frame.revalidate();
				frame.repaint();
			}
		});
		frame.setBounds(100, 100, 754, 320);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JComboBox comboBox = new JComboBox();
		springLayout.putConstraint(SpringLayout.NORTH, comboBox, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, comboBox, -253, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, comboBox, 0, SpringLayout.EAST, frame.getContentPane());
		comboBox.setFont(new Font("Dialog", Font.BOLD, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Nail", "Tool", "PowerTool", "StripNail"}));
		comboBox.setSelectedIndex(0);
		frame.getContentPane().add(comboBox);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 38, SpringLayout.SOUTH, comboBox);
		springLayout.putConstraint(SpringLayout.EAST, textField, -158, SpringLayout.EAST, frame.getContentPane());
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setForeground(Color.BLACK);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		springLayout.putConstraint(SpringLayout.SOUTH, textField, -38, SpringLayout.NORTH, textArea);
		springLayout.putConstraint(SpringLayout.EAST, textArea, 744, SpringLayout.WEST, frame.getContentPane());
		textArea.setLineWrap(true);
		springLayout.putConstraint(SpringLayout.NORTH, textArea, 167, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, textArea, 0, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(textArea);
		
		JLabel lblEnterUpc = new JLabel("Enter UPC:");
		springLayout.putConstraint(SpringLayout.NORTH, lblEnterUpc, 56, SpringLayout.SOUTH, comboBox);
		springLayout.putConstraint(SpringLayout.WEST, textArea, 0, SpringLayout.WEST, lblEnterUpc);
		springLayout.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, lblEnterUpc);
		lblEnterUpc.setFont(new Font("Dialog", Font.BOLD, 20));
		springLayout.putConstraint(SpringLayout.WEST, lblEnterUpc, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblEnterUpc);
		
		JButton button_submit = new JButton("Submit");
		button_submit.addMouseListener(new MouseAdapter() {
			/**
			 * when the mouse is clicked and released
			 */
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
				String upc = textField.getText();
				try {
					/* get the details of the inventory item */
					InventoryItem item = InventoryItem.getDetails(upc, comboBox.getSelectedItem().toString());
					if(item != null)
					{
						textArea.setText(item.toString());
						if(item instanceof PowerTool)
						{
							ArrayList<StripNail> stripList = ((PowerTool) item).getStripNailList();
							textArea.append("\nWorks With:\n");
							
							/* print the array list of what stripnails work with the power tools */
							for(StripNail stripNail : stripList)
							{
								textArea.append(stripNail.toString() + "\n");
							}							
						}
						else if(item instanceof StripNail)
						{
							ArrayList<PowerTool> powerToolList = ((StripNail) item).getPowerToolList();
							textArea.append("\nWorks with:\n");
							
							/* print the array list of what powertools work witht eh stripnail */
							for(PowerTool powerTool : powerToolList)
							{
								textArea.append(powerTool.toString() + "\n");
							}
						}
					}
					else
					{
						textArea.setText("Error: No Such Item Exists");
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ItemNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_submit.setFont(new Font("Dialog", Font.BOLD, 20));
		springLayout.putConstraint(SpringLayout.NORTH, button_submit, 0, SpringLayout.NORTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, button_submit, -138, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, button_submit, 0, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.EAST, button_submit, -10, SpringLayout.EAST, frame.getContentPane());
		button_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		frame.getContentPane().add(button_submit);
	}
}
