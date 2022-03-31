package fr.phlayne.imagicube.util;

import java.util.ArrayList;
import java.util.List;

public class SimpleJSON {

	public List<String> contents = new ArrayList<String>();

	public SimpleJSON add(String text, boolean italic, boolean bold, boolean underlined, boolean strike, Color color,
			boolean translate) {
		contents.add("{\"" + (translate ? "translate" : "text") + "\":\"" + text + "\",\"italic\":\"" + italic
				+ "\",\"bold\":\"" + bold + "\",\"underlined\":\"" + underlined + "\",\"strikethrough\":\"" + strike
				+ "\",\"color\":\"" + color.getColor() + "\"}");
		return this;
	}

	public SimpleJSON add(SimpleJSON sj) {
		this.contents.addAll(sj.contents);
		return this;
	}

	public String convert() {
		String string = "[";
		for (int i = 0; i < contents.size(); i++) {
			if (i != 0)
				string = string.concat(",");
			string = string.concat(contents.get(i));
		}
		string = string.concat("]");
		return string;
	}

	public static class Color {
		public static Color BLACK = new Color(0, 0, 0);
		public static Color DARK_BLUE = new Color(0, 0, 170);
		public static Color DARK_GREEN = new Color(0, 170, 0);
		public static Color DARK_AQUA = new Color(0, 170, 170);
		public static Color DARK_RED = new Color(170, 0, 0);
		public static Color DARK_PURPLE = new Color(170, 0, 170);
		public static Color GOLD = new Color(255, 170, 0);
		public static Color GRAY = new Color(170, 170, 170);
		public static Color DARK_GRAY = new Color(85, 85, 85);
		public static Color BLUE = new Color(85, 85, 255);
		public static Color GREEN = new Color(85, 255, 85);
		public static Color AQUA = new Color(85, 255, 255);
		public static Color RED = new Color(255, 85, 85);
		public static Color LIGHT_PURPLE = new Color(255, 85, 255);
		public static Color YELLOW = new Color(255, 255, 85);
		public static Color WHITE = new Color(255, 255, 255);

		private int red, green, blue;

		public Color(int value) {
			this.red = (value / 65536) % 256;
			this.green = (value / 256) % 256;
			this.blue = value % 256;
		}

		public Color(int red, int green, int blue) {
			this.red = red % 256;
			this.green = green % 256;
			this.blue = blue % 256;
		}

		public Color multiply(float value) {
			return new Color((int) (this.red * value), (int) (this.green * value), (int) (this.blue * value));
		}

		public Color(String hexColor) {
			this(Integer.parseInt(hexColor.replace("#", ""), 16));
		}

		public int[] getColors() {
			return new int[] { this.red, this.green, this.blue };
		}

		public String getColor() {
			return "#" + String.format("%1$06x", this.red * 65536 + this.green * 256 + this.blue);
		}

		public static Color get(String string) {
			switch (string) {
			case "black":
				return Color.BLACK;
			case "dark_blue":
				return Color.DARK_BLUE;
			case "dark_green":
				return Color.DARK_GREEN;
			case "dark_aqua":
				return Color.DARK_AQUA;
			case "dark_red":
				return Color.DARK_RED;
			case "black_purple":
				return Color.DARK_PURPLE;
			case "gold":
				return Color.GOLD;
			case "gray":
				return Color.GRAY;
			case "dark_gray":
				return Color.DARK_GRAY;
			case "blue":
				return Color.BLUE;
			case "green":
				return Color.GREEN;
			case "aqua":
				return Color.AQUA;
			case "red":
				return Color.RED;
			case "light_purple":
				return Color.LIGHT_PURPLE;
			case "yellow":
				return Color.YELLOW;
			case "white":
				return Color.WHITE;
			default:
				return new Color(string);
			}
		}
	}
}
