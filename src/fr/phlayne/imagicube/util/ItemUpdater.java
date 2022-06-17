package fr.phlayne.imagicube.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTCompoundList;
import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.crafts.armor.ArmorRecipes;
import fr.phlayne.imagicube.crafts.armor.WeaponRecipes;
import fr.phlayne.imagicube.data.Config;
import fr.phlayne.imagicube.events.CraftingEvents;
import fr.phlayne.imagicube.exception.CannotUpdateItemException;
import fr.phlayne.imagicube.item.ArmorProperty;
import fr.phlayne.imagicube.item.Durability;
import fr.phlayne.imagicube.item.FoodProperty;
import fr.phlayne.imagicube.item.ItemUpdatingCause;
import fr.phlayne.imagicube.item.WeaponProperty;
import fr.phlayne.imagicube.util.SimpleJSON.Color;

public class ItemUpdater {

	public static Random random = new Random();

	public static final List<Material> MaterialToUpdate = Arrays.asList(Material.DIAMOND_SWORD, Material.DIAMOND_HOE,
			Material.DIAMOND_AXE, Material.DIAMOND_PICKAXE, Material.DIAMOND_SHOVEL, Material.GOLDEN_SWORD,
			Material.GOLDEN_HOE, Material.GOLDEN_AXE, Material.GOLDEN_PICKAXE, Material.GOLDEN_SHOVEL,
			Material.IRON_SWORD, Material.IRON_HOE, Material.IRON_AXE, Material.IRON_PICKAXE, Material.IRON_SHOVEL,
			Material.STONE_SWORD, Material.STONE_HOE, Material.STONE_AXE, Material.STONE_PICKAXE, Material.STONE_SHOVEL,
			Material.WOODEN_SWORD, Material.WOODEN_HOE, Material.WOODEN_AXE, Material.WOODEN_PICKAXE,
			Material.WOODEN_SHOVEL, Material.BOW, Material.CROSSBOW, Material.ELYTRA, Material.LEATHER_BOOTS,
			Material.LEATHER_LEGGINGS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET, Material.CHAINMAIL_BOOTS,
			Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_HELMET, Material.IRON_BOOTS,
			Material.IRON_LEGGINGS, Material.IRON_CHESTPLATE, Material.IRON_HELMET, Material.GOLDEN_BOOTS,
			Material.GOLDEN_LEGGINGS, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_HELMET, Material.DIAMOND_BOOTS,
			Material.DIAMOND_LEGGINGS, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_HELMET, Material.TURTLE_HELMET,
			Material.SHIELD, Material.TRIDENT, Material.FLINT_AND_STEEL, Material.FISHING_ROD,
			Material.CARROT_ON_A_STICK, Material.SHEARS, Material.WARPED_FUNGUS_ON_A_STICK, Material.NETHERITE_SWORD,
			Material.NETHERITE_SHOVEL, Material.NETHERITE_PICKAXE, Material.NETHERITE_AXE, Material.NETHERITE_HOE,
			Material.NETHERITE_HELMET, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_LEGGINGS,
			Material.NETHERITE_BOOTS);

	public static final int updateVersion = 1;

