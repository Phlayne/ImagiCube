package fr.phlayne.imagicube.crafts;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

import fr.phlayne.imagicube.Reference;

public class ConcreteCrafts implements Listener {

	public static ShapelessRecipe WHITE_CONCRETE_CRAFT;
	public static ShapelessRecipe ORANGE_CONCRETE_CRAFT;
	public static ShapelessRecipe MAGENTA_CONCRETE_CRAFT;
	public static ShapelessRecipe LIGHT_BLUE_CONCRETE_CRAFT;
	public static ShapelessRecipe YELLOW_CONCRETE_CRAFT;
	public static ShapelessRecipe LIME_CONCRETE_CRAFT;
	public static ShapelessRecipe PINK_CONCRETE_CRAFT;
	public static ShapelessRecipe GRAY_CONCRETE_CRAFT;
	public static ShapelessRecipe LIGHT_GRAY_CONCRETE_CRAFT;
	public static ShapelessRecipe CYAN_CONCRETE_CRAFT;
	public static ShapelessRecipe PURPLE_CONCRETE_CRAFT;
	public static ShapelessRecipe BLUE_CONCRETE_CRAFT;
	public static ShapelessRecipe BROWN_CONCRETE_CRAFT;
	public static ShapelessRecipe GREEN_CONCRETE_CRAFT;
	public static ShapelessRecipe RED_CONCRETE_CRAFT;
	public static ShapelessRecipe BLACK_CONCRETE_CRAFT;

	public static void init() {
		Plugin plugin = Bukkit.getPluginManager().getPlugin(Reference.PLUGIN_NAME);
		WHITE_CONCRETE_CRAFT = new ShapelessRecipe(new NamespacedKey(plugin, "white_concrete_powder_recipe"),
				new ItemStack(Material.WHITE_CONCRETE, 8));
		ORANGE_CONCRETE_CRAFT = new ShapelessRecipe(new NamespacedKey(plugin, "orange_concrete_powder_recipe"),
				new ItemStack(Material.ORANGE_CONCRETE, 8));
		MAGENTA_CONCRETE_CRAFT = new ShapelessRecipe(new NamespacedKey(plugin, "magenta_concrete_powder_recipe"),
				new ItemStack(Material.MAGENTA_CONCRETE, 8));
		LIGHT_BLUE_CONCRETE_CRAFT = new ShapelessRecipe(new NamespacedKey(plugin, "light_blue_concrete_powder_recipe"),
				new ItemStack(Material.LIGHT_BLUE_CONCRETE, 8));
		YELLOW_CONCRETE_CRAFT = new ShapelessRecipe(new NamespacedKey(plugin, "yellow_concrete_powder_recipe"),
				new ItemStack(Material.YELLOW_CONCRETE, 8));
		LIME_CONCRETE_CRAFT = new ShapelessRecipe(new NamespacedKey(plugin, "lime_concrete_powder_recipe"),
				new ItemStack(Material.LIME_CONCRETE, 8));
		PINK_CONCRETE_CRAFT = new ShapelessRecipe(new NamespacedKey(plugin, "pink_concrete_powder_recipe"),
				new ItemStack(Material.PINK_CONCRETE, 8));
		GRAY_CONCRETE_CRAFT = new ShapelessRecipe(new NamespacedKey(plugin, "gray_concrete_powder_recipe"),
				new ItemStack(Material.GRAY_CONCRETE, 8));
		LIGHT_GRAY_CONCRETE_CRAFT = new ShapelessRecipe(new NamespacedKey(plugin, "light_gray_concrete_powder_recipe"),
				new ItemStack(Material.LIGHT_GRAY_CONCRETE, 8));
		CYAN_CONCRETE_CRAFT = new ShapelessRecipe(new NamespacedKey(plugin, "cyan_concrete_powder_recipe"),
				new ItemStack(Material.CYAN_CONCRETE, 8));
		PURPLE_CONCRETE_CRAFT = new ShapelessRecipe(new NamespacedKey(plugin, "purple_concrete_powder_recipe"),
				new ItemStack(Material.PURPLE_CONCRETE, 8));
		BLUE_CONCRETE_CRAFT = new ShapelessRecipe(new NamespacedKey(plugin, "blue_concrete_powder_recipe"),
				new ItemStack(Material.BLUE_CONCRETE, 8));
		BROWN_CONCRETE_CRAFT = new ShapelessRecipe(new NamespacedKey(plugin, "brown_concrete_powder_recipe"),
				new ItemStack(Material.BROWN_CONCRETE, 8));
		GREEN_CONCRETE_CRAFT = new ShapelessRecipe(new NamespacedKey(plugin, "green_concrete_powder_recipe"),
				new ItemStack(Material.GREEN_CONCRETE, 8));
		RED_CONCRETE_CRAFT = new ShapelessRecipe(new NamespacedKey(plugin, "red_concrete_powder_recipe"),
				new ItemStack(Material.RED_CONCRETE, 8));
		BLACK_CONCRETE_CRAFT = new ShapelessRecipe(new NamespacedKey(plugin, "black_concrete_powder_recipe"),
				new ItemStack(Material.BLACK_CONCRETE, 8));
		if (plugin.getConfig().getBoolean("concrete_powder_to_concrete_craft_with_bucket")) {
			initCraft(WHITE_CONCRETE_CRAFT, Material.WHITE_CONCRETE_POWDER);
			initCraft(ORANGE_CONCRETE_CRAFT, Material.ORANGE_CONCRETE_POWDER);
			initCraft(MAGENTA_CONCRETE_CRAFT, Material.MAGENTA_CONCRETE_POWDER);
			initCraft(LIGHT_BLUE_CONCRETE_CRAFT, Material.LIGHT_BLUE_CONCRETE_POWDER);
			initCraft(YELLOW_CONCRETE_CRAFT, Material.YELLOW_CONCRETE_POWDER);
			initCraft(LIME_CONCRETE_CRAFT, Material.LIME_CONCRETE_POWDER);
			initCraft(PINK_CONCRETE_CRAFT, Material.PINK_CONCRETE_POWDER);
			initCraft(GRAY_CONCRETE_CRAFT, Material.GRAY_CONCRETE_POWDER);
			initCraft(LIGHT_GRAY_CONCRETE_CRAFT, Material.LIGHT_GRAY_CONCRETE_POWDER);
			initCraft(CYAN_CONCRETE_CRAFT, Material.CYAN_CONCRETE_POWDER);
			initCraft(PURPLE_CONCRETE_CRAFT, Material.PURPLE_CONCRETE_POWDER);
			initCraft(BLUE_CONCRETE_CRAFT, Material.BLUE_CONCRETE_POWDER);
			initCraft(BROWN_CONCRETE_CRAFT, Material.BROWN_CONCRETE_POWDER);
			initCraft(GREEN_CONCRETE_CRAFT, Material.GREEN_CONCRETE_POWDER);
			initCraft(RED_CONCRETE_CRAFT, Material.RED_CONCRETE_POWDER);
			initCraft(BLACK_CONCRETE_CRAFT, Material.BLACK_CONCRETE_POWDER);
		}

	}

