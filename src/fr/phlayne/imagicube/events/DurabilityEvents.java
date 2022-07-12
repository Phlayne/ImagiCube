package fr.phlayne.imagicube.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Dispenser;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockShearEntityEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRiptideEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.ImagiCube;
import fr.phlayne.imagicube.Reference;
import fr.phlayne.imagicube.data.Config;
import fr.phlayne.imagicube.item.ArmorProperty;
import fr.phlayne.imagicube.item.Durability;
import fr.phlayne.imagicube.item.Tool;
import fr.phlayne.imagicube.item.Tools;
import fr.phlayne.imagicube.item.WeaponProperty;
import fr.phlayne.imagicube.util.NBTUtil;

public class DurabilityEvents implements Listener {

	public Random random = new Random();

	@EventHandler
	public void onAttack(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			Player player = (Player) event.getDamager();
			if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
				ItemStack item = player.getInventory().getItemInMainHand();
				if (item != null && !item.getType().equals(Material.AIR)) {
					NBTItem nbti = new NBTItem(item);
					if (WeaponProperty.getWeaponProperty(nbti) != null) {
						double damage = event.getDamage();
						int durability = 0;
						while (damage > 10D) {
							durability++;
							damage -= 10D;
						}
						if (random.nextDouble() < damage / 10D) {
							durability++;
						}
						int d = 0;
						boolean broke = false;
						while (d < durability && !broke) {
							broke = Durability.applyDurability(nbti);
							d++;
						}
						if (broke) {
							Durability.playBreakItemAnimation(player, nbti.getItem());
							player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
						} else
							player.getInventory().setItemInMainHand(nbti.getItem());
					}
				}
			}
		}
		if (event.getEntity() instanceof Player) {
			int thornsDurabilityModifier = Config.getConfig().getInt("thorns_durability_modifier");
			if (thornsDurabilityModifier > 0) {
				Player player = (Player) event.getEntity();
				List<Integer> thornsSlots = new ArrayList<Integer>();
				EquipmentSlot[] slots = { EquipmentSlot.FEET, EquipmentSlot.LEGS, EquipmentSlot.CHEST,
						EquipmentSlot.HEAD };
				for (int i = 0; i < 4; i++) {
					ItemStack item = player.getEquipment().getItem(slots[i]);
					if (item != null && !item.getType().equals(Material.AIR))
						if (item.getEnchantmentLevel(Enchantment.THORNS) > 0)
							thornsSlots.add(i);
				}
				if (thornsSlots.size() > 0) {
					int damagedSlot = thornsSlots.get(random.nextInt(thornsSlots.size()));
					NBTItem nbti = new NBTItem(player.getEquipment().getItem(slots[damagedSlot]));
					boolean broke = false;
					for (int i = 0; i < thornsDurabilityModifier; i++) {
						broke |= Durability.applyDurability(nbti);
						if (broke)
							break;
					}
					if (broke) {
						Durability.playBreakItemAnimation(player, nbti.getItem());
						player.getEquipment().setItem(slots[damagedSlot], new ItemStack(Material.AIR));
					} else
						player.getEquipment().setItem(slots[damagedSlot], nbti.getItem());
				}
			}
		}
	}

	@EventHandler
	public void onAttack(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			if (!event.getCause().equals(DamageCause.CRAMMING) && !event.getCause().equals(DamageCause.DRAGON_BREATH)
					&& !event.getCause().equals(DamageCause.DROWNING) && !event.getCause().equals(DamageCause.FIRE_TICK)
					&& !event.getCause().equals(DamageCause.POISON) && !event.getCause().equals(DamageCause.STARVATION)
					&& !event.getCause().equals(DamageCause.SUFFOCATION)
					&& !event.getCause().equals(DamageCause.SUICIDE) && !event.getCause().equals(DamageCause.VOID)
					&& !event.getCause().equals(DamageCause.WITHER) && !event.getCause().equals(DamageCause.FALL)
					&& !event.getCause().equals(DamageCause.FLY_INTO_WALL)) {
				Player player = (Player) event.getEntity();
				if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
					ItemStack[] armor = new ItemStack[4];
					armor[0] = player.getEquipment().getBoots();
					armor[1] = player.getEquipment().getLeggings();
					armor[2] = player.getEquipment().getChestplate();
					armor[3] = player.getEquipment().getHelmet();
					double damage = event.getDamage();
					int durability = 0;
					while (damage > 10D) {
						durability++;
						damage -= 10D;
					}
					if (random.nextDouble() < damage / 10D) {
						durability++;
					}
					for (int d = 0; d < durability; d++)
						for (int i = 0; i < 4; i++)
							if (armor[i] != null) {
								NBTItem nbti = new NBTItem(armor[i]);
								if (ArmorProperty.getArmorProperty(nbti) != null) {
									boolean broke = Durability.applyDurability(nbti);
									if (broke) {
										Durability.playBreakItemAnimation(player, nbti.getItem());
										armor[i] = new ItemStack(Material.AIR);
									} else
										armor[i] = nbti.getItem();
								}
							}
					player.getEquipment().setBoots(armor[0]);
					player.getEquipment().setLeggings(armor[1]);
					player.getEquipment().setChestplate(armor[2]);
					player.getEquipment().setHelmet(armor[3]);
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onShearing(PlayerShearEntityEvent event) {
		Player player = event.getPlayer();
		if (!event.isCancelled() && (player.getGameMode().equals(GameMode.SURVIVAL)
				|| player.getGameMode().equals(GameMode.ADVENTURE))) {
			NBTItem nbti = new NBTItem(event.getItem());
			String mainHandItemType = nbti.hasKey(NBTUtil.ITEM_TYPE) ? nbti.getString(NBTUtil.ITEM_TYPE) : "";
			String offHandItemType = nbti.hasKey(NBTUtil.ITEM_TYPE) ? nbti.getString(NBTUtil.ITEM_TYPE) : "";
			boolean isMainHand = mainHandItemType.equals("shears");
			boolean isOffHandCheck = offHandItemType.equals("shears");
			if (isMainHand || isOffHandCheck) {
				if (isMainHand) {
					nbti = new NBTItem(player.getEquipment().getItemInMainHand());
					boolean broke = Durability.applyDurability(nbti);
					if (broke) {
						Durability.playBreakItemAnimation(player, nbti.getItem());
						player.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
					} else
						player.getEquipment().setItemInMainHand(nbti.getItem());
				} else {
					nbti = new NBTItem(player.getEquipment().getItemInOffHand());
					boolean broke = Durability.applyDurability(nbti);
					if (broke) {
						Durability.playBreakItemAnimation(player, nbti.getItem());
						player.getEquipment().setItemInOffHand(new ItemStack(Material.AIR));
					} else
						player.getEquipment().setItemInOffHand(nbti.getItem());
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onShearing(BlockShearEntityEvent event) {
		if (!event.isCancelled() && event.getBlock().getState() instanceof Dispenser) {
			Dispenser dispenser = (Dispenser) event.getBlock().getState();
			List<Integer> shearsIndex = new ArrayList<Integer>();
			for (int i = 0; i < dispenser.getInventory().getSize(); i++)
				if (event.getTool().equals(dispenser.getInventory().getItem(i)))
					shearsIndex.add(i);
			if (shearsIndex.size() > 0) {
				NBTItem nbti = new NBTItem(event.getTool());
				boolean broke = Durability.applyDurability(nbti);
				if (broke)
					Bukkit.getScheduler().scheduleSyncDelayedTask(ImagiCube.getInstance(),
							() -> dispenser.getInventory().setItem(shearsIndex.get(random.nextInt(shearsIndex.size())),
									new ItemStack(Material.AIR)));
				else
					Bukkit.getScheduler().scheduleSyncDelayedTask(ImagiCube.getInstance(),
							() -> dispenser.getInventory().setItem(shearsIndex.get(random.nextInt(shearsIndex.size())),
									nbti.getItem()));
			}
		}
	}

	// TODO Check if we can simplify this without using schedulers
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onShoot(EntityShootBowEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if (new NBTItem(event.getBow()).hasKey(NBTUtil.DURABILITY)
					&& (player.getGameMode().equals(GameMode.SURVIVAL)
							|| player.getGameMode().equals(GameMode.ADVENTURE))) {
				ItemStack bow = event.getBow();
				final boolean isMainHand = player.getEquipment().getItemInMainHand().equals(bow);
				boolean isOffHandCheck = player.getEquipment().getItemInOffHand().equals(bow);
				if (bow.getType().equals(Material.BOW) && (isMainHand || isOffHandCheck)) {
					if (isMainHand) {
						NBTItem nbti = new NBTItem(player.getEquipment().getItemInMainHand());
						boolean broke = Durability.applyDurability(nbti);
						if (broke) {
							Durability.playBreakItemAnimation(player, nbti.getItem());
							player.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
						} else
							player.getEquipment().setItemInMainHand(nbti.getItem());
					} else {
						NBTItem nbti = new NBTItem(player.getEquipment().getItemInOffHand());
						boolean broke = Durability.applyDurability(nbti);
						if (broke) {
							Durability.playBreakItemAnimation(player, nbti.getItem());
							player.getEquipment().setItemInOffHand(new ItemStack(Material.AIR));
						} else
							player.getEquipment().setItemInOffHand(nbti.getItem());
					}
				} else if (bow.getType().equals(Material.CROSSBOW))
					Bukkit.getScheduler().scheduleSyncDelayedTask(
							Bukkit.getPluginManager().getPlugin(Reference.PLUGIN_NAME), new Runnable() {
								@Override
								public void run() {
									if (isMainHand) {
										NBTItem nbti = new NBTItem(player.getEquipment().getItemInMainHand());
										boolean broke = Durability.applyDurability(nbti);
										if (broke) {
											Durability.playBreakItemAnimation(player, nbti.getItem());
											player.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
										} else
											player.getEquipment().setItemInMainHand(nbti.getItem());
									} else {
										NBTItem nbti = new NBTItem(player.getEquipment().getItemInOffHand());
										boolean broke = Durability.applyDurability(nbti);
										if (broke) {
											Durability.playBreakItemAnimation(player, nbti.getItem());
											player.getEquipment().setItemInOffHand(new ItemStack(Material.AIR));
										} else
											player.getEquipment().setItemInOffHand(nbti.getItem());
									}
								}
							}, 0L);
			}
		}
	}

	@EventHandler
	public void newItemMend(PlayerExpChangeEvent event) {
		Player player = event.getPlayer();
		ItemStack item;
		int index = -1;
		List<ItemStack> itemList = new ArrayList<ItemStack>();
		itemList.add(player.getInventory().getItemInMainHand());
		itemList.add(player.getInventory().getItemInOffHand());
		itemList.add(player.getInventory().getBoots());
		itemList.add(player.getInventory().getLeggings());
		itemList.add(player.getInventory().getChestplate());
		itemList.add(player.getInventory().getHelmet());
		if (Config.getConfig().getBoolean("lossless_mending_system")) {
			List<Byte> numberList = new ArrayList<Byte>();
			for (int i = 0; i < 6; i++) {
				if (itemList.get(i) != null && itemList.get(i).getType() != Material.AIR) {
					NBTItem nbti = new NBTItem(itemList.get(i));
					if (itemList.get(i).getEnchantmentLevel(Enchantment.MENDING) > 0 && nbti.hasKey(NBTUtil.DURABILITY)
							&& nbti.getInteger(NBTUtil.DURABILITY) > 0) {
						numberList.add((byte) i);
					}
				}
			}
			if (numberList.size() > 0)
				index = numberList.get(random.nextInt(numberList.size()));
		} else {
			index = random.nextInt(6);
		}
		if (index != -1) {
			item = itemList.get(index);
			if (item != null && !item.getType().equals(Material.AIR)) {
				NBTItem nbti = new NBTItem(item);
				int durability = nbti.getInteger(NBTUtil.DURABILITY);
				int newDurability = 0;
				if (event.getAmount() * 2 > durability) {
					newDurability = 0;
					event.setAmount(event.getAmount() * 2 - durability);
				} else {
					newDurability = durability - event.getAmount() * 2;
					event.setAmount(0);
				}
				Durability.setDurability(nbti, newDurability);
				switch (index) {
				case 0:
					player.getInventory().setItemInMainHand(nbti.getItem());
					break;
				case 1:
					player.getInventory().setItemInOffHand(nbti.getItem());
					break;
				case 2:
					player.getInventory().setBoots(nbti.getItem());
					break;
				case 3:
					player.getInventory().setLeggings(nbti.getItem());
					break;
				case 4:
					player.getInventory().setChestplate(nbti.getItem());
					break;
				case 5:
					player.getInventory().setHelmet(nbti.getItem());
					break;
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockBreak(BlockBreakEvent event) {
		if (!event.isCancelled()) {
			Player player = event.getPlayer();
			if ((player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE))) {
				ItemStack item = player.getEquipment().getItemInMainHand();
				if (item != null && !item.getType().equals(Material.AIR)) {
					NBTItem nbti = new NBTItem(item);
					String itemType = nbti.hasKey(NBTUtil.ITEM_TYPE) ? nbti.getString(NBTUtil.ITEM_TYPE) : "";
					Tool tool = Tool.getTool(itemType);
					if (tool != null)
						if (tool.canDamage(event.getBlock().getType())) {
							boolean broke = Durability.applyDurability(nbti);
							if (broke) {
								Durability.playBreakItemAnimation(player, nbti.getItem());
								player.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
							} else
								player.getEquipment().setItemInMainHand(nbti.getItem());
						}
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlow(BlockPlaceEvent event) {
		if (!event.isCancelled()) {
			Player player = (Player) event.getPlayer();
			if ((player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE))) {
				List<String> itemType = new ArrayList<String>();
				switch (event.getBlock().getType()) {
				case FARMLAND:
					itemType.add(Tools.HOE.getName());
					break;
				case DIRT_PATH:
					itemType.add(Tools.SHOVEL.getName());
					break;
				case COPPER_BLOCK:
				case CUT_COPPER:
				case CUT_COPPER_SLAB:
				case CUT_COPPER_STAIRS:
				case EXPOSED_COPPER:
				case EXPOSED_CUT_COPPER:
				case EXPOSED_CUT_COPPER_SLAB:
				case EXPOSED_CUT_COPPER_STAIRS:
				case WEATHERED_COPPER:
				case WEATHERED_CUT_COPPER:
				case WEATHERED_CUT_COPPER_SLAB:
				case WEATHERED_CUT_COPPER_STAIRS:
				case OXIDIZED_COPPER:
				case OXIDIZED_CUT_COPPER:
				case OXIDIZED_CUT_COPPER_SLAB:
				case OXIDIZED_CUT_COPPER_STAIRS:
					itemType.add(Tools.AXE.getName());
					break;
				default:
					break;
				}
				if (itemType.size() > 0) {
					ItemStack handItem = event.getItemInHand();
					NBTItem nbti = new NBTItem(handItem);
					if (nbti.hasKey(NBTUtil.ITEM_TYPE) && itemType.contains(nbti.getString(NBTUtil.ITEM_TYPE))) {
						if (handItem.equals(event.getPlayer().getEquipment().getItemInMainHand())) {
							boolean broke = Durability.applyDurability(nbti);
							if (broke) {
								Durability.playBreakItemAnimation(player, nbti.getItem());
								player.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
							} else
								player.getEquipment().setItemInMainHand(nbti.getItem());
						} else if (handItem.equals(event.getPlayer().getEquipment().getItemInOffHand())) {
							boolean broke = Durability.applyDurability(nbti);
							if (broke) {
								Durability.playBreakItemAnimation(player, nbti.getItem());
								player.getEquipment().setItemInOffHand(new ItemStack(Material.AIR));
							} else
								player.getEquipment().setItemInOffHand(nbti.getItem());
						}
					}
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onTridentThrow(ProjectileLaunchEvent event) {
		if (!event.isCancelled() && event.getEntity() instanceof Trident) {
			Trident trident = (Trident) event.getEntity();
			NBTItem nbti = new NBTItem(trident.getItem());
			Durability.applyDurability(nbti);
			Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin(Reference.PLUGIN_NAME),
					new Runnable() {
						@Override
						public void run() {
							trident.setItem(nbti.getItem());
						}
					}, 0L);
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onRiptide(PlayerRiptideEvent event) {
		Player player = event.getPlayer();
		boolean isEventMainHand = event.getItem().equals(event.getPlayer().getInventory().getItemInMainHand());
		if (isEventMainHand) {
			NBTItem nbti = new NBTItem(player.getEquipment().getItemInMainHand());
			boolean broke = Durability.applyDurability(nbti);
			if (broke) {
				Durability.playBreakItemAnimation(player, nbti.getItem());
				player.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
			} else
				player.getEquipment().setItemInMainHand(nbti.getItem());
		} else {
			NBTItem nbti = new NBTItem(player.getEquipment().getItemInOffHand());
			boolean broke = Durability.applyDurability(nbti);
			if (broke) {
				Durability.playBreakItemAnimation(player, nbti.getItem());
				player.getEquipment().setItemInOffHand(new ItemStack(Material.AIR));
			} else
				player.getEquipment().setItemInOffHand(nbti.getItem());
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockIgnite(BlockIgniteEvent event) {
		if (!event.isCancelled() && event.getCause().equals(IgniteCause.FLINT_AND_STEEL)
				&& (event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)
						|| event.getPlayer().getGameMode().equals(GameMode.ADVENTURE))) {
			Player player = event.getPlayer();
			if (player.getInventory().getItemInMainHand().getType().equals(Material.FLINT_AND_STEEL)) {
				NBTItem nbti = new NBTItem(player.getEquipment().getItemInMainHand());
				boolean broke = Durability.applyDurability(nbti);
				if (broke) {
					Durability.playBreakItemAnimation(player, nbti.getItem());
					player.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
				} else
					player.getEquipment().setItemInMainHand(nbti.getItem());
			} else if (event.getPlayer().getInventory().getItemInOffHand().getType().equals(Material.FLINT_AND_STEEL)) {
				NBTItem nbti = new NBTItem(player.getEquipment().getItemInOffHand());
				boolean broke = Durability.applyDurability(nbti);
				if (broke) {
					Durability.playBreakItemAnimation(player, nbti.getItem());
					player.getEquipment().setItemInOffHand(new ItemStack(Material.AIR));
				} else
					player.getEquipment().setItemInOffHand(nbti.getItem());
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onFish(PlayerFishEvent event) {
		if (!event.isCancelled() && !event.getState().equals(org.bukkit.event.player.PlayerFishEvent.State.REEL_IN)
				&& !event.getState().equals(org.bukkit.event.player.PlayerFishEvent.State.FISHING)
				&& !event.getState().equals(org.bukkit.event.player.PlayerFishEvent.State.BITE)) {
			Player player = event.getPlayer();
			if (player.getInventory().getItemInMainHand().getType().equals(Material.FISHING_ROD)) {
				NBTItem nbti = new NBTItem(player.getEquipment().getItemInMainHand());
				boolean broke = Durability.applyDurability(nbti);
				if (broke) {
					Durability.playBreakItemAnimation(player, nbti.getItem());
					player.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
				} else
					player.getEquipment().setItemInMainHand(nbti.getItem());
			} else if (player.getInventory().getItemInOffHand().getType().equals(Material.FISHING_ROD)) {
				NBTItem nbti = new NBTItem(player.getEquipment().getItemInOffHand());
				boolean broke = Durability.applyDurability(nbti);
				if (broke) {
					Durability.playBreakItemAnimation(player, nbti.getItem());
					player.getEquipment().setItemInOffHand(new ItemStack(Material.AIR));
				} else
					player.getEquipment().setItemInOffHand(nbti.getItem());
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockClick(PlayerInteractEvent event) {
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (!event.useInteractedBlock().equals(Event.Result.DENY)
					&& (event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)
							|| event.getPlayer().getGameMode().equals(GameMode.ADVENTURE))) {
				Material material = event.getClickedBlock().getType();
				Tool toolCompatibility = null;
				switch (material) {
				case PUMPKIN:
					toolCompatibility = Tools.SHEARS;
					break;
				case CAMPFIRE:
					if (((org.bukkit.block.data.type.Campfire) event.getClickedBlock().getBlockData()).isLit())
						toolCompatibility = Tools.SHOVEL;
					break;
				default:
					break;
				}
				if (toolCompatibility != null) {
					Player player = event.getPlayer();
					boolean isMainHand = event.getHand().equals(EquipmentSlot.HAND);
					ItemStack item = isMainHand ? player.getEquipment().getItemInMainHand()
							: player.getEquipment().getItemInOffHand();
					NBTItem nbti = new NBTItem(item);
					String itemType = nbti.hasKey(NBTUtil.ITEM_TYPE) ? nbti.getString(NBTUtil.ITEM_TYPE) : "";
					if (itemType != null && itemType.equals(toolCompatibility.getName())) {
						if (isMainHand) {
							boolean broke = Durability.applyDurability(nbti);
							if (broke) {
								Durability.playBreakItemAnimation(player, nbti.getItem());
								player.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
							} else
								player.getEquipment().setItemInMainHand(nbti.getItem());
						} else {
							boolean broke = Durability.applyDurability(nbti);
							if (broke) {
								Durability.playBreakItemAnimation(player, nbti.getItem());
								player.getEquipment().setItemInOffHand(new ItemStack(Material.AIR));
							} else
								player.getEquipment().setItemInOffHand(nbti.getItem());
						}
					}
				}
			}
		}
	}

}