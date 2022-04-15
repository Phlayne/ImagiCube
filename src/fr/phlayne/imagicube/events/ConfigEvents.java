package fr.phlayne.imagicube.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.phlayne.imagicube.Reference;
import fr.phlayne.imagicube.data.Config;
import fr.phlayne.imagicube.event.ConfigRequestEvent;

public class ConfigEvents implements Listener {

	@EventHandler
	public void onConfigRequest(ConfigRequestEvent event) {
		event.addConfigFile(Config.getConfig(Reference.PLUGIN_NAME, event.getConfigName()));
	}
}
