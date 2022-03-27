package fr.phlayne.imagicube.crafts;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.ImagiCube;
import fr.phlayne.imagicube.crafts.armor.ArmorRecipes;
import fr.phlayne.imagicube.crafts.armor.WeaponRecipes;
import fr.phlayne.imagicube.exception.CannotUpdateItemException;
import fr.phlayne.imagicube.item.ItemUpdatingCause;
import fr.phlayne.imagicube.util.ItemUpdater;

public class Crafts {

	public static ShapedRecipe SADDLE_CRAFT;
	public static ShapedRecipe WET_SPONGE_CRAFT;
	public static ShapedRecipe NETHER_BRICK_CRAFT;
	public static ShapedRecipe LEFT_RED_NETHER_BRICK_CRAFT;
	public static ShapedRecipe RIGHT_RED_NETHER_BRICK_CRAFT;

	public static ShapedRecipe ACACIA_TRAPDOOR_CRAFT;
	public static ShapedRecipe BIRCH_TRAPDOOR_CRAFT;
	public static ShapedRecipe DARK_OAK_TRAPDOOR_CRAFT;
	public static ShapedRecipe JUNGLE_TRAPDOOR_CRAFT;
	public static ShapedRecipe OAK_TRAPDOOR_CRAFT;
	public static ShapedRecipe SPRUCE_TRAPDOOR_CRAFT;
	public static ShapedRecipe CRIMSON_TRAPDOOR_CRAFT;
	public static ShapedRecipe WARPED_TRAPDOOR_CRAFT;

	public static ShapedRecipe L_BOW_CRAFT;
	public static ShapedRecipe R_BOW_CRAFT;
	public static ShapedRecipe CROSSBOW_CRAFT;
	public static ShapedRecipe SHIELD_CRAFT;
	public static ShapelessRecipe FLINT_AND_STEEL_CRAFT;
	public static ShapedRecipe L_SHEARS_CRAFT;
	public static ShapedRecipe R_SHEARS_CRAFT;
	public static ShapedRecipe L_FISHING_ROD_CRAFT;
	public static ShapedRecipe R_FISHING_ROD_CRAFT;
	public static ShapelessRecipe DRAGON_BREATH;
	public static ShapedRecipe INVISIBLE_ITEM_FRAME;

	public static ShapedRecipe ARMOR_STAND_WITH_ARMS_CRAFT;

	public static ShapelessRecipe BLACK_DYE;
	public static ShapelessRecipe BLACK_DYE2;
	public static ShapelessRecipe GRAY_DYE;
	public static ShapelessRecipe ORANGE_DYE;
	public static ShapedRecipe LIME_DYE;
	public static ShapedRecipe BROWN_DYE;
	public static ShapelessRecipe PURPLE_DYE;
	public static ShapelessRecipe RED_DYE;
	public static ShapelessRecipe CACTUS_LEATHER;

