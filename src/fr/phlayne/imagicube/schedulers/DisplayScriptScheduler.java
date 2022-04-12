package fr.phlayne.imagicube.schedulers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.phlayne.imagicube.ImagiCube;
import fr.phlayne.imagicube.display.DisplayScript;
import fr.phlayne.imagicube.util.SimpleJSON;
import fr.phlayne.imagicube.util.TranslatableReference;
import fr.phlayne.imagicube.util.SimpleJSON.Color;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;

public class DisplayScriptScheduler extends SchedulerScript {

	protected static long timer = 0;

	@Override
	public void tick() {

		timer++;
		for (Player player : Bukkit.getOnlinePlayers()) {
			SimpleJSON message = new SimpleJSON();
			boolean space = false;
			if (ImagiCube.getInstance().getResourcePackUtil().resourcePackLoaded.get(player)) {
				for (DisplayScript displayScript : ImagiCube.getInstance().addonList.displayScripts) {
					if (displayScript.shouldDisplay(player)) {
						if (space)
							message.add(" ", false, false, false, false, Color.WHITE, false);
						message.add(displayScript.getMessage(player));
						space = true;
					}
				}
			} else {
				message = new SimpleJSON().add(TranslatableReference.RESOURCEPACK_DOWNLOADING.translate(player), false,
						true, false, false, Color.AQUA, false);
			}
			player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
					new TextComponent(ComponentSerializer.parse(message.convert())));
		}
	}

}
