package fr.phlayne.imagicube.events;

import java.rmi.AlreadyBoundException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import fr.phlayne.imagicube.ImagiCube;
import fr.phlayne.imagicube.data.PlayerData;
import fr.phlayne.imagicube.util.CustomMetadata;

public class AFKEvents implements Listener {

	public CustomMetadata lastAction = new CustomMetadata("lastAction", true, 0, Integer.MAX_VALUE);

	public void init() {
		try {
			lastAction.register();
			for (Player player : Bukkit.getOnlinePlayers()) {
				PlayerData.setAFK(player, false);
			}
			Bukkit.getScheduler().scheduleSyncRepeatingTask(ImagiCube.getInstance(), new Runnable() {
				@Override
				public void run() {
					for (Player player : Bukkit.getOnlinePlayers()) {
						lastAction.setValue(player, lastAction.getValue(player) + 1);
					}
				}
			}, 0, 1L);
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}

	@EventHandler
	public void onInventoryOpen(InventoryOpenEvent event) {
		if (event.getPlayer() instanceof Player)
			lastAction.setValue((Player) event.getPlayer(), 0);
	}

	@EventHandler
	public void onInventoryOpen(InventoryCloseEvent event) {
		if (event.getPlayer() instanceof Player)
			lastAction.setValue((Player) event.getPlayer(), 0);
	}

	@EventHandler
	public void onInventoryInteract(InventoryInteractEvent event) {
		if (event.getWhoClicked() instanceof Player)
			lastAction.setValue((Player) event.getWhoClicked(), 0);
	}

	@EventHandler
	public void onSneak(PlayerToggleSneakEvent event) {
		lastAction.setValue(event.getPlayer(), 0);
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		lastAction.setValue(event.getPlayer(), 0);
	}

	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		lastAction.setValue(event.getPlayer(), 0);
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		lastAction.setValue(event.getPlayer(), 0);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		lastAction.setValue(event.getPlayer(), 0);
		PlayerData.setAFK(event.getPlayer(), false);
	}
}
