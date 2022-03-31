package fr.phlayne.imagicube.item;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

import fr.phlayne.imagicube.data.Config;

public class ArmorProperties {

	private static List<ArmorProperty> armorProperties = new ArrayList<ArmorProperty>();

	public static ArmorProperty LEATHER_BOOTS = init(66, 1, 5, 4, 0, "boots", "leather", Material.LEATHER_BOOTS); //
	public static ArmorProperty LEATHER_LEGGINGS = init(76, 2, 8, 7, 0, "leggings", "leather",
			Material.LEATHER_LEGGINGS); //
	public static ArmorProperty LEATHER_CHESTPLATE = init(81, 4, 10, 9, 0, "chestplate", "leather",
			Material.LEATHER_CHESTPLATE); //
	public static ArmorProperty LEATHER_HELMET = init(56, 1, 5, 5, 0, "helmet", "leather", Material.LEATHER_HELMET); //

	public static ArmorProperty CHAINMAIL_BOOTS = init(196, 2, 6, 12, 1, "boots", "chainmail",
			Material.CHAINMAIL_BOOTS); //
	public static ArmorProperty CHAINMAIL_LEGGINGS = init(226, 3, 9, 19, 1, "leggings", "chainmail",
			Material.CHAINMAIL_LEGGINGS); //
	public static ArmorProperty CHAINMAIL_CHESTPLATE = init(241, 5, 11, 29, 1, "chestplate", "chainmail",
			Material.CHAINMAIL_CHESTPLATE); //
	public static ArmorProperty CHAINMAIL_HELMET = init(166, 2, 6, 15, 1, "helmet", "chainmail",
			Material.CHAINMAIL_HELMET); //

	public static ArmorProperty GOLDEN_BOOTS = init(92, 3, 3, 4, 0, "boots", "gold", Material.GOLDEN_BOOTS); //
	public static ArmorProperty GOLDEN_LEGGINGS = init(106, 5, 5, 7, 0, "leggings", "gold", Material.GOLDEN_LEGGINGS); //
	public static ArmorProperty GOLDEN_CHESTPLATE = init(113, 7, 7, 9, 0, "chestplate", "gold",
			Material.GOLDEN_CHESTPLATE); //
	public static ArmorProperty GOLDEN_HELMET = init(78, 3, 3, 5, 0, "helmet", "gold", Material.GOLDEN_HELMET); //

	public static ArmorProperty IRON_BOOTS = init(196, 4, 4, 12, 1, "boots", "iron", Material.IRON_BOOTS); //
	public static ArmorProperty IRON_LEGGINGS = init(226, 6, 6, 19, 1, "leggings", "iron", Material.IRON_LEGGINGS); //
	public static ArmorProperty IRON_CHESTPLATE = init(241, 8, 8, 29, 1, "chestplate", "iron",
			Material.IRON_CHESTPLATE); //
	public static ArmorProperty IRON_HELMET = init(166, 4, 4, 15, 1, "helmet", "iron", Material.IRON_HELMET); //

	public static ArmorProperty DIAMOND_BOOTS = init(430, 5, 1, 4, 0, "boots", "diamond", Material.DIAMOND_BOOTS); //
	public static ArmorProperty DIAMOND_LEGGINGS = init(496, 8, 2, 7, 0, "leggings", "diamond",
			Material.DIAMOND_LEGGINGS); //
	public static ArmorProperty DIAMOND_CHESTPLATE = init(529, 10, 4, 9, 0, "chestplate", "diamond",
			Material.DIAMOND_CHESTPLATE); //
	public static ArmorProperty DIAMOND_HELMET = init(364, 5, 1, 5, 0, "helmet", "diamond", Material.DIAMOND_HELMET); //

	public static ArmorProperty NETHERITE_BOOTS = init(482, 6, 2, 12, 1, "boots", "netherite",
			Material.NETHERITE_BOOTS); //
	public static ArmorProperty NETHERITE_LEGGINGS = init(556, 9, 3, 19, 1, "leggings", "netherite",
			Material.NETHERITE_LEGGINGS); //
	public static ArmorProperty NETHERITE_CHESTPLATE = init(593, 11, 5, 29, 1, "chestplate", "netherite",
			Material.NETHERITE_CHESTPLATE); //
	public static ArmorProperty NETHERITE_HELMET = init(408, 6, 2, 15, 1, "helmet", "netherite",
			Material.NETHERITE_HELMET); //

	public static ArmorProperty TURTLE_HELMET = init(276, 9, 9, 29, 0, "helmet", "turtle", Material.TURTLE_HELMET); //

	public static ArmorProperty init(int durability, int physicalResistance, int magicResistance, int weight,
			int knockbackResistance, String type, String material, Material bukkitMaterial) {
		String path = "imagicube.armor." + material + "." + type;
		if (Config.getConfig("durability").contains(path))
			durability = Config.getConfig("durability").getInt(path);
		ArmorProperty armorProperty = new ArmorProperty(durability, physicalResistance, magicResistance, weight,
				knockbackResistance, type, material, bukkitMaterial);
		armorProperties.add(armorProperty);
		return armorProperty;
	}

	public static List<ArmorProperty> getArmorProperties() {
		return armorProperties;
	}
}
