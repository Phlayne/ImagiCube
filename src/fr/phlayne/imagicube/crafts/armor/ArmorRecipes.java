package fr.phlayne.imagicube.crafts.armor;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTCompoundList;
import de.tr7zw.nbtapi.NBTContainer;
import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.ImagiCube;
import fr.phlayne.imagicube.data.Config;
import fr.phlayne.imagicube.item.ArmorProperties;
import fr.phlayne.imagicube.item.ArmorProperty;
import fr.phlayne.imagicube.item.Durability;
import fr.phlayne.imagicube.util.ItemUpdater;
import fr.phlayne.imagicube.util.NBTUtil;
import fr.phlayne.imagicube.util.SimpleJSON;

public class ArmorRecipes {

	public static Random random = new Random();

	public static void init() {
		ItemStack leatherBoots = setArmorValues(new ItemStack(Material.LEATHER_BOOTS), ArmorProperties.LEATHER_BOOTS);
		ItemStack leatherLeggings = setArmorValues(new ItemStack(Material.LEATHER_LEGGINGS),
				ArmorProperties.LEATHER_LEGGINGS);
		ItemStack leatherChestplate = setArmorValues(new ItemStack(Material.LEATHER_CHESTPLATE),
				ArmorProperties.LEATHER_CHESTPLATE);
		ItemStack leatherHelmet = setArmorValues(new ItemStack(Material.LEATHER_HELMET),
				ArmorProperties.LEATHER_HELMET);
		addCraft(leatherBoots, Material.LEATHER, CraftType.BOOTS);
		addCraft(leatherLeggings, Material.LEATHER, CraftType.LEGGINGS);
		addCraft(leatherChestplate, Material.LEATHER, CraftType.CHESTPLATE);
		addCraft(leatherHelmet, Material.LEATHER, CraftType.HELMET);

		ItemStack chainBoots = setArmorValues(new ItemStack(Material.CHAINMAIL_BOOTS), ArmorProperties.CHAINMAIL_BOOTS);
		ItemStack chainLeggings = setArmorValues(new ItemStack(Material.CHAINMAIL_LEGGINGS),
				ArmorProperties.CHAINMAIL_LEGGINGS);
		ItemStack chainChestplate = setArmorValues(new ItemStack(Material.CHAINMAIL_CHESTPLATE),
				ArmorProperties.CHAINMAIL_CHESTPLATE);
		ItemStack chainHelmet = setArmorValues(new ItemStack(Material.CHAINMAIL_HELMET),
				ArmorProperties.CHAINMAIL_HELMET);
		Material chainMaterial = Config.getConfig().getBoolean("craftable_chainmail_armor_with_chains") ? Material.CHAIN
				: Material.IRON_INGOT;
		addCraft(chainBoots, chainMaterial, CraftType.BOOTS);
		addCraft(chainLeggings, chainMaterial, CraftType.LEGGINGS);
		addCraft(chainChestplate, chainMaterial, CraftType.CHESTPLATE);
		addCraft(chainHelmet, chainMaterial, CraftType.HELMET);

		ItemStack ironBoots = setArmorValues(new ItemStack(Material.IRON_BOOTS), ArmorProperties.IRON_BOOTS);
		ItemStack ironLeggings = setArmorValues(new ItemStack(Material.IRON_LEGGINGS), ArmorProperties.IRON_LEGGINGS);
		ItemStack ironChestplate = setArmorValues(new ItemStack(Material.IRON_CHESTPLATE),
				ArmorProperties.IRON_CHESTPLATE);
		ItemStack ironHelmet = setArmorValues(new ItemStack(Material.IRON_HELMET), ArmorProperties.IRON_HELMET);
		addCraft(ironBoots, Material.IRON_INGOT, CraftType.BOOTS);
		addCraft(ironLeggings, Material.IRON_INGOT, CraftType.LEGGINGS);
		addCraft(ironChestplate, Material.IRON_INGOT, CraftType.CHESTPLATE);
		addCraft(ironHelmet, Material.IRON_INGOT, CraftType.HELMET);

		ItemStack goldBoots = setArmorValues(new ItemStack(Material.GOLDEN_BOOTS), ArmorProperties.GOLDEN_BOOTS);
		ItemStack goldLeggings = setArmorValues(new ItemStack(Material.GOLDEN_LEGGINGS),
				ArmorProperties.GOLDEN_LEGGINGS);
		ItemStack goldChestplate = setArmorValues(new ItemStack(Material.GOLDEN_CHESTPLATE),
				ArmorProperties.GOLDEN_CHESTPLATE);
		ItemStack goldHelmet = setArmorValues(new ItemStack(Material.GOLDEN_HELMET), ArmorProperties.GOLDEN_HELMET);
		addCraft(goldBoots, Material.GOLD_INGOT, CraftType.BOOTS);
		addCraft(goldLeggings, Material.GOLD_INGOT, CraftType.LEGGINGS);
		addCraft(goldChestplate, Material.GOLD_INGOT, CraftType.CHESTPLATE);
		addCraft(goldHelmet, Material.GOLD_INGOT, CraftType.HELMET);

		ItemStack diamondBoots = setArmorValues(new ItemStack(Material.DIAMOND_BOOTS), ArmorProperties.DIAMOND_BOOTS);
		ItemStack diamondLeggings = setArmorValues(new ItemStack(Material.DIAMOND_LEGGINGS),
				ArmorProperties.DIAMOND_LEGGINGS);
		ItemStack diamondChestplate = setArmorValues(new ItemStack(Material.DIAMOND_CHESTPLATE),
				ArmorProperties.DIAMOND_CHESTPLATE);
		ItemStack diamondHelmet = setArmorValues(new ItemStack(Material.DIAMOND_HELMET),
				ArmorProperties.DIAMOND_HELMET);
		addCraft(diamondBoots, Material.DIAMOND, CraftType.BOOTS);
		addCraft(diamondLeggings, Material.DIAMOND, CraftType.LEGGINGS);
		addCraft(diamondChestplate, Material.DIAMOND, CraftType.CHESTPLATE);
		addCraft(diamondHelmet, Material.DIAMOND, CraftType.HELMET);

		ItemStack turtleHelmet = setArmorValues(new ItemStack(Material.TURTLE_HELMET), ArmorProperties.TURTLE_HELMET);
		addCraft(turtleHelmet, Material.SCUTE, CraftType.HELMET);

	}

