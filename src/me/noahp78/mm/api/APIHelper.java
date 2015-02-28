package me.noahp78.mm.api;

import com.google.gson.Gson;

public class APIHelper {
	public static Mod getMod(String id) throws Exception{
		String json = Web.getResult("http://stream1-nas.cloudapp.net/mm/mod.php?api=1&id=" + id);
		Gson g = new Gson();
		Mod mod = g.fromJson(json, Mod.class);
		return mod;
	}
}
