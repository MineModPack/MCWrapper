package me.noahp78.mm.tasks;

import me.noahp78.mm.Log;
import me.noahp78.mm.Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by noahp78 on 5-3-2015.
 *
 * Task to download files.
 */

public class DownloadTask {
    public static void DownloadMod(String url, String path) {
        File f = new File(path);
        if (!(f.exists())) {
            //TODO: Make some cool downloading code
            Log.info("Downloading " + url);
            try {
                DownloadFile(url, path);
            } catch (IOException e) {
                Log.error("There was a error downloading " + url);
                e.printStackTrace();
            }
        }else{
            Log.info("We alread have a local copy of " + url);

        }
    }

    private static void DownloadFile(String url, String fileloc) throws IOException {
        URL website = new URL(url);
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(fileloc);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }

    /** Generate the path to the local mod file.
     *
     * @param modname The Modname
     * @param versionid The version file name
     * @return
     */
    public static String makeModLoc(String modname, String versionid){
        String appdata = Util.getappdata();
        String s = File.separator;
        appdata = appdata + s + "ModManager" + s + "mods" + s + modname +s +  versionid + ".zip" ;
        return appdata;
    }
}
