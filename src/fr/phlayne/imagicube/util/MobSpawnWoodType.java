package fr.phlayne.imagicube.util;

import java.io.File;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.configuration.file.FileConfiguration;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.Reference;
import fr.phlayne.imagicube.crafts.armor.WeaponRecipes;
import fr.phlayne.imagicube.data.Config;
import fr.phlayne.imagicube.item.Durability;
import fr.phlayne.imagicube.item.WeaponProperty;

public class MobSpawnWoodType {

	public static String getWoodType(Biome biome) {
		// If config exists
		String configName = "woodtype_by_biome";
		File file = new File("plugins/" + Reference.PLUGIN_NAME + "/" + configName + ".yml");
		if (file.exists()) {
			FileConfiguration config = Config.getConfig(Reference.PLUGIN_NAME, configName);
			if (config.contains(biome.getKey().toString().replace(":", ".")))
				return config.getString(biome.getKey().toString().replace(":", "."));
		}
		return null;
	}

	public static void changeWoodType(NBTItem nbti, String woodType) {
		WeaponProperty wp = WeaponProperty.getWeaponProperty(nbti);
		if (wp != null && wp.getType().equals("oak")) {
			WeaponProperty newWeaponProperty = WeaponProperty.getWeaponProperty(wp.getType(), woodType);
			int durability = nbti.getInteger(NBTUtil.DURABILITY);
			WeaponRecipes.setWeaponValues(nbti, newWeaponProperty);
			Durability.setDurability(nbti, durability);
		}
	}

	public static void changeLeatherColor(NBTItem nbti, String woodType) {
		if (Arrays.asList(Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.LEATHER_CHESTPLATE,
				Material.LEATHER_HELMET).contains(nbti.getItem().getType())) {
			Integer color = null;
			switch (woodType) {
			case "oak":
				color = 8807718;
				break;
			case "spruce":
				color = 6438937;
				break;
			case "birch":
				color = 13352579;
				break;
			case "jungle":
				color = 10643518;
				break;
			case "acacia":
				color = 10571554;
				break;
			case "dark_oak":
				color = 4796181;
				break;
			case "crimson":
				color = 7746637;
				break;
			case "warped":
				color = 4746603;
				break;
			default:
				break;
			}
			if (color != null) {
				NBTCompound display = nbti.getOrCreateCompound("display");
				display.setInteger("color", color);
			}
		}
	}
}
