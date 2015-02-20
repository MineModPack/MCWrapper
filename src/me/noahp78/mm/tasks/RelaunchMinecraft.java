package me.noahp78.mm.tasks;

import java.lang.management.ManagementFactory;
import java.util.List;

import me.noahp78.mm.Log;

public class RelaunchMinecraft {
	public static void doTask(){
		Log.debug("Relaunching Minecraft");
		List<String> launch = ManagementFactory.getRuntimeMXBean().getInputArguments();
		Log.debug("Found full command string: " + launch.toString());
		
		
	}
}
