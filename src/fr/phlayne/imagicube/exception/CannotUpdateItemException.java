package fr.phlayne.imagicube.exception;

import org.bukkit.inventory.ItemStack;

import de.tr7zw.nbtapi.NBTItem;

public class CannotUpdateItemException extends Exception {

	private static final long serialVersionUID = -617067622717569402L;

	protected ItemStack problematicItem;

	public CannotUpdateItemException(String errorMessage, ItemStack itemStack) {
		super(errorMessage+new NBTItem(itemStack).toString());
		this.problematicItem = itemStack;
	}
	
	public ItemStack getProblematicItem() {
		return this.problematicItem;
	}

}
