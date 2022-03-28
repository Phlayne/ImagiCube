package fr.phlayne.imagicube.crafts.armor;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTCompoundList;
import de.tr7zw.nbtapi.NBTContainer;
import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.ImagiCube;
import fr.phlayne.imagicube.item.ArmorProperties;
import fr.phlayne.imagicube.item.ArmorProperty;
import fr.phlayne.imagicube.item.Durability;
import fr.phlayne.imagicube.util.ItemUpdater;
import fr.phlayne.imagicube.util.NBTUtil;
import fr.phlayne.imagicube.util.SimpleJSON;

public class ArmorRecipes {

	public static Random random = new Random();

	public static void init(ImagiCube plugin) {
		ItemStack leatherBoots = setArmorValues(plugin, new ItemStack(Material.LEATHER_BOOTS), ArmorProperties.LEATHER_BOOTS);
		ItemStack leatherLeggings = setArmorValues(plugin, new ItemStack(Material.LEATHER_LEGGINGS),
				ArmorProperties.LEATHER_LEGGINGS);
		ItemStack leatherChestplate = setArmorValues(plugin, new ItemStack(Material.LEATHER_CHESTPLATE),
				ArmorProperties.LEATHER_CHESTPLATE);
		ItemStack leatherHelmet = setArmorValues(plugin, new ItemStack(Material.LEATHER_HELMET), ArmorProperties.LEATHER_HELMET);
		addCraft(plugin, leatherBoots, Material.LEATHER, CraftType.BOOTS);
		addCraft(plugin, leatherLeggings, Material.LEATHER, CraftType.LEGGINGS);
		addCraft(plugin, leatherChestplate, Material.LEATHER, CraftType.CHESTPLATE);
		addCraft(plugin, leatherHelmet, Material.LEATHER, CraftType.HELMET);

		ItemStack chainBoots = setArmorValues(plugin, new ItemStack(Material.CHAINMAIL_BOOTS), ArmorProperties.CHAINMAIL_BOOTS);
		ItemStack chainLeggings = setArmorValues(plugin, new ItemStack(Material.CHAINMAIL_LEGGINGS),
				ArmorProperties.CHAINMAIL_LEGGINGS);
		ItemStack chainChestplate = setArmorValues(plugin, new ItemStack(Material.CHAINMAIL_CHESTPLATE),
				ArmorProperties.CHAINMAIL_CHESTPLATE);
		ItemStack chainHelmet = setArmorValues(plugin, new ItemStack(Material.CHAINMAIL_HELMET),
				ArmorProperties.CHAINMAIL_HELMET);
		addCraft(plugin, chainBoots, Material.CHAIN, CraftType.BOOTS);
		addCraft(plugin, chainLeggings, Material.CHAIN, CraftType.LEGGINGS);
		addCraft(plugin, chainChestplate, Material.CHAIN, CraftType.CHESTPLATE);
		addCraft(plugin, chainHelmet, Material.CHAIN, CraftType.HELMET);

		ItemStack ironBoots = setArmorValues(plugin, new ItemStack(Material.IRON_BOOTS), ArmorProperties.IRON_BOOTS);
		ItemStack ironLeggings = setArmorValues(plugin, new ItemStack(Material.IRON_LEGGINGS), ArmorProperties.IRON_LEGGINGS);
		ItemStack ironChestplate = setArmorValues(plugin, new ItemStack(Material.IRON_CHESTPLATE),
				ArmorProperties.IRON_CHESTPLATE);
		ItemStack ironHelmet = setArmorValues(plugin, new ItemStack(Material.IRON_HELMET), ArmorProperties.IRON_HELMET);
		addCraft(plugin, ironBoots, Material.IRON_INGOT, CraftType.BOOTS);
		addCraft(plugin, ironLeggings, Material.IRON_INGOT, CraftType.LEGGINGS);
		addCraft(plugin, ironChestplate, Material.IRON_INGOT, CraftType.CHESTPLATE);
		addCraft(plugin, ironHelmet, Material.IRON_INGOT, CraftType.HELMET);

		ItemStack goldBoots = setArmorValues(plugin, new ItemStack(Material.GOLDEN_BOOTS), ArmorProperties.GOLDEN_BOOTS);
		ItemStack goldLeggings = setArmorValues(plugin, new ItemStack(Material.GOLDEN_LEGGINGS), ArmorProperties.GOLDEN_LEGGINGS);
		ItemStack goldChestplate = setArmorValues(plugin, new ItemStack(Material.GOLDEN_CHESTPLATE),
				ArmorProperties.GOLDEN_CHESTPLATE);
		ItemStack goldHelmet = setArmorValues(plugin, new ItemStack(Material.GOLDEN_HELMET), ArmorProperties.GOLDEN_HELMET);
		addCraft(plugin, goldBoots, Material.GOLD_INGOT, CraftType.BOOTS);
		addCraft(plugin, goldLeggings, Material.GOLD_INGOT, CraftType.LEGGINGS);
		addCraft(plugin, goldChestplate, Material.GOLD_INGOT, CraftType.CHESTPLATE);
		addCraft(plugin, goldHelmet, Material.GOLD_INGOT, CraftType.HELMET);

		ItemStack diamondBoots = setArmorValues(plugin, new ItemStack(Material.DIAMOND_BOOTS), ArmorProperties.DIAMOND_BOOTS);
		ItemStack diamondLeggings = setArmorValues(plugin, new ItemStack(Material.DIAMOND_LEGGINGS),
				ArmorProperties.DIAMOND_LEGGINGS);
		ItemStack diamondChestplate = setArmorValues(plugin, new ItemStack(Material.DIAMOND_CHESTPLATE),
				ArmorProperties.DIAMOND_CHESTPLATE);
		ItemStack diamondHelmet = setArmorValues(plugin, new ItemStack(Material.DIAMOND_HELMET), ArmorProperties.DIAMOND_HELMET);
		addCraft(plugin, diamondBoots, Material.DIAMOND, CraftType.BOOTS);
		addCraft(plugin, diamondLeggings, Material.DIAMOND, CraftType.LEGGINGS);
		addCraft(plugin, diamondChestplate, Material.DIAMOND, CraftType.CHESTPLATE);
		addCraft(plugin, diamondHelmet, Material.DIAMOND, CraftType.HELMET);

		ItemStack turtleHelmet = setArmorValues(plugin, new ItemStack(Material.TURTLE_HELMET), ArmorProperties.TURTLE_HELMET);
		addCraft(plugin, turtleHelmet, Material.SCUTE, CraftType.HELMET);

	}

	public static void addCraft(ImagiCube plugin, ItemStack result, Material ingredient, CraftType craftType) {
		NamespacedKey key = new NamespacedKey(plugin,
				ingredient.getKey().getKey() + "_" + craftType.craftName);
		ShapedRecipe recipe = new ShapedRecipe(key, result);
		recipe.shape(craftType.getCraft());
		recipe.setIngredient('m', ingredient);
		Bukkit.addRecipe(recipe);
	}

	public static ItemStack setArmorValues(ImagiCube plugin, ItemStack item, ArmorProperty armorProperty) {
		NBTItem nbti = new NBTItem(item);
		setArmorValues(plugin, nbti, armorProperty);
		return nbti.getItem();
	}
	
	public static void setArmorValues(ImagiCube plugin, NBTItem nbti, ArmorProperty armorProperty) {
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
		Durability.setDurability(nbti, 0, plugin);
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