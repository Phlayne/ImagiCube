package fr.phlayne.imagicube.display;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;

import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.item.Durability;
import fr.phlayne.imagicube.util.NBTUtil;
import fr.phlayne.imagicube.util.SimpleJSON;

public class RightHandDurabilityScript extends DisplayScript {

	private double order = 6;

	@Override
	public double getOrder() {
		return order;
	}

	@Override
	public void setOrder(double order) {
		this.order = order;
	}

	@Override
	public String getName(String string) {
		return "RightHandDurability";
	}

	@Override
	public SimpleJSON getMessage(Player player) {
		boolean leftHanded = player.getMainHand().equals(MainHand.LEFT);
		float durability = -1;
		if (new NBTItem(player.getEquipment().getItem(leftHanded ? EquipmentSlot.OFF_HAND : EquipmentSlot.HAND))
				.hasKey(NBTUtil.DURABILITY))
			durability = Durability.getPercentDurability(new NBTItem(
					player.getEquipment().getItem(leftHanded ? EquipmentSlot.OFF_HAND : EquipmentSlot.HAND)));
		if (durability != -1)
			return new SimpleJSON().add("" + (int) (durability * 100), false, true, false, false,
					Durability.getColorDurability(durability).multiply(0.8F), false).add("\u26cf", false, false, false,
							false, Durability.getColorDurability(durability).multiply(0.8F), false);
		return null;
	}

	@Override
	public boolean shouldDisplay(Player player) {
		boolean leftHanded = player.getMainHand().equals(MainHand.LEFT);
		ItemStack i = player.getEquipment().getItem(leftHanded ? EquipmentSlot.OFF_HAND : EquipmentSlot.HAND);
		if (i != null && i.getType() != Material.AIR)
			return new NBTItem(i).hasKey(NBTUtil.DURABILITY);
		return false;
	}

}
