package fr.phlayne.imagicube.item;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTCompoundList;
import de.tr7zw.nbtapi.NBTContainer;
import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.util.NBTUtil;
import fr.phlayne.imagicube.util.SimpleJSON;
import fr.phlayne.imagicube.util.SimpleJSON.Color;

public class Durability {

	public static Random random = new Random();

	public static int getMaxDurability(ItemStack item) {
		NBTItem nbti = new NBTItem(item);
		WeaponProperty weaponProperty = WeaponProperty.getWeaponProperty(nbti);
		ArmorProperty armorProperty = ArmorProperty.getArmorProperty(nbti);
		if (weaponProperty != null)
			return weaponProperty.getDurability();
		if (armorProperty != null)
			return armorProperty.getDurability();
		if (nbti.hasKey(NBTUtil.ITEM_TYPE)) {
			String itemType = nbti.getString(NBTUtil.ITEM_TYPE);
			return getMaxDurability(itemType);
		}
		return 1;
	}

	// TODO Create a config file where you can change the max durability.
	public static int getMaxDurability(String type) {
		switch (type) {
		case "spell":
			return 342;
		case "elytra":
			return 432;
		case "bow":
			return 384;
		case "bamboo_bow":
			return 268;
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
		case "slingshot":
			return 312;
		}
		return 1;
	}

	public static ItemStack applyRandomDurability(ItemStack item) {
		if (new NBTItem(item).hasKey(NBTUtil.DURABILITY))
			return setDurability(item, random.nextInt(getMaxDurability(item)));
		else
			return item;
	}

	public static ItemStack applyDurability(ItemStack item) {
		return applyDurability(item, item.getEnchantmentLevel(Enchantment.DURABILITY));
	}

	public static ItemStack applyDurability(ItemStack item, int unbreakingLevel) {
		NBTItem nbti = new NBTItem(item);
		if (nbti != null) {
			if (nbti.hasKey(NBTUtil.DURABILITY)) {
				int maxDurability = getMaxDurability(item);
				if (maxDurability != 1) {
					ArmorProperty armorProperty = ArmorProperty.getArmorProperty(nbti);
					boolean damage = random.nextFloat() < (armorProperty == null ? (1F / (unbreakingLevel + 1F))
							: (0.6F * (0.4F / (unbreakingLevel + 1F))));
					if (damage) {
						int durability = nbti.getInteger(NBTUtil.DURABILITY);
						item = setDurability(item, durability + 1);
						boolean isElytra = (nbti.hasKey(NBTUtil.ITEM_TYPE) ? nbti.getString(NBTUtil.ITEM_TYPE) : "")
								.equals("elytra");
						if (!isElytra && durability + 1 >= maxDurability) {
							item = new ItemStack(Material.AIR);
						}
					}
				}
			}
		}
		return item;
	}

	public static float getPercentDurability(ItemStack item) {
		return getPercentDurability(item, -1);
	}

	public static float getPercentDurability(ItemStack item, int durability) {
		return getPercentDurability(new NBTItem(item), durability);
	}

	public static float getPercentDurability(NBTCompound tag) {
		return getPercentDurability(tag, -1);
	}

	public static float getPercentDurability(NBTCompound tag, int durability) {
		WeaponProperty weaponProperty = WeaponProperty.getWeaponProperty(tag);
		ArmorProperty armorProperty = ArmorProperty.getArmorProperty(tag);
		if (durability < 0)
			durability = tag.hasKey(NBTUtil.DURABILITY) ? tag.getInteger(NBTUtil.DURABILITY) : 0;
		return 1 - ((float) durability / ((float) (weaponProperty != null ? weaponProperty.getDurability()
				: armorProperty != null ? armorProperty.getDurability()
						: getMaxDurability(
								((tag.hasKey(NBTUtil.ITEM_TYPE) ? tag.getString(NBTUtil.ITEM_TYPE) : ""))))));
	}

	public static Color getColorDurability(float percentDurability) {
		return getColorDurability(percentDurability, false, null);
	}

	public static Color getColorDurability(float percentDurability, boolean isSpell) {
		return getColorDurability(percentDurability, isSpell, null);
	}

	public static Color getColorDurability(float percentDurability, Color forcedColor) {
		return getColorDurability(percentDurability, false, forcedColor);
	}

