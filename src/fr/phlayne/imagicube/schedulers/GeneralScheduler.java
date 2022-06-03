package fr.phlayne.imagicube.schedulers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.phlayne.imagicube.ImagiCube;

public class GeneralScheduler {

	public static void init() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(JavaPlugin.getPlugin(ImagiCube.class), new Runnable() {

			@Override
			public void run() {
				for(SchedulerScript schedulerScript : ImagiCube.getInstance().getAddonList().getSchedulerScripts()) {
					schedulerScript.tick();
				}
			}
		}, 0, 1L);
	}
}
