package fr.phlayne.imagicube.item;

import org.bukkit.Material;

public enum WeaponProperties implements WeaponProperty {

	OAK_PICKAXE(59, 2F, 1.2F, Tools.PICKAXE.getName(), "oak", Material.WOODEN_PICKAXE, 0), //
	SPRUCE_PICKAXE(59, 2F, 1.2F, Tools.PICKAXE.getName(), "spruce", Material.WOODEN_PICKAXE, 1), //
	BIRCH_PICKAXE(59, 2F, 1.2F, Tools.PICKAXE.getName(), "birch", Material.WOODEN_PICKAXE, 2), //
	JUNGLE_PICKAXE(59, 2F, 1.2F, Tools.PICKAXE.getName(), "jungle", Material.WOODEN_PICKAXE, 3), //
	ACACIA_PICKAXE(59, 2F, 1.2F, Tools.PICKAXE.getName(), "acacia", Material.WOODEN_PICKAXE, 4), //
	DARK_OAK_PICKAXE(59, 2F, 1.2F, Tools.PICKAXE.getName(), "dark_oak", Material.WOODEN_PICKAXE, 5), //
	CRIMSON_PICKAXE(59, 2F, 1.2F, Tools.PICKAXE.getName(), "crimson", Material.WOODEN_PICKAXE, 6), //
	WARPED_PICKAXE(59, 2F, 1.2F, Tools.PICKAXE.getName(), "warped", Material.WOODEN_PICKAXE, 7), //
	GOLDEN_PICKAXE(847, 4F, 1.2F, Tools.PICKAXE.getName(), "golden", Material.GOLDEN_PICKAXE, 0), //
	STONE_PICKAXE(131, 3F, 1.2F, Tools.PICKAXE.getName(), "stone", Material.STONE_PICKAXE, 0), //
	BLACKSTONE_PICKAXE(190, 3F, 1.2F, Tools.PICKAXE.getName(), "blackstone", Material.STONE_PICKAXE, 1), //
	IRON_PICKAXE(250, 4F, 1.2F, Tools.PICKAXE.getName(), "iron", Material.IRON_PICKAXE, 0), //
	DIAMOND_PICKAXE(1561, 5F, 1.2F, Tools.PICKAXE.getName(), "diamond", Material.DIAMOND_PICKAXE, 0), //
	NETHERITE_PICKAXE(2031, 6F, 1.2F, Tools.PICKAXE.getName(), "netherite", Material.NETHERITE_PICKAXE, 0), //
	PRISMARINE_PICKAXE(631, 4F, 1.2F, Tools.PICKAXE.getName(), "prismarine", Material.IRON_PICKAXE, 1), //

	OAK_SHOVEL(59, 2.5F, 1F, Tools.SHOVEL.getName(), "oak", Material.WOODEN_SHOVEL, 0), //
	SPRUCE_SHOVEL(59, 2.5F, 1F, Tools.SHOVEL.getName(), "spruce", Material.WOODEN_SHOVEL, 1), //
	BIRCH_SHOVEL(59, 2.5F, 1F, Tools.SHOVEL.getName(), "birch", Material.WOODEN_SHOVEL, 2), //
	JUNGLE_SHOVEL(59, 2.5F, 1F, Tools.SHOVEL.getName(), "jungle", Material.WOODEN_SHOVEL, 3), //
	ACACIA_SHOVEL(59, 2.5F, 1F, Tools.SHOVEL.getName(), "acacia", Material.WOODEN_SHOVEL, 4), //
	DARK_OAK_SHOVEL(59, 2.5F, 1F, Tools.SHOVEL.getName(), "dark_oak", Material.WOODEN_SHOVEL, 5), //
	CRIMSON_SHOVEL(59, 2.5F, 1F, Tools.SHOVEL.getName(), "crimson", Material.WOODEN_SHOVEL, 6), //
	WARPED_SHOVEL(59, 2.5F, 1F, Tools.SHOVEL.getName(), "warped", Material.WOODEN_SHOVEL, 7), //
	GOLDEN_SHOVEL(847, 4.5F, 1F, Tools.SHOVEL.getName(), "golden", Material.GOLDEN_SHOVEL, 0), //
	STONE_SHOVEL(131, 3.5F, 1F, Tools.SHOVEL.getName(), "stone", Material.STONE_SHOVEL, 0), //
	BLACKSTONE_SHOVEL(190, 3.5F, 1F, Tools.SHOVEL.getName(), "blackstone", Material.STONE_SHOVEL, 1), //
	IRON_SHOVEL(250, 4.5F, 1F, Tools.SHOVEL.getName(), "iron", Material.IRON_SHOVEL, 0), //
	DIAMOND_SHOVEL(1561, 5.5F, 1F, Tools.SHOVEL.getName(), "diamond", Material.DIAMOND_SHOVEL, 0), //
	NETHERITE_SHOVEL(2031, 6.5F, 1F, Tools.SHOVEL.getName(), "netherite", Material.NETHERITE_SHOVEL, 0), //
	PRISMARINE_SHOVEL(631, 4.5F, 1F, Tools.SHOVEL.getName(), "prismarine", Material.IRON_SHOVEL, 1), //

