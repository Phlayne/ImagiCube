package fr.phlayne.imagicube.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Commands {

	public static List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		return new ArrayList<String>();
	}

	public static List<String> getMatchingTexts(List<String> list, String[] args) {
		List<String> result = new ArrayList<String>();
		for (String s : list) {
			if (s.toLowerCase().startsWith(args[args.length - 1].toLowerCase()))
				result.add(s);
		}
		return result;
	}
}
