package LoginPackageGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import LoginPackageSRC.AdminAccount;
import LoginPackageSRC.Club;
import LoginPackageSRC.MemberAccount;

@SuppressWarnings("serial")
public class ProfileDialog extends RegisterDialog implements ActionListener {
	
	private JLabel yearOfCourseLabel, nameOfCourseLabel, dateOfBirthLabel, addressLabel, label92;
	private JButton updateBtn;
	private JTextField yearOfCourseField, nameOfCourseField, dateOfBirthField, addressField;
	

	public ProfileDialog(ArrayList<AdminAccount> adminList, ArrayList<Club> clubList, ArrayList<MemberAccount> memberList) {
		super(adminList,clubList,memberList);
		box2.setEnabled(false);
		passBox2.setEnabled(false);
		passBox3.setEnabled(false);
		comboBox.setVisible(false);
		label10.setVisible(false);
		label6.setVisible(false);
		button3.setVisible(false);
		button4.setVisible(false);
		setAlwaysOnTop(true);
		setSize(295, 600);
		setResizable(false);
		setFocusable(true);
		getContentPane().setLayout(null);
		setVisible(true);
		
		label92 = new JLabel("Update Account");
		label92.setFont(new Font("SimSun", Font.PLAIN, 16));
		label92.setBounds(10,11,144,38);
		getContentPane().add(label92);
		
		yearOfCourseLabel = new JLabel("Course Year:");
		yearOfCourseLabel.setBounds(32,340,106,29);
		getContentPane().add(yearOfCourseLabel);
		
		yearOfCourseField = new JTextField();
		yearOfCourseField.setBounds(148,340,108,29);
		getContentPane().add(yearOfCourseField);
		
		nameOfCourseLabel = new JLabel("Course Name:");
		nameOfCourseLabel.setBounds(32,380,106,29);
		getContentPane().add(nameOfCourseLabel);
		
		nameOfCourseField = new JTextField();
		nameOfCourseField.setBounds(148,380,108,29);
		getContentPane().add(nameOfCourseField);
		
		dateOfBirthLabel = new JLabel("Date of Birth:");
		dateOfBirthLabel.setBounds(32,420,106,29);
		getContentPane().add(dateOfBirthLabel);
		
		dateOfBirthField = new JTextField();
		dateOfBirthField.setBounds(148,420,108,29);
		getContentPane().add(dateOfBirthField);
		
		addressLabel = new JLabel("Address:");
		addressLabel.setBounds(32,460,106,29);
		getContentPane().add(addressLabel);
		
		addressField = new JTextField();
		addressField.setBounds(148,460,108,29);
		getContentPane().add(addressField);
		
		updateBtn = new JButton("Update");
		updateBtn.addActionListener(this);
		updateBtn.setBounds(148,512,108,29);
		getContentPane().add(updateBtn);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("Update")){
			
		}
		else
		{
			super.actionPerformed(e);
		}
		
	}
	
}
