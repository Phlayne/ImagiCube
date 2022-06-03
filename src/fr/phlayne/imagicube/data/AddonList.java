package fr.phlayne.imagicube.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.inventory.ItemStack;

import fr.phlayne.imagicube.craftbehaviour.FuseScript;
import fr.phlayne.imagicube.craftbehaviour.SmithScript;
import fr.phlayne.imagicube.display.DisplayScript;
import fr.phlayne.imagicube.item.ArmorProperty;
import fr.phlayne.imagicube.item.FoodProperty;
import fr.phlayne.imagicube.item.MineralProperty;
import fr.phlayne.imagicube.item.Tool;
import fr.phlayne.imagicube.item.WeaponProperty;
import fr.phlayne.imagicube.schedulers.SchedulerScript;

public class AddonList {

	private List<WeaponProperty> weapons;
	private List<ArmorProperty> armors;
	private List<MineralProperty> minerals;
	private List<FoodProperty> foods;
	private List<ItemStack> uniqueItems;
	private List<DisplayScript> displayScripts;
	private List<SchedulerScript> schedulerScripts;
	private List<FuseScript> fuseScripts;
	private List<SmithScript> smithScripts;
	private List<Tool> tools;
	private Map<String, List<String>> itemGroups;

	public AddonList() {
		this.weapons = new ArrayList<WeaponProperty>();
		this.armors = new ArrayList<ArmorProperty>();
		this.minerals = new ArrayList<MineralProperty>();
		this.foods = new ArrayList<FoodProperty>();
		this.uniqueItems = new ArrayList<ItemStack>();
		this.displayScripts = new ArrayList<DisplayScript>();
		this.schedulerScripts = new ArrayList<SchedulerScript>();
		this.fuseScripts = new ArrayList<FuseScript>();
		this.smithScripts = new ArrayList<SmithScript>();
		this.tools = new ArrayList<Tool>();
		this.itemGroups = new HashMap<String, List<String>>();
	}

	public void addWeapons(WeaponProperty... wp) {
		this.weapons.addAll(Arrays.asList(wp));
	}

	public List<WeaponProperty> getWeapons() {
		return new ArrayList<WeaponProperty>(this.weapons);
	}

	public void addArmors(ArmorProperty... ap) {
		this.armors.addAll(Arrays.asList(ap));
	}

	public List<ArmorProperty> getArmors() {
		return new ArrayList<ArmorProperty>(this.armors);
	}

	public void addMinerals(MineralProperty... mp) {
		this.minerals.addAll(Arrays.asList(mp));
	}

	public List<MineralProperty> getMinerals() {
		return new ArrayList<MineralProperty>(this.minerals);
	}

	public void addFoods(FoodProperty... fp) {
		this.foods.addAll(Arrays.asList(fp));
	}

	public List<FoodProperty> getFoods() {
		return new ArrayList<FoodProperty>(this.foods);
	}

	public void addUniqueItems(ItemStack... ui) {
		this.uniqueItems.addAll(Arrays.asList(ui));
	}

	public List<ItemStack> getUniqueItems() {
		return new ArrayList<ItemStack>(this.uniqueItems);
	}

	public void addDisplayScripts(DisplayScript... ds) {
		this.displayScripts.addAll(Arrays.asList(ds));
	}

	public List<DisplayScript> getDisplayScripts() {
		return new ArrayList<DisplayScript>(this.displayScripts);
	}

	public void addSchedulerScripts(SchedulerScript... ss) {
		this.schedulerScripts.addAll(Arrays.asList(ss));
	}

	public List<SchedulerScript> getSchedulerScripts() {
		return new ArrayList<SchedulerScript>(this.schedulerScripts);
	}

	public void addFuseScripts(FuseScript... fs) {
		this.fuseScripts.addAll(Arrays.asList(fs));
	}

	public List<FuseScript> getFuseScripts() {
		return new ArrayList<FuseScript>(this.fuseScripts);
	}

	public void addSmithScripts(SmithScript... ss) {
		this.smithScripts.addAll(Arrays.asList(ss));
	}

	public List<SmithScript> getSmithScripts() {
		return new ArrayList<SmithScript>(this.smithScripts);
	}

	public void addTools(Tool... tool) {
		this.tools.addAll(Arrays.asList(tool));
	}

	public List<Tool> getTools() {
		return new ArrayList<Tool>(this.tools);
	}

	public void addItemsToGroup(String groupName, String... itemTypes) {
		List<String> group = itemGroups.containsKey(groupName) ? itemGroups.get(groupName) : new ArrayList<String>();
		group.addAll(Arrays.asList(itemTypes));
		itemGroups.put(groupName, group);
	}

	public List<String> getItemsInGroup(String groupName) {
		return itemGroups.containsKey(groupName) ? itemGroups.get(groupName) : new ArrayList<String>();
	}

	public void sortDisplayScripts() {
		this.displayScripts.sort(new Comparator<DisplayScript>() {
			@Override
			public int compare(DisplayScript ds1, DisplayScript ds2) {
				if (ds1.getOrder() < ds2.getOrder())
					return -1;
				else if (ds1.getOrder() == ds2.getOrder())
					return 0;
				else
					return 1;
			}
		});
	}
}
