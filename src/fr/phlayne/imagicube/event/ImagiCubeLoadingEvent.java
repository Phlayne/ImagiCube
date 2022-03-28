package fr.phlayne.imagicube.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.phlayne.imagicube.item.ItemList;

public class ImagiCubeLoadingEvent extends Event {

	private static final HandlerList HANDLERS = new HandlerList();

	protected boolean cancelled;
	protected ItemList itemList;

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

	public ImagiCubeLoadingEvent(ItemList itemList) {
		this.cancelled = false;
		this.itemList = itemList;
	}

	public ItemList getItemList() {
		return this.itemList;
	}
}
