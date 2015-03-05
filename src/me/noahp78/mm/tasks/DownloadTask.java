package me.noahp78.mm.tasks;

import me.noahp78.mm.Log;
import me.noahp78.mm.WinUtil;

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
    public static void DownloadMod(String url, String path){
        //TODO: Make some cool downloading code
        Log.info("Downloading " + url);
        try {
            DownloadFile(url, path);
        } catch (IOException e) {
            Log.error("There was a error downloading " + url);
            e.printStackTrace();
        }
    }

    private static void DownloadFile(String url, String fileloc) throws IOException {
        URL website = new URL("http://www.website.com/information.asp");
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
        String appdata = WinUtil.getappdata();
        String s = File.separator;
        appdata = appdata + s + "ModManager" + s + "mods" + s + modname +s +  versionid ;
        return appdata;
    }
}
