package gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import domain.*;
import exceptions.ItemNotFoundException;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.MatteBorder;

import data_source.InventoryItemDTO;
import data_source.InventoryItemGateway;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class ItemToUpdatePrompt 
{

	private JFrame frame;
	private JComboBox comboBox_InventoryItem;
	private JScrollPane scrollPane;
	private JPanel panel_InventoryItem = new JPanel();
	private ButtonGroup buttonGroup;
	
	private static List<JRadioButton> buttonList;
	
	/* list of items */
	private static List<Nail> nailsList;
	private static List<Tool> toolsList;
	private static List<PowerTool> powerToolsList;
	private static List<StripNail> stripNailsList;

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
		comboBox_InventoryItem = new JComboBox();
		comboBox_InventoryItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String item = comboBox_InventoryItem.getSelectedItem().toString();
				buttonList = new ArrayList<JRadioButton>();
				buttonGroup = new ButtonGroup();
				
				panel_InventoryItem.removeAll();
				
				GridBagConstraints gbc_RadioButton = new GridBagConstraints();
				gbc_RadioButton.insets = new Insets(0, 0, 20, 0);
				gbc_RadioButton.gridx = 0;
				gbc_RadioButton.gridy = GridBagConstraints.RELATIVE;
				gbc_RadioButton.anchor = GridBagConstraints.WEST;
				JRadioButton jrb = null;

				try {
					
					switch(item)
					{
					case "Nail":
						List<InventoryItemDTO> listInventoryItemDTO_Nail = InventoryItemGateway.getAllNails();
						nailsList = new ArrayList<Nail>();
						for(InventoryItemDTO iiDTO : listInventoryItemDTO_Nail)
						{
							Nail nail = new Nail(iiDTO.getId());
							nailsList.add(nail);
							
							jrb = new JRadioButton(nail.toString());
							buttonGroup.add(jrb);
							buttonList.add(jrb);
							panel_InventoryItem.add(jrb, gbc_RadioButton);
						}
						break;
						
					case "Tool":
						List<InventoryItemDTO> listInventoryItemDTO_Tool = InventoryItemGateway.getAllTools();
						toolsList = new ArrayList<Tool>();
						for(InventoryItemDTO iiDTO : listInventoryItemDTO_Tool)
						{
							Tool tool = new Tool(iiDTO.getId());
							toolsList.add(tool);
							
							jrb = new JRadioButton(tool.toString());
							buttonGroup.add(jrb);
							buttonList.add(jrb);
							panel_InventoryItem.add(jrb, gbc_RadioButton);
						}
						break;
						
					case "StripNail":
						List<InventoryItemDTO> listInventoryItemDTO_SN = InventoryItemGateway.getAllStripNails();
						stripNailsList = new ArrayList<StripNail>();
						for(InventoryItemDTO iiDTO : listInventoryItemDTO_SN)
						{
							StripNail stripNail = new StripNail(iiDTO.getId());
							stripNailsList.add(stripNail);
							
							jrb = new JRadioButton(stripNail.toString());
							buttonGroup.add(jrb);
							buttonList.add(jrb);
							panel_InventoryItem.add(jrb, gbc_RadioButton);
						}
						break;
						
					case "PowerTool":
						List<InventoryItemDTO> listInventoryItemDTO_PT = InventoryItemGateway.getAllPowerTools();
						powerToolsList = new ArrayList<PowerTool>();
						for(InventoryItemDTO iiDTO : listInventoryItemDTO_PT)
						{
							PowerTool powerTool = new PowerTool(iiDTO.getId());
							powerToolsList.add(powerTool);
							
							jrb = new JRadioButton(powerTool.toString());
							buttonGroup.add(jrb);
							buttonList.add(jrb);
							panel_InventoryItem.add(jrb, gbc_RadioButton);
						}
						break;
						
					}
					panel_InventoryItem.revalidate();
					panel_InventoryItem.repaint();
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ItemNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
				
			}
		});
		
		comboBox_InventoryItem.setModel(new DefaultComboBoxModel(new String[] {"", "Nail", "Tool", "PowerTool", "StripNail"}));
		comboBox_InventoryItem.setSelectedIndex(0);
		comboBox_InventoryItem.setBounds(12, 12, 402, 33);
		frame.getContentPane().add(comboBox_InventoryItem);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 56, 392, 336);
		frame.getContentPane().add(scrollPane);
		
		panel_InventoryItem.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setViewportView(panel_InventoryItem);
		GridBagLayout gbl_panel_InventoryItem = new GridBagLayout();
		gbl_panel_InventoryItem.columnWidths = new int[]{0};
		gbl_panel_InventoryItem.rowHeights = new int[]{0};
		panel_InventoryItem.setLayout(gbl_panel_InventoryItem);
		panel_InventoryItem.setVisible(true);
		
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
