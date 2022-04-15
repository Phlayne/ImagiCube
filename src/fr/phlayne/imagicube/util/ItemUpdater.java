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
import fr.phlayne.imagicube.item.FoodProperties;
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
					boolean isUncraftable = nbti.hasKey(NBTUtil.CANNOT_BE_UNCRAFTED)
							? nbti.getBoolean(NBTUtil.CANNOT_BE_UNCRAFTED)
							: false;
					String forcedColor = nbti.hasKey("imagicube.forced_color")
							? nbti.getString("imagicube.forced_color")
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
						// This method is deprecated because it should only be used in this plugin.
						Durability.setDurability(nbti, durability, Durability.getPercentDurability(nbti));
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
							// Change the old Damage to the new CustomModelData in order to change the
							// texture.
							if (!nbti.hasKey(NBTUtil.UPDATEVERSION))
								Durability.setDurability(nbti, durability, Durability.getPercentDurability(nbti));
							if (!cosmeticEffect.equals("none"))
								NBTUtil.addLore(nbti, "imagicube.cosmetic_effect." + cosmeticEffect, false, false,
										false, false, true, Color.YELLOW);
							return nbti.getItem();
						}
					} else
						/*
						 * if (item.getType() == Material.DIAMOND_SWORD && item instanceof Damageable &&
						 * ((Damageable) item).getDamage() > 0) {
						 * 
						 * Hats - TODO Move it to ImagiCubeHats
						 * 
						 * if (Hat.isHat(item)) { Hat hat = Hat.getHat(item); if (!hat.equals(Hat.NONE))
						 * { ItemStack result = hat.getHat(); NBTTagCompound tag =
						 * NBTUtil.getTag(result); setValues(tag, 0, null, forcedColor, cosmeticEffect,
						 * itemName, 0); tag.setInt(NBTUtil.UPDATEVERSION, updateVersion); // Change the
						 * old Damage to the new CustomModelData in order to change the // texture. if
						 * (!tag.hasKey(NBTUtil.UPDATEVERSION) || tag.getInt(NBTUtil.UPDATEVERSION) ==
						 * 0) { item = Durability.setDurability(item, 0); tag.setInt("CustomModelData",
						 * durability); } item = NBTUtil.setTag(result, tag); if
						 * (!cosmeticEffect.equals("none")) item = NBTUtil.addLore(item,
						 * "imagicube.cosmetic_effect." + cosmeticEffect, false, false, false, false,
						 * true, Color.YELLOW); } } else
						 */

						/*
						 * Magic Items - TODO Move it to ImagiCubeSpells
						 * 
						 * if (nbti.hasKey(NBTUtil.ITEM_TYPE) && Arrays.asList("scepter", "parchment",
						 * "globe") .contains(nbti.getString(NBTUtil.ITEM_TYPE))) { String itemType =
						 * nbti.getString(NBTUtil.ITEM_TYPE); item =
						 * ItemList.createSpellElement(itemType.equals("scepter") ? SpellType.RAY :
						 * itemType.equals("parchment") ? SpellType.SELF : SpellType.ZONE);
						 * NBTTagCompound tag = nbti; setValues(tag, repairCost, enchantments,
						 * forcedColor, cosmeticEffect, itemName, color); } else { int damage =
						 * nbti.getInt("Damage"); switch (damage) { case 1: case 2: case 3: String spell
						 * = nbti.hasKey(NBTUtil.SPELL) ? nbti.getString(NBTUtil.SPELL) : ""; if (spell
						 * != "") { String[] s = spell.split(";"); if (s.length < 4) {
						 * Bukkit.getLogger().log(Level.SEVERE, playerName +
						 * " : Could not update following item : " + item.getType().getKey().getKey() +
						 * nbti.toString()); } else { String type = s[1].split("/")[0]; String property
						 * = s[0].split("/")[0]; int level = Integer.parseInt(s[0].split("/")[1]); item
						 * = SpellUtil.createSpell( type.equals("ray") ? SpellType.RAY :
						 * type.equals("zone") ? SpellType.ZONE : SpellType.SELF, property, level);
						 * NBTTagCompound tag = nbti; int newDurability = tag.hasKey(NBTUtil.DURABILITY)
						 * ? tag.getInt(NBTUtil.DURABILITY) : 0; setValues(tag, repairCost,
						 * enchantments, forcedColor, cosmeticEffect, itemName, 0);
						 * tag.setInt(NBTUtil.UPDATEVERSION, updateVersion); item = NBTUtil.setTag(item,
						 * tag); item = Durability.setDurability(item, newDurability); if
						 * (!cosmeticEffect.equals("none")) item = NBTUtil.addLore(item,
						 * "imagicube.cosmetic_effect." + cosmeticEffect, false, false, false, false,
						 * true, Color.YELLOW); } } break; default: NBTTagCompound tag = nbti; int
						 * newDurability = tag.hasKey(NBTUtil.DURABILITY) ?
						 * tag.getInt(NBTUtil.DURABILITY) : 0; WeaponProperty wp =
						 * WeaponProperty.getWeaponProperty(tag); if (wp != null) item =
						 * WeaponRecipes.setWeaponValues(wp); tag = nbti; setValues(tag, repairCost,
						 * enchantments, forcedColor, cosmeticEffect, itemName, 0);
						 * tag.setInt(NBTUtil.UPDATEVERSION, updateVersion); item = NBTUtil.setTag(item,
						 * tag); item = Durability.setDurability(item, newDurability); if
						 * (!cosmeticEffect.equals("none")) item = NBTUtil.addLore(item,
						 * "imagicube.cosmetic_effect." + cosmeticEffect, false, false, false, false,
						 * true, Color.YELLOW); } } }
						 */
						NBTUtil.removeUselessLines(nbti);
					if ((cause.equals(ItemUpdatingCause.VILLAGER) || isUncraftable
							|| nbti.hasKey(NBTUtil.CANNOT_BE_UNCRAFTED)) && CraftingEvents.uncraft(item) != null) {
						nbti.setBoolean(NBTUtil.CANNOT_BE_UNCRAFTED,
								nbti.hasKey(NBTUtil.CANNOT_BE_UNCRAFTED) ? nbti.getBoolean(NBTUtil.CANNOT_BE_UNCRAFTED)
										: true);
						NBTUtil.addLore(nbti, "imagicube.cannot.uncraft", false, false, false, false, true, Color.BLUE);
						return nbti.getItem();
					}
				}
			} else if (item.getType().isEdible() || FoodProperties.getFoodProperty(item) != null) {
				FoodProperties fp = FoodProperties.getFoodProperty(item);
				if (fp != null) {
					item = FoodProperties.setFoodName(item);
					if (item.getType().isEdible())
						item = FoodProperties.setFoodNeededLore(item, fp.getFoodLevel(), fp.getSaturation());
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
						item = FoodProperties.setFoodNeededLore(item, food, saturation);
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
			nbti.setString("imagicube.forced_color", forcedColor);
		if (!cosmeticEffect.equals("none"))
			nbti.setString("imagicube.cosmetic_effect", cosmeticEffect);
		Durability.setDurability(nbti, durability,
				Durability.getPercentDurability(nbti, durability, Durability.getMaxDurability(nbti)));
	}

}
