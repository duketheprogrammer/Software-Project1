package LoginPackageGUI;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class DialogBuilder implements ActionListener{

	private JDialog d;
	private JLabel label6, label7, label8, label9, label10;
	private JTextField box2, box3, box4;
	private JTextField box5, box6, box7, box8;
	private JPasswordField passBox2, passBox3;
	private JButton button3, button4;
	private JComboBox comboBox;
	
	public DialogBuilder() {
		// TODO Auto-generated constructor stub
		initalizeDialog();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DialogBuilder();
	}
	
	private void initalizeDialog() {
		
		d = new JDialog();
		d.setAlwaysOnTop(true);
		d.setSize(449, 205);
		d.setResizable(false);
		d.setFocusable(true);
		d.getContentPane().setLayout(null);
		d.setVisible(true);

		label6 = new JLabel("Choose Club");
		label6.setFont(new Font("SimSun", Font.PLAIN, 16));
		label6.setBounds(136,11,144,38);
		d.getContentPane().add(label6);

		
		String [] clubListNames = new String [clubList.size()];
		for(int i = 0; i< clubListNames.length; i++){
			clubListNames[i] = clubList.get(i).getClubName();
		}
		
		comboBox = new JComboBox(clubListNames);
		comboBox.setBounds(136,60,165,38);
		comboBox.setSelectedIndex(1);
		comboBox.addActionListener(this);
		d.getContentPane().add(comboBox);
		
		button3 = new JButton("Cancel");
		button3.addActionListener(this);
		button3.setBounds(10,137,108,29);
		d.getContentPane().add(button3);

		button4 = new JButton("Create");
		button4.addActionListener(this);
		button4.setBounds(298,137,108,29);
		d.getContentPane().add(button4);

		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
