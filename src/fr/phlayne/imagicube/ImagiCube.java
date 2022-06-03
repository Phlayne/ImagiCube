package fr.phlayne.imagicube;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.tr7zw.nbtapi.NBTContainer;
import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.commands.Commands;
import fr.phlayne.imagicube.craftbehaviour.DiamondToNetheriteScript;
import fr.phlayne.imagicube.craftbehaviour.FuseEnchantmentsScript;
import fr.phlayne.imagicube.craftbehaviour.NameColorScript;
import fr.phlayne.imagicube.craftbehaviour.RenamingScript;
import fr.phlayne.imagicube.craftbehaviour.RepairWithMaterialScript;
import fr.phlayne.imagicube.craftbehaviour.RepairWithSimilarItemScript;
import fr.phlayne.imagicube.crafts.ConcreteCrafts;
import fr.phlayne.imagicube.crafts.Crafts;
import fr.phlayne.imagicube.crafts.armor.ArmorRecipes;
import fr.phlayne.imagicube.crafts.armor.WeaponRecipes;
import fr.phlayne.imagicube.data.AddonList;
import fr.phlayne.imagicube.data.Config;
import fr.phlayne.imagicube.display.ArmorScript;
import fr.phlayne.imagicube.display.LeftHandDurabilityScript;
import fr.phlayne.imagicube.display.RightHandDurabilityScript;
import fr.phlayne.imagicube.event.ImagiCubeLoadingEvent;
import fr.phlayne.imagicube.events.AFKEvents;
import fr.phlayne.imagicube.events.BlockEvents;
import fr.phlayne.imagicube.events.ChairEvents;
import fr.phlayne.imagicube.events.ChatEvents;
import fr.phlayne.imagicube.events.ConfigEvents;
import fr.phlayne.imagicube.events.CraftBehaviorEvents;
import fr.phlayne.imagicube.events.CraftingEvents;
import fr.phlayne.imagicube.events.DurabilityEvents;
import fr.phlayne.imagicube.events.FoodEvents;
import fr.phlayne.imagicube.events.InteractEvents;
import fr.phlayne.imagicube.events.ItemUpdatingEvents;
import fr.phlayne.imagicube.events.ItemUseEvents;
import fr.phlayne.imagicube.events.SpawnEvents;
import fr.phlayne.imagicube.events.VillagerTradeUpdatingEvents;
import fr.phlayne.imagicube.exception.CannotUpdateItemException;
import fr.phlayne.imagicube.item.ArmorProperties;
import fr.phlayne.imagicube.item.ArmorProperty;
import fr.phlayne.imagicube.item.FoodProperty;
import fr.phlayne.imagicube.item.MineralProperties;
import fr.phlayne.imagicube.item.Tools;
import fr.phlayne.imagicube.item.WeaponProperties;
import fr.phlayne.imagicube.item.WeaponProperty;
import fr.phlayne.imagicube.schedulers.DisplayScriptScheduler;
import fr.phlayne.imagicube.schedulers.EntityScheduler;
import fr.phlayne.imagicube.schedulers.GeneralScheduler;
import fr.phlayne.imagicube.schedulers.ListNameScheduler;
import fr.phlayne.imagicube.schedulers.PlayerScheduler;
import fr.phlayne.imagicube.util.NBTUtil;
import fr.phlayne.imagicube.util.ResourcePackUtil;

public class ImagiCube extends JavaPlugin implements Listener {

	protected ResourcePackUtil resourcePackUtil;

	protected AddonList addonList;

	public void onEnable() {

		/* Events */

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this, this);
		pm.registerEvents(new ConfigEvents(), this);
		pm.registerEvents(new CraftingEvents(), this);
		pm.registerEvents(new ItemUpdatingEvents(), this);
		pm.registerEvents(new DurabilityEvents(), this);
		pm.registerEvents(new SpawnEvents(), this);
		pm.registerEvents(new CraftBehaviorEvents(), this);
		pm.registerEvents(new ItemUseEvents(), this);
		pm.registerEvents(new BlockEvents(), this);
		pm.registerEvents(new InteractEvents(), this);
		pm.registerEvents(new ChatEvents(), this);
		pm.registerEvents(new FoodEvents(), this);
		if (Config.getConfig().contains("sitting_on_blocks") && Config.getConfig().getBoolean("sitting_on_blocks"))
			pm.registerEvents(new ChairEvents(), this);
		pm.registerEvents(new VillagerTradeUpdatingEvents(), this);
		AFKEvents afkEvents = new AFKEvents();
		afkEvents.init();
		pm.registerEvents(afkEvents, this);
		resourcePackUtil = new ResourcePackUtil();
		resourcePackUtil.init();
		pm.registerEvents(resourcePackUtil, this);

		/* Config checks */

		Config.copyFilesIfAbsent();
		Config.checkConfigs();

		/* Crafts */

