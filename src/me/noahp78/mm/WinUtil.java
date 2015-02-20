package me.noahp78.mm;

public class WinUtil {
	public static String getappdata(){
		String ad = System.getenv("APPDATA") + "\\.modmanager\\";
		return ad;

	}
}
