package fr.phlayne.imagicube.craftbehaviour;

import de.tr7zw.nbtapi.NBTItem;

public interface FuseScript {

	// When called for every FuseResult, clone leftItem and rightItem but not result, so only result is modified at the end.
	public FuseResult getResult(NBTItem leftItem, NBTItem rightItem, NBTItem result, String newName);
}
