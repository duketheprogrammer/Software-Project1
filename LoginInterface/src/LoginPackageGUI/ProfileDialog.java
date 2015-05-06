package LoginPackageGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import LoginPackageSRC.AdminAccount;
import LoginPackageSRC.Club;
import LoginPackageSRC.MemberAccount;

@SuppressWarnings("serial")
public class ProfileDialog extends RegisterDialog implements ActionListener {


	public ProfileDialog(ArrayList<AdminAccount> adminList, ArrayList<Club> clubList, ArrayList<MemberAccount> memberList) {
		super(adminList,clubList,memberList);
		box2.setEnabled(false);
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
