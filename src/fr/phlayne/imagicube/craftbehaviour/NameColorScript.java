package fr.phlayne.imagicube.craftbehaviour;

import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.item.Durability;
import fr.phlayne.imagicube.util.NBTUtil;
import fr.phlayne.imagicube.util.SimpleJSON;
import fr.phlayne.imagicube.util.SimpleJSON.Color;
import fr.phlayne.imagicube.util.TranslatableReference;

public class NameColorScript implements FuseScript {

	public FuseResult getAnvilResult(NBTItem leftItem, NBTItem rightItem, NBTItem result, String newName) {
		Color color = getColor(rightItem);
		if (color != null) {
			result.setString("imagicube.forced_color", color.getColor());
			// We then multiply if there is durability
			if (result.hasKey(NBTUtil.DURABILITY))
				color.multiply(Durability.getPercentDurability(result));
			SimpleJSON name = NBTUtil.readName(result, color);
			// If the item doesn't have a name but we want to change its color, we choose it
			if (name.convert().equals(new SimpleJSON().convert())) {
				name = new SimpleJSON().add(TranslatableReference.getUnlocalizedName(result.getItem().getType()), false,
						false, false, false, color, true);
			}
			// Rename item at the end
			result.getOrCreateCompound("display").setString("Name", name.convert());
			Durability.setDurability(result, result.getInteger(NBTUtil.DURABILITY));
			// This reloads the lore and name of the item according to this new durability.
			return new FuseResult(result.getItem(), 1, 1).showResult(true);
		}
		return new FuseResult(result.getItem(), 0, 0);
	}

	public Color getColor(NBTItem item) {
		Color color = null;
		if (item != null) {
			switch (item.getItem().getType()) {
			case RED_DYE:
				color = Color.RED;
				break;
			case GREEN_DYE:
				color = Color.DARK_GREEN;
				break;
			case PURPLE_DYE:
				color = Color.LIGHT_PURPLE;
				break;
			case CYAN_DYE:
				color = Color.DARK_AQUA;
				break;
			case LIGHT_GRAY_DYE:
				color = Color.GRAY;
				break;
			case GRAY_DYE:
				color = Color.DARK_GRAY;
				break;
			case PINK_DYE:
				color = new Color("#aa55aa");
				break;
			case LIME_DYE:
				color = Color.GREEN;
				break;
			case YELLOW_DYE:
				color = new Color("#ffc72a");
				break;
			case LIGHT_BLUE_DYE:
				color = new Color("#aaaaff");
				break;
			case MAGENTA_DYE:
				color = new Color("#d42ad4");
				break;
			case ORANGE_DYE:
				color = new Color("#ff7f00");
				break;
			case BLUE_DYE:
				color = Color.BLUE;
				break;
			case BROWN_DYE:
				color = new Color("#7f5500");
				break;
			case BLACK_DYE:
				color = new Color("#1f1f1f");
				break;
			case WHITE_DYE:
				color = Color.WHITE;
				break;
			default:
				break;
			}
		}
		return color;
	}

}
