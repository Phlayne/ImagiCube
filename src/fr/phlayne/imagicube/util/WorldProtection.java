package fr.phlayne.imagicube.util;

import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class WorldProtection {

	public static boolean canBreakBlock(Player player, Block blockToBreak) {
		boolean canBreak = !(blockToBreak.getWorld().getName().equals("Continent")
				&& (!player.isOp() || (player.getGameMode().equals(GameMode.SURVIVAL)
						|| player.getGameMode().equals(GameMode.ADVENTURE))));
		return canBreak;
	}

	public static boolean canBreakBlock(Block blockToBreak) {
		return !(blockToBreak.getWorld().getName().equals("Continent"));
	}
}