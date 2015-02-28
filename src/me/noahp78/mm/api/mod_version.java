package me.noahp78.mm.api;

import java.util.List;

public class mod_version {
	//Yes. I'm using strings for ID's because it's easier and extensible incase we
	//ever run out of numbers... (2^32 mods or versions :P)
	
	public String name;
	public String ID;
	public String desc;
	public List depends;
	
}
