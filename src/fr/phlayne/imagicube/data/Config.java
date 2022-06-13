package fr.phlayne.imagicube.data;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.phlayne.imagicube.ImagiCube;
import fr.phlayne.imagicube.Reference;
import fr.phlayne.imagicube.event.ConfigRequestEvent;

public class Config {

	public static final String FOOD_PROPERTIES = "food_properties";
	public static final String DURABILITY = "durability";
	public static final String ENCHANTMENTS = "enchantments";
	public static final String WORLD_COLORS = "world_colors";

	public static FileConfiguration getConfig() {
		return YamlConfiguration.loadConfiguration(new File("plugins/" + Reference.PLUGIN_NAME + "/config.yml"));
	}

	public static List<FileConfiguration> getConfigs(String fileName) {
		ConfigRequestEvent cre = new ConfigRequestEvent(fileName);
		Bukkit.getPluginManager().callEvent(cre);
		return cre.getConfigFiles();
	}

	public static FileConfiguration getConfig(String plugin, String fileName) {
		return YamlConfiguration.loadConfiguration(new File("plugins/" + plugin + "/" + fileName + ".yml"));
	}

	public static void copyFilesIfAbsent() {
		String configPath = "config.yml";
		String foodPropertiesPath = FOOD_PROPERTIES + ".yml";
		String durabilityPath = DURABILITY + ".yml";
		String enchantmentsPath = ENCHANTMENTS + ".yml";
		String worldColorsPath = WORLD_COLORS + ".yml";
		String folderPath = "plugins/" + Reference.PLUGIN_NAME + "/";
		if (!new File(folderPath + configPath).exists())
			ImagiCube.getInstance().saveResource(configPath, false);
		if (!new File(folderPath + foodPropertiesPath).exists())
			ImagiCube.getInstance().saveResource(foodPropertiesPath, false);
		if (!new File(folderPath + durabilityPath).exists())
			ImagiCube.getInstance().saveResource(durabilityPath, false);
		if (!new File(folderPath + enchantmentsPath).exists())
			ImagiCube.getInstance().saveResource(enchantmentsPath, false);
		if (!new File(folderPath + worldColorsPath).exists())
			ImagiCube.getInstance().saveResource(worldColorsPath, false);
	}

	public static void checkConfigs() {
		FileConfiguration config = getConfig();
		boolean missingValuesConfig = false;
		for (String key : Arrays.asList("prismarineTools", "stoneToolsVariants", "woodToolsVariants",
				"concrete_powder_to_concrete_craft_with_bucket", "lossless_mending_system",
				"craftable_chainmail_armor_with_chains", "ender_pearl_cooldown", "concrete_powder_interacts_with_water",
				"boost_quartz_drop", "farmland_not_breaking_on_jump", "player_velocity_on_attack_modifier",
				"thorns_durability_modifier", "seconds_before_afk", "sitting_on_blocks")) {
			if (!config.contains(key)) {
				warnKeyAbsent(key);
				missingValuesConfig = true;
			}
		}
		boolean containsCustomResourcePackLink = config.contains("custom_resource_pack_link");
		if (!containsCustomResourcePackLink) {
			warnKeyAbsent("custom_resource_pack_link");
			missingValuesConfig = true;
		}
		if (!containsCustomResourcePackLink || config.getBoolean("custom_resource_pack_link")) {
			if (!config.contains("resource_pack_link")) {
				warnKeyAbsent("resource_pack_link");
				missingValuesConfig = true;
			}
		}
		boolean containsWeightModifier = config.contains("weight_modifier");
		if (!containsWeightModifier) {
			warnKeyAbsent("weight_modifier");
			missingValuesConfig = true;
		}
		if (!containsWeightModifier || config.getBoolean("weight_modifier")) {
			for (String key : Arrays.asList("weight_modifier_in_creative", "weight_gliding_modifier")) {
				if (!config.contains(key)) {
					warnKeyAbsent(key);
					missingValuesConfig = true;
				}
			}
		}
		boolean fireAspectPickaxesDropMoltenMaterial = config.contains("fire_aspect_pickaxes_drop_molten_material");
		if (!fireAspectPickaxesDropMoltenMaterial) {
			warnKeyAbsent("fire_aspect_pickaxes_drop_molten_material");
			missingValuesConfig = true;
		}
		if (!fireAspectPickaxesDropMoltenMaterial || config.getBoolean("fire_aspect_pickaxes_drop_molten_material")) {
			if (!config.contains("fire_aspect_pickaxe_drops_exp")) {
				warnKeyAbsent("fire_aspect_pickaxe_drops_exp");
				missingValuesConfig = true;
			}if(!config.contains("chance_of_dropping_molten_ore_by_fire_aspect_level")) {
				warnKeyAbsent("chance_of_dropping_molten_ore_by_fire_aspect_level");
				missingValuesConfig = true;
			}
		}
		if (missingValuesConfig)
			Bukkit.getLogger().warning("Please add these keys in the file.");
	}

	public static void warnKeyAbsent(String key) {
		Bukkit.getLogger().warning("The key \"" + key + "\" is absent from config.yml");
	}

}
