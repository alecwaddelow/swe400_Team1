package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DeleteItem {

	private JFrame frameDeleteItem;

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
		
		JComboBox comboBox_Item = new JComboBox();
		comboBox_Item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String item = comboBox_Item.getSelectedItem().toString();
				
			}
		});
		comboBox_Item.setModel(new DefaultComboBoxModel(new String[] {"", "Nail", "Tool", "StripNail", "PowerTool"}));
		comboBox_Item.setSelectedIndex(0);
		comboBox_Item.setBounds(230, 12, 484, 31);
		frameDeleteItem.getContentPane().add(comboBox_Item);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 77, 949, 446);
		frameDeleteItem.getContentPane().add(scrollPane);
		
		JPanel panel_ListOfItem = new JPanel();
		scrollPane.setViewportView(panel_ListOfItem);
		
		JButton button_Submit = new JButton("Submit");
		button_Submit.setBounds(715, 535, 117, 51);
		frameDeleteItem.getContentPane().add(button_Submit);
		
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
}
