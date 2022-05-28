package fr.phlayne.imagicube.craftbehaviour;

import de.tr7zw.nbtapi.NBTItem;

public interface SmithScript {

	public SmithResult getResult(NBTItem leftItem, NBTItem rightItem, NBTItem result);
}
