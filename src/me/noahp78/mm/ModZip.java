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
        Log.debug("Patching " + newpath + " into " + path);
        // write the modded zip with new Name
        ZipOutputStream moddedZip = new ZipOutputStream(new FileOutputStream(newpath.substring(0, (newpath.length()-4))+"-modded.jar"));

        // copy contents from original zip to the modded zip
        Enumeration<? extends ZipEntry> entries = originalZip.entries();
        int copied = 0;
        while (entries.hasMoreElements()) {
            ZipEntry e = entries.nextElement();

            String name = e.getName();
            if(newZip.getEntry(name) == null) {
                copied++;



                moddedZip.putNextEntry(e);
                if (!e.isDirectory()) {
                    copy(originalZip.getInputStream(e), moddedZip);
                }
                moddedZip.closeEntry();
                }else{
                    Log.info("Skipped " + name + " because it has to do with META-INF");
                }
            }

        // replace the original zip-files with new ones       

        Enumeration<? extends ZipEntry> newentries = newZip.entries();
        System.out.println(newentries);
        int added = 0;
        while (newentries.hasMoreElements())

    {
        ZipEntry e = newentries.nextElement();
        if (!e.getName().contains("META-INF")) {
            //System.out.println("append: " + e.getName());
            added++;
            moddedZip.putNextEntry(e);
            //System.out.println("putnextEntry done");
            if (!e.isDirectory()) {
                copy(newZip.getInputStream(e), moddedZip);
            }
            moddedZip.closeEntry();
        }
    }

        Log.info("Added " + added + " files to minecraft jar and copied " + copied + " files");

        // close
        originalZip.close();
        newZip.close();
        moddedZip.close();
        Log.info("Putting mod in jar is done!");

    }
}
