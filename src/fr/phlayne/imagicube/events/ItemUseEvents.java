package fr.phlayne.imagicube.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import fr.phlayne.imagicube.ImagiCube;
import fr.phlayne.imagicube.data.Config;

public class ItemUseEvents implements Listener {

	@EventHandler
	public void onEnderPearlUse(ProjectileLaunchEvent event) {
		if (event.getEntity() instanceof EnderPearl && event.getEntity().getShooter() instanceof Player) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(ImagiCube.getInstance(), new Runnable() {
				@Override
				public void run() {
					((Player) event.getEntity().getShooter()).setCooldown(Material.ENDER_PEARL,
							Config.getConfig().getInt("ender_pearl_cooldown"));
				}
			});
		}
	}
}
