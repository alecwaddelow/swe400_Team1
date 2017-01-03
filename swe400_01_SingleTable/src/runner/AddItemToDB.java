package runner;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.text.NumberFormatter;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class AddItemToDB {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void addItem() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddItemToDB window = new AddItemToDB();
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
	public AddItemToDB() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		JPanel mainItemPanel = new JPanel();
		frame.setBounds(100, 100, 725, 509);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		
		
		
		
		JComboBox comboBox = new JComboBox();
		springLayout.putConstraint(SpringLayout.NORTH, mainItemPanel, 6, SpringLayout.SOUTH, comboBox);
		springLayout.putConstraint(SpringLayout.WEST, mainItemPanel, 0, SpringLayout.WEST, comboBox);
		springLayout.putConstraint(SpringLayout.SOUTH, mainItemPanel, 440, SpringLayout.SOUTH, comboBox);
		springLayout.putConstraint(SpringLayout.EAST, mainItemPanel, -353, SpringLayout.EAST, comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String item = comboBox.getSelectedItem().toString();
				
				if(item.equals("Nail"))
				{
				}
				else if(item.equals("Tool"))
				{
					
				}
				else if(item.equals("StripNail"))
				{
					
				}
				else if(item.equals("PowerTool"))
				{
					
				}
				else
				{
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Nail", "Tool", "StripNail", "PowerTool"}));
		comboBox.setSelectedIndex(0);
		springLayout.putConstraint(SpringLayout.NORTH, comboBox, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, comboBox, 715, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(comboBox);
		frame.getContentPane().add(mainItemPanel);
		SpringLayout sl_mainItemPanel = new SpringLayout();
		mainItemPanel.setLayout(sl_mainItemPanel);
		mainItemPanel.setVisible(true);
		
		JLabel lblNewLabel = new JLabel("UPC");
		sl_mainItemPanel.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, mainItemPanel);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		mainItemPanel.add(lblNewLabel);
		
		JSpinner spinner = new JSpinner();
		sl_mainItemPanel.putConstraint(SpringLayout.EAST, lblNewLabel, -6, SpringLayout.WEST, spinner);
		sl_mainItemPanel.putConstraint(SpringLayout.NORTH, spinner, 6, SpringLayout.NORTH, mainItemPanel);
		sl_mainItemPanel.putConstraint(SpringLayout.WEST, spinner, 202, SpringLayout.WEST, mainItemPanel);
		sl_mainItemPanel.putConstraint(SpringLayout.SOUTH, spinner, -378, SpringLayout.SOUTH, mainItemPanel);
		sl_mainItemPanel.putConstraint(SpringLayout.EAST, spinner, -3, SpringLayout.EAST, mainItemPanel);
		spinner.setModel(new SpinnerNumberModel(new Long(0), null, null, new Long(1)));
		mainItemPanel.add(spinner);
		
		JLabel lblManufacturerId = new JLabel("Manufacturer ID");
		sl_mainItemPanel.putConstraint(SpringLayout.NORTH, lblManufacturerId, 209, SpringLayout.NORTH, mainItemPanel);
		sl_mainItemPanel.putConstraint(SpringLayout.WEST, lblManufacturerId, 10, SpringLayout.WEST, mainItemPanel);
		lblManufacturerId.setFont(new Font("Dialog", Font.BOLD, 20));
		mainItemPanel.add(lblManufacturerId);
		
		JSpinner spinner_1 = new JSpinner();
		sl_mainItemPanel.putConstraint(SpringLayout.NORTH, spinner_1, 143, SpringLayout.SOUTH, spinner);
		sl_mainItemPanel.putConstraint(SpringLayout.WEST, spinner_1, 6, SpringLayout.EAST, lblManufacturerId);
		sl_mainItemPanel.putConstraint(SpringLayout.EAST, spinner_1, -3, SpringLayout.EAST, mainItemPanel);
		mainItemPanel.add(spinner_1);
		
		JSpinner spinner_2 = new JSpinner();
		sl_mainItemPanel.putConstraint(SpringLayout.SOUTH, spinner_1, -128, SpringLayout.NORTH, spinner_2);
		sl_mainItemPanel.putConstraint(SpringLayout.NORTH, spinner_2, -57, SpringLayout.SOUTH, mainItemPanel);
		sl_mainItemPanel.putConstraint(SpringLayout.WEST, spinner_2, 202, SpringLayout.WEST, mainItemPanel);
		sl_mainItemPanel.putConstraint(SpringLayout.SOUTH, spinner_2, 0, SpringLayout.SOUTH, mainItemPanel);
		sl_mainItemPanel.putConstraint(SpringLayout.EAST, spinner_2, 0, SpringLayout.EAST, spinner);
		mainItemPanel.add(spinner_2);
	}
}
