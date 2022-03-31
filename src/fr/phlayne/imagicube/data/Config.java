package fr.phlayne.imagicube.data;

import java.io.File;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.phlayne.imagicube.ImagiCube;
import fr.phlayne.imagicube.Reference;

public class Config {

	public static final String FOOD_PROPERTIES = "food_properties";

	public static FileConfiguration getConfig() {
		return YamlConfiguration.loadConfiguration(new File("plugins/" + Reference.PLUGIN_NAME + "/config.yml"));
	}

	public static FileConfiguration getConfig(String fileName) {
		return YamlConfiguration
				.loadConfiguration(new File("plugins/" + Reference.PLUGIN_NAME + "/" + fileName + ".yml"));
	}

	public static FileConfiguration getConfig(String plugin, String fileName) {
		return YamlConfiguration.loadConfiguration(new File("plugins/" + plugin + "/" + fileName + ".yml"));
	}

	public static void copyFilesIfAbsent() {
		String configPath = "config.yml";
		String foodPropertiesPath = FOOD_PROPERTIES + ".yml";
		String folderPath = "plugins/" + Reference.PLUGIN_NAME + "/";
		if (!new File(folderPath + configPath).exists())
			ImagiCube.getInstance().saveResource(configPath, false);
		if (!new File(folderPath + foodPropertiesPath).exists())
			ImagiCube.getInstance().saveResource(foodPropertiesPath, false);
	}

	public static void checkConfigs() {
		FileConfiguration config = getConfig();
		boolean missingValuesConfig = false;
		for (String key : Arrays.asList("prismarineTools", "stoneToolsVariants", "woodToolsVariants",
				"concretePowderToConcreteCraftWithBucket", "lossless_mending_system")) {
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
		if (missingValuesConfig)
			Bukkit.getLogger().warning("Please add these keys in the file.");
	}

	public static void warnKeyAbsent(String key) {
		Bukkit.getLogger().warning("The key \"" + key + "\" is absent from config.yml");
	}

	public static void setDefaults() {
		// TODO
		// Sets defaults values in the files if a key is absent
		// This would replace the manual adding required in checkConfigs
	}

}
