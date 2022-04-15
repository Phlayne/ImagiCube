package fr.phlayne.imagicube.item;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import fr.phlayne.imagicube.data.Config;

public class WeaponProperties {

	private static List<WeaponProperty> weaponProperties = new ArrayList<WeaponProperty>();

	public static WeaponProperty OAK_PICKAXE = init(59, 2F, 1.2F, Tools.PICKAXE.getName(), "oak", Material.WOODEN_PICKAXE, 0);
	public static WeaponProperty SPRUCE_PICKAXE = init(59, 2F, 1.2F, Tools.PICKAXE.getName(), "spruce", Material.WOODEN_PICKAXE, 1);
	public static WeaponProperty BIRCH_PICKAXE = init(59, 2F, 1.2F, Tools.PICKAXE.getName(), "birch", Material.WOODEN_PICKAXE, 2);
	public static WeaponProperty JUNGLE_PICKAXE = init(59, 2F, 1.2F, Tools.PICKAXE.getName(), "jungle", Material.WOODEN_PICKAXE, 3);
	public static WeaponProperty ACACIA_PICKAXE = init(59, 2F, 1.2F, Tools.PICKAXE.getName(), "acacia", Material.WOODEN_PICKAXE, 4);
	public static WeaponProperty DARK_OAK_PICKAXE = init(59, 2F, 1.2F, Tools.PICKAXE.getName(), "dark_oak", Material.WOODEN_PICKAXE, 5);
	public static WeaponProperty CRIMSON_PICKAXE = init(59, 2F, 1.2F, Tools.PICKAXE.getName(), "crimson", Material.WOODEN_PICKAXE, 6);
	public static WeaponProperty WARPED_PICKAXE = init(59, 2F, 1.2F, Tools.PICKAXE.getName(), "warped", Material.WOODEN_PICKAXE, 7);
	public static WeaponProperty GOLDEN_PICKAXE = init(847, 4F, 1.2F, Tools.PICKAXE.getName(), "gold", Material.GOLDEN_PICKAXE, 0);
	public static WeaponProperty STONE_PICKAXE = init(131, 3F, 1.2F, Tools.PICKAXE.getName(), "stone", Material.STONE_PICKAXE, 0);
	public static WeaponProperty BLACKSTONE_PICKAXE = init(190, 3F, 1.2F, Tools.PICKAXE.getName(), "blackstone", Material.STONE_PICKAXE, 1);
	public static WeaponProperty IRON_PICKAXE = init(250, 4F, 1.2F, Tools.PICKAXE.getName(), "iron", Material.IRON_PICKAXE, 0);
	public static WeaponProperty DIAMOND_PICKAXE = init(1561, 5F, 1.2F, Tools.PICKAXE.getName(), "diamond", Material.DIAMOND_PICKAXE, 0);
	public static WeaponProperty NETHERITE_PICKAXE = init(2031, 6F, 1.2F, Tools.PICKAXE.getName(), "netherite", Material.NETHERITE_PICKAXE, 0);
	public static WeaponProperty PRISMARINE_PICKAXE = init(631, 4F, 1.2F, Tools.PICKAXE.getName(), "prismarine", Material.IRON_PICKAXE, 1);

	public static WeaponProperty OAK_SHOVEL = init(59, 2.5F, 1F, Tools.SHOVEL.getName(), "oak", Material.WOODEN_SHOVEL, 0);
	public static WeaponProperty SPRUCE_SHOVEL = init(59, 2.5F, 1F, Tools.SHOVEL.getName(), "spruce", Material.WOODEN_SHOVEL, 1);
	public static WeaponProperty BIRCH_SHOVEL = init(59, 2.5F, 1F, Tools.SHOVEL.getName(), "birch", Material.WOODEN_SHOVEL, 2);
	public static WeaponProperty JUNGLE_SHOVEL = init(59, 2.5F, 1F, Tools.SHOVEL.getName(), "jungle", Material.WOODEN_SHOVEL, 3);
	public static WeaponProperty ACACIA_SHOVEL = init(59, 2.5F, 1F, Tools.SHOVEL.getName(), "acacia", Material.WOODEN_SHOVEL, 4);
	public static WeaponProperty DARK_OAK_SHOVEL = init(59, 2.5F, 1F, Tools.SHOVEL.getName(), "dark_oak", Material.WOODEN_SHOVEL, 5);
	public static WeaponProperty CRIMSON_SHOVEL = init(59, 2.5F, 1F, Tools.SHOVEL.getName(), "crimson", Material.WOODEN_SHOVEL, 6);
	public static WeaponProperty WARPED_SHOVEL = init(59, 2.5F, 1F, Tools.SHOVEL.getName(), "warped", Material.WOODEN_SHOVEL, 7);
	public static WeaponProperty GOLDEN_SHOVEL = init(847, 4.5F, 1F, Tools.SHOVEL.getName(), "gold", Material.GOLDEN_SHOVEL, 0);
	public static WeaponProperty STONE_SHOVEL = init(131, 3.5F, 1F, Tools.SHOVEL.getName(), "stone", Material.STONE_SHOVEL, 0);
	public static WeaponProperty BLACKSTONE_SHOVEL = init(190, 3.5F, 1F, Tools.SHOVEL.getName(), "blackstone", Material.STONE_SHOVEL, 1);
	public static WeaponProperty IRON_SHOVEL = init(250, 4.5F, 1F, Tools.SHOVEL.getName(), "iron", Material.IRON_SHOVEL, 0);
	public static WeaponProperty DIAMOND_SHOVEL = init(1561, 5.5F, 1F, Tools.SHOVEL.getName(), "diamond", Material.DIAMOND_SHOVEL, 0);
	public static WeaponProperty NETHERITE_SHOVEL = init(2031, 6.5F, 1F, Tools.SHOVEL.getName(), "netherite", Material.NETHERITE_SHOVEL, 0);
	public static WeaponProperty PRISMARINE_SHOVEL = init(631, 4.5F, 1F, Tools.SHOVEL.getName(), "prismarine", Material.IRON_SHOVEL, 1);

