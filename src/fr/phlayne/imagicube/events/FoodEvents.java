package fr.phlayne.imagicube.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import fr.phlayne.imagicube.ImagiCube;
import fr.phlayne.imagicube.exception.CannotUpdateItemException;
import fr.phlayne.imagicube.item.FoodProperty;
import fr.phlayne.imagicube.item.ItemUpdatingCause;
import fr.phlayne.imagicube.util.ItemUpdater;

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

	@EventHandler
	public void onCraft(PrepareItemCraftEvent event) {
		if (event.getRecipe() != null && event.getRecipe().getResult() != null
				&& event.getRecipe().getResult().getType().isEdible()) {
			try {
				event.getInventory()
						.setResult(ItemUpdater.updateItem(event.getRecipe().getResult(), ItemUpdatingCause.CRAFT));
			} catch (CannotUpdateItemException e) {
				e.printStackTrace();
			}

		}

	}

	@EventHandler
	public void onSmelt(FurnaceSmeltEvent event) {
		if (!event.isCancelled() && event.getResult() != null && event.getResult().getType().isEdible()) {
			try {
				event.setResult(ItemUpdater.updateItem(event.getResult(), ItemUpdatingCause.CRAFT));
			} catch (CannotUpdateItemException e) {
				e.printStackTrace();
			}
		}
	}

}
