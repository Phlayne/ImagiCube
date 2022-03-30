package fr.phlayne.imagicube.item;

import org.bukkit.Material;

import fr.phlayne.imagicube.ImagiCube;

public interface Tool {

	public abstract String getName();

	public abstract boolean canDamage(Material material);

	public static Tool getTool(String name) {
		for (Tool tool : ImagiCube.getInstance().addonList.tools) {
			if (tool.getName().equals(name))
				return tool;
		}
		return null;
	}
}
