package LoginPackageSRC;

import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class DateFormater extends SimpleDateFormat
{
	public DateFormater(boolean time) 
	{
		super("yyyy-MM-dd HH:mm:ss");		
	}
	public DateFormater() 
	{
		super("yyyy-MM-dd");		
	}	
}
