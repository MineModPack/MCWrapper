package me.noahp78.mm.tasks;

import me.noahp78.mm.Log;
import me.noahp78.mm.ModZip;
import me.noahp78.mm.Util;
import me.noahp78.mm.api.APIHelper;
import me.noahp78.mm.api.Mod;
import me.noahp78.mm.api.ModVersion;
import me.noahp78.mm.api.mod_version;

import java.io.File;

public class InstallMod {
	/** Install mod ID
	 * 
	 * @param versionid the version to install
	 */
	public static void DoTask(String mod, String versionid){
		InstallDependencies(mod,versionid);
		InstallMod(mod,versionid);
	}
	
	
	/** resolve and install dependencies of mod (id)
	 * @author noah
	 * @param version
	 */
	public static void InstallDependencies(String mod, String version){
		Log.debug("starting dependency resolving for Version " + version + " from mod " + mod);
        try {
            ModVersion M = APIHelper.getVersion(version);
            for (String a : M.depends){
                ModVersion b = APIHelper.getVersion(a);

                InstallMod(b.mod_id,a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.debug("Dependency resolving for mod " + mod + " is done");



    }
	public static void InstallMod(String mod, String version) {
        if(version==null) {
            return;
        }
        if (!(ModpackInstaller.installedmods.containsKey(version))) {
            try {
                Mod m = APIHelper.getMod(mod);
                ModVersion install = APIHelper.getVersion(version);

                Log.debug("[InstallMod] Installing v" + install.name + " from mod " + m.mod_name);
                //TODO download code and stuff
                String ModLoc = DownloadTask.makeModLoc(install.name, m.mod_name);
                DownloadTask.DownloadMod(Util.BuildDLurl(version), ModLoc);
                if (install.installtype == 1) {
                    //This is a mod that has to be patched to the Minecraft Jar
                    Log.debug("[InstallMod] " + m.mod_name + " is going to be patched in the Minecraft Jar");
                    ModZip.patch(ModLoc,ModpackInstaller.jarfile);
                } else {
                    Log.debug("[InstallMod] " + m.mod_name + " is going to be put in the mods directory");
                    //This is a mod that goes in the jar directory!
                    String s = File.separator;
                    String moddir = Util.getappdata() + s + ".minecraft" + s + "mods"+ s + install.desc;
                    new File(Util.getappdata() + s + ".minecraft" + s + "mods" + s).mkdirs();
                    Util.copyFile(new File(ModLoc),new File(moddir));
                }
                ModpackInstaller.installedmods.put(version,true);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.error("Could not install version " + version + " from " + mod);
                e.printStackTrace();
            }


        }
    }
}
