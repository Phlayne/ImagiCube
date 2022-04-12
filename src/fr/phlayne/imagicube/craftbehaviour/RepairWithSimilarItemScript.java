package fr.phlayne.imagicube.craftbehaviour;

import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.item.Durability;
import fr.phlayne.imagicube.util.NBTUtil;

public class RepairWithSimilarItemScript implements FuseScript {

	public FuseResult getAnvilResult(NBTItem leftItem, NBTItem rightItem, NBTItem result, String newName) {
		if (rightItem != null)
			if (leftItem.hasKey(NBTUtil.ITEM_TYPE) && rightItem.hasKey(NBTUtil.ITEM_TYPE)
					&& leftItem.getString(NBTUtil.ITEM_TYPE).equals(rightItem.getString(NBTUtil.ITEM_TYPE)))
				if (leftItem.hasKey(NBTUtil.DURABILITY) && rightItem.hasKey(NBTUtil.DURABILITY)) {
					// TODO Move the following to ImagiCubeSpells
					if (leftItem.hasKey(NBTUtil.SPELL) && rightItem.hasKey(NBTUtil.SPELL)) {
						if (leftItem.getString(NBTUtil.SPELL).equals(rightItem.getString(NBTUtil.SPELL))) {
							repairItems(leftItem, rightItem, result);
						}
					} else {
						repairItems(leftItem, rightItem, result);
					}
					return new FuseResult(result.getItem(), 1, 1).showResult(
							!leftItem.getInteger(NBTUtil.DURABILITY).equals(result.getInteger(NBTUtil.DURABILITY)));
				}
		return new FuseResult(result.getItem(), 0, 0);
	}

	public void repairItems(NBTItem nbti1, NBTItem nbti2, NBTItem result) {
		int maxDurability = Durability.getMaxDurability(nbti1);
		int newDurability = Math.max(0, maxDurability - Math.max(0, (int) (maxDurability * 2.05F)
				- nbti1.getInteger(NBTUtil.DURABILITY) - nbti2.getInteger(NBTUtil.DURABILITY)));
		Durability.setDurability(result, newDurability);
	}
}
