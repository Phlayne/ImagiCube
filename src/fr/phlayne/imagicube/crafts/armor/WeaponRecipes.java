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
import fr.phlayne.imagicube.Reference;
import fr.phlayne.imagicube.item.Durability;
import fr.phlayne.imagicube.item.WeaponProperty;
import fr.phlayne.imagicube.util.ItemUpdater;
import fr.phlayne.imagicube.util.NBTUtil;
import fr.phlayne.imagicube.util.SimpleJSON;

public class WeaponRecipes {

	public static Random random = new Random();

	public static void init(ImagiCube plugin) {
		ItemStack oakSword = setWeaponValues(WeaponProperty.OAK_SWORD);
		ItemStack oakAxe = setWeaponValues(WeaponProperty.OAK_AXE);
		ItemStack oakPickaxe = setWeaponValues(WeaponProperty.OAK_PICKAXE);
		ItemStack oakShovel = setWeaponValues(WeaponProperty.OAK_SHOVEL);
		ItemStack oakHoe = setWeaponValues(WeaponProperty.OAK_HOE);
		addCraft(plugin, oakSword, Material.OAK_PLANKS, CraftType.SWORD);
		addCraft(plugin, oakAxe, Material.OAK_PLANKS, CraftType.AXE);
		addCraft(plugin, oakPickaxe, Material.OAK_PLANKS, CraftType.PICKAXE);
		addCraft(plugin, oakShovel, Material.OAK_PLANKS, CraftType.SHOVEL);
		addCraft(plugin, oakHoe, Material.OAK_PLANKS, CraftType.HOE);
		if (plugin.getConfig().getBoolean("woodToolsVariants")) {
			ItemStack spruceSword = setWeaponValues(WeaponProperty.SPRUCE_SWORD);
			ItemStack spruceAxe = setWeaponValues(WeaponProperty.SPRUCE_AXE);
			ItemStack sprucePickaxe = setWeaponValues(WeaponProperty.SPRUCE_PICKAXE);
			ItemStack spruceShovel = setWeaponValues(WeaponProperty.SPRUCE_SHOVEL);
			ItemStack spruceHoe = setWeaponValues(WeaponProperty.SPRUCE_HOE);
			ItemStack birchSword = setWeaponValues(WeaponProperty.BIRCH_SWORD);
			ItemStack birchAxe = setWeaponValues(WeaponProperty.BIRCH_AXE);
			ItemStack birchPickaxe = setWeaponValues(WeaponProperty.BIRCH_PICKAXE);
			ItemStack birchShovel = setWeaponValues(WeaponProperty.BIRCH_SHOVEL);
			ItemStack birchHoe = setWeaponValues(WeaponProperty.BIRCH_HOE);
			ItemStack jungleSword = setWeaponValues(WeaponProperty.JUNGLE_SWORD);
			ItemStack jungleAxe = setWeaponValues(WeaponProperty.JUNGLE_AXE);
			ItemStack junglePickaxe = setWeaponValues(WeaponProperty.JUNGLE_PICKAXE);
			ItemStack jungleShovel = setWeaponValues(WeaponProperty.JUNGLE_SHOVEL);
			ItemStack jungleHoe = setWeaponValues(WeaponProperty.JUNGLE_HOE);
			ItemStack acaciaSword = setWeaponValues(WeaponProperty.ACACIA_SWORD);
			ItemStack acaciaAxe = setWeaponValues(WeaponProperty.ACACIA_AXE);
			ItemStack acaciaPickaxe = setWeaponValues(WeaponProperty.ACACIA_PICKAXE);
			ItemStack acaciaShovel = setWeaponValues(WeaponProperty.ACACIA_SHOVEL);
			ItemStack acaciaHoe = setWeaponValues(WeaponProperty.ACACIA_HOE);
			ItemStack dark_oakSword = setWeaponValues(WeaponProperty.DARK_OAK_SWORD);
			ItemStack dark_oakAxe = setWeaponValues(WeaponProperty.DARK_OAK_AXE);
			ItemStack dark_oakPickaxe = setWeaponValues(WeaponProperty.DARK_OAK_PICKAXE);
			ItemStack dark_oakShovel = setWeaponValues(WeaponProperty.DARK_OAK_SHOVEL);
			ItemStack dark_oakHoe = setWeaponValues(WeaponProperty.DARK_OAK_HOE);
			ItemStack crimsonSword = setWeaponValues(WeaponProperty.CRIMSON_SWORD);
			ItemStack crimsonAxe = setWeaponValues(WeaponProperty.CRIMSON_AXE);
			ItemStack crimsonPickaxe = setWeaponValues(WeaponProperty.CRIMSON_PICKAXE);
			ItemStack crimsonShovel = setWeaponValues(WeaponProperty.CRIMSON_SHOVEL);
			ItemStack crimsonHoe = setWeaponValues(WeaponProperty.CRIMSON_HOE);
			ItemStack warpedSword = setWeaponValues(WeaponProperty.WARPED_SWORD);
			ItemStack warpedAxe = setWeaponValues(WeaponProperty.WARPED_AXE);
			ItemStack warpedPickaxe = setWeaponValues(WeaponProperty.WARPED_PICKAXE);
			ItemStack warpedShovel = setWeaponValues(WeaponProperty.WARPED_SHOVEL);
			ItemStack warpedHoe = setWeaponValues(WeaponProperty.WARPED_HOE);
			addCraft(plugin, spruceSword, Material.SPRUCE_PLANKS, CraftType.SWORD);
			addCraft(plugin, spruceAxe, Material.SPRUCE_PLANKS, CraftType.AXE);
			addCraft(plugin, sprucePickaxe, Material.SPRUCE_PLANKS, CraftType.PICKAXE);
			addCraft(plugin, spruceShovel, Material.SPRUCE_PLANKS, CraftType.SHOVEL);
			addCraft(plugin, spruceHoe, Material.SPRUCE_PLANKS, CraftType.HOE);
			addCraft(plugin, birchSword, Material.BIRCH_PLANKS, CraftType.SWORD);
			addCraft(plugin, birchAxe, Material.BIRCH_PLANKS, CraftType.AXE);
			addCraft(plugin, birchPickaxe, Material.BIRCH_PLANKS, CraftType.PICKAXE);
			addCraft(plugin, birchShovel, Material.BIRCH_PLANKS, CraftType.SHOVEL);
			addCraft(plugin, birchHoe, Material.BIRCH_PLANKS, CraftType.HOE);
			addCraft(plugin, jungleSword, Material.JUNGLE_PLANKS, CraftType.SWORD);
			addCraft(plugin, jungleAxe, Material.JUNGLE_PLANKS, CraftType.AXE);
			addCraft(plugin, junglePickaxe, Material.JUNGLE_PLANKS, CraftType.PICKAXE);
			addCraft(plugin, jungleShovel, Material.JUNGLE_PLANKS, CraftType.SHOVEL);
			addCraft(plugin, jungleHoe, Material.JUNGLE_PLANKS, CraftType.HOE);
			addCraft(plugin, acaciaSword, Material.ACACIA_PLANKS, CraftType.SWORD);
			addCraft(plugin, acaciaAxe, Material.ACACIA_PLANKS, CraftType.AXE);
			addCraft(plugin, acaciaPickaxe, Material.ACACIA_PLANKS, CraftType.PICKAXE);
			addCraft(plugin, acaciaShovel, Material.ACACIA_PLANKS, CraftType.SHOVEL);
			addCraft(plugin, acaciaHoe, Material.ACACIA_PLANKS, CraftType.HOE);
			addCraft(plugin, dark_oakSword, Material.DARK_OAK_PLANKS, CraftType.SWORD);
			addCraft(plugin, dark_oakAxe, Material.DARK_OAK_PLANKS, CraftType.AXE);
			addCraft(plugin, dark_oakPickaxe, Material.DARK_OAK_PLANKS, CraftType.PICKAXE);
			addCraft(plugin, dark_oakShovel, Material.DARK_OAK_PLANKS, CraftType.SHOVEL);
			addCraft(plugin, dark_oakHoe, Material.DARK_OAK_PLANKS, CraftType.HOE);
			addCraft(plugin, crimsonSword, Material.CRIMSON_PLANKS, CraftType.SWORD);
			addCraft(plugin, crimsonAxe, Material.CRIMSON_PLANKS, CraftType.AXE);
			addCraft(plugin, crimsonPickaxe, Material.CRIMSON_PLANKS, CraftType.PICKAXE);
			addCraft(plugin, crimsonShovel, Material.CRIMSON_PLANKS, CraftType.SHOVEL);
			addCraft(plugin, crimsonHoe, Material.CRIMSON_PLANKS, CraftType.HOE);
			addCraft(plugin, warpedSword, Material.WARPED_PLANKS, CraftType.SWORD);
			addCraft(plugin, warpedAxe, Material.WARPED_PLANKS, CraftType.AXE);
			addCraft(plugin, warpedPickaxe, Material.WARPED_PLANKS, CraftType.PICKAXE);
			addCraft(plugin, warpedShovel, Material.WARPED_PLANKS, CraftType.SHOVEL);
			addCraft(plugin, warpedHoe, Material.WARPED_PLANKS, CraftType.HOE);
		}

		ItemStack stoneSword = setWeaponValues(WeaponProperty.STONE_SWORD);
		ItemStack stoneAxe = setWeaponValues(WeaponProperty.STONE_AXE);
		ItemStack stonePickaxe = setWeaponValues(WeaponProperty.STONE_PICKAXE);
		ItemStack stoneShovel = setWeaponValues(WeaponProperty.STONE_SHOVEL);
		ItemStack stoneHoe = setWeaponValues(WeaponProperty.STONE_HOE);
		addCraft(plugin, stoneSword, Material.COBBLESTONE, CraftType.SWORD);
		addCraft(plugin, stoneAxe, Material.COBBLESTONE, CraftType.AXE);
		addCraft(plugin, stonePickaxe, Material.COBBLESTONE, CraftType.PICKAXE);
		addCraft(plugin, stoneShovel, Material.COBBLESTONE, CraftType.SHOVEL);
		addCraft(plugin, stoneHoe, Material.COBBLESTONE, CraftType.HOE);

		ItemStack ironSword = setWeaponValues(WeaponProperty.IRON_SWORD);
		ItemStack ironAxe = setWeaponValues(WeaponProperty.IRON_AXE);
		ItemStack ironPickaxe = setWeaponValues(WeaponProperty.IRON_PICKAXE);
		ItemStack ironShovel = setWeaponValues(WeaponProperty.IRON_SHOVEL);
		ItemStack ironHoe = setWeaponValues(WeaponProperty.IRON_HOE);
		addCraft(plugin, ironSword, Material.IRON_INGOT, CraftType.SWORD);
		addCraft(plugin, ironAxe, Material.IRON_INGOT, CraftType.AXE);
		addCraft(plugin, ironPickaxe, Material.IRON_INGOT, CraftType.PICKAXE);
		addCraft(plugin, ironShovel, Material.IRON_INGOT, CraftType.SHOVEL);
		addCraft(plugin, ironHoe, Material.IRON_INGOT, CraftType.HOE);

		ItemStack goldenSword = setWeaponValues(WeaponProperty.GOLDEN_SWORD);
		ItemStack goldenAxe = setWeaponValues(WeaponProperty.GOLDEN_AXE);
		ItemStack goldenPickaxe = setWeaponValues(WeaponProperty.GOLDEN_PICKAXE);
		ItemStack goldenShovel = setWeaponValues(WeaponProperty.GOLDEN_SHOVEL);
		ItemStack goldenHoe = setWeaponValues(WeaponProperty.GOLDEN_HOE);
		addCraft(plugin, goldenSword, Material.GOLD_INGOT, CraftType.SWORD);
		addCraft(plugin, goldenAxe, Material.GOLD_INGOT, CraftType.AXE);
		addCraft(plugin, goldenPickaxe, Material.GOLD_INGOT, CraftType.PICKAXE);
		addCraft(plugin, goldenShovel, Material.GOLD_INGOT, CraftType.SHOVEL);
		addCraft(plugin, goldenHoe, Material.GOLD_INGOT, CraftType.HOE);

		ItemStack diamondSword = setWeaponValues(WeaponProperty.DIAMOND_SWORD);
		ItemStack diamondAxe = setWeaponValues(WeaponProperty.DIAMOND_AXE);
		ItemStack diamondPickaxe = setWeaponValues(WeaponProperty.DIAMOND_PICKAXE);
		ItemStack diamondShovel = setWeaponValues(WeaponProperty.DIAMOND_SHOVEL);
		ItemStack diamondHoe = setWeaponValues(WeaponProperty.DIAMOND_HOE);
		addCraft(plugin, diamondSword, Material.DIAMOND, CraftType.SWORD);
		addCraft(plugin, diamondAxe, Material.DIAMOND, CraftType.AXE);
		addCraft(plugin, diamondPickaxe, Material.DIAMOND, CraftType.PICKAXE);
		addCraft(plugin, diamondShovel, Material.DIAMOND, CraftType.SHOVEL);
		addCraft(plugin, diamondHoe, Material.DIAMOND, CraftType.HOE);

		ItemStack netheriteSword = setWeaponValues(WeaponProperty.NETHERITE_SWORD);
		ItemStack netheriteAxe = setWeaponValues(WeaponProperty.NETHERITE_AXE);
		ItemStack netheritePickaxe = setWeaponValues(WeaponProperty.NETHERITE_PICKAXE);
		ItemStack netheriteShovel = setWeaponValues(WeaponProperty.NETHERITE_SHOVEL);
		ItemStack netheriteHoe = setWeaponValues(WeaponProperty.NETHERITE_HOE);
		addCraft(plugin, netheriteSword, Material.NETHERITE_INGOT, CraftType.SWORD);
		addCraft(plugin, netheriteAxe, Material.NETHERITE_INGOT, CraftType.AXE);
		addCraft(plugin, netheritePickaxe, Material.NETHERITE_INGOT, CraftType.PICKAXE);
		addCraft(plugin, netheriteShovel, Material.NETHERITE_INGOT, CraftType.SHOVEL);
		addCraft(plugin, netheriteHoe, Material.NETHERITE_INGOT, CraftType.HOE);

		if (plugin.getConfig().getBoolean("prismarineTools")) {
			ItemStack prismarineSword = setWeaponValues(WeaponProperty.PRISMARINE_SWORD);
			ItemStack prismarineAxe = setWeaponValues(WeaponProperty.PRISMARINE_AXE);
			ItemStack prismarinePickaxe = setWeaponValues(WeaponProperty.PRISMARINE_PICKAXE);
			ItemStack prismarineShovel = setWeaponValues(WeaponProperty.PRISMARINE_SHOVEL);
			ItemStack prismarineHoe = setWeaponValues(WeaponProperty.PRISMARINE_HOE);
			addCraft(plugin, prismarineSword, Material.PRISMARINE, CraftType.SWORD);
			addCraft(plugin, prismarineAxe, Material.PRISMARINE, CraftType.AXE);
			addCraft(plugin, prismarinePickaxe, Material.PRISMARINE, CraftType.PICKAXE);
			addCraft(plugin, prismarineShovel, Material.PRISMARINE, CraftType.SHOVEL);
			addCraft(plugin, prismarineHoe, Material.PRISMARINE, CraftType.HOE);
			if (plugin.getConfig().getBoolean("craftableTrident")) {
				 ItemStack trident = setWeaponValues(WeaponProperty.TRIDENT);
				 addCraft(plugin, trident, Material.PRISMARINE, CraftType.TRIDENT, "trident");
			}
		}

		if (plugin.getConfig().getBoolean("stoneToolsVariants")) {
			ItemStack blackstoneSword = setWeaponValues(WeaponProperty.BLACKSTONE_SWORD);
			ItemStack blackstoneAxe = setWeaponValues(WeaponProperty.BLACKSTONE_AXE);
			ItemStack blackstonePickaxe = setWeaponValues(WeaponProperty.BLACKSTONE_PICKAXE);
			ItemStack blackstoneShovel = setWeaponValues(WeaponProperty.BLACKSTONE_SHOVEL);
			ItemStack blackstoneHoe = setWeaponValues(WeaponProperty.BLACKSTONE_HOE);
			addCraft(plugin, blackstoneSword, Material.BLACKSTONE, CraftType.SWORD);
			addCraft(plugin, blackstoneAxe, Material.BLACKSTONE, CraftType.AXE);
			addCraft(plugin, blackstonePickaxe, Material.BLACKSTONE, CraftType.PICKAXE);
			addCraft(plugin, blackstoneShovel, Material.BLACKSTONE, CraftType.SHOVEL);
			addCraft(plugin, blackstoneHoe, Material.BLACKSTONE, CraftType.HOE);
		}
	}

