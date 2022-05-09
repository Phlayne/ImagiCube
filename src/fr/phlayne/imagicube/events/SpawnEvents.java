package fr.phlayne.imagicube.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.ImagiCube;
import fr.phlayne.imagicube.crafts.armor.WeaponRecipes;
import fr.phlayne.imagicube.data.Config;
import fr.phlayne.imagicube.item.Durability;
import fr.phlayne.imagicube.item.ItemUpdatingCause;
import fr.phlayne.imagicube.item.WeaponProperties;
import fr.phlayne.imagicube.item.WeaponProperty;
import fr.phlayne.imagicube.util.MobSpawnWoodType;

public class SpawnEvents implements Listener {

	public Random random = new Random();

	@EventHandler(priority = EventPriority.HIGHEST)
	public void changeEntityInventory(EntitySpawnEvent event) {
		if (event.getEntity() instanceof LivingEntity && event.getEntity().getTicksLived() == 0) {
			// This code multiplies by 0 every armor and armor toughness values. The armor
			// system is now managed in TODO
			LivingEntity entity = (LivingEntity) event.getEntity();
			entity.getAttribute(Attribute.GENERIC_ARMOR)
					.addModifier(new AttributeModifier("ImagiCube Armor Modifier", -1, Operation.MULTIPLY_SCALAR_1));
			entity.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS)
					.addModifier(new AttributeModifier("ImagiCube Armor Modifier", -1, Operation.MULTIPLY_SCALAR_1));
			//
			for (EquipmentSlot slot : EquipmentSlot.values()) {
				if (entity.getEquipment().getItem(slot) != null
						&& !entity.getEquipment().getItem(slot).getType().equals(Material.AIR)) {
					String woodType = MobSpawnWoodType.getWoodType(entity.getWorld().getBiome(entity.getLocation()));
					if (woodType != null) {
						ItemStack item = entity.getEquipment().getItem(slot);
						if (item != null && item.getType().equals(Material.AIR)) {
							NBTItem nbti = new NBTItem(item);
							switch (slot) {
							case HAND:
							case OFF_HAND:
								MobSpawnWoodType.changeWoodType(nbti, woodType);
								break;
							case FEET:
							case LEGS:
							case CHEST:
							case HEAD:
								MobSpawnWoodType.changeLeatherColor(nbti, woodType);
								break;
							}
							entity.getEquipment().setItem(slot, nbti.getItem());
						}
					}
					if (entity instanceof WitherSkeleton && slot.equals(EquipmentSlot.HAND)) {
						if (entity.getEquipment().getItem(slot).getType().equals(Material.STONE_SWORD)
								&& Config.getConfig().getBoolean("stoneToolsVariants")) {
							// 50% chance of having a blackstone sword or a blackstone random tool
							NBTItem nbti;
							if (random.nextBoolean()) {
								List<WeaponProperty> list = new ArrayList<WeaponProperty>();
								for (WeaponProperty wp : ImagiCube.getInstance().addonList.weapons)
									if (wp.getMaterial().equals("blackstone"))
										list.add(wp);
								WeaponProperty wp = list.get(random.nextInt(list.size()));
								nbti = new NBTItem(WeaponRecipes.setWeaponValues(wp));
								Durability.applyRandomDurability(nbti, Durability.getMaxDurability(nbti));
								entity.getEquipment().setItem(slot, nbti.getItem());
							} else {
								nbti = new NBTItem(WeaponRecipes.setWeaponValues(WeaponProperties.BLACKSTONE_SWORD));
								Durability.applyRandomDurability(nbti, Durability.getMaxDurability(nbti));
								entity.getEquipment().setItem(slot, nbti.getItem());
							}
						}
					} else {
						NBTItem nbti = new NBTItem(ItemUpdatingEvents.update(entity.getEquipment().getItem(slot),
								ItemUpdatingCause.DROP_OR_SPAWN));
						Durability.applyRandomDurability(nbti, Durability.getMaxDurability(nbti));
						entity.getEquipment().setItem(slot, nbti.getItem());
					}
				}
			}
		}
	}

}