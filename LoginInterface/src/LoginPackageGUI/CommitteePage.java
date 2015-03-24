package LoginPackageGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JSpinner;


public class CommitteePage extends JFrame {

	private JPanel contentPane;
	private JTable eventTable;
	private JTextField typeField;
	private JTextField locationField;
	private JTextField dateAndTimeField;
	private JTextField infoField;
	private JTable memberTable;
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_2;
	
	String[] busSizeArray = {"25", "40", "50"};
	String[] accommodationArray = {"Hostel", "Hotel","B&B"};
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CommitteePage frame = new CommitteePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CommitteePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 1024);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 1244, 964);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 1224, 942);
		panel.add(tabbedPane);
		
		JPanel eventPanel1 = new JPanel();
		tabbedPane.addTab("Event", null, eventPanel1, null);
		eventPanel1.setLayout(null);
		
		JPanel eventPanel2 = new JPanel();
		eventPanel2.setBounds(10, 11, 750, 875);
		eventPanel1.add(eventPanel2);
		eventPanel2.setLayout(null);
		
		eventTable = new JTable();
		eventTable.setBounds(725, 113, -703, 649);
		eventPanel2.add(eventTable);
		
		JButton addEventButton = new JButton("Add Event");
		addEventButton.setBounds(24, 809, 102, 23);
		eventPanel2.add(addEventButton);
		
		JButton editEventButton1 = new JButton("Edit Event");
		editEventButton1.setBounds(326, 809, 89, 23);
		eventPanel2.add(editEventButton1);
		
		JLabel typeLabel = new JLabel("Type");
		typeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		typeLabel.setBounds(24, 41, 71, 16);
		eventPanel2.add(typeLabel);
		
		JLabel locationLabel = new JLabel("Location");
		locationLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		locationLabel.setBounds(200, 42, 81, 14);
		eventPanel2.add(locationLabel);
		
		JLabel dateAndTimeLabel = new JLabel("Date and Time");
		dateAndTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dateAndTimeLabel.setBounds(383, 42, 109, 14);
		eventPanel2.add(dateAndTimeLabel);
		
		JLabel infoLabel = new JLabel("Info");
		infoLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		infoLabel.setBounds(602, 44, 46, 14);
		eventPanel2.add(infoLabel);
		
		JButton removeEventButton = new JButton("Remove Event");
		removeEventButton.setBounds(602, 809, 115, 23);
		eventPanel2.add(removeEventButton);
		
		JPanel eventPanel3 = new JPanel();
		eventPanel3.setBounds(770, 11, 439, 875);
		eventPanel1.add(eventPanel3);
		eventPanel3.setLayout(null);
		
		typeField = new JTextField();
		typeField.setBounds(175, 60, 239, 29);
		eventPanel3.add(typeField);
		typeField.setColumns(10);
		
		locationField = new JTextField();
		locationField.setColumns(10);
		locationField.setBounds(175, 152, 239, 29);
		eventPanel3.add(locationField);
		
		dateAndTimeField = new JTextField();
		dateAndTimeField.setColumns(10);
		dateAndTimeField.setBounds(175, 240, 239, 29);
		eventPanel3.add(dateAndTimeField);
		
		infoField = new JTextField();
		infoField.setColumns(10);
		infoField.setBounds(175, 318, 239, 108);
		eventPanel3.add(infoField);
		
		JLabel typeLabel2 = new JLabel("Type");
		typeLabel2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		typeLabel2.setBounds(50, 64, 71, 16);
		eventPanel3.add(typeLabel2);
		
		JLabel locationLabel2 = new JLabel("Location");
		locationLabel2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		locationLabel2.setBounds(50, 157, 81, 14);
		eventPanel3.add(locationLabel2);
		
		JLabel dateAndTimeLabel2 = new JLabel("Date and Time");
		dateAndTimeLabel2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dateAndTimeLabel2.setBounds(50, 247, 109, 14);
		eventPanel3.add(dateAndTimeLabel2);
		
		JLabel infoLabel2 = new JLabel("Info");
		infoLabel2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		infoLabel2.setBounds(50, 318, 46, 14);
		eventPanel3.add(infoLabel2);
		
		JButton okButton = new JButton("Ok");
		okButton.setBounds(325, 812, 89, 23);
		eventPanel3.add(okButton);
		
		JComboBox busSizeCombo = new JComboBox(busSizeArray);
		busSizeCombo.setBounds(185, 474, 172, 20);
		eventPanel3.add(busSizeCombo);
		
		JComboBox accommodationCombo = new JComboBox(accommodationArray);
		accommodationCombo.setBounds(185, 622, 172, 20);
		eventPanel3.add(accommodationCombo);
		
		JLabel busSizeLabel = new JLabel("Bus Size");
		busSizeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		busSizeLabel.setBounds(50, 475, 97, 14);
		eventPanel3.add(busSizeLabel);
		
		JLabel accommodationLabel = new JLabel("Accommodation");
		accommodationLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		accommodationLabel.setBounds(50, 623, 125, 14);
		eventPanel3.add(accommodationLabel);
		
		JLabel numPeopleLabel = new JLabel("Num People");
		numPeopleLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		numPeopleLabel.setBounds(50, 542, 97, 29);
		eventPanel3.add(numPeopleLabel);
		
		JSpinner numPeopleSpinner = new JSpinner();
		numPeopleSpinner.setBounds(185, 548, 29, 20);
		eventPanel3.add(numPeopleSpinner);
		
		JPanel memberPanel1 = new JPanel();
		tabbedPane.addTab("Member", null, memberPanel1, null);
		memberPanel1.setLayout(null);
		
		JPanel memberPanel2 = new JPanel();
		memberPanel2.setBounds(10, 11, 736, 892);
		memberPanel1.add(memberPanel2);
		memberPanel2.setLayout(null);
		
		JButton addMemButton = new JButton("Add Member");
		addMemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		addMemButton.setBounds(147, 839, 112, 23);
		memberPanel2.add(addMemButton);
		
		JButton removeMemButton = new JButton("Remove Member");
		removeMemButton.setBounds(489, 839, 147, 23);
		memberPanel2.add(removeMemButton);
		
		memberTable = new JTable();
		memberTable.setBounds(10, 799, 694, -715);
		memberPanel2.add(memberTable);
		
		JLabel studentIDLabel = new JLabel("Student ID");
		studentIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		studentIDLabel.setBounds(10, 32, 96, 14);
		memberPanel2.add(studentIDLabel);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nameLabel.setBounds(215, 32, 56, 14);
		memberPanel2.add(nameLabel);
		
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailLabel.setBounds(409, 32, 46, 14);
		memberPanel2.add(emailLabel);
		
		JLabel phoneLabel = new JLabel("Phone");
		phoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		phoneLabel.setBounds(563, 32, 46, 14);
		memberPanel2.add(phoneLabel);
		
		JPanel memberPanel3 = new JPanel();
		memberPanel3.setBounds(756, 11, 453, 892);
		memberPanel1.add(memberPanel3);
		memberPanel3.setLayout(null);
		
		JLabel studentIDLabel2 = new JLabel("Student ID");
		studentIDLabel2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		studentIDLabel2.setBounds(10, 31, 96, 14);
		memberPanel3.add(studentIDLabel2);
		
		textField = new JTextField();
		textField.setBounds(140, 30, 276, 20);
		memberPanel3.add(textField);
		textField.setColumns(10);
		
		JPanel equipmentPanel1 = new JPanel();
		tabbedPane.addTab("Equipment", null, equipmentPanel1, null);
		equipmentPanel1.setLayout(null);
		
		JPanel equipmentPanel2 = new JPanel();
		equipmentPanel2.setBounds(10, 5, 812, 898);
		equipmentPanel1.add(equipmentPanel2);
		equipmentPanel2.setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 799, 770, -717);
		equipmentPanel2.add(table);
		
		JLabel typeEquipLabel = new JLabel("Type");
		typeEquipLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		typeEquipLabel.setBounds(24, 32, 46, 14);
		equipmentPanel2.add(typeEquipLabel);
		
		JLabel stockLabel = new JLabel("Stock");
		stockLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		stockLabel.setBounds(340, 32, 46, 14);
		equipmentPanel2.add(stockLabel);
		
		JLabel costLabel = new JLabel("Cost");
		costLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		costLabel.setBounds(627, 34, 46, 14);
		equipmentPanel2.add(costLabel);
		
		JButton editStockButton = new JButton("Edit Stock");
		editStockButton.setBounds(653, 864, 149, 23);
		equipmentPanel2.add(editStockButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(832, 5, 377, 898);
		equipmentPanel1.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel orderLabel = new JLabel("Order");
		orderLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
		orderLabel.setBounds(165, 11, 54, 14);
		panel_1.add(orderLabel);
		
		JLabel tyoeLabel2 = new JLabel("Type");
		tyoeLabel2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tyoeLabel2.setBounds(48, 63, 46, 14);
		panel_1.add(tyoeLabel2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(136, 62, 208, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel quantityLabel2 = new JLabel("Quantity");
		quantityLabel2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		quantityLabel2.setBounds(48, 126, 64, 14);
		panel_1.add(quantityLabel2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(133, 125, 211, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JButton orderButton = new JButton("Order");
		orderButton.setBounds(255, 189, 89, 23);
		panel_1.add(orderButton);
	}
}