	public static void addCraft(ImagiCube plugin, ItemStack result, Material ingredient, CraftType craftType) {
		addCraft(plugin, result, ingredient, craftType, ingredient.getKey().getKey() + "_" + craftType.craftName);
	}

	public static void addCraft(ImagiCube plugin, ItemStack result, Material ingredient, CraftType craftType,
			String craftName) {
		if (!craftType.hasMirrorCraft()) {
			NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin(Reference.PLUGIN_NAME),
					craftName);
			ShapedRecipe recipe = new ShapedRecipe(key, result);
			recipe.shape(craftType.getCraft());
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
				.add("+" + weaponProperty.getAttackSpeed() + " ", false, false, false, false, SimpleJSON.Color.BLUE,
						false)
				.add("imagicube.attack.speed", false, false, false, false, SimpleJSON.Color.BLUE, true).convert());
		NBTUtil.addLore(display, new SimpleJSON().add(" ", false, false, false, false, SimpleJSON.Color.WHITE, false)
				.add("+" + weaponProperty.getDamage() + " ", false, false, false, false, SimpleJSON.Color.BLUE, false)
				.add("imagicube.attack.damage", false, false, false, false, SimpleJSON.Color.BLUE, true).convert());
		String weaponName = getWeaponName(weaponProperty);
		if (weaponName != null)
			display.setString("Name", new SimpleJSON()
					.add(weaponName, false, false, false, false, SimpleJSON.Color.WHITE, true).convert());
		nbti.setInteger(NBTUtil.UPDATEVERSION, ItemUpdater.updateVersion);
		Durability.setDurability(nbti, 0);
	}

	public static String getWeaponName(WeaponProperty weaponProperty) {
		if ((weaponProperty.getMaterial().equals("prismarine") && !weaponProperty.getType().equals("trident"))
				|| weaponProperty.getMaterial().equals("blackstone") || weaponProperty.getMaterial().equals("oak")
				|| weaponProperty.getMaterial().equals("spruce") || weaponProperty.getMaterial().equals("birch")
				|| weaponProperty.getMaterial().equals("jungle") || weaponProperty.getMaterial().equals("acacia")
				|| weaponProperty.getMaterial().equals("dark_oak") || weaponProperty.getMaterial().equals("crimson")
				|| weaponProperty.getMaterial().equals("warped") || weaponProperty.getType().equals("")
				|| weaponProperty.getType().equals("") || weaponProperty.getType().equals("")
				|| weaponProperty.getType().equals(""))
			return "item.imagicube." + weaponProperty.getMaterial() + "_" + weaponProperty.getType();
		else
			return null;
	}

	public enum CraftType {
		SWORD("sword", new String[] { "I", "I", "|" }, false), //
		AXE("axe", new String[] { "II", "|I", "| " }, true), //
		PICKAXE("pickaxe", new String[] { "III", " | ", " | " }, false), //
		SHOVEL("shovel", new String[] { "I", "|", "|" }, false), //
		HOE("hoe", new String[] { "II", "| ", "| " }, true), //
		TRIDENT("trident", new String[] { " II", " |I", "|  " }, true);//

		public String craftName;
		public String[] lines;
		boolean hasMirrorCraft;

		CraftType(String craftName, String[] lines, boolean hasMirrorCraft) {
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

		public String[] getSecondCraft() {
			String[] newLines = new String[this.lines.length];
			for (int i = 0; i < this.lines.length; i++)
				newLines[i] = new StringBuilder(this.lines[i]).reverse().toString();
			return newLines;
		}
	}
}