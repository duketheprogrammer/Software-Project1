package LoginPackageGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import LoginPackageSRC.*;


@SuppressWarnings("serial")
public class MailDialog extends JDialog {
	private Mailing mailService;
	private JLabel labelSubject;
	private JTextField textSubject;
	private JLabel labelTo;
	private JTextField textTo;
	private JTextArea textContent;
	private JButton buttonSend;
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
		
		labelTo = new JLabel("To:");
		labelTo.setFont(new Font("SimSun", Font.BOLD, 13));
		labelTo.setBounds(24,26,110,22);
		this.add(labelTo);		
		
		textTo = new JTextField();
		textTo.setBounds(124,26,210,22);
		this.add(textTo);		
				
		labelSubject = new JLabel("Subject:");
		labelSubject.setFont(new Font("SimSun", Font.BOLD, 13));
		labelSubject.setBounds(24,71,110,22);
		this.add(labelSubject);
		
		textSubject = new JTextField();
		textSubject.setBounds(124,71,210,22);
		this.add(textSubject);	
		
		textContent = new JTextArea();
		textContent.setBounds(24,116,310,400);
		this.add(textContent);	
		
		buttonSend = new JButton("send Mail");
		buttonSend.setBounds(24, 538, 200, 22);
		buttonSend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				mailService.setSubjectLine(textSubject.getText());
				mailService.setMailContent(textContent.getText());
				mailService.sendMail();
			}
		});
		this.add(buttonSend);
	}
	public void contactClub(Club club, MemberAccount ma)
	{
		textTo.setText( club.getClubName());
		textSubject.setText("Request for club information");
		textContent.setText("Hello " + club.getClubName() + ",\n\r" +
							"my name is " + ma.getFName() + " and I would like to have some information" +
				 			" about your club, please email me at " + ma.getEmail() + "\r\n" +
							"Regards,\r\n" + ma.getFName());
		mailService.setRecipients(club.getCommitteeMails());
	}
}
