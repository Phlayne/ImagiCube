package fr.phlayne.imagicube.craftbehaviour;

import org.bukkit.inventory.ItemStack;

public class SmithResult {

	private ItemStack resultItem;
	private int rightItemRemovedAmount;
	private boolean showResult, cancelResult;

	public SmithResult(ItemStack resultItem, int rightItemRemovedAmount) {
		this.rightItemRemovedAmount = rightItemRemovedAmount;
		this.resultItem = resultItem;
		this.showResult = false;
		this.cancelResult = false;
	}

	public ItemStack getResultItem() {
		return this.resultItem;
	}

	public int getRightItemRemovedAmount() {
		return this.rightItemRemovedAmount;
	}

	public boolean showResult() {
		return this.showResult;
	}

	public SmithResult showResult(boolean showResult) {
		this.showResult = showResult;
		return this;
	}

	public boolean resultCancelled() {
		return this.cancelResult;
	}

	public SmithResult cancelResult() {
		this.cancelResult = true;
		return this;
	}

}
