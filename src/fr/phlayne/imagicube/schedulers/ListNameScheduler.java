package fr.phlayne.imagicube.schedulers;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import fr.phlayne.imagicube.Reference;
import fr.phlayne.imagicube.data.Config;
import fr.phlayne.imagicube.data.PlayerData;
import fr.phlayne.imagicube.util.CustomMetadata;
import net.md_5.bungee.api.ChatColor;

public class ListNameScheduler extends SchedulerScript {

	protected static long timer = 0;

	@Override
	public void tick() {

		timer++;
		for (Player player : Bukkit.getOnlinePlayers()) {
			int secondsBeforeAFK = Config.getConfig().getInt("seconds_before_afk");
			String prefix = "";
			if (secondsBeforeAFK > 0) {
				PlayerData.setAFK(player, CustomMetadata.get("lastAction").getValue(player) > (secondsBeforeAFK * 20));
				if (!player.getGameMode().equals(GameMode.SPECTATOR) && PlayerData.isAFK(player)) {
					prefix = "AFK - ";
				}
			}
			FileConfiguration worldColors = Config.getConfig(Reference.PLUGIN_NAME, "world_colors");
			String color = "#ffffff";
			if (worldColors.contains(player.getWorld().getName()))
				color = worldColors.getString(player.getWorld().getName());
			player.setPlayerListName(ChatColor.of(color) + prefix + player.getName());
		}
	}

}
