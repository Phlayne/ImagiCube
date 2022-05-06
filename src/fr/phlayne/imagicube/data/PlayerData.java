package fr.phlayne.imagicube.data;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import fr.phlayne.imagicube.Reference;

public class PlayerData implements Listener {

	protected static final File playerDataFile = new File("plugins/" + Reference.PLUGIN_NAME + "/playerdata.yml");
	protected static final FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerDataFile);

	public static void save(Player player, String path, Object value) {
		playerData.set(player.getUniqueId() + "." + path, value);
		try {
			playerData.save(playerDataFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Object get(Player player, String path) {
		return playerData.get(player.getUniqueId() + "." + path);
	}

}
