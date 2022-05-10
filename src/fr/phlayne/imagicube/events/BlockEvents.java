package fr.phlayne.imagicube.events;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.ImagiCube;
import fr.phlayne.imagicube.data.Config;
import fr.phlayne.imagicube.item.WeaponProperty;
import fr.phlayne.imagicube.util.NBTUtil;
import fr.phlayne.imagicube.util.WorldProtection;

public class BlockEvents implements Listener {

	public static final List<Material> concrete = Arrays.asList(Material.WHITE_CONCRETE_POWDER,
			Material.ORANGE_CONCRETE_POWDER, Material.MAGENTA_CONCRETE_POWDER, Material.LIGHT_BLUE_CONCRETE_POWDER,
			Material.YELLOW_CONCRETE_POWDER, Material.LIME_CONCRETE_POWDER, Material.PINK_CONCRETE_POWDER,
			Material.GRAY_CONCRETE_POWDER, Material.LIGHT_GRAY_CONCRETE_POWDER, Material.CYAN_CONCRETE_POWDER,
			Material.PURPLE_CONCRETE_POWDER, Material.BLUE_CONCRETE_POWDER, Material.BROWN_CONCRETE_POWDER,
			Material.GREEN_CONCRETE_POWDER, Material.RED_CONCRETE_POWDER, Material.BLACK_CONCRETE_POWDER,
			Material.WHITE_CONCRETE, Material.ORANGE_CONCRETE, Material.MAGENTA_CONCRETE, Material.LIGHT_BLUE_CONCRETE,
			Material.YELLOW_CONCRETE, Material.LIME_CONCRETE, Material.PINK_CONCRETE, Material.GRAY_CONCRETE,
			Material.LIGHT_GRAY_CONCRETE, Material.CYAN_CONCRETE, Material.PURPLE_CONCRETE, Material.BLUE_CONCRETE,
			Material.BROWN_CONCRETE, Material.GREEN_CONCRETE, Material.RED_CONCRETE, Material.BLACK_CONCRETE);

	@EventHandler
	public void blockProtect(BlockBreakEvent event) {
		event.setCancelled(!WorldProtection.canBreakBlock(event.getPlayer(), event.getBlock()));
	}

