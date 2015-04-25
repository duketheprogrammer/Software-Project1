package LoginPackageGUI;

import LoginPackageSRC.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.table.*;
import javax.swing.event.*;

public class MemberScreen implements ActionListener{

	private JFrame frame;
	private JPanel panel1, panel2, eventPanel, membersPanel,equipmentPanel, mini_panel1, mini_panel2, mini_panel3, mini_panel4, mini_panel5, mini_panel6;
	private JTabbedPane mainTabbedPane, comm_tabbedPane;
	private JLabel label1, label2, label3, label4, mini_label1, mini_label2, mini_label3, mini_label4, mini_label5, mini_label6, mini_label7, comm_D_label1;
	private JTextField mini_box1, mini_box2, mini_box3, mini_box4, mini_box5, mini_box6, mini_box7, box5, box6, box7;
	private JTextArea mini_textArea;
	private JTextArea box2;
	private JPasswordField passBox1, passBox2, passBox3;
	private JButton m_button1, m_button2, m_button3, mini_button1, mini_button2, mini_button3, mini_button4, mini_button5, mini_button6, mini_button7, comm_D_button3, comm_D_button4;
	private MenuBar menuBar;
	private MenuItem mI1, mI2, mI3, mI4, mI5, mI6, mI7, mI8;
	private Menu mOpt1, mOpt2;
	private ArrayList<AdminAccount> adminList;
	private ArrayList<MemberAccount> memberList;
	private ArrayList<Club> clubList;
	private MemberAccount mA;
	private JTable m_table1, m_table2, m_table3, comm_table1, comm_table2, comm_table3, comm_table4;
	private JScrollPane sp, sp1, sp2;
	private WelcomePanel wP;
	private JSplitPane splitPane, doubleSplitPane, doubleSplitPane1, doubleSplitPane2,  splitPane1, splitPane2;
	private JDialog d;
	private JComboBox comboBox;

	public MemberScreen(JFrame frame, ArrayList<AdminAccount> adminList, ArrayList<Club> clubList, ArrayList<MemberAccount> memberList, Account a){
		this.frame = frame;
		this.adminList = adminList;
		this.clubList = clubList;
		this.memberList = memberList;
		this.mA = (MemberAccount) a;
		initialize();
	}

	private void initialize(){
		frame.setTitle("Member Screen");

		wP = new WelcomePanel();
		wP.setBounds(0,0,1365,764);
		frame.getContentPane().add(wP);
		wP.setLayout(null);


		mainTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		/*mainTabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
			// note at this point the tab will be on another pane
			//identify current pane
				int selectedPane = mainTabbedPane.getSelectedIndex();


			if (mA.getClub().getClub) {
					mainTabbedPane.setSelectedIndex(lastPane);
				}
				lastPane = selectedPane;
			}
		});*/
		mainTabbedPane.setBounds(24, 11, 1313, 720);
		wP.add(mainTabbedPane);

		panel1 = new JPanel(){
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawImage(getImage(), 0,0, mainTabbedPane.getWidth(), mainTabbedPane.getHeight(), null);
			}

			public Image getImage(){
				ImageIcon i = new ImageIcon(getClass().getResource("/Images/white.jpg"));
				return i.getImage();
			}
		};
		panel1.setLayout(null);
		mainTabbedPane.add(panel1, "MEMBER");

		label1 = new JLabel(mA.getFName() + " " + mA.getLName() + "'s " + mA.getAccType() + " Page");
		label1.setFont(new Font("SimSun", Font.BOLD, 22));
		label1.setBounds(359,14,447,47);
		panel1.add(label1);

		label2 = new JLabel("Clubs & Societies Joined");
		label2.setFont(new Font("SimSun", Font.PLAIN, 14));
		label2.setBounds(204,51,215,32);
		panel1.add(label2);

