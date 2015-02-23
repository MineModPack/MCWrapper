package me.noahp78.mm;

import me.noahp78.mm.tasks.ModpackInstaller;
import me.noahp78.mm.tasks.RelaunchMinecraft;

public class ModManager {
	public static String currentpack = "0";
	public static void main(String[] args){
		
		Log.debug("Starting ModManager V1");
		
		Log.debug("Trying to find all args");
		int curarg = -1;
		int maxarg = args.length;
		for(String a: args){
			curarg++;
			if(a.contains("mod-packid")){
				currentpack = args[(curarg+1)];
				Log.debug("Found ModPackID at " + curarg + " : " + args[(curarg+1)]);
				
				
			}else{
				Log.debug("Found (unused) argument: " +  a);
			}
		}
		//Simple check to see if we found what we wanted
		if(!currentpack.equals("0")){
			Log.debug("Starting download of ModPack " + currentpack);
			ModpackInstaller.install(currentpack);
			//Just to test
			try {
				ModpackInstaller.getMod(currentpack);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RelaunchMinecraft.doTask();
		}else{
			Log.debug("No valid modpack ID found or is 0. Starting normal minecraft");
			RelaunchMinecraft.doTask();
		}
	}
}
