package gui;

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
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import data_source.LinkTableGateway;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

public class AddItemToDB {

	private JFrame frmAddInventoryItem;
	private JLabel label_AddCompatibles = new JLabel();
	
	private JRadioButton radioButton_True = new JRadioButton("TRUE");
	private JRadioButton radioButton_False = new JRadioButton("FALSE");
	private ButtonGroup buttonGroup = new ButtonGroup();
	
	private JSpinner spinner_upc = new JSpinner();
	private JSpinner spinner_manufacturerID = new JSpinner();
	private JSpinner spinner_price = new JSpinner();
	private JSpinner spinner_SNLength = new JSpinner();
	private JSpinner spinner_numberInStrip = new JSpinner();
	private JSpinner spinner_length = new JSpinner();
	private JSpinner spinner_numberInBox = new JSpinner();
	
	private JTextArea textArea_Description = new JTextArea();
	private JTextArea textArea_PT_Description = new JTextArea();
	
	private JPanel panel_PowerTool = new JPanel();
	private JPanel panel_StripNail = new JPanel();
	private JPanel sharedItemPanel = new JPanel();
	private JPanel panel_nail = new JPanel();
	private JPanel panel_tool = new JPanel();
	private JPanel panel_AddCompatibles = new JPanel();
	private JScrollPane scrollPane_AddCompatibles = new JScrollPane();
	
	private JButton btnSubmit = new JButton("Submit");

	private static ArrayList<PowerTool> powerToolList;
	private static ArrayList<StripNail> stripNailList;
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
		
		
		sharedItemPanel.setBounds(10, 40, 352, 434);
		
		sharedItemPanel.setVisible(true);
		panel_tool.setVisible(false);
		
