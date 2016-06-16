package com.boyz.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	public static void main(String[] args){		
		System.out.println(DateUtil.formatDate("{date:yyyyMM,0}", DateUtil.add(new java.util.Date(), -2 , "DAILY") ));
		System.out.println(DateUtil.formatDate("{date:dd,-2}", DateUtil.add(new java.util.Date(), -2 , "DAILY") ));
		System.out.println(DateUtil.formatDate("{date:yyyyMMdd,-2}", DateUtil.add(new java.util.Date(), 0 , "MONTHLY") ));
		System.out.println(new java.text.SimpleDateFormat("yyyyMMdd").format(DateUtil.add(new java.util.Date(), -2 , "MONTHLY")));
	}
	
	public static Date add(Date time , int nums , String type ){
		int year = new Integer(new java.text.SimpleDateFormat("yyyy").format(time)).intValue();
		int month = new Integer(new java.text.SimpleDateFormat("MM").format(time)).intValue();
		int day = new Integer(new java.text.SimpleDateFormat("dd").format(time)).intValue();
		int hour = new Integer(new java.text.SimpleDateFormat("HH").format(time)).intValue();
		int minute = new Integer(new java.text.SimpleDateFormat("mm").format(time)).intValue();
		int second = new Integer(new java.text.SimpleDateFormat("ss").format(time)).intValue();
		GregorianCalendar gct = new GregorianCalendar(year,month-1,day,hour,minute,second);
		if(type!=null && type.equals("DAILY")){
			gct.add(Calendar.DAY_OF_MONTH, nums);
		}
		else if(type.equals("MONTHLY")) {
			gct.add(Calendar.MONTH, nums);
		}
		else if(type.equals("HOUR")) {
			gct.add(Calendar.HOUR, nums);
		}
		else if(type.equals("MINUTE")) {
			gct.add(Calendar.MINUTE, nums);
		}
		else if(type.equals("SECOND")) {
			gct.add(Calendar.SECOND, nums);
		}
		return gct.getTime();
	}
	
	public static String formatDate(String src,Date time){
		
		String retu = "";
		
		int _year = new Integer(new java.text.SimpleDateFormat("yyyy").format(time)).intValue();
		int _month = new Integer(new java.text.SimpleDateFormat("MM").format(time)).intValue();
		int _day = new Integer(new java.text.SimpleDateFormat("dd").format(time)).intValue();
		int _hour = new Integer(new java.text.SimpleDateFormat("HH").format(time)).intValue();
		int _minute = new Integer(new java.text.SimpleDateFormat("mm").format(time)).intValue();
		int _second = new Integer(new java.text.SimpleDateFormat("ss").format(time)).intValue();
		
		int start_index = src.indexOf('{');
		int end_index = src.indexOf("}");
		
		if(start_index < 0 || end_index < 0)
			return src;
		String leftStr = src.substring(0,start_index);
		String rightStr = src.substring(end_index+1);
		String mixStr = src.substring(start_index,end_index+1);
		String type = mixStr.split(":")[0];
		String content = mixStr.split(":")[1];
		
		if(type.toLowerCase().equals("{date")){
			String[] splitStr = content.split(",");
			String cycle = "";
			String pattern = "";
			String cnt = "";
			
			if(splitStr.length == 3){
				cycle = content.split(",")[0];
				pattern = content.split(",")[1];
				cnt = content.split(",")[2];
			}
			else if(splitStr.length == 2){
				pattern = content.split(",")[0];
				cnt = content.split(",")[1];
			}
			
			GregorianCalendar gct = new GregorianCalendar(_year,_month-1,_day,_hour,_minute,_second);
			
			if(cycle.trim().toLowerCase().equals("daily") )
				gct.add(Calendar.DAY_OF_MONTH, new Integer(cnt.substring(0,cnt.length()-1)).intValue());
			else if(cycle.trim().toLowerCase().equals("monthly"))
				gct.add(Calendar.MONTH, new Integer(cnt.substring(0,cnt.length()-1)).intValue());
			else {
				if( (pattern.indexOf("yyyy")>-1 ||pattern.indexOf("y")>-1 )
						&& ( pattern.indexOf("MM") >-1 || pattern.indexOf("M") >-1 ) 
						&& ( pattern.indexOf("dd") >-1 || pattern.indexOf("d") >-1) ){
					
					gct.add(Calendar.DAY_OF_MONTH, new Integer(cnt.substring(0,cnt.length()-1)).intValue());
				}
				else if( ( pattern.indexOf("yyyy")>-1 || pattern.indexOf("y") >-1 )
						&& ( pattern.indexOf("MM")>-1 || pattern.indexOf("M")>-1 )
						&& ( pattern.indexOf("dd")<0 || pattern.indexOf("d")<0 ) ){
					gct.add(Calendar.MONTH, new Integer(cnt.substring(0,cnt.length()-1)).intValue());
				}
				else if( ( pattern.indexOf("yyyy") < 0 || pattern.indexOf("y") < 0 )
						&& ( pattern.indexOf("MM") < 0 || pattern.indexOf("M") < 0 )
						&& ( pattern.indexOf("dd") > -1 || pattern.indexOf("d") > -1 ) ){
					gct.add(Calendar.DAY_OF_MONTH, new Integer(cnt.substring(0,cnt.length()-1)).intValue());
				}
			}
			
			//int year = gct.get(Calendar.YEAR);
			//int month = gct.get(Calendar.MONTH) + 1;
			//int day = gct.get(Calendar.DAY_OF_MONTH);
			
			String ftime = new java.text.SimpleDateFormat(pattern).format(gct.getTime());
			
			retu = leftStr + ftime + rightStr;
		}
		
		
		int sub_start_index = src.indexOf('{');
		int sub_end_index = src.indexOf("}");
		if( sub_start_index >0 && sub_end_index >0 ){
			retu = formatDate(retu,time);
		}
		
		return retu ;
	}
}
