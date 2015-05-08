package LoginPackageGUI;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import LoginPackageSRC.Account;
import LoginPackageSRC.AdminAccount;
import LoginPackageSRC.Club;
import LoginPackageSRC.MemberAccount;

public class RegisterDialog extends JDialog implements ActionListener {
	protected JLabel labelCreate;
	protected JLabel labelPassword;
	protected JTextField boxUserName, boxEmail, boxFirstName, boxLastName, boxPhone;
	protected JPasswordField passBox2, passBox3;
	protected JButton button3;
	protected JButton button4;
	protected ArrayList<MemberAccount> memberList;
	protected JLabel labelClub;
	protected JComboBox comboBox;
	protected JLabel labelPasswordConfirm, label62, labelEmail;
	private JLabel label9;
	protected ArrayList<Club> clubList;
	protected String passWd;
	protected String cPassWd;
	protected String username, email, accType, fName, lName, pNo;
	protected JLabel labelLastName;
	protected JLabel labelFirstName;

	public RegisterDialog(ArrayList<AdminAccount> adminList, ArrayList<Club> clubList, ArrayList<MemberAccount> memberList) {
		this.memberList=memberList;
		this.clubList = clubList;
		setAlwaysOnTop(true);
		setSize(295, 474);
		setResizable(false);
		setFocusable(true);
		getContentPane().setLayout(null);
		setVisible(true);

		labelCreate = new JLabel("Create Account");
		labelCreate.setFont(new Font("SimSun", Font.PLAIN, 16));
		labelCreate.setBounds(10,11,144,38);
		getContentPane().add(labelCreate);

		label62 = new JLabel("Username:");
		label62.setBounds(32,60,106,29);
		getContentPane().add(label62);

		boxUserName = new JTextField();
		boxUserName.setBounds(148,60,108,29);
		getContentPane().add(boxUserName);

		labelPassword = new JLabel("Password:");
		labelPassword.setBounds(32,100,106,29);
		getContentPane().add(labelPassword);

		passBox2 = new JPasswordField();
		passBox2.setBounds(148,100,108,29);
		getContentPane().add(passBox2);

		labelPasswordConfirm = new JLabel("Confirm PassWd:");
		labelPasswordConfirm.setBounds(32,140,106,29);
		getContentPane().add(labelPasswordConfirm);

		passBox3 = new JPasswordField();
		passBox3.setBounds(148,140,108,29);
		getContentPane().add(passBox3);

		labelEmail = new JLabel("Email:");
		labelEmail.setBounds(32,180,106,29);
		getContentPane().add(labelEmail);

		boxEmail = new JTextField();
		boxEmail.setBounds(148,180,108,29);
		getContentPane().add(boxEmail);

		labelFirstName = new JLabel("First Name:");
		labelFirstName.setBounds(32,220,106,29);
		getContentPane().add(labelFirstName);

		boxFirstName = new JTextField();
		boxFirstName.setBounds(148,220,108,29);
		getContentPane().add(boxFirstName);

		labelLastName = new JLabel("Last Name:");
		labelLastName.setBounds(32,260,106,29);
		getContentPane().add(labelLastName);

		boxLastName = new JTextField();
		boxLastName.setBounds(148,260,108,29);
		getContentPane().add(boxLastName);

		label9 = new JLabel("Phone Number:");
		label9.setBounds(32,300,106,29);
		getContentPane().add(label9);

		boxPhone = new JTextField();
		boxPhone.setBounds(148,300,108,29);
		getContentPane().add(boxPhone);

		button3 = new JButton("Cancel");
		button3.addActionListener(this);
		button3.setBounds(32,392,108,29);
		getContentPane().add(button3);

		button4 = new JButton("Create");
		button4.addActionListener(this);
		button4.setBounds(148,392,108,29);
		getContentPane().add(button4);

		String [] clubListNames = new String [clubList.size()];
		for(int i = 0; i< clubListNames.length; i++){

			clubListNames[i] = clubList.get(i).getClubName();

		}

		labelClub = new JLabel("Choose Club");
		labelClub.setBounds(32,340,106,29);
		getContentPane().add(labelClub);

		comboBox = new JComboBox(clubListNames);
		comboBox.setBounds(148,340,108,29);
		comboBox.setSelectedIndex(0);
		comboBox.addActionListener(this);
		getContentPane().add(comboBox);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("Create"))
		{
			String clubName;
			Account acc = null;
			username = boxUserName.getText();
			if(username.equals("") || username == null){
				JOptionPane.showMessageDialog(null, "Please Enter a Username");
			}
			else
			{
				if(confirmPasswd())
				{

					getDialogData();

					clubName = (String)comboBox.getSelectedItem();
					System.out.println(clubName);
					for(Club c : clubList){
						if(clubName.equals(c.getClubName()))
						{
							acc = new MemberAccount(username, passWd, accType, email, fName, lName, pNo,true);
							((MemberAccount) acc).addClub(c);
							c.addMember((MemberAccount) acc);
							memberList.add((MemberAccount) acc);
							JOptionPane.showMessageDialog(null, "Account Created");
							this.dispose(); 
						}
					}
				}
			}
		}
		
	}

	protected void getDialogData() {
		accType = "Member";
		email = boxEmail.getText();
		fName = boxFirstName.getText();
		lName = boxLastName.getText();
		pNo = boxPhone.getText();	
	}

	protected boolean confirmPasswd() {
		passWd = passBox2.getText().toString();
		cPassWd = passBox3.getText().toString();
		if(passWd.equals("") || passWd == null){
			JOptionPane.showMessageDialog(null, "Please Enter a Password");
			return false;
		}
		else{
			if(!passWd.equals(cPassWd)){
				JOptionPane.showMessageDialog(null, "Passwords not matching");
				return false;
			}
		}
		return true;
	}
	
}