		GridBagConstraints gbc_RadioButton = new GridBagConstraints();
		gbc_RadioButton.insets = new Insets(0, 0, 20, 0);
		gbc_RadioButton.gridx = 0;
		gbc_RadioButton.gridy = GridBagConstraints.RELATIVE;
		gbc_RadioButton.anchor = GridBagConstraints.WEST;
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 10, 705, 24);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String item = comboBox.getSelectedItem().toString();
				
				manageDisplay(item);
				if(item.equals("StripNail"))
				{					
					powerToolList = new ArrayList<PowerTool>();
					buttonList = new ArrayList<JRadioButton>();
					
					try {
						
						ResultSet rs = InventoryItemGateway.getAllPowerTools();
						while(rs.next())
						{
							PowerTool powerTool = new PowerTool(rs.getInt("id"));
							powerToolList.add(powerTool);
							
							JRadioButton jrb = new JRadioButton(powerTool.toString());
							buttonList.add(jrb);
							panel_AddCompatibles.add(jrb, gbc_RadioButton);
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
					stripNailList = new ArrayList<StripNail>();
					buttonList = new ArrayList<JRadioButton>();
					
					try {
						
						ResultSet rs = InventoryItemGateway.getAllStripNails();
						for(int index = 0; rs.next(); index++)
						{
							StripNail stripNail = new StripNail(rs.getInt("id"));
							stripNailList.add(stripNail);
							
							JRadioButton jrb = new JRadioButton(stripNail.toString());
							buttonList.add(jrb);
							panel_AddCompatibles.add(jrb, gbc_RadioButton);
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
			}

			/**
			 * manages what to display
			 * @param item
			 */
			private void manageDisplay(String item) 
			{
				spinner_upc.setValue(0);
				spinner_manufacturerID.setValue(0);
				spinner_price.setValue(1);
				spinner_length.setValue(0);
				spinner_SNLength.setValue(0);
				spinner_numberInBox.setValue(1);
				spinner_numberInStrip.setValue(1);
				textArea_Description.setText(null);
				textArea_PT_Description.setText(null);
				
				btnSubmit.setEnabled((!item.equals("")) ? true:false);
				
				switch(item)
				{
				case "Nail":
					panel_tool.setVisible(false);
					panel_PowerTool.setVisible(false);
					label_AddCompatibles.setVisible(false);
					panel_AddCompatibles.setVisible(false);
					panel_StripNail.setVisible(false);
					panel_nail.setVisible(true);
					break;
				case "Tool":
					panel_nail.setVisible(false);
					label_AddCompatibles.setVisible(false);
					panel_AddCompatibles.setVisible(false);
					panel_PowerTool.setVisible(false);
					panel_StripNail.setVisible(false);
					panel_tool.setVisible(true);
					break;
				case "StripNail":
					panel_AddCompatibles.removeAll();
					panel_nail.setVisible(false);
					panel_tool.setVisible(false);
					panel_PowerTool.setVisible(false);
					panel_StripNail.setVisible(true);
					label_AddCompatibles.setVisible(true);
					panel_AddCompatibles.setVisible(true);
					break;
				case "PowerTool":
					panel_AddCompatibles.removeAll();
					panel_nail.setVisible(false);
					panel_tool.setVisible(false);
					panel_StripNail.setVisible(false);
					panel_PowerTool.setVisible(true);
					label_AddCompatibles.setVisible(true);
					panel_AddCompatibles.setVisible(true);
					break;
				default:
					panel_nail.setVisible(false);
					panel_tool.setVisible(false);
					btnSubmit.setEnabled(false);
					panel_PowerTool.setVisible(false);
					panel_StripNail.setVisible(false);
					label_AddCompatibles.setVisible(false);
					panel_AddCompatibles.setVisible(false);
					break;					
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
		
		spinner_upc.setBounds(202, 6, 147, 50);
		spinner_upc.setModel(new SpinnerNumberModel(new Long(0), new Long(0), null, new Long(1)));
		sharedItemPanel.add(spinner_upc);
		
		JLabel lblManufacturerId = new JLabel("Manufacturer ID");
		lblManufacturerId.setBounds(10, 209, 186, 24);
		lblManufacturerId.setFont(new Font("Dialog", Font.BOLD, 20));
		sharedItemPanel.add(lblManufacturerId);
		
		spinner_manufacturerID.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner_manufacturerID.setBounds(202, 199, 147, 50);
		sharedItemPanel.add(spinner_manufacturerID);
		
		spinner_price.setModel(new SpinnerNumberModel(new Integer(1), new Integer(0), null, new Integer(1)));
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
		
		panel_StripNail.setBounds(0, 0, 347, 369);
		layeredPane.add(panel_StripNail);
		panel_StripNail.setLayout(null);
		
		spinner_SNLength.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		spinner_SNLength.setBounds(177, 99, 170, 48);
		panel_StripNail.add(spinner_SNLength);
		
		JLabel label_SNLength = new JLabel("Length");
		label_SNLength.setFont(new Font("Dialog", Font.BOLD, 20));
		label_SNLength.setBounds(91, 103, 85, 32);
		panel_StripNail.add(label_SNLength);
		
		spinner_numberInStrip.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner_numberInStrip.setBounds(177, 279, 170, 48);
		panel_StripNail.add(spinner_numberInStrip);
		
		JLabel lblNumberInStrip = new JLabel("Number In Strip");
		lblNumberInStrip.setFont(new Font("Dialog", Font.BOLD, 19));
		lblNumberInStrip.setBounds(0, 286, 190, 32);
		panel_StripNail.add(lblNumberInStrip);
		
		panel_PowerTool.setBounds(0, 0, 347, 369);
		layeredPane.add(panel_PowerTool);
		panel_PowerTool.setLayout(null);
		panel_PowerTool.setVisible(false);
		
		textArea_PT_Description.setBounds(0, 191, 347, 178);
		panel_PowerTool.add(textArea_PT_Description);
		
		JLabel label_PT_Description = new JLabel("Description");
		label_PT_Description.setFont(new Font("Dialog", Font.BOLD, 20));
		label_PT_Description.setBounds(12, 144, 146, 61);
		panel_PowerTool.add(label_PT_Description);
		
		JLabel lblBatteryPowered = new JLabel("Battery Powered?");
		lblBatteryPowered.setFont(new Font("Dialog", Font.BOLD, 20));
		lblBatteryPowered.setBounds(12, 12, 323, 37);
		panel_PowerTool.add(lblBatteryPowered);
		
		radioButton_True.setBounds(12, 86, 149, 23);
		panel_PowerTool.add(radioButton_True);
		buttonGroup.add(radioButton_True);
		
		radioButton_False.setBounds(190, 86, 149, 23);
		panel_PowerTool.add(radioButton_False);
		buttonGroup.add(radioButton_False);
		
		panel_nail.setBounds(0, 0, 347, 369);
		layeredPane.add(panel_nail);
		panel_nail.setVisible(false);
		panel_nail.setLayout(null);
		
		spinner_length.setBounds(177, 98, 170, 48);
		spinner_length.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		panel_nail.add(spinner_length);
		
		JLabel lblLength = new JLabel("Length");
		lblLength.setBounds(87, 103, 85, 32);
		lblLength.setFont(new Font("Dialog", Font.BOLD, 20));
		panel_nail.add(lblLength);
		
		spinner_numberInBox.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
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
		
		textArea_Description.setLineWrap(true);
		textArea_Description.setFont(new Font("Dialog", Font.PLAIN, 14));
		textArea_Description.setBounds(0, 85, 347, 284);
		panel_tool.add(textArea_Description);
		textArea_Description.setColumns(10);
		
		label_AddCompatibles.setText("Add Compatibles");
		label_AddCompatibles.setHorizontalAlignment(SwingConstants.CENTER);
		label_AddCompatibles.setFont(new Font("Dialog", Font.BOLD, 20));
		label_AddCompatibles.setBounds(733, 10, 334, 24);
		label_AddCompatibles.setVisible(false);
		frmAddInventoryItem.getContentPane().add(label_AddCompatibles);
		
		scrollPane_AddCompatibles.setBounds(727, 40, 340, 430);
		scrollPane_AddCompatibles.setVisible(true);
		frmAddInventoryItem.getContentPane().add(scrollPane_AddCompatibles);
		
		scrollPane_AddCompatibles.setViewportView(panel_AddCompatibles);
		GridBagLayout gbl_panel_AddCompatibles = new GridBagLayout();
		gbl_panel_AddCompatibles.columnWidths = new int[]{0};
		gbl_panel_AddCompatibles.rowHeights = new int[]{0};
		panel_AddCompatibles.setLayout(gbl_panel_AddCompatibles);
		panel_AddCompatibles.setVisible(false);
		
		textArea_Description.addKeyListener(new KeyAdapter()
				{
					public void keyTyped(KeyEvent e) {
						if(textArea_Description.getText().length() >= 100)
							e.consume();
					}
				});
		
		textArea_PT_Description.addKeyListener(new KeyAdapter()
				{
					public void keyTyped(KeyEvent e) {
						if(textArea_PT_Description.getText().length() >= 100)
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
										String toolDescription = textArea_Description.getText();
										Tool tool = new Tool(upc, manufacturerID, price, toolDescription, "Tool");
										break;
										
									case "PowerTool":
										String powerToolDescription = textArea_PT_Description.getText(); 
										boolean batteryPowered = (radioButton_True.isSelected()) ? true:false;
										PowerTool powerTool = new PowerTool(upc, manufacturerID, price, powerToolDescription, batteryPowered, "PowerTool");
										for(int i = 0; i < buttonList.size(); i++)
										{
											if(buttonList.get(i).isSelected())
											{
												LinkTableGateway.addRelation(powerTool.getId(), stripNailList.get(i).getId());
											}
										}
										break;
										
									case "StripNail":
										double stripNailLength = (double) spinner_SNLength.getValue();
										int numberInStrip = (int) spinner_numberInStrip.getValue();
										StripNail stripNail = new StripNail(upc, manufacturerID, price, stripNailLength, numberInStrip, "StripNail");
										for(int i = 0; i < buttonList.size(); i++)
										{
											if(buttonList.get(i).isSelected())
											{
												LinkTableGateway.addRelation(powerToolList.get(i).getId(), stripNail.getId());
											}
										}
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
