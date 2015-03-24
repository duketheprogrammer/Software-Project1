package LoginPackageGUI;

import LoginPackageSRC.*;

import com.mysql.jdbc.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.*;


public class AdminScreen implements ActionListener{

	private JFrame frame;
	private JPanel panel1, panel2, panel3, panel4;
	private JLabel label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11, label12, label13;
	private JTextField box1, box3, box4, box5, box6, box7;
	private JTextArea box2, text;
	private JPasswordField passBox1, passBox2, passBox3;
	private JButton button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13, button14, button15;
	private MenuBar menuBar;
	private MenuItem mI1, mI2, mI3, mI4, mI5, mI6, mI7, mI8;
	private Menu mOpt1, mOpt2;
	private ArrayList<AdminAccount> adminList;
	private ArrayList<MemberAccount> memberList;
	private ArrayList<Club> clubList;
	private Club club;
	private ArrayList<Object[]> list;
	private AdminAccount aA;
	private JTable table1, table2, table3;
	private DatabaseConnector dbC;
	private ResultSet rs;
	private JDialog d;
	private JComboBox comboBox;
	private String [] accTypes = {"Admin", "Committee" ,"Member"};
	private SavingArrayListToFile saveAL;
	private SortArrayList sortAL;
	private JScrollPane sp;
	private WelcomePanel wP;
	private JTabbedPane tabbedPane;

	public AdminScreen(JFrame frame, ArrayList<AdminAccount> adminList, ArrayList<Club> clubList, ArrayList<MemberAccount> memberList, Account a) {
		// TODO Auto-generated constructor stub
		this.adminList = adminList; 
		this.clubList = clubList;
		this.frame = frame;
		this.aA = (AdminAccount) a;
		initialize();

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
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawImage(getImage(), 0,0, 520, 300, null);
			}

