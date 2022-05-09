package fr.phlayne.imagicube.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.ImagiCube;
import fr.phlayne.imagicube.util.NBTUtil;
import fr.phlayne.imagicube.util.SimpleJSON;

public class FoodProperty {

	protected Material baseMaterial;
	protected int modelData, foodLevel;
	protected float saturationModifier;
	protected String name;
	protected FoodProperty whenCooked;

	public FoodProperty(Material baseMaterial, int modelData, int foodLevel, float saturationModifier, String name) {
		this.baseMaterial = baseMaterial;
		this.modelData = modelData;
		this.foodLevel = foodLevel;
		this.saturationModifier = saturationModifier;
		this.name = name;
		this.whenCooked = null;
	}

	public Material getBaseMaterial() {
		return this.baseMaterial;
	}

	public int getModelData() {
		return this.modelData;
	}

	public int getFoodLevel() {
		return this.foodLevel;
	}

	public float getSaturationModifier() {
		return this.saturationModifier;
	}

	public float getSaturation() {
		return this.getSaturationModifier() * this.getFoodLevel() * 2;
	}

	public String getName() {
		return this.name;
	}

	public void setWhenCooked(FoodProperty fp) {
		this.whenCooked = fp;
	}

	public FoodProperty getWhenCooked() {
		return this.whenCooked;
	}

	public ItemStack getItemStack() {
		ItemStack item = new ItemStack(this.getBaseMaterial());
		NBTItem nbti = new NBTItem(item);
		nbti.setInteger("CustomModelData", this.getModelData());
		nbti.setString(NBTUtil.ITEM_TYPE, "food." + this.getName());
		return setFoodName(nbti.getItem());
	}

	public boolean corresponds(ItemStack item) {
		if (item != null && item.getType().equals(this.getBaseMaterial()) && item.hasItemMeta()
				&& item.getItemMeta().hasCustomModelData()
				&& item.getItemMeta().getCustomModelData() == this.getModelData())
			return true;
		return false;
	}

	public static FoodProperty getFoodProperty(ItemStack item) {
		for (FoodProperty fp : ImagiCube.getInstance().addonList.foods)
			if (fp.corresponds(item))
				return fp;
		return null;
	}

	public static ItemStack setFoodName(ItemStack item) {
		return setFoodName(item, null);
	}

	public static ItemStack setFoodName(ItemStack item, FoodProperty fp) {
		NBTItem nbti = new NBTItem(item);
		setFoodName(nbti, fp);
		return nbti.getItem();
	}

	public static void setFoodName(NBTItem nbti, FoodProperty fp) {
		if (fp == null)
			fp = FoodProperty.getFoodProperty(nbti.getItem());
		if (fp != null) {
			if (!nbti.hasKey("display")) {
				NBTCompound display = nbti.getOrCreateCompound("display");
				display.setString("Name", new SimpleJSON()
						.add("imagicube.food." + fp.getName(), false, false, false, false, SimpleJSON.Color.WHITE, true)
						.convert());
			}
			nbti.setInteger("CustomModelData", fp.getModelData());
		}
	}

	public static ItemStack setFoodNeededLore(ItemStack item, int food, double saturation) {
		NBTItem nbti = new NBTItem(item);
		setFoodNeededLore(nbti, food, saturation);
		return nbti.getItem();
	}

	public static void setFoodNeededLore(NBTItem nbti, int food, double saturation) {
		NBTCompound displayTag = nbti.getOrCreateCompound("display");
		NBTUtil.changeLore(displayTag,
				new SimpleJSON().add(food + " ", false, false, false, false, SimpleJSON.Color.BLUE, false)
						.add("imagicube.foodPoints", false, false, false, false, SimpleJSON.Color.BLUE, true).convert(),
				0);
		NBTUtil.changeLore(displayTag, new SimpleJSON()
				.add(String.format("%.1f", saturation).replace(',', '.') + " ", false, false, false, false,
						SimpleJSON.Color.BLUE, false)
				.add("imagicube.saturationPoints", false, false, false, false, SimpleJSON.Color.BLUE, true).convert(),
				1);
	}
}
