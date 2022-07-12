package fr.phlayne.imagicube.chat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;

public class ChatFormat {

	@SuppressWarnings("deprecation")
	public static void sendMessage(Player player, String message) {
		System.out.println(player.getName() + " : " + message);
		String[] splittedMessage = message.split("\\[hand\\]", -1);

		ComponentBuilder messageComponent = new ComponentBuilder();
		messageComponent.append(player.getName() + " : ").color(ChatColor.GRAY);

		ItemStack itemInHand = player.getEquipment().getItemInMainHand();
		boolean printFace = message.contains("[hand]") && itemInHand != null && itemInHand.hasItemMeta()
				&& itemInHand.getItemMeta() instanceof SkullMeta;

		if (splittedMessage.length == 0) {
			messageComponent.append(message.substring(0, 1).toUpperCase()).bold(true).color(ChatColor.WHITE);
			messageComponent.append(message.substring(1)).bold(false);
		} else {
			if (splittedMessage[0].length() == 0) {
				boolean bold = true;
				messageComponent.color(ChatColor.WHITE);
				for (int i = 1; i < splittedMessage.length; i++) {
					messageComponent.append("[").bold(bold);
					bold = false;
					messageComponent.append(ItemPrinter.convertItemToText(itemInHand));
					messageComponent.append("]").event((HoverEvent) null).color(ChatColor.WHITE);
					if (splittedMessage[i].length() > 0) {
						messageComponent.append(splittedMessage[i]);
					}
				}
			} else {
				messageComponent.append(splittedMessage[0].substring(0, 1).toUpperCase()).bold(true)
						.color(ChatColor.WHITE);
				messageComponent.append(splittedMessage[0].substring(1)).bold(false);
				for (int i = 1; i < splittedMessage.length; i++) {
					messageComponent.append("[");
					messageComponent.append(ItemPrinter.convertItemToText(itemInHand));
					messageComponent.append("]").event((HoverEvent) null).color(ChatColor.WHITE);
					if (splittedMessage[i].length() > 0) {
						messageComponent.append(splittedMessage[i]);
					}
				}
			}
		}
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.spigot().sendMessage(messageComponent.create());
			if (printFace)
				FacePrinter.printFace((SkullMeta) itemInHand.getItemMeta(), p);
		}
	}
}
