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
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.craftbehaviour.FuseResult;
import fr.phlayne.imagicube.craftbehaviour.RepairWithSimilarItemScript;
import fr.phlayne.imagicube.exception.CannotUpdateItemException;
import fr.phlayne.imagicube.item.Durability;
import fr.phlayne.imagicube.item.ItemUpdatingCause;
import fr.phlayne.imagicube.util.ItemUpdater;
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

	@EventHandler
	public void repairItemsOnCraft(PrepareItemCraftEvent event) {
		ItemStack[] matrix = event.getInventory().getMatrix();
		int itemAmount = 0;
		NBTItem nbti1 = null;
		NBTItem nbti2 = null;
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i] != null && !matrix[i].getType().equals(Material.AIR)) {
				if (itemAmount == 0)
					nbti1 = new NBTItem(matrix[i]);
				if (itemAmount == 1)
					nbti2 = new NBTItem(matrix[i]);
				if (itemAmount > 1)
					return;
				itemAmount++;
			}
		}
		if (itemAmount < 2)
			return;
		NBTItem result = new NBTItem(nbti1.getItem());
		result.removeKey("Enchantments");
		result.removeKey(NBTUtil.FORCED_COLOR);
		// TODO Find a way to remove the custom name. For the moment, I don't have a
		// method to get an item's original name if it has been renamed.
		FuseResult fuseResult = new RepairWithSimilarItemScript().getResult(nbti1, nbti2, result, null);
		if (fuseResult.showResult() && !fuseResult.resultCancelled())
			event.getInventory().setResult(fuseResult.getResultItem());
		else if (nbti1.getItem().getType().equals(nbti2.getItem().getType()) && nbti1.hasKey("Damage"))
			// If minecraft would repair these items
			event.getInventory().setResult(null);
	}

	@EventHandler
	public void onThingOnAStickCraft(PrepareItemCraftEvent event) {
		ItemStack[] matrix = event.getInventory().getMatrix();

		int[] values = matrix.length == 9 ? new int[] { 0, 1, 3, 4 } : new int[] { 0 };
		for (int i : values) {
			if (matrix[i] != null && !matrix[i].getType().equals(Material.AIR)) {
				NBTItem nbti = new NBTItem(matrix[i]);
				if (nbti.hasKey(NBTUtil.ITEM_TYPE)) {
					String itemType = nbti.getString(NBTUtil.ITEM_TYPE);
					if (!nbti.hasKey(NBTUtil.DURABILITY) || nbti.getInteger(NBTUtil.DURABILITY) > 0)
						return;
					if (itemType.equals("fishing_rod") && matrix[i + 4] != null) {
						for (int j = 0; j < 9; j++)
							if (!(matrix[j] == null || matrix[j].getType().equals(Material.AIR))
									&& (j != i && j != i + 4))
								return;
						Material material = matrix[i + 4].getType();
						NBTItem resultNBTI;
						// TODO Replace this with an addonList "Map<Material, String>
						// materialTofishingRodType" after this
						switch (material) {
						case CARROT:
							resultNBTI = new NBTItem(new ItemStack(Material.CARROT_ON_A_STICK));
							break;
						case WARPED_FUNGUS:
							resultNBTI = new NBTItem(new ItemStack(Material.WARPED_FUNGUS_ON_A_STICK));
							break;
						// TODO add the bait_on_a_stick, a fishing rod only for fish
						default:
							return;
						}
						ItemStack resultItem = new ItemStack(Material.AIR);
						try {
							// TODO Call events to update the item
							resultItem = ItemUpdater.updateItem(resultNBTI.getItem(), ItemUpdatingCause.CRAFT);
						} catch (CannotUpdateItemException e) {
							e.printStackTrace();
						}
						Durability.setDurability(resultNBTI, i);
						event.getInventory().setResult(resultItem);
					}
				}
			}
		}
	}

	@EventHandler
	public void onArmorDye(PrepareItemCraftEvent event) {
		ItemStack[] matrix = event.getInventory().getMatrix();
		int leatherItemAmount = 0;
		int leatherArmorIndex = 0;
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i] != null && !matrix[i].getType().equals(Material.AIR)) {
				NBTItem nbti = new NBTItem(matrix[i]);
				if (nbti.hasKey(NBTUtil.MATERIAL) && nbti.getString(NBTUtil.MATERIAL).equals("leather")) {
					leatherItemAmount += 1;
					leatherArmorIndex = i;
				}
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

	@EventHandler
	public void checkDiamondCraft(PrepareItemCraftEvent event) {
		ItemStack[] matrix = event.getInventory().getMatrix();
		if (matrix.length != 9)
			return;
		boolean canCreateDiamondBlock = true;
		boolean canCreateDiamond = true;
		for (ItemStack item : matrix) {
			if (item != null) {
				int customModelData = item.hasItemMeta()
						? item.getItemMeta().hasCustomModelData() ? item.getItemMeta().getCustomModelData() : 0
						: 0;
				switch (item.getType()) {
				case DIAMOND:
					if (event.getRecipe() != null)
						if (event.getRecipe().getResult().getType().equals(Material.DIAMOND_BLOCK)) {
							if (customModelData == 3)
								canCreateDiamondBlock = false;
							else
								canCreateDiamond = false;
						} else if (customModelData != 0)
							event.getInventory().setResult(null);
					break;
				default:
					break;
				}
				if (event.getRecipe() != null
						&& event.getRecipe().getResult().getType().equals(Material.DIAMOND_BLOCK)) {
					if (canCreateDiamond)
						event.getInventory().setResult(new ItemStack(Material.DIAMOND));
					else if (canCreateDiamondBlock)
						event.getInventory().setResult(new ItemStack(Material.DIAMOND_BLOCK));
					else
						event.getInventory().setResult(null);
				}
			}
		}
	}
}
