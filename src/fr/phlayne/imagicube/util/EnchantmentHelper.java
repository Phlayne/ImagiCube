package fr.phlayne.imagicube.util;

import org.bukkit.configuration.file.FileConfiguration;

import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.ImagiCube;
import fr.phlayne.imagicube.data.Config;
import fr.phlayne.imagicube.util.SimpleJSON.Color;

public class EnchantmentHelper {

	public static int getMaxLevel(String key) {
		return getMaxLevel(key, null);
	}

	public static int getMaxLevel(String key, String material) {
		int maxLevel = 1;
		for (FileConfiguration config : Config.getConfigs("enchantments")) {
			if (config.contains(key + ".max_level.base"))
				maxLevel = config.getInt(key + ".max_level.base");
			if (material != null) {
				if (config.contains(key + ".max_level." + material))
					maxLevel = config.getInt(key + ".max_level." + material);
			}
		}
		return maxLevel;
	}

	public static boolean isCompatibleWith(String key1, String key2) {
		boolean isCompatible = true;
		for (FileConfiguration config : Config.getConfigs("enchantments")) {
			String path = key1 + ".compatibility.exclude";
			isCompatible &= !config.contains(path) || !config.getStringList(path).contains(key2);
		}
		return isCompatible;
	}

	public static boolean isCompatibleWith(String key, NBTItem nbti) {
		for (FileConfiguration config : Config.getConfigs("enchantments")) {
			if (nbti.hasKey(NBTUtil.ITEM_TYPE)) {
				if (config.getBoolean(key + ".compatibility.everything"))
					return true;
				if (config.getBoolean(key + ".compatibility.everything_having_durability")
						&& nbti.hasKey(NBTUtil.DURABILITY))
					return true;
				if (config.getBoolean(key + ".compatibility.every_sword") && isGenericType(nbti, "sword"))
					return true;
				if (config.getBoolean(key + ".compatibility.every_tool") && isGenericType(nbti, "tool"))
					return true;
				if (config.getBoolean(key + ".compatibility.every_axe") && isGenericType(nbti, "axe"))
					return true;
				if (config.getBoolean(key + ".compatibility.every_pickaxe") && isGenericType(nbti, "pickaxe"))
					return true;
				if (config.getBoolean(key + ".compatibility.every_fishing_rod") && isGenericType(nbti, "fishing_rod"))
					return true;
				if (config.getBoolean(key + ".compatibility.every_bow") && isGenericType(nbti, "bow"))
					return true;
				if (config.getBoolean(key + ".compatibility.every_crossbow") && isGenericType(nbti, "crossbow"))
					return true;
				if (config.contains(key + ".compatibility.type") && config.getStringList(key + ".compatibility.type")
						.contains(nbti.getString(NBTUtil.ITEM_TYPE)))
					return true;
			}
		}
		return false;
	}

	private static boolean isGenericType(NBTItem nbti, String type) {
		return nbti.hasKey(NBTUtil.ITEM_TYPE) && ImagiCube.getInstance().getAddonList().getItemsInGroup(type)
				.contains(nbti.getString(NBTUtil.ITEM_TYPE));
	}

	public static Color getItemBaseColor(NBTItem nbti) {
		boolean forcedColor = nbti.hasKey(NBTUtil.FORCED_COLOR) ? !nbti.getString(NBTUtil.FORCED_COLOR).equals("none")
				: false;
		Color color = Color.WHITE;
		Rarity rarity;
		switch (nbti.getItem().getType()) {
		case CREEPER_BANNER_PATTERN:
		case SKULL_BANNER_PATTERN:
		case EXPERIENCE_BOTTLE:
		case DRAGON_BREATH:
		case ELYTRA:
		case ENCHANTED_BOOK:
		case CREEPER_HEAD:
		case DRAGON_HEAD:
		case PLAYER_HEAD:
		case ZOMBIE_HEAD:
		case SKELETON_SKULL:
		case WITHER_SKELETON_SKULL:
		case NETHER_STAR:
			rarity = Rarity.UNCOMMON;
			break;
		case BEACON:
		case CONDUIT:
		case END_CRYSTAL:
		case GOLDEN_APPLE:
		case MUSIC_DISC_11:
		case MUSIC_DISC_13:
		case MUSIC_DISC_BLOCKS:
		case MUSIC_DISC_CAT:
		case MUSIC_DISC_CHIRP:
		case MUSIC_DISC_FAR:
		case MUSIC_DISC_MALL:
		case MUSIC_DISC_MELLOHI:
		case MUSIC_DISC_PIGSTEP:
		case MUSIC_DISC_STAL:
		case MUSIC_DISC_STRAD:
		case MUSIC_DISC_WAIT:
		case MUSIC_DISC_WARD:
			rarity = Rarity.RARE;
			break;
		case FLOWER_BANNER_PATTERN:
		case GLOBE_BANNER_PATTERN:
		case MOJANG_BANNER_PATTERN:
		case PIGLIN_BANNER_PATTERN:
		case COMMAND_BLOCK:
		case CHAIN_COMMAND_BLOCK:
		case REPEATING_COMMAND_BLOCK:
		case DRAGON_EGG:
		case ENCHANTED_GOLDEN_APPLE:
		case STRUCTURE_BLOCK:
		case JIGSAW:
			rarity = Rarity.EPIC;
			break;
		default:
			rarity = Rarity.COMMON;
		}
		color = rarity.color;
		if (forcedColor)
			color = new Color(nbti.getString(NBTUtil.FORCED_COLOR));
		return color;
		// TODO put all this in a config file, and change the item color on item
		// updating so it is customizable
	}

	public enum Rarity {
		COMMON(Color.WHITE, 0), UNCOMMON(Color.YELLOW, 1), RARE(Color.AQUA, 2), EPIC(Color.LIGHT_PURPLE, 3);

		public Color color;
		public int value;

		Rarity(Color color, int value) {
			this.color = color;
			this.value = value;
		}
	}
}
