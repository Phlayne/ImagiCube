package fr.phlayne.imagicube.item;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.data.Config;
import fr.phlayne.imagicube.util.NBTUtil;
import fr.phlayne.imagicube.util.SimpleJSON;
import fr.phlayne.imagicube.util.SimpleJSON.Color;

public class Durability {

	public static Random random = new Random();

	protected static int getMaxDurability(WeaponProperty weaponProperty) {
		return weaponProperty.getDurability();
	}

	protected static int getMaxDurability(ArmorProperty armorProperty) {
		return armorProperty.getDurability();
	}

	public static int getMaxDurability(String type) {
		for (FileConfiguration durability : Config.getConfigs(Config.DURABILITY)) {
			if (durability.contains("minecraft." + type))
				return durability.getInt("minecraft." + type);
			if (durability.contains("imagicube." + type))
				return durability.getInt("imagicube." + type);
		}
		switch (type) {
		case "elytra":
			return 432;
		case "bow":
			return 384;
		case "crossbow":
			return 326;
		case "trident":
			return 246;
		case "shield":
			return 336;
		case "flint_and_steel":
			return 64;
		case "fishing_rod":
			return 64;
		case "carrot_on_a_stick":
			return 25;
		case "warped_fungus_on_a_stick":
			return 100;
		case "shears":
			return 238;
		}
		return 1;
	}

	public static int getMaxDurability(NBTItem nbti) {
		ArmorProperty armorProperty = ArmorProperty.getArmorProperty(nbti);
		if (armorProperty != null)
			return armorProperty.getDurability();
		WeaponProperty weaponProperty = WeaponProperty.getWeaponProperty(nbti);
		if (weaponProperty != null)
			return weaponProperty.getDurability();
		if (nbti.hasKey(NBTUtil.ITEM_TYPE))
			return getMaxDurability(nbti.getString(NBTUtil.ITEM_TYPE));
		return 1;
	}

	// Must be executed on a updated item;
	public static void applyRandomDurability(NBTItem nbti, int maxDurability) {
		int durability = random.nextInt(maxDurability);
		setDurability(nbti, durability, getPercentDurability(nbti, durability, maxDurability));
	}

	public static boolean isDamageable(NBTItem nbti) {
		return nbti.hasKey(NBTUtil.DURABILITY);
	}

	// Returns true if the item should break;
	public static boolean applyDurability(NBTItem nbti) {
		int maxDurability = getMaxDurability(nbti);
		int unbreakingLevel = nbti.getItem().getEnchantmentLevel(Enchantment.DURABILITY);
		if (nbti != null && nbti.hasKey(NBTUtil.DURABILITY) && maxDurability != 1) {
			List<String> armorParts = Arrays.asList("boots", "leggings", "chestplate", "helmet");
			boolean isArmor = nbti.hasKey(NBTUtil.ITEM_TYPE) ? armorParts.contains(nbti.getString(NBTUtil.ITEM_TYPE))
					: false;
			boolean damage = random.nextFloat() < (!isArmor ? (1F / (unbreakingLevel + 1F))
					: (0.6F * (0.4F / (unbreakingLevel + 1F))));
			if (damage) {
				int durability = nbti.getInteger(NBTUtil.DURABILITY);
				setDurability(nbti, durability + 1, getPercentDurability(nbti, durability + 1, maxDurability));
				boolean isElytra = (nbti.hasKey(NBTUtil.ITEM_TYPE) ? nbti.getString(NBTUtil.ITEM_TYPE) : "")
						.equals("elytra");
				if (!isElytra && durability + 1 >= maxDurability) {
					return true;
				}
			}
		}
		return false;
	}

	public static float getPercentDurability(NBTItem nbti) {
		return getPercentDurability(nbti, nbti.getInteger(NBTUtil.DURABILITY), getMaxDurability(nbti));
	}

	protected static float getPercentDurability(NBTItem nbti, int durability, int maxDurability) {
		if (durability < 0)
			durability = nbti.hasKey(NBTUtil.DURABILITY) ? nbti.getInteger(NBTUtil.DURABILITY) : 0;
		return 1 - ((float) durability / ((float) maxDurability));
	}

	public static Color getColorDurability(float percentDurability) {
		return getColorDurability(percentDurability, null);
	}

	public static Color getColorDurability(float percentDurability, Color forcedColor) {
		if (forcedColor != null)
			return forcedColor.multiply(percentDurability);
		if (percentDurability < 0.02)
			return new Color(128, 0, 0);
		float red = Math.min(1F, 0.3359375F * percentDurability + 0.9140625F);
		float green = 1F - (1 - percentDurability) * (1 - percentDurability);
		float blue = 1F - (float) Math.sqrt(1 - percentDurability);
		return new Color((int) (red * 255), (int) (green * 255), (int) (blue * 255));
	}

	protected static Color getColorDurability(NBTItem nbti, int durability, int maxDurability) {
		return getColorDurability(getPercentDurability(nbti, durability, maxDurability), getForcedColor(nbti));
	}

	protected static Color getColorDurability(NBTItem nbti, int durability, int maxDurability, Color forcedColor) {
		return getColorDurability(getPercentDurability(nbti, durability, maxDurability), forcedColor);
	}

	public static Color getForcedColor(NBTItem nbti) {
		return nbti.hasKey(NBTUtil.FORCED_COLOR) ? !nbti.getString(NBTUtil.FORCED_COLOR).equals("none")
				? new Color(nbti.getString(NBTUtil.FORCED_COLOR))
				: null : null;
	}

	public static void setDurability(NBTItem nbti, int durability) {
		setDurability(nbti, durability, 1 - (float) durability / getMaxDurability(nbti));
	}

	public static void setDurability(NBTItem nbti, int durability, float percentDurability) {
		Color color = getColorDurability(percentDurability, getForcedColor(nbti));
		int percent = (int) (100F * percentDurability);
		NBTCompound displayTag = nbti.getOrCreateCompound("display");
		if (!displayTag.hasKey("Lore"))
			NBTUtil.addLore(displayTag,
					new SimpleJSON().add("", false, false, false, false, new Color(255, 255, 255), false).convert());
		NBTUtil.changeLore(displayTag,
				new SimpleJSON().add("imagicube.durability", false, false, false, false, Color.GRAY, true)
						.add(" " + percent + "%", false, false, false, false, color.multiply(2 / 3F), false).convert(),
				0);
		if (displayTag.hasKey("Name")) {
			SimpleJSON simpleJsonName = NBTUtil.readName(nbti, color);
			displayTag.setString("Name", simpleJsonName.convert());
		} else {
			displayTag.setString("Name",
					new SimpleJSON().add("item.minecraft." + nbti.getItem().getType().getKey().getKey(), false, false,
							false, false, color, true).convert());
		}

		nbti.setInteger(NBTUtil.DURABILITY, durability);
		if ((nbti.hasKey(NBTUtil.ITEM_TYPE) ? nbti.getString(NBTUtil.ITEM_TYPE) : "").equals("elytra")) {
			if (percent <= 0)
				nbti.setInteger("Damage", 432);
			else
				nbti.setInteger("Damage", 0);
		}
	}

	public static void playBreakItemAnimation(HumanEntity player, ItemStack item) {
		Location loc = player.getEyeLocation();
		loc.getWorld().playSound(loc, Sound.ENTITY_ITEM_BREAK, 1.0f, 1.0f);
		loc.getWorld().spawnParticle(Particle.ITEM_CRACK, loc.add(loc.getDirection()), 10, 0.3, 0.5, 0.3, 0, item);

	}
}