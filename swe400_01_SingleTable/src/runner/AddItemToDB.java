package runner;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.JTextField;

public class AddItemToDB {

	private JFrame frmAddInventoryItem;
	private JTextField textField_Description;

	/**
	 * Launch the application.
	 */
	public static void addItem() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddItemToDB window = new AddItemToDB();
					window.frmAddInventoryItem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddItemToDB() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddInventoryItem = new JFrame();
		frmAddInventoryItem.setTitle("Add Inventory Item");
		frmAddInventoryItem.setBounds(100, 100, 725, 509);
		frmAddInventoryItem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmAddInventoryItem.getContentPane().setLayout(springLayout);
		
		JPanel sharedItemPanel = new JPanel();
		JPanel panel_nail = new JPanel();
		JPanel panel_tool = new JPanel();
		JButton btnSubmit = new JButton("Submit");
		sharedItemPanel.setVisible(true);
		panel_nail.setVisible(false);
		panel_tool.setVisible(false);
		
		
		JComboBox comboBox = new JComboBox();
		springLayout.putConstraint(SpringLayout.NORTH, sharedItemPanel, 6, SpringLayout.SOUTH, comboBox);
		springLayout.putConstraint(SpringLayout.WEST, sharedItemPanel, 0, SpringLayout.WEST, comboBox);
		springLayout.putConstraint(SpringLayout.SOUTH, sharedItemPanel, 440, SpringLayout.SOUTH, comboBox);
		springLayout.putConstraint(SpringLayout.EAST, sharedItemPanel, -353, SpringLayout.EAST, comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String item = comboBox.getSelectedItem().toString();
				
				if(!item.equals(""))
				{
					btnSubmit.setEnabled(true);
					if(item.equals("Nail"))
					{
						panel_tool.setVisible(false);
						panel_nail.setVisible(true);
					}
					else if(item.equals("Tool"))
					{
						panel_nail.setVisible(false);
						panel_tool.setVisible(true);
					}
					else if(item.equals("StripNail"))
					{
						panel_nail.setVisible(false);
						panel_tool.setVisible(false);
					}
					else if(item.equals("PowerTool"))
					{
						panel_nail.setVisible(false);
						panel_tool.setVisible(false);
					}
				}
				else
				{
					panel_nail.setVisible(false);
					panel_tool.setVisible(false);
					btnSubmit.setEnabled(false);
				}
				
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Nail", "Tool", "StripNail", "PowerTool"}));
		comboBox.setSelectedIndex(0);
		springLayout.putConstraint(SpringLayout.NORTH, comboBox, 10, SpringLayout.NORTH, frmAddInventoryItem.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 10, SpringLayout.WEST, frmAddInventoryItem.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, comboBox, 715, SpringLayout.WEST, frmAddInventoryItem.getContentPane());
		frmAddInventoryItem.getContentPane().add(comboBox);
		frmAddInventoryItem.getContentPane().add(sharedItemPanel);
		SpringLayout sl_sharedItemPanel = new SpringLayout();
		sharedItemPanel.setLayout(sl_sharedItemPanel);
		sharedItemPanel.setVisible(true);
		
		JLabel lblNewLabel = new JLabel("UPC");
		sl_sharedItemPanel.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, sharedItemPanel);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		sharedItemPanel.add(lblNewLabel);
		
		JSpinner spinner_upc = new JSpinner();
		sl_sharedItemPanel.putConstraint(SpringLayout.EAST, lblNewLabel, -6, SpringLayout.WEST, spinner_upc);
		sl_sharedItemPanel.putConstraint(SpringLayout.NORTH, spinner_upc, 6, SpringLayout.NORTH, sharedItemPanel);
		sl_sharedItemPanel.putConstraint(SpringLayout.WEST, spinner_upc, 202, SpringLayout.WEST, sharedItemPanel);
		sl_sharedItemPanel.putConstraint(SpringLayout.SOUTH, spinner_upc, -378, SpringLayout.SOUTH, sharedItemPanel);
		sl_sharedItemPanel.putConstraint(SpringLayout.EAST, spinner_upc, -3, SpringLayout.EAST, sharedItemPanel);
		spinner_upc.setModel(new SpinnerNumberModel(new Long(0), null, null, new Long(1)));
		sharedItemPanel.add(spinner_upc);
		
		JLabel lblManufacturerId = new JLabel("Manufacturer ID");
		sl_sharedItemPanel.putConstraint(SpringLayout.NORTH, lblManufacturerId, 209, SpringLayout.NORTH, sharedItemPanel);
		sl_sharedItemPanel.putConstraint(SpringLayout.WEST, lblManufacturerId, 10, SpringLayout.WEST, sharedItemPanel);
		lblManufacturerId.setFont(new Font("Dialog", Font.BOLD, 20));
		sharedItemPanel.add(lblManufacturerId);
		
