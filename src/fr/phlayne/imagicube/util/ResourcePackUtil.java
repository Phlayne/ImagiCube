package fr.phlayne.imagicube.util;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

import fr.phlayne.imagicube.ImagiCube;
import fr.phlayne.imagicube.Reference;
import fr.phlayne.imagicube.data.Config;

public class ResourcePackUtil implements Listener {

	public HashMap<Player, Boolean> resourcePackLoaded = new HashMap<Player, Boolean>();

	private static final String RESOURCEPACK_LINK = "https://www.dropbox.com/s/0xi7p8cppk03rep/imagicube%20v3.1.0beta4.zip?dl=1";
	public String resourcepackLink;
	// TODO load custommodeldata values from config file so people can change them

	// add a condition when the custommodeldata.yml file is edited so items can
	// reload and have proper textures.
	// this should be a bit complicated but it is important to code, instead of
	// changing manually the updateversion in ItemUpdater.java

	public void init() {
		FileConfiguration config = Config.getConfig();
		if (config.getBoolean("custom_resourcepack_link"))
			this.resourcepackLink = Config.getConfig().getString("resource_pack_link");
		else
			this.resourcepackLink = RESOURCEPACK_LINK;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		enableResourcepack(event.getPlayer());
	}

	public void enableResourcepack(Player player) {
		resourcePackLoaded.put(player, false);
		player.setResourcePack(this.resourcepackLink);
	}

	@EventHandler
	public void checkResourcepack(PlayerResourcePackStatusEvent event) {
		if (event.getStatus().equals(PlayerResourcePackStatusEvent.Status.DECLINED)
				|| event.getStatus().equals(PlayerResourcePackStatusEvent.Status.FAILED_DOWNLOAD)) {
			if (event.getStatus().equals(PlayerResourcePackStatusEvent.Status.FAILED_DOWNLOAD))
				event.getPlayer().kickPlayer(TranslatableReference.get(TranslatableReference.RESOURCEPACK_KICK_FAILED,
						event.getPlayer().getLocale()));
			else
				event.getPlayer().kickPlayer(TranslatableReference.get(TranslatableReference.RESOURCEPACK_KICK,
						event.getPlayer().getLocale()));
		}
		switch (event.getStatus()) {
		case ACCEPTED:
			Bukkit.getLogger().info("[" + Reference.PLUGIN_NAME + "] " + event.getPlayer().getName()
					+ " accepted the download of the resource pack.");
			break;
		case SUCCESSFULLY_LOADED:
			Bukkit.getLogger().info("[" + Reference.PLUGIN_NAME + "] " + event.getPlayer().getName()
					+ " successfully loaded the resource pack.");
			Bukkit.getScheduler().scheduleSyncDelayedTask(ImagiCube.getPlugin(ImagiCube.class), new Runnable() {
				@Override
				public void run() {
					resourcePackLoaded.put(event.getPlayer(), true);
				}
			}, 20L);
			break;
		case DECLINED:
			Bukkit.getLogger().info("[" + Reference.PLUGIN_NAME + "] " + event.getPlayer().getName()
					+ " declined the download of the resource pack.");
			break;
		case FAILED_DOWNLOAD:
			Bukkit.getLogger().info("[" + Reference.PLUGIN_NAME + "] " + event.getPlayer().getName()
					+ " failed the download of the resource pack.");
			break;
		}
	}

	public static byte[] convertHexToByteArray(String hash) {
		byte[] result = new byte[20];
		for (int i = 0; i < 20; i++) {
			result[i] = (byte) Integer.parseInt("a", 16);
		}
		return result;
	}
}