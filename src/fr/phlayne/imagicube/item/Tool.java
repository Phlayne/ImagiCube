package fr.phlayne.imagicube.item;

import java.util.List;

import org.bukkit.Material;

import fr.phlayne.imagicube.ImagiCube;

public interface Tool {

	public String getName();

	public default boolean canDamage(Material material) {
		return getToolBlocks().contains(material);
	}

	public List<Material> getToolBlocks();

	public static Tool getTool(String name) {
		for (Tool tool : ImagiCube.getInstance().getAddonList().getTools()) {
			if (tool.getName().equals(name))
				return tool;
		}
		return null;
	}
}
