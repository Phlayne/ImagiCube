package fr.phlayne.imagicube.item;

import org.bukkit.Material;

public enum ArmorProperties implements ArmorProperty {

	LEATHER_BOOTS(78, 1, 5, 4, 0, "boots", "leather", Material.LEATHER_BOOTS), //
	LEATHER_LEGGINGS(113, 2, 8, 7, 0, "leggings", "leather", Material.LEATHER_LEGGINGS), //
	LEATHER_CHESTPLATE(106, 4, 10, 9, 0, "chestplate", "leather", Material.LEATHER_CHESTPLATE), //
	LEATHER_HELMET(92, 1, 5, 5, 0, "helmet", "leather", Material.LEATHER_HELMET), //

	CHAINMAIL_BOOTS(196, 2, 6, 12, 1, "boots", "chainmail", Material.CHAINMAIL_BOOTS), //
	CHAINMAIL_LEGGINGS(226, 3, 9, 19, 1, "leggings", "chainmail", Material.CHAINMAIL_LEGGINGS), //
	CHAINMAIL_CHESTPLATE(241, 5, 11, 29, 1, "chestplate", "chainmail", Material.CHAINMAIL_CHESTPLATE), //
	CHAINMAIL_HELMET(166, 2, 6, 15, 1, "helmet", "chainmail", Material.CHAINMAIL_HELMET), //

	GOLDEN_BOOTS(308, 3, 3, 4, 0, "boots", "gold", Material.GOLDEN_BOOTS), //
	GOLDEN_LEGGINGS(356, 5, 5, 7, 0, "leggings", "gold", Material.GOLDEN_LEGGINGS), //
	GOLDEN_CHESTPLATE(379, 7, 7, 9, 0, "chestplate", "gold", Material.GOLDEN_CHESTPLATE), //
	GOLDEN_HELMET(261, 3, 3, 5, 0, "helmet", "gold", Material.GOLDEN_HELMET), //

	IRON_BOOTS(196, 4, 4, 12, 1, "boots", "iron", Material.IRON_BOOTS), //
	IRON_LEGGINGS(226, 6, 6, 19, 1, "leggings", "iron", Material.IRON_LEGGINGS), //
	IRON_CHESTPLATE(241, 8, 8, 29, 1, "chestplate", "iron", Material.IRON_CHESTPLATE), //
	IRON_HELMET(166, 4, 4, 15, 1, "helmet", "iron", Material.IRON_HELMET), //

	DIAMOND_BOOTS(430, 5, 1, 4, 0, "boots", "diamond", Material.DIAMOND_BOOTS), //
	DIAMOND_LEGGINGS(496, 8, 2, 7, 0, "leggings", "diamond", Material.DIAMOND_LEGGINGS), //
	DIAMOND_CHESTPLATE(529, 10, 4, 9, 0, "chestplate", "diamond", Material.DIAMOND_CHESTPLATE), //
	DIAMOND_HELMET(364, 5, 1, 5, 0, "helmet", "diamond", Material.DIAMOND_HELMET), //

	NETHERITE_BOOTS(482, 6, 2, 12, 1, "boots", "netherite", Material.NETHERITE_BOOTS), //
	NETHERITE_LEGGINGS(556, 9, 3, 19, 1, "leggings", "netherite", Material.NETHERITE_LEGGINGS), //
	NETHERITE_CHESTPLATE(593, 11, 5, 29, 1, "chestplate", "netherite", Material.NETHERITE_CHESTPLATE), //
	NETHERITE_HELMET(408, 6, 2, 15, 1, "helmet", "netherite", Material.NETHERITE_HELMET), //

	TURTLE_HELMET(276, 9, 9, 29, 0, "helmet", "turtle", Material.TURTLE_HELMET); //

	private int durability;
	private int physicalResistance;
	private int magicalResistance;
	private int knockbackResistance;
	private int weight;
	private String type;
	private String material;
	private Material bukkitMaterial;

	ArmorProperties(int durability, int physicalResistance, int magicResistance, int weight, int knockbackResistance,
			String type, String material, Material bukkitMaterial) {
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

	@Override
	public Material getBukkitMaterial() {
		return this.bukkitMaterial;
	}
}
