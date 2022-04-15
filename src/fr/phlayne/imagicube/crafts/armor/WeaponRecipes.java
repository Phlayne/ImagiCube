package fr.phlayne.imagicube.crafts.armor;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTCompoundList;
import de.tr7zw.nbtapi.NBTContainer;
import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.Reference;
import fr.phlayne.imagicube.data.Config;
import fr.phlayne.imagicube.item.Durability;
import fr.phlayne.imagicube.item.Tools;
import fr.phlayne.imagicube.item.WeaponProperties;
import fr.phlayne.imagicube.item.WeaponProperty;
import fr.phlayne.imagicube.util.ItemUpdater;
import fr.phlayne.imagicube.util.NBTUtil;
import fr.phlayne.imagicube.util.SimpleJSON;

public class WeaponRecipes {

	public static Random random = new Random();

	public static void init() {
		ItemStack oakSword = setWeaponValues(WeaponProperties.OAK_SWORD);
		ItemStack oakAxe = setWeaponValues(WeaponProperties.OAK_AXE);
		ItemStack oakPickaxe = setWeaponValues(WeaponProperties.OAK_PICKAXE);
		ItemStack oakShovel = setWeaponValues(WeaponProperties.OAK_SHOVEL);
		ItemStack oakHoe = setWeaponValues(WeaponProperties.OAK_HOE);
		addCraft(oakSword, Material.OAK_PLANKS, CraftRecipe.SWORD);
		addCraft(oakAxe, Material.OAK_PLANKS, CraftRecipe.AXE);
		addCraft(oakPickaxe, Material.OAK_PLANKS, CraftRecipe.PICKAXE);
		addCraft(oakShovel, Material.OAK_PLANKS, CraftRecipe.SHOVEL);
		addCraft(oakHoe, Material.OAK_PLANKS, CraftRecipe.HOE);
		if (Config.getConfig().getBoolean("woodToolsVariants")) {
			ItemStack spruceSword = setWeaponValues(WeaponProperties.SPRUCE_SWORD);
			ItemStack spruceAxe = setWeaponValues(WeaponProperties.SPRUCE_AXE);
			ItemStack sprucePickaxe = setWeaponValues(WeaponProperties.SPRUCE_PICKAXE);
			ItemStack spruceShovel = setWeaponValues(WeaponProperties.SPRUCE_SHOVEL);
			ItemStack spruceHoe = setWeaponValues(WeaponProperties.SPRUCE_HOE);
			ItemStack birchSword = setWeaponValues(WeaponProperties.BIRCH_SWORD);
			ItemStack birchAxe = setWeaponValues(WeaponProperties.BIRCH_AXE);
			ItemStack birchPickaxe = setWeaponValues(WeaponProperties.BIRCH_PICKAXE);
			ItemStack birchShovel = setWeaponValues(WeaponProperties.BIRCH_SHOVEL);
			ItemStack birchHoe = setWeaponValues(WeaponProperties.BIRCH_HOE);
			ItemStack jungleSword = setWeaponValues(WeaponProperties.JUNGLE_SWORD);
			ItemStack jungleAxe = setWeaponValues(WeaponProperties.JUNGLE_AXE);
			ItemStack junglePickaxe = setWeaponValues(WeaponProperties.JUNGLE_PICKAXE);
			ItemStack jungleShovel = setWeaponValues(WeaponProperties.JUNGLE_SHOVEL);
			ItemStack jungleHoe = setWeaponValues(WeaponProperties.JUNGLE_HOE);
			ItemStack acaciaSword = setWeaponValues(WeaponProperties.ACACIA_SWORD);
			ItemStack acaciaAxe = setWeaponValues(WeaponProperties.ACACIA_AXE);
			ItemStack acaciaPickaxe = setWeaponValues(WeaponProperties.ACACIA_PICKAXE);
			ItemStack acaciaShovel = setWeaponValues(WeaponProperties.ACACIA_SHOVEL);
			ItemStack acaciaHoe = setWeaponValues(WeaponProperties.ACACIA_HOE);
			ItemStack dark_oakSword = setWeaponValues(WeaponProperties.DARK_OAK_SWORD);
			ItemStack dark_oakAxe = setWeaponValues(WeaponProperties.DARK_OAK_AXE);
			ItemStack dark_oakPickaxe = setWeaponValues(WeaponProperties.DARK_OAK_PICKAXE);
			ItemStack dark_oakShovel = setWeaponValues(WeaponProperties.DARK_OAK_SHOVEL);
			ItemStack dark_oakHoe = setWeaponValues(WeaponProperties.DARK_OAK_HOE);
			ItemStack crimsonSword = setWeaponValues(WeaponProperties.CRIMSON_SWORD);
			ItemStack crimsonAxe = setWeaponValues(WeaponProperties.CRIMSON_AXE);
			ItemStack crimsonPickaxe = setWeaponValues(WeaponProperties.CRIMSON_PICKAXE);
			ItemStack crimsonShovel = setWeaponValues(WeaponProperties.CRIMSON_SHOVEL);
			ItemStack crimsonHoe = setWeaponValues(WeaponProperties.CRIMSON_HOE);
			ItemStack warpedSword = setWeaponValues(WeaponProperties.WARPED_SWORD);
			ItemStack warpedAxe = setWeaponValues(WeaponProperties.WARPED_AXE);
			ItemStack warpedPickaxe = setWeaponValues(WeaponProperties.WARPED_PICKAXE);
			ItemStack warpedShovel = setWeaponValues(WeaponProperties.WARPED_SHOVEL);
			ItemStack warpedHoe = setWeaponValues(WeaponProperties.WARPED_HOE);
			addCraft(spruceSword, Material.SPRUCE_PLANKS, CraftRecipe.SWORD);
			addCraft(spruceAxe, Material.SPRUCE_PLANKS, CraftRecipe.AXE);
			addCraft(sprucePickaxe, Material.SPRUCE_PLANKS, CraftRecipe.PICKAXE);
			addCraft(spruceShovel, Material.SPRUCE_PLANKS, CraftRecipe.SHOVEL);
			addCraft(spruceHoe, Material.SPRUCE_PLANKS, CraftRecipe.HOE);
			addCraft(birchSword, Material.BIRCH_PLANKS, CraftRecipe.SWORD);
			addCraft(birchAxe, Material.BIRCH_PLANKS, CraftRecipe.AXE);
			addCraft(birchPickaxe, Material.BIRCH_PLANKS, CraftRecipe.PICKAXE);
			addCraft(birchShovel, Material.BIRCH_PLANKS, CraftRecipe.SHOVEL);
			addCraft(birchHoe, Material.BIRCH_PLANKS, CraftRecipe.HOE);
			addCraft(jungleSword, Material.JUNGLE_PLANKS, CraftRecipe.SWORD);
			addCraft(jungleAxe, Material.JUNGLE_PLANKS, CraftRecipe.AXE);
			addCraft(junglePickaxe, Material.JUNGLE_PLANKS, CraftRecipe.PICKAXE);
			addCraft(jungleShovel, Material.JUNGLE_PLANKS, CraftRecipe.SHOVEL);
			addCraft(jungleHoe, Material.JUNGLE_PLANKS, CraftRecipe.HOE);
			addCraft(acaciaSword, Material.ACACIA_PLANKS, CraftRecipe.SWORD);
			addCraft(acaciaAxe, Material.ACACIA_PLANKS, CraftRecipe.AXE);
			addCraft(acaciaPickaxe, Material.ACACIA_PLANKS, CraftRecipe.PICKAXE);
			addCraft(acaciaShovel, Material.ACACIA_PLANKS, CraftRecipe.SHOVEL);
			addCraft(acaciaHoe, Material.ACACIA_PLANKS, CraftRecipe.HOE);
			addCraft(dark_oakSword, Material.DARK_OAK_PLANKS, CraftRecipe.SWORD);
			addCraft(dark_oakAxe, Material.DARK_OAK_PLANKS, CraftRecipe.AXE);
			addCraft(dark_oakPickaxe, Material.DARK_OAK_PLANKS, CraftRecipe.PICKAXE);
			addCraft(dark_oakShovel, Material.DARK_OAK_PLANKS, CraftRecipe.SHOVEL);
			addCraft(dark_oakHoe, Material.DARK_OAK_PLANKS, CraftRecipe.HOE);
			addCraft(crimsonSword, Material.CRIMSON_PLANKS, CraftRecipe.SWORD);
			addCraft(crimsonAxe, Material.CRIMSON_PLANKS, CraftRecipe.AXE);
			addCraft(crimsonPickaxe, Material.CRIMSON_PLANKS, CraftRecipe.PICKAXE);
			addCraft(crimsonShovel, Material.CRIMSON_PLANKS, CraftRecipe.SHOVEL);
			addCraft(crimsonHoe, Material.CRIMSON_PLANKS, CraftRecipe.HOE);
			addCraft(warpedSword, Material.WARPED_PLANKS, CraftRecipe.SWORD);
			addCraft(warpedAxe, Material.WARPED_PLANKS, CraftRecipe.AXE);
			addCraft(warpedPickaxe, Material.WARPED_PLANKS, CraftRecipe.PICKAXE);
			addCraft(warpedShovel, Material.WARPED_PLANKS, CraftRecipe.SHOVEL);
			addCraft(warpedHoe, Material.WARPED_PLANKS, CraftRecipe.HOE);
		}

		ItemStack stoneSword = setWeaponValues(WeaponProperties.STONE_SWORD);
		ItemStack stoneAxe = setWeaponValues(WeaponProperties.STONE_AXE);
		ItemStack stonePickaxe = setWeaponValues(WeaponProperties.STONE_PICKAXE);
		ItemStack stoneShovel = setWeaponValues(WeaponProperties.STONE_SHOVEL);
		ItemStack stoneHoe = setWeaponValues(WeaponProperties.STONE_HOE);
		addCraft(stoneSword, Material.COBBLESTONE, CraftRecipe.SWORD);
		addCraft(stoneAxe, Material.COBBLESTONE, CraftRecipe.AXE);
		addCraft(stonePickaxe, Material.COBBLESTONE, CraftRecipe.PICKAXE);
		addCraft(stoneShovel, Material.COBBLESTONE, CraftRecipe.SHOVEL);
		addCraft(stoneHoe, Material.COBBLESTONE, CraftRecipe.HOE);

		ItemStack ironSword = setWeaponValues(WeaponProperties.IRON_SWORD);
		ItemStack ironAxe = setWeaponValues(WeaponProperties.IRON_AXE);
		ItemStack ironPickaxe = setWeaponValues(WeaponProperties.IRON_PICKAXE);
		ItemStack ironShovel = setWeaponValues(WeaponProperties.IRON_SHOVEL);
		ItemStack ironHoe = setWeaponValues(WeaponProperties.IRON_HOE);
		addCraft(ironSword, Material.IRON_INGOT, CraftRecipe.SWORD);
		addCraft(ironAxe, Material.IRON_INGOT, CraftRecipe.AXE);
		addCraft(ironPickaxe, Material.IRON_INGOT, CraftRecipe.PICKAXE);
		addCraft(ironShovel, Material.IRON_INGOT, CraftRecipe.SHOVEL);
		addCraft(ironHoe, Material.IRON_INGOT, CraftRecipe.HOE);

		ItemStack goldenSword = setWeaponValues(WeaponProperties.GOLDEN_SWORD);
		ItemStack goldenAxe = setWeaponValues(WeaponProperties.GOLDEN_AXE);
		ItemStack goldenPickaxe = setWeaponValues(WeaponProperties.GOLDEN_PICKAXE);
		ItemStack goldenShovel = setWeaponValues(WeaponProperties.GOLDEN_SHOVEL);
		ItemStack goldenHoe = setWeaponValues(WeaponProperties.GOLDEN_HOE);
		addCraft(goldenSword, Material.GOLD_INGOT, CraftRecipe.SWORD);
		addCraft(goldenAxe, Material.GOLD_INGOT, CraftRecipe.AXE);
		addCraft(goldenPickaxe, Material.GOLD_INGOT, CraftRecipe.PICKAXE);
		addCraft(goldenShovel, Material.GOLD_INGOT, CraftRecipe.SHOVEL);
		addCraft(goldenHoe, Material.GOLD_INGOT, CraftRecipe.HOE);

		ItemStack diamondSword = setWeaponValues(WeaponProperties.DIAMOND_SWORD);
		ItemStack diamondAxe = setWeaponValues(WeaponProperties.DIAMOND_AXE);
		ItemStack diamondPickaxe = setWeaponValues(WeaponProperties.DIAMOND_PICKAXE);
		ItemStack diamondShovel = setWeaponValues(WeaponProperties.DIAMOND_SHOVEL);
		ItemStack diamondHoe = setWeaponValues(WeaponProperties.DIAMOND_HOE);
		addCraft(diamondSword, Material.DIAMOND, CraftRecipe.SWORD);
		addCraft(diamondAxe, Material.DIAMOND, CraftRecipe.AXE);
		addCraft(diamondPickaxe, Material.DIAMOND, CraftRecipe.PICKAXE);
		addCraft(diamondShovel, Material.DIAMOND, CraftRecipe.SHOVEL);
		addCraft(diamondHoe, Material.DIAMOND, CraftRecipe.HOE);

		ItemStack netheriteSword = setWeaponValues(WeaponProperties.NETHERITE_SWORD);
		ItemStack netheriteAxe = setWeaponValues(WeaponProperties.NETHERITE_AXE);
		ItemStack netheritePickaxe = setWeaponValues(WeaponProperties.NETHERITE_PICKAXE);
		ItemStack netheriteShovel = setWeaponValues(WeaponProperties.NETHERITE_SHOVEL);
		ItemStack netheriteHoe = setWeaponValues(WeaponProperties.NETHERITE_HOE);
		addCraft(netheriteSword, Material.NETHERITE_INGOT, CraftRecipe.SWORD);
		addCraft(netheriteAxe, Material.NETHERITE_INGOT, CraftRecipe.AXE);
		addCraft(netheritePickaxe, Material.NETHERITE_INGOT, CraftRecipe.PICKAXE);
		addCraft(netheriteShovel, Material.NETHERITE_INGOT, CraftRecipe.SHOVEL);
		addCraft(netheriteHoe, Material.NETHERITE_INGOT, CraftRecipe.HOE);

		if (Config.getConfig().getBoolean("prismarineTools")) {
			ItemStack prismarineSword = setWeaponValues(WeaponProperties.PRISMARINE_SWORD);
			ItemStack prismarineAxe = setWeaponValues(WeaponProperties.PRISMARINE_AXE);
			ItemStack prismarinePickaxe = setWeaponValues(WeaponProperties.PRISMARINE_PICKAXE);
			ItemStack prismarineShovel = setWeaponValues(WeaponProperties.PRISMARINE_SHOVEL);
			ItemStack prismarineHoe = setWeaponValues(WeaponProperties.PRISMARINE_HOE);
			addCraft(prismarineSword, Material.PRISMARINE, CraftRecipe.SWORD);
			addCraft(prismarineAxe, Material.PRISMARINE, CraftRecipe.AXE);
			addCraft(prismarinePickaxe, Material.PRISMARINE, CraftRecipe.PICKAXE);
			addCraft(prismarineShovel, Material.PRISMARINE, CraftRecipe.SHOVEL);
			addCraft(prismarineHoe, Material.PRISMARINE, CraftRecipe.HOE);
			if (Config.getConfig().getBoolean("craftableTrident")) {
				ItemStack trident = setWeaponValues(WeaponProperties.TRIDENT);
				addCraft(trident, Material.PRISMARINE, CraftRecipe.TRIDENT, "trident");
			}
		}

		if (Config.getConfig().getBoolean("stoneToolsVariants")) {
			ItemStack blackstoneSword = setWeaponValues(WeaponProperties.BLACKSTONE_SWORD);
			ItemStack blackstoneAxe = setWeaponValues(WeaponProperties.BLACKSTONE_AXE);
			ItemStack blackstonePickaxe = setWeaponValues(WeaponProperties.BLACKSTONE_PICKAXE);
			ItemStack blackstoneShovel = setWeaponValues(WeaponProperties.BLACKSTONE_SHOVEL);
			ItemStack blackstoneHoe = setWeaponValues(WeaponProperties.BLACKSTONE_HOE);
			addCraft(blackstoneSword, Material.BLACKSTONE, CraftRecipe.SWORD);
			addCraft(blackstoneAxe, Material.BLACKSTONE, CraftRecipe.AXE);
			addCraft(blackstonePickaxe, Material.BLACKSTONE, CraftRecipe.PICKAXE);
			addCraft(blackstoneShovel, Material.BLACKSTONE, CraftRecipe.SHOVEL);
			addCraft(blackstoneHoe, Material.BLACKSTONE, CraftRecipe.HOE);
		}
	}

