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
		JPanel nailPanel = new JPanel();
		frame.setBounds(100, 100, 725, 509);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		
		
		
		
		JComboBox comboBox = new JComboBox();
		springLayout.putConstraint(SpringLayout.NORTH, nailPanel, 6, SpringLayout.SOUTH, comboBox);
		springLayout.putConstraint(SpringLayout.WEST, nailPanel, 0, SpringLayout.WEST, comboBox);
		springLayout.putConstraint(SpringLayout.SOUTH, nailPanel, 440, SpringLayout.SOUTH, comboBox);
		springLayout.putConstraint(SpringLayout.EAST, nailPanel, -353, SpringLayout.EAST, comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String item = comboBox.getSelectedItem().toString();
				
				if(item.equals("Nail"))
				{
					nailPanel.setVisible(true);
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
					nailPanel.setVisible(false);
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Nail", "Tool", "StripNail", "PowerTool"}));
		comboBox.setSelectedIndex(0);
		springLayout.putConstraint(SpringLayout.NORTH, comboBox, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, comboBox, 715, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(comboBox);
		frame.getContentPane().add(nailPanel);
		SpringLayout sl_nailPanel = new SpringLayout();
		nailPanel.setLayout(sl_nailPanel);
		
		
		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		format.setMaximumIntegerDigits(20);
//		formatter.setMaximum(Integer.MAX_VALUE);
		formatter.setAllowsInvalid(false);
		formatter.setCommitsOnValidEdit(true);
		
		
		JLabel lblNewLabel = new JLabel("UPC");
		sl_nailPanel.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, nailPanel);
		sl_nailPanel.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, nailPanel);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		nailPanel.add(lblNewLabel);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Long(0), null, null, new Long(1)));
		sl_nailPanel.putConstraint(SpringLayout.NORTH, spinner, -4, SpringLayout.NORTH, lblNewLabel);
		sl_nailPanel.putConstraint(SpringLayout.WEST, spinner, 6, SpringLayout.EAST, lblNewLabel);
		sl_nailPanel.putConstraint(SpringLayout.SOUTH, spinner, 9, SpringLayout.SOUTH, lblNewLabel);
		sl_nailPanel.putConstraint(SpringLayout.EAST, spinner, 153, SpringLayout.EAST, lblNewLabel);
		nailPanel.add(spinner);
		nailPanel.setVisible(false);
	}
}
