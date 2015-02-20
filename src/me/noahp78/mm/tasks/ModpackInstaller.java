package me.noahp78.mm.tasks;

import me.noahp78.mm.Log;
import me.noahp78.mm.WinUtil;


public class ModpackInstaller {
	public static String appdata;
	public static String moddb = appdata + "//moddb//";
	public static String mc = appdata +"//.minecraft//";
	public static void install(String id){
		appdata=WinUtil.getappdata();
		Log.debug("Appdata found in " + appdata);
		
		
		
		
		Log.debug("Relaunching Minecraft with Modpack installed");
		
		
		
	}
}
