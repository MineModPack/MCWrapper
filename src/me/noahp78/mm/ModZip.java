package me.noahp78.mm;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ModZip {   
    // 4MB buffer
    private static final byte[] BUFFER = new byte[4096 * 1024];

    // copy input to output stream   
    public static void copy(InputStream input, OutputStream output) throws IOException {
        int bytesRead;
        while ((bytesRead = input.read(BUFFER))!= -1) {
            output.write(BUFFER, 0, bytesRead);
        }
    }
/** Merge 2 zip files together
 * 
 * @param path Minecraft Jar + path
 * @param newpath Mod jar/zip + path
 * @throws Exception
 */
    public static void patch(String path, String newpath) throws Exception {
        // read the zips
        ZipFile originalZip = new ZipFile(path);
        ZipFile newZip = new ZipFile(newpath);


        // write the modded zip with new Name
        ZipOutputStream moddedZip = new ZipOutputStream(new FileOutputStream(path.substring(0, (path.length()-4))+"-modded.jar"));

        // copy contents from original zip to the modded zip
        Enumeration<? extends ZipEntry> entries = originalZip.entries();
        while (entries.hasMoreElements()) {
            ZipEntry e = entries.nextElement();

            String name = e.getName();
            if(newZip.getEntry(name) == null) {
                Log.debug("Copying " +  e.getName());
                moddedZip.putNextEntry(e);
                if (!e.isDirectory()) {
                    copy(originalZip.getInputStream(e), moddedZip);
                }
                moddedZip.closeEntry();
            }
        }

        // replace the original zip-files with new ones       

        Enumeration<? extends ZipEntry> newentries = newZip.entries();
        System.out.println(newentries);
        while (newentries.hasMoreElements()) {
            ZipEntry e = newentries.nextElement();
            //System.out.println("append: " + e.getName());
            Log.debug("Adding " + e.getName());
            moddedZip.putNextEntry(e);
            //System.out.println("putnextEntry done");
            if (!e.isDirectory()) {
                copy(newZip.getInputStream(e), moddedZip);
            }
            moddedZip.closeEntry();
        }

        System.out.println("appending done ");

        // close
        originalZip.close();
        newZip.close();
        moddedZip.close();
        Log.info("Putting mod in jar is done!");

    }
}
