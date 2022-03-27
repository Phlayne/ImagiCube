package fr.phlayne.imagicube.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import fr.phlayne.imagicube.item.ItemUpdatingCause;

public class ItemUpdatingEvent extends Event {

	private static final HandlerList HANDLERS = new HandlerList();

	protected ItemStack itemToUpdate;
	protected ItemStack result;
	protected boolean updated;
	protected ItemUpdatingCause cause;

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

	public ItemUpdatingEvent(ItemStack itemToUpdate, ItemUpdatingCause cause) {
		this.itemToUpdate = itemToUpdate;
		this.cause = cause;
		this.updated = false;
	}

	public ItemStack getItemToUpdate() {
		return this.getItemToUpdate();
	}

	public ItemUpdatingCause getCause() {
		return this.cause;
	}

	public void setResult(ItemStack result) {
		this.result = result;
	}
	
	public void update() {
		this.updated = true;
	}
	
	public boolean isUpdated() {
		return this.updated;
	}

	public ItemStack getResult() {
		return this.result;
	}

}
