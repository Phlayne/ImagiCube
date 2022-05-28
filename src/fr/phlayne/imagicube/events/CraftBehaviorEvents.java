package fr.phlayne.imagicube.events;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.SmithingInventory;

import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.ImagiCube;
import fr.phlayne.imagicube.craftbehaviour.FuseResult;
import fr.phlayne.imagicube.craftbehaviour.FuseScript;
import fr.phlayne.imagicube.craftbehaviour.SmithResult;
import fr.phlayne.imagicube.craftbehaviour.SmithScript;

public class CraftBehaviorEvents implements Listener {

	@EventHandler
	public static void onForge(PrepareAnvilEvent event) {
		AnvilInventory anvilInventory = (AnvilInventory) event.getInventory();
		final ItemStack item1 = anvilInventory.getContents()[0];
		final ItemStack item2 = anvilInventory.getContents()[1];
		if (item1 != null && !item1.getType().equals(Material.AIR)) {
			final FuseResult fuseResult = getFuseResult(item1, item2, anvilInventory.getRenameText());
			if (fuseResult.showResult() && !fuseResult.resultCancelled() && !item1.equals(fuseResult.getResultItem())) {
				event.setResult(fuseResult.getResultItem());
				Bukkit.getScheduler().scheduleSyncDelayedTask(ImagiCube.getInstance(), new Runnable() {
					@Override
					public void run() {
						anvilInventory.setRepairCost(fuseResult.getRepairCost());
					}
				});
			} else
				sendNullResult(event);
		} else
			sendNullResult(event);
	}

	@EventHandler
	public static void onSmith(PrepareSmithingEvent event) {
		SmithingInventory smithingInventory = (SmithingInventory) event.getInventory();
		final ItemStack item1 = smithingInventory.getContents()[0];
		final ItemStack item2 = smithingInventory.getContents()[1];
		if (item1 != null && !item1.getType().equals(Material.AIR) && item2 != null
				&& !item2.getType().equals(Material.AIR)) {
			final SmithResult smithResult = getSmithResult(item1, item2);
			if (smithResult.showResult() && !smithResult.resultCancelled()
					&& !item1.equals(smithResult.getResultItem()))
				event.setResult(smithResult.getResultItem());
			else
				event.setResult(new ItemStack(Material.AIR));
		} else
			event.setResult(new ItemStack(Material.AIR));
	}

	// TODO Grindstone

	public static FuseResult getFuseResult(ItemStack item1, ItemStack item2, String newName) {
		int repairCost = 0;
		int rightItemRemovedAmount = 0;
		boolean showResult = false;
		boolean cancelResult = false;
		NBTItem nbti1 = new NBTItem(item1.clone());
		NBTItem nbti2 = null;
		if (item2 != null && !item2.getType().equals(Material.AIR))
			nbti2 = new NBTItem(item2.clone());
		ItemStack result = item1.clone();
		for (FuseScript fuseScript : ImagiCube.getInstance().addonList.fuseScripts) {
			FuseResult fuseResult = fuseScript.getResult(nbti1, nbti2, new NBTItem(result), newName);
			result = fuseResult.getResultItem();
			repairCost += fuseResult.getRepairCost();
			rightItemRemovedAmount += fuseResult.getRightItemRemovedAmount();
			showResult |= fuseResult.showResult();
			cancelResult |= fuseResult.resultCancelled();
		}
		if (cancelResult)
			return new FuseResult(new ItemStack(Material.AIR), 0, 0);
		return new FuseResult(result, rightItemRemovedAmount, repairCost).showResult(showResult);
	}

	public static SmithResult getSmithResult(ItemStack item1, ItemStack item2) {
		int rightItemRemovedAmount = 0;
		boolean showResult = false;
		boolean cancelResult = false;
		NBTItem nbti1 = new NBTItem(item1.clone());
		NBTItem nbti2 = null;
		if (item2 != null && !item2.getType().equals(Material.AIR))
			nbti2 = new NBTItem(item2.clone());
		ItemStack result = item1.clone();
		for (SmithScript smithScript : ImagiCube.getInstance().addonList.smithScripts) {
			SmithResult smithResult = smithScript.getResult(nbti1, nbti2, new NBTItem(result));
			result = smithResult.getResultItem();
			rightItemRemovedAmount += smithResult.getRightItemRemovedAmount();
			showResult |= smithResult.showResult();
			cancelResult |= smithResult.resultCancelled();
		}
		if (cancelResult)
			return new SmithResult(new ItemStack(Material.AIR), 0);
		return new SmithResult(result, rightItemRemovedAmount).showResult(showResult);
	}

	public static void sendNullResult(PrepareAnvilEvent event) {
		event.setResult(new ItemStack(Material.AIR));
		Bukkit.getScheduler().scheduleSyncDelayedTask(ImagiCube.getInstance(), new Runnable() {
			@Override
			public void run() {
				((AnvilInventory) event.getInventory()).setRepairCost(0);
			}
		});
	}

	@EventHandler
	public static void onForge(InventoryClickEvent event) {
		if (event.getInventory() instanceof AnvilInventory && event.getSlotType().equals(SlotType.RESULT)
				&& Arrays.asList(InventoryAction.PICKUP_ALL, InventoryAction.PICKUP_HALF, InventoryAction.PICKUP_ONE,
						InventoryAction.PICKUP_SOME).contains(event.getAction())) {
			AnvilInventory anvilInventory = (AnvilInventory) event.getInventory();
			ItemStack item2 = anvilInventory.getItem(1);
			FuseResult fuseResult = getFuseResult(anvilInventory.getItem(0), item2, anvilInventory.getRenameText());
			if (item2 != null)
				item2.setAmount(item2.getAmount() - fuseResult.getRightItemRemovedAmount());
			Bukkit.getScheduler().scheduleSyncDelayedTask(ImagiCube.getInstance(), new Runnable() {
				@Override
				public void run() {
					anvilInventory.setItem(1, item2);
				}
			});
		}
	}

}