			public Image getImage(){
				ImageIcon i = new ImageIcon(getClass().getResource("/Images/white.jpg"));
				return i.getImage();
			}
		};
		panel1.setBounds(28,24,512,292);
		panel1.setLayout(null);
		wP.add(panel1);

		label1 = new JLabel(aA.getFName() + " " + aA.getLName() + "'s " + aA.getAccType() + " Page");
		label1.setFont(new Font("SimSun", Font.BOLD, 22));
		label1.setBounds(10,0,447,59);
		panel1.add(label1);

		button5 = new JButton("Add Account");
		button5.addActionListener(this);
		button5.setBounds(20,86,155,33);
		panel1.add(button5);

		button11 = new JButton("Edit Account");
		button11.addActionListener(this);
		button11.setBounds(20, 130, 155, 33);
		panel1.add(button11);

		button6 = new JButton("Remove Account");
		button6.addActionListener(this);
		button6.setBounds(20,174, 155,33);
		panel1.add(button6);

		button10 = new JButton("Add Club");
		button10.addActionListener(this);
		button10.setBounds(185,86,155,33);
		panel1.add(button10);

		button7 = new JButton("Connect to Database");
		button7.addActionListener(this);
		button7.setBounds(347, 86, 155, 33);
		panel1.add(button7);

		button8 = new JButton("Close Database");
		button8.addActionListener(this);
		button8.setBounds(347, 130, 155, 33);
		panel1.add(button8);

		button9 = new JButton("Display AccountList");
		button9.addActionListener(this);
		button9.setBounds(20, 218, 155,33);
		panel1.add(button9);

		button12 = new JButton("Check Clubs");
		button12.addActionListener(this);
		button12.setBounds(185, 174, 155, 33);
		panel1.add(button12);

		button13 = new JButton("Remove Club");
		button13.addActionListener(this);
		button13.setBounds(185,130,155,33);
		panel1.add(button13);

		panel2 = new JPanel(){
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawImage(getImage(), 0,0, 415, 300, null);
			}

			public Image getImage(){
				ImageIcon i = new ImageIcon(getClass().getResource("/Images/white.jpg"));
				return i.getImage();
			}
		};
		panel2.setBounds(723,327,410,292);
		panel2.setLayout(null);
		wP.add(panel2);

		label1 = new JLabel("SQL - Query Statements");
		label1.setBounds(10,0,144,25);
		panel2.add(label1);

		box1 = new JTextField();
		box1.setBounds(10,25,272,51);
		panel2.add(box1);

		button1 = new JButton("Send Query");
		button1.addActionListener(this);
		button1.setBounds(10,87,133,33);
		panel2.add(button1);

		button2 = new JButton("Download Data");
		button2.addActionListener(this);
		button2.setBounds(153,87,129,33);
		panel2.add(button2);

		label2 = new JLabel("SQL - Update Database Table");
		label2.setBounds(10,131,165,25);
		panel2.add(label2);

		box2 = new JTextArea();
		box2.setLineWrap(true);
		box2.setWrapStyleWord(true);
		sp = new JScrollPane(box2);
		sp.setBounds(10,161,272,76);
		panel2.add(sp);

		button3 = new JButton("Update Database");
		button3.setBounds(10, 248, 137, 33);
		button3.addActionListener(this);
		panel2.add(button3);

		button4 = new JButton("Upload Data");
		button4.addActionListener(this);
		button4.setBounds(153,248,129,33);
		panel2.add(button4);

		panel3 = new JPanel();
		panel3.setBounds(28,327,685,391);
		panel3.setLayout(null);
		wP.add(panel3);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 685, 391);
		panel3.add(tabbedPane);

		table1 = new JTable();
		table1.setFillsViewportHeight(true);
		table1.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {"Username", "Password", "Account Type", "E-mail", "First Name", "Surname", "Phone Number"}));
		sp = new JScrollPane(table1);
		tabbedPane.add(sp, "Account List");

		table2 = new JTable();
		table2.setFillsViewportHeight(true);
		table2.setModel(new DefaultTableModel(
				new Object[][]{

				}, new String[] {"ClubID", "ClubName", "Club Description"}));

		table2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int index = table2.rowAtPoint(e.getPoint());
				if(index != -1){
					displayClubMembers(index);
				}
			}
		});
		sp = new JScrollPane(table2);
		tabbedPane.add(sp, "Club List");

		table3 = new JTable();
		table3.setFillsViewportHeight(true);
		table3.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {"Email", "First Name", "Last Name", "Phone Number"}));
		sp = new JScrollPane(table3);
		tabbedPane.add(sp, "Club Members");

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

		mOpt2 = new Menu("Sort/Search");
		menuBar.add(mOpt2);

		mI3 = new MenuItem("Sort By First Name");
		mI3.addActionListener(this);
		mOpt2.add(mI3);

		mI4 = new MenuItem("Sort By Last Name");
		mI4.addActionListener(this);
		mOpt2.add(mI4);

		mI5 = new MenuItem("Search By First Name");
		mI5.addActionListener(this);
		mOpt2.add(mI5);

		mI6 = new MenuItem("Search By Last Name");
		mI6.addActionListener(this);
		mOpt2.add(mI6);

		frame.setMenuBar(menuBar);
	}

	public void actionPerformed(ActionEvent e){
		String action = e.getActionCommand();

		if(action.equals("Check Clubs")){
			displayClubListInTable();
		}

		if(action.equals("Save Account List")){
			saveAL = new SavingArrayListToFile();
			saveAL.saveAdminsToFile(adminList);
			//saveAL.saveMembersToFile(memberList);
		}
		if(action.equals("Load Account List")){
			saveAL = new SavingArrayListToFile();
			saveAL.loadAdminsToArray(adminList);
			/*saveAL.loadMembersToArray(memberList);
			displayAccListInTable();*/ //TODO 
		}
		if(action.equals("Display AccountList")){
			displayAccListInTable();
		}
		if(action.equals("Send Query")){
			
		}
		if(action.equals("Update Database")){
			String x = box2.getText();
			dbC.updateData(x);
		}
		if(action.equals("Download Data")){
			
		}
		if(action.equals("Upload Data")){
			String username, passWd, email, accType, fName, lName, pNo, dataUploader;

			for(AdminAccount acc : adminList){

				username = acc.getUserName();
				passWd = acc.getPassWord();
				accType = acc.getAccType();
				email = acc.getEmail();
				fName = acc.getFName();
				lName = acc.getLName();
				pNo = acc.getPNo();

				dataUploader = "INSERT INTO Users VALUES ('"+ username + "', '" + passWd + "', '" +email +"', '" +accType +"', '" + fName +"', '" + lName + "', '" + pNo +"' );";
				dbC.updateData(dataUploader);
			}

			for(Club c : clubList){
				/*for(int i = 0; i < c.getCommitteeSize(); i++){

					username = c.getCommitteeMember(i).getUserName();
					passWd = c.getCommitteeMember(i).getPassWord();
					accType = c.getCommitteeMember(i).getAccType();
					email = c.getCommitteeMember(i).getEmail();
					fName = c.getCommitteeMember(i).getFName();
					lName = c.getCommitteeMember(i).getLName();
					pNo = c.getCommitteeMember(i).getPNo();

					dataUploader = "INSERT INTO Users VALUES ('"+ username + "', '" + passWd + "', '" +email +"', '" +accType +"', '" + fName +"', '" + lName + "', '" + pNo +"' );";
					dbC.updateData(dataUploader);
				}*/

				for(int i = 0; i < c.getMemberSize(); i++){
					username = c.getMember(i).getUserName();
					passWd = c.getMember(i).getPassWord();
					accType = c.getMember(i).getAccType();
					email = c.getMember(i).getEmail();
					fName = c.getMember(i).getFName();
					lName = c.getMember(i).getLName();
					pNo = c.getMember(i).getPNo();

					dataUploader = "INSERT INTO Users VALUES ('"+ username + "', '" + passWd + "', '" +email +"', '" +accType +"', '" + fName +"', '" + lName + "', '" + pNo +"' );";
					dbC.updateData(dataUploader);
				}
			}
		}


		if(action.equals("Add Account")){
			initalizeDialog();
		}
		if(action.equals("Remove Account")){
			boolean accountExist = false;
			String nameChecker = JOptionPane.showInputDialog(null, "Type the First Name of the Person to Be Deleted");
			if(nameChecker.equals("") || nameChecker == null){
				JOptionPane.showMessageDialog(null, "Please Specify who you want to delete");
			}
			else{
				for(AdminAccount acc: adminList)
				{
					if(nameChecker.equals(acc.getFName())){
						accountExist = true;
						int reply = JOptionPane.showConfirmDialog(null, "Remove "+ acc.getFName() +" " + acc.getLName() + "'s Account?","Confirm Action", JOptionPane.YES_NO_OPTION);
						if(reply == JOptionPane.YES_OPTION){
							adminList.remove(acc);
							JOptionPane.showMessageDialog(null, nameChecker+" has been deleted!");
							displayAccListInTable();
						}
					}
				}
				for(Club c : clubList){
					/*for(int i = 0; i< c.getCommitteeSize(); i++){
						if(nameChecker.equals(c.getCommitteeMember(i).getFName())){
							accountExist = true;
							int reply = JOptionPane.showConfirmDialog(null, "Remove "+ c.getCommitteeMember(i).getFName() +" " + c.getCommitteeMember(i).getLName() + "'s Account?","Confirm Action", JOptionPane.YES_NO_OPTION);
							if(reply == JOptionPane.YES_OPTION){
								c.removeCommittee(i);
								JOptionPane.showMessageDialog(null, nameChecker+" has been deleted!");
								displayAccListInTable();
							}
						}
					}*/

					for(int i = 0; i< c.getMemberSize(); i++){
						if(nameChecker.equals(c.getMember(i).getFName())){
							accountExist = true;
							int reply = JOptionPane.showConfirmDialog(null, "Remove "+ c.getMember(i).getFName() +" " + c.getMember(i).getLName() + "'s Account?","Confirm Action", JOptionPane.YES_NO_OPTION);
							if(reply == JOptionPane.YES_OPTION){
								c.removeMember(i);
								JOptionPane.showMessageDialog(null, nameChecker+" has been deleted!");
								displayAccListInTable();
							}
						}
					}
				}
				if(accountExist != true){
					JOptionPane.showMessageDialog(null, "Account Does Not Exist");
				}
			}
		}
		if(action.equals("Add Club")){
			initializeClubDialog();
		}
		if(action.equals("Create Club")){
			String clubIDs = box3.getText();
			int clubID = Integer.parseInt(clubIDs);
			String clubName = box4.getText();
			String clubDescription = text.getText();

			club = new Club(clubID, clubName, clubDescription);
			clubList.add(club);
			JOptionPane.showMessageDialog(null, "Club Created");
			d.dispose();
		}
		if(action.equals("Sort By First Name")){

		}
		if(action.equals("Sort By Last Name")){

		}


		if(action.equals("Logout")){
			int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?","Confirm Action", JOptionPane.YES_NO_OPTION);
			if(reply == JOptionPane.YES_OPTION){
				frame.getContentPane().removeAll();
				LoginScreen lS = new LoginScreen(frame, adminList, clubList, memberList);
			}
		}
		if(action.equals("Connect to Database")){
			dbC = new DatabaseConnector();
			JOptionPane.showMessageDialog(null, "Database Successfully Connected");
		}
		if(action.equals("Close Database")){
			dbC.closeDB();
			JOptionPane.showMessageDialog(null, "Database Successfully Dis-connected");
		}
		if(action.equals("Create")){
			String username, passWd, cPassWd, email, accType, fName, lName, pNo;
			Account acc = null;
			username = box3.getText();
			if(username.equals("") || username == null){
				JOptionPane.showMessageDialog(null, "Please Enter a Username");
			}
			else{
				passWd = passBox1.getText().toString();
				cPassWd = passBox2.getText().toString();
				if(passWd.equals("") || passWd == null){
					JOptionPane.showMessageDialog(null, "Please Enter a Password");
				}
				else{
					if(cPassWd.equals(passWd)){
						email = box4.getText();
						fName = box5.getText();
						lName = box6.getText();
						pNo = box7.getText();

						accType = (String)comboBox.getSelectedItem();

						if(accType.equals("Admin")){
							acc = new AdminAccount(username, passWd, accType, email, fName, lName, pNo);
							adminList.add((AdminAccount) acc);
							JOptionPane.showMessageDialog(null, "Account Created");
							d.dispose();
						}else
							for(Club c : clubList){
								/*for(int i = 0; i< c.getCommitteeSize(); i++){
									if(accType.equals("Committee")){
										acc = new CommitteeMember(username, passWd, accType, email, fName, lName, pNo);
										c.addCommitteeMember((CommitteeMember) acc);
										JOptionPane.showMessageDialog(null, "Account Created");
										d.dispose();
									}
								}*/
								for(int i = 0; i< c.getMemberSize(); i++){
									if(accType.equals("Committee")){
										acc = new MemberAccount(username, passWd, accType, email, fName, lName, pNo);
										c.addMember((MemberAccount) acc);
										JOptionPane.showMessageDialog(null, "Account Created");
										d.dispose();
									}
								}
							}
					}
					else{
						JOptionPane.showMessageDialog(null, "Passwords must Match, please enter Correct Password");
					}
				}
			}
		}
	}

	private void displayClubMembers(int index) {
		// TODO Auto-generated method stub
		list = new ArrayList<Object[]>();
		Club c = clubList.get(index);
		/*for(int i = 0; i < c.getCommitteeSize(); i++){
			list.add(new Object[]{
					c.getCommitteeMember(i).getEmail(),
					c.getCommitteeMember(i).getFName(),
					c.getCommitteeMember(i).getLName(),
					c.getCommitteeMember(i).getPNo()
			});
		}*/
		for(int i = 0; i < c.getMemberSize(); i++){
			list.add(new Object[]{
					c.getMember(i).getEmail(),
					c.getMember(i).getFName(),
					c.getMember(i).getLName(),
					c.getMember(i).getPNo()
			});
		}

		table3.setModel(new DefaultTableModel(list.toArray(new Object[][] {}), 
				new String[] {"E-Mail", "First Name", "Surname", "Phone Number"}));
	}

	private void displayClubListInTable() {
		// TODO Auto-generated method stub
		list = new ArrayList<Object[]>();
		for(int i = 0; i < clubList.size(); i++){
			list.add(new Object[] {
					clubList.get(i).getClubID(),
					clubList.get(i).getClubName(),
					clubList.get(i).getClubDescription()
			});
		}
		table2.setModel(new DefaultTableModel(list.toArray(new Object[][] {}), 
				new String[] {"ClubID", "Club Name", "Club Description"}));
	}

	private void initializeClubDialog() {
		// TODO Auto-generated method stub
		d = new JDialog();
		d.setSize(400, 300);
		d.getContentPane().setLayout(null);
		d.setVisible(true);

		label6 = new JLabel("Create Club");
		label6.setFont(new Font("SimSun", Font.PLAIN, 16));
		label6.setBounds(111,11,144,38);
		d.getContentPane().add(label6);

		label7 = new JLabel("Club ID:");
		label7.setBounds(50,62,91,29);
		d.getContentPane().add(label7);

		box3 = new JTextField();
		box3.setBounds(165, 60, 100,30);
		d.getContentPane().add(box3);

		label8 = new JLabel("Club Name:");
		label8.setBounds(50, 102, 91, 29);
		d.getContentPane().add(label8);

		box4 = new JTextField();
		box4.setBounds(165,101, 101, 30);
		d.getContentPane().add(box4);

		label9 = new JLabel("Club Description");
		label9.setBounds(50,142,91,29);
		d.getContentPane().add(label9);

		text = new JTextArea();
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setBounds(165,144, 179, 72);
		d.getContentPane().add(text);

		button3 = new JButton("Create Club");
		button3.addActionListener(this);
		button3.setBounds(132, 246, 123, 30);
		d.getContentPane().add(button3);
	}




	private void displayAccListInTable() {
		// TODO Auto-generated method stub
		list = new ArrayList<Object[]>();
		for (int i = 0; i < adminList.size(); i++) {
			list.add(new Object[] { 
					adminList.get(i).getUserName(),
					adminList.get(i).getPassWord(),
					adminList.get(i).getAccType(),
					adminList.get(i).getEmail(),
					adminList.get(i).getFName(), 
					adminList.get(i).getLName(),
					adminList.get(i).getPNo()
			});
		}

		table1.setModel(new DefaultTableModel(list.toArray(new Object[][] {}), 
				new String[] {"Username", "Password", "Account Type", "E-Mail", "First Name", "Surname", "Phone Number"}));
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