	public static ItemStack updateItem(ItemStack item, ItemUpdatingCause cause) throws CannotUpdateItemException {
		if (item != null && !item.getType().equals(Material.AIR)) {
			if (MaterialToUpdate.contains(item.getType())) {
				NBTItem nbti = new NBTItem(item);
				if ((!nbti.hasKey(NBTUtil.ITEM_TYPE) || !nbti.hasKey(NBTUtil.DURABILITY))
						|| (nbti.hasKey(NBTUtil.UPDATEVERSION) ? nbti.getInteger(NBTUtil.UPDATEVERSION) != updateVersion
								: false)) {

					// Properties about the ItemStack

					String itemName = "";
					if (nbti.hasKey("display") && nbti.getCompound("display").hasKey("Name"))
						itemName = nbti.getCompound("display").getString("Name");
					int repairCost = nbti.hasKey("RepairCost") ? nbti.getInteger("RepairCost") : 0;
					int durability = nbti.hasKey(NBTUtil.DURABILITY) ? nbti.getInteger(NBTUtil.DURABILITY)
							: nbti.hasKey("Damage") ? nbti.getInteger("Damage") : 0;
					NBTCompoundList enchantments = nbti.hasKey("Enchantments") ? nbti.getCompoundList("Enchantments")
							: null;
					String forcedColor = nbti.hasKey(NBTUtil.FORCED_COLOR)
							? nbti.getString(NBTUtil.FORCED_COLOR)
							: "none";
					String cosmeticEffect = nbti.hasKey("imagicube.cosmetic_effect")
							? nbti.getString("imagicube.cosmetic_effect")
							: "none";
					int color = -1;
					if (nbti.hasKey("display")) {
						if (nbti.getCompound("display").hasKey("color"))
							color = nbti.getCompound("display").getInteger("color");
					}

					// Bows, crossbows and other special tools with durability

					if (item.getType().equals(Material.BOW) || item.getType().equals(Material.CROSSBOW)
							|| item.getType().equals(Material.ELYTRA) || item.getType().equals(Material.SHIELD)
							|| item.getType().equals(Material.FLINT_AND_STEEL) || item.getType().equals(Material.SHEARS)
							|| item.getType().equals(Material.FISHING_ROD)
							|| item.getType().equals(Material.CARROT_ON_A_STICK)
							|| item.getType().equals(Material.WARPED_FUNGUS_ON_A_STICK)) {
						nbti.setBoolean("Unbreakable", true);
						nbti.setInteger("CustomModelData", 0);
						nbti.setShort("HideFlags", (short) 6);
						nbti.setInteger(NBTUtil.UPDATEVERSION, updateVersion);
						String itemType = null;
						switch (item.getType()) {
						case BOW:
							itemType = "bow";
							break;
						case CROSSBOW:
							itemType = "crossbow";
							break;
						case ELYTRA:
							itemType = "elytra";
							break;
						case SHIELD:
							itemType = "shield";
							break;
						case FLINT_AND_STEEL:
							itemType = "flint_and_steel";
							break;
						case SHEARS:
							itemType = "shears";
							break;
						case FISHING_ROD:
							itemType = "fishing_rod";
							break;
						case CARROT_ON_A_STICK:
							itemType = "carrot_on_a_stick";
							break;
						case WARPED_FUNGUS_ON_A_STICK:
							itemType = "warped_fungus_on_a_stick";
							break;
						default:
							break;
						}
						if (itemType != null)
							nbti.setString(NBTUtil.ITEM_TYPE, itemType);
						NBTCompound displayTag = nbti.getOrCreateCompound("display");
						NBTUtil.addLore(displayTag, new SimpleJSON()
								.add("", false, false, false, false, SimpleJSON.Color.WHITE, false).convert());
						Durability.setDurability(nbti, durability);
						NBTUtil.removeUselessLines(nbti);
						if (cause.equals(ItemUpdatingCause.VILLAGER))
							setUncraftImpossible(nbti);
						return nbti.getItem();
					} else

					// Weapons and armors

					if (!nbti.hasKey(NBTUtil.ITEM_TYPE)) {
						WeaponProperty weaponProperty = WeaponProperty.getWeaponProperty(nbti);
						ArmorProperty armorProperty = ArmorProperty.getVanillaArmorProperty(item.getType());
						if (armorProperty == null && weaponProperty == null)
							weaponProperty = WeaponProperty.getVanillaWeaponProperty(item.getType());
						if (armorProperty == null && weaponProperty == null
								&& !Arrays
										.asList(Material.BOW, Material.CROSSBOW, Material.ELYTRA, Material.SHIELD,
												Material.FLINT_AND_STEEL, Material.SHEARS, Material.FISHING_ROD,
												Material.CARROT_ON_A_STICK, Material.WARPED_FUNGUS_ON_A_STICK)
										.contains(item.getType())) {
							throw new CannotUpdateItemException(cause + " could not update the following Item:", item);
						} else {
							if (weaponProperty != null)
								nbti = new NBTItem(WeaponRecipes.setWeaponValues(weaponProperty));
							else if (armorProperty != null)
								nbti = new NBTItem(ArmorRecipes.setArmorValues(armorProperty));
							setValues(nbti, repairCost, enchantments, forcedColor, cosmeticEffect, itemName, color,
									durability);
							nbti.setInteger(NBTUtil.UPDATEVERSION, updateVersion);
							if (!nbti.hasKey(NBTUtil.UPDATEVERSION))
								Durability.setDurability(nbti, durability);
							if (!cosmeticEffect.equals("none"))
								NBTUtil.addLore(nbti, "imagicube.cosmetic_effect." + cosmeticEffect, false, false,
										false, false, true, Color.YELLOW);
							NBTUtil.removeUselessLines(nbti);
							if (cause.equals(ItemUpdatingCause.VILLAGER))
								setUncraftImpossible(nbti);
							return nbti.getItem();
						}
					}
				}
			} else if (item.getType().isEdible() || FoodProperty.getFoodProperty(item) != null) {
				FoodProperty fp = FoodProperty.getFoodProperty(item);
				if (fp != null) {
					item = FoodProperty.setFoodName(item);
					if (item.getType().isEdible())
						item = FoodProperty.setFoodNeededLore(item, fp.getFoodLevel(), fp.getSaturation());
					return item;
				} else if (item.getType().isEdible()) {
					int food = 0;
					double saturation = 0;
					String foodPath = item.getType().getKey().getNamespace() + "." + item.getType().getKey().getKey()
							+ ".nutrition";
					String saturationPath = item.getType().getKey().getNamespace() + "."
							+ item.getType().getKey().getKey() + ".saturationModifier";
					for (FileConfiguration foodInfo : Config.getConfigs(Config.FOOD_PROPERTIES)) {
						if (foodInfo.contains(foodPath))
							food = foodInfo.getInt(foodPath);
						if (foodInfo.contains(saturationPath))
							saturation = foodInfo.getDouble(saturationPath) * food * 2;
					}
					if (food != 0 || saturation != 0)
						item = FoodProperty.setFoodNeededLore(item, food, saturation);
					return item;
				}
			}
		}
		return null;
	}

