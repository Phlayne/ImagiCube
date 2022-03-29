package fr.phlayne.imagicube.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.phlayne.imagicube.data.AddonList;

public class ImagiCubeLoadingEvent extends Event {

	private static final HandlerList HANDLERS = new HandlerList();

	protected boolean cancelled;
	protected AddonList addonList;

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

	public ImagiCubeLoadingEvent(AddonList addonList) {
		this.cancelled = false;
		this.addonList = addonList;
	}

	public AddonList getAddonList() {
		return this.addonList;
	}
}
