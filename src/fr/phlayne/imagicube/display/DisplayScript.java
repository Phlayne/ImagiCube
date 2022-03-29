package fr.phlayne.imagicube.display;

import org.bukkit.entity.Player;

import fr.phlayne.imagicube.util.SimpleJSON;

public abstract class DisplayScript {

	public abstract String getName(String string);
	
	public abstract SimpleJSON getMessage(Player player);

	public abstract boolean shouldDisplay(Player player);

}
