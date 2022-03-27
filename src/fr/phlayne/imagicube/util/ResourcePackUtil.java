package fr.phlayne.imagicube.util;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

import fr.phlayne.imagicube.ImagiCube;
import fr.phlayne.imagicube.Reference;

public class ResourcePackUtil implements Listener {

	public HashMap<Player, Boolean> resourcePackLoaded = new HashMap<Player, Boolean>();

	public static final String RESOURCEPACK_LINK = "https://www.dropbox.com/s/gle3f6p58500rum/imagicube%20v3.1.0beta2.zip?dl=1";
	public static final String RESOURCEPACK_VERSION = "3.1.0";

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		enableResourcepack(event.getPlayer());
	}

	public void enableResourcepack(Player player) {
		resourcePackLoaded.put(player, false);
		player.setResourcePack(RESOURCEPACK_LINK);
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