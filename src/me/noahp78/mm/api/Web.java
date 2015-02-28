package me.noahp78.mm.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import me.noahp78.mm.Log;

public class Web {
	//Editable settings
	private static String url = "https://mods.noahp78.me/";
	private static String ApiVersion = "v1/";
	private static String contentendpoint = "servers.php";
	private static String mod = "mods/";
	private static String pack = "packs/";
	
	
	public static String base = url + ApiVersion;
	public static String modurl = base + mod;
	public static String packurl = base+pack;
	
	
	public static String getResult(String urls) throws Exception{
		String result = "";
		URL oracle = new URL(urls);
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null){
            result = result+inputLine;
        }
        Log.debug("[webResult] " + result);
        in.close();
        return result;
	}
	
	

	
	
}
