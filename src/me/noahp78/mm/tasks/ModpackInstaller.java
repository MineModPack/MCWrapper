package me.noahp78.mm.tasks;

import java.io.File;
import java.util.HashMap;

import me.noahp78.mm.Log;
import me.noahp78.mm.ModManager;
import me.noahp78.mm.Util;
import me.noahp78.mm.api.APIHelper;
import me.noahp78.mm.api.Mod;
import me.noahp78.mm.api.ModVersion;
import me.noahp78.mm.api.Modpack;
import me.noahp78.mm.api.ModpackVersion;
import me.noahp78.mm.api.Web;

import com.google.gson.Gson;


public class ModpackInstaller {
	public static String appdata;
	public static String moddb = appdata + "//moddb//";
	public static String mc = appdata +"//.minecraft//";
    public static String jarfile;
	
	/**
	 *  Holds all installed mods in the format:
	 *  (modid):(versionid)
	 *  
	 *  This is used in dependency resolving.
	 */
	public static HashMap<String, Boolean>installedmods = new HashMap<String,Boolean>();
	public static String mcjard;
	public static String mcjar;
	
	public static void install(String id) throws Exception{
		appdata= Util.getappdata();
		Log.debug("Appdata found in " + appdata);
		String s = File.separator;
		mcjard = appdata + s + ".minecraft" + s + "versions" + s + ModManager.mcv + s + ModManager.mcv + ".jar";
		mcjar = appdata + s + ".minecraft" + s + "versions" + s + "ModManager-" + id + s +"minecraft.jar";
		Log.debug("Making my own copy of " + mcjard + " and putting it into " + mcjar);
		jarfile = mcjar;

		Util.copyFile(new File(mcjard), new File(mcjar));
		
		Log.debug("Copy made... Let's get installing!");
		Modpack m = APIHelper.getModPack(id);
		Log.debug("Installing " + m.mod_name +" with " + m.versions.length + " versions");
		int latestversion = m.versions.length-1;
		ModpackVersion toinstall = m.versions[latestversion];
		Log.debug("Determined I need to install " + toinstall.mods.length + " mods from version " + toinstall.desc);
		for (ModVersion mod : toinstall.mods) {
			String versionid = mod.id;
			String modid = mod.mod_id;
			Log.debug("Installing " + versionid + " from modid" + modid);
			InstallMod.DoTask(modid, versionid);
		}


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
