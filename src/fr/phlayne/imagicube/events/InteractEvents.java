package fr.phlayne.imagicube.events;

import java.util.Random;

import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Shulker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import fr.phlayne.imagicube.util.DamageStats;

public class InteractEvents implements Listener {

	public Random random = new Random();

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDamage(EntityDamageEvent event) {
		event = DamageStats.changeStats(event);
	}

	@EventHandler
	public void rightClick(PlayerInteractEvent event) {
		if (event.getHand() == EquipmentSlot.OFF_HAND)
			return;
		// Store experience into experience bottle.
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)
				&& event.getClickedBlock().getType().equals(Material.ENCHANTING_TABLE)) {
			Player player = event.getPlayer();
			ItemStack handItem = player.getEquipment().getItemInMainHand();
			if (player.getLevel() >= 1 && handItem.getType().equals(Material.GLASS_BOTTLE)) {
				Item item = player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.EXPERIENCE_BOTTLE));
				item.setVelocity(new Vector(0, 0, 0));
				item.setPickupDelay(0);
				player.giveExp(-7);
				if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
					handItem.setAmount(handItem.getAmount() - 1);
					player.getEquipment().setItemInMainHand(handItem);
				}
				player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F,
						(1.25F + random.nextFloat() * 0.15F));
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void rightClickOnEntity(PlayerInteractEntityEvent event) {
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
