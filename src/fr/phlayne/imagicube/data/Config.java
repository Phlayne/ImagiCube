package fr.phlayne.imagicube.data;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

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
}
