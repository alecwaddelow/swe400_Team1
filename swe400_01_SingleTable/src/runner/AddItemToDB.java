package runner;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import domain.*;
import exceptions.ItemNotFoundException;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import data_source.InventoryItemGateway;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class AddItemToDB {

	private JFrame frmAddInventoryItem;
	private JTextArea textField_Description;
	private JLabel label_AddCompatibles = new JLabel();
	private JPanel panel_AddCompatibles = new JPanel();
	private JScrollPane scrollPane_AddCompatibles = new JScrollPane();
	private ArrayList<PowerTool> powerToolList;
	private ArrayList<JRadioButton> buttonList;

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
		frmAddInventoryItem.setBounds(100, 100, 1079, 509);
		frmAddInventoryItem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel sharedItemPanel = new JPanel();
		sharedItemPanel.setBounds(10, 40, 352, 434);
		JPanel panel_nail = new JPanel();
		JPanel panel_tool = new JPanel();
		JButton btnSubmit = new JButton("Submit");
		
		sharedItemPanel.setVisible(true);
		panel_tool.setVisible(false);
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 10, 705, 24);
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
						label_AddCompatibles.setVisible(false);
						scrollPane_AddCompatibles.setVisible(false);
					}
					else if(item.equals("Tool"))
					{
						panel_nail.setVisible(false);
						panel_tool.setVisible(true);
						label_AddCompatibles.setVisible(false);
						scrollPane_AddCompatibles.setVisible(false);
					}
					else if(item.equals("StripNail"))
					{
						panel_nail.setVisible(false);
						panel_tool.setVisible(false);
						label_AddCompatibles.setVisible(true);
						scrollPane_AddCompatibles.setVisible(true);
						
						powerToolList = new ArrayList<PowerTool>();
						buttonList = new ArrayList<JRadioButton>();
						
						try {
							
							
							ResultSet rs = InventoryItemGateway.getPowerToolUPCs();
							while(rs.next())
							{
								powerToolList.add(new PowerTool(rs.getInt("id")));
							}
							
							
							GridBagConstraints gbc_ptRadioButton = new GridBagConstraints();
							gbc_ptRadioButton.insets = new Insets(0, 0, 20, 0);
							gbc_ptRadioButton.gridx = 0;
							gbc_ptRadioButton.gridy = GridBagConstraints.RELATIVE;
							gbc_ptRadioButton.anchor = GridBagConstraints.WEST;
							for(PowerTool pt : powerToolList)
							{
								JRadioButton jrb = new JRadioButton(pt.toString());
								System.out.println(pt.getUpc());
								buttonList.add(jrb);
								panel_AddCompatibles.add(jrb, gbc_ptRadioButton);
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
					else if(item.equals("PowerTool"))
					{
						panel_nail.setVisible(false);
						panel_tool.setVisible(false);
						label_AddCompatibles.setVisible(true);
						scrollPane_AddCompatibles.setVisible(false);
					}
				}
				else
				{
					panel_nail.setVisible(false);
					panel_tool.setVisible(false);
					btnSubmit.setEnabled(false);
					label_AddCompatibles.setVisible(false);
					scrollPane_AddCompatibles.setVisible(false);
				}
				
			}
		});
		frmAddInventoryItem.getContentPane().setLayout(null);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Nail", "Tool", "StripNail", "PowerTool"}));
		comboBox.setSelectedIndex(0);
		frmAddInventoryItem.getContentPane().add(comboBox);
		frmAddInventoryItem.getContentPane().add(sharedItemPanel);
		sharedItemPanel.setVisible(true);
		sharedItemPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UPC");
		lblNewLabel.setBounds(150, 10, 46, 24);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		sharedItemPanel.add(lblNewLabel);
		
		JSpinner spinner_upc = new JSpinner();
		spinner_upc.setBounds(202, 6, 147, 50);
		spinner_upc.setModel(new SpinnerNumberModel(new Long(0), null, null, new Long(1)));
		sharedItemPanel.add(spinner_upc);
		
		JLabel lblManufacturerId = new JLabel("Manufacturer ID");
		lblManufacturerId.setBounds(10, 209, 186, 24);
		lblManufacturerId.setFont(new Font("Dialog", Font.BOLD, 20));
		sharedItemPanel.add(lblManufacturerId);
		
		JSpinner spinner_manufacturerID = new JSpinner();
		spinner_manufacturerID.setBounds(202, 199, 147, 50);
		sharedItemPanel.add(spinner_manufacturerID);
		
		JSpinner spinner_price = new JSpinner();
		spinner_price.setBounds(202, 377, 147, 57);
		sharedItemPanel.add(spinner_price);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(138, 389, 58, 24);
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 20));
		sharedItemPanel.add(lblPrice);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(368, 40, 347, 432);
		frmAddInventoryItem.getContentPane().add(layeredPane);
		layeredPane.setVisible(true);
		panel_nail.setVisible(false);
		
		
		panel_nail.setBounds(0, 0, 347, 369);
		layeredPane.add(panel_nail);
		panel_nail.setVisible(false);
		panel_nail.setLayout(null);
		
		JSpinner spinner_length = new JSpinner();
		spinner_length.setBounds(177, 98, 170, 48);
		spinner_length.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		panel_nail.add(spinner_length);
		
		JLabel lblLength = new JLabel("Length");
		lblLength.setBounds(87, 103, 85, 32);
		lblLength.setFont(new Font("Dialog", Font.BOLD, 20));
		panel_nail.add(lblLength);
		
		JSpinner spinner_numberInBox = new JSpinner();
		spinner_numberInBox.setBounds(177, 284, 170, 48);
		panel_nail.add(spinner_numberInBox);
		
		JLabel lblNumberInBox = new JLabel("Number In Box");
		lblNumberInBox.setBounds(0, 289, 172, 32);
		lblNumberInBox.setFont(new Font("Dialog", Font.BOLD, 20));
		panel_nail.add(lblNumberInBox);
		
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
		
		textField_Description = new JTextArea();
		textField_Description.setLineWrap(true);
		textField_Description.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField_Description.setBounds(0, 85, 347, 284);
		panel_tool.add(textField_Description);
		textField_Description.setColumns(10);
		
		label_AddCompatibles.setText("Add Compatibles");
		label_AddCompatibles.setHorizontalAlignment(SwingConstants.CENTER);
		label_AddCompatibles.setFont(new Font("Dialog", Font.BOLD, 20));
		label_AddCompatibles.setBounds(733, 10, 334, 24);
		label_AddCompatibles.setVisible(false);
		frmAddInventoryItem.getContentPane().add(label_AddCompatibles);
		
		scrollPane_AddCompatibles.setBounds(727, 40, 340, 430);
		scrollPane_AddCompatibles.setVisible(false);
		frmAddInventoryItem.getContentPane().add(scrollPane_AddCompatibles);
		
		scrollPane_AddCompatibles.setViewportView(panel_AddCompatibles);
		GridBagLayout gbl_panel_AddCompatibles = new GridBagLayout();
		gbl_panel_AddCompatibles.columnWidths = new int[]{0};
		gbl_panel_AddCompatibles.rowHeights = new int[]{0};
		panel_AddCompatibles.setLayout(gbl_panel_AddCompatibles);
		
		textField_Description.addKeyListener(new KeyAdapter()
				{
					public void keyTyped(KeyEvent e) {
						if(textField_Description.getText().length() >= 100)
							e.consume();
					}
				});
		
		/**
		 * add actions listener to the button when it is clicked
		 */
		btnSubmit.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				String item = comboBox.getSelectedItem().toString();
				
				String upc = spinner_upc.getValue().toString();
				int manufacturerID = (int) spinner_manufacturerID.getValue();
				int price = (int) spinner_price.getValue();
				
				
			
				/**
				 * Create a dialog box for confirmation
				 */
				JDialog dialog = new JDialog();
				JPanel contentPanel = new JPanel();
				JLabel itemLabel = new JLabel();
				JLabel confirmationLabel = new JLabel("Are you sure you want to add this " + item + "?\n");
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);	
			
				dialog.setBounds(100, 100, 450, 300);
				dialog.getContentPane().setLayout(new BorderLayout());
				contentPanel.setLayout(new FlowLayout());
				contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
				dialog.getContentPane().add(contentPanel, BorderLayout.CENTER);
				{
					dialog.getContentPane().add(confirmationLabel, BorderLayout.CENTER);
					
					confirmationLabel.setFont(new Font("Dialog", Font.BOLD, 16));
					
					JPanel buttonPane = new JPanel();
					buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
					dialog.getContentPane().add(buttonPane, BorderLayout.SOUTH);
					{
						JButton okButton = new JButton("OK");
						okButton.addMouseListener(new MouseAdapter() 
						{
							@Override
							public void mouseClicked(MouseEvent e) 
							{
								try {
								
								
									switch(item)
									{
									case "Nail":
										double length = (double) spinner_length.getValue();
										int numberInBox = (int) spinner_numberInBox.getValue();
										Nail nail = new Nail(upc, manufacturerID, price, length, numberInBox, "Nail");
										break;
										
									case "Tool":
										String description = textField_Description.getText();
										Tool tool = new Tool(upc, manufacturerID, price, description, "Tool");
										break;
									}
								
								
								} catch (ClassNotFoundException | SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							
								dialog.dispose();
								frmAddInventoryItem.dispose();
							}
						});
					
						okButton.setActionCommand("OK");
						buttonPane.add(okButton);
						dialog.getRootPane().setDefaultButton(okButton);
					}
					{
						JButton cancelButton = new JButton("Cancel");
						cancelButton.addMouseListener(new MouseAdapter() 
						{
							@Override
							public void mouseClicked(MouseEvent e) 
							{
								dialog.dispose();
							}
						});
						cancelButton.setActionCommand("Cancel");
						buttonPane.add(cancelButton);
					}
				}
			}
		});
	}
}