	public static void addCraft(ItemStack result, Material ingredient, CraftType craftType) {
		NamespacedKey key = new NamespacedKey(JavaPlugin.getPlugin(ImagiCube.class),
				ingredient.getKey().getKey() + "_" + craftType.craftName);
		ShapedRecipe recipe = new ShapedRecipe(key, result);
		recipe.shape(craftType.getCraft());
		recipe.setIngredient('m', ingredient);
		Bukkit.addRecipe(recipe);
	}

	public static ItemStack setArmorValues(ArmorProperty armorProperty) {
		return setArmorValues(new ItemStack(armorProperty.getBukkitMaterial()), armorProperty);
	}

	public static ItemStack setArmorValues(ItemStack item, ArmorProperty armorProperty) {
		NBTItem nbti = new NBTItem(item);
		setArmorValues(nbti, armorProperty);
		return nbti.getItem();
	}

	public static void setArmorValues(NBTItem nbti, ArmorProperty armorProperty) {
		int physical = armorProperty.getPhysicalResistance();
		int magical = armorProperty.getMagicalResistance();
		int weight = armorProperty.getWeight();
		String slot = "mainhand";
		switch (armorProperty.getType()) {
		case "boots":
			slot = "feet";
			break;
		case "leggings":
			slot = "legs";
			break;
		case "chestplate":
			slot = "chest";
			break;
		case "helmet":
			slot = "head";
			break;
		}
		nbti.setInteger(NBTUtil.PHYSICAL_ARMOR, physical);
		nbti.setInteger(NBTUtil.MAGICAL_ARMOR, magical);
		nbti.setInteger(NBTUtil.WEIGHT, weight);
		nbti.setString(NBTUtil.ITEM_TYPE, armorProperty.getType());
		nbti.setString(NBTUtil.MATERIAL, armorProperty.getMaterial());
		nbti.setInteger(NBTUtil.UPDATEVERSION, ItemUpdater.updateVersion);
		NBTCompoundList modifiers = nbti.getCompoundList("AttributeModifiers");
		NBTCompound armor = new NBTContainer();
		armor.setString("Name", "generic.armor");
		armor.setString("AttributeName", "generic.armor");
		armor.setDouble("Amount", 0.0D);
		armor.setInteger("Operation", 0);
		armor.setIntArray("UUID", new int[] { random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt() });
		NBTCompound armorToughness = new NBTContainer();
		armorToughness.setString("Name", "generic.armor_toughness");
		armorToughness.setString("AttributeName", "generic.armor_toughness");
		armorToughness.setDouble("Amount", 0.0D);
		armorToughness.setInteger("Operation", 0);
		armorToughness.setIntArray("UUID",
				new int[] { random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt() });
		NBTCompound knockbackResistance = new NBTContainer();
		knockbackResistance.setString("Name", "generic.knockback_resistance");
		knockbackResistance.setString("AttributeName", "generic.knockback_resistance");
		knockbackResistance.setDouble("Amount", armorProperty.getKnockbackResistance() / 10D);
		knockbackResistance.setInteger("Operation", 0);
		knockbackResistance.setIntArray("UUID",
				new int[] { random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt() });
		modifiers.addCompound(armor);
		modifiers.addCompound(armorToughness);
		modifiers.addCompound(knockbackResistance);
		nbti.setBoolean("Unbreakable", true);
		nbti.setInteger("HideFlags", 6);
		NBTCompound display = nbti.getOrCreateCompound("display");
		NBTUtil.addLore(display,
				new SimpleJSON().add(" ", false, false, false, false, SimpleJSON.Color.WHITE, false).convert());
		NBTUtil.addLore(display,
				new SimpleJSON().add(" ", false, false, false, false, SimpleJSON.Color.WHITE, false).convert());
		NBTUtil.addLore(display, new SimpleJSON()
				.add("item.modifiers." + slot, false, false, false, false, SimpleJSON.Color.GRAY, true).convert());
		NBTUtil.addLore(display, new SimpleJSON().add(" ", false, false, false, false, SimpleJSON.Color.WHITE, false)
				.add("+" + physical + " ", false, false, false, false, SimpleJSON.Color.BLUE, false)
				.add("imagicube.physical.armor", false, false, false, false, SimpleJSON.Color.BLUE, true).convert());
		NBTUtil.addLore(display, new SimpleJSON().add(" ", false, false, false, false, SimpleJSON.Color.WHITE, false)
				.add("+" + magical + " ", false, false, false, false, SimpleJSON.Color.BLUE, false)
				.add("imagicube.magical.armor", false, false, false, false, SimpleJSON.Color.BLUE, true).convert());
		if (armorProperty.getKnockbackResistance() > 0)
			NBTUtil.addLore(display,
					new SimpleJSON().add(" ", false, false, false, false, SimpleJSON.Color.WHITE, false)
							.add("+" + armorProperty.getKnockbackResistance() + " ", false, false, false, false,
									SimpleJSON.Color.BLUE, false)
							.add("attribute.name.generic.knockback_resistance", false, false, false, false,
									SimpleJSON.Color.BLUE, true)
							.convert());
		NBTUtil.addLore(display,
				new SimpleJSON().add(" ", false, false, false, false, SimpleJSON.Color.WHITE, false)
						.add("+" + weight + " ", false, false, false, false, SimpleJSON.Color.BLUE, false)
						.add("imagicube.weight", false, false, false, false, SimpleJSON.Color.BLUE, true).convert());
		Durability.setDurability(nbti, 0, 1);
	}

	public enum CraftType {
		BOOTS("boots", new String[] { "m m", "m m" }), LEGGINGS("leggings", new String[] { "mmm", "m m", "m m" }),
		CHESTPLATE("chestplate", new String[] { "m m", "mmm", "mmm" }), HELMET("helmet", new String[] { "mmm", "m m" });

		public String craftName;
		public String[] lines;

		CraftType(String craftName, String[] lines) {
			this.craftName = craftName;
			this.lines = lines;
		}

		public String[] getCraft() {
			return this.lines;
		}
	}
}