	public static void addCraft(ItemStack result, Material ingredient, CraftType craftType) {
		addCraft(result, ingredient, craftType, ingredient.getKey().getKey() + "_" + craftType.getCraftName());
	}

	public static void addCraft(ItemStack result, Material ingredient, CraftType craftType, String craftName) {
		if (!craftType.hasMirrorCraft()) {
			NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin(Reference.PLUGIN_NAME),
					craftName);
			ShapedRecipe recipe = new ShapedRecipe(key, result);
			recipe.shape(craftType.getCraft());
			if (ingredient.equals(Material.OAK_PLANKS) && !Config.getConfig().getBoolean("woodToolsVariants"))
				recipe.setIngredient('I', new RecipeChoice.MaterialChoice(Tag.PLANKS));
			else
				recipe.setIngredient('I', ingredient);
			recipe.setIngredient('|', Material.STICK);
			Bukkit.addRecipe(recipe);
		} else {
			NamespacedKey leftKey = new NamespacedKey(Bukkit.getPluginManager().getPlugin(Reference.PLUGIN_NAME),
					craftName + "_left");
			NamespacedKey rightKey = new NamespacedKey(Bukkit.getPluginManager().getPlugin(Reference.PLUGIN_NAME),
					craftName + "_right");
			ShapedRecipe rightRecipe = new ShapedRecipe(rightKey, result);
			rightRecipe.shape(craftType.getCraft());
			rightRecipe.setIngredient('I', ingredient);
			rightRecipe.setIngredient('|', Material.STICK);
			ShapedRecipe leftRecipe = new ShapedRecipe(leftKey, result);
			leftRecipe.shape(craftType.getSecondCraft());
			leftRecipe.setIngredient('I', ingredient);
			leftRecipe.setIngredient('|', Material.STICK);
			Bukkit.addRecipe(rightRecipe);
			Bukkit.addRecipe(leftRecipe);
		}
	}

	public static ItemStack setWeaponValues(WeaponProperty weaponProperty) {
		return setWeaponValues(new ItemStack(weaponProperty.getBukkitMaterial()), weaponProperty);
	}

	public static ItemStack setWeaponValues(ItemStack item, WeaponProperty weaponProperty) {
		NBTItem nbti = new NBTItem(item);
		setWeaponValues(nbti, weaponProperty);
		return nbti.getItem();
	}

	public static void setWeaponValues(NBTItem nbti, WeaponProperty weaponProperty) {
		nbti.setInteger("CustomModelData", weaponProperty.getCustomModelData());
		nbti.setBoolean("Unbreakable", true);
		nbti.setInteger("HideFlags", 6);
		nbti.setString(NBTUtil.ITEM_TYPE, weaponProperty.getType());
		nbti.setString(NBTUtil.MATERIAL, weaponProperty.getMaterial());
		NBTCompoundList modifiers = nbti.getCompoundList("AttributeModifiers");
		NBTCompound attackDamage = new NBTContainer();
		attackDamage.setString("Slot", "mainhand");
		attackDamage.setString("AttributeName", "generic.attack_damage");
		attackDamage.setString("Name", "imagicube.attack_damage");
		attackDamage.setDouble("Amount", weaponProperty.getDamage());
		attackDamage.setInteger("Operation", 0);
		attackDamage.setIntArray("UUID",
				new int[] { random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt() });
		NBTCompound attackSpeed = new NBTContainer();
		attackSpeed.setString("Slot", "mainhand");
		attackSpeed.setString("AttributeName", "generic.attack_speed");
		attackSpeed.setString("Name", "imagicube.attack_speed");
		attackSpeed.setDouble("Amount", Math.round((weaponProperty.getAttackSpeed() - 4.0D) * 1000D) / 1000D);
		attackSpeed.setInteger("Operation", 0);
		attackSpeed.setIntArray("UUID",
				new int[] { random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt() });
		modifiers.addCompound(attackDamage);
		modifiers.addCompound(attackSpeed);
		NBTCompound display = nbti.getOrCreateCompound("display");
		NBTUtil.addLore(display,
				new SimpleJSON().add(" ", false, false, false, false, SimpleJSON.Color.WHITE, false).convert());
		NBTUtil.addLore(display,
				new SimpleJSON().add(" ", false, false, false, false, SimpleJSON.Color.WHITE, false).convert());
		NBTUtil.addLore(display, new SimpleJSON()
				.add("item.modifiers.mainhand", false, false, false, false, SimpleJSON.Color.GRAY, true).convert());
		NBTUtil.addLore(display, new SimpleJSON().add(" ", false, false, false, false, SimpleJSON.Color.WHITE, false)
				.add("+" + Math.round((weaponProperty.getAttackSpeed()) * 1000D) / 1000D + " ", false, false, false,
						false, SimpleJSON.Color.BLUE, false)
				.add("imagicube.attack.speed", false, false, false, false, SimpleJSON.Color.BLUE, true).convert());
		NBTUtil.addLore(display, new SimpleJSON().add(" ", false, false, false, false, SimpleJSON.Color.WHITE, false)
				.add("+" + weaponProperty.getDamage() + " ", false, false, false, false, SimpleJSON.Color.BLUE, false)
				.add("imagicube.attack.damage", false, false, false, false, SimpleJSON.Color.BLUE, true).convert());
		String weaponName = getWeaponName(weaponProperty);
		if (weaponName != null)
			display.setString("Name", new SimpleJSON()
					.add(weaponName, false, false, false, false, SimpleJSON.Color.WHITE, true).convert());
		nbti.setInteger(NBTUtil.UPDATEVERSION, ItemUpdater.updateVersion);
		Durability.setDurability(nbti, 0, 1);
	}

	public static String getWeaponName(WeaponProperty weaponProperty) {
		if ((weaponProperty.getMaterial().equals("prismarine") && !weaponProperty.getType().equals("trident"))
				|| Arrays.asList("blackstone", "oak", "spruce", "birch", "jungle", "acacia", "dark_oak", "crimson",
						"warped").contains(weaponProperty.getMaterial())
				|| !Arrays.asList(Tools.values()).stream().map(Tools::getName).collect(Collectors.toList())
						.contains(weaponProperty.getType())) {
			boolean woodToolsActive = Config.getConfig().getBoolean("woodToolsVariants");
			String material = weaponProperty.getMaterial();
			if (!woodToolsActive && material.equals("oak"))
				material = "wood";
			return "item.imagicube." + material + "_" + weaponProperty.getType();
		} else
			return null;
	}

	public enum CraftRecipe implements CraftType {
		SWORD("sword", new String[] { "I", "I", "|" }, false), //
		AXE("axe", new String[] { "II", "|I", "| " }, true), //
		PICKAXE("pickaxe", new String[] { "III", " | ", " | " }, false), //
		SHOVEL("shovel", new String[] { "I", "|", "|" }, false), //
		HOE("hoe", new String[] { "II", "| ", "| " }, true), //
		TRIDENT("trident", new String[] { " II", " |I", "|  " }, true);//

		private String craftName;
		private String[] lines;
		boolean hasMirrorCraft;

		CraftRecipe(String craftName, String[] lines, boolean hasMirrorCraft) {
			this.craftName = craftName;
			this.lines = lines;
			this.hasMirrorCraft = hasMirrorCraft;
		}

		public boolean hasMirrorCraft() {
			return this.hasMirrorCraft;
		}

		public String[] getCraft() {
			return this.lines;
		}

		public String getCraftName() {
			return this.craftName;
		}

		public String[] getSecondCraft() {
			String[] newLines = new String[this.lines.length];
			for (int i = 0; i < this.lines.length; i++)
				newLines[i] = new StringBuilder(this.lines[i]).reverse().toString();
			return newLines;
		}
	}
}