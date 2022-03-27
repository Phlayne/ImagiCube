package fr.phlayne.imagicube.util;

import java.rmi.AlreadyBoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;

public class CustomMetadata {

	public static List<CustomMetadata> customMetadataList = new ArrayList<CustomMetadata>();

	public String name;
	public boolean increase;
	public int minimum;
	public int maximum;
	public HashMap<Player, Integer> value = new HashMap<Player, Integer>();

	public CustomMetadata(String name, boolean increase, int minimum, int maximum) {
		this.name = name;
		this.increase = increase;
		this.minimum = minimum;
		this.maximum = maximum;
	}

	public void setValue(Player player, int value) {
		this.value.put(player, value);
	}

	public void add(Player player, int value) {
		this.value.put(player, Math.max(minimum, Math.min(maximum, this.getValue(player) + value)));
	}

	public int getValue(Player player) {
		return this.value.containsKey(player) ? this.value.get(player) : 0;
	}

	public static CustomMetadata get(String name) {
		for (CustomMetadata cm : customMetadataList) {
			if (cm.name.equals(name))
				return cm;
		}
		return null;
	}

	public void register() throws AlreadyBoundException {
		if (!customMetadataList.contains(this)) {
			customMetadataList.add(this);
		} else
			throw new AlreadyBoundException(this.name + "already registered or with the same name.");
	}
}