package LoginPackageSRC;

import java.util.*;
public class SortArrayList{
	
	public SortArrayList(){}
	
	public ArrayList<Account> sortAdminArrayListByFName(ArrayList<Account> pList)
	{
		Collections.sort(pList, new Comparator<Account>(){
			@Override
			public int compare(Account acc1, Account acc2)
			{
				return acc1.getFName().compareTo(acc2.getFName());
			}
		});
		return pList;
	}
	
	public ArrayList<MemberAccount> sortMemberArrayListByFName(ArrayList<MemberAccount> pList)
	{
		Collections.sort(pList, new Comparator<MemberAccount>(){
			@Override
			public int compare(MemberAccount acc1, MemberAccount acc2)
			{
				return acc1.getFName().compareTo(acc2.getFName());
			}
		});
		return pList;
	}
	
	public ArrayList<Account> sortAdminArrayListByLName(ArrayList<Account> pList)
	{
		Collections.sort(pList, new Comparator<Account>(){
			@Override
			public int compare(Account acc1, Account acc2)
			{
				return acc1.getLName().compareTo(acc2.getLName());
			}
		});
		return pList;
	}
	
	public ArrayList<MemberAccount> sortMemberArrayListByLName(ArrayList<MemberAccount> pList)
	{
		Collections.sort(pList, new Comparator<MemberAccount>(){
			@Override
			public int compare(MemberAccount acc1, MemberAccount acc2)
			{
				return acc1.getLName().compareTo(acc2.getLName());
			}
		});
		return pList;
	}
}
