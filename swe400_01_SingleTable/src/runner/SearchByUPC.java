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
import exceptions.ItemNotFoundException;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

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
		frame.setBounds(100, 100, 441, 320);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Dialog", Font.BOLD, 20));
		springLayout.putConstraint(SpringLayout.NORTH, comboBox, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, comboBox, -248, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, comboBox, 431, SpringLayout.WEST, frame.getContentPane());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Nail", "Tool", "PowerTool", "StripNail"}));
		comboBox.setSelectedIndex(0);
		frame.getContentPane().add(comboBox);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 33, SpringLayout.SOUTH, comboBox);
		springLayout.putConstraint(SpringLayout.EAST, textField, -158, SpringLayout.EAST, frame.getContentPane());
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setForeground(Color.BLACK);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		springLayout.putConstraint(SpringLayout.SOUTH, textField, -38, SpringLayout.NORTH, textArea);
		springLayout.putConstraint(SpringLayout.NORTH, textArea, 167, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, textArea, 0, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textArea, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textArea, 441, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(textArea);
		
		JLabel lblEnterUpc = new JLabel("Enter UPC:");
		springLayout.putConstraint(SpringLayout.NORTH, lblEnterUpc, 51, SpringLayout.SOUTH, comboBox);
		springLayout.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, lblEnterUpc);
		lblEnterUpc.setFont(new Font("Dialog", Font.BOLD, 20));
		springLayout.putConstraint(SpringLayout.WEST, lblEnterUpc, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblEnterUpc);
		
		JButton button_submit = new JButton("Submit");
		button_submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
				String upc = textField.getText();
				try {
					InventoryItem item = InventoryItem.getDetails(upc, comboBox.getSelectedItem().toString());
					textArea.setText(item.toString());
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