	public static void loadRecipes(ImagiCube plugin) throws CannotUpdateItemException {
		removeCrafts();
		ItemStack wetSponge = new ItemStack(Material.WET_SPONGE);
		NamespacedKey wetSpongeKey = new NamespacedKey(plugin,
				"wet_sponge");
		WET_SPONGE_CRAFT = new ShapedRecipe(wetSpongeKey, wetSponge);
		WET_SPONGE_CRAFT.shape("...", ".-.", "...");
		WET_SPONGE_CRAFT.setIngredient('.', Material.DRIED_KELP);
		WET_SPONGE_CRAFT.setIngredient('-', Material.SAND);

		ItemStack saddle = new ItemStack(Material.SADDLE);
		NamespacedKey saddleKey = new NamespacedKey(plugin,
				"saddle");
		SADDLE_CRAFT = new ShapedRecipe(saddleKey, saddle);
		SADDLE_CRAFT.shape("LLL", "SIS", "I I");
		SADDLE_CRAFT.setIngredient('L', Material.LEATHER);
		SADDLE_CRAFT.setIngredient('S', Material.STRING);
		SADDLE_CRAFT.setIngredient('I', Material.IRON_INGOT);

		ItemStack netherBricks = new ItemStack(Material.NETHER_BRICKS, 4);
		NamespacedKey netherBricksKey = new NamespacedKey(plugin,
				"nether_bricks");
		NETHER_BRICK_CRAFT = new ShapedRecipe(netherBricksKey, netherBricks);
		NETHER_BRICK_CRAFT.shape("oo", "oo");
		NETHER_BRICK_CRAFT.setIngredient('o', Material.NETHER_BRICK);

		ItemStack redNetherBricks = new ItemStack(Material.RED_NETHER_BRICKS, 2);
		NamespacedKey redLeftNetherBricksKey = new NamespacedKey(
				plugin, "left_red_nether_bricks");
		LEFT_RED_NETHER_BRICK_CRAFT = new ShapedRecipe(redLeftNetherBricksKey, redNetherBricks);
		LEFT_RED_NETHER_BRICK_CRAFT.shape("oi", "io");
		LEFT_RED_NETHER_BRICK_CRAFT.setIngredient('o', Material.NETHER_BRICK);
		LEFT_RED_NETHER_BRICK_CRAFT.setIngredient('i', Material.NETHER_WART);
		NamespacedKey redRightNetherBricksKey = new NamespacedKey(
				plugin, "right_red_nether_bricks");
		RIGHT_RED_NETHER_BRICK_CRAFT = new ShapedRecipe(redRightNetherBricksKey, redNetherBricks);
		RIGHT_RED_NETHER_BRICK_CRAFT.shape("io", "oi");
		RIGHT_RED_NETHER_BRICK_CRAFT.setIngredient('o', Material.NETHER_BRICK);
		RIGHT_RED_NETHER_BRICK_CRAFT.setIngredient('i', Material.NETHER_WART);

		ItemStack acaciaTrapdoor = new ItemStack(Material.ACACIA_TRAPDOOR, 6);
		NamespacedKey acaciaTrapdoorKey = new NamespacedKey(plugin,
				"acacia_trapdoor");
		ACACIA_TRAPDOOR_CRAFT = new ShapedRecipe(acaciaTrapdoorKey, acaciaTrapdoor);
		ACACIA_TRAPDOOR_CRAFT.shape("---", "---");
		ACACIA_TRAPDOOR_CRAFT.setIngredient('-', Material.ACACIA_PLANKS);

		ItemStack birchTrapdoor = new ItemStack(Material.BIRCH_TRAPDOOR, 6);
		NamespacedKey birchTrapdoorKey = new NamespacedKey(plugin,
				"birch_trapdoor");
		BIRCH_TRAPDOOR_CRAFT = new ShapedRecipe(birchTrapdoorKey, birchTrapdoor);
		BIRCH_TRAPDOOR_CRAFT.shape("---", "---");
		BIRCH_TRAPDOOR_CRAFT.setIngredient('-', Material.BIRCH_PLANKS);

		ItemStack dark_oakTrapdoor = new ItemStack(Material.DARK_OAK_TRAPDOOR, 6);
		NamespacedKey dark_oakTrapdoorKey = new NamespacedKey(
				plugin, "dark_oak_trapdoor");
		DARK_OAK_TRAPDOOR_CRAFT = new ShapedRecipe(dark_oakTrapdoorKey, dark_oakTrapdoor);
		DARK_OAK_TRAPDOOR_CRAFT.shape("---", "---");
		DARK_OAK_TRAPDOOR_CRAFT.setIngredient('-', Material.DARK_OAK_PLANKS);

		ItemStack jungleTrapdoor = new ItemStack(Material.JUNGLE_TRAPDOOR, 6);
		NamespacedKey jungleTrapdoorKey = new NamespacedKey(plugin,
				"jungle_trapdoor");
		JUNGLE_TRAPDOOR_CRAFT = new ShapedRecipe(jungleTrapdoorKey, jungleTrapdoor);
		JUNGLE_TRAPDOOR_CRAFT.shape("---", "---");
		JUNGLE_TRAPDOOR_CRAFT.setIngredient('-', Material.JUNGLE_PLANKS);

		ItemStack oakTrapdoor = new ItemStack(Material.OAK_TRAPDOOR, 6);
		NamespacedKey oakTrapdoorKey = new NamespacedKey(plugin,
				"oak_trapdoor");
		OAK_TRAPDOOR_CRAFT = new ShapedRecipe(oakTrapdoorKey, oakTrapdoor);
		OAK_TRAPDOOR_CRAFT.shape("---", "---");
		OAK_TRAPDOOR_CRAFT.setIngredient('-', Material.OAK_PLANKS);

		ItemStack spruceTrapdoor = new ItemStack(Material.SPRUCE_TRAPDOOR, 6);
		NamespacedKey spruceTrapdoorKey = new NamespacedKey(plugin,
				"spruce_trapdoor");
		SPRUCE_TRAPDOOR_CRAFT = new ShapedRecipe(spruceTrapdoorKey, spruceTrapdoor);
		SPRUCE_TRAPDOOR_CRAFT.shape("---", "---");
		SPRUCE_TRAPDOOR_CRAFT.setIngredient('-', Material.SPRUCE_PLANKS);

		ItemStack crimsonTrapdoor = new ItemStack(Material.CRIMSON_TRAPDOOR, 6);
		NamespacedKey crimsonTrapdoorKey = new NamespacedKey(plugin,
				"crimson_trapdoor");
		CRIMSON_TRAPDOOR_CRAFT = new ShapedRecipe(crimsonTrapdoorKey, crimsonTrapdoor);
		CRIMSON_TRAPDOOR_CRAFT.shape("---", "---");
		CRIMSON_TRAPDOOR_CRAFT.setIngredient('-', Material.CRIMSON_PLANKS);

		ItemStack warpedTrapdoor = new ItemStack(Material.WARPED_TRAPDOOR, 6);
		NamespacedKey warpedTrapdoorKey = new NamespacedKey(plugin,
				"warped_trapdoor");
		WARPED_TRAPDOOR_CRAFT = new ShapedRecipe(warpedTrapdoorKey, warpedTrapdoor);
		WARPED_TRAPDOOR_CRAFT.shape("---", "---");
		WARPED_TRAPDOOR_CRAFT.setIngredient('-', Material.WARPED_PLANKS);

		ItemStack bow = ItemUpdater.updateItem(new ItemStack(Material.BOW), ItemUpdatingCause.CRAFT);
		NamespacedKey leftBowKey = new NamespacedKey(plugin,
				"left_bow");
		NamespacedKey rightBowKey = new NamespacedKey(plugin,
				"right_bow");
		L_BOW_CRAFT = new ShapedRecipe(leftBowKey, bow);
		L_BOW_CRAFT.shape(" |s", "| s", " |s");
		L_BOW_CRAFT.setIngredient('|', Material.STICK);
		L_BOW_CRAFT.setIngredient('s', Material.STRING);
		R_BOW_CRAFT = new ShapedRecipe(rightBowKey, bow);
		R_BOW_CRAFT.shape("s| ", "s |", "s| ");
		R_BOW_CRAFT.setIngredient('|', Material.STICK);
		R_BOW_CRAFT.setIngredient('s', Material.STRING);

		ItemStack crossbow = ItemUpdater.updateItem(new ItemStack(Material.CROSSBOW), ItemUpdatingCause.CRAFT);
		NamespacedKey crossbowKey = new NamespacedKey(plugin,
				"crossbow");
		CROSSBOW_CRAFT = new ShapedRecipe(crossbowKey, crossbow);
		CROSSBOW_CRAFT.shape("|i|", "scs", " | ");
		CROSSBOW_CRAFT.setIngredient('|', Material.STICK);
		CROSSBOW_CRAFT.setIngredient('s', Material.STRING);
		CROSSBOW_CRAFT.setIngredient('i', Material.IRON_INGOT);
		CROSSBOW_CRAFT.setIngredient('c', Material.TRIPWIRE_HOOK);

		ItemStack shield = ItemUpdater.updateItem(new ItemStack(Material.SHIELD), ItemUpdatingCause.CRAFT);
		NamespacedKey shieldKey = new NamespacedKey(plugin,
				"shield");
		SHIELD_CRAFT = new ShapedRecipe(shieldKey, shield);
		SHIELD_CRAFT.shape("pip", "ppp", " p ");
		SHIELD_CRAFT.setIngredient('p',
				new RecipeChoice.MaterialChoice(Material.OAK_PLANKS, Material.SPRUCE_PLANKS, Material.BIRCH_PLANKS,
						Material.JUNGLE_PLANKS, Material.ACACIA_PLANKS, Material.DARK_OAK_PLANKS,
						Material.CRIMSON_PLANKS, Material.WARPED_PLANKS));
		SHIELD_CRAFT.setIngredient('i', Material.IRON_INGOT);

		ItemStack flintAndSteel = ItemUpdater.updateItem(new ItemStack(Material.FLINT_AND_STEEL), ItemUpdatingCause.CRAFT);
		NamespacedKey flintAndSteelKey = new NamespacedKey(plugin,
				"flint_and_steel");
		FLINT_AND_STEEL_CRAFT = new ShapelessRecipe(flintAndSteelKey, flintAndSteel);
		FLINT_AND_STEEL_CRAFT.addIngredient(Material.FLINT);
		FLINT_AND_STEEL_CRAFT.addIngredient(Material.IRON_INGOT);

		ItemStack shears = ItemUpdater.updateItem(new ItemStack(Material.SHEARS), ItemUpdatingCause.CRAFT);
		NamespacedKey leftShearsKey = new NamespacedKey(plugin,
				"left_shears");
		NamespacedKey rightShearsKey = new NamespacedKey(plugin,
				"right_shears");
		L_SHEARS_CRAFT = new ShapedRecipe(leftShearsKey, shears);
		L_SHEARS_CRAFT.shape("i ", " i");
		L_SHEARS_CRAFT.setIngredient('i', Material.IRON_INGOT);
		R_SHEARS_CRAFT = new ShapedRecipe(rightShearsKey, shears);
		R_SHEARS_CRAFT.shape(" i", "i ");
		R_SHEARS_CRAFT.setIngredient('i', Material.IRON_INGOT);

		ItemStack fishing_rod = ItemUpdater.updateItem(new ItemStack(Material.FISHING_ROD), ItemUpdatingCause.CRAFT);
		NamespacedKey leftFishingRodKey = new NamespacedKey(plugin,
				"left_fishing_rod");
		NamespacedKey rightFishingRodKey = new NamespacedKey(plugin,
				"right_fishing_rod");
		L_FISHING_ROD_CRAFT = new ShapedRecipe(leftFishingRodKey, fishing_rod);
		L_FISHING_ROD_CRAFT.shape("  |", " |s", "| s");
		L_FISHING_ROD_CRAFT.setIngredient('|', Material.STICK);
		L_FISHING_ROD_CRAFT.setIngredient('s', Material.STRING);
		R_FISHING_ROD_CRAFT = new ShapedRecipe(rightFishingRodKey, fishing_rod);
		R_FISHING_ROD_CRAFT.shape("|  ", "s| ", "s |");
		R_FISHING_ROD_CRAFT.setIngredient('|', Material.STICK);
		R_FISHING_ROD_CRAFT.setIngredient('s', Material.STRING);

		ItemStack dragonBreath = new ItemStack(Material.DRAGON_BREATH);
		NamespacedKey dragonBreathKey = new NamespacedKey(plugin,
				"dragon_breath");
		DRAGON_BREATH = new ShapelessRecipe(dragonBreathKey, dragonBreath);
		DRAGON_BREATH.addIngredient(Material.GLASS_BOTTLE);
		DRAGON_BREATH.addIngredient(Material.CHORUS_FLOWER);

		ItemStack invisibleItemFrame = new ItemStack(Material.ITEM_FRAME);
		NBTItem invisibleItemFrameNBTI = new NBTItem(invisibleItemFrame);
		NBTCompound invisibleItemFrameEntityNBT = invisibleItemFrameNBTI.getOrCreateCompound("EntityTag");
		invisibleItemFrameEntityNBT.setBoolean("Invisible", true);
		invisibleItemFrameNBTI.setInteger("CustomModelData", 1);
		invisibleItemFrame = invisibleItemFrameNBTI.getItem();
		NamespacedKey invisibleItemFrameKey = new NamespacedKey(
				plugin, "invisible_item_frame");
		INVISIBLE_ITEM_FRAME = new ShapedRecipe(invisibleItemFrameKey, invisibleItemFrame);
		INVISIBLE_ITEM_FRAME.shape("sss", "s s", "sss");
		INVISIBLE_ITEM_FRAME.setIngredient('s', Material.STICK);

		ItemStack blackDye = new ItemStack(Material.BLACK_DYE);
		NamespacedKey blackDyeKey = new NamespacedKey(plugin,
				"black_dye");
		BLACK_DYE = new ShapelessRecipe(blackDyeKey, blackDye);
		BLACK_DYE.addIngredient(Material.COAL);
		NamespacedKey blackDye2Key = new NamespacedKey(plugin,
				"black_dye2");
		BLACK_DYE2 = new ShapelessRecipe(blackDye2Key, blackDye);
		BLACK_DYE2.addIngredient(Material.CHARCOAL);

		ItemStack grayDye = new ItemStack(Material.GRAY_DYE);
		NamespacedKey grayDyeKey = new NamespacedKey(plugin,
				"gray_dye");
		GRAY_DYE = new ShapelessRecipe(grayDyeKey, grayDye);
		GRAY_DYE.addIngredient(Material.GUNPOWDER);

		ItemStack orangeDye = new ItemStack(Material.ORANGE_DYE);
		NamespacedKey orangeDyeKey = new NamespacedKey(plugin,
				"orange_dye");
		ORANGE_DYE = new ShapelessRecipe(orangeDyeKey, orangeDye);
		ORANGE_DYE.addIngredient(Material.CARROT);

		ItemStack limeDye = new ItemStack(Material.LIME_DYE);
		NamespacedKey limeDyeKey = new NamespacedKey(plugin,
				"lime_dye");
		LIME_DYE = new ShapedRecipe(limeDyeKey, limeDye);
		LIME_DYE.shape("sss", "sss", "sss");
		LIME_DYE.setIngredient('s', Material.WHEAT_SEEDS);

		ItemStack brownDye = new ItemStack(Material.BROWN_DYE);
		NamespacedKey brownDyeKey = new NamespacedKey(plugin,
				"brown_dye");
		BROWN_DYE = new ShapedRecipe(brownDyeKey, brownDye);
		BROWN_DYE.shape("sss", "sss", "sss");
		BROWN_DYE.setIngredient('s', Material.MELON_SEEDS);

		ItemStack purpleDye = new ItemStack(Material.PURPLE_DYE);
		NamespacedKey purpleDyeKey = new NamespacedKey(plugin,
				"purple_dye");
		PURPLE_DYE = new ShapelessRecipe(purpleDyeKey, purpleDye);
		PURPLE_DYE.addIngredient(Material.CHORUS_FRUIT);

		ItemStack redDye = new ItemStack(Material.RED_DYE);
		NamespacedKey redDyeKey = new NamespacedKey(plugin,
				"red_dye");
		RED_DYE = new ShapelessRecipe(redDyeKey, redDye);
		RED_DYE.addIngredient(Material.REDSTONE);

		ItemStack cactusLeather = new ItemStack(Material.LEATHER, 2);
		NBTItem cactusLeatherNBTI = new NBTItem(cactusLeather);
		cactusLeatherNBTI.setInteger("CustomModelData", 1);
		NamespacedKey cactusLeatherKey = new NamespacedKey(plugin,
				"cactus_leather");
		CACTUS_LEATHER = new ShapelessRecipe(cactusLeatherKey, cactusLeatherNBTI.getItem());
		CACTUS_LEATHER.addIngredient(Material.CACTUS);
		CACTUS_LEATHER.addIngredient(Material.CACTUS);

		Bukkit.addRecipe(SADDLE_CRAFT);
		Bukkit.addRecipe(WET_SPONGE_CRAFT);
		Bukkit.addRecipe(NETHER_BRICK_CRAFT);
		Bukkit.addRecipe(LEFT_RED_NETHER_BRICK_CRAFT);
		Bukkit.addRecipe(RIGHT_RED_NETHER_BRICK_CRAFT);

		Bukkit.addRecipe(ACACIA_TRAPDOOR_CRAFT);
		Bukkit.addRecipe(BIRCH_TRAPDOOR_CRAFT);
		Bukkit.addRecipe(DARK_OAK_TRAPDOOR_CRAFT);
		Bukkit.addRecipe(JUNGLE_TRAPDOOR_CRAFT);
		Bukkit.addRecipe(OAK_TRAPDOOR_CRAFT);
		Bukkit.addRecipe(SPRUCE_TRAPDOOR_CRAFT);
		Bukkit.addRecipe(CRIMSON_TRAPDOOR_CRAFT);
		Bukkit.addRecipe(WARPED_TRAPDOOR_CRAFT);
		Bukkit.addRecipe(L_BOW_CRAFT);
		Bukkit.addRecipe(R_BOW_CRAFT);
		Bukkit.addRecipe(CROSSBOW_CRAFT);
		Bukkit.addRecipe(SHIELD_CRAFT);
		Bukkit.addRecipe(FLINT_AND_STEEL_CRAFT);
		Bukkit.addRecipe(L_SHEARS_CRAFT);
		Bukkit.addRecipe(R_SHEARS_CRAFT);
		Bukkit.addRecipe(L_FISHING_ROD_CRAFT);
		Bukkit.addRecipe(R_FISHING_ROD_CRAFT);
		Bukkit.addRecipe(DRAGON_BREATH);
		Bukkit.addRecipe(INVISIBLE_ITEM_FRAME);

		Bukkit.addRecipe(BLACK_DYE);
		Bukkit.addRecipe(BLACK_DYE2);
		Bukkit.addRecipe(GRAY_DYE);
		Bukkit.addRecipe(ORANGE_DYE);
		Bukkit.addRecipe(LIME_DYE);
		Bukkit.addRecipe(BROWN_DYE);
		Bukkit.addRecipe(PURPLE_DYE);
		Bukkit.addRecipe(RED_DYE);
		Bukkit.addRecipe(CACTUS_LEATHER);
		// Bukkit.addRecipe(ARMOR_STAND_WITH_ARMS_CRAFT);

		ArmorRecipes.init(plugin);
		WeaponRecipes.init(plugin);
	}

