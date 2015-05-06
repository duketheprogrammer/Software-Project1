package LoginPackageGUI;

import LoginPackageSRC.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.*;


public class AdminScreen implements ActionListener{

	private JFrame frame;
	private JPanel panel1, panel2, panel3, panel4, panel5;
	private JLabel label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11, label12, label13;
	private JTextField box1, box3, box4, box5, box6, box7, split_box1, split_box2, split_box_clubSearch, split_box_clubMemberSearch;
	private JTextArea split_box3, text;
	private JPasswordField passBox1, passBox2, passBox3;
	private JButton button1, button2, button3, button4, button5, split_button1, split_button2, split_button3, split_button4, split_button_refresh, split_button_assignRole, split_button_clubMemberSearch;
	private MenuBar menuBar;
	private MenuItem mI1, mI2, mI3, mI4, mI5, mI6, mI7, mI8;
	private Menu mOpt1, mOpt2;
	private ArrayList<AdminAccount> adminList;
	private ArrayList<MemberAccount> memberList;
	private ArrayList<Club> clubList;
	private Club club;
	private ArrayList<Object[]> list;
	private AdminAccount aA;
	private JSplitPane mainSplitPane, innerSplitPane;
	private JTable table1, table2, table3;
	private DatabaseConnector dbC;
	private ResultSet rs;
	private JDialog d;
	private JComboBox comboBox, combo, comboC;
	private String [] accTypes = {"Admin","Member"};
	private SavingArrayListToFile saveAL;
	private SortArrayList sortAL;
	private JScrollPane sp;
	private WelcomePanel wP;
	private JTabbedPane tabbedPane;
	private String [] selection = {"Recreational", "Competitive", "Recreational/Competitive"};
	private int clubIndex, memberIndex;

	public AdminScreen(JFrame frame, ArrayList<AdminAccount> adminList, ArrayList<Club> clubList, ArrayList<MemberAccount> memberList, Account a) {
		// TODO Auto-generated constructor stub
		this.adminList = adminList; 
		this.clubList = clubList;
		this.memberList = memberList;
		this.frame = frame;
		this.aA = (AdminAccount) a;
		initialize();
		displayClubListInTable();
	}

	private void initialize(){
		frame.setTitle("Admin Screen");

		/*frame = new JFrame("Admin Screen");
		frame.setSize(1000,695);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);*/

		wP = new WelcomePanel();
		wP.setLayout(null);
		wP.setBounds(0,0,1365,764);
		frame.getContentPane().add(wP);

		panel1 = new JPanel(){
			@Override
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawImage(getImage(), 0,0, wP.getWidth(), wP.getHeight(), null);
			}

			public Image getImage(){
				ImageIcon i = new ImageIcon(getClass().getResource("/Images/white.jpg"));
				return i.getImage();
			}
		};
		panel1.setLayout(null);
		panel1.setBounds(399, 11, 519, 44);
		wP.add(panel1);

		label1 = new JLabel(aA.getFName() + " " + aA.getLName() + "'s " + aA.getAccType() + " Page");
		label1.setFont(new Font("SimSun", Font.BOLD, 22));
		label1.setBounds(0,0,498,45);
		panel1.add(label1);

	
		panel2 = new JPanel(){
			@Override
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawImage(getImage(), 0,0, wP.getWidth(), wP.getHeight(), null);
			}

