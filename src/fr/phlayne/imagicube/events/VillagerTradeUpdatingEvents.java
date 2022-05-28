package fr.phlayne.imagicube.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import fr.phlayne.imagicube.exception.CannotUpdateItemException;
import fr.phlayne.imagicube.item.ItemUpdatingCause;
import fr.phlayne.imagicube.util.ItemUpdater;

public class VillagerTradeUpdatingEvents implements Listener {

	@EventHandler
	public void onVillagerClick(PlayerInteractAtEntityEvent event) {
		if (event.getHand().equals(EquipmentSlot.OFF_HAND))
			return;
		if (event.getRightClicked() instanceof Villager) {
			Villager villager = (Villager) event.getRightClicked();
			List<MerchantRecipe> recipes = new ArrayList<MerchantRecipe>();
			for (MerchantRecipe recipe : villager.getRecipes()) {
				ItemStack resultItem = null;
				try {
					resultItem = ItemUpdater.updateItem(recipe.getResult(), ItemUpdatingCause.VILLAGER);
				} catch (CannotUpdateItemException e) {
					e.printStackTrace();
				}
				if (resultItem == null) {
					recipes.add(recipe);
				} else {
					MerchantRecipe newRecipe = new MerchantRecipe(resultItem, recipe.getUses(), recipe.getMaxUses(),
							recipe.hasExperienceReward(), recipe.getVillagerExperience(), recipe.getPriceMultiplier());
					newRecipe.setIngredients(recipe.getIngredients());
					recipes.add(newRecipe);
				}
			}
			villager.setRecipes(recipes);
		}
	}
}