	OAK_HOE(59, 1F, 1.0F, Tools.HOE.getName(), "oak", Material.WOODEN_HOE, 0), //
	SPRUCE_HOE(59, 1F, 1.0F, Tools.HOE.getName(), "spruce", Material.WOODEN_HOE, 1), //
	BIRCH_HOE(59, 1F, 1.0F, Tools.HOE.getName(), "birch", Material.WOODEN_HOE, 2), //
	JUNGLE_HOE(59, 1F, 1.0F, Tools.HOE.getName(), "jungle", Material.WOODEN_HOE, 3), //
	ACACIA_HOE(59, 1F, 1.0F, Tools.HOE.getName(), "acacia", Material.WOODEN_HOE, 4), //
	DARK_OAK_HOE(59, 1F, 1.0F, Tools.HOE.getName(), "dark_oak", Material.WOODEN_HOE, 5), //
	CRIMSON_HOE(59, 1F, 1.0F, Tools.HOE.getName(), "crimson", Material.WOODEN_HOE, 6), //
	WARPED_HOE(59, 1F, 1.0F, Tools.HOE.getName(), "warped", Material.WOODEN_HOE, 7), //
	GOLDEN_HOE(847, 1F, 5.0F, Tools.HOE.getName(), "golden", Material.GOLDEN_HOE, 0), //
	STONE_HOE(131, 1F, 2.0F, Tools.HOE.getName(), "stone", Material.STONE_HOE, 0), //
	BLACKSTONE_HOE(190, 1F, 2.0F, Tools.HOE.getName(), "blackstone", Material.STONE_HOE, 1), //
	IRON_HOE(250, 1F, 3.0F, Tools.HOE.getName(), "iron", Material.IRON_HOE, 0), //
	DIAMOND_HOE(1561, 1F, 4.0F, Tools.HOE.getName(), "diamond", Material.DIAMOND_HOE, 0), //
	NETHERITE_HOE(2031, 1F, 4.0F, Tools.HOE.getName(), "netherite", Material.NETHERITE_HOE, 0), //
	PRISMARINE_HOE(631, 1F, 3.0F, Tools.HOE.getName(), "prismarine", Material.IRON_HOE, 1), //

