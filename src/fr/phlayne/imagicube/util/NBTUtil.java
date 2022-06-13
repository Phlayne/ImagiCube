package fr.phlayne.imagicube.util;

import org.bukkit.inventory.ItemStack;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTContainer;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTList;
import fr.phlayne.imagicube.util.SimpleJSON.Color;

public class NBTUtil {
	public static String MATERIAL = "imagicube.material";
	public static String WEIGHT = "weight";
	public static String ITEM_TYPE = "imagicube.itemtype";
	public static String ITEM_COOLDOWN = "imagicube.cooldown";
	public static String PHYSICAL_ARMOR = "armor.physical";
	public static String MAGICAL_ARMOR = "armor.magical";
	public static String DESCRIPTION = "description";
	public static String DURABILITY = "imagicube.durability";
	public static String UPDATEVERSION = "imagicube.update.version";
	public static String CANNOT_BE_UNCRAFTED = "imagicube.impossible.uncraft";
	public static String HAT_TYPE = "imagicube.hat_type";
	public static String FORCED_COLOR = "imagicube.forced_color";

	public static NBTItem clearNBT(NBTItem nbti) {
		return new NBTItem(new ItemStack(nbti.getItem().getType(), nbti.getItem().getAmount()));
	}

	public static ItemStack addLore(ItemStack item, String lore, boolean italic, boolean bold, boolean underlined,
			boolean strike, boolean translate, SimpleJSON.Color color) {
		NBTItem nbti = new NBTItem(item);
		addLore(nbti, lore, italic, bold, underlined, strike, translate, color);
		return nbti.getItem();
	}

	public static void addLore(NBTItem nbti, String lore, boolean italic, boolean bold, boolean underlined,
			boolean strike, boolean translate, SimpleJSON.Color color) {
		NBTCompound displayTag = nbti.getOrCreateCompound("display");
		addLore(displayTag, new SimpleJSON().add(lore, italic, bold, underlined, strike, color, translate).convert());
	}

	public static void addLore(NBTCompound displayTag, String formatedLore) {
		NBTList<String> lore = displayTag.getStringList("Lore");
		lore.add(formatedLore);
	}

	public static ItemStack changeLore(ItemStack item, String formatedLore, int line) {
		NBTItem nbti = new NBTItem(item);
		NBTCompound displayTag = nbti.getOrCreateCompound("display");
		changeLore(displayTag, formatedLore, line);
		return nbti.getItem();
	}

	public static void changeLore(NBTCompound displayTag, String formatedLore, int line) {
		NBTList<String> lore = displayTag.getStringList("Lore");
		if (line >= lore.size()) {
			while (line >= lore.size())
				lore.add(" ");
		}
		lore.set(line, formatedLore);
	}

	public static String getLoreLine(ItemStack item, int line) {
		NBTItem nbti = new NBTItem(item);
		return getLoreLine(nbti.getOrCreateCompound("display"), line);
	}

	public static String getLoreLine(NBTCompound displayTag, int line) {
		NBTList<String> lore = displayTag.getStringList("Lore");
		return lore.size() >= line ? null : lore.get(line);
	}

	public static int getLoreLineContaining(ItemStack item, String text) {
		NBTItem nbti = new NBTItem(item);
		return getLoreLineContaining(nbti.getOrCreateCompound("display"), text);
	}

	public static int getLoreLineContaining(NBTCompound displayTag, String text) {
		NBTList<String> lore = displayTag.getStringList("Lore");
		for (int i = 0; i < lore.size(); i++) {
			if (lore.get(i).contains(text))
				return i;
		}
		return -1;
	}

	public static ItemStack removeUselessLines(ItemStack item) {
		NBTItem nbti = new NBTItem(item);
		removeUselessLines(nbti);
		return nbti.getItem();
	}

	public static void removeUselessLines(NBTItem nbti) {
		NBTCompound display = nbti.getOrCreateCompound("display");
		if (display.hasKey("Lore")) {
			NBTList<String> lore = display.getStringList("Lore");
			boolean stop = false;
			for (int i = lore.size() - 1; i > 0; i--) {
				NBTContainer loreLine = new NBTContainer(getFirstBrackets(lore.get(i)));
				if (loreLine.getString("text").equals("") && !loreLine.hasKey("translate")) {
					if (!stop)
						lore.remove(i);
				} else
					stop = true;
			}
		}
	}

	public static String getFirstBrackets(String string) {
		int firstBracket = -1;
		int bracketCount = 0;
		int oldBracketCount = 0;
		for (int i = 0; i < string.length(); i++) {
			if (string.toCharArray()[i] == '{') {
				bracketCount++;
				if (firstBracket == -1)
					firstBracket = i;
			}
			if (string.toCharArray()[i] == '}')
				bracketCount--;
			if (bracketCount == 0 && oldBracketCount == 1)
				return string.substring(firstBracket == -1 ? 0 : firstBracket, i + 1);
			oldBracketCount = bracketCount;
		}
		return string;
	}

	public static SimpleJSON readName(NBTItem nbti, Color color) {
		SimpleJSON simpleJsonName = new SimpleJSON();
		if (nbti.hasKey("display")) {
			String s = nbti.getCompound("display").getString("Name");
			JsonElement name = JsonParser.parseString(s);
			if (name.isJsonArray()) {
				for (JsonElement jsonObj : name.getAsJsonArray()) {
					SimpleJSON element = read(jsonObj.getAsJsonObject(), color);
					if (element != null)
						simpleJsonName.add(element);
				}
			} else {
				SimpleJSON element = read(name.getAsJsonObject(), color);
				if (element != null)
					simpleJsonName.add(element);
			}
		}
		return simpleJsonName;
	}

	public static SimpleJSON read(JsonObject nameElement, Color color) {
		return readAndReplaceTranslatedText(nameElement, color, "", "");
	}

	public static SimpleJSON readAndReplaceTranslatedText(JsonObject nameElement, Color color, String toBeReplaced,
			String replacement) {
		SimpleJSON simpleJSON = new SimpleJSON();
		boolean add = nameElement.has("text") || nameElement.has("translate");
		if (add) {
			boolean translated = nameElement.has("translate");
			simpleJSON.add(
					translated ? nameElement.get("translate").getAsString().replace(toBeReplaced, replacement)
							: nameElement.get("text").getAsString(),
					!translated && nameElement.get("italic").getAsBoolean(), nameElement.get("bold").getAsBoolean(),
					nameElement.get("underlined").getAsBoolean(), nameElement.get("strikethrough").getAsBoolean(),
					(color == null ? Color.get(nameElement.get("color").getAsString()) : color), translated);
			if (nameElement.has("extra")) {
				for (JsonElement ee : nameElement.get("extra").getAsJsonArray()) {
					JsonObject extraElement = ee.getAsJsonObject();
					simpleJSON.add(
							extraElement.has("translate") ? extraElement.get("translate").getAsString()
									: extraElement.get("text").getAsString(),
							!extraElement.has("translate") && extraElement.get("italic").getAsBoolean(),
							extraElement.get("bold").getAsBoolean(), extraElement.get("underlined").getAsBoolean(),
							extraElement.get("strikethrough").getAsBoolean(),
							(color == null ? Color.get(nameElement.get("color").getAsString()) : color),
							extraElement.has("translate"));
				}
			}
			return simpleJSON;
		}
		return null;
	}
}