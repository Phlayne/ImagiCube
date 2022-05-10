package fr.phlayne.imagicube.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import fr.phlayne.imagicube.ImagiCube;
import fr.phlayne.imagicube.item.FoodProperty;

public class FoodEvents implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEat(PlayerItemConsumeEvent event) {
		FoodProperty fp = FoodProperty.getFoodProperty(event.getItem());
		if (fp != null) {
			final float saturation = event.getPlayer().getSaturation();
			final int foodLevel = event.getPlayer().getFoodLevel();
			Bukkit.getScheduler().scheduleSyncDelayedTask(ImagiCube.getInstance(), new Runnable() {
				@Override
				public void run() {
					event.getPlayer().setSaturation(Math.min(20, saturation + fp.getSaturation()));
					event.getPlayer().setFoodLevel(Math.min(20, foodLevel + fp.getFoodLevel()));
				}
			}, 0L);
		}
	}

}
