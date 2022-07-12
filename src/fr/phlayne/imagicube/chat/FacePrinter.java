package fr.phlayne.imagicube.chat;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.SkullMeta;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class FacePrinter {

	public static void printFace(SkullMeta skullMeta, Player player) {
		if (skullMeta != null && skullMeta.getOwnerProfile() != null
				&& skullMeta.getOwnerProfile().getTextures() != null
				&& skullMeta.getOwnerProfile().getTextures().getSkin() != null) {
			try {
				BufferedImage image = ImageIO.read(skullMeta.getOwnerProfile().getTextures().getSkin());
				int offsetX = 8;
				int offsetY = 8;
				for (int y = 0 + offsetY; y < 8 + offsetY; y++) {
					ComponentBuilder line = new ComponentBuilder();
					for (int x = 0 + offsetX; x < 8 + offsetX; x++) {
						int[] rgb = getRGB(image.getRGB(x, y));
						line.append("\u25a0 ").color(ChatColor.of(new Color(rgb[0], rgb[1], rgb[2])));
					}
					player.spigot().sendMessage(line.create());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static int[] getRGB(int color) {
		int green = color & 0xff;
		int blue = (color & 0xff00) >> 8;
		int red = (color & 0xff0000) >> 16;
		return new int[] { red, blue, green };
	}

}
