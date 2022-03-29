package fr.phlayne.imagicube.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.exception.CannotUpdateItemException;
import fr.phlayne.imagicube.item.Durability;
import fr.phlayne.imagicube.item.ItemUpdatingCause;
import fr.phlayne.imagicube.util.ItemUpdater;
import fr.phlayne.imagicube.util.ItemUtil;
import fr.phlayne.imagicube.util.LeatherColor;
import fr.phlayne.imagicube.util.NBTUtil;

public class CraftingEvents implements Listener {

	@EventHandler
	public void onCraft(PrepareItemCraftEvent event) {
		ItemStack decraftItem = null;
		for (int i = 0; i < event.getInventory().getMatrix().length; i++) {
			ItemStack item = event.getInventory().getItem(i + 1);
			if (item != null)
				if (decraftItem == null) {
					decraftItem = item;
				} else
					return;
		}
		if (decraftItem == null)
			return;
		ItemStack result = uncraft(decraftItem);
		if (result != null)
			event.getInventory().setResult(uncraft(decraftItem));
	}

	// @EventHandler Unused for now
	public void checkCraft(PrepareItemCraftEvent event) {
		ItemStack[] matrix = event.getInventory().getMatrix();
		if (matrix.length != 9)
			return;
		for (ItemStack item : matrix) {
			if (item != null) {
				// int customModelData = item.hasItemMeta() ?
				// item.getItemMeta().getCustomModelData() : 0;
				switch (item.getType()) {
				/*
				 * TODO Move this bit to ImagiCubeSpells
				 * 
				 * case NETHER_STAR: if (event.getRecipe() != null) if
				 * (Arrays.asList(Crafts.SCEPTER_CRAFT.getResult(),
				 * Crafts.PARCHMENT_CRAFT.getResult(),
				 * Crafts.GLOBE_CRAFT.getResult()).contains(event.getRecipe().getResult())) { if
				 * (customModelData != 1) event.getInventory().setResult(null); } else if
				 * (customModelData != 0) event.getInventory().setResult(null); break;
				 */
				/*
				 * TODO Move this bit to ImagiCubeFood
				 * 
				 * case WHEAT_SEEDS: if (event.getRecipe() != null) if (Arrays
				 * .asList(FoodRecipes.CONCHIGLIE_HORIZONTAL_DOWN_LEFT.getResult(),
				 * FoodRecipes.FARFALLE_HORIZONTAL_DOWN.getResult(),
				 * FoodRecipes.PENNE_HORIZONTAL.getResult(),
				 * FoodRecipes.SHELL_DOWN_LEFT.getResult(),
				 * FoodRecipes.SPAGHETTI_LEFT.getResult())
				 * .contains(event.getRecipe().getResult())) { if (customModelData !=
				 * FoodProperty.GROUND_WHEAT.getModelData())
				 * event.getInventory().setResult(null); } else if (customModelData != 0)
				 * event.getInventory().setResult(null); break;
				 */
				default:
					break;
				}
			}
		}
	}

	@EventHandler
	public void spellCraftDuplicatingItemsFix(CraftItemEvent event) {
	}

	@EventHandler
	public void onThingOnAStickCraft(PrepareItemCraftEvent event) {
		ItemStack[] matrix = event.getInventory().getMatrix();

		int[] values = matrix.length == 9 ? new int[] { 0, 1, 3, 4 } : new int[] { 0 };
		for (int i : values) {
			if (matrix[i] != null) {
				String itemType = ItemUtil.getItemType(matrix[i]);
				if (itemType.equals("fishing_rod") && matrix[i + 4] != null) {
					for (int j = 0; j < 9; j++)
						if (!(matrix[j] == null || matrix[j].getType().equals(Material.AIR)) && (j != i && j != i + 4))
							return;
					Material material = matrix[i + 4].getType();
					Material resultMaterial;
					switch (material) {
					case CARROT:
						resultMaterial = Material.CARROT_ON_A_STICK;
						break;
					case WARPED_FUNGUS:
						resultMaterial = Material.WARPED_FUNGUS_ON_A_STICK;
						break;
					// TODO add the bait on a stick
					default:
						return;
					}
					ItemStack resultItem = new ItemStack(Material.AIR);
					try {
						resultItem = ItemUpdater.updateItem(new ItemStack(resultMaterial), ItemUpdatingCause.CRAFT);
					} catch (CannotUpdateItemException e) {
						e.printStackTrace();
					}
					event.getInventory().setResult(resultItem);
				}
			}
		}
	}

