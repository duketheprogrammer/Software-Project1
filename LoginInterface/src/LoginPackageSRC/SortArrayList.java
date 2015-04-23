package LoginPackageSRC;

import java.util.*;
public class SortArrayList{
	
	public SortArrayList(){}
	
	public ArrayList<AdminAccount> sortAdminArrayListByFName(ArrayList<AdminAccount> pList)
	{
		Collections.sort(pList, new Comparator<AdminAccount>(){
			@Override
			public int compare(AdminAccount acc1, AdminAccount acc2)
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
	
	public ArrayList<AdminAccount> sortAdminArrayListByLName(ArrayList<AdminAccount> pList)
	{
		Collections.sort(pList, new Comparator<AdminAccount>(){
			@Override
			public int compare(AdminAccount acc1, AdminAccount acc2)
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
