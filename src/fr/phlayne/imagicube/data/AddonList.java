package fr.phlayne.imagicube.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.inventory.ItemStack;

import fr.phlayne.imagicube.craftbehaviour.FuseScript;
import fr.phlayne.imagicube.display.DisplayScript;
import fr.phlayne.imagicube.item.ArmorProperty;
import fr.phlayne.imagicube.item.FoodProperty;
import fr.phlayne.imagicube.item.MineralProperty;
import fr.phlayne.imagicube.item.Tool;
import fr.phlayne.imagicube.item.WeaponProperty;
import fr.phlayne.imagicube.schedulers.SchedulerScript;

public class AddonList {

	public List<WeaponProperty> weapons;
	public List<ArmorProperty> armors;
	public List<MineralProperty> minerals;
	public List<FoodProperty> foods;
	public List<ItemStack> uniqueItems;
	public List<DisplayScript> displayScripts;
	public List<SchedulerScript> schedulerScripts;
	public List<FuseScript> fuseScripts;
	public List<Tool> tools;
	public HashMap<String, List<String>> itemGroups;

	public void addItemsToGroup(String groupName, String... itemTypes) {
		List<String> group = itemGroups.containsKey(groupName) ? itemGroups.get(groupName) : new ArrayList<String>();
		group.addAll(Arrays.asList(itemTypes));
		itemGroups.put(groupName, group);
	}
}
