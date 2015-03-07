package me.noahp78.mm;

import me.noahp78.mm.api.APIHelper;
import me.noahp78.mm.api.Mod;
import me.noahp78.mm.api.ModVersion;
import me.noahp78.mm.tasks.DownloadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class Util {
    private static String workingDirectory;
    //here, we assign the name of the OS, according to Java, to a variable...
    private static  String OS = (System.getProperty("os.name")).toUpperCase();

    public static void copyFile(File sourceFile, File destFile) throws IOException {
	    if(!destFile.exists()) {
	        destFile.createNewFile();
	    }

	    FileChannel source = null;
	    FileChannel destination = null;

	    try {
	        source = new FileInputStream(sourceFile).getChannel();
	        destination = new FileOutputStream(destFile).getChannel();
	        destination.transferFrom(source, 0, source.size());
	    }
	    finally {
	        if(source != null) {
	            source.close();
	        }
	        if(destination != null) {
	            destination.close();
	        }
	    }
	}
    public static String BuildDLurl(String versionid) throws Exception {
        ModVersion v = APIHelper.getVersion(versionid);
        if(v.desc!=null){
            String modid = v.mod_id;
            String vfile = v.name;
            Mod a = APIHelper.getMod(modid);
            String modname = a.mod_name.replace(" ", "_");
            String url = "http://content1.newaurorastudios.tk/mods/" + modname + "/" + vfile;
            return url;
        }else{
            Log.error("Could not build ModUrl for " + versionid);
            return null;

        }


    }

    public static String getappdata(){
        //to determine what the workingDirectory is.
        //if it is some version of Windows
        if (OS.contains("WIN"))
        {
            //it is simply the location of the "AppData" folder
            workingDirectory = System.getenv("AppData");
        }
        //Otherwise, we assume Linux or Mac
        else
        {
            //in either case, we would start in the user's home directory
            workingDirectory = System.getProperty("user.home");
            //if we are on a Mac, we are not done, we look for "Application Support"
            workingDirectory += "/Library/Application Support";
        }
        return workingDirectory;
	}
}
