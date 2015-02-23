package me.noahp78.mm.tasks;

import java.util.HashMap;

import me.noahp78.mm.Log;
import me.noahp78.mm.WinUtil;
import me.noahp78.mm.api.Mod;
import me.noahp78.mm.api.Web;

import com.google.gson.Gson;


public class ModpackInstaller {
	public static String appdata;
	public static String moddb = appdata + "//moddb//";
	public static String mc = appdata +"//.minecraft//";
	
	/**
	 *  Holds all installed mods in the format:
	 *  (modid):(versionid)
	 *  
	 *  This is used in dependency resolving.
	 */
	public static HashMap<String, Boolean>installedmods = new HashMap<String,Boolean>();
	
	
	public static void install(String id){
		appdata=WinUtil.getappdata();
		Log.debug("Appdata found in " + appdata);
		
		
		
		
		Log.debug("Relaunching Minecraft with Modpack installed");
		
		
		
	}
	
	
	public static Mod getMod(String id) throws Exception{
		String r = Web.getResult("http://stream1-nas.cloudapp.net/mm/mod.php?id=" + id + "&api");
		Log.debug(r);
		Gson g = new Gson();
		Mod m = g.fromJson(r, Mod.class);
		Log.debug("ModDetails: " + m.mod_name);
		return m;
		
		
	}
}
