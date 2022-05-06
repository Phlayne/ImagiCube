package fr.phlayne.imagicube.display;

import org.bukkit.entity.Player;

import fr.phlayne.imagicube.util.DamageStats;
import fr.phlayne.imagicube.util.SimpleJSON;
import fr.phlayne.imagicube.util.SimpleJSON.Color;

public class ArmorScript extends DisplayScript {

	private double order = 1;

	@Override
	public double getOrder() {
		return order;
	}

	@Override
	public void setOrder(double order) {
		this.order = order;
	}

	@Override
	public String getName(String string) {
		return "Armor";
	}

	@Override
	public SimpleJSON getMessage(Player player) {
		return new SimpleJSON()
				.add("" + DamageStats.getPhysicalResistance(player), false, true, false, false,
						new Color(174, 174, 174), false)
				.add("" + (char) 9830, false, false, false, false, new Color(174, 174, 174), false);
	}

	@Override
	public boolean shouldDisplay(Player player) {
		return DamageStats.getPhysicalResistance(player) > 0;
	}

}
