package gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import data_source.*;
import domain.*;
import exceptions.ItemNotFoundException;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.GridBagLayout;

public class DeleteItem {

	private JFrame frameDeleteItem;
	private JPanel panel_ListOfItem = new JPanel();
	private String itemType = null;
	private List<InventoryItem> itemList = new ArrayList<InventoryItem>();
	private List<JRadioButton> buttonList;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteItem window = new DeleteItem();
					window.frameDeleteItem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DeleteItem() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameDeleteItem = new JFrame();
		frameDeleteItem.setBounds(100, 100, 973, 625);
		frameDeleteItem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameDeleteItem.getContentPane().setLayout(null);

		JButton button_Submit = new JButton("Submit");
		button_Submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try {
				
					switch(itemType)
					{
					case "Nail":
						removeNails();
						loadNails();
						break;
					}
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ItemNotFoundException e) {
					// TODO Auto-Generated catch block
					e.printStackTrace();
				}
				
				frameDeleteItem.revalidate();
				frameDeleteItem.repaint();
			}
		});
		button_Submit.setBounds(715, 535, 117, 51);
		frameDeleteItem.getContentPane().add(button_Submit);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 77, 949, 446);
		frameDeleteItem.getContentPane().add(scrollPane);
		
		scrollPane.setViewportView(panel_ListOfItem);
		GridBagLayout gbl_panel_ListOfItem = new GridBagLayout();
		gbl_panel_ListOfItem.columnWidths = new int[]{0};
		gbl_panel_ListOfItem.rowHeights = new int[]{0};
		panel_ListOfItem.setLayout(gbl_panel_ListOfItem);
		
		JComboBox comboBox_Item = new JComboBox();
		comboBox_Item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itemType = comboBox_Item.getSelectedItem().toString();
				button_Submit.setEnabled(!itemType.equals(""));
				
				try {
					
					switch(itemType)
					{
					case "Nail":
						loadNails();
						break;	
					default:
						panel_ListOfItem.removeAll();
					}
					
					frameDeleteItem.revalidate();
					frameDeleteItem.repaint();
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
		comboBox_Item.setModel(new DefaultComboBoxModel(new String[] {"", "Nail", "Tool", "StripNail", "PowerTool"}));
		comboBox_Item.setSelectedIndex(0);
		comboBox_Item.setBounds(230, 12, 484, 31);
		frameDeleteItem.getContentPane().add(comboBox_Item);
		
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frameDeleteItem.dispose();
			}
		});
		btnCancel.setBounds(844, 535, 117, 51);
		frameDeleteItem.getContentPane().add(btnCancel);
	}
	
	/**
	 * loads the nails
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ItemNotFoundException 
	 */
	private void loadNails() throws ClassNotFoundException, SQLException, ItemNotFoundException 
	{
		panel_ListOfItem.removeAll();
		
		GridBagConstraints gbc_RadioButton = new GridBagConstraints();
		gbc_RadioButton.insets = new Insets(0, 0, 20, 0);
		gbc_RadioButton.gridx = 0;
		gbc_RadioButton.gridy = GridBagConstraints.RELATIVE;
		gbc_RadioButton.anchor = GridBagConstraints.WEST;
		buttonList = new ArrayList<JRadioButton>();
		
		List<InventoryItemDTO> listInventoryItemDTO = InventoryItemGateway.getAllNails();	
		for(InventoryItemDTO iiDTO : listInventoryItemDTO)
		{
			Nail nail = new Nail(iiDTO.getId());
			itemList.add((InventoryItem) nail);
			
			JRadioButton jrb = new JRadioButton(nail.toString());
			buttonList.add(jrb);
			panel_ListOfItem.add(jrb, gbc_RadioButton);
		}
	}
	
	/**
	 * removes the nails from the inventory item table
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private void removeNails() throws ClassNotFoundException, SQLException 
	{
		for(int index = 0; index < buttonList.size(); index++)
		{
			if(buttonList.get(index).isSelected())
			{
				Nail nail = (Nail) itemList.get(index);
				nail.removeFromTable();
				nail = null;
			}
		}
	}
}
