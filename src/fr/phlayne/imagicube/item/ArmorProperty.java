package fr.phlayne.imagicube.item;

import org.bukkit.Material;

import de.tr7zw.nbtapi.NBTCompound;
import fr.phlayne.imagicube.ImagiCube;
import fr.phlayne.imagicube.util.NBTUtil;

public class ArmorProperty {

	private int durability;
	private int physicalResistance;
	private int magicalResistance;
	private int knockbackResistance;
	private int weight;
	private String type;
	private String material;
	private Material bukkitMaterial;

	public ArmorProperty(int durability, int physicalResistance, int magicResistance, int weight,
			int knockbackResistance, String type, String material, Material bukkitMaterial) {
		this.durability = durability;
		this.physicalResistance = physicalResistance;
		this.magicalResistance = magicResistance;
		this.knockbackResistance = knockbackResistance;
		this.weight = weight;
		this.type = type;
		this.material = material;
		this.bukkitMaterial = bukkitMaterial;
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

	public Material getBukkitMaterial() {
		return this.bukkitMaterial;
	}

	public static ArmorProperty getVanillaArmorProperty(Material material) {
		for (ArmorProperty armorProperty : ArmorProperties.getArmorProperties())
			if (armorProperty.getBukkitMaterial().equals(material))
				return armorProperty;
		return null;
	}

	public static ArmorProperty getArmorProperty(NBTCompound nbt) {
		String itemType = nbt.hasKey(NBTUtil.ITEM_TYPE) ? nbt.getString(NBTUtil.ITEM_TYPE) : "";
		String material = nbt.hasKey(NBTUtil.MATERIAL) ? nbt.getString(NBTUtil.MATERIAL) : "";
		if (itemType.equals("") || material.equals(""))
			return null;
		for (ArmorProperty armorProperty : ImagiCube.getInstance().getAddonList().getArmors()) {
			if (armorProperty.getType().equals(itemType) && armorProperty.getMaterial().equals(material))
				return armorProperty;
		}
		return null;
	}


	public static ArmorProperty getArmorProperty(String type, String material) {
		for (ArmorProperty ap : ImagiCube.getInstance().getAddonList().getArmors())
			if (ap.getType().equals(type) && ap.getMaterial().equals(material))
				return ap;
		return null;
	}
}
