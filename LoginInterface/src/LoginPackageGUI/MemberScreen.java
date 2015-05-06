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
	private JPanel panel1, panel2, eventPanel, membersPanel,equipmentPanel, mini_panel1, mini_panel2, mini_panel3, mini_panel4;
	private JTabbedPane mainTabbedPane, comm_tabbedPane;
	private JLabel label1, label2, label3, label4, mini_label1, mini_label2, mini_label3, mini_label4, mini_label5, comm_D_label1;
	private JTextField editType, editLocation, editDate, mini_box4, mini_box5, box5, box6, box7;
	private JTextArea editEventInfo;
	private JTextArea box2;
	private JPasswordField passBox1, passBox2, passBox3;
	private JButton m_button1, m_button2, m_button3, m_button4, mini_button1, mini_button2, mini_button3, mini_button4, mini_button5, comm_D_button3, comm_D_button4, updateInfoBtn;
	private MenuBar menuBar;
	private MenuItem mI1, mI2, mI3, mI4, mI5, mI6, mI7, mI8;
	private Menu mOpt1, mOpt2;
	private ArrayList<AdminAccount> adminList;
	private ArrayList<MemberAccount> memberList;
	private ArrayList<Club> clubList;
	private ArrayList<Club> registeredList, unregisteredList;
	private Club selectedClub, committeeClub;
	private MemberAccount mA;
	private JTable m_tableRegisteredClubs, m_tableEvents, m_tableAvailableClubs, comm_tableEvent, comm_table2, comm_tableMembers;
	private JScrollPane sp, sp1;
	private WelcomePanel wP;
	private JSplitPane splitPane, doubleSplitPane, doubleSplitPane1, splitPane1;
	private JDialog d;
	private JComboBox comboBox;
	private JSplitPane doubleSplitPane2;
	private JSplitPane splitPane2;
	private JScrollPane sp2;
	private JTable comm_table4;
	private JPanel mini_panel5;
	private JLabel mini_label6;
	private JTextField mini_box6;
	private JLabel mini_label7;
	private JTextField mini_box7;
	private JPanel mini_panel6;
	private JButton mini_button6;
	private JButton mini_button7;
	private JTable m_tableCommittee;
	
	
	public MemberScreen(JFrame frame, ArrayList<AdminAccount> adminList, ArrayList<Club> clubList, ArrayList<MemberAccount> memberList, Account a){
		this.frame = frame;
		this.adminList = adminList;
		this.clubList = clubList;
		this.memberList = memberList;
		this.mA = (MemberAccount) a;
		initialize();
	}
	
	@SuppressWarnings("serial")
	private void initialize(){
		frame.setTitle("Member Screen");
		
		wP = new WelcomePanel();
		wP.setBounds(0,0,1365,764);
		frame.getContentPane().add(wP);
		wP.setLayout(null);
		
				
		mainTabbedPane = new JTabbedPane(SwingConstants.TOP);
		mainTabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
			// note at this point the tab will be on another pane
			//identify current pane
				int selectedPane = mainTabbedPane.getSelectedIndex(), lastPane = 0;
				String tab = mainTabbedPane.getTitleAt(selectedPane);
				
				
				if(tab.equals("COMMITTEE")){
					String clubName = "";
					if(selectedClub != null)
					{
						clubName = selectedClub.getClubName();
					}
					clubName = JOptionPane.showInputDialog(null, "Please enter the name of the club you are a committee member of:",clubName);
					if(clubName == null)
					{
						JOptionPane.showMessageDialog(null, "Enter name of a club, please");
						mainTabbedPane.setSelectedIndex(lastPane);					
					}
					else
					{
						boolean found = false;
						for(int i = 0; i < mA.getNoOfClubs(); i++){
							if(clubName.equals(mA.getClub(i).getClubName())){
								Club c = mA.getClub(i);
								if(c.getIsCommittee(mA) == true){
									lastPane = selectedPane;
									committeeClub = c;
									fillCommitteeTab();
									found = true;
								}
								else{
									JOptionPane.showMessageDialog(null, "You are not a committee member for this club");
									mainTabbedPane.setSelectedIndex(lastPane);
								}
								
							}
						}
						if (!found)
						{
							JOptionPane.showMessageDialog(null, "You might not be registered for "+ clubName + "\nor the club does not exist");
							mainTabbedPane.setSelectedIndex(lastPane);
						}
					}
				}
			}


		});
		mainTabbedPane.setBounds(24, 11, 1313, 720);
		wP.add(mainTabbedPane);
			
		panel1 = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(getImage(), 0, 0, mainTabbedPane.getWidth(),
						mainTabbedPane.getHeight(), null);
			}

			public Image getImage() {
				ImageIcon i = new ImageIcon(getClass().getResource(
						"/Images/white.jpg"));
				return i.getImage();
			}
		};
		panel1.setLayout(null);
		mainTabbedPane.add(panel1, "MEMBER");

		label1 = new JLabel(mA.getFName() + " " + mA.getLName() + "'s "
				+ mA.getAccType() + " Page");
		label1.setFont(new Font("SimSun", Font.BOLD, 22));
		label1.setBounds(465, 0, 447, 47);
		panel1.add(label1);

		label2 = new JLabel("Clubs & Societies Joined");
		label2.setFont(new Font("SimSun", Font.PLAIN, 14));
		label2.setBounds(203, 40, 215, 32);
		panel1.add(label2);

		m_tableRegisteredClubs = new JTable();
		m_tableRegisteredClubs.setFillsViewportHeight(true);
		m_tableRegisteredClubs.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "CLUB_ID", "CLUB_NAME", "REC/COMP",
						"CLUB_DESCRIPTION" }));
		m_tableRegisteredClubs.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int index = m_tableRegisteredClubs.rowAtPoint(e.getPoint());
				m_tableAvailableClubs.clearSelection();
				if (index != -1) {
					displayClubEvents(registeredList.get(index));
				}
			}
		});
		sp = new JScrollPane(m_tableRegisteredClubs);
		sp.setBounds(28, 72, 633, 255);
		panel1.add(sp);

		m_button1 = new JButton("De-Register From Club");
		m_button1.addActionListener(this);
		m_button1.setBounds(28, 338, 162, 32);
		panel1.add(m_button1);

		m_button4 = new JButton("Contact Club");
		m_button4.addActionListener(this);
		m_button4.setBounds(202, 338, 162, 32);
		panel1.add(m_button4);

		label3 = new JLabel("Events for Club");
		label3.setFont(new Font("SimSun", Font.PLAIN, 14));
		label3.setBounds(246, 367, 215, 32);
		panel1.add(label3);

		m_tableEvents = new JTable();
		m_tableEvents.setFillsViewportHeight(true);
		m_tableEvents.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "EVENT_TYPE", "EVENT_LOCATION", "TIME & DATE",
						"EVENT_INFO" }));
		sp = new JScrollPane(m_tableEvents);
		sp.setBounds(30, 404, 631, 277);
		panel1.add(sp);

		label4 = new JLabel("Available Clubs & Societies");
		label4.setFont(new Font("SimSun", Font.PLAIN, 14));
		label4.setBounds(922, 40, 215, 32);
		panel1.add(label4);

		m_tableAvailableClubs = new JTable();
		m_tableAvailableClubs.setFillsViewportHeight(true);
		m_tableAvailableClubs.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "CLUB_ID", "CLUB_NAME", "REC/COMP",
						"CLUB_DESCRIPTION" }));
		m_tableAvailableClubs.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int index = m_tableAvailableClubs.rowAtPoint(e.getPoint());
				m_tableRegisteredClubs.clearSelection();
				if (index != -1) {
					displayClubEvents(unregisteredList.get(index));
				}
			}
		});
		sp = new JScrollPane(m_tableAvailableClubs);
		sp.setBounds(710, 72, 588, 255);
		panel1.add(sp);
		displayClubs();
		
		m_tableCommittee = new JTable();
		m_tableCommittee.setFillsViewportHeight(true);
		m_tableCommittee.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Name", "E-Mail" }));
		sp = new JScrollPane(m_tableCommittee);
		sp.setBounds(710, 404, 588, 255);
		panel1.add(sp);

		m_button2 = new JButton("Register For Club");
		m_button2.addActionListener(this);
		m_button2.setBounds(710, 338, 146, 32);
		panel1.add(m_button2);
		
		updateInfoBtn = new JButton("Update Info");
		updateInfoBtn.addActionListener(this);
		updateInfoBtn.setBounds(874, 338, 146, 32);
		panel1.add(updateInfoBtn);
		

		panel2 = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(getImage(), 0, 0, mainTabbedPane.getWidth(),
						mainTabbedPane.getHeight(), null);
			}

			public Image getImage() {
				ImageIcon i = new ImageIcon(getClass().getResource(
						"/Images/white.jpg"));
				return i.getImage();
			}
		};
		panel2.setLayout(null);
		mainTabbedPane.add(panel2, "COMMITTEE");

		comm_tabbedPane = new JTabbedPane(SwingConstants.TOP);
		comm_tabbedPane.setBounds(0, 0, 1308, 692);
		panel2.add(comm_tabbedPane);

		eventPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(getImage(), 0, 0, mainTabbedPane.getWidth(),
						mainTabbedPane.getHeight(), null);
			}

			public Image getImage() {
				ImageIcon i = new ImageIcon(getClass().getResource(
						"/Images/white.jpg"));
				return i.getImage();
			}
		};
		eventPanel.setLayout(null);
		comm_tabbedPane.add(eventPanel, "EVENTS");

		doubleSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		doubleSplitPane.setOneTouchExpandable(true);
		doubleSplitPane.setBounds(28, 11, 1125, 570);
		eventPanel.add(doubleSplitPane);

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setOneTouchExpandable(true);
		splitPane.setBounds(10, 11, 629, 522);
		doubleSplitPane.setTopComponent(splitPane);

		sp = new JScrollPane();
		splitPane.setLeftComponent(sp);

		comm_tableEvent = new JTable();
		sp.setViewportView(comm_tableEvent);
		comm_tableEvent.setFillsViewportHeight(true);
		comm_tableEvent.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "EVENT_TYPE", "EVENT_LOCATION", "TIME & DATE",
						"EVENT_INFO" }));

		mini_panel1 = new JPanel();
		mini_panel1.setLayout(null);

		mini_label1 = new JLabel("Type:");
		mini_label1.setFont(new Font("SimSun", Font.PLAIN, 14));
		mini_label1.setBounds(10, 5, 69, 33);
		mini_panel1.add(mini_label1);

		editType = new JTextField();
		editType.setBounds(89, 5, 200, 33);
		mini_panel1.add(editType);

		mini_label2 = new JLabel("Location:");
		mini_label2.setFont(new Font("SimSun", Font.PLAIN, 14));
		mini_label2.setBounds(10, 49, 69, 33);
		mini_panel1.add(mini_label2);

		editLocation = new JTextField();
		editLocation.setBounds(89, 49, 200, 33);
		mini_panel1.add(editLocation);

		mini_label3 = new JLabel("Time & Date");
		mini_label3.setFont(new Font("SimSun", Font.PLAIN, 14));
		mini_label3.setBounds(10, 95, 92, 33);
		mini_panel1.add(mini_label3);

		editDate = new JTextField();
		editDate.setBounds(89, 93, 200, 33);
		mini_panel1.add(editDate);

		mini_label4 = new JLabel("Event Info");
		mini_label4.setFont(new Font("SimSun", Font.PLAIN, 14));
		mini_label4.setBounds(10, 143, 92, 33);
		mini_panel1.add(mini_label4);

		editEventInfo = new JTextArea();
		editEventInfo.setLineWrap(true);
		editEventInfo.setWrapStyleWord(true);
		sp = new JScrollPane(editEventInfo);
		sp.setBounds(89, 137, 200, 100);
		mini_panel1.add(sp);

		splitPane.setRightComponent(mini_panel1);
		splitPane.setResizeWeight(0.5);

		mini_panel2 = new JPanel();
		mini_panel2.setLayout(null);

		mini_button1 = new JButton("Add Event");
		mini_button1.addActionListener(this);
		mini_button1.setBounds(10, 11, 147, 33);
		mini_panel2.add(mini_button1);

		mini_button2 = new JButton("Edit Event");
		mini_button2.addActionListener(this);
		mini_button2.setBounds(406, 11, 147, 33);
		mini_panel2.add(mini_button2);

		mini_button3 = new JButton("Delete Event");
		mini_button3.addActionListener(this);
		mini_button3.setBounds(741, 11, 147, 33);
		mini_panel2.add(mini_button3);

		doubleSplitPane.setResizeWeight(0.5);
		doubleSplitPane.setBottomComponent(mini_panel2);

		membersPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(getImage(), 0, 0, mainTabbedPane.getWidth(),
						mainTabbedPane.getHeight(), null);
			}

			public Image getImage() {
				ImageIcon i = new ImageIcon(getClass().getResource(
						"/Images/white.jpg"));
				return i.getImage();
			}
		};
		membersPanel.setLayout(null);
		comm_tabbedPane.add(membersPanel, "MEMBERS");

		doubleSplitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		doubleSplitPane1.setOneTouchExpandable(true);
		doubleSplitPane1.setBounds(28, 11, 1125, 570);
		membersPanel.add(doubleSplitPane1);

		splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane1.setOneTouchExpandable(true);
		splitPane1.setBounds(10, 11, 629, 522);
		doubleSplitPane1.setTopComponent(splitPane1);

		sp1 = new JScrollPane();
		splitPane1.setLeftComponent(sp1);

		comm_tableMembers = new JTable();
		sp1.setViewportView(comm_tableMembers);
		comm_tableMembers.setFillsViewportHeight(true);
		comm_tableMembers.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "MEMBER_EMAIL", "MEMBER_FNAME", "MEMBER_LNAME",
						"MEMBER_PHONE" , "COMMITTEE"}));

		mini_panel3 = new JPanel();
		mini_panel3.setLayout(null);

		mini_label5 = new JLabel("Student No:");
		mini_label5.setFont(new Font("SimSun", Font.PLAIN, 14));
		mini_label5.setBounds(10, 5, 119, 33);
		mini_panel3.add(mini_label5);

		mini_box5 = new JTextField();
		mini_box5.setBounds(110, 5, 192, 33);
		mini_panel3.add(mini_box5);

		splitPane1.setRightComponent(mini_panel3);
		splitPane1.setResizeWeight(0.5);

		mini_panel4 = new JPanel();
		mini_panel4.setLayout(null);

		mini_button4 = new JButton("Add Member");
		mini_button4.addActionListener(this);
		mini_button4.setBounds(10, 11, 147, 33);
		mini_panel4.add(mini_button4);

		mini_button5 = new JButton("Remove Member");
		mini_button5.addActionListener(this);
		mini_button5.setBounds(741, 11, 147, 33);
		mini_panel4.add(mini_button5);

		doubleSplitPane1.setResizeWeight(0.5);
		doubleSplitPane1.setBottomComponent(mini_panel4);

		equipmentPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(getImage(), 0, 0, mainTabbedPane.getWidth(),
						mainTabbedPane.getHeight(), null);
			}

			public Image getImage() {
				ImageIcon i = new ImageIcon(getClass().getResource(
						"/Images/white.jpg"));
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
//				frame.getContentPane().removeAll();
				frame.getContentPane().repaint();
				LoginScreen lS = new LoginScreen(frame, adminList, clubList, memberList);
			}
		}
		
		if(action.equals("De-Register From Club")){
			int index = m_tableRegisteredClubs.getSelectedRow();
			if(index < 0)
			{
				JOptionPane.showMessageDialog(null, "no club selected");
				return;
			}
			Club club = registeredList.get(index);
			mA.removeClub(club);
			displayClubs();
			JOptionPane.showMessageDialog(null, "Successfully unregistered from " + club.getClubName());
		}
		if(action.equals("Contact Club")){
			Club club;
			int index = m_tableAvailableClubs.getSelectedRow();
			if(index < 0)
			{
				index = m_tableRegisteredClubs.getSelectedRow();
				if(index < 0)
				{
					JOptionPane.showMessageDialog(null, "no club selected");
					return;
				}
				club = registeredList.get(index);
			}
			else
			{
				club = unregisteredList.get(index);
			}
			MailDialog mailer = new MailDialog();
			mailer.contactClub(club, mA);
			mailer.setVisible(true);
		}
		if(action.equals("Register For Club")){
			int index = m_tableAvailableClubs.getSelectedRow();
			if(index < 0)
			{
				JOptionPane.showMessageDialog(null, "no club selected");
				return;
			}
			Club club = unregisteredList.get(index);
			mA.addClub(club);
			displayClubs();
			JOptionPane.showMessageDialog(null, "Successfully registered for " + club.getClubName());
		}
		if(action.equals("Cancel Committee Check")){
			d.dispose();
		}
		if(action.equals("Check Committee Status")){
			String clubName = (String)comboBox.getSelectedItem(); 
			JOptionPane.showMessageDialog(null, clubName);
		}
		if(action.equals("Add Event")){
			addEvent();
		}
		
		if(action.equals("Update Info")){
			d = new ProfileDialog(adminList, clubList, memberList);
		}
		
		
	}
	
	private void addEvent() {
		
		String location = editLocation.getText();
		String date = editDate.getText();
		String eventType = editType.getText();
		String info = editEventInfo.getText();
		new ClubEvent(eventType, location, date, info, selectedClub, true);
		displayEventsInCommitteeTab();
		displayClubEvents(selectedClub);
	}

	private void displayClubs()
	{
		ArrayList<Object []> registered, unregistered;
		registered = new ArrayList<Object[]>();
		unregistered = new ArrayList<Object[]>();
		registeredList = new ArrayList<Club>();
		unregisteredList = new ArrayList<Club>();
		for(int i = 0; i < clubList.size(); i++){
			Club club=clubList.get(i);
			if (club.getIsMember(mA))
			{
				registered.add(new Object[] {
						club.getClubID(),
						club.getClubName(),
						club.getClubDescription(),
						club.getClubType()
				});
				registeredList.add(club);
			}
			else
			{
				unregistered.add(new Object[] {
						club.getClubID(),
						club.getClubName(),
						club.getClubDescription(),
						club.getClubType()
				});
				unregisteredList.add(club);
			}
		}
		m_tableRegisteredClubs.setModel(new DefaultTableModel(registered.toArray(new Object[][] {}), 
				new String[] {"CLUB_ID", "CLUB_NAME", "CLUB_DESCRIPTION", "CLUB_TYPE"}));
		m_tableAvailableClubs.setModel(new DefaultTableModel(unregistered.toArray(new Object[][] {}), 
				new String[] {"CLUB_ID", "CLUB_NAME", "CLUB_DESCRIPTION", "CLUB_TYPE"}));		
	}
