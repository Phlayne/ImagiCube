package fr.phlayne.imagicube.craftbehaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.SmithingRecipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTCompoundList;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTList;
import fr.phlayne.imagicube.ImagiCube;
import fr.phlayne.imagicube.crafts.armor.ArmorRecipes;
import fr.phlayne.imagicube.crafts.armor.WeaponRecipes;
import fr.phlayne.imagicube.item.ArmorProperty;
import fr.phlayne.imagicube.item.Durability;
import fr.phlayne.imagicube.item.WeaponProperty;
import fr.phlayne.imagicube.util.NBTUtil;
import fr.phlayne.imagicube.util.SimpleJSON;
import fr.phlayne.imagicube.util.SimpleJSON.Color;

public class DiamondToNetheriteScript implements SmithScript {

	public void init() {
		Bukkit.getScheduler().scheduleSyncDelayedTask(ImagiCube.getInstance(), new Runnable() {
			@Override
			public void run() {
				List<Material> materialChoice = new ArrayList<Material>();
				materialChoice.addAll(ImagiCube.getInstance().getAddonList().getWeapons().stream()
						.filter(weapon -> weapon.getMaterial().equals("diamond"))
						.map(weapon -> weapon.getBukkitMaterial()).collect(Collectors.toList()));
				SmithingRecipe recipe = new SmithingRecipe(
						new NamespacedKey(ImagiCube.getInstance(), "diamond_to_netherite"), new ItemStack(Material.AIR),
						new RecipeChoice.MaterialChoice(materialChoice),
						new RecipeChoice.MaterialChoice(Material.NETHERITE_INGOT));
				Bukkit.addRecipe(recipe);
				// Allows items to be untaken from the smithing table.
			}
		});
	}

	public SmithResult getResult(NBTItem leftItem, NBTItem rightItem, NBTItem result) {
		if (leftItem != null && rightItem != null) {
			if (rightItem.getItem().getType().equals(Material.NETHERITE_INGOT) && leftItem.hasKey(NBTUtil.MATERIAL)
					&& leftItem.getString(NBTUtil.MATERIAL).equals("diamond") && leftItem.hasKey(NBTUtil.ITEM_TYPE)) {
				WeaponProperty wp = WeaponProperty.getWeaponProperty(leftItem.getString(NBTUtil.ITEM_TYPE),
						"netherite");
				ArmorProperty ap = ArmorProperty.getArmorProperty(leftItem.getString(NBTUtil.ITEM_TYPE), "netherite");
				if (wp != null || ap != null) {
					if (wp != null)
						result = new NBTItem(WeaponRecipes.setWeaponValues(wp));
					else
						result = new NBTItem(ArmorRecipes.setArmorValues(ap));
					NBTCompound display = leftItem.getOrCreateCompound("display");
					int repairCost = leftItem.hasKey("RepairCost") ? leftItem.getInteger("RepairCost") : 0;
					String forcedColor = leftItem.hasKey(NBTUtil.FORCED_COLOR)
							? leftItem.getString(NBTUtil.FORCED_COLOR)
							: null;
					NBTCompoundList enchantments = leftItem.getCompoundList("Enchantments");
					if (leftItem.hasKey("display")) {
						display = leftItem.getCompound("display");
						NBTUtil.readName(result, null);
						if (display.hasKey("Name")) {
							SimpleJSON simpleJsonName = new SimpleJSON();
							JsonElement name = JsonParser
									.parseString(leftItem.getCompound("display").getString("Name"));
							if (name.isJsonArray())
								for (JsonElement jsonObj : name.getAsJsonArray())
									// We do not check for null because these items always have a name, because it
									// is set when updating its durability (needed to display the color)
									simpleJsonName.add(NBTUtil.readAndReplaceTranslatedText(jsonObj.getAsJsonObject(),
											forcedColor != null ? SimpleJSON.Color.get(forcedColor) : null, "diamond",
											"netherite"));
							result.setString(NBTUtil.MATERIAL, "netherite");
							result.getOrCreateCompound("display").setString("Name", simpleJsonName.convert());
						}
					}
					if (repairCost > 0)
						result.setInteger("RepairCost", repairCost);
					if (forcedColor != null)
						result.setString(NBTUtil.FORCED_COLOR, forcedColor);
					if (enchantments.size() > 0)
						result.getCompoundList("Enchantments").addAll(enchantments);
					if (leftItem.hasKey(NBTUtil.CANNOT_BE_UNCRAFTED))
						result.setBoolean(NBTUtil.CANNOT_BE_UNCRAFTED,
								leftItem.getBoolean(NBTUtil.CANNOT_BE_UNCRAFTED));
					if (leftItem.hasKey("display")) {
						// Only lore stored in "CustomLore" will be copied, meaning if you want to use
						// custom lore that is specific to one item, you have to add it to CustomLore
						// too.
						// TODO Put the CustomLore in the new item's Lore and CustomLore in ItemUpdater.
						// Useless for now, I guess
						NBTList<String> lore = (leftItem.getCompound("display")).hasKey("CustomLore")
								? leftItem.getCompound("display").getStringList("CustomLore")
								: null;
						if (lore != null) {
							result.getOrCreateCompound("display").getStringList("CustomLore").addAll(lore);
							result.getOrCreateCompound("display").getStringList("Lore").addAll(lore);
						}
					}
					if (leftItem.hasKey(NBTUtil.CANNOT_BE_UNCRAFTED)
							&& leftItem.getBoolean(NBTUtil.CANNOT_BE_UNCRAFTED)) {
						NBTUtil.addLore(result, "imagicube.cannot.uncraft", false, false, false, false, true,
								Color.BLUE);
					}
					Durability.setDurability(result, leftItem.getInteger(NBTUtil.DURABILITY));
					return new SmithResult(result.getItem(), 1).showResult(true);
				}
			}
		}
		return new SmithResult(result.getItem(), 0);
	}

}
