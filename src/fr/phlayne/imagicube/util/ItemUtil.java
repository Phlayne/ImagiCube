package fr.phlayne.imagicube.util;

import org.bukkit.inventory.ItemStack;

import de.tr7zw.nbtapi.NBTItem;

public class ItemUtil {

	public static String getMaterial(ItemStack item) {
		NBTItem nbti = new NBTItem(item);
		return nbti.hasKey(NBTUtil.MATERIAL) ? nbti.getString(NBTUtil.MATERIAL) : "";
	}

	public static String getItemType(ItemStack item) {
		NBTItem nbti = new NBTItem(item);
		return nbti.hasKey(NBTUtil.ITEM_TYPE) ? nbti.getString(NBTUtil.ITEM_TYPE) : "";
	}

}
