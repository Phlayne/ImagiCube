package fr.phlayne.imagicube.event;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ConfigRequestEvent extends Event {

	private static final HandlerList HANDLERS = new HandlerList();

	protected String configName;
	protected List<FileConfiguration> configList;

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

	public ConfigRequestEvent(String configName) {
		this.configName = configName;
		this.configList = new ArrayList<FileConfiguration>();
	}

	public String getConfigName() {
		return this.configName;
	}

	public void addConfigFile(FileConfiguration config) {
		this.configList.add(config);
	}

	public List<FileConfiguration> getConfigFiles() {
		return this.configList;
	}

}
