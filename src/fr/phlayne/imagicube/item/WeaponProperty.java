package fr.phlayne.imagicube.item;

import org.bukkit.Material;

import de.tr7zw.nbtapi.NBTCompound;
import fr.phlayne.imagicube.ImagiCube;
import fr.phlayne.imagicube.util.NBTUtil;

public class WeaponProperty {

	private int durability;
	private double damage;
	private double attackSpeed;
	private String type;
	private String material;
	private Material bukkitMaterial;
	private int customModelData;

	public WeaponProperty(int durability, float damage, float attackSpeed, String type, String material,
			Material bukkitMaterial, int customModelData) {
		this.durability = durability;
		this.damage = damage;
		this.attackSpeed = attackSpeed;
		this.type = type;
		this.material = material;
		this.bukkitMaterial = bukkitMaterial;
		this.customModelData = customModelData;
	}

	public int getDurability() {
		return durability;
	}

	public double getDamage() {
		return damage;
	}

	public double getAttackSpeed() {
		return attackSpeed;
	}

	public String getType() {
		return type;
	}

	public String getMaterial() {
		return material;
	}

	public Material getBukkitMaterial() {
		return bukkitMaterial;
	}

	public int getCustomModelData() {
		return this.customModelData;
	}

	public static WeaponProperty getVanillaWeaponProperty(Material material) {
		switch (material) {
		case NETHERITE_AXE:
			return WeaponProperties.NETHERITE_AXE;
		case NETHERITE_HOE:
			return WeaponProperties.NETHERITE_HOE;
		case NETHERITE_PICKAXE:
			return WeaponProperties.NETHERITE_PICKAXE;
		case NETHERITE_SHOVEL:
			return WeaponProperties.NETHERITE_SHOVEL;
		case NETHERITE_SWORD:
			return WeaponProperties.NETHERITE_SWORD;
		case DIAMOND_AXE:
			return WeaponProperties.DIAMOND_AXE;
		case DIAMOND_HOE:
			return WeaponProperties.DIAMOND_HOE;
		case DIAMOND_PICKAXE:
			return WeaponProperties.DIAMOND_PICKAXE;
		case DIAMOND_SHOVEL:
			return WeaponProperties.DIAMOND_SHOVEL;
		case DIAMOND_SWORD:
			return WeaponProperties.DIAMOND_SWORD;
		case GOLDEN_AXE:
			return WeaponProperties.GOLDEN_AXE;
		case GOLDEN_HOE:
			return WeaponProperties.GOLDEN_HOE;
		case GOLDEN_PICKAXE:
			return WeaponProperties.GOLDEN_PICKAXE;
		case GOLDEN_SHOVEL:
			return WeaponProperties.GOLDEN_SHOVEL;
		case GOLDEN_SWORD:
			return WeaponProperties.GOLDEN_SWORD;
		case IRON_AXE:
			return WeaponProperties.IRON_AXE;
		case IRON_HOE:
			return WeaponProperties.IRON_HOE;
		case IRON_PICKAXE:
			return WeaponProperties.IRON_PICKAXE;
		case IRON_SHOVEL:
			return WeaponProperties.IRON_SHOVEL;
		case IRON_SWORD:
			return WeaponProperties.IRON_SWORD;
		case STONE_AXE:
			return WeaponProperties.STONE_AXE;
		case STONE_HOE:
			return WeaponProperties.STONE_HOE;
		case STONE_PICKAXE:
			return WeaponProperties.STONE_PICKAXE;
		case STONE_SHOVEL:
			return WeaponProperties.STONE_SHOVEL;
		case STONE_SWORD:
			return WeaponProperties.STONE_SWORD;
		case TRIDENT:
			return WeaponProperties.TRIDENT;
		case WOODEN_AXE:
			return WeaponProperties.OAK_AXE;
		case WOODEN_HOE:
			return WeaponProperties.OAK_HOE;
		case WOODEN_PICKAXE:
			return WeaponProperties.OAK_PICKAXE;
		case WOODEN_SHOVEL:
			return WeaponProperties.OAK_SHOVEL;
		case WOODEN_SWORD:
			return WeaponProperties.OAK_SWORD;
		default:
			return null;
		}
	}

	public static WeaponProperty getWeaponProperty(NBTCompound nbt) {
		String itemType = nbt.hasKey(NBTUtil.ITEM_TYPE) ? nbt.getString(NBTUtil.ITEM_TYPE) : "";
		String material = nbt.hasKey(NBTUtil.MATERIAL) ? nbt.getString(NBTUtil.MATERIAL) : "";
		if (itemType == "" || material == "")
			return null;
		for (WeaponProperty weaponProperty : ImagiCube.getInstance().getAddonList().getWeapons()) {
			if (weaponProperty.getType().equals(itemType) && weaponProperty.getMaterial().equals(material))
				return weaponProperty;
		}
		return null;
	}

	public static WeaponProperty getWeaponProperty(String type, String material) {
		for (WeaponProperty wp : ImagiCube.getInstance().getAddonList().getWeapons())
			if (wp.getType().equals(type) && wp.getMaterial().equals(material))
				return wp;
		return null;
	}
}