		try {
			Crafts.loadRecipes();
		} catch (CannotUpdateItemException e) {
			e.printStackTrace();
		}
		ConcreteCrafts.init();
		ArmorRecipes.init();
		WeaponRecipes.init();

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

		/* Schedulers */

		GeneralScheduler.init();

		/* Subplugins */

		this.addonList = new AddonList();
		this.addonList.addWeapons((WeaponProperty[]) WeaponProperties.getWeaponProperties().toArray());
		this.addonList.addArmors((ArmorProperty[]) ArmorProperties.getArmorProperties().toArray());
		this.addonList.addMinerals(MineralProperties.values());
		this.addonList.addUniqueItems(Crafts.INVISIBLE_ITEM_FRAME.getResult(), Crafts.CACTUS_LEATHER.getResult());
		this.addonList.addDisplayScripts(new ArmorScript(), new LeftHandDurabilityScript(),
				new RightHandDurabilityScript());
		this.addonList.addSchedulerScripts(new DisplayScriptScheduler(), new PlayerScheduler(), new EntityScheduler(),
				new ListNameScheduler());
		this.addonList.addFuseScripts(new RepairWithSimilarItemScript(), new RepairWithMaterialScript(),
						new FuseEnchantmentsScript(), new NameColorScript(), new RenamingScript());
		this.addonList.addSmithScripts(new DiamondToNetheriteScript());
		this.addonList.addTools(Tools.values());
		this.addonList.addItemsToGroup("sword", "sword");
		this.addonList.addItemsToGroup("tool", "axe", "hoe", "pickaxe", "shovel");
		this.addonList.addItemsToGroup("axe", "axe");
		this.addonList.addItemsToGroup("fishing_rod", "fishing_rod");
		this.addonList.addItemsToGroup("bow", "bow");
		this.addonList.addItemsToGroup("hoe", "hoe");
		this.addonList.addItemsToGroup("crossbow", "crossbow");

		ImagiCubeLoadingEvent imagiCubeLoadingEvent = new ImagiCubeLoadingEvent(this.addonList);

		getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			public void run() {
				Bukkit.getServer().getPluginManager().callEvent(imagiCubeLoadingEvent);
				imagiCubeLoadingEvent.getAddonList().sortDisplayScripts();
			}
		});
	}

	public void onDisable() {
		for (Player player : Bukkit.getOnlinePlayers())
			resetPlayerValues(player);
		getServer().clearRecipes();
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		resetPlayerValues(event.getPlayer());
	}

	public void resetPlayerValues(Player player) {
		// If the plugin is removed, no player will have slower or higher speed, and no
		// player will be stuck invulnerable.
		player.setWalkSpeed(0.2F);
		player.setInvulnerable(false);
	}

	public ResourcePackUtil getResourcePackUtil() {
		return this.resourcePackUtil;
	}

	public AddonList getAddonList() {
		return this.addonList;
	}

	public static ImagiCube getInstance() {
		return (ImagiCube) Bukkit.getPluginManager().getPlugin(Reference.PLUGIN_NAME);
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
				if (player.getEquipment().getItemInMainHand() != null
						&& !player.getEquipment().getItemInMainHand().getType().equals(Material.AIR)) {
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
				try {
					List<ItemStack> itemList = new ArrayList<ItemStack>();
					itemList.addAll(this.addonList.getUniqueItems());
					for (WeaponProperty weaponProperty : this.addonList.getWeapons())
						itemList.add(WeaponRecipes.setWeaponValues(weaponProperty));
					for (ArmorProperty armorProperty : this.addonList.getArmors())
						itemList.add(ArmorRecipes.setArmorValues(new ItemStack(armorProperty.getBukkitMaterial()),
								armorProperty));
					for (FoodProperty foodProperty : this.addonList.getFoods())
						itemList.add(foodProperty.getItemStack());
					int size = itemList.size();
					int page = 1;
					if (args.length > 0)
						page = Integer.parseInt(args[0]);
					int maxPage = (int) Math.ceil(size / 54f);
					if (page < 1)
						page = 1;
					else if (page > maxPage)
						page = maxPage;
					if (maxPage > 1)
						sender.sendMessage("Showing items, page " + page + "/" + maxPage);
					while (page > 1) {
						for (int i = 0; i < 54; i++) {
							itemList.remove(0);
						}
						page--;
					}
					size = itemList.size();
					Inventory inv = Bukkit.createInventory(null, Math.min((int) Math.ceil(size / 9f) * 9, 54));
					ItemStack is;
					for (int i = 0; i < Math.min(itemList.size(), 54); i++) {
						is = itemList.get(i).clone();
						is.setAmount(1);
						inv.setItem(i, is);
					}
					((Player) sender).openInventory(inv);
				} catch (NumberFormatException e) {
					sender.sendMessage("Invalid page number: \"" + args[0] + "\" is not a number");
				}
			}
		}
		return true;

	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		return Commands.onTabComplete(sender, command, label, args);
	}
}