	public static void setValues(NBTItem nbti, int repairCost, NBTCompoundList enchantments, String forcedColor,
			String cosmeticEffect, String itemName, int color, int durability) {
		boolean shouldColor = color >= 0;
		boolean shouldName = itemName != null && itemName != "";
		if (shouldColor || shouldName) {
			NBTCompound display = nbti.getOrCreateCompound("display");
			if (shouldColor)
				display.setInteger("color", color);
			if (shouldName)
				display.setString("Name", itemName);
		}
		if (repairCost > 0)
			nbti.setInteger("RepairCost", repairCost);
		if (enchantments != null)
			nbti.getCompoundList("Enchantments").addAll(enchantments);
		if (!forcedColor.equals("none"))
			nbti.setString(NBTUtil.FORCED_COLOR, forcedColor);
		if (!cosmeticEffect.equals("none"))
			nbti.setString("imagicube.cosmetic_effect", cosmeticEffect);
		Durability.setDurability(nbti, durability);
	}

	public static void setUncraftImpossible(NBTItem nbti) {
		if (nbti.hasKey(NBTUtil.CANNOT_BE_UNCRAFTED) ? nbti.getBoolean(NBTUtil.CANNOT_BE_UNCRAFTED)
				: false || CraftingEvents.uncraft(nbti.getItem()) != null) {
			nbti.setBoolean(NBTUtil.CANNOT_BE_UNCRAFTED,
					nbti.hasKey(NBTUtil.CANNOT_BE_UNCRAFTED) ? nbti.getBoolean(NBTUtil.CANNOT_BE_UNCRAFTED) : true);
			NBTUtil.addLore(nbti, "imagicube.cannot.uncraft", false, false, false, false, true, Color.BLUE);
		}
	}

}
