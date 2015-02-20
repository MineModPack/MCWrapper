package me.noahp78.mm;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Log {

	public static void debug(String msg){
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(cal.getTime());
		String m = "[ModManager][" + time +"][Debug] " + msg;
		System.out.println(m);
	}
}