	OAK_SWORD(59, 4F, 1.6F, Tools.SWORD.getName(), "oak", Material.WOODEN_SWORD, 0), //
	SPRUCE_SWORD(59, 4F, 1.6F, Tools.SWORD.getName(), "spruce", Material.WOODEN_SWORD, 1), //
	BIRCH_SWORD(59, 4F, 1.6F, Tools.SWORD.getName(), "birch", Material.WOODEN_SWORD, 2), //
	JUNGLE_SWORD(59, 4F, 1.6F, Tools.SWORD.getName(), "jungle", Material.WOODEN_SWORD, 3), //
	ACACIA_SWORD(59, 4F, 1.6F, Tools.SWORD.getName(), "acacia", Material.WOODEN_SWORD, 4), //
	DARK_OAK_SWORD(59, 4F, 1.6F, Tools.SWORD.getName(), "dark_oak", Material.WOODEN_SWORD, 5), //
	CRIMSON_SWORD(59, 4F, 1.6F, Tools.SWORD.getName(), "crimson", Material.WOODEN_SWORD, 6), //
	WARPED_SWORD(59, 4F, 1.6F, Tools.SWORD.getName(), "warped", Material.WOODEN_SWORD, 7), //
	GOLDEN_SWORD(847, 6F, 1.6F, Tools.SWORD.getName(), "golden", Material.GOLDEN_SWORD, 0), //
	STONE_SWORD(131, 5F, 1.6F, Tools.SWORD.getName(), "stone", Material.STONE_SWORD, 0), //
	BLACKSTONE_SWORD(190, 5F, 1.6F, Tools.SWORD.getName(), "blackstone", Material.STONE_SWORD, 1), //
	IRON_SWORD(250, 6F, 1.6F, Tools.SWORD.getName(), "iron", Material.IRON_SWORD, 0), //
	DIAMOND_SWORD(1561, 7F, 1.6F, Tools.SWORD.getName(), "diamond", Material.DIAMOND_SWORD, 0), //
	NETHERITE_SWORD(2031, 8F, 1.6F, Tools.SWORD.getName(), "netherite", Material.NETHERITE_SWORD, 0), //
	PRISMARINE_SWORD(631, 6F, 1.6F, Tools.SWORD.getName(), "prismarine", Material.IRON_SWORD, 1), //

	OAK_AXE(59, 6.5F, 0.8F, Tools.AXE.getName(), "oak", Material.WOODEN_AXE, 0), //
	SPRUCE_AXE(59, 6.5F, 0.8F, Tools.AXE.getName(), "spruce", Material.WOODEN_AXE, 1), //
	BIRCH_AXE(59, 6.5F, 0.8F, Tools.AXE.getName(), "birch", Material.WOODEN_AXE, 2), //
	JUNGLE_AXE(59, 6.5F, 0.8F, Tools.AXE.getName(), "jungle", Material.WOODEN_AXE, 3), //
	ACACIA_AXE(59, 6.5F, 0.8F, Tools.AXE.getName(), "acacia", Material.WOODEN_AXE, 4), //
	DARK_OAK_AXE(59, 6.5F, 0.8F, Tools.AXE.getName(), "dark_oak", Material.WOODEN_AXE, 5), //
	CRIMSON_AXE(59, 6.5F, 0.8F, Tools.AXE.getName(), "crimson", Material.WOODEN_AXE, 6), //
	WARPED_AXE(59, 6.5F, 0.8F, Tools.AXE.getName(), "warped", Material.WOODEN_AXE, 7), //
	GOLDEN_AXE(847, 9.5F, 0.9F, Tools.AXE.getName(), "golden", Material.GOLDEN_AXE, 0), //
	STONE_AXE(131, 8.0F, 0.8F, Tools.AXE.getName(), "stone", Material.STONE_AXE, 0), //
	BLACKSTONE_AXE(190, 8.0F, 0.8F, Tools.AXE.getName(), "blackstone", Material.STONE_AXE, 1), //
	IRON_AXE(250, 9.5F, 0.9F, Tools.AXE.getName(), "iron", Material.IRON_AXE, 0), //
	DIAMOND_AXE(1561, 11F, 1F, Tools.AXE.getName(), "diamond", Material.DIAMOND_AXE, 0), //
	NETHERITE_AXE(2031, 12F, 1F, Tools.AXE.getName(), "netherite", Material.NETHERITE_AXE, 0), //
	PRISMARINE_AXE(631, 9.5F, 0.9F, Tools.AXE.getName(), "prismarine", Material.IRON_AXE, 1), //

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
