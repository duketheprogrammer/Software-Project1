package LoginPackageSRC;

import java.io.*;
import java.util.*;

public class SavingArrayListToFile {
	
	private File f1,f2;
	private FileWriter fWriter;
	private PrintWriter pWriter;
	private Scanner pReader;
	
	public SavingArrayListToFile() {}
	
	public void saveAdminsToFile(ArrayList<Account> accList)
	{
		f1 = new File("AdminList.txt");
		try {
			fWriter = new FileWriter(f1, false);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pWriter = new PrintWriter(fWriter, true);

		for(Account acc: accList)
		{
			pWriter.println(acc.getUserName());
			pWriter.println(acc.getPassWord());
			pWriter.println(acc.getAccType());
			pWriter.println(acc.getEmail());
			pWriter.println(acc.getFName());
			pWriter.println(acc.getLName());
			pWriter.println(acc.getPNo());
		}
		pWriter.close();
	}
	
	public void saveMembersToFile(ArrayList<MemberAccount> accList) {
		// TODO Auto-generated method stub
		f1 = new File("MemberList.txt");
		try {
			fWriter = new FileWriter(f1, false);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pWriter = new PrintWriter(fWriter, true);

		for(MemberAccount acc: accList)
		{
			pWriter.println(acc.getUserName());
			pWriter.println(acc.getPassWord());
			pWriter.println(acc.getAccType());
			pWriter.println(acc.getEmail());
			pWriter.println(acc.getFName());
			pWriter.println(acc.getLName());
			pWriter.println(acc.getPNo());
		}
		pWriter.close();
	}

	public ArrayList<Account> loadAdminsToArray(ArrayList<Account> accList)
	{
		f2 = new File("AdminList.txt");
		try {
			pReader = new Scanner(f2);
			while (pReader.hasNext())
			{
				String username = pReader.nextLine();
				String passWd = pReader.nextLine();
				String accType = pReader.nextLine();
				String email = pReader.nextLine();
				String fName = pReader.nextLine();
				String lName = pReader.nextLine();
				String pNo = pReader.nextLine();
			
				Account acc = new Account(username,passWd,accType, email, fName, lName, pNo);
				accList.add(acc);
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pReader.close();
		return accList;
	}
	
	public ArrayList<MemberAccount> loadMembersToArray(ArrayList<MemberAccount> accList){
		f2 = new File("MemberList.txt");
		try {
			pReader = new Scanner(f2);
			while (pReader.hasNext())
			{
				String username = pReader.nextLine();
				String passWd = pReader.nextLine();
				String accType = pReader.nextLine();
				String email = pReader.nextLine();
				String fName = pReader.nextLine();
				String lName = pReader.nextLine();
				String pNo = pReader.nextLine();
			
				MemberAccount acc = new MemberAccount(username,passWd,accType, email, fName, lName, pNo,false);
				accList.add(acc);
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pReader.close();
		return accList;
	}
}