	public static void initCraft(ShapelessRecipe recipe, Material material) {
		recipe.addIngredient(Material.WATER_BUCKET);
		for (int i = 0; i < 8; i++)
			recipe.addIngredient(material);
		Bukkit.addRecipe(recipe);
	}

	@EventHandler
	public void onCraft(CraftItemEvent event) {
		List<ShapelessRecipe> recipes = Arrays.asList(WHITE_CONCRETE_CRAFT, ORANGE_CONCRETE_CRAFT,
				MAGENTA_CONCRETE_CRAFT, LIGHT_BLUE_CONCRETE_CRAFT, YELLOW_CONCRETE_CRAFT, LIME_CONCRETE_CRAFT,
				PINK_CONCRETE_CRAFT, GRAY_CONCRETE_CRAFT, LIGHT_GRAY_CONCRETE_CRAFT, CYAN_CONCRETE_CRAFT,
				PURPLE_CONCRETE_CRAFT, BLUE_CONCRETE_CRAFT, BROWN_CONCRETE_CRAFT, GREEN_CONCRETE_CRAFT,
				RED_CONCRETE_CRAFT, BLACK_CONCRETE_CRAFT);
		if (recipes.contains(event.getRecipe())) {
			int craftAmount = getAmountCraftItem(event.getRecipe().getResult().getType(), event) / 8;
			event.getWhoClicked().getInventory().addItem(new ItemStack(Material.BUCKET, craftAmount));
		}
	}

	public static int getAmountCraftItem(Material m, CraftItemEvent e) {
		if (e.isCancelled())
			return 0;
		if (!e.getRecipe().getResult().getType().equals(m))
			return 0;
		int amount = e.getRecipe().getResult().getAmount();
		if (e.isShiftClick()) {
			int max = e.getInventory().getMaxStackSize();
			org.bukkit.inventory.ItemStack[] matrix = e.getInventory().getMatrix();
			for (org.bukkit.inventory.ItemStack is : matrix) {
				if (is == null || is.getType().equals(Material.AIR))
					continue;
				int tmp = is.getAmount();
				if (tmp < max && tmp > 0)
					max = tmp;
			}
			amount *= max;
		}
		return amount;
	}
	// Source :
	// https://www.spigotmc.org/threads/util-get-the-crafted-item-amount-from-a-craftitemevent.162952/
	// Thanks to Kwizzy
}