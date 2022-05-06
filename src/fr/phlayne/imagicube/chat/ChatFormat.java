package fr.phlayne.imagicube.chat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

public class ChatFormat {

	public static void sendMessage(Player player, String message) {
		System.out.println(player.getName() + " : " + message);
		for (Player p : Bukkit.getOnlinePlayers()) {
			TextComponent playerName = new TextComponent(player.getName() + " : ");
			playerName.setColor(ChatColor.GRAY);
			TextComponent firstLetter = new TextComponent(message.substring(0, 1).toUpperCase());
			firstLetter.setBold(true);
			TextComponent messageLeft = new TextComponent(message.substring(1));
			p.spigot().sendMessage(playerName, firstLetter, messageLeft);
		}
	}
}