		m_table1 = new JTable();
		m_table1.setFillsViewportHeight(true);
		m_table1.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {"CLUB_ID", "CLUB_NAME", "REC/COMP", "CLUB_DESCRIPTION" }));
		sp = new JScrollPane(m_table1);
		sp.setBounds(20,84,588,255);
		panel1.add(sp);

		m_button1 = new JButton("De-Register From Club");
		m_button1.addActionListener(this);
		m_button1.setBounds(30,350,162,32);
		panel1.add(m_button1);

		label3 = new JLabel("Events for Club");
		label3.setFont(new Font("SimSun", Font.PLAIN, 14));
		label3.setBounds(941,51,215,32);
		panel1.add(label3);

		m_table2 = new JTable();
		m_table2.setFillsViewportHeight(true);
		m_table2.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {"EVENT_TYPE", "EVENT_LOCATION", "TIME & DATE", "EVENT_INFO"}));
		sp = new JScrollPane(m_table2);
		sp.setBounds(710,84,570,255);
		panel1.add(sp);

		label4 = new JLabel("Available Clubs & Societies");
		label4.setFont(new Font("SimSun", Font.PLAIN, 14));
		label4.setBounds(524,350,215,32);
		panel1.add(label4);

		m_table3 = new JTable();
		m_table3.setFillsViewportHeight(true);
		m_table3.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {"CLUB_ID", "CLUB_NAME", "REC/COMP", "CLUB_DESCRIPTION" }));
		sp = new JScrollPane(m_table3);
		sp.setBounds(343,383,588,255);
		panel1.add(sp);

		m_button2 = new JButton("Register For Club");
		m_button2.addActionListener(this);
		m_button2.setBounds(785,649,146,32);
		panel1.add(m_button2);

		panel2 = new JPanel(){
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawImage(getImage(), 0,0, mainTabbedPane.getWidth(), mainTabbedPane.getHeight(), null);
			}

			public Image getImage(){
				ImageIcon i = new ImageIcon(getClass().getResource("/Images/white.jpg"));
				return i.getImage();
			}
		};
		panel2.setLayout(null);
		mainTabbedPane.add(panel2, "COMMITTEE");

		comm_tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		comm_tabbedPane.setBounds(0,0,1308,692);
		panel2.add(comm_tabbedPane);

		eventPanel = new JPanel(){
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawImage(getImage(), 0,0, mainTabbedPane.getWidth(), mainTabbedPane.getHeight(), null);
			}

			public Image getImage(){
				ImageIcon i = new ImageIcon(getClass().getResource("/Images/white.jpg"));
				return i.getImage();
			}
		};	
		eventPanel.setLayout(null);
		comm_tabbedPane.add(eventPanel, "EVENTS");

		doubleSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		doubleSplitPane.setOneTouchExpandable(true);
		doubleSplitPane.setBounds(28,11,1125,570);
		eventPanel.add(doubleSplitPane);


		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setOneTouchExpandable(true);
		splitPane.setBounds(10,11,629,522);
		doubleSplitPane.setTopComponent(splitPane);

		sp = new JScrollPane();
		splitPane.setLeftComponent(sp);

		comm_table1 = new JTable();
		sp.setViewportView(comm_table1);
		comm_table1.setFillsViewportHeight(true);
		comm_table1.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {"EVENT_TYPE", "EVENT_LOCATION", "TIME & DATE", "EVENT_INFO"}));

		mini_panel1 = new JPanel();
		mini_panel1.setLayout(null);

		mini_label1 = new JLabel("Type:");
		mini_label1.setFont(new Font("SimSun", Font.PLAIN, 14));
		mini_label1.setBounds(10,5,69,33);
		mini_panel1.add(mini_label1);

		mini_box1 = new JTextField();
		mini_box1.setBounds(89,5,200,33);
		mini_panel1.add(mini_box1);

		mini_label2 = new JLabel("Location:");
		mini_label2.setFont(new Font("SimSun", Font.PLAIN, 14));
		mini_label2.setBounds(10,49,69,33);
		mini_panel1.add(mini_label2);

		mini_box2 = new JTextField();
		mini_box2.setBounds(89,49,200,33);
		mini_panel1.add(mini_box2);

		mini_label3 = new JLabel("Time & Date");
		mini_label3.setFont(new Font("SimSun", Font.PLAIN, 14));
		mini_label3.setBounds(10,95,92,33);
		mini_panel1.add(mini_label3);

		mini_box3 = new JTextField();
		mini_box3.setBounds(89,93,200,33);
		mini_panel1.add(mini_box3);

		mini_label4 = new JLabel("Event Info");
		mini_label4.setFont(new Font("SimSun", Font.PLAIN, 14));
		mini_label4.setBounds(10,143,92,33);
		mini_panel1.add(mini_label4);

		mini_textArea = new JTextArea();
		mini_textArea.setLineWrap(true);
		mini_textArea.setWrapStyleWord(true);
		sp = new JScrollPane(mini_textArea);
		sp.setBounds(89,137,200,100);
		mini_panel1.add(sp);

		splitPane.setRightComponent(mini_panel1);
		splitPane.setResizeWeight(0.5);


		mini_panel2 = new JPanel();
		mini_panel2.setLayout(null);

		mini_button1 = new JButton("Add Event");
		mini_button1.addActionListener(this);
		mini_button1.setBounds(10,11,147,33);
		mini_panel2.add(mini_button1);

		mini_button2 = new JButton("Edit Event");
		mini_button2.addActionListener(this);
		mini_button2.setBounds(406,11,147,33);
		mini_panel2.add(mini_button2);

		mini_button3 = new JButton("Delete Event");
		mini_button3.addActionListener(this);
		mini_button3.setBounds(741,11,147,33);
		mini_panel2.add(mini_button3);

		doubleSplitPane.setResizeWeight(0.5);
		doubleSplitPane.setBottomComponent(mini_panel2);

		membersPanel = new JPanel(){
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawImage(getImage(), 0,0, mainTabbedPane.getWidth(), mainTabbedPane.getHeight(), null);
			}

			public Image getImage(){
				ImageIcon i = new ImageIcon(getClass().getResource("/Images/white.jpg"));
				return i.getImage();
			}
		};
		membersPanel.setLayout(null);
		comm_tabbedPane.add(membersPanel, "MEMBERS");


		doubleSplitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		doubleSplitPane1.setOneTouchExpandable(true);
		doubleSplitPane1.setBounds(28,11,1125,570);
		membersPanel.add(doubleSplitPane1);


		splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane1.setOneTouchExpandable(true);
		splitPane1.setBounds(10,11,629,522);
		doubleSplitPane1.setTopComponent(splitPane1);

		sp1 = new JScrollPane();
		splitPane1.setLeftComponent(sp1);

		comm_table3 = new JTable();
		sp1.setViewportView(comm_table3);
		comm_table3.setFillsViewportHeight(true);
		comm_table3.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {"ID", "Name", "Email", "Phone"}));

		mini_panel3 = new JPanel();
		mini_panel3.setLayout(null);

		mini_label5 = new JLabel("Student No:");
		mini_label5.setFont(new Font("SimSun", Font.PLAIN, 14));
		mini_label5.setBounds(10,5,119,33);
		mini_panel3.add(mini_label5);

		mini_box5 = new JTextField();
		mini_box5.setBounds(110,5,192,33);
		mini_panel3.add(mini_box5);

		splitPane1.setRightComponent(mini_panel3);
		splitPane1.setResizeWeight(0.5);

		mini_panel4 = new JPanel();
		mini_panel4.setLayout(null);

		mini_button4 = new JButton("Add Member");
		mini_button4.addActionListener(this);
		mini_button4.setBounds(151,13,147,33);
		mini_panel4.add(mini_button4);

		mini_button5 = new JButton("Remove Member");
		mini_button5.addActionListener(this);
		mini_button5.setBounds(444,13,147,33);
		mini_panel4.add(mini_button5);

		doubleSplitPane1.setResizeWeight(0.5);
		doubleSplitPane1.setBottomComponent(mini_panel4);

		equipmentPanel = new JPanel(){
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawImage(getImage(), 0,0, mainTabbedPane.getWidth(), mainTabbedPane.getHeight(), null);
			}

			public Image getImage(){
				ImageIcon i = new ImageIcon(getClass().getResource("/Images/white.jpg"));
				return i.getImage();
			}
		};
		equipmentPanel.setLayout(null);
		comm_tabbedPane.add(equipmentPanel, "EQUIPMENT");


		doubleSplitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		doubleSplitPane2.setOneTouchExpandable(true);
		doubleSplitPane2.setBounds(28,11,1125,570);
		equipmentPanel.add(doubleSplitPane2);


		splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane2.setOneTouchExpandable(true);
		splitPane2.setBounds(10,11,629,522);
		doubleSplitPane2.setTopComponent(splitPane2);

		sp2 = new JScrollPane();
		splitPane2.setLeftComponent(sp2);

		comm_table4 = new JTable();
		sp2.setViewportView(comm_table4);
		comm_table4.setFillsViewportHeight(true);
		comm_table4.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {"Type", "Stock", "Cost"}));

		mini_panel5 = new JPanel();
		mini_panel5.setLayout(null);

		mini_label6 = new JLabel("Type:");
		mini_label6.setFont(new Font("SimSun", Font.PLAIN, 14));
		mini_label6.setBounds(10,5,69,33);
		mini_panel5.add(mini_label6);

		mini_box6 = new JTextField();
		mini_box6.setBounds(89,5,200,33);
		mini_panel5.add(mini_box6);

		mini_label7 = new JLabel("Quantity:");
		mini_label7.setFont(new Font("SimSun", Font.PLAIN, 14));
		mini_label7.setBounds(10,50,69,33);
		mini_panel5.add(mini_label7);

		mini_box7 = new JTextField();
		mini_box7.setBounds(89,50,200,33);
		mini_panel5.add(mini_box7);

		splitPane2.setRightComponent(mini_panel5);
		splitPane2.setResizeWeight(0.5);


		mini_panel6 = new JPanel();
		mini_panel6.setLayout(null);

		mini_button6 = new JButton("Edit Stock");
		mini_button6.addActionListener(this);
		mini_button6.setBounds(10,11,147,33);
		mini_panel6.add(mini_button6);

		mini_button7 = new JButton("Order");
		mini_button7.addActionListener(this);
		mini_button7.setBounds(879,11,147,33);
		mini_panel6.add(mini_button7);

		doubleSplitPane2.setResizeWeight(0.5);
		doubleSplitPane2.setBottomComponent(mini_panel6);

		menuBar = new MenuBar();
		mOpt1 = new Menu("File"); //Create new Menu
		menuBar.add(mOpt1);

		mI1 = new MenuItem("Logout");
		mI1.addActionListener(this);
		mOpt1.add(mI1);

		frame.setMenuBar(menuBar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String action = e.getActionCommand();

		if(action.equals("Logout")){
			int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?","Confirm Action", JOptionPane.YES_NO_OPTION);
			if(reply == JOptionPane.YES_OPTION){
				frame.getContentPane().removeAll();
				LoginScreen lS = new LoginScreen(frame, adminList, clubList, memberList);
			}
		}

		if(action.equals("De-Register From Club")){
			JOptionPane.showMessageDialog(null, "Still under construction");
		}
		if(action.equals("Register For Club")){
			JOptionPane.showMessageDialog(null, "Still under construction");
		}
		if(action.equals("Cancel Committee Check")){
			d.dispose();
		}
		if(action.equals("Check Committee Status")){
			String clubName = (String)comboBox.getSelectedItem(); 
			JOptionPane.showMessageDialog(null, clubName);
		}
	}

	private void initalizeCommitteeCheckerDialog(){
		d = new JDialog();
		d.setAlwaysOnTop(true);
		d.setSize(449, 205);
		d.setResizable(false);
		d.setFocusable(true);
		d.getContentPane().setLayout(null);
		d.setVisible(true);

		comm_D_label1 = new JLabel("Choose Club");
		comm_D_label1.setFont(new Font("SimSun", Font.PLAIN, 16));
		comm_D_label1.setBounds(136,11,144,38);
		d.getContentPane().add(comm_D_label1);


		String [] clubListNames = new String [mA.getNoOfClubs()];
		for(int i = 0; i< clubListNames.length; i++){
			clubListNames[i] = mA.getClub(i).getClubName();
		}

		comboBox = new JComboBox(clubListNames);
		comboBox.setBounds(136,60,165,38);
		comboBox.setSelectedIndex(1);
		comboBox.addActionListener(this);
		d.getContentPane().add(comboBox);

		comm_D_button3 = new JButton("Cancel Committee Check");
		comm_D_button3.addActionListener(this);
		comm_D_button3.setBounds(10,137,108,29);
		d.getContentPane().add(comm_D_button3);

		comm_D_button4 = new JButton("Check Committee Status");
		comm_D_button4.addActionListener(this);
		comm_D_button4.setBounds(298,137,108,29);
		d.getContentPane().add(comm_D_button4);

	}
}

