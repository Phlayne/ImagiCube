package fr.phlayne.imagicube.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.util.NBTUtil;

public class ItemWeight {

	public static int getWeight(ItemStack item) {
		if (item != null && !item.getType().equals(Material.AIR)) {
			NBTItem nbti = new NBTItem(item);
			if (nbti.hasKey(NBTUtil.WEIGHT)) {
				return nbti.getInteger(NBTUtil.WEIGHT);
			} else
				return 0;
		}
		return 0;
	}
}