	@EventHandler
	public void onShieldPatternCraft(PrepareItemCraftEvent event) {
		ItemStack[] matrix = event.getInventory().getMatrix();

		List<Material> banners = new ArrayList<Material>();
		banners.add(Material.WHITE_BANNER);
		banners.add(Material.ORANGE_BANNER);
		banners.add(Material.MAGENTA_BANNER);
		banners.add(Material.LIGHT_BLUE_BANNER);
		banners.add(Material.YELLOW_BANNER);
		banners.add(Material.LIME_BANNER);
		banners.add(Material.PINK_BANNER);
		banners.add(Material.GRAY_BANNER);
		banners.add(Material.LIGHT_GRAY_BANNER);
		banners.add(Material.CYAN_BANNER);
		banners.add(Material.PURPLE_BANNER);
		banners.add(Material.BLUE_BANNER);
		banners.add(Material.BROWN_BANNER);
		banners.add(Material.GREEN_BANNER);
		banners.add(Material.RED_BANNER);
		banners.add(Material.BLACK_BANNER);

		ItemStack shield = null;
		ItemStack banner = null;
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i] != null) {
				String itemType = ItemUtil.getItemType(matrix[i]);
				if (itemType.equals("shield")) {
					if (shield == null)
						shield = matrix[i];
					else
						return;
				} else if (banners.contains(matrix[i].getType()))
					if (banner == null)
						banner = matrix[i];
					else
						return;
				else
					return;
			}
		}
		if (shield == null || banner == null)
			return;
		int baseBannerValue = banners.indexOf(banner.getType());
		NBTItem bannerNBTI = new NBTItem(banner);
		NBTCompound bannerBlockEntityNBT = bannerNBTI.getOrCreateCompound("BlockEntityTag");
		NBTItem shieldNBTI = new NBTItem(shield);
		NBTCompound shieldBlockEntityNBT = shieldNBTI.getOrCreateCompound("BlockEntityTag");
		shieldBlockEntityNBT.mergeCompound(bannerBlockEntityNBT);
		shieldBlockEntityNBT.setInteger("Base", baseBannerValue);
		event.getInventory().setResult(shieldNBTI.getItem());
	}

	@EventHandler
	public void onArmorDye(PrepareItemCraftEvent event) {
		ItemStack[] matrix = event.getInventory().getMatrix();
		int leatherItemAmount = 0;
		int leatherArmorIndex = 0;
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i] != null && ItemUtil.getMaterial(matrix[i]).equals("leather")) {
				leatherItemAmount += 1;
				leatherArmorIndex = i;
			}
		}
		if (leatherItemAmount != 1)
			return;
		List<Integer> colorList = new ArrayList<Integer>();
		for (int i = 0; i < matrix.length; i++) {
			int value = getItemColor(matrix[i]);
			if (value >= 0)
				colorList.add(value);
			if (value == -2)
				return;
		}
		if (colorList.size() == 0)
			return;
		Color newColor = fuseColors(colorList);
		ItemStack result = matrix[leatherArmorIndex].clone();
		if (result.hasItemMeta() && result.getItemMeta() instanceof LeatherArmorMeta) {
			LeatherArmorMeta meta = (LeatherArmorMeta) result.getItemMeta();
			meta.setColor(newColor);
			result.setItemMeta(meta);
			event.getInventory().setResult(result);
		}
	}

	public static int getItemColor(ItemStack item) {
		int color = -2;
		if (item == null)
			return -3;
		if (item.hasItemMeta() && item.getItemMeta() instanceof LeatherArmorMeta) {
			LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
			if (meta.getColor().equals(Bukkit.getServer().getItemFactory().getDefaultLeatherColor()))
				color = -1;
			else
				color = meta.getColor().asRGB();
		}
		switch (item.getType()) {
		case WHITE_DYE:
			color = 0xf9fffe;
			break;
		case ORANGE_DYE:
			color = 0xf9801d;
			break;
		case MAGENTA_DYE:
			color = 0xc74ebd;
			break;
		case LIGHT_BLUE_DYE:
			color = 0x3ab3da;
			break;
		case YELLOW_DYE:
			color = 0xfed83d;
			break;
		case LIME_DYE:
			color = 0x80c71f;
			break;
		case PINK_DYE:
			color = 0xf38baa;
			break;
		case GRAY_DYE:
			color = 0x474f52;
			break;
		case LIGHT_GRAY_DYE:
			color = 0x9d9d97;
			break;
		case CYAN_DYE:
			color = 0x169c9c;
			break;
		case PURPLE_DYE:
			color = 0x8932b8;
			break;
		case BLUE_DYE:
			color = 0x3c44aa;
			break;
		case BROWN_DYE:
			color = 0x835432;
			break;
		case GREEN_DYE:
			color = 0x5e7c16;
			break;
		case RED_DYE:
			color = 0xb02e16;
			break;
		case BLACK_DYE:
			color = 0x1d1d21;
			break;
		default:
			break;
		}
		return color;
	}

	public static Color fuseColors(List<Integer> colors) {
		float totalRed = 0;
		float totalGreen = 0;
		float totalBlue = 0;
		float totalCount = 0;
		for (int i = 0; i < colors.size(); i++)
			if (i != -1) {
				totalRed += colors.get(i) >> 16 & 255;
				totalGreen += colors.get(i) >> 8 & 255;
				totalBlue += colors.get(i) & 255;
				totalCount++;
			}
		float averageRed = totalRed / totalCount;
		float averageGreen = totalGreen / totalCount;
		float averageBlue = totalBlue / totalCount;
		return Color.fromRGB((int) averageRed, (int) averageGreen, (int) averageBlue);
	}

	public static ItemStack uncraft(ItemStack item) {
		NBTItem nbti = new NBTItem(item);
		if (nbti.hasKey(NBTUtil.MATERIAL) && nbti.hasKey(NBTUtil.ITEM_TYPE) && nbti.hasKey(NBTUtil.DURABILITY)) {
			if (nbti.hasKey(NBTUtil.CANNOT_BE_UNCRAFTED) && nbti.getBoolean(NBTUtil.CANNOT_BE_UNCRAFTED))
				return null;
			String material = nbti.getString(NBTUtil.MATERIAL);
			String itemType = nbti.getString(NBTUtil.ITEM_TYPE);
			Material resultMaterial = null;
			int resultCount = 1;
			switch (itemType) {
			case "katana":
			case "sword":
			case "hammer":
			case "hoe":
				resultCount = 2;
				break;
			case "trident":
			case "axe":
			case "pickaxe":
			case "scythe":
				resultCount = 3;
				break;
			case "boots":
				resultCount = 4;
				break;
			case "helmet":
				resultCount = 5;
				break;
			case "leggings":
				resultCount = 7;
				break;
			case "chestplate":
				resultCount = 8;
				break;
			}
			switch (material) {
			case "wood":
				resultMaterial = Material.OAK_PLANKS;
				break;
			case "oak":
				resultMaterial = Material.OAK_PLANKS;
				break;
			case "spruce":
				resultMaterial = Material.SPRUCE_PLANKS;
				break;
			case "birch":
				resultMaterial = Material.BIRCH_PLANKS;
				break;
			case "jungle":
				resultMaterial = Material.JUNGLE_PLANKS;
				break;
			case "acacia":
				resultMaterial = Material.ACACIA_PLANKS;
				break;
			case "dark_oak":
				resultMaterial = Material.DARK_OAK_PLANKS;
				break;
			case "crimson":
				resultMaterial = Material.CRIMSON_PLANKS;
				break;
			case "warped":
				resultMaterial = Material.WARPED_PLANKS;
				break;
			case "stone":
				resultMaterial = Material.COBBLESTONE;
				break;
			case "blackstone":
				resultMaterial = Material.BLACKSTONE;
				break;
			case "iron":
				resultMaterial = Material.IRON_INGOT;
				break;
			case "diamond":
				resultMaterial = Material.DIAMOND;
				break;
			case "gold":
				resultMaterial = Material.GOLD_INGOT;
				break;
			case "leather":
				resultMaterial = Material.LEATHER;
				break;
			case "chainmail":
				resultMaterial = Material.CHAIN;
				break;
			case "netherite":
				resultMaterial = Material.DIAMOND;
				break;
			case "turtle":
				resultMaterial = Material.SCUTE;
				break;
			case "prismarine":
				resultMaterial = Material.PRISMARINE_SHARD;
				break;
			}
			if (resultMaterial == null) {
				Bukkit.getLogger().log(Level.WARNING, "Unhandled uncraft material : " + material);
				return null;
			}
			return new ItemStack(resultMaterial,
					(int) ((Durability.getPercentDurability(nbti) * ((float) resultCount))));
		}
		return null;
	}

	@EventHandler
	public void cactusLeatherArmorColorFixer(PrepareItemCraftEvent event) {
		ItemStack[] matrix = event.getInventory().getMatrix();
		if (matrix.length != 9)
			return;
		List<Material> leatherItems = Arrays.asList(Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS,
				Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET);
		if (event.getInventory().getResult() != null
				&& leatherItems.contains(event.getInventory().getResult().getType())) {
			int leather = 0;
			int cactusLeather = 0;
			for (ItemStack item : matrix) {
				if (item != null && item.getType().equals(Material.LEATHER)) {
					NBTItem nbti = new NBTItem(item);
					int customModelData = nbti != null
							? nbti.hasKey("CustomModelData") ? nbti.getInteger("CustomModelData") : 0
							: 0;
					switch (customModelData) {
					case 1:
						cactusLeather++;
						break;
					default:
						leather++;
						break;
					}
				}
			}
			ItemStack result = event.getInventory().getResult();
			LeatherArmorMeta armorMeta = (LeatherArmorMeta) result.getItemMeta();
			armorMeta.setColor(LeatherColor.getColor(leather, cactusLeather));
			result.setItemMeta(armorMeta);
			event.getInventory().setResult(result);
		}
	}
}
