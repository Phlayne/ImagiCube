package fr.phlayne.imagicube.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.phlayne.imagicube.event.ItemUpdatingEvent;
import fr.phlayne.imagicube.exception.CannotUpdateItemException;
import fr.phlayne.imagicube.item.ItemUpdatingCause;
import fr.phlayne.imagicube.util.ItemUpdater;

public class ItemUpdatingEvents implements Listener {

	public ItemUpdatingEvents() {
	}

	@EventHandler
	public void onInventoryOpen(InventoryOpenEvent event) {
		updateItemInInventory(event.getInventory(), ItemUpdatingCause.PLAYER);
	}

	@EventHandler
	public void onItemDrop(EntitySpawnEvent event) {
		if (event.getEntity() instanceof Item) {
			Item entityItem = (Item) event.getEntity();
			ItemUpdatingEvent itemUpdatingEvent = new ItemUpdatingEvent(entityItem.getItemStack(),
					ItemUpdatingCause.DROP_OR_SPAWN);
			Bukkit.getPluginManager().callEvent(itemUpdatingEvent);
			if (!itemUpdatingEvent.isCancelled()) {
				entityItem.setItemStack(itemUpdatingEvent.getResult());
			}
		}
	}

	public void updateItemInInventory(Inventory inv, ItemUpdatingCause cause) {
		if (inv != null) {
			for (int i = 0; i < inv.getSize(); i++) {
				ItemUpdatingEvent event = new ItemUpdatingEvent(inv.getItem(i), cause);
				Bukkit.getPluginManager().callEvent(event);
				if (event.isUpdated()) {
					inv.setItem(i, event.getResult());
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onItemUpdate(ItemUpdatingEvent event) {
		try {
			ItemStack result = ItemUpdater.updateItem(event.getItemToUpdate(), event.getCause());
			event.setResult(result);
			event.update();
			if (event.getResult() == null)
				event.setCancelled(true);
		} catch (CannotUpdateItemException e) {
			e.printStackTrace();
			event.setCancelled(true);
		}
	}
}