	public static WeaponProperty OAK_HOE = init(59, 1F, 1.0F, Tools.HOE.getName(), "oak", Material.WOODEN_HOE, 0);
	public static WeaponProperty SPRUCE_HOE = init(59, 1F, 1.0F, Tools.HOE.getName(), "spruce", Material.WOODEN_HOE, 1);
	public static WeaponProperty BIRCH_HOE = init(59, 1F, 1.0F, Tools.HOE.getName(), "birch", Material.WOODEN_HOE, 2);
	public static WeaponProperty JUNGLE_HOE = init(59, 1F, 1.0F, Tools.HOE.getName(), "jungle", Material.WOODEN_HOE, 3);
	public static WeaponProperty ACACIA_HOE = init(59, 1F, 1.0F, Tools.HOE.getName(), "acacia", Material.WOODEN_HOE, 4);
	public static WeaponProperty DARK_OAK_HOE = init(59, 1F, 1.0F, Tools.HOE.getName(), "dark_oak", Material.WOODEN_HOE, 5);
	public static WeaponProperty CRIMSON_HOE = init(59, 1F, 1.0F, Tools.HOE.getName(), "crimson", Material.WOODEN_HOE, 6);
	public static WeaponProperty WARPED_HOE = init(59, 1F, 1.0F, Tools.HOE.getName(), "warped", Material.WOODEN_HOE, 7);
	public static WeaponProperty GOLDEN_HOE = init(847, 1F, 5.0F, Tools.HOE.getName(), "gold", Material.GOLDEN_HOE, 0);
	public static WeaponProperty STONE_HOE = init(131, 1F, 2.0F, Tools.HOE.getName(), "stone", Material.STONE_HOE, 0);
	public static WeaponProperty BLACKSTONE_HOE = init(190, 1F, 2.0F, Tools.HOE.getName(), "blackstone", Material.STONE_HOE, 1);
	public static WeaponProperty IRON_HOE = init(250, 1F, 3.0F, Tools.HOE.getName(), "iron", Material.IRON_HOE, 0);
	public static WeaponProperty DIAMOND_HOE = init(1561, 1F, 4.0F, Tools.HOE.getName(), "diamond", Material.DIAMOND_HOE, 0);
	public static WeaponProperty NETHERITE_HOE = init(2031, 1F, 4.0F, Tools.HOE.getName(), "netherite", Material.NETHERITE_HOE, 0);
	public static WeaponProperty PRISMARINE_HOE = init(631, 1F, 3.0F, Tools.HOE.getName(), "prismarine", Material.IRON_HOE, 1);

