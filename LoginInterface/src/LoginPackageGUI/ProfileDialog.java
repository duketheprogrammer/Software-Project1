package LoginPackageGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.peer.LabelPeer;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.html.StyleSheet.BoxPainter;

import com.sun.jndi.cosnaming.IiopUrl.Address;

import LoginPackageSRC.AdminAccount;
import LoginPackageSRC.Club;
import LoginPackageSRC.CommitteeData;
import LoginPackageSRC.DatabaseConnector;
import LoginPackageSRC.MemberAccount;

@SuppressWarnings("serial")
public class ProfileDialog extends RegisterDialog implements ActionListener {
	
	private JLabel yearOfCourseLabel, nameOfCourseLabel, dateOfBirthLabel, addressLabel, label92;
	private JButton updateBtn;
	private JTextField yearOfCourseField, nameOfCourseField, dateOfBirthField, addressField;
	private MemberAccount mA;
	private JCheckBox checkCommittee;
	private boolean bCheckCommittee;
	

	public ProfileDialog(ArrayList<AdminAccount> adminList, ArrayList<Club> clubList, ArrayList<MemberAccount> memberList, MemberAccount mA) {
		super(adminList,clubList,memberList);
		boxUserName.setEnabled(false);
		comboBox.setVisible(false);
		labelClub.setVisible(false);
		labelCreate.setVisible(false);
		boxLastName.setEnabled(false);
		boxFirstName.setEnabled(false);
		button3.setVisible(false);
		button4.setVisible(false);
		setAlwaysOnTop(true);
		setSize(295, 640);
		setResizable(false);
		setFocusable(true);
		getContentPane().setLayout(null);
		setVisible(true);
		
		bCheckCommittee = (mA.getCommitteeData()!=null);
		this.mA = mA;
		
		
		
		label92 = new JLabel("Update Account");
		label92.setFont(new Font("SimSun", Font.PLAIN, 16));
		label92.setBounds(10,11,144,38);
		getContentPane().add(label92);
		
		checkCommittee = new JCheckBox("committee related information");
		checkCommittee.setBounds(32,340,206,29);
		getContentPane().add(checkCommittee);
		checkCommittee.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
            	if(e.getStateChange() == ItemEvent.SELECTED)
            	{
            		bCheckCommittee=true;
            	}
            	else
            	{
               		bCheckCommittee=false;
               	}
            	updateView();
            }

        });
		
		yearOfCourseLabel = new JLabel("Course Year:");
		yearOfCourseLabel.setBounds(32,380,106,29);
		getContentPane().add(yearOfCourseLabel);
		
		yearOfCourseField = new JTextField();
		yearOfCourseField.setBounds(148,380,108,29);
		getContentPane().add(yearOfCourseField);
		
		nameOfCourseLabel = new JLabel("Course Name:");
		nameOfCourseLabel.setBounds(32,420,106,29);
		getContentPane().add(nameOfCourseLabel);
		
		nameOfCourseField = new JTextField();
		nameOfCourseField.setBounds(148,420,108,29);
		getContentPane().add(nameOfCourseField);
		
		dateOfBirthLabel = new JLabel("Date of Birth:");
		dateOfBirthLabel.setBounds(32,460,106,29);
		getContentPane().add(dateOfBirthLabel);
		
		dateOfBirthField = new JTextField();
		dateOfBirthField.setBounds(148,460,108,29);
		getContentPane().add(dateOfBirthField);
		
		addressLabel = new JLabel("Address:");
		addressLabel.setBounds(32,500,106,29);
		getContentPane().add(addressLabel);
		
		addressField = new JTextField();
		addressField.setBounds(148,500,108,29);
		getContentPane().add(addressField);
		
		updateBtn = new JButton("Update");
		updateBtn.addActionListener(this);
		updateBtn.setBounds(148,552,108,29);
		getContentPane().add(updateBtn);
		
		boxUserName.setText(mA.getUserName());
		labelPassword.setText("New Password");
		passBox2.setText(mA.getPassWord());
		passBox3.setText(mA.getPassWord());
		boxEmail.setText(mA.getEmail());
		boxPhone.setText(mA.getPNo());
		boxFirstName.setText(mA.getFName());
		boxLastName.setText(mA.getLName());
		if(mA.getCommitteeData()!= null)
		{
			yearOfCourseField.setText(mA.getCommitteeData().getCourseYear());
			nameOfCourseField.setText(mA.getCommitteeData().getCourseName());
			dateOfBirthField.setText(mA.getCommitteeData().getDateOfBirth());
			addressField.setText(mA.getCommitteeData().getAddress());
		}
		updateView();
	}
	private void updateView()
	{
		if(bCheckCommittee)
		{
			yearOfCourseField.setEnabled(true);
			nameOfCourseField.setEnabled(true);
			dateOfBirthField.setEnabled(true);
			addressField.setEnabled(true);
			checkCommittee.setSelected(true);
		}
		else
		{
			yearOfCourseField.setEnabled(false);
			nameOfCourseField.setEnabled(false);
			dateOfBirthField.setEnabled(false);
			addressField.setEnabled(false);
			checkCommittee.setSelected(false);

		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("Update"))
		{		
			String courseYear, courseName, dateOfBirth, address;
			if(confirmPasswd())
			{
				courseYear = yearOfCourseField.getText();
				courseName = nameOfCourseField.getText();
				dateOfBirth = dateOfBirthField.getText();
				address = addressField.getText();
				getDialogData();
				mA.setPassWord(passWd);
				mA.setPNo(boxPhone.getText());
				mA.setEmail(boxEmail.getText());
				if (bCheckCommittee)
				{
					mA.setCommitteeData(new CommitteeData(courseYear, courseName, dateOfBirth, address));
				}
				else
				{
					mA.setCommitteeData(null);
				}
				try {
					LoginScreen.DBCon.updateMember(mA);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
			else
			{
				
			}
		}
		else
		{
			super.actionPerformed(e);
		}
		
	}
	
}
