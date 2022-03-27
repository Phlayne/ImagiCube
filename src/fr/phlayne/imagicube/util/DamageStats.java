package fr.phlayne.imagicube.util;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.item.Durability;

public class DamageStats {

	public static int getPhysicalArmorLevel(ItemStack item) {
		if (item == null)
			return 0;
		NBTItem nbti = new NBTItem(item);
		return nbti.hasKey(NBTUtil.PHYSICAL_ARMOR) ? nbti.getInteger(NBTUtil.PHYSICAL_ARMOR) : 0;
	}

	public static int getPhysicalResistance(Entity entity) {
		if (entity instanceof LivingEntity) {
			LivingEntity e = (LivingEntity) entity;
			int lt = 0;
			lt += getPhysicalArmorLevel(e.getEquipment().getBoots());
			lt += getPhysicalArmorLevel(e.getEquipment().getLeggings());
			lt += getPhysicalArmorLevel(e.getEquipment().getChestplate());
			lt += getPhysicalArmorLevel(e.getEquipment().getHelmet());
			return lt;
		}
		return 0;
	}

	public static int getArmorToughness(Entity entity) {
		if (entity instanceof LivingEntity) {
			LivingEntity e = (LivingEntity) entity;
			int lt = 0;
			lt += getArmorToughness(e.getEquipment().getBoots());
			lt += getArmorToughness(e.getEquipment().getLeggings());
			lt += getArmorToughness(e.getEquipment().getChestplate());
			lt += getArmorToughness(e.getEquipment().getHelmet());
			return lt;
		}
		return 0;
	}

	public static int getMagicalArmorLevel(ItemStack item) {
		if (item == null)
			return 0;
		NBTItem nbti = new NBTItem(item);
		return nbti.hasKey(NBTUtil.MAGICAL_ARMOR) ? nbti.getInteger(NBTUtil.MAGICAL_ARMOR) : 0;
	}

	public static int getMagicalResistance(Entity entity) {
		if (entity instanceof LivingEntity) {
			LivingEntity e = (LivingEntity) entity;
			int lt = 0;
			if (e instanceof HumanEntity && ((HumanEntity) e).isBlocking())
				lt += 8;
			lt += getMagicalArmorLevel(e.getEquipment().getBoots());
			lt += getMagicalArmorLevel(e.getEquipment().getLeggings());
			lt += getMagicalArmorLevel(e.getEquipment().getChestplate());
			lt += getMagicalArmorLevel(e.getEquipment().getHelmet());
			return lt;
		}
		return 0;
	}

	public static int getArmorToughness(ItemStack item) {
		// TODO Use armorProperty instead of this chunk of code
		if (item == null)
			return 0;
		NBTItem nbti = new NBTItem(item);
		if (!nbti.hasKey(NBTUtil.MATERIAL))
			return 0;
		String material = nbti.getString(NBTUtil.MATERIAL);
		switch (material) {
		case "diamond":
			return 2;
		case "netherite":
			return 3;
		default:
			return 0;
		}
	}

	public static boolean magicDamage(LivingEntity source, Entity target, double damage) {
		if (target instanceof LivingEntity) {
			Vector vector = target.getVelocity();
			((LivingEntity) target).damage(damage);
			EntityDamageEvent event = new EntityDamageEvent(target, DamageCause.MAGIC, damage);
			if (source != null)
				event = new EntityDamageByEntityEvent(source, target, DamageCause.MAGIC, damage);
			target.setLastDamageCause(event);
			target.setVelocity(vector);
			return true;
			// Check if this works, before, I used NMS
		}
		return false;
	}

	public static float getSpellResistance(Entity entity) {
		// Define 40 as max Magical Resistance. Else, people could get confused and
		// change the config files to have a better magical resistance than 40
		return (float) (40 - getMagicalResistance(entity)) / (float) 40;
	}