	public static WeaponProperty OAK_SWORD = init(59, 4F, 1.6F, Tools.SWORD.getName(), "oak", Material.WOODEN_SWORD, 0);
	public static WeaponProperty SPRUCE_SWORD = init(59, 4F, 1.6F, Tools.SWORD.getName(), "spruce", Material.WOODEN_SWORD, 1);
	public static WeaponProperty BIRCH_SWORD = init(59, 4F, 1.6F, Tools.SWORD.getName(), "birch", Material.WOODEN_SWORD, 2);
	public static WeaponProperty JUNGLE_SWORD = init(59, 4F, 1.6F, Tools.SWORD.getName(), "jungle", Material.WOODEN_SWORD, 3);
	public static WeaponProperty ACACIA_SWORD = init(59, 4F, 1.6F, Tools.SWORD.getName(), "acacia", Material.WOODEN_SWORD, 4);
	public static WeaponProperty DARK_OAK_SWORD = init(59, 4F, 1.6F, Tools.SWORD.getName(), "dark_oak", Material.WOODEN_SWORD, 5);
	public static WeaponProperty CRIMSON_SWORD = init(59, 4F, 1.6F, Tools.SWORD.getName(), "crimson", Material.WOODEN_SWORD, 6);
	public static WeaponProperty WARPED_SWORD = init(59, 4F, 1.6F, Tools.SWORD.getName(), "warped", Material.WOODEN_SWORD, 7);
	public static WeaponProperty GOLDEN_SWORD = init(847, 6F, 1.6F, Tools.SWORD.getName(), "gold", Material.GOLDEN_SWORD, 0);
	public static WeaponProperty STONE_SWORD = init(131, 5F, 1.6F, Tools.SWORD.getName(), "stone", Material.STONE_SWORD, 0);
	public static WeaponProperty BLACKSTONE_SWORD = init(190, 5F, 1.6F, Tools.SWORD.getName(), "blackstone", Material.STONE_SWORD, 1);
	public static WeaponProperty IRON_SWORD = init(250, 6F, 1.6F, Tools.SWORD.getName(), "iron", Material.IRON_SWORD, 0);
	public static WeaponProperty DIAMOND_SWORD = init(1561, 7F, 1.6F, Tools.SWORD.getName(), "diamond", Material.DIAMOND_SWORD, 0);
	public static WeaponProperty NETHERITE_SWORD = init(2031, 8F, 1.6F, Tools.SWORD.getName(), "netherite", Material.NETHERITE_SWORD, 0);
	public static WeaponProperty PRISMARINE_SWORD = init(631, 6F, 1.6F, Tools.SWORD.getName(), "prismarine", Material.IRON_SWORD, 1);

	public static WeaponProperty OAK_AXE = init(59, 6.5F, 0.8F, Tools.AXE.getName(), "oak", Material.WOODEN_AXE, 0);
	public static WeaponProperty SPRUCE_AXE = init(59, 6.5F, 0.8F, Tools.AXE.getName(), "spruce", Material.WOODEN_AXE, 1);
	public static WeaponProperty BIRCH_AXE = init(59, 6.5F, 0.8F, Tools.AXE.getName(), "birch", Material.WOODEN_AXE, 2);
	public static WeaponProperty JUNGLE_AXE = init(59, 6.5F, 0.8F, Tools.AXE.getName(), "jungle", Material.WOODEN_AXE, 3);
	public static WeaponProperty ACACIA_AXE = init(59, 6.5F, 0.8F, Tools.AXE.getName(), "acacia", Material.WOODEN_AXE, 4);
	public static WeaponProperty DARK_OAK_AXE = init(59, 6.5F, 0.8F, Tools.AXE.getName(), "dark_oak", Material.WOODEN_AXE, 5);
	public static WeaponProperty CRIMSON_AXE = init(59, 6.5F, 0.8F, Tools.AXE.getName(), "crimson", Material.WOODEN_AXE, 6);
	public static WeaponProperty WARPED_AXE = init(59, 6.5F, 0.8F, Tools.AXE.getName(), "warped", Material.WOODEN_AXE, 7);
	public static WeaponProperty GOLDEN_AXE = init(847, 9.5F, 0.9F, Tools.AXE.getName(), "gold", Material.GOLDEN_AXE, 0);
	public static WeaponProperty STONE_AXE = init(131, 8.0F, 0.8F, Tools.AXE.getName(), "stone", Material.STONE_AXE, 0);
	public static WeaponProperty BLACKSTONE_AXE = init(190, 8.0F, 0.8F, Tools.AXE.getName(), "blackstone", Material.STONE_AXE, 1);
	public static WeaponProperty IRON_AXE = init(250, 9.5F, 0.9F, Tools.AXE.getName(), "iron", Material.IRON_AXE, 0);
	public static WeaponProperty DIAMOND_AXE = init(1561, 11F, 1F, Tools.AXE.getName(), "diamond", Material.DIAMOND_AXE, 0);
	public static WeaponProperty NETHERITE_AXE = init(2031, 12F, 1F, Tools.AXE.getName(), "netherite", Material.NETHERITE_AXE, 0);
	public static WeaponProperty PRISMARINE_AXE = init(631, 9.5F, 0.9F, Tools.AXE.getName(), "prismarine", Material.IRON_AXE, 1);

	public static WeaponProperty TRIDENT = init(631, 9F, 1.1F, "trident", "prismarine", Material.TRIDENT, 0);
	
	public static WeaponProperty init(int durability, float damage, float attackSpeed, String type, String material,
			Material bukkitMaterial, int customModelData) {
		String path = "imagicube.tools." + material;
		for (FileConfiguration config : Config.getConfigs("durability")) {
			if (config.contains(path + "." + type))
				durability = config.getInt(path + "." + type);
			else if (config.contains(path))
				durability = config.getInt(path);
		}
		WeaponProperty weaponProperty = new WeaponProperty(durability, damage, attackSpeed, type, material,
				bukkitMaterial, customModelData);
		weaponProperties.add(weaponProperty);
		return weaponProperty;
	}

	public static List<WeaponProperty> getWeaponProperties() {
		return weaponProperties;
	}
}
