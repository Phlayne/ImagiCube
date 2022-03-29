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

	public Material getBukkitMaterial();

	public static ArmorProperty getVanillaArmorProperty(Material material) {
		for (ArmorProperty armorProperty : ArmorProperties.values())
			if (armorProperty.getBukkitMaterial().equals(material))
				return armorProperty;
		return null;
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
