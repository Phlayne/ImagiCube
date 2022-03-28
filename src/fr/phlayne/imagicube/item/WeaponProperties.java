package fr.phlayne.imagicube.item;

import org.bukkit.Material;

public enum WeaponProperties implements WeaponProperty {

	OAK_PICKAXE(59, 2F, 1.2F, "pickaxe", "oak", Material.WOODEN_PICKAXE, 0), //
	SPRUCE_PICKAXE(59, 2F, 1.2F, "pickaxe", "spruce", Material.WOODEN_PICKAXE, 1), //
	BIRCH_PICKAXE(59, 2F, 1.2F, "pickaxe", "birch", Material.WOODEN_PICKAXE, 2), //
	JUNGLE_PICKAXE(59, 2F, 1.2F, "pickaxe", "jungle", Material.WOODEN_PICKAXE, 3), //
	ACACIA_PICKAXE(59, 2F, 1.2F, "pickaxe", "acacia", Material.WOODEN_PICKAXE, 4), //
	DARK_OAK_PICKAXE(59, 2F, 1.2F, "pickaxe", "dark_oak", Material.WOODEN_PICKAXE, 5), //
	CRIMSON_PICKAXE(59, 2F, 1.2F, "pickaxe", "crimson", Material.WOODEN_PICKAXE, 6), //
	WARPED_PICKAXE(59, 2F, 1.2F, "pickaxe", "warped", Material.WOODEN_PICKAXE, 7), //
	GOLDEN_PICKAXE(847, 4F, 1.2F, "pickaxe", "golden", Material.GOLDEN_PICKAXE, 0), //
	STONE_PICKAXE(131, 3F, 1.2F, "pickaxe", "stone", Material.STONE_PICKAXE, 0), //
	BLACKSTONE_PICKAXE(190, 3F, 1.2F, "pickaxe", "blackstone", Material.STONE_PICKAXE, 1), //
	IRON_PICKAXE(250, 4F, 1.2F, "pickaxe", "iron", Material.IRON_PICKAXE, 0), //
	DIAMOND_PICKAXE(1561, 5F, 1.2F, "pickaxe", "diamond", Material.DIAMOND_PICKAXE, 0), //
	NETHERITE_PICKAXE(2031, 6F, 1.2F, "pickaxe", "netherite", Material.NETHERITE_PICKAXE, 0), //
	PRISMARINE_PICKAXE(631, 4F, 1.2F, "pickaxe", "prismarine", Material.IRON_PICKAXE, 1), //

	OAK_SHOVEL(59, 2.5F, 1F, "shovel", "oak", Material.WOODEN_SHOVEL, 0), //
	SPRUCE_SHOVEL(59, 2.5F, 1F, "shovel", "spruce", Material.WOODEN_SHOVEL, 1), //
	BIRCH_SHOVEL(59, 2.5F, 1F, "shovel", "birch", Material.WOODEN_SHOVEL, 2), //
	JUNGLE_SHOVEL(59, 2.5F, 1F, "shovel", "jungle", Material.WOODEN_SHOVEL, 3), //
	ACACIA_SHOVEL(59, 2.5F, 1F, "shovel", "acacia", Material.WOODEN_SHOVEL, 4), //
	DARK_OAK_SHOVEL(59, 2.5F, 1F, "shovel", "dark_oak", Material.WOODEN_SHOVEL, 5), //
	CRIMSON_SHOVEL(59, 2.5F, 1F, "shovel", "crimson", Material.WOODEN_SHOVEL, 6), //
	WARPED_SHOVEL(59, 2.5F, 1F, "shovel", "warped", Material.WOODEN_SHOVEL, 7), //
	GOLDEN_SHOVEL(847, 4.5F, 1F, "shovel", "golden", Material.GOLDEN_SHOVEL, 0), //
	STONE_SHOVEL(131, 3.5F, 1F, "shovel", "stone", Material.STONE_SHOVEL, 0), //
	BLACKSTONE_SHOVEL(190, 3.5F, 1F, "shovel", "blackstone", Material.STONE_SHOVEL, 1), //
	IRON_SHOVEL(250, 4.5F, 1F, "shovel", "iron", Material.IRON_SHOVEL, 0), //
	DIAMOND_SHOVEL(1561, 5.5F, 1F, "shovel", "diamond", Material.DIAMOND_SHOVEL, 0), //
	NETHERITE_SHOVEL(2031, 6.5F, 1F, "shovel", "netherite", Material.NETHERITE_SHOVEL, 0), //
	PRISMARINE_SHOVEL(631, 4.5F, 1F, "shovel", "prismarine", Material.IRON_SHOVEL, 1), //

