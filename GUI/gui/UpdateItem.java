package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import javax.swing.JFrame;
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
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;

import data_source.InventoryItemDTO;
import data_source.InventoryItemGateway;
import data_source.LinkTableDTO;
import data_source.LinkTableGateway;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class UpdateItem {

	private JFrame frameUpdateItem;
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

	private static List<PowerTool> powerToolList;
	private static List<StripNail> stripNailList;
	private List<JRadioButton> buttonList;
	
	private static InventoryItem itemToUpdate;
	private static String item = null;

	/**
	 * Launch the application.
	 */
	public static void updateItem(InventoryItem item) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					itemToUpdate = item;
					UpdateItem window = new UpdateItem();
					window.frameUpdateItem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws ItemNotFoundException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public UpdateItem() throws ClassNotFoundException, SQLException, ItemNotFoundException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ItemNotFoundException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private void initialize() throws ClassNotFoundException, SQLException, ItemNotFoundException {
		frameUpdateItem = new JFrame();
		frameUpdateItem.setTitle("Update Inventory Item");
		frameUpdateItem.setBounds(100, 100, 1079, 509);
		frameUpdateItem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		sharedItemPanel.setBounds(10, 40, 352, 434);
		
		sharedItemPanel.setVisible(true);
		panel_tool.setVisible(false);
		
		GridBagConstraints gbc_RadioButton = new GridBagConstraints();
		gbc_RadioButton.insets = new Insets(0, 0, 20, 0);
		gbc_RadioButton.gridx = 0;
		gbc_RadioButton.gridy = GridBagConstraints.RELATIVE;
		gbc_RadioButton.anchor = GridBagConstraints.WEST;
		frameUpdateItem.getContentPane().setLayout(null);
		frameUpdateItem.getContentPane().add(sharedItemPanel);
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
		frameUpdateItem.getContentPane().add(layeredPane);
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
		
		btnSubmit.setBounds(110, 393, 117, 39);
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
		
		JButton btnNewCancel = new JButton("Cancel");
		btnNewCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frameUpdateItem.dispose();
			}
		});
		btnNewCancel.setBounds(230, 393, 117, 39);
		layeredPane.add(btnNewCancel);
		
		label_AddCompatibles.setText("Add Compatibles");
		label_AddCompatibles.setHorizontalAlignment(SwingConstants.CENTER);
		label_AddCompatibles.setFont(new Font("Dialog", Font.BOLD, 20));
		label_AddCompatibles.setBounds(733, 10, 334, 24);
		label_AddCompatibles.setVisible(false);
		frameUpdateItem.getContentPane().add(label_AddCompatibles);
		
		scrollPane_AddCompatibles.setBounds(727, 40, 340, 430);
		scrollPane_AddCompatibles.setVisible(true);
		frameUpdateItem.getContentPane().add(scrollPane_AddCompatibles);
		
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
		
		
		updateFields();
		
		
		
		/**
		 * add actions listener to the button when it is clicked
		 */
		btnSubmit.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{					
				String upc = spinner_upc.getValue().toString();
				int manufacturerID = (int) spinner_manufacturerID.getValue();
				int price = (int) spinner_price.getValue();
			
				/**
				 * Create a dialog box for confirmation
				 */
				JDialog dialog = new JDialog();
				JPanel contentPanel = new JPanel();
				JLabel itemLabel = new JLabel();
				JLabel confirmationLabel = new JLabel("Are you sure you want to update this " + item + "?\n");
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
										Nail nail = (Nail) itemToUpdate;
										nail.update(upc, manufacturerID, price, length, numberInBox);
										break;
										
									case "Tool":
										String toolDescription = textArea_Description.getText();
										Tool tool = (Tool) itemToUpdate;
										tool.update(upc, manufacturerID, price, toolDescription);
										break;
										
									case "PowerTool":
										String powerToolDescription = textArea_PT_Description.getText(); 
										boolean batteryPowered = (radioButton_True.isSelected()) ? true:false;
										PowerTool powerTool = (PowerTool) itemToUpdate;
										powerTool.update(upc, manufacturerID, price, powerToolDescription, batteryPowered);
										managePowerToolCompatibles(powerTool);
										break;
										
									case "StripNail":
										double stripNailLength = (double) spinner_SNLength.getValue();
										int numberInStrip = (int) spinner_numberInStrip.getValue();
										StripNail stripNail = (StripNail) itemToUpdate;
										stripNail.update(upc, manufacturerID, price, stripNailLength, numberInStrip);
										manageStripNailCompatibles(stripNail);
										break;
									}
								
								
								} catch (ClassNotFoundException | SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							
								dialog.dispose();
								frameUpdateItem.dispose();
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

	/**
	 * updates the fields from the itemToUpdate to the screen
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ItemNotFoundException 
	 */
	private void updateFields() throws ClassNotFoundException, SQLException, ItemNotFoundException 
	{
		spinner_upc.setValue(Long.valueOf(itemToUpdate.getUpc()));
		spinner_manufacturerID.setValue(itemToUpdate.getManufacturerID());
		spinner_price.setValue(itemToUpdate.getPrice());
		
		GridBagConstraints gbc_RadioButton = new GridBagConstraints();
		gbc_RadioButton.insets = new Insets(0, 0, 20, 0);
		gbc_RadioButton.gridx = 0;
		gbc_RadioButton.gridy = GridBagConstraints.RELATIVE;
		gbc_RadioButton.anchor = GridBagConstraints.WEST;
		
		if(itemToUpdate instanceof Nail)
		{
			item = "Nail";
			Nail nail = (Nail) itemToUpdate;
			spinner_length.setValue(nail.getLength());
			spinner_numberInBox.setValue(nail.getNumberInBox());
		}
		else if(itemToUpdate instanceof Tool)
		{
			item = "Tool";
			Tool tool = (Tool) itemToUpdate;
			textArea_Description.setText(tool.getDescription());
		}
		else if(itemToUpdate instanceof PowerTool)
		{
			item = "PowerTool";
			PowerTool powerTool = (PowerTool) itemToUpdate;
			radioButton_True.setSelected(powerTool.isBatteryPowered());
			radioButton_False.setSelected(!powerTool.isBatteryPowered());
			textArea_PT_Description.setText(powerTool.getDescription());
			
			stripNailList = new ArrayList<StripNail>();
			buttonList = new ArrayList<JRadioButton>();
			
			List<InventoryItemDTO> listInventoryItemDTO = InventoryItemGateway.getAllStripNails();
			List<LinkTableDTO> listLinkTableDTO = LinkTableGateway.queryDBForStripNails(powerTool.getId());
			
			for(InventoryItemDTO iiDTO : listInventoryItemDTO)
			{
				StripNail stripNail = new StripNail(iiDTO.getId());
				stripNailList.add(stripNail);
				
				JRadioButton jrb = new JRadioButton(stripNail.toString());
				
				for(LinkTableDTO ltDTO : listLinkTableDTO)
				{
					if(iiDTO.getId() == ltDTO.getStripNailID())
					{
						jrb.setSelected(true);
						break;
					}
				}
				
				buttonList.add(jrb);
				panel_AddCompatibles.add(jrb, gbc_RadioButton);
			}
		}
		else if(itemToUpdate instanceof StripNail)
		{
			item = "StripNail";
			StripNail stripNail = (StripNail) itemToUpdate;
			spinner_SNLength.setValue(stripNail.getLength());
			spinner_numberInStrip.setValue(stripNail.getNumberInStrip());
			
			powerToolList = new ArrayList<PowerTool>();
			buttonList = new ArrayList<JRadioButton>();
			
			List<InventoryItemDTO> listInventoryItemDTO = InventoryItemGateway.getAllPowerTools();
			List<LinkTableDTO> listLinkTableDTO = LinkTableGateway.queryDBForPowerTools(stripNail.getId());
			
			for(InventoryItemDTO iiDTO : listInventoryItemDTO)
			{
				PowerTool powerTool = new PowerTool(iiDTO.getId());
				powerToolList.add(powerTool);
				
				JRadioButton jrb = new JRadioButton(powerTool.toString());
				
				for(LinkTableDTO ltDTO : listLinkTableDTO)
				{
					if(iiDTO.getId() == ltDTO.getPowerToolID())
					{
						jrb.setSelected(true);
						break;
					}
				}
				
				buttonList.add(jrb);
				panel_AddCompatibles.add(jrb, gbc_RadioButton);
			}			
		}
		manageDisplay();
	}

	/**
	 * manages what to display
	 */
	private void manageDisplay() 
	{
		btnSubmit.setEnabled(true);
		panel_nail.setVisible(item.equals("Nail"));
		panel_tool.setVisible(item.equals("Tool"));
		panel_PowerTool.setVisible(item.equals("PowerTool"));
		panel_StripNail.setVisible(item.equals("StripNail"));
		label_AddCompatibles.setVisible(item.equals("PowerTool") || item.equals("StripNail"));
		panel_AddCompatibles.setVisible(item.equals("PowerTool") || item.equals("StripNail"));
		frameUpdateItem.revalidate();
		frameUpdateItem.repaint();
	}
	
	/**
	 * manages the stripnail compatibles 
	 * 
	 * @param stripNail
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void manageStripNailCompatibles(StripNail stripNail) throws ClassNotFoundException, SQLException 
	{
		List<LinkTableDTO> listLinkTableDTO = LinkTableGateway.queryDBForPowerTools(stripNail.getId());
		for(int index = 0; index < buttonList.size(); index++)
		{
			/* only adds compatibles that don't already exist within the relationship */
			if(buttonList.get(index).isSelected())
			{
				boolean alreadyExists = false;
				for(LinkTableDTO ltDTO : listLinkTableDTO)
				{
					if(ltDTO.getPowerToolID() == powerToolList.get(index).getId())
					{
						alreadyExists = true;
					}
				}
				
				if(!alreadyExists)
				{
					LinkTableGateway.addRelation(powerToolList.get(index).getId(), stripNail.getId());
				}
			}
			/* removes relations if pre-existing relations' buttons are no longer selected */
			else
			{
				for(LinkTableDTO ltDTO : listLinkTableDTO)
				{
					if(ltDTO.getPowerToolID() == powerToolList.get(index).getId())
					{
						LinkTableGateway.removeRelation(ltDTO.getPowerToolID(), stripNail.getId());												
					}
				}
			}
		}
	}
	
	/**
	 * manages the powertool compatibles
	 * 
	 * @param powerTool
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private void managePowerToolCompatibles(PowerTool powerTool) throws ClassNotFoundException, SQLException 
	{
		List<LinkTableDTO> listLinkTableDTO = LinkTableGateway.queryDBForStripNails(powerTool.getId());
		for(int index = 0; index < buttonList.size(); index++)
		{
			/* only adds compatibles that don't already exist within the relationship */
			if(buttonList.get(index).isSelected())
			{
				boolean alreadyExists = false;
				for(LinkTableDTO ltDTO : listLinkTableDTO)
				{
					if(ltDTO.getStripNailID() == stripNailList.get(index).getId())
					{
						alreadyExists = true;
					}
				}
				
				if(!alreadyExists)
				{
					LinkTableGateway.addRelation(powerTool.getId(), stripNailList.get(index).getId());
				}
			}
			/* removes relations if pre-existing relations' buttons are no longer selected */
			else
			{
				for(LinkTableDTO ltDTO : listLinkTableDTO)
				{
					if(ltDTO.getStripNailID() == stripNailList.get(index).getId())
					{
						LinkTableGateway.removeRelation(powerTool.getId(), ltDTO.getStripNailID());
					}
				}
			}								
		}
		
	}
}
