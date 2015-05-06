package LoginPackageGUI;

import LoginPackageSRC.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;

public class LoginScreen implements ActionListener{

	/**
	 * @param args
	 */

	private static JFrame frame;
	private JPanel panel1;
	private JLabel label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11, label12, label13;
	private JTextField box1, box2, box3, box4, box5, box6;
	private JPasswordField passBox1, passBox2, passBox3;
	private JButton button1, button2, button3, button4;
	private MenuBar menuBar;
	private MenuItem mI1, mI2;
	private Menu mOpt1;
	public static DatabaseConnector DBCon;
	private ArrayList<AdminAccount> adminList;
	private ArrayList<MemberAccount> memberList;
	private ArrayList<Club> clubList;
	private DatabaseConnector dbC;
	//private ResultSet rs;
	private WelcomePanel wP;
	private CITPanelLogo logo;
	private JDialog d;
	private JComboBox comboBox;
	private int width = 1365, height = 764;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoginScreen gui = new LoginScreen();
		LoginScreen.frame.setVisible(true);
	}

	public LoginScreen(){
		initialize();
		adminList = new ArrayList<AdminAccount>();
		memberList = new ArrayList<MemberAccount>();
		clubList = new ArrayList<Club>();
		getDataBaseData();
		addTestData();
	}
	
	private void getDataBaseData() {
		DBCon = DatabaseConnector.getInstance();
		try {
			memberList = DBCon.getMemberAccounts();
	//		clubList = DBCon.getClubs();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private void addTestData()
	{
		AdminAccount acc = new AdminAccount("melvster", "melvster77", "Admin", "melvin.nwokoye@mycit.ie", "Melvin", "Nwokoye", "0858170471");
		Club club = new Club(12345, "Badminton", "Badminton consist of a racquet and a shuttle, for Leisure and for Competitive", "Recreational/Competitive", false);
		Club club2 = new Club(23456, "Volleyball", "Volleyball consists of balls and players, for Leisure and for Competitive", "Recreational/Competitive", false);
		Club club3 = new Club(34567, "Origami", "Making sculptures from paper", "Recreational", false);
		adminList.add(acc);		
		clubList.add(club);
		clubList.add(club2);
		clubList.add(club3);
		MemberAccount mA = new MemberAccount("R00096729", "lemniscata", "Member", "dukey@tt.com", "Duke", "Nukem", "0871234567",false);
		memberList.add(mA);		
		mA.addClub(club);
		MemberAccount mA2 = new MemberAccount("R12345678", "12345678", "Member", "alexander.nill@mycit.ie", "Alex", "Nill", "0871234567",false);
		memberList.add(mA2);		
		mA2.addClub(club);
		MemberAccount mA3 = new MemberAccount("R1", "1", "Member", "a@mycit.ie", "A", "F", "01234567",false);
		memberList.add(mA3);		
		mA3.addClub(club);
		MemberAccount mA4 = new MemberAccount("R2", "2", "Member", "b@mycit.ie", "B", "F", "02345678",false);
		memberList.add(mA4);		
		mA4.addClub(club);
		MemberAccount mA5 = new MemberAccount("R3", "3", "Member", "c@mycit.ie", "C", "F", "03456789",false);
		memberList.add(mA5);		
		mA5.addClub(club);
		mA5.addClub(club2);
		club.addCommittee(mA2);
		club.addCommittee(mA5);
		club2.addCommittee(mA5);
		
		new ClubEvent("hello", "over there", "today", "good morning",club, false);
		new ClubEvent("hell", "over here", "tomorrow", "good evening",club, false);
		new ClubEvent("olleh", "e", "t", "g",club2, false);
		
	}
	public LoginScreen(JFrame frame, ArrayList<AdminAccount> adminList, ArrayList<Club> clubList, ArrayList<MemberAccount> memberList){

//		this.frame = frame;
		clearFrame();
		this.adminList = adminList;
		this.clubList = clubList;
		this.memberList = memberList;
		initialize_1();
	}

	static JFrame getFrame()
	{
		return frame;
	}
	
	static void clearFrame()
	{
		frame.getContentPane().removeAll();
		frame.getContentPane().repaint();
	}
	
	private void initialize(){

		frame = new JFrame("Login Please");
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setUndecorated(true); 
		//	frame.setSize(1000, 997);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);


		wP = new WelcomePanel();
		wP.setLayout(null);
		wP.setBounds(0,0,width,height);
		frame.getContentPane().add(wP);

		panel1 = new JPanel(){
			@Override
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawImage(getImage(), 0,0, 455, 450, null);
			}

			public Image getImage(){
				ImageIcon i = new ImageIcon(getClass().getResource("/Images/white.jpg"));
				return i.getImage();
			}
		};
		panel1.setBounds(435,134,450,430);
		panel1.setLayout(null);
		wP.add(panel1);

		label1 = new JLabel("Already have an account?");
		label1.setFont(new Font("SimSun", Font.BOLD, 22));
		label1.setBounds(24,26,302,59);
		panel1.add(label1);

		label2 = new JLabel("Sign in:");
		label2.setFont(new Font("SimSun", Font.BOLD, 22));
		label2.setBounds(24,104,113,59);
		panel1.add(label2);

		label3 = new JLabel("Username:");
		label3.setBounds(87,174,92,41);
		label3.setFont(new Font("SimSun", Font.BOLD, 16));
		panel1.add(label3);

		box1 = new JTextField();
		box1.setBounds(189,181,137,30);
		panel1.add(box1);

		label4 = new JLabel("Password:");
		label4.setBounds(87,226,92,41);
		label4.setFont(new Font("SimSun", Font.BOLD, 16));
		panel1.add(label4);

		passBox1 = new JPasswordField();
		passBox1.setBounds(189,233,137,30);
		panel1.add(passBox1);

		button1 = new JButton("Login");
		button1.setFont(new Font("SimSun", Font.BOLD, 16));
		button1.addActionListener(this);
		button1.setBounds(297,289,107,30);
		panel1.add(button1);

		label5 = new JLabel("Dont have an Account?: ");
		label5.setFont(new Font("SimSun", Font.BOLD, 22));
		label5.setBounds(24,372,276,47);
		panel1.add(label5);

		button2 = new JButton("Register");
		button2.setFont(new Font("SimSun", Font.BOLD, 16));
		button2.addActionListener(this);
		button2.setBounds(297,382,107,30);
		panel1.add(button2);

		logo = new CITPanelLogo();
		logo.setBounds(1124,603,185,130);
		wP.add(logo);


		menuBar = new MenuBar();
		mOpt1 = new Menu("File"); //Create new Menu
		menuBar.add(mOpt1);

		mI1 = new MenuItem("Exit");
		mI1.addActionListener(this);
		mOpt1.add(mI1);

		frame.setMenuBar(menuBar);
	}

	private void initialize_1(){

		frame.setTitle("Login Please");

		wP = new WelcomePanel();
		wP.setLayout(null);
		wP.setBounds(0,0,width,height);
		frame.getContentPane().add(wP);

		panel1 = new JPanel(){
			@Override
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawImage(getImage(), 0,0, 455, 450, null);
			}

			public Image getImage(){
				ImageIcon i = new ImageIcon(getClass().getResource("/Images/white.jpg"));
				return i.getImage();
			}
		};
		panel1.setBounds(435,134,450,430);
		panel1.setLayout(null);
		wP.add(panel1);

		label1 = new JLabel("Already have an account?");
		label1.setFont(new Font("SimSun", Font.BOLD, 22));
		label1.setBounds(24,26,302,59);
		panel1.add(label1);

		label2 = new JLabel("Sign in:");
		label2.setFont(new Font("SimSun", Font.BOLD, 22));
		label2.setBounds(24,104,113,59);
		panel1.add(label2);

		label3 = new JLabel("Username:");
		label3.setBounds(87,174,92,41);
		label3.setFont(new Font("SimSun", Font.BOLD, 16));
		panel1.add(label3);

		box1 = new JTextField();
		box1.setBounds(189,181,137,30);
		panel1.add(box1);

		label4 = new JLabel("Password:");
		label4.setBounds(87,226,92,41);
		label4.setFont(new Font("SimSun", Font.BOLD, 16));
		panel1.add(label4);

		passBox1 = new JPasswordField();
		passBox1.setBounds(189,233,137,30);
		panel1.add(passBox1);

		button1 = new JButton("Login");
		button1.setFont(new Font("SimSun", Font.BOLD, 16));
		button1.addActionListener(this);
		button1.setBounds(297,289,107,30);
		panel1.add(button1);

		label5 = new JLabel("Dont have an Account?: ");
		label5.setFont(new Font("SimSun", Font.BOLD, 22));
		label5.setBounds(24,372,276,47);
		panel1.add(label5);

		button2 = new JButton("Register");
		button2.setFont(new Font("SimSun", Font.BOLD, 16));
		button2.addActionListener(this);
		button2.setBounds(297,382,107,30);
		panel1.add(button2);

		logo = new CITPanelLogo();
		logo.setBounds(1124,603,185,130);
		wP.add(logo);


		menuBar = new MenuBar();
		mOpt1 = new Menu("File"); //Create new Menu
		menuBar.add(mOpt1);

		mI1 = new MenuItem("Exit");
		mI1.addActionListener(this);
		mOpt1.add(mI1);

		frame.setMenuBar(menuBar);

	}

	@Override
	public void actionPerformed(ActionEvent e){
		String action = e.getActionCommand();

		if(action.equals("Login")){ //For Login Checker
			boolean uN = false, pW = false;
			String username = box1.getText().toString();
			String password = passBox1.getText().toString();

			for(AdminAccount a : adminList){
				if(username.equalsIgnoreCase(a.getUserName())){
					uN = true;
					if(password.equals(a.getPassWord())){
						pW = true;
						if(uN == true && pW == true){
							if((a.getAccType()).equals("Admin")){
								clearFrame();
								AdminScreen aS = new AdminScreen(frame, adminList, clubList, memberList, a);
							}
						}
					}
				}
			}
			for(MemberAccount m : memberList){
				if(username.equalsIgnoreCase(m.getUserName())){
					uN = true;
					if(password.equals(m.getPassWord())){
						pW = true;
						if(uN == true && pW == true){
							if((m.getAccType()).equals("Member")){
								clearFrame();
								MemberScreen mS = new MemberScreen(frame, adminList, clubList, memberList, m);
							}
						}
					}
				}
			}
			if(uN != true || pW != true){
				JOptionPane.showMessageDialog(null, "Username/Password is In-Correct");
			}
		}
		if(action.equals("Create")){
			String username, passWd, cPassWd, email, accType, fName, lName, pNo, clubName;
			Account acc = null;
			username = box2.getText();
			if(username.equals("") || username == null){
				JOptionPane.showMessageDialog(null, "Please Enter a Username");
			}
			else{
				passWd = passBox2.getText().toString();
				cPassWd = passBox3.getText().toString();
				if(passWd.equals("") || passWd == null){
					JOptionPane.showMessageDialog(null, "Please Enter a Password");
				}
				else{
					if(passWd.equals(cPassWd)){
						accType = "Member";
						email = box3.getText();
						fName = box4.getText();
						lName = box5.getText();
						pNo = box6.getText();

						clubName = (String)comboBox.getSelectedItem();
						System.out.println(clubName);
						for(Club c : clubList){
							if(clubName.equals(c.getClubName())){
								acc = new MemberAccount(username, passWd, accType, email, fName, lName, pNo,true);
								((MemberAccount) acc).addClub(c);
								c.addMember((MemberAccount) acc);
								memberList.add((MemberAccount) acc);
								JOptionPane.showMessageDialog(null, "Account Created");
								d.dispose(); 
							}
						}

					}
				}
			}
		}

		if(action.equals("Register")){
			initializeDialog();
		}

		if(action.equals("Cancel")){
			int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel?","Confirm Action", JOptionPane.YES_NO_OPTION);
			if(reply == JOptionPane.YES_OPTION){
				d.dispose();
			}
		}

		if(action.equals("Exit")){
			int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?","Confirm Action", JOptionPane.YES_NO_OPTION);
			if(reply == JOptionPane.YES_OPTION){
				System.exit(0);
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initializeDialog() {
		// TODO Auto-generated method stub
		d = new JDialog();
		d.setAlwaysOnTop(true);
		d.setSize(295, 474);
		d.setResizable(false);
		d.setFocusable(true);
		d.getContentPane().setLayout(null);
		d.setVisible(true);

		label6 = new JLabel("Create Account");
		label6.setFont(new Font("SimSun", Font.PLAIN, 16));
		label6.setBounds(10,11,144,38);
		d.getContentPane().add(label6);

		label6 = new JLabel("Username:");
		label6.setBounds(32,60,106,29);
		d.getContentPane().add(label6);

		box2 = new JTextField();
		box2.setBounds(148,60,108,29);
		d.getContentPane().add(box2);

		label7 = new JLabel("Password:");
		label7.setBounds(32,100,106,29);
		d.getContentPane().add(label7);

		passBox2 = new JPasswordField();
		passBox2.setBounds(148,100,108,29);
		d.getContentPane().add(passBox2);

		label8 = new JLabel("Confirm PassWd:");
		label8.setBounds(32,140,106,29);
		d.getContentPane().add(label8);

		passBox3 = new JPasswordField();
		passBox3.setBounds(148,140,108,29);
		d.getContentPane().add(passBox3);

		label6 = new JLabel("Email:");
		label6.setBounds(32,180,106,29);
		d.getContentPane().add(label6);

		box3 = new JTextField();
		box3.setBounds(148,180,108,29);
		d.getContentPane().add(box3);

		label7 = new JLabel("First Name:");
		label7.setBounds(32,220,106,29);
		d.getContentPane().add(label7);

		box4 = new JTextField();
		box4.setBounds(148,220,108,29);
		d.getContentPane().add(box4);

		label8 = new JLabel("Last Name:");
		label8.setBounds(32,260,106,29);
		d.getContentPane().add(label8);

		box5 = new JTextField();
		box5.setBounds(148,260,108,29);
		d.getContentPane().add(box5);

		label9 = new JLabel("Phone Number:");
		label9.setBounds(32,300,106,29);
		d.getContentPane().add(label9);

		box6 = new JTextField();
		box6.setBounds(148,300,108,29);
		d.getContentPane().add(box6);

		button3 = new JButton("Cancel");
		button3.addActionListener(this);
		button3.setBounds(32,392,108,29);
		d.getContentPane().add(button3);

		button4 = new JButton("Create");
		button4.addActionListener(this);
		button4.setBounds(148,392,108,29);
		d.getContentPane().add(button4);

		String [] clubListNames = new String [clubList.size()];
		for(int i = 0; i< clubListNames.length; i++){

			clubListNames[i] = clubList.get(i).getClubName();

		}

		label10 = new JLabel("Choose Club");
		label10.setBounds(32,340,106,29);
		d.getContentPane().add(label10);

		comboBox = new JComboBox(clubListNames);
		comboBox.setBounds(148,340,108,29);
		comboBox.setSelectedIndex(0);
		comboBox.addActionListener(this);
		d.getContentPane().add(comboBox);
	}
}