	OAK_HOE(59, 1F, 1.0F, "hoe", "oak", Material.WOODEN_HOE, 0), //
	SPRUCE_HOE(59, 1F, 1.0F, "hoe", "spruce", Material.WOODEN_HOE, 1), //
	BIRCH_HOE(59, 1F, 1.0F, "hoe", "birch", Material.WOODEN_HOE, 2), //
	JUNGLE_HOE(59, 1F, 1.0F, "hoe", "jungle", Material.WOODEN_HOE, 3), //
	ACACIA_HOE(59, 1F, 1.0F, "hoe", "acacia", Material.WOODEN_HOE, 4), //
	DARK_OAK_HOE(59, 1F, 1.0F, "hoe", "dark_oak", Material.WOODEN_HOE, 5), //
	CRIMSON_HOE(59, 1F, 1.0F, "hoe", "crimson", Material.WOODEN_HOE, 6), //
	WARPED_HOE(59, 1F, 1.0F, "hoe", "warped", Material.WOODEN_HOE, 7), //
	GOLDEN_HOE(847, 1F, 5.0F, "hoe", "golden", Material.GOLDEN_HOE, 0), //
	STONE_HOE(131, 1F, 2.0F, "hoe", "stone", Material.STONE_HOE, 0), //
	BLACKSTONE_HOE(190, 1F, 2.0F, "hoe", "blackstone", Material.STONE_HOE, 1), //
	IRON_HOE(250, 1F, 3.0F, "hoe", "iron", Material.IRON_HOE, 0), //
	DIAMOND_HOE(1561, 1F, 4.0F, "hoe", "diamond", Material.DIAMOND_HOE, 0), //
	NETHERITE_HOE(2031, 1F, 4.0F, "hoe", "netherite", Material.NETHERITE_HOE, 0), //
	PRISMARINE_HOE(631, 1F, 3.0F, "hoe", "prismarine", Material.IRON_HOE, 1), //

	OAK_SWORD(59, 4F, 1.6F, "sword", "oak", Material.WOODEN_SWORD, 0), //
	SPRUCE_SWORD(59, 4F, 1.6F, "sword", "spruce", Material.WOODEN_SWORD, 1), //
	BIRCH_SWORD(59, 4F, 1.6F, "sword", "birch", Material.WOODEN_SWORD, 2), //
	JUNGLE_SWORD(59, 4F, 1.6F, "sword", "jungle", Material.WOODEN_SWORD, 3), //
	ACACIA_SWORD(59, 4F, 1.6F, "sword", "acacia", Material.WOODEN_SWORD, 4), //
	DARK_OAK_SWORD(59, 4F, 1.6F, "sword", "dark_oak", Material.WOODEN_SWORD, 5), //
	CRIMSON_SWORD(59, 4F, 1.6F, "sword", "crimson", Material.WOODEN_SWORD, 6), //
	WARPED_SWORD(59, 4F, 1.6F, "sword", "warped", Material.WOODEN_SWORD, 7), //
	GOLDEN_SWORD(847, 6F, 1.6F, "sword", "golden", Material.GOLDEN_SWORD, 0), //
	STONE_SWORD(131, 5F, 1.6F, "sword", "stone", Material.STONE_SWORD, 0), //
	BLACKSTONE_SWORD(190, 5F, 1.6F, "sword", "blackstone", Material.STONE_SWORD, 1), //
	IRON_SWORD(250, 6F, 1.6F, "sword", "iron", Material.IRON_SWORD, 0), //
	DIAMOND_SWORD(1561, 7F, 1.6F, "sword", "diamond", Material.DIAMOND_SWORD, 0), //
	NETHERITE_SWORD(2031, 8F, 1.6F, "sword", "netherite", Material.NETHERITE_SWORD, 0), //
	PRISMARINE_SWORD(631, 6F, 1.6F, "sword", "prismarine", Material.IRON_SWORD, 1), //