	@EventHandler
	public void blockProtect(BlockIgniteEvent event) {
		if (!WorldProtection.canBreakBlock(event.getPlayer(), event.getBlock())
				|| !WorldProtection.canBreakBlock(event.getPlayer(), event.getBlock()))
			if (event.getIgnitingEntity().getType().equals(EntityType.PLAYER)) {
				Player player = (Player) event.getIgnitingEntity();
				if (player.isOp() && player.getGameMode().equals(GameMode.CREATIVE))
					event.setCancelled(false);
				else
					event.setCancelled(true);
			} else
				event.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	// We need to know if the event is cancelled or not so it's better to be fired
	// at the last moment, where all plugins have interacted with the event.
	public void onBlockBreak(BlockBreakEvent event) {
		if (!event.isCancelled()) {
			if (Arrays
					.asList(Material.DIAMOND_ORE, Material.EMERALD_ORE, Material.LAPIS_ORE, Material.REDSTONE_ORE,
							Material.DEEPSLATE_DIAMOND_ORE, Material.DEEPSLATE_EMERALD_ORE,
							Material.DEEPSLATE_LAPIS_ORE, Material.DEEPSLATE_REDSTONE_ORE)
					.contains(event.getBlock().getType())) {
				if (event.getPlayer().getEquipment().getItemInMainHand() != null) {
					ItemStack pickaxe = event.getPlayer().getEquipment().getItemInMainHand();
					if (pickaxe.getType().equals(Material.GOLDEN_PICKAXE) && event.isDropItems()) {
						Material resultMaterial = null;
						int minExpAmount = 0;
						int maxExpAmount = 0;
						switch (event.getBlock().getType()) {
						case DIAMOND_ORE:
						case DEEPSLATE_DIAMOND_ORE:
							resultMaterial = Material.DIAMOND;
							minExpAmount = 3;
							maxExpAmount = 7;
							break;
						case EMERALD_ORE:
						case DEEPSLATE_EMERALD_ORE:
							resultMaterial = Material.EMERALD;
							break;
						case REDSTONE_ORE:
						case DEEPSLATE_REDSTONE_ORE:
							resultMaterial = Material.REDSTONE;
							minExpAmount = 1;
							maxExpAmount = 5;
							break;
						case LAPIS_ORE:
						case DEEPSLATE_LAPIS_ORE:
							resultMaterial = Material.LAPIS_LAZULI;
							minExpAmount = 2;
							maxExpAmount = 5;
							break;
						default:
							break;
						}
						boolean silkTouch = false;
						boolean drop = false;
						Random random = new Random();
						int bonusLoot = 1;
						if (event.getPlayer() != null)
							if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
								event.setDropItems(false);
						if (event.getPlayer().getEquipment().getItemInMainHand() != null) {
							WeaponProperty wp = WeaponProperty.getWeaponProperty(new NBTItem(pickaxe));
							drop = wp != null && wp.getType().equals("pickaxe");
							int level = pickaxe.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
							silkTouch = pickaxe.getEnchantmentLevel(Enchantment.SILK_TOUCH) > 0;
							if (random.nextFloat() < ((float) level) / ((float) level + 2))
								bonusLoot = (int) (random.nextFloat() * (level - 1) + 1);
						}
						int amount = resultMaterial.equals(Material.LAPIS_LAZULI) ? random.nextInt(6) + 4
								: resultMaterial.equals(Material.REDSTONE) ? random.nextInt(2) + 4 : 1;
						amount += bonusLoot;
						ItemStack item = new ItemStack(resultMaterial, amount);
						if (event.isDropItems() && drop && !silkTouch) {
							event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(),
									silkTouch ? new ItemStack(event.getBlock().getType()) : item);
							((ExperienceOrb) event.getBlock().getWorld()
									.spawn(event.getBlock().getLocation().add(0.5D, 0.5D, 0.5D), ExperienceOrb.class))
											.setExperience(random.nextInt(maxExpAmount - minExpAmount) + minExpAmount);
						}
						event.setDropItems(false);
						event.getPlayer().incrementStatistic(Statistic.MINE_BLOCK, event.getBlock().getType());
					}
				}
			} else if (!event.isCancelled() && event.getBlock().getType().equals(Material.NETHER_QUARTZ_ORE)) {
				if (event.isDropItems()) {
					boolean silkTouch = false;
					boolean drop = false;
					Random random = new Random();
					int bonusLoot = 1;
					if (event.getPlayer() != null)
						if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
							event.setDropItems(false);
					if (event.getPlayer().getEquipment().getItemInMainHand() != null) {
						ItemStack pickaxe = event.getPlayer().getEquipment().getItemInMainHand();
						WeaponProperty wp = WeaponProperty.getWeaponProperty(new NBTItem(pickaxe));
						drop = wp != null && wp.getType().equals("pickaxe");
						int level = pickaxe.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
						silkTouch = pickaxe.getEnchantmentLevel(Enchantment.SILK_TOUCH) > 0;
						if (random.nextFloat() < ((float) level) / ((float) level + 2))
							bonusLoot = (int) (random.nextFloat() * (level - 1) + 1);
					}
					int amount = Config.getConfig().getBoolean("boost_quartz_drop") ? random.nextInt(4) + 4 : 1;
					// 4-7 quartz dropped
					amount += bonusLoot;
					ItemStack item = new ItemStack(Material.QUARTZ, amount);
					if (event.isDropItems() && drop)
						event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(),
								silkTouch ? new ItemStack(Material.NETHER_QUARTZ_ORE) : item);
					event.setDropItems(false);
					event.getPlayer().incrementStatistic(Statistic.MINE_BLOCK, Material.NETHER_QUARTZ_ORE);
				}
			} else if (Arrays
					.asList(Material.IRON_ORE, Material.DEEPSLATE_IRON_ORE, Material.GOLD_ORE,
							Material.DEEPSLATE_GOLD_ORE, Material.COPPER_ORE, Material.DEEPSLATE_COPPER_ORE)
					.contains(event.getBlock().getType())) {
				boolean silkTouch = false;
				boolean fireAspect = false;
				boolean drop = false;
				Random random = new Random();
				int bonusLoot = 0;
				if (event.getPlayer() != null)
					if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
						event.setDropItems(false);
				if (event.getPlayer().getEquipment().getItemInMainHand() != null) {
					ItemStack pickaxe = event.getPlayer().getEquipment().getItemInMainHand();
					WeaponProperty wp = WeaponProperty.getWeaponProperty(new NBTItem(pickaxe));
					drop = wp != null && wp.getType().equals("pickaxe");
					int level = pickaxe.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
					silkTouch = pickaxe.getEnchantmentLevel(Enchantment.SILK_TOUCH) > 0;
					fireAspect = pickaxe.getEnchantmentLevel(Enchantment.FIRE_ASPECT) > 0
							&& Config.getConfig().getBoolean("fire_aspect_pickaxes_drop_molten_material");
					// TODO Set a config value: chance_of_dropping_molten_ore_by_fire_aspect_level:
					// 0.33333333333
					if (random.nextFloat() < ((float) level) / ((float) level + 2))
						bonusLoot = (int) (random.nextFloat() * (level - 1) + 1);
				}
				int amount = Arrays.asList(Material.COPPER_ORE, Material.DEEPSLATE_COPPER_ORE)
						.contains(event.getBlock().getType()) ? random.nextInt(4) + 2 : 1;
				amount += bonusLoot;
				Material[] materials = { Material.RAW_IRON, Material.IRON_INGOT };
				int[] xpValues = { 2, 1 };
				switch (event.getBlock().getType()) {
				case GOLD_ORE:
				case DEEPSLATE_GOLD_ORE:
					materials = new Material[] { Material.RAW_GOLD, Material.GOLD_INGOT };
					xpValues = new int[] { 4, 3 };
					break;
				case COPPER_ORE:
				case DEEPSLATE_COPPER_ORE:
					materials = new Material[] { Material.RAW_COPPER, Material.COPPER_INGOT };
					xpValues = new int[] { 3, 2 };
					break;
				default:
					break;
				}
				if (event.isDropItems() && drop)
					event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(),
							(silkTouch || !fireAspect) ? new ItemStack(materials[0])
									: new ItemStack(materials[1], amount));
				if (fireAspect && !silkTouch && Config.getConfig().getBoolean("fire_aspect_pickaxe_drops_exp"))
					((ExperienceOrb) event.getBlock().getWorld()
							.spawn(event.getBlock().getLocation().add(0.5D, 0.5D, 0.5D), ExperienceOrb.class))
									.setExperience(random.nextInt(xpValues[0]) + xpValues[1]);
				event.setDropItems(false);
				event.getPlayer().incrementStatistic(Statistic.MINE_BLOCK, event.getBlock().getType());
			}
		}
	}

	@EventHandler
	public void onFarmlandJump(PlayerInteractEvent event) {
		if (event.getAction().equals(Action.PHYSICAL)
				&& Config.getConfig().getBoolean("farmland_not_breaking_on_jump")) {
			Block block = event.getClickedBlock();
			if (block == null)
				return;
			if (block.getType().equals(Material.FARMLAND)) {
				event.setUseInteractedBlock(org.bukkit.event.Event.Result.DENY);
				event.setCancelled(true);
			}
			return;
		}
	}

	@EventHandler
	public void cancelConcreteWater(BlockFormEvent event) {
		if (!Config.getConfig().getBoolean("concrete_powder_interacts_with_water")) {
			if (concrete.contains(event.getBlock().getType())
					|| (event.getBlock().getType().equals(Material.WATER)
							&& concrete.contains(event.getNewState().getType()))
					|| (event.getBlock().getType().equals(Material.AIR)
							&& concrete.contains(event.getNewState().getType()))) {
				event.setCancelled(true);
				event.getBlock().getState().update();
			}
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if (event.getBlockPlaced().getType().equals(Material.FARMLAND)) {
			ItemStack mainHandItem = event.getPlayer().getEquipment().getItemInMainHand();
			if (mainHandItem != null && !mainHandItem.getType().equals(Material.FARMLAND)) {
				ItemStack offHandItem = event.getPlayer().getEquipment().getItemInOffHand();
				String mainHandItemType = null;
				String offHandItemType = null;
				if (mainHandItem != null && !mainHandItem.getType().equals(Material.AIR))
					if (new NBTItem(mainHandItem).hasKey(NBTUtil.ITEM_TYPE))
						mainHandItemType = new NBTItem(mainHandItem).getString(NBTUtil.ITEM_TYPE);
				if (offHandItem != null && !offHandItem.getType().equals(Material.AIR))
					if (new NBTItem(offHandItem).hasKey(NBTUtil.ITEM_TYPE))
						offHandItemType = new NBTItem(offHandItem).getString(NBTUtil.ITEM_TYPE);
				boolean cancelled = true;
				List<String> hoeGroup = ImagiCube.getInstance().addonList.itemGroups.get("hoe");
				if (mainHandItemType != null && hoeGroup.contains(mainHandItemType))
					cancelled = false;
				// If the player has a scythe in main hand and a hoe in off hand, vanilla
				// behavior would make the plow work and take the durability of the hoe.
				// We cancel it here to make the code simpler, but that's an inconsistency
				else if (!Arrays.asList(Material.DIAMOND_HOE, Material.GOLDEN_HOE, Material.IRON_HOE,
						Material.NETHERITE_HOE, Material.STONE_HOE, Material.WOODEN_HOE)
						.contains(mainHandItem.getType()) && offHandItemType != null
						&& hoeGroup.contains(offHandItemType))
					cancelled = false;
				event.setCancelled(cancelled);
			}
		}
	}
}