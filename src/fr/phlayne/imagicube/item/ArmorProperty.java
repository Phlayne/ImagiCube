package fr.phlayne.imagicube.item;

import org.bukkit.Material;

import de.tr7zw.nbtapi.NBTCompound;
import fr.phlayne.imagicube.ImagiCube;
import fr.phlayne.imagicube.util.NBTUtil;

public interface ArmorProperty {

	public int getDurability();

	public int getMagicalResistance();

	public int getPhysicalResistance();

	public int getKnockbackResistance();

	public int getWeight();

	public String getType();

	public String getMaterial();

	public static ArmorProperty getVanillaArmorProperty(Material material) {
		switch (material) {
		case LEATHER_BOOTS:
			return ArmorProperties.LEATHER_BOOTS;
		case LEATHER_LEGGINGS:
			return ArmorProperties.LEATHER_LEGGINGS;
		case LEATHER_CHESTPLATE:
			return ArmorProperties.LEATHER_CHESTPLATE;
		case LEATHER_HELMET:
			return ArmorProperties.LEATHER_HELMET;
		case CHAINMAIL_BOOTS:
			return ArmorProperties.CHAINMAIL_BOOTS;
		case CHAINMAIL_LEGGINGS:
			return ArmorProperties.CHAINMAIL_LEGGINGS;
		case CHAINMAIL_CHESTPLATE:
			return ArmorProperties.CHAINMAIL_CHESTPLATE;
		case CHAINMAIL_HELMET:
			return ArmorProperties.CHAINMAIL_HELMET;
		case IRON_BOOTS:
			return ArmorProperties.IRON_BOOTS;
		case IRON_LEGGINGS:
			return ArmorProperties.IRON_LEGGINGS;
		case IRON_CHESTPLATE:
			return ArmorProperties.IRON_CHESTPLATE;
		case IRON_HELMET:
			return ArmorProperties.IRON_HELMET;
		case GOLDEN_BOOTS:
			return ArmorProperties.GOLDEN_BOOTS;
		case GOLDEN_LEGGINGS:
			return ArmorProperties.GOLDEN_LEGGINGS;
		case GOLDEN_CHESTPLATE:
			return ArmorProperties.GOLDEN_CHESTPLATE;
		case GOLDEN_HELMET:
			return ArmorProperties.GOLDEN_HELMET;
		case DIAMOND_BOOTS:
			return ArmorProperties.DIAMOND_BOOTS;
		case DIAMOND_LEGGINGS:
			return ArmorProperties.DIAMOND_LEGGINGS;
		case DIAMOND_CHESTPLATE:
			return ArmorProperties.DIAMOND_CHESTPLATE;
		case DIAMOND_HELMET:
			return ArmorProperties.DIAMOND_HELMET;
		case NETHERITE_BOOTS:
			return ArmorProperties.NETHERITE_BOOTS;
		case NETHERITE_LEGGINGS:
			return ArmorProperties.NETHERITE_LEGGINGS;
		case NETHERITE_CHESTPLATE:
			return ArmorProperties.NETHERITE_CHESTPLATE;
		case NETHERITE_HELMET:
			return ArmorProperties.NETHERITE_HELMET;
		case TURTLE_HELMET:
			return ArmorProperties.TURTLE_HELMET;
		default:
			return null;
		}
	}

	public static ArmorProperty getArmorProperty(NBTCompound nbt) {
		String itemType = nbt.hasKey(NBTUtil.ITEM_TYPE) ? nbt.getString(NBTUtil.ITEM_TYPE) : "";
		String material = nbt.hasKey(NBTUtil.MATERIAL) ? nbt.getString(NBTUtil.MATERIAL) : "";
		if (itemType.equals("") || material.equals(""))
			return null;
		for (ArmorProperty armorProperty : ImagiCube.getInstance().addonList.armors) {
			if (armorProperty.getType().equals(itemType) && armorProperty.getMaterial().equals(material))
				return armorProperty;
		}
		return null;
	}
}
