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
	private JLabel label6;
	private JLabel label7;
	protected JTextField box2, box3, box4, box5, box6;
	protected JPasswordField passBox2, passBox3;
	protected JButton button3;
	protected JButton button4;
	protected ArrayList<MemberAccount> memberList;
	private JLabel label10;
	protected JComboBox comboBox;
	private JLabel label8;
	private JLabel label9;
	protected ArrayList<Club> clubList;

	public RegisterDialog(ArrayList<AdminAccount> adminList, ArrayList<Club> clubList, ArrayList<MemberAccount> memberList) {
		this.memberList=memberList;
		this.clubList = clubList;
		setAlwaysOnTop(true);
		setSize(295, 474);
		setResizable(false);
		setFocusable(true);
		getContentPane().setLayout(null);
		setVisible(true);

		label6 = new JLabel("Create Account");
		label6.setFont(new Font("SimSun", Font.PLAIN, 16));
		label6.setBounds(10,11,144,38);
		getContentPane().add(label6);

		label6 = new JLabel("Username:");
		label6.setBounds(32,60,106,29);
		getContentPane().add(label6);

		box2 = new JTextField();
		box2.setBounds(148,60,108,29);
		getContentPane().add(box2);

		label7 = new JLabel("Password:");
		label7.setBounds(32,100,106,29);
		getContentPane().add(label7);

		passBox2 = new JPasswordField();
		passBox2.setBounds(148,100,108,29);
		getContentPane().add(passBox2);

		label8 = new JLabel("Confirm PassWd:");
		label8.setBounds(32,140,106,29);
		getContentPane().add(label8);

		passBox3 = new JPasswordField();
		passBox3.setBounds(148,140,108,29);
		getContentPane().add(passBox3);

		label6 = new JLabel("Email:");
		label6.setBounds(32,180,106,29);
		getContentPane().add(label6);

		box3 = new JTextField();
		box3.setBounds(148,180,108,29);
		getContentPane().add(box3);

		label7 = new JLabel("First Name:");
		label7.setBounds(32,220,106,29);
		getContentPane().add(label7);

		box4 = new JTextField();
		box4.setBounds(148,220,108,29);
		getContentPane().add(box4);

		label8 = new JLabel("Last Name:");
		label8.setBounds(32,260,106,29);
		getContentPane().add(label8);

		box5 = new JTextField();
		box5.setBounds(148,260,108,29);
		getContentPane().add(box5);

		label9 = new JLabel("Phone Number:");
		label9.setBounds(32,300,106,29);
		getContentPane().add(label9);

		box6 = new JTextField();
		box6.setBounds(148,300,108,29);
		getContentPane().add(box6);

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

		label10 = new JLabel("Choose Club");
		label10.setBounds(32,340,106,29);
		getContentPane().add(label10);

		comboBox = new JComboBox(clubListNames);
		comboBox.setBounds(148,340,108,29);
		comboBox.setSelectedIndex(0);
		comboBox.addActionListener(this);
		getContentPane().add(comboBox);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
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
								this.dispose(); 
							}
						}

					}
				}
			}
		}
		
	}
	
}
