package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JTextArea;
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
import java.util.List;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;

public class SearchByUPC {

	private JFrame frame;
	private JTextField textField;
	private JButton button_submit = new JButton("Submit");
	private JTextArea textArea_Output = new JTextArea();
	private JComboBox comboBox = new JComboBox();

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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		comboBox.setBounds(6, 12, 730, 40);
		comboBox.setFont(new Font("Dialog", Font.BOLD, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Nail", "Tool", "PowerTool", "StripNail"}));
		comboBox.setSelectedIndex(0);
		frame.getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(139, 78, 457, 51);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				button_submit.setEnabled(true);
			}
		});
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setForeground(Color.BLACK);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterUpc = new JLabel("Enter UPC:");
		lblEnterUpc.setBounds(10, 96, 123, 24);
		lblEnterUpc.setFont(new Font("Dialog", Font.BOLD, 20));
		frame.getContentPane().add(lblEnterUpc);
		button_submit.setBounds(608, 76, 128, 51);
		
		
		button_submit.addMouseListener(new MouseAdapter() {
			/**
			 * when the mouse is clicked and released
			 */
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
				String upc = textField.getText();
				try {
					getOutput(upc);
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
		button_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_submit.setEnabled(false);
		frame.getContentPane().add(button_submit);
		
		JScrollPane scrollPane_Output = new JScrollPane();
		scrollPane_Output.setBounds(12, 145, 730, 136);
		frame.getContentPane().add(scrollPane_Output);
		
		scrollPane_Output.setViewportView(textArea_Output);
	}
	
	/**
	 * gets the output by the item's upc
	 * 
	 * @param upc
	 * @throws ItemNotFoundException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private void getOutput(String upc) throws ClassNotFoundException, SQLException, ItemNotFoundException 
	{
		/* get the details of the inventory item */
		InventoryItem item = InventoryItem.getDetails(upc, comboBox.getSelectedItem().toString());
		if(item != null)
		{
			textArea_Output.setText(item.toString());
			if(item instanceof PowerTool)
			{
				List<StripNail> stripList = ((PowerTool) item).getStripNailList();
				textArea_Output.append("\nWorks With:\n");
				
				/* print the array list of what stripnails work with the power tools */
				for(StripNail stripNail : stripList)
				{
					textArea_Output.append(stripNail.toString() + "\n");
				}							
			}
			else if(item instanceof StripNail)
			{
				List<PowerTool> powerToolList = ((StripNail) item).getPowerToolList();
				textArea_Output.append("\nWorks with:\n");
				
				/* print the array list of what powertools work witht eh stripnail */
				for(PowerTool powerTool : powerToolList)
				{
					textArea_Output.append(powerTool.toString() + "\n");
				}
			}
		}
		else
		{
			textArea_Output.setText("Error: No Such Item Exists");
		}
	}
}
