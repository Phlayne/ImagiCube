package fr.phlayne.imagicube.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;

public class LeatherColor {

	public static final int leatherColor = Bukkit.getServer().getItemFactory().getDefaultLeatherColor().asRGB();
	public static final int cactusLeatherColor = 0x6fa11a;

	public static Color getColor(int leather, int cactusLeather) {
		List<Integer> colorList = new ArrayList<Integer>();
		for (int i = 0; i < leather; i++)
			colorList.add(leatherColor);
		for (int i = 0; i < cactusLeather; i++)
			colorList.add(cactusLeatherColor);
		return fuseColors(colorList);
	}

	public static Color fuseColors(List<Integer> colors) {
		float totalRed = 0;
		float totalGreen = 0;
		float totalBlue = 0;
		float totalCount = 0;
		for (int i = 0; i < colors.size(); i++)
			if (i != -1) {
				totalRed += colors.get(i) >> 16 & 255;
				totalGreen += colors.get(i) >> 8 & 255;
				totalBlue += colors.get(i) & 255;
				totalCount++;
			}
		float averageRed = totalRed / totalCount;
		float averageGreen = totalGreen / totalCount;
		float averageBlue = totalBlue / totalCount;
		return Color.fromRGB((int) averageRed, (int) averageGreen, (int) averageBlue);
	}
}
