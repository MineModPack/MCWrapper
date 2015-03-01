package me.noahp78.mm.api;

import me.noahp78.mm.Log;

import com.google.gson.Gson;

public class APIHelper {
	public static Mod getMod(String id) throws Exception{
		String json = Web.getResult("http://stream1-nas.cloudapp.net/mm/mod.php?api=1&id=" + id);
		Gson g = new Gson();
		Mod mod = g.fromJson(json, Mod.class);
		return mod;
	}
	public static Modpack getModPack(String id)throws Exception{
		String json = Web.getResult("http://stream1-nas.cloudapp.net/mm/modpack.php?api=1&id=" + id);
		Gson g = new Gson();
		Modpack mod = g.fromJson(json, Modpack.class);
		return mod;
		
		
	}
	public static ModVersion getVersion(String id)throws Exception{
		Log.debug("Requesting Version " + id);
		String json = Web.getResult("http://stream1-nas.cloudapp.net/mm/versions.php?api=1&id=" + id);
		Gson g = new Gson();
		ModVersion mod = g.fromJson(json, ModVersion.class);
		return mod;
		
	}
}