			public Image getImage(){
				ImageIcon i = new ImageIcon(getClass().getResource("/Images/white.jpg"));
				return i.getImage();
			}
		};
		panel2.setLayout(null);
		panel2.setBounds(21,93,1306,614);
		wP.add(panel2);
		
		mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		mainSplitPane.setOneTouchExpandable(true);
		mainSplitPane.setBounds(0,0,1306,614);
		panel2.add(mainSplitPane);
		
			innerSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			innerSplitPane.setOneTouchExpandable(true);
			innerSplitPane.setBounds(0,0,200,200);
			mainSplitPane.setTopComponent(innerSplitPane);
			
				panel3 = new JPanel();
				panel3.setBounds(0,0,200,200);
				panel3.setLayout(null);
				innerSplitPane.setTopComponent(panel3);
				
				label1 = new JLabel("Club ID:");
				label1.setFont(new Font("SimSun", Font.PLAIN, 14));
				label1.setBounds(10,34,74,30);
				panel3.add(label1);
				
				split_box1 = new JTextField();
				split_box1.setBounds(152, 35, 119, 30);
				panel3.add(split_box1);
				
				label2 = new JLabel("Club Name:");
				label2.setFont(new Font("SimSun", Font.PLAIN, 14));
				label2.setBounds(10,75,74,30);
				panel3.add(label2);
				
				split_box2 = new JTextField();
				split_box2.setBounds(152, 75, 119, 30);
				panel3.add(split_box2);
				
				label3 = new JLabel("Club Description:");
				label3.setFont(new Font("SimSun", Font.PLAIN, 14));
				label3.setBounds(10,116,119,30);
				panel3.add(label3);
				
				split_box3 = new JTextArea();
				split_box3.setLineWrap(true);
				split_box3.setWrapStyleWord(true);
				sp = new JScrollPane(split_box3);
				sp.setBounds(152, 116, 224, 66);
				panel3.add(sp);
				
				label4 = new JLabel("Club Type:");
				label4.setFont(new Font("SimSun", Font.PLAIN, 14));
				label4.setBounds(10,192,74,30);
				panel3.add(label4);
				
				combo = new JComboBox(selection);
				combo.setBounds(152,193,119,30);
				combo.addActionListener(this);
				panel3.add(combo);
				
				split_button1 = new JButton("Add Club");
				split_button1.setBounds(77,271,119,30);
				split_button1.addActionListener(this);
				panel3.add(split_button1);
				
				panel4 = new JPanel();
				panel4.setLayout(null);
				innerSplitPane.setBottomComponent(panel4);
				
				split_box_clubSearch = new JTextField();
				split_box_clubSearch.setBounds(10,22,197,30);
				panel4.add(split_box_clubSearch);
				
				split_button2 = new JButton("Search Club By Name");
				split_button2.setBounds(217,22,156,30);
				split_button2.addActionListener(this);
				panel4.add(split_button2);
				
				split_button_refresh = new JButton("Refresh ClubList");
				split_button_refresh.setBounds(736,22,119,30);
				split_button_refresh.addActionListener(this);
				panel4.add(split_button_refresh);
				
				table1 = new JTable();
				table1.setFillsViewportHeight(true);
				table1.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {"CLUB_ID", "CLUB_NAME", "CLUB_DESCRIPTION", "CLUB_TYPE"}));
				table1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						int index = table1.rowAtPoint(e.getPoint());
						
						if(index != -1){
							clubIndex = index;
							displayClubMembers(index);
						}
					}
				});	
				sp = new JScrollPane(table1);
				sp.setBounds(10,63,845,358);
				panel4.add(sp);
				
				split_button3 = new JButton("Edit Club");
				split_button3.setBounds(10,432,119,30);
				split_button3.addActionListener(this);
				panel4.add(split_button3);
				
				split_button4 = new JButton("Delete Club");
				split_button4.setBounds(736,432,119,30);
				split_button4.addActionListener(this);
				panel4.add(split_button4);
			
			panel5 = new JPanel();
			panel5.setLayout(null);
			mainSplitPane.setBottomComponent(panel5);
			
				split_box_clubMemberSearch = new JTextField();
				split_box_clubMemberSearch.setBounds(10,22,197,30);
				panel5.add(split_box_clubMemberSearch);
			
				table2 = new JTable();
				table2.setFillsViewportHeight(true);
				table2.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {"CLUB_MEMBER_ID", "CLUB_MEMBER_FNAME", "CLUB_MEMBER_LNAME", "CLUB_MEMBER_EMAIL", "CLUB_MEMBER_PNO"}));
				sp = new JScrollPane(table2);
				sp.setBounds(10,63,845,358);
				panel5.add(sp);
				
				split_button_assignRole = new JButton("Assign Role");
				split_button_assignRole.addActionListener(this);
				split_button_assignRole.setBounds(10,432,135,30);
				panel5.add(split_button_assignRole);
				
				split_button_clubMemberSearch = new JButton("Search Member By Name");
				split_button_clubMemberSearch.addActionListener(this);
				split_button_clubMemberSearch.setBounds(217,22,171,30);
				panel5.add(split_button_clubMemberSearch);
		
		menuBar = new MenuBar();

			mOpt1 = new Menu("File");
			menuBar.add(mOpt1);
	
			mI1 = new MenuItem("Save Account List");
			mI1.addActionListener(this);
			mOpt1.add(mI1);
	
			mI2 = new MenuItem("Load Account List");
			mI2.addActionListener(this);
			mOpt1.add(mI2);
	
			mI3 = new MenuItem("Logout");
			mI3.addActionListener(this);
			mOpt1.add(mI3);

		frame.setMenuBar(menuBar);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		String action = e.getActionCommand();

		if(action.equals("Logout")){
			int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?","Confirm Action", JOptionPane.YES_NO_OPTION);
			if(reply == JOptionPane.YES_OPTION){
				//frame.getContentPane().removeAll();
				frame.getContentPane().repaint();
				LoginScreen lS = new LoginScreen(frame, adminList, clubList, memberList);
			}
		}
		
		if(action.equals("Add Club")){
			int iD; String id, name, description, clubType;
			
			id = split_box1.getText();
			iD = Integer.parseInt(id);
			name = split_box2.getText();
			description = split_box3.getText();
			clubType = (String)combo.getSelectedItem();
			
			Club c = new Club(iD, name, description, clubType,true);
			clubList.add(c);
			JOptionPane.showMessageDialog(null, "The " + name + " club has been added");
			split_box1.setText("");
			split_box2.setText("");
			split_box3.setText("");
			displayClubListInTable();
		}
		
		if(action.equals("Search Club By Name")){
			String name = split_box_clubSearch.getText();
		
			boolean found = displayClubSearch(name);
			if(found == false){
				JOptionPane.showMessageDialog(null, "The club you were looking for doesn't exist " +
													"\n or has the name spelt wrong, " +
													"\n Try again");
			}
		}
		
		if(action.equals("Refresh ClubList")){
			displayClubListInTable();
		}
		
		if(action.equals("Edit Club")){
			initializeClubDialog();
		}
		
		if(action.equals("Delete Club")){
			
			int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + clubList.get(clubIndex).getClubName() + " club?","Confirm Action", JOptionPane.YES_NO_OPTION);
			if(reply == JOptionPane.YES_OPTION){
				clubList.remove(clubIndex);
				JOptionPane.showMessageDialog(null, "Club has been deleted!");
			}
				
		}
		
		if(action.equals("Search Member By Name")){
			String memberName = split_box_clubMemberSearch.getText();
			
			boolean found = displayMemberSearch(memberName);
			if(found == false){
				JOptionPane.showMessageDialog(null, "The member you were looking for doesn't exist " +
													"\n or has the name spelt wrong, " +
													"\n Try again");
			}
		}
		
		if(action.equals("Assign Role")){
			initializeAssignRole();
		}
		
		if(action.equals("Update")){
			String clubName = box4.getText(), clubDescription = text.getText(), clubType = (String)comboC.getSelectedItem();
		
			clubList.get(clubIndex).setClubName(clubName);
			clubList.get(clubIndex).setClubDescription(clubDescription);
			clubList.get(clubIndex).setClubType(clubType);
			
			d.dispose();
			JOptionPane.showMessageDialog(null, "Club Details Updated");
			displayClubListInTable();
		}
	}
	
	private void initializeAssignRole() {
		// TODO Auto-generated method stub
		
	}

	private boolean displayMemberSearch(String memberName){
		boolean found = false;
		

		
		return found;
	}
	
	private boolean displayClubSearch(String name){
		boolean found = false;
		list = new ArrayList<Object[]>();
		for(int i = 0; i < clubList.size(); i++){
			if(name.equals(clubList.get(i).getClubName())){
				list.add(new Object[] {
						clubList.get(i).getClubID(),
						clubList.get(i).getClubName(),
						clubList.get(i).getClubDescription(),
						clubList.get(i).getClubType()
				});
				found = true;
			}
		}
		table1.setModel(new DefaultTableModel(list.toArray(new Object[][] {}), 
				new String[] {"CLUB_ID", "CLUB_NAME", "CLUB_DESCRIPTION", "CLUB_TYPE"}));
		
		return found;
	}
	
	private void displayClubMembers(int index) {
		// TODO Auto-generated method stub
		list = new ArrayList<Object[]>();
		Club c = clubList.get(index);
		
		for(int i = 0; i < c.getMemberSize(); i++){
			list.add(new Object[]{
					c.getMember(i).getUserName(),
					c.getMember(i).getFName(),
					c.getMember(i).getLName(),
					c.getMember(i).getEmail(),
					c.getMember(i).getPNo()
			});
		}

		table2.setModel(new DefaultTableModel(list.toArray(new Object[][] {}), 
				new String[] {"CLUB_MEMBER_ID", "CLUB_MEMBER_FNAME", "CLUB_MEMBER_LNAME", "CLUB_MEMBER_EMAIL", "CLUB_MEMBER_PNO"}));
	}

	private void displayClubListInTable() {
		// TODO Auto-generated method stub
		list = new ArrayList<Object[]>();
		for(int i = 0; i < clubList.size(); i++){
			list.add(new Object[] {
					clubList.get(i).getClubID(),
					clubList.get(i).getClubName(),
					clubList.get(i).getClubDescription(),
					clubList.get(i).getClubType()
			});
		}
		table1.setModel(new DefaultTableModel(list.toArray(new Object[][] {}), 
				new String[] {"CLUB_ID", "CLUB_NAME", "CLUB_DESCRIPTION", "CLUB_TYPE"}));
	}

	private void initializeClubDialog() {
		// TODO Auto-generated method stub
		d = new JDialog();
		d.setSize(435, 400);
		d.getContentPane().setLayout(null);
		d.setVisible(true);

		label6 = new JLabel("Edit Club");
		label6.setFont(new Font("SimSun", Font.PLAIN, 16));
		label6.setBounds(10,11,144,38);
		d.getContentPane().add(label6);


		label8 = new JLabel("Club Name:");
		label8.setBounds(50, 60, 91, 29);
		d.getContentPane().add(label8);

		box4 = new JTextField();
		box4.setText(clubList.get(clubIndex).getClubName());
		box4.setBounds(165,59, 101, 30);
		d.getContentPane().add(box4);

		label9 = new JLabel("Club Description");
		label9.setBounds(50,100,91,29);
		d.getContentPane().add(label9);
		sp = new JScrollPane();
		sp.setBounds(165,100, 179, 72);
		d.getContentPane().add(sp);
		
				text = new JTextArea();
				sp.setViewportView(text);
				text.setLineWrap(true);
				text.setWrapStyleWord(true);
				text.setText(clubList.get(clubIndex).getClubDescription());
		
		label10 = new JLabel("Club Type");
		label10.setBounds(50,205,91,30);
		d.getContentPane().add(label10);
		
		comboC = new JComboBox(selection);
		comboC.setBounds(165,205,123,30);
		comboC.addActionListener(this);
		d.getContentPane().add(comboC);

		button3 = new JButton("Update");
		button3.addActionListener(this);
		button3.setBounds(132, 294, 123, 30);
		d.getContentPane().add(button3);
	}

	private void initalizeDialog() {
		// TODO Auto-generated method stub
		d = new JDialog();
		d.setSize(280, 460);
		d.getContentPane().setLayout(null);
		d.setAlwaysOnTop(true);
		d.setVisible(true);

		label3 = new JLabel("Create Account");
		label3.setFont(new Font("SimSun", Font.PLAIN, 16));
		label3.setBounds(10,11,144,38);
		d.getContentPane().add(label3);

		label3 = new JLabel("Username:");
		label3.setBounds(32,60,91,29);
		d.getContentPane().add(label3);

		box3 = new JTextField();
		box3.setBounds(133,60,108,29);
		d.getContentPane().add(box3);

		label4 = new JLabel("Password:");
		label4.setBounds(32,100,91,29);
		d.getContentPane().add(label4);

		passBox1 = new JPasswordField();
		passBox1.setBounds(133,100,108,29);
		d.getContentPane().add(passBox1);

		label5 = new JLabel("Confirm Password:");
		label5.setBounds(32,140,94,29);
		d.getContentPane().add(label5);

		passBox2 = new JPasswordField();
		passBox2.setBounds(133,140,108,29);
		d.getContentPane().add(passBox2);

		label6 = new JLabel("Email:");
		label6.setBounds(32,180,91,29);
		d.getContentPane().add(label6);

		box4 = new JTextField();
		box4.setBounds(133,180,108,29);
		d.getContentPane().add(box4);

		label7 = new JLabel("First Name:");
		label7.setBounds(32,220,91,29);
		d.getContentPane().add(label7);

		box5 = new JTextField();
		box5.setBounds(133,220,108,29);
		d.getContentPane().add(box5);

		label8 = new JLabel("Last Name:");
		label8.setBounds(32,260,91,29);
		d.getContentPane().add(label8);

		box6 = new JTextField();
		box6.setBounds(133,260,108,29);
		d.getContentPane().add(box6);

		label9 = new JLabel("Phone Number:");
		label9.setBounds(32,300,91,29);
		d.getContentPane().add(label9);

		box7 = new JTextField();
		box7.setBounds(133,300,108,29);
		d.getContentPane().add(box7);

		label10 = new JLabel("Account Type:");
		label10.setBounds(32,340,91,29);
		d.getContentPane().add(label10);

		comboBox = new JComboBox(accTypes);
		comboBox.setBounds(133,340,108,29);
		comboBox.setSelectedIndex(1);
		comboBox.addActionListener(this);
		d.getContentPane().add(comboBox);

		button5 = new JButton("Create");
		button5.addActionListener(this);
		button5.setBounds(81,382,108,29);
		d.getContentPane().add(button5);
	}
}
