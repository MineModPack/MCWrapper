package me.noahp78.mm;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Log {
	private static String authtoken = "";
	private static String username = "";
	public static void debug(String msg){
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(cal.getTime());
		String m = "[ModManager][" + time +"][Debug] " + msg;
		print(m);
		
	}
    public static void info(String msg){
        String m = "[ModManager] " + msg;
        print(m);
    }
    public static void error(String msg){
        String m = "[ModManager][ERROR] " + msg;
        print(m);
    }
	private static void print(String msg){
		int total = authtoken.length();
		int current = 0;
		String replace = "";
		while (current != total){
			current++;
			replace = replace +"x";
		}
		
		msg = msg.replace(authtoken, replace);
		total = username.length();
		current = 0;
		replace = "";
		while (current != total){
			current++;
			replace = replace +"x";
		}
		msg = msg.replace(username, replace);
		System.out.println(msg);
		
	}
	public static void mc(String msg){
		String m = "[MC]" + msg;
		print(m);
		
	}
	public static void setauthtoken(String token){
        authtoken = token;
        Log.debug("Token Changed to " + token);
		

	}
	public static void setusername(String user){
        username = user;
        Log.debug("Username Changed to " + user);

	}
}
