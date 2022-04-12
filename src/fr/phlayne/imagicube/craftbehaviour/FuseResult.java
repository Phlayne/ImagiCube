package fr.phlayne.imagicube.craftbehaviour;

import org.bukkit.inventory.ItemStack;

public class FuseResult {

	private ItemStack resultItem;
	private int rightItemRemovedAmount, repairCost;
	private boolean showResult, cancelResult;

	public FuseResult(ItemStack resultItem, int rightItemRemovedAmount, int repairCost) {
		this.rightItemRemovedAmount = rightItemRemovedAmount;
		this.resultItem = resultItem;
		this.repairCost = repairCost;
		this.showResult = false;
		this.cancelResult = false;
	}

	public ItemStack getResultItem() {
		return this.resultItem;
	}

	public int getRightItemRemovedAmount() {
		return this.rightItemRemovedAmount;
	}

	public int getRepairCost() {
		return this.repairCost;
	}

	public boolean showResult() {
		return this.showResult;
	}

	public FuseResult showResult(boolean showResult) {
		this.showResult = showResult;
		return this;
	}

	public boolean resultCancelled() {
		return this.cancelResult;
	}

	public FuseResult cancelResult() {
		this.cancelResult = true;
		return this;
	}

}
