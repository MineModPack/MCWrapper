package me.noahp78.mm.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Web {
	//Editable settings
	private static String url = "https://mods.noahp78.me/";
	private static String ApiVersion = "v1/";
	private static String contentendpoint = "servers.php";
	private static String mod = "mods/";
	private static String pack = "packs/";
	
	
	private static String base = url + ApiVersion;
	private static String modurl = base + mod;
	private static String packurl = base+pack;
	
	
	public static void getResult(String urls) throws Exception{
		String result = "";
		URL oracle = new URL(urls);
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null){
            System.out.println(inputLine);
            result = result+inputLine;
        }
        in.close();
	}
	
	

	
	
}
