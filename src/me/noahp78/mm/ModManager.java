package me.noahp78.mm;

import me.noahp78.mm.tasks.InstallMod;
import me.noahp78.mm.tasks.ModpackInstaller;
import me.noahp78.mm.tasks.RelaunchMinecraft;

public class ModManager {
	public static String currentpack = "0";
	public static String mcv = "";
	public static String argline = "";
	public static void main(String[] args){
		
		Log.debug("Starting ModManager V1");
		
		Log.debug("Trying to find all args");
		boolean justrelaunch = false;
		boolean forgebased = false;
		int curarg = -1;
		int maxarg = args.length;
		
		for(String a: args){
			curarg++;
			if(a.contains("mod-packid")){
				currentpack = args[(curarg+1)];
				Log.debug("Found ModPackID at " + curarg + " : " + args[(curarg+1)]);
				
				
			}else if(a.contains("mod-relaunch")){
				Log.debug("Just going to (try) relaunching minecraft");
				justrelaunch = true;
				
			}else if(a.contains("mod-mc_default")){
				mcv = args[(curarg+1)];
				Log.debug("Default Minecraft Version determined: " + mcv);
			}else if(a.contains("mod-forge")){
				Log.debug("We have a MCForge based pack. - Need to inject TweakClass");
				forgebased=true;
			}else if(a.contains("-accessToken")){
				String accesstoken = args[(curarg+1)];
				Log.setauthtoken(accesstoken);
			}else if(a.contains("-username")){
				String username = args[(curarg+1)];
				Log.setusername(username);
			}else{
					//Log.debug("Found (unused) argument: " +  a);
			}
			if(a!=null){
			argline = argline + a + " " ;			
			}else{
				Log.debug("found 'null' arg at " + curarg);
				
			}
		}
		if(forgebased){
			argline = argline + "--tweakClass cpw.mods.fml.common.launcher.FMLTweaker";
		}
		if(!justrelaunch){
			//Simple check to see if we found what we wanted
			if(!currentpack.equals("0")){
				Log.debug("Starting download of ModPack " + currentpack);
				ModpackInstaller.install(currentpack);
				
				//Just to test
				try {
					ModpackInstaller.getMod(currentpack);
					InstallMod.InstallMod("2", 0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RelaunchMinecraft.doTask();
			}else{
				Log.debug("No valid modpack ID found or is 0. Starting normal minecraft");
				RelaunchMinecraft.doTask();
			}
		}else{
			Log.debug("Just relaunching Minecraft");
			RelaunchMinecraft.doTask();
		}
	}
}
