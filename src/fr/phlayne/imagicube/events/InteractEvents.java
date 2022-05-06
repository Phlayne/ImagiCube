package fr.phlayne.imagicube.events;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Shulker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import fr.phlayne.imagicube.util.DamageStats;

public class InteractEvents implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDamage(EntityDamageEvent event) {
		event = DamageStats.changeStats(event);
	}

	@EventHandler
	public void onRightClick(PlayerInteractEntityEvent event) {
		if (event.getRightClicked() instanceof Shulker) {
			ItemStack handItem = event.getPlayer().getEquipment().getItem(event.getHand());
			Shulker shulker = (Shulker) event.getRightClicked();
			DyeColor color = getDyeColor(handItem.getType());
			if (color != null) {
				shulker.setColor(color);
				handItem.setAmount(handItem.getAmount() - 1);
			}
		}
	}

	public static DyeColor getDyeColor(Material material) {
		switch (material) {
		case WHITE_DYE:
			return DyeColor.WHITE;
		case ORANGE_DYE:
			return DyeColor.ORANGE;
		case MAGENTA_DYE:
			return DyeColor.MAGENTA;
		case LIGHT_BLUE_DYE:
			return DyeColor.LIGHT_BLUE;
		case YELLOW_DYE:
			return DyeColor.YELLOW;
		case LIME_DYE:
			return DyeColor.LIME;
		case PINK_DYE:
			return DyeColor.PINK;
		case GRAY_DYE:
			return DyeColor.GRAY;
		case LIGHT_GRAY_DYE:
			return DyeColor.LIGHT_GRAY;
		case CYAN_DYE:
			return DyeColor.CYAN;
		case PURPLE_DYE:
			return DyeColor.PURPLE;
		case BLUE_DYE:
			return DyeColor.BLUE;
		case BROWN_DYE:
			return DyeColor.BROWN;
		case GREEN_DYE:
			return DyeColor.GREEN;
		case RED_DYE:
			return DyeColor.RED;
		case BLACK_DYE:
			return DyeColor.BLACK;
		default:
			return null;
		}
	}
}
