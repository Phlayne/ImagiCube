package fr.phlayne.imagicube.items;

import org.bukkit.Material;

import de.tr7zw.nbtapi.NBTCompound;
import fr.phlayne.imagicube.util.NBTUtil;

public enum ArmorProperty {

	LEATHER_BOOTS(78, 1, 5, 4, 0, "boots", "leather"), //
	LEATHER_LEGGINGS(113, 2, 8, 7, 0, "leggings", "leather"), //
	LEATHER_CHESTPLATE(106, 4, 10, 9, 0, "chestplate", "leather"), //
	LEATHER_HELMET(92, 1, 5, 5, 0, "helmet", "leather"), //

	CHAINMAIL_BOOTS(196, 2, 6, 12, 1, "boots", "chainmail"), //
	CHAINMAIL_LEGGINGS(226, 3, 9, 19, 1, "leggings", "chainmail"), //
	CHAINMAIL_CHESTPLATE(241, 5, 11, 29, 1, "chestplate", "chainmail"), //
	CHAINMAIL_HELMET(166, 2, 6, 15, 1, "helmet", "chainmail"), //

	GOLDEN_BOOTS(308, 3, 3, 4, 0, "boots", "golden"), //
	GOLDEN_LEGGINGS(356, 5, 5, 7, 0, "leggings", "golden"), //
	GOLDEN_CHESTPLATE(379, 7, 7, 9, 0, "chestplate", "golden"), //
	GOLDEN_HELMET(261, 3, 3, 5, 0, "helmet", "golden"), //

	IRON_BOOTS(196, 4, 4, 12, 1, "boots", "iron"), //
	IRON_LEGGINGS(226, 6, 6, 19, 1, "leggings", "iron"), //
	IRON_CHESTPLATE(241, 8, 8, 29, 1, "chestplate", "iron"), //
	IRON_HELMET(166, 4, 4, 15, 1, "helmet", "iron"), //

	DIAMOND_BOOTS(430, 5, 1, 4, 0, "boots", "diamond"), //
	DIAMOND_LEGGINGS(496, 8, 2, 7, 0, "leggings", "diamond"), //
	DIAMOND_CHESTPLATE(529, 10, 4, 9, 0, "chestplate", "diamond"), //
	DIAMOND_HELMET(364, 5, 1, 5, 0, "helmet", "diamond"), //

	NETHERITE_BOOTS(482, 6, 2, 12, 1, "boots", "netherite"), //
	NETHERITE_LEGGINGS(556, 9, 3, 19, 1, "leggings", "netherite"), //
	NETHERITE_CHESTPLATE(593, 11, 5, 29, 1, "chestplate", "netherite"), //
	NETHERITE_HELMET(408, 6, 2, 15, 1, "helmet", "netherite"), //

	TURTLE_HELMET(276, 9, 9, 29, 0, "helmet", "turtle"); //

	private int durability;
	private int physicalResistance;
	private int magicalResistance;
	private int knockbackResistance;
	private int weight;
	private String type;
	private String material;

	ArmorProperty(int durability, int physicalResistance, int magicResistance, int weight, int knockbackResistance,
			String type, String material) {
		this.durability = durability;
		this.physicalResistance = physicalResistance;
		this.magicalResistance = magicResistance;
		this.knockbackResistance = knockbackResistance;
		this.weight = weight;
		this.type = type;
		this.material = material;
	}

	public int getDurability() {
		return this.durability;
	}

	public int getMagicalResistance() {
		return this.magicalResistance;
	}

	public int getPhysicalResistance() {
		return this.physicalResistance;
	}

	public int getKnockbackResistance() {
		return this.knockbackResistance;
	}

	public int getWeight() {
		return this.weight;
	}

	public String getType() {
		return this.type;
	}

	public String getMaterial() {
		return this.material;
	}

	public static ArmorProperty getVanillaArmorProperty(Material material) {
		switch (material) {
		case LEATHER_BOOTS:
			return ArmorProperty.LEATHER_BOOTS;
		case LEATHER_LEGGINGS:
			return ArmorProperty.LEATHER_LEGGINGS;
		case LEATHER_CHESTPLATE:
			return ArmorProperty.LEATHER_CHESTPLATE;
		case LEATHER_HELMET:
			return ArmorProperty.LEATHER_HELMET;
		case CHAINMAIL_BOOTS:
			return ArmorProperty.CHAINMAIL_BOOTS;
		case CHAINMAIL_LEGGINGS:
			return ArmorProperty.CHAINMAIL_LEGGINGS;
		case CHAINMAIL_CHESTPLATE:
			return ArmorProperty.CHAINMAIL_CHESTPLATE;
		case CHAINMAIL_HELMET:
			return ArmorProperty.CHAINMAIL_HELMET;
		case IRON_BOOTS:
			return ArmorProperty.IRON_BOOTS;
		case IRON_LEGGINGS:
			return ArmorProperty.IRON_LEGGINGS;
		case IRON_CHESTPLATE:
			return ArmorProperty.IRON_CHESTPLATE;
		case IRON_HELMET:
			return ArmorProperty.IRON_HELMET;
		case GOLDEN_BOOTS:
			return ArmorProperty.GOLDEN_BOOTS;
		case GOLDEN_LEGGINGS:
			return ArmorProperty.GOLDEN_LEGGINGS;
		case GOLDEN_CHESTPLATE:
			return ArmorProperty.GOLDEN_CHESTPLATE;
		case GOLDEN_HELMET:
			return ArmorProperty.GOLDEN_HELMET;
		case DIAMOND_BOOTS:
			return ArmorProperty.DIAMOND_BOOTS;
		case DIAMOND_LEGGINGS:
			return ArmorProperty.DIAMOND_LEGGINGS;
		case DIAMOND_CHESTPLATE:
			return ArmorProperty.DIAMOND_CHESTPLATE;
		case DIAMOND_HELMET:
			return ArmorProperty.DIAMOND_HELMET;
		case NETHERITE_BOOTS:
			return ArmorProperty.NETHERITE_BOOTS;
		case NETHERITE_LEGGINGS:
			return ArmorProperty.NETHERITE_LEGGINGS;
		case NETHERITE_CHESTPLATE:
			return ArmorProperty.NETHERITE_CHESTPLATE;
		case NETHERITE_HELMET:
			return ArmorProperty.NETHERITE_HELMET;
		case TURTLE_HELMET:
			return ArmorProperty.TURTLE_HELMET;
		default:
			return null;
		}
	}

	public static ArmorProperty getArmorProperty(NBTCompound nbt) {
		String itemType = nbt.hasKey(NBTUtil.ITEM_TYPE) ? nbt.getString(NBTUtil.ITEM_TYPE) : "";
		String material = nbt.hasKey(NBTUtil.MATERIAL) ? nbt.getString(NBTUtil.MATERIAL) : "";
		if (itemType.equals("") || material.equals(""))
			return null;
		for (ArmorProperty armorProperty : ArmorProperty.values()) {
			if (armorProperty.getType().equals(itemType) && armorProperty.getMaterial().equals(material))
				return armorProperty;
		}
		return null;
	}
}
