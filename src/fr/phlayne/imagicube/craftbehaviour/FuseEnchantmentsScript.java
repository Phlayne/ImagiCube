package fr.phlayne.imagicube.craftbehaviour;

import org.bukkit.Material;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTCompoundList;
import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.util.EnchantmentHelper;
import fr.phlayne.imagicube.util.NBTUtil;

public class FuseEnchantmentsScript implements FuseScript {

	public FuseResult getResult(NBTItem leftItem, NBTItem rightItem, NBTItem result, String newName) {
		if (rightItem != null) {
			int repairCost = fuseEnchantments(leftItem, rightItem, result, true, true);
			String item1Enchants = leftItem.getItem().getType().equals(Material.ENCHANTED_BOOK) ? "StoredEnchantments"
					: "Enchantments";
			if (repairCost != 0)
				return new FuseResult(result.getItem(), 1, repairCost).showResult(
						!leftItem.getCompoundList(item1Enchants).equals(result.getCompoundList(item1Enchants)));
		}
		return new FuseResult(result.getItem(), 0, 0);
	}

	// Do not keep curses when repairing in crafting table
	// Keep curses in grindstones
	public int fuseEnchantments(NBTItem nbti1, NBTItem nbti2, NBTItem result, boolean keepEnchantments,
			boolean keepCurses) {
		boolean item1Book = nbti1.getItem().getType().equals(Material.ENCHANTED_BOOK);
		boolean item2Book = nbti2.getItem().getType().equals(Material.ENCHANTED_BOOK);
		String item1Enchants = item1Book ? "StoredEnchantments" : "Enchantments";
		String item2Enchants = item2Book ? "StoredEnchantments" : "Enchantments";
		NBTCompoundList newEnchants = result.getCompoundList(item1Enchants);
		newEnchants.clear();
		if (!item1Book || item2Book) {
			for (NBTCompound enchant : nbti1.getCompoundList(item1Enchants))
				if (item1Book || EnchantmentHelper.isCompatibleWith(enchant.getString("id"), nbti1))
					newEnchants.addCompound(enchant);
			for (NBTCompound enchant : nbti2.getCompoundList(item2Enchants)) {
				if (item1Book || EnchantmentHelper.isCompatibleWith(enchant.getString("id"), nbti1)) {
					boolean isCompatible = true;
					boolean alreadyPresent = false;

					// Checks if the enchant can be added on the item;

					for (NBTCompound itemEnchant : newEnchants) {
						isCompatible &= EnchantmentHelper.isCompatibleWith(enchant.getString("id"),
								itemEnchant.getString("id"));
						alreadyPresent |= enchant.getString("id").equals(itemEnchant.getString("id"));
						if (enchant.getString("id").equals(itemEnchant.getString("id"))) {
							int enchant1Level = itemEnchant.getShort("lvl");
							int enchant2Level = enchant.getShort("lvl");
							if (enchant1Level == enchant2Level)
								itemEnchant.setShort("lvl",
										(short) Math.min(enchant1Level + 1,
												EnchantmentHelper.getMaxLevel(enchant.getString("id"),
														result.hasKey(NBTUtil.MATERIAL)
																? result.getString(NBTUtil.MATERIAL)
																: null)));
							else
								itemEnchant.setShort("lvl", (short) Math.max(enchant1Level, enchant2Level));
						}
					}
					if (isCompatible && !alreadyPresent)
						newEnchants.addCompound(enchant);
				}
			}
			result.getCompoundList(item1Enchants).clear();
			result.getCompoundList(item1Enchants).addAll(newEnchants);
			return getEnchantCost(newEnchants);
		}
		return 0;
	}

	// TODO Make that we can change this behaviour in the config file
	// This is a new behaviour
	public static int getEnchantCost(NBTCompoundList enchants) {
		int c = 0;
		for (NBTCompound nbtc : enchants)
			c += nbtc.getInteger("lvl");
		return c;
	}
}
