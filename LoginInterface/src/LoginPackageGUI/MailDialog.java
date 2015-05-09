package LoginPackageGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import LoginPackageSRC.*;


@SuppressWarnings("serial")
public class MailDialog extends JDialog implements ActionListener {
	private Mailing mailService;
	private JLabel labelSubject;
	private JTextField textSubject;
	private JLabel labelTo;
	private JTextField textTo;
	private JTextArea textContent;
	private JButton buttonSend;
	private ArrayList<Club> clubs;
	private MemberAccount mA;
	private Account accTo;
	private Account aA;
	private JComboBox comboClubs;
	private JLabel labelClub;
	private JComboBox comboMembers;
	private String clubName;
	public MailDialog() {
		super();
		initDialog();
		mailService = new Mailing();
	}
	void initDialog()
	{
		setAlwaysOnTop(true);
		setSize(495, 774);
		setResizable(false);
		setFocusable(true);
		getContentPane().setLayout(null);
		setVisible(true);
		
		labelTo = new JLabel("Select Club:");
		labelTo.setFont(new Font("SimSun", Font.BOLD, 13));
		labelTo.setBounds(32,24,110,22);
		this.add(labelTo);	
		
		int size = LoginScreen.clubList.size()+2;
		if(aA != null)
		{
			size+=1;
		}
		String [] clubListNames = new String [size];
		clubListNames[0] = "Single Person";
		clubListNames[1] = "admins";
		for(int i = 2; i <= LoginScreen.clubList.size()+1; i++)
		{
			clubListNames[i] = LoginScreen.clubList.get(i-2).getClubName();
		}
		if(aA != null)
		{
			clubListNames[size-1] = "All Clubs";
		}

		comboClubs = new JComboBox(clubListNames);
		comboClubs.setBounds(148,24,208,29);
		comboClubs.setSelectedIndex(0);
		comboClubs.addActionListener(this);
		getContentPane().add(comboClubs);
		
		
		labelClub = new JLabel("Choose Recipients");
		labelClub.setBounds(32,60,106,29);
		getContentPane().add(labelClub);
		
		comboMembers = new JComboBox(new String[]{"Committee","All Members"});
		comboMembers.setBounds(148,60,208,29);
		comboMembers.setSelectedIndex(0);
		comboMembers.addActionListener(this);
		getContentPane().add(comboMembers);
		
//		textTo = new JTextField();
//		textTo.setBounds(124,26,210,22);
//		this.add(textTo);		
				
		labelTo = new JLabel("To:");
		labelTo.setFont(new Font("SimSun", Font.BOLD, 13));
		labelTo.setBounds(32,96,110,22);
		this.add(labelTo);
		
		textTo = new JTextField();
		textTo.setBounds(148,96,210,22);
		this.add(textTo);	
		textTo.setEnabled(false);
		
		labelSubject = new JLabel("Subject:");
		labelSubject.setFont(new Font("SimSun", Font.BOLD, 13));
		labelSubject.setBounds(32,132,110,22);
		this.add(labelSubject);
		
		textSubject = new JTextField();
		textSubject.setBounds(148,132,210,22);
		this.add(textSubject);	
		
		textContent = new JTextArea();
		textContent.setBounds(24,166,310,400);
		this.add(textContent);	
		
		buttonSend = new JButton("send Mail");
		buttonSend.setBounds(24, 574, 200, 22);
		buttonSend.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				mailService.setSubjectLine(textSubject.getText());
				mailService.setMailContent(textContent.getText());
				if (((String)comboClubs.getSelectedItem()).equals("All Clubs")&&((String)comboMembers.getSelectedItem()).equals("All Members"))
				{
					mailService.addMemberAccounts(LoginScreen.memberList);
				}
				else if(comboClubs.getSelectedIndex()==0)
				{
					ArrayList<Account> accs = new ArrayList<Account>();
					accs.add(accTo);
					mailService.addMemberAccounts(accs);
				}
				else
				{
					for(int i = 0; i<clubs.size();i++)
					{
						if(comboMembers.getSelectedIndex()==0)
						{
							mailService.addMemberAccounts(clubs.get(i).getCommitteeMembers());						
						}
						else
						{
							mailService.addMemberAccounts(clubs.get(i).getAllMembers());						
						}
					}
				}
				mailService.sendMail();
			}
		});
		this.add(buttonSend);
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{		
		String action = e.getActionCommand();
		if(action.equals("comboBoxChanged"))
		{
			//String clubName = (String)comboClubs.getSelectedItem();

			int selection = comboClubs.getSelectedIndex();
			if(selection>1)
			{
				if(selection+2<LoginScreen.clubList.size())
				{
					Club club = LoginScreen.clubList.get(comboClubs.getSelectedIndex()-2);
					clubs = new ArrayList<Club>();
					clubs.add(club);
				}
				else
				{
					clubs = LoginScreen.clubList;
				}
				comboMembers.setEnabled(true);
			}
			else
			{
				comboMembers.setEnabled(false);
			}
		}
	}
	public void setFromTo(Club club, MemberAccount mA)
	{
		comboClubs.setSelectedItem(club.getClubName());
		clubs = new ArrayList<Club>();
		clubs.add(club);
		this.mA = mA;
	}
	public void setFromTo(String clubName, MemberAccount mA, Account accountTo)
	{
		comboClubs.setSelectedIndex(0);
		comboClubs.setEnabled(false);
		comboMembers.setEnabled(false);
		this.mA = mA;
		this.accTo = accountTo;
		this.clubName = clubName;
	}
	public void setFromAdmin(Account aA)
	{
		this.aA=aA;
		textSubject.setText("Admin Announcement");
	}
	public void presetComRequest() 
	{
		textSubject.setText("Request for committee");
		textContent.setText("Hello " + clubs.get(0).getClubName() + ",\n\r" +
							"my name is " + mA.getFName() + " "+ mA.getLName() + " and I would like to join the committee" +
				 			" of your club, please consider my application " + mA.getEmail() + "\r\n" +
							"Regards,\r\n" + mA.getFName() + " "+ mA.getLName());
	}
	public void presetComRequest2() 
	{
		textTo.setText(accTo.getEmail());
		textSubject.setText("Request for committee");
		textContent.setText("Hello " + accTo.getFName() + " "+ accTo.getLName() + ",\n\r" +
							"my name is " + mA.getFName() + " "+ mA.getLName() + " and I would like to join the committee" +
				 			" of " + clubName + "\r\n, please consider my application " + mA.getEmail() + "\r\n" +
							"Regards,\r\n" + mA.getFName() + " "+ mA.getLName());
	}
	public void presetInfoRequest()
	{
		textSubject.setText("Request for club information");
		textContent.setText("Hello " + clubs.get(0).getClubName() + ",\n\r" +
							"my name is " + mA.getFName() + " and I would like to have some information" +
				 			" about your club, please email me at " + mA.getEmail() + "\r\n" +
							"Regards,\r\n" + mA.getFName());
	}
	public void presetInfoRequest2()
	{
		textTo.setText(accTo.getFName()+" " + accTo.getLName());
		textSubject.setText("Personal Text");
		textContent.setText("Hello " + accTo.getFName()+" " + accTo.getLName() + ",\n\r" +
							"my name is " + mA.getFName() + " "+ mA.getLName() + " and I would like ask you something about" +
				 			" your club " + clubName + "\r\n" +
							"Regards,\r\n" + mA.getFName() + " "+ mA.getLName());
	}
}
