package LoginPackageGUI;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import LoginPackageSRC.Account;
import LoginPackageSRC.Club;
import LoginPackageSRC.ClubEvent;
import LoginPackageSRC.DatabaseConnector;
import LoginPackageSRC.MemberAccount;

public class LoginScreen implements ActionListener{

	/**
	 * @param args
	 */

	private static JFrame frame;
	private JPanel panel1;
	private JLabel label1, label2, label3, label4, label5;
	private JTextField box1;
	private JPasswordField passBox1;
	private JButton button1, button2;
	private MenuBar menuBar;
	private MenuItem mI1;
	private Menu mOpt1;
	public static DatabaseConnector DBCon;
	private ArrayList<Account> adminList;
	public static ArrayList<MemberAccount> memberList;
	public static ArrayList<Club> clubList;
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
		adminList = new ArrayList<Account>();
		memberList = new ArrayList<MemberAccount>();
		clubList = new ArrayList<Club>();
		getDataBaseData();
		addTestData(false);
	}
	
	private void getDataBaseData() {
		DBCon = DatabaseConnector.getInstance();
		if (DBCon == null)
		{
			return;
		}
		try {
			memberList = DBCon.getMemberAccounts();
			clubList = DBCon.getClubs();
			DBCon.getMemberships(memberList,clubList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private void addTestData(boolean init)
	{
		Account acc = new Account("melvster", "melvster77", "Melvin", "Nwokoye", "0858170471", "Admin", "melvin.nwokoye@mycit.ie");
		adminList.add(acc);		
		if(!init)
		{
			return;
		}
		Club club = new Club(12345, "Badminton", "Badminton consist of a racquet and a shuttle, for Leisure and for Competitive", "Recreational/Competitive", init);
		Club club2 = new Club(23456, "Volleyball", "Volleyball consists of balls and players, for Leisure and for Competitive", "Recreational/Competitive", init);
		Club club3 = new Club(34567, "Origami", "Making sculptures from paper", "Recreational", init);
		clubList.add(club);
		clubList.add(club2);
		clubList.add(club3);
		MemberAccount mA = new MemberAccount("R00096729", "lemniscata", "Member", "dukey@tt.com", "Duke", "Nukem", "0871234567",init);
		memberList.add(mA);		
		mA.addClub(club,init);
		MemberAccount mA2 = new MemberAccount("R12345678", "12345678", "Member", "alexander.nill@mycit.ie", "Alex", "Nill", "0871234567",init);
		memberList.add(mA2);		
		mA2.addClub(club,init);
		MemberAccount mA3 = new MemberAccount("R1", "1", "Member", "a@mycit.ie", "A", "F", "01234567",init);
		memberList.add(mA3);		
		mA3.addClub(club,init);
		MemberAccount mA4 = new MemberAccount("R2", "2", "Member", "b@mycit.ie", "B", "F", "02345678",init);
		memberList.add(mA4);		
		mA4.addClub(club,init);
		MemberAccount mA5 = new MemberAccount("R3", "3", "Member", "c@mycit.ie", "C", "F", "03456789",init);
		memberList.add(mA5);		
		mA5.addClub(club,init);
		mA5.addClub(club2,init);
		club.addCommittee(mA2,init);
		club.addCommittee(mA5,init);
		club2.addCommittee(mA5,init);
		
		new ClubEvent("hello", "over there", "2005-11-11 00:11:12", "good morning",club, init);
		new ClubEvent("hell", "over here", "2015-12-13 01:11:12", "good evening",club, init);
		new ClubEvent("olleh", "e", "t", "2009-01-15 02:21:12",club2, init);
		
	}
	public LoginScreen(JFrame frame, ArrayList<Account> adminList, ArrayList<Club> clubList, ArrayList<MemberAccount> memberList){

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

			for(Account a : adminList){
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
		d = new RegisterDialog(adminList, clubList, memberList);

	}
}