	public static void removeCrafts() {
		remove(Material.IRON_BOOTS);
		remove(Material.IRON_LEGGINGS);
		remove(Material.IRON_CHESTPLATE);
		remove(Material.IRON_HELMET);
		remove(Material.GOLDEN_BOOTS);
		remove(Material.GOLDEN_LEGGINGS);
		remove(Material.GOLDEN_CHESTPLATE);
		remove(Material.GOLDEN_HELMET);
		remove(Material.DIAMOND_BOOTS);
		remove(Material.DIAMOND_LEGGINGS);
		remove(Material.DIAMOND_CHESTPLATE);
		remove(Material.DIAMOND_HELMET);
		remove(Material.LEATHER_BOOTS);
		remove(Material.LEATHER_LEGGINGS);
		remove(Material.LEATHER_CHESTPLATE);
		remove(Material.LEATHER_HELMET);
		remove(Material.NETHER_BRICKS);
		remove(Material.RED_NETHER_BRICKS);
		remove(Material.TURTLE_HELMET);

		remove(Material.ACACIA_TRAPDOOR);
		remove(Material.BIRCH_TRAPDOOR);
		remove(Material.DARK_OAK_TRAPDOOR);
		remove(Material.JUNGLE_TRAPDOOR);
		remove(Material.OAK_TRAPDOOR);
		remove(Material.SPRUCE_TRAPDOOR);
		remove(Material.CRIMSON_TRAPDOOR);
		remove(Material.WARPED_TRAPDOOR);

		remove(Material.BOW);
		remove(Material.CROSSBOW);
		remove(Material.SHIELD);
		remove(Material.TRIDENT);
		remove(Material.FLINT_AND_STEEL);
		remove(Material.SHEARS);
		remove(Material.FISHING_ROD);
		remove(Material.CARROT_ON_A_STICK);
		remove(Material.WARPED_FUNGUS_ON_A_STICK);

		remove(Material.NETHERITE_HOE);
		remove(Material.DIAMOND_HOE);
		remove(Material.GOLDEN_HOE);
		remove(Material.IRON_HOE);
		remove(Material.STONE_HOE);
		remove(Material.WOODEN_HOE);
		remove(Material.NETHERITE_PICKAXE);
		remove(Material.DIAMOND_PICKAXE);
		remove(Material.GOLDEN_PICKAXE);
		remove(Material.IRON_PICKAXE);
		remove(Material.STONE_PICKAXE);
		remove(Material.WOODEN_PICKAXE);
		remove(Material.NETHERITE_SHOVEL);
		remove(Material.DIAMOND_SHOVEL);
		remove(Material.GOLDEN_SHOVEL);
		remove(Material.IRON_SHOVEL);
		remove(Material.STONE_SHOVEL);
		remove(Material.WOODEN_SHOVEL);
		remove(Material.NETHERITE_SWORD);
		remove(Material.DIAMOND_SWORD);
		remove(Material.GOLDEN_SWORD);
		remove(Material.IRON_SWORD);
		remove(Material.STONE_SWORD);
		remove(Material.WOODEN_SWORD);
		remove(Material.NETHERITE_AXE);
		remove(Material.DIAMOND_AXE);
		remove(Material.GOLDEN_AXE);
		remove(Material.IRON_AXE);
		remove(Material.STONE_AXE);
		remove(Material.WOODEN_AXE);
	}

	public static void remove(Material m) {

		Iterator<Recipe> it = Bukkit.recipeIterator();
		Recipe recipe;
		while (it.hasNext()) {
			recipe = it.next();
			if (recipe != null && recipe.getResult().getType() == m) {
				it.remove();
			}
		}
	}

}
