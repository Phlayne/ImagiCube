package fr.phlayne.imagicube.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class ItemRequestEvent extends Event {

	private static final HandlerList HANDLERS = new HandlerList();

	protected String itemName;
	protected ItemStack requestedItem;

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

	public ItemRequestEvent(String itemName) {
		this.itemName = itemName;
		this.requestedItem = null;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setRequestedItem(ItemStack item) {
		this.requestedItem = item;
	}

	public ItemStack getRequestedItem() {
		return this.requestedItem;
	}

}