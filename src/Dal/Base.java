package Dal;
import java.util.*;
import java.util.Date;
import java.text.*;

public class Base {
		/**
	���� * ��ȡ��ǰ���������ڼ�<br>
	���� *
	���� * @param dt
	���� * @return ��ǰ���������ڼ�
	���� */
	 public static int getWeekOfNum(Date dt) {
	int[] weekDays = {0, 1, 2, 3, 4, 5, 6};
	Calendar cal = Calendar.getInstance();
	cal.setTime(dt);
	int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
	if (w < 0)
		w = 0;
	return weekDays[w];
	}
	 
	 /**
���� * ��ȡ��ǰ���������ڼ�<br>
���� *
���� * @param dt
���� * @return ��ǰ���������ڼ�
���� */
	public static String getWeekOfCn(Date dt) {
		String[] weekDays = {"������", "����һ", "���ڶ�", "������", "������", "������", "������"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
		w = 0;
		return weekDays[w];
	}
	
	//STRING������ 
	public static java.sql.Date stringToDate(String dateStr) 
	{ 
		return java.sql.Date.valueOf(dateStr); 
	} 
	//���ڵ�STRING 
	public static String dateToString(java.sql.Date datee) 
	{ 
		return datee.toString(); 
	}
	
	///��ȡ���
	public static long GetDay(Date starttime,Date endTime)
	{
		long  day=(endTime.getTime()-starttime.getTime())/(24*60*60*1000);
		return day;
	}
	
	///��ȡСʱ��
	public static long GetHours(Date starttime,Date endTime)
	{
		long  hours=(endTime.getTime()-starttime.getTime())/(60*60*1000);
		return hours;
	}
	
	///��ȡ���Ӳ�
	public static long GetMinutes(Date starttime,Date endTime)
	{
		long  minutes=(endTime.getTime()-starttime.getTime())/(60*1000);
		return minutes;
	}
}
