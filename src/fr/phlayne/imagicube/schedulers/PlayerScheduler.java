package fr.phlayne.imagicube.schedulers;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.ImagiCube;
import fr.phlayne.imagicube.data.Config;
import fr.phlayne.imagicube.item.Durability;
import fr.phlayne.imagicube.item.ItemWeight;
import fr.phlayne.imagicube.util.CustomMetadata;
import fr.phlayne.imagicube.util.NBTUtil;

public class PlayerScheduler extends SchedulerScript {

	public static long tick = 0;

	public void tick() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			for (CustomMetadata cm : CustomMetadata.customMetadataList) {
				if (cm.increase)
					cm.add(player, 1);
				else
					cm.add(player, -1);
			}

			// Player invulnerability system;

			player.setInvulnerable(!ImagiCube.getInstance().getResourcePackUtil().resourcePackLoaded.get(player));

			// Player AFK System

			// PlayerData.playerAFK.put(player,
			// CustomMetadata.get("lastAction").getValue(player) > (60 * 20));

			/* Player modifier speed */

			if (Config.getConfig().getBoolean("weight_modifier")) {
				float modifier = (float) (ItemWeight.getWeight(player.getEquipment().getBoots())
						+ ItemWeight.getWeight(player.getEquipment().getLeggings())
						+ ItemWeight.getWeight(player.getEquipment().getChestplate())
						+ ItemWeight.getWeight(player.getEquipment().getHelmet())
						+ ItemWeight.getWeight(player.getEquipment().getItemInMainHand())
						+ ItemWeight.getWeight(player.getEquipment().getItemInOffHand()));
				float value = Math.max(0.05F, 0.25F - modifier / 1000);
				if (player.getWalkSpeed() != value)
					player.setWalkSpeed(value);
				if (player.getGameMode() == GameMode.CREATIVE
						&& !Config.getConfig().getBoolean("weight_modifier_in_creative")) {
					player.setWalkSpeed(0.25F);
				}
				double glidingModifier = Config.getConfig().getDouble("weight_gliding_modifier");
				if (player.isGliding() && glidingModifier != 0.0D) {
					player.setVelocity(player.getVelocity().add(new Vector(0, -modifier * glidingModifier, 0)));
				}
			}

			if (!player.getGameMode().equals(GameMode.SPECTATOR))
				player.setFlySpeed(0.15F);
			// Set 0 so if the player is in survival or adventure, so when they lags while
			// double jumping and can fly, they doesn't move (and put this in ImagiCubeHats)

			if (player.getEquipment().getChestplate() != null) {
				NBTItem nbti = new NBTItem(player.getInventory().getChestplate());
				if (nbti.hasKey(NBTUtil.DURABILITY)
						&& nbti.getInteger(NBTUtil.DURABILITY) >= Durability.getMaxDurability("elytra")) {
					player.setGliding(false);
				}
			}
			if (tick % 20 == 0) {
				if (player.isGliding() && (player.getGameMode().equals(GameMode.SURVIVAL)
						|| player.getGameMode().equals(GameMode.ADVENTURE))) {
					if (player.getInventory().getChestplate() != null) {
						NBTItem nbti = new NBTItem(player.getInventory().getChestplate());
						if ((nbti.hasKey(NBTUtil.DURABILITY) ? nbti.getInteger(NBTUtil.DURABILITY)
								: Integer.MAX_VALUE) < Durability.getMaxDurability("elytra")) {
							Durability.applyDurability(nbti);
							player.getInventory().setChestplate(nbti.getItem());
						}
					}
				}
			}

			for (EquipmentSlot hand : Arrays.asList(EquipmentSlot.HAND, EquipmentSlot.OFF_HAND)) {
				ItemStack item = player.getEquipment().getItem(hand);
				if (item != null && !item.getType().equals(Material.AIR)) {
					NBTItem nbti = new NBTItem(item);
					if (nbti.hasKey(NBTUtil.ITEM_COOLDOWN) && nbti.getInteger(NBTUtil.ITEM_COOLDOWN) > 0) {
						nbti.setInteger(NBTUtil.ITEM_COOLDOWN, nbti.getInteger(NBTUtil.ITEM_COOLDOWN) - 1);
						player.getEquipment().setItem(hand, nbti.getItem());
					}
				}
			}
		}
		tick++;
	}
}
