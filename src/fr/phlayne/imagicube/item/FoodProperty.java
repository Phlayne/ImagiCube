package fr.phlayne.imagicube.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.util.NBTUtil;
import fr.phlayne.imagicube.util.SimpleJSON;

public enum FoodProperty {

	/* TODO Move this in ImagiCubeFood
	
	PINE_CONE(Material.APPLE, 1, 1, 0.0F, "imagicube.food.pine_cone"),
	PEAR(Material.APPLE, 2, 3, 0.3F, "imagicube.food.pear"),
	BANANA(Material.APPLE, 3, 3, 0.4F, "imagicube.food.banana"),
	ORANGE(Material.APPLE, 4, 2, 0.3F, "imagicube.food.orange"),
	LEMON(Material.APPLE, 5, 2, 0.3F, "imagicube.food.lemon"),
	TOMATO(Material.APPLE, 6, 3, 0.2F, "imagicube.food.tomato"),
	RED_CHILI(Material.APPLE, 7, 3, 0.2F, "imagicube.food.red_chili"),
	CYAN_CHILI(Material.APPLE, 8, 3, 0.2F, "imagicube.food.cyan_chili"),
	CORN(Material.APPLE, 9, 4, 0.3F, "imagicube.food.corn"),
	BUTTER(Material.APPLE, 10, 6, 0.5F, "imagicube.food.butter"),
	CARAMEL(Material.APPLE, 11, 4, 0.5F, "imagicube.food.caramel"),
	CHEESE(Material.APPLE, 12, 2, 1.2F, "imagicube.food.cheese"),
	DARK_CHOCOLATE(Material.APPLE, 13, 7, 0.7F, "imagicube.food.chocolate_dark"),
	MILK_CHOCOLATE(Material.APPLE, 14, 7, 0.8F, "imagicube.food.chocolate_milk"),
	WHITE_CHOCOLATE(Material.APPLE, 15, 7, 1.0F, "imagicube.food.chocolate_white"),
	BOILED_EGG(Material.APPLE, 16, 2, 0.6F, "imagicube.food.boiled_egg"),
	BOILED_EGG_SHELL(Material.SNOWBALL, 2, 0, 0F, "imagicube.food.boiled_egg"),
	ROTTEN_TOMATO(Material.SNOWBALL, 1, 0, 0F, "imagicube.food.rotten_tomato"),
	GRUB(Material.GOLDEN_CARROT, SpookificationSpell.GRUB_MODEL_DATA_ID, 1, 1.0F, "imagicube.grub"),
	LARVA(Material.GOLDEN_CARROT, SpookificationSpell.LARVA_MODEL_DATA_ID, 1, 1.0F, "imagicube.larva"),
	WORM(Material.GOLDEN_CARROT, SpookificationSpell.WORM_MODEL_DATA_ID, 1, 1.0F, "imagicube.worm"),
	RAW_FLESH(Material.BEEF, PurificationSpell.PURIFIED_ROTTEN_FLESH_MODEL_DATA_ID, 4, 0.2F,
			"imagicube.raw_purified_rotten_flesh"),
	COOKED_FLESH(Material.COOKED_BEEF, PurificationSpell.PURIFIED_ROTTEN_FLESH_MODEL_DATA_ID, 10, 0.75F,
			"imagicube.cooked_purified_rotten_flesh"),
	SPAGHETTI(Material.BEEF, 2, 1, 0.25F, "imagicube.spaghetti"),
	PENNE(Material.BEEF, 3, 1, 0.25F, "imagicube.penne"),
	FARFALLE(Material.BEEF, 4, 1, 0.25F, "imagicube.farfalle"),
	SHELL(Material.BEEF, 5, 1, 0.25F, "imagicube.shell"),
	CONCHIGLIE(Material.BEEF, 6, 1, 0.25F, "imagicube.conchiglie"),
	COOKED_SPAGHETTI(Material.APPLE, 17, 6, 0.75F, "imagicube.cooked_spaghetti"),
	COOKED_PENNE(Material.APPLE, 18, 6, 0.75F, "imagicube.cooked_penne"),
	COOKED_FARFALLE(Material.APPLE, 19, 6, 0.75F, "imagicube.cooked_farfalle"),
	COOKED_SHELL(Material.APPLE, 20, 6, 0.75F, "imagicube.cooked_shell"),
	COOKED_CONCHIGLIE(Material.APPLE, 21, 6, 0.75F, "imagicube.cooked_conchiglie"),
	GROUND_WHEAT(Material.WHEAT_SEEDS, 1, 0, 0F, "imagicube.ground_wheat"),
	GUARDIAN_FLESH(Material.APPLE, 22, 4, 0.5F, "imagicube.guardian_flesh"),
	RICE(Material.APPLE, 23, 4, 0.75F, "imagicube.rice"),
	CHICKEN_NUGGET(Material.CHICKEN, 1, 1, 0.3F, "imagicube.chicken_nugget"),
	COOKED_CHICKEN_NUGGET(Material.COOKED_CHICKEN, 1, 3, 0.6F, "imagicube.cooked_chicken_nugget"),
	SAUSAGE(Material.PORKCHOP, 1, 2, 0.2F, "imagicube.sausage"),
	CHORIZO(Material.PORKCHOP, 2, 2, 0.3F, "imagicube.chorizo"),
	COOKED_SAUSAGE(Material.COOKED_PORKCHOP, 1, 3, 0.6F, "imagicube.cooked_sausage"),
	COOKED_CHORIZO(Material.COOKED_PORKCHOP, 2, 4, 0.8F, "imagicube.cooked_chorizo");
	*/

	;
	
	protected Material baseMaterial;
	protected int modelData, foodLevel;
	protected float saturationModifier;
	protected String name;

	FoodProperty(Material baseMaterial, int modelData, int foodLevel, float saturationModifier, String name) {
		this.baseMaterial = baseMaterial;
		this.modelData = modelData;
		this.foodLevel = foodLevel;
		this.saturationModifier = saturationModifier;
		this.name = name;
	}

	public boolean corresponds(ItemStack item) {
		if (item != null && item.getType().equals(this.baseMaterial) && item.hasItemMeta()
				&& item.getItemMeta().hasCustomModelData() && item.getItemMeta().getCustomModelData() == this.modelData)
			return true;
		return false;
	}

	public static FoodProperty getFoodProperty(ItemStack item) {
		for (FoodProperty fp : FoodProperty.values())
			if (fp.corresponds(item))
				return fp;
		return null;
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

	public ItemStack getItemStack() {
		ItemStack item = new ItemStack(this.baseMaterial);
		ItemMeta meta = item.getItemMeta();
		meta.setCustomModelData(this.modelData);
		item.setItemMeta(meta);
		return setFoodName(item);
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
						.add(fp.getName(), false, false, false, false, SimpleJSON.Color.WHITE, true).convert());
				nbti.setInteger("CustomModelData", fp.modelData);
			}
		}
	}

	public static ItemStack setFoodName(ItemStack item) {
		return setFoodName(item, null);
	}

	public static ItemStack setFoodNeededLore(ItemStack item, int food, float saturation) {
		NBTItem nbti = new NBTItem(item);
		setFoodNeededLore(nbti, food, saturation);
		return nbti.getItem();
	}
	
	public static void setFoodNeededLore(NBTItem nbti, int food, float saturation) {
		NBTCompound displayTag = nbti.getCompound("display");
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
