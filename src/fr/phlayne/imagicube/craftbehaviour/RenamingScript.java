package fr.phlayne.imagicube.craftbehaviour;

import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.item.Durability;
import fr.phlayne.imagicube.util.EnchantmentHelper;
import fr.phlayne.imagicube.util.NBTUtil;
import fr.phlayne.imagicube.util.SimpleJSON;
import fr.phlayne.imagicube.util.SimpleJSON.Color;

public class RenamingScript implements FuseScript {

	public FuseResult getResult(NBTItem leftItem, NBTItem rightItem, NBTItem result, String newName) {
		if (rightItem == null && newName != null && !newName.equals("")) {
			Color color = EnchantmentHelper.getItemBaseColor(result);
			if (result.hasKey(NBTUtil.DURABILITY))
				color.multiply(Durability.getPercentDurability(result));
			result.getOrCreateCompound("display").setString("Name",
					new SimpleJSON().add(newName, true, false, false, false, color, false).convert());
			return new FuseResult(result.getItem(), 0, 1).showResult(true);
		}
		return new FuseResult(result.getItem(), 0, 0);
	}

}