	public static EntityDamageEvent changeStats(EntityDamageEvent event) {
		if (event.getEntity() instanceof Item)
			return event;
		DamageCause dst = event.getCause();
		if (!event.isCancelled()) {
			if (event.getDamage() == 0.0000123D)
				event.setDamage(0);
			List<DamageCause> magicCauses = Arrays.asList(DamageCause.CUSTOM, DamageCause.LIGHTNING, DamageCause.MAGIC,
					DamageCause.DRAGON_BREATH, DamageCause.FIRE_TICK);
			boolean isDamageMagical = magicCauses.contains(dst);

			// enchantments

			int FPE = 0;
			if (event.getEntity() instanceof LivingEntity) {
				EntityEquipment e = ((LivingEntity) event.getEntity()).getEquipment();
				if (e.getBoots() != null)
					FPE += e.getBoots().getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL);
				if (e.getLeggings() != null)
					FPE += e.getLeggings().getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL);
				if (e.getChestplate() != null)
					FPE += e.getChestplate().getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL);
				if (e.getHelmet() != null)
					FPE += e.getHelmet().getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL);
				if (dst.equals(DamageCause.BLOCK_EXPLOSION) || dst.equals(DamageCause.ENTITY_EXPLOSION)) {
					if (e.getBoots() != null)
						FPE += 2 * e.getBoots().getEnchantmentLevel(Enchantment.PROTECTION_EXPLOSIONS);
					if (e.getLeggings() != null)
						FPE += 2 * e.getLeggings().getEnchantmentLevel(Enchantment.PROTECTION_EXPLOSIONS);
					if (e.getChestplate() != null)
						FPE += 2 * e.getChestplate().getEnchantmentLevel(Enchantment.PROTECTION_EXPLOSIONS);
					if (e.getHelmet() != null)
						FPE += 2 * e.getHelmet().getEnchantmentLevel(Enchantment.PROTECTION_EXPLOSIONS);
				} else if (dst.equals(DamageCause.FIRE) || dst.equals(DamageCause.FIRE_TICK)
						|| dst.equals(DamageCause.LAVA) || dst.equals(DamageCause.HOT_FLOOR)) {
					if (e.getBoots() != null)
						FPE += 2 * e.getBoots().getEnchantmentLevel(Enchantment.PROTECTION_FIRE);
					if (e.getLeggings() != null)
						FPE += 2 * e.getLeggings().getEnchantmentLevel(Enchantment.PROTECTION_FIRE);
					if (e.getChestplate() != null)
						FPE += 2 * e.getChestplate().getEnchantmentLevel(Enchantment.PROTECTION_FIRE);
					if (e.getHelmet() != null)
						FPE += 2 * e.getHelmet().getEnchantmentLevel(Enchantment.PROTECTION_FIRE);
				} else if (dst.equals(DamageCause.PROJECTILE)) {
					if (e.getBoots() != null)
						FPE += 2 * e.getBoots().getEnchantmentLevel(Enchantment.PROTECTION_PROJECTILE);
					if (e.getLeggings() != null)
						FPE += 2 * e.getLeggings().getEnchantmentLevel(Enchantment.PROTECTION_PROJECTILE);
					if (e.getChestplate() != null)
						FPE += 2 * e.getChestplate().getEnchantmentLevel(Enchantment.PROTECTION_PROJECTILE);
					if (e.getHelmet() != null)
						FPE += 2 * e.getHelmet().getEnchantmentLevel(Enchantment.PROTECTION_PROJECTILE);
				} else if (dst.equals(DamageCause.FALL) || dst.equals(DamageCause.FLY_INTO_WALL)) {
					if (e.getBoots() != null)
						FPE += 3 * e.getBoots().getEnchantmentLevel(Enchantment.PROTECTION_FALL);
				}
				FPE = Math.min(20, FPE);
			}
			double dgts = event.getDamage();
			double armorResistance = (isDamageMagical ? getMagicalResistance(event.getEntity())
					: dst.equals(DamageCause.FALL) ? 0 : getPhysicalResistance(event.getEntity()));
			double toughness = isDamageMagical ? 0 : getArmorToughness(event.getEntity());
			armorResistance -= toughness;
			double damageMultiplier = (1.0F
					- (Math.min(20, Math.max(armorResistance / 5.0F, armorResistance - dgts / (2 + toughness / 4.0F)))
							/ 25));
			dgts *= damageMultiplier;
			dgts = dgts * (1 - (FPE / 25));
			/**
			 * if (event instanceof EntityDamageByEntityEvent && (dst ==
			 * DamageCause.ENTITY_ATTACK || dst == DamageCause.ENTITY_SWEEP_ATTACK)) {
			 * EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event; Vector v =
			 * e.getDamager().getVelocity(); dgts += Math.sqrt(v.getX() * v.getX() +
			 * v.getY() * v.getY() + v.getZ() * v.getZ()) * 10; // Velocity has an impact on
			 * damage }
			 */
			if (dst.equals(DamageCause.CRAMMING) || dst.equals(DamageCause.DRAGON_BREATH)
					|| dst.equals(DamageCause.DROWNING) || dst.equals(DamageCause.FIRE_TICK)
					|| dst.equals(DamageCause.POISON) || dst.equals(DamageCause.STARVATION)
					|| dst.equals(DamageCause.SUFFOCATION) || dst.equals(DamageCause.SUICIDE)
					|| dst.equals(DamageCause.VOID) || dst.equals(DamageCause.WITHER))
				dgts = event.getDamage();
			event.setDamage(0);
			event.setDamage(dgts);

			if (event instanceof EntityDamageByEntityEvent) {
				EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
				if (event.getEntity() instanceof HumanEntity) {
					HumanEntity player = (HumanEntity) event.getEntity();
					if (player.isBlocking()) {
						float playerYaw = player.getLocation().getYaw();
						if (playerYaw < 0)
							playerYaw += 360;
						Vector difference = e.getDamager().getLocation().toVector()
								.subtract(player.getLocation().toVector()).normalize();
						double damagerYaw = Math.atan(difference.getX() / (difference.getZ())) * (180D / Math.PI);
						if (damagerYaw < 0)
							damagerYaw += 360;
						if (Math.abs(damagerYaw - playerYaw) > 90F) {

							// TODO cancel damage & calculate new durability
							int shieldDamage = (int) Math.ceil(event.getDamage());
							if (shieldDamage > 2) {
								if (player.getEquipment().getItemInMainHand().getType().equals(Material.SHIELD)) {
									ItemStack newShield = player.getEquipment().getItemInMainHand();
									for (int i = 0; i < shieldDamage; i++)
										newShield = Durability.applyDurability(newShield,
												newShield.getEnchantmentLevel(Enchantment.DURABILITY));
									player.getEquipment().setItemInMainHand(newShield);
								} else if (player.getEquipment().getItemInMainHand().getType()
										.equals(Material.SHIELD)) {
									ItemStack newShield = player.getEquipment().getItemInOffHand();
									for (int i = 0; i < shieldDamage; i++)
										newShield = Durability.applyDurability(newShield,
												newShield.getEnchantmentLevel(Enchantment.DURABILITY));
									player.getEquipment().setItemInOffHand(newShield);
								}
							}
						}
					}
				}
			}
		}
		return event;
	}
}