		JSpinner spinner_manufacturerID = new JSpinner();
		sl_sharedItemPanel.putConstraint(SpringLayout.NORTH, spinner_manufacturerID, 143, SpringLayout.SOUTH, spinner_upc);
		sl_sharedItemPanel.putConstraint(SpringLayout.WEST, spinner_manufacturerID, 6, SpringLayout.EAST, lblManufacturerId);
		sl_sharedItemPanel.putConstraint(SpringLayout.EAST, spinner_manufacturerID, -3, SpringLayout.EAST, sharedItemPanel);
		sharedItemPanel.add(spinner_manufacturerID);
		
		JSpinner spinner_price = new JSpinner();
		sl_sharedItemPanel.putConstraint(SpringLayout.SOUTH, spinner_manufacturerID, -128, SpringLayout.NORTH, spinner_price);
		sl_sharedItemPanel.putConstraint(SpringLayout.NORTH, spinner_price, -57, SpringLayout.SOUTH, sharedItemPanel);
		sl_sharedItemPanel.putConstraint(SpringLayout.WEST, spinner_price, 202, SpringLayout.WEST, sharedItemPanel);
		sl_sharedItemPanel.putConstraint(SpringLayout.SOUTH, spinner_price, 0, SpringLayout.SOUTH, sharedItemPanel);
		sl_sharedItemPanel.putConstraint(SpringLayout.EAST, spinner_price, 0, SpringLayout.EAST, spinner_upc);
		sharedItemPanel.add(spinner_price);
		
		JLabel lblPrice = new JLabel("Price");
		sl_sharedItemPanel.putConstraint(SpringLayout.SOUTH, lblPrice, -21, SpringLayout.SOUTH, sharedItemPanel);
		sl_sharedItemPanel.putConstraint(SpringLayout.EAST, lblPrice, 0, SpringLayout.EAST, lblNewLabel);
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 20));
		sharedItemPanel.add(lblPrice);
		
		JLayeredPane layeredPane = new JLayeredPane();
		springLayout.putConstraint(SpringLayout.NORTH, layeredPane, 6, SpringLayout.SOUTH, comboBox);
		springLayout.putConstraint(SpringLayout.WEST, layeredPane, 6, SpringLayout.EAST, sharedItemPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, layeredPane, 438, SpringLayout.SOUTH, comboBox);
		springLayout.putConstraint(SpringLayout.EAST, layeredPane, 353, SpringLayout.EAST, sharedItemPanel);
		frmAddInventoryItem.getContentPane().add(layeredPane);
		layeredPane.setVisible(true);
		
		btnSubmit.setBounds(116, 393, 117, 39);
		layeredPane.add(btnSubmit);
		btnSubmit.setEnabled(false);
		
		panel_tool.setBounds(0, 0, 347, 369);
		layeredPane.add(panel_tool);
		panel_tool.setLayout(null);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Dialog", Font.BOLD, 20));
		lblDescription.setBounds(0, 0, 146, 61);
		panel_tool.add(lblDescription);
		
		textField_Description = new JTextField();
		textField_Description.setBounds(0, 85, 347, 284);
		panel_tool.add(textField_Description);
		textField_Description.setColumns(10);
		textField_Description.addKeyListener(new KeyAdapter()
				{
					public void keyTyped(KeyEvent e) {
						if(textField_Description.getText().length() >= 100)
							e.consume();
					}
				});
		
		
		panel_nail.setBounds(0, 0, 347, 369);
		layeredPane.add(panel_nail);
		panel_nail.setLayout(null);
		panel_nail.setVisible(false);
		
		JSpinner spinner_length = new JSpinner();
		spinner_length.setBounds(177, 98, 170, 48);
		panel_nail.add(spinner_length);
		
		JLabel lblLength = new JLabel("Length");
		lblLength.setFont(new Font("Dialog", Font.BOLD, 20));
		lblLength.setBounds(87, 103, 85, 32);
		panel_nail.add(lblLength);
		
		JSpinner spinner_numberInBox = new JSpinner();
		spinner_numberInBox.setBounds(177, 284, 170, 48);
		panel_nail.add(spinner_numberInBox);
		
		JLabel lblNumberInBox = new JLabel("Number In Box");
		lblNumberInBox.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNumberInBox.setBounds(0, 289, 172, 32);
		panel_nail.add(lblNumberInBox);
	}
}
