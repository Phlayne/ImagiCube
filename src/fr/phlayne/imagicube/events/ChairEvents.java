package fr.phlayne.imagicube.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.type.Slab;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.util.BoundingBox;
import org.spigotmc.event.entity.EntityDismountEvent;

import fr.phlayne.imagicube.ImagiCube;

public class ChairEvents implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (event.getPlayer().getVehicle() == null
				&& (event.getPlayer().getEquipment().getItemInMainHand().getType().isAir()
						&& event.getPlayer().getEquipment().getItemInOffHand().getType().isAir())
				&& event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getHand().equals(EquipmentSlot.HAND)) {
			if (event.getClickedBlock().getBlockData() instanceof Slab
					&& ((Slab) event.getClickedBlock().getBlockData()).getType().equals(Slab.Type.BOTTOM)

					||

					event.getClickedBlock().getBlockData() instanceof Stairs
							&& ((Stairs) event.getClickedBlock().getBlockData()).getHalf().equals(Bisected.Half.BOTTOM)

					||

					event.getClickedBlock().getType().equals(Material.PLAYER_HEAD)) {
				BoundingBox boundingBox = new BoundingBox(event.getClickedBlock().getX() + 0.2F,
						event.getClickedBlock().getY() - 0.1F, event.getClickedBlock().getZ() + 0.2F,
						event.getClickedBlock().getX() + 0.8F, event.getClickedBlock().getY() + 1.7F,
						event.getClickedBlock().getZ() + 0.8F);
				if (!boundingBox.overlaps(event.getClickedBlock().getRelative(0, 1, 0).getBoundingBox())
						&& !boundingBox.overlaps(event.getClickedBlock().getRelative(0, 2, 0).getBoundingBox())) {
					Arrow arrow = (Arrow) event.getPlayer().getWorld().spawnEntity(
							event.getClickedBlock().getLocation().add(0.5D, -0.1D, 0.5D), EntityType.ARROW);
					arrow.setSilent(true);
					arrow.setGravity(false);
					arrow.setInvulnerable(true);
					arrow.addPassenger(event.getPlayer());
				}
			}
		}
	}

	@EventHandler
	public void onChairDismount(EntityDismountEvent event) {
		if (event.getDismounted() instanceof Arrow && event.getEntity() instanceof Player) {
			event.getDismounted().remove();
			Bukkit.getScheduler().scheduleSyncDelayedTask(ImagiCube.getInstance(),
					() -> event.getEntity().teleport(event.getEntity().getLocation().add(0.0F, 0.6F, 0.0F)));
		}
	}
}
