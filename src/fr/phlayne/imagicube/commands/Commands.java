package fr.phlayne.imagicube.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;

public class Commands {

	public static List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("enchant")) {
			if (args.length == 1) {
				return getMatchingTexts(Arrays.asList("set", "remove"), args);
			} else {
				if (args.length == 2)
					return getMatchingTexts(Arrays.asList(Enchantment.values()).stream().map(e -> e.getKey().toString())
							.collect(Collectors.toList()), args);
				if (args[0].equalsIgnoreCase("set")) {
					if (args.length == 3)
						return getMatchingTexts(Arrays.asList("max"), args);
					else if (args.length == 4)
						return getMatchingTexts(Arrays.asList("force"), args);
				}
			}
		}
		return new ArrayList<String>();

	}

	public static List<String> getMatchingTexts(List<String> list, String[] args) {
		List<String> result = new ArrayList<String>();
		for (String s : list) {
			if (s.toLowerCase().contains(args[args.length - 1].toLowerCase()))
				result.add(s);
		}
		return result;
	}
}