	OAK_AXE(59, 6.5F, 0.8F, "axe", "oak", Material.WOODEN_AXE, 0), //
	SPRUCE_AXE(59, 6.5F, 0.8F, "axe", "spruce", Material.WOODEN_AXE, 1), //
	BIRCH_AXE(59, 6.5F, 0.8F, "axe", "birch", Material.WOODEN_AXE, 2), //
	JUNGLE_AXE(59, 6.5F, 0.8F, "axe", "jungle", Material.WOODEN_AXE, 3), //
	ACACIA_AXE(59, 6.5F, 0.8F, "axe", "acacia", Material.WOODEN_AXE, 4), //
	DARK_OAK_AXE(59, 6.5F, 0.8F, "axe", "dark_oak", Material.WOODEN_AXE, 5), //
	CRIMSON_AXE(59, 6.5F, 0.8F, "axe", "crimson", Material.WOODEN_AXE, 6), //
	WARPED_AXE(59, 6.5F, 0.8F, "axe", "warped", Material.WOODEN_AXE, 7), //
	GOLDEN_AXE(847, 9.5F, 0.9F, "axe", "golden", Material.GOLDEN_AXE, 0), //
	STONE_AXE(131, 8.0F, 0.8F, "axe", "stone", Material.STONE_AXE, 0), //
	BLACKSTONE_AXE(190, 8.0F, 0.8F, "axe", "blackstone", Material.STONE_AXE, 1), //
	IRON_AXE(250, 9.5F, 0.9F, "axe", "iron", Material.IRON_AXE, 0), //
	DIAMOND_AXE(1561, 11F, 1F, "axe", "diamond", Material.DIAMOND_AXE, 0), //
	NETHERITE_AXE(2031, 12F, 1F, "axe", "netherite", Material.NETHERITE_AXE, 0), //
	PRISMARINE_AXE(631, 9.5F, 0.9F, "axe", "prismarine", Material.IRON_AXE, 1), //

	OAK_SCYTHE(59, 4F, 1.2F, "scythe", "oak", Material.WOODEN_HOE, 10), //
	SPRUCE_SCYTHE(59, 4F, 1.2F, "scythe", "spruce", Material.WOODEN_HOE, 11), //
	BIRCH_SCYTHE(59, 4F, 1.2F, "scythe", "birch", Material.WOODEN_HOE, 12), //
	JUNGLE_SCYTHE(59, 4F, 1.2F, "scythe", "jungle", Material.WOODEN_HOE, 13), //
	ACACIA_SCYTHE(59, 4F, 1.2F, "scythe", "acacia", Material.WOODEN_HOE, 14), //
	DARK_OAK_SCYTHE(59, 4F, 1.2F, "scythe", "dark_oak", Material.WOODEN_HOE, 15), //
	CRIMSON_SCYTHE(59, 4F, 1.2F, "scythe", "crimson", Material.WOODEN_HOE, 16), //
	WARPED_SCYTHE(59, 4F, 1.2F, "scythe", "warped", Material.WOODEN_HOE, 17), //
	GOLDEN_SCYTHE(847, 6F, 1.3F, "scythe", "golden", Material.GOLDEN_HOE, 1), //
	STONE_SCYTHE(131, 5F, 1.2F, "scythe", "stone", Material.STONE_HOE, 2), //
	BLACKSTONE_SCYTHE(190, 5F, 1.2F, "scythe", "blackstone", Material.STONE_HOE, 3), //
	IRON_SCYTHE(250, 6F, 1.3F, "scythe", "iron", Material.IRON_HOE, 2), //
	DIAMOND_SCYTHE(1561, 7F, 1.4F, "scythe", "diamond", Material.DIAMOND_HOE, 1), //
	NETHERITE_SCYTHE(2031, 8F, 1.4F, "scythe", "netherite", Material.NETHERITE_HOE, 1), //
	PRISMARINE_SCYTHE(631, 6F, 1.3F, "scythe", "prismarine", Material.IRON_HOE, 3), //

	TRIDENT(631, 9F, 1.1F, "trident", "prismarine", Material.TRIDENT, 0); //

	private int durability;
	private double damage;
	private double attackSpeed;
	private String type;
	private String material;
	private Material bukkitMaterial;
	private int customModelData;

	WeaponProperties(int durability, float damage, float attackSpeed, String type, String material,
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
}
