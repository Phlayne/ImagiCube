package fr.phlayne.imagicube;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.tr7zw.nbtapi.NBTContainer;
import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.commands.Commands;
import fr.phlayne.imagicube.crafts.ConcreteCrafts;
import fr.phlayne.imagicube.crafts.Crafts;
import fr.phlayne.imagicube.crafts.armor.ArmorRecipes;
import fr.phlayne.imagicube.crafts.armor.WeaponRecipes;
import fr.phlayne.imagicube.events.CraftingEvents;
import fr.phlayne.imagicube.events.ItemUpdatingEvents;
import fr.phlayne.imagicube.exception.CannotUpdateItemException;
import fr.phlayne.imagicube.util.NBTUtil;
import fr.phlayne.imagicube.util.ResourcePackUtil;

public class ImagiCube extends JavaPlugin implements Listener {

	protected ResourcePackUtil resourcePackUtil = new ResourcePackUtil();

	public void onEnable() {

		/* Events */

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this, this);
		pm.registerEvents(new CraftingEvents(), this);
		pm.registerEvents(new ItemUpdatingEvents(), this);
		pm.registerEvents(resourcePackUtil, this);

		/* Crafts */

		try {
			Crafts.loadRecipes(this);
		} catch (CannotUpdateItemException e) {
			e.printStackTrace();
		}
		ConcreteCrafts.init(this);
		ArmorRecipes.init(this);
		WeaponRecipes.init(this);

		/* Player Data */

		for (Player player : Bukkit.getOnlinePlayers()) {
			// This part cancels armor modifiers because the damage system is modified in
			// DamageStats.
			// Monsters should also have this modifier. Add it in MobSpawnEvent.
			player.getAttribute(Attribute.GENERIC_ARMOR)
					.addModifier(new AttributeModifier("ImagiCube Armor Modifier", -1, Operation.MULTIPLY_SCALAR_1));
			player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS)
					.addModifier(new AttributeModifier("ImagiCube Armor Modifier", -1, Operation.MULTIPLY_SCALAR_1));
			resourcePackUtil.resourcePackLoaded.put(player, true);
		}
	}

	public void onDisable() {
		getServer().clearRecipes();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equals("invsee")) {
			if (args.length < 1) {
				sender.sendMessage("§cSyntax: /" + label + " <player> [inv|end].");
				return true;
			}
			String arg0 = args.length > 1 ? args[1] : "inv";
			String argument1 = args[0];
			Player targetPlayer = Bukkit.getPlayer(argument1);
			Player player = (Player) sender;
			if (targetPlayer == null) {
				sender.sendMessage("§cThis player does not exist or is not connected.");
				return true;
			}
			if (arg0.equalsIgnoreCase("inv")) {
				Inventory targetInv = targetPlayer.getInventory();
				player.openInventory(targetInv);
				player.updateInventory();
			} else if (arg0.equalsIgnoreCase("end")) {
				Inventory targetInv = targetPlayer.getEnderChest();
				player.openInventory(targetInv);
				player.updateInventory();
			} else
				sender.sendMessage("§c\"" + arg0 + "\" is neither \"inv\" nor \"end\".");
		}
		if (command.getName().equalsIgnoreCase("itemdata")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (args.length == 0) {
					sender.sendMessage("§cSynatxe: /" + label + " {DataTags:value} OU /" + label
							+ " +DataTag:value OU /" + label + " ?");
					return true;
				}
				if (!player.getEquipment().getItemInMainHand().equals(null)) {
					String str = "";
					for (int j = 0; j < args.length; j++) {
						if (j == 0)
							str = str + args[j];
						else
							str = str + ' ' + args[j];
					}
					if (str.startsWith("?")) {
						sender.sendMessage(
								NBTItem.convertItemtoNBT(player.getEquipment().getItemInMainHand()).toString());
						return true;
					} else {
						NBTItem item = NBTUtil.clearNBT(new NBTItem(player.getEquipment().getItemInMainHand()));
						item.mergeCompound(new NBTContainer(str));
						player.getInventory().setItemInMainHand(item.getItem());
					}
				}
			}
		}
		if (command.getName().equalsIgnoreCase("item")) {
			if (sender instanceof Player) {
				// Get every plugin that extends ImagiCube and add their activated items here.
			}
		}
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		return Commands.onTabComplete(sender, command, label, args);
	}
}