	public static Color getColorDurability(float percentDurability, boolean isSpell, Color forcedColor) {
		if (forcedColor != null)
			return forcedColor.multiply(percentDurability);
		/*
		 * // "Stair" Mode.
		 * 
		 * if (percentDurability <= 0.02) return Color.DARK_RED; if (percentDurability
		 * <= 0.1) return Color.RED; if (percentDurability <= 0.5) return Color.YELLOW;
		 * return Color.WHITE;
		 */
		// Continuous Mode.
		if (percentDurability < 0.02)
			if (!isSpell)
				return new Color(128, 0, 0);
		float red = 1.0F;
		float green = 1.0F;
		float blue = 1.0F;
		if (isSpell) {
			red = percentDurability;
			green = 0.334F * percentDurability;
			blue = percentDurability;
		} else {
			red = Math.min(1F, 0.3359375F * percentDurability + 0.9140625F);
			green = 1F - (1 - percentDurability) * (1 - percentDurability);
			blue = 1F - (float) Math.sqrt(1 - percentDurability);
		}
		return new Color((int) (red * 255), (int) (green * 255), (int) (blue * 255));
	}

	public static Color getColorDurability(ItemStack item, int durability) {
		NBTItem nbti = new NBTItem(item);
		return getColorDurability(getPercentDurability(item, durability), nbti.hasKey(NBTUtil.SPELL),
				nbti.hasKey("imagicube.forced_color")
						? nbti.getString("imagicube.forced_color").equals("none")
								? new Color(nbti.getString("imagicube.forced_color"))
								: null
						: null);
	}

	public static Color getColorDurability(ItemStack item, int durability, Color forcedColor) {
		return getColorDurability(getPercentDurability(item, durability), new NBTItem(item).hasKey(NBTUtil.SPELL),
				forcedColor);
	}

	public static Color getColorDurability(ItemStack item) {
		NBTItem nbti = new NBTItem(item);
		return getColorDurability(getPercentDurability(item), nbti.hasKey(NBTUtil.SPELL),
				nbti.hasKey("imagicube.forced_color")
						? nbti.getString("imagicube.forced_color").equals("none")
								? new Color(nbti.getString("imagicube.forced_color"))
								: null
						: null);
	}

	public static Color getColorDurability(ItemStack item, Color forcedColor) {
		return getColorDurability(getPercentDurability(item), new NBTItem(item).hasKey(NBTUtil.SPELL), forcedColor);
	}

	public static ItemStack setDurability(ItemStack item, int durability) {
		NBTItem nbti = new NBTItem(item);
		setDurability(nbti, durability);
		return nbti.getItem();
	}
	
	public static void setDurability(NBTItem nbti, int durability) {
		NBTCompound displayTag = nbti.getOrCreateCompound("display");
		if (!displayTag.hasKey("Lore"))
			NBTUtil.addLore(displayTag,
					new SimpleJSON().add("", false, false, false, false, new Color(255, 255, 255), false).convert());
		int percent = (int) (100F * getPercentDurability(nbti, durability));
		Color color = getColorDurability(nbti.getItem(), durability);
		NBTUtil.changeLore(displayTag,
				new SimpleJSON().add("imagicube.durability", false, false, false, false, Color.GRAY, true)
						.add(" " + percent + "%", false, false, false, false, color.multiply(2 / 3F), false).convert(),
				0);
		if (displayTag.hasKey("Name")) {
			NBTContainer name = new NBTContainer(displayTag.getString("Name"));
			boolean isTranslate = name.hasKey("translate");
			SimpleJSON jsonName = new SimpleJSON().add(
					isTranslate ? name.getString("translate") : name.getString("text"), !isTranslate,
					name.getBoolean("bold"), name.getBoolean("underlined"), name.getBoolean("strikethrough"), color,
					isTranslate);
			NBTCompoundList list = name.getCompoundList("extra");
			for (NBTCompound nameTag : list) {
				jsonName.add(nameTag.hasKey("translate") ? nameTag.getString("translate") : nameTag.getString("text"),
						!nameTag.hasKey("translate"), nameTag.getBoolean("bold"), nameTag.getBoolean("underlined"),
						nameTag.getBoolean("strikethrough"), color, nameTag.hasKey("translate"));
			}
			displayTag.setString("Name", jsonName.convert());
		} else {
			displayTag.setString("Name", new SimpleJSON()
					.add("item.minecraft." + nbti.getItem().getType().getKey().getKey(), false, false, false, false, color, true)
					.convert());
		}

		nbti.setInteger(NBTUtil.DURABILITY, durability);
		if ((nbti.hasKey(NBTUtil.ITEM_TYPE) ? nbti.getString(NBTUtil.ITEM_TYPE) : "").equals("elytra")) {
			if (percent <= 0)
				nbti.setInteger("Damage", 432);
			else
				nbti.setInteger("Damage", 0);
		}
	}
}