//	private void displayRegisteredClubs(){
//		list = new ArrayList<Object[]>();
//		for(int i = 0; i < mA.getNoOfClubs(); i++){
//			
//			list.add(new Object[] {
//					mA.getClub(i).getClubID(),
//					mA.getClub(i).getClubName(),
//					mA.getClub(i).getClubDescription(),
//					mA.getClub(i).getClubType()
//			});
//			
//		}
//		m_table1.setModel(new DefaultTableModel(list.toArray(new Object[][] {}), 
//				new String[] {"CLUB_ID", "CLUB_NAME", "CLUB_DESCRIPTION", "CLUB_TYPE"}));
//		
//	}
	
	private void displayClubEvents(Club club){
		selectedClub = club;
		ArrayList<Object[]> list = new ArrayList<Object[]>();

		for(int i = 0; i < club.getNoOfEvents(); i++){
			
			list.add(new Object[] {
					club.getEvent(i).getEventType(),
					club.getEvent(i).getLocation(),
					club.getEvent(i).getDate(),
					club.getEvent(i).getInfo()
			});		
		}
		m_tableEvents.setModel(new DefaultTableModel(list.toArray(new Object[][] {}), 
				new String[] {"EVENT_TYPE", "EVENT_LOCATION", "TIME & DATE", "EVENT_INFO"}));
		displayCommitteeMembers(club);

	}
	private void displayCommitteeMembers(Club club) {
		ArrayList<Object[]> list2 = new ArrayList<Object[]>();
		Iterator<MemberAccount> iter = club.getCommitteeMembers().iterator();
		while(iter.hasNext())
		{
			MemberAccount committee = iter.next();
			list2.add(new Object[] {
					committee.getFName(),
					committee.getLName(),
					committee.getEmail()
			});
		}
		m_tableCommittee.setModel(new DefaultTableModel(list2.toArray(new Object[][] {}), 
				new String[] {"FirstName", "LastName", "E-mail"}));
	}

	private void fillCommitteeTab() 
	{
		displayEventsInCommitteeTab();
		displayMembers();
	}
	private void displayEventsInCommitteeTab() {
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		for(int i = 0; i < committeeClub.getNoOfEvents(); i++){
			
			list.add(new Object[] {
					committeeClub.getEvent(i).getEventType(),
					committeeClub.getEvent(i).getLocation(),
					committeeClub.getEvent(i).getDate(),
					committeeClub.getEvent(i).getInfo()
			});		
		}
		comm_tableEvent.setModel(new DefaultTableModel(list.toArray(new Object[][] {}), 
				new String[] {"EVENT_TYPE", "EVENT_LOCATION", "TIME & DATE", "EVENT_INFO"}));
	}

	private void displayMembers() {
		ArrayList<Object[]> list2 = new ArrayList<Object[]>();
		Iterator<MemberAccount> iter = committeeClub.getAllMembers().iterator();
		while(iter.hasNext())
		{
			MemberAccount member = iter.next();
			list2.add(new Object[] {
					member.getEmail(),
					member.getFName(),
					member.getLName(),
					member.getPNo(),
					committeeClub.getIsCommittee(member)
			});
		}		
		comm_tableMembers.setModel(new DefaultTableModel(list2.toArray(new Object[][] {}), 
				new String[] { "MEMBER_EMAIL", "MEMBER_FNAME", "MEMBER_LNAME",
			"MEMBER_PHONE" , "COMMITTEE"}));
		
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

	