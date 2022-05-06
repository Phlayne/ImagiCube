package fr.phlayne.imagicube.events;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.phlayne.imagicube.chat.ChatFormat;

public class ChatEvents implements Listener {

	@EventHandler
	public void onMessage(AsyncPlayerChatEvent event) {
		ChatFormat.sendMessage(event.getPlayer(), event.getMessage());
		event.setCancelled(true);
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (event.getMessage().contains(player.getName())) {
				player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.VOICE, 1.0F,
						1.0F);
			}
		}
	}
}
