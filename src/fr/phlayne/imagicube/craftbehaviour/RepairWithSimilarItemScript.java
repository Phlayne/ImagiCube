package fr.phlayne.imagicube.craftbehaviour;

import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.item.Durability;
import fr.phlayne.imagicube.util.NBTUtil;

public class RepairWithSimilarItemScript implements FuseScript {

	public FuseResult getResult(NBTItem leftItem, NBTItem rightItem, NBTItem result, String newName) {
		if (rightItem != null) {
			if (leftItem.hasKey(NBTUtil.ITEM_TYPE) && rightItem.hasKey(NBTUtil.ITEM_TYPE)) {
				// We cancel if the two items are not the same type.
				boolean cancel = !leftItem.getString(NBTUtil.ITEM_TYPE).equals(rightItem.getString(NBTUtil.ITEM_TYPE));
				// We cancel if items have a different materials
				if (leftItem.hasKey(NBTUtil.MATERIAL) && rightItem.hasKey(NBTUtil.MATERIAL))
					cancel |= !leftItem.getString(NBTUtil.MATERIAL).equals(rightItem.getString(NBTUtil.MATERIAL));
				if (leftItem.hasKey(NBTUtil.DURABILITY) && rightItem.hasKey(NBTUtil.DURABILITY)) {
					repairItems(leftItem, rightItem, result);
					FuseResult fuseResult = new FuseResult(result.getItem(), 1, 2).showResult(
							!leftItem.getInteger(NBTUtil.DURABILITY).equals(result.getInteger(NBTUtil.DURABILITY)));
					if (cancel)
						fuseResult.cancelResult();
					return fuseResult;
				}
			}
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
