package me.noahp78.mm.tasks;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

import me.noahp78.mm.Log;
import me.noahp78.mm.ModManager;
import me.noahp78.mm.WinUtil;

public class RelaunchMinecraft {
	public static boolean relaunchedonce =false;
	public static boolean mcclasserror = false;
	public static int trycount = 0; 
	public static void doTask(){
		Log.debug("Relaunching Minecraft");
		List<String> launch = ManagementFactory.getRuntimeMXBean().getInputArguments();
		String javaargs = "";
		for (String item : launch) {
		    javaargs = javaargs + item + " ";
		}
		Log.debug("Found full command string: " + javaargs);
		
		
		String s = File.separator;
		String appdata = WinUtil.getappdata() + s+ "."  + "minecraft";
		
		
		//TODO determine if bug (may) be because we could not find a Main class?!?
		
		String mcfix = "";
		String mcfix2= "";
		
		//TODO We need to update this to launch the jar in ModManager-(packid)/minecraft.jar when we are done!
		String jarlocation = appdata + s +"versions"+ s + ModManager.mcv + s + ModManager.mcv + ".jar ";
		
		
		if(trycount==0){
			mcclasserror=true;
			
		}
		if(mcclasserror){
			if(trycount==2){
				Log.debug("added ForgeClassPathFix to launch argument and Jar Injection. hope this helps.");
				mcfix2 = "-jar " + jarlocation + "net.minecraft.launchwrapper.Launch";
				trycount++;
				
			}else if(trycount==0){
			Log.debug("added ForgeClassPathFix to launch argument. hope this helps.");
			mcfix2 = " net.minecraft.launchwrapper.Launch";
			trycount++;
			}else if(trycount==1){
				Log.debug("added VanillaClassPathFix to launch argument. hope this helps.");
				mcfix2 = " net.minecraft.client.main.Main";
				trycount++;
					
				
			}else{
			}
			
		}
		mcfix = getclasspath();
		

		String fulljarpath =  mcfix + "" + jarlocation + mcfix2;
		Log.debug("Going to launch " + fulljarpath + " - Start Constructing Full argument");
		String launchcmd = "java " + javaargs +fulljarpath;
		
		Log.debug("Launch Arguments Found: " + ModManager.argline);
		
		launchcmd = launchcmd + " "+ ModManager.argline;
		
		Log.debug("Final launch argument " + launchcmd);
		
	
		try {
			mcclasserror=false;
		      String line;
		      Process p = Runtime.getRuntime().exec(launchcmd);
		      BufferedReader bri = new BufferedReader
		        (new InputStreamReader(p.getInputStream()));
		      BufferedReader bre = new BufferedReader
		        (new InputStreamReader(p.getErrorStream()));
		      while ((line = bri.readLine()) != null) {
		        System.out.println(line);
		      }
		      bri.close();
		      while ((line = bre.readLine()) != null) {
		        Log.mc(line);
		        if(line.contains("Could not find or load main class")){
		        	mcclasserror=true;
		        	Log.debug("Found a bad instance where we could not load the main class.");
		        }
		      }
		      bre.close();
		      p.waitFor();
		      
		      if(mcclasserror){
		    	  
		    	  if(!(trycount==3)){
		    		  relaunchedonce=true;
		    		  RelaunchMinecraft.doTask();
		    	  }else{
		    		  Log.debug("Already tried " + trycount + " classpath fixes. it didn't work so Not retrying");
		    	  }
		      }
		      Log.debug("Minecraft Closed - ModManager cleaning up");
		    }
		    catch (Exception err) {
		      err.printStackTrace();
		    }
	}
	public static String getclasspath(){
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        String cp = "-cp ";
        URL[] urls = ((URLClassLoader)cl).getURLs();
 
        for(URL url: urls){
        	String urlfile = url.getFile().toString();
        	urlfile =  urlfile.substring(1);
        	
        	cp = cp + urlfile + ";";
        }
        Log.debug("Build Classpath " + cp);
        return cp;
		
	}
}
