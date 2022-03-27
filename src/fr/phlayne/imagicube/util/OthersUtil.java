package fr.phlayne.imagicube.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import fr.phlayne.imagicube.Reference;

public class OthersUtil {

	public static Map<Player, Location> freezeLocation = new HashMap<Player, Location>();
	public static Random random = new Random();

	public static boolean isIntangible(Location location) {
		return location.getBlock().isPassable() || !location.getBlock().getBoundingBox().contains(location.toVector());
	}

	public static boolean isRightClickableWeapon(ItemStack item, Player player) {
		switch (item.getType()) {
		case BOW:
		case CROSSBOW:
			return player.getInventory().contains(Material.ARROW)
					|| player.getInventory().contains(Material.SPECTRAL_ARROW)
					|| player.getInventory().contains(Material.TIPPED_ARROW);
		default:
			return false;
		}
	}

	public static Location getBlockAt(Player player, int rayon) {
		Location location = player.getEyeLocation();
		Location nextloc = player.getEyeLocation();
		int count = rayon * 4;
		while (count > 0) {
			nextloc.add(player.getLocation().getDirection().multiply(0.25));
			if (nextloc.getBlock().getType().equals(Material.AIR)) {
				location = nextloc;
			} else {
				return location.subtract(player.getLocation().getDirection());
			}

			count--;
		}
		return location;
	}

	/*
	 * I'll have to replace the entity by a BoundingBox However, the BBox hasn't a
	 * spherical radius
	 */
	@Deprecated
	public static void DamageParticle(Location particleLocation, Player player, int damage, float particleRadius,
			boolean hasKnockBack) {
		Arrow a = particleLocation.getWorld().spawnArrow(particleLocation, new Vector(0, 0, 0), 0, 0);
		if (a.getNearbyEntities(particleRadius, particleRadius, particleRadius).size() > 0) {
			for (Entity entities : a.getNearbyEntities(particleRadius, particleRadius, particleRadius)) {
				if (entities instanceof Player) {
					Player players = (Player) entities;
					if (players != player) {
						Vector v = player.getVelocity();
						players.damage(damage, player);
						if (!hasKnockBack) {
							players.setVelocity(v);
						}
					}
				}
			}
		}
		a.remove();
	}

	public static Entity getNearEntity(Entity entity, int rayon, Class<? extends Entity> lookingType) {
		Entity returnEntity = null;
		if (entity.getNearbyEntities(rayon, rayon, rayon).size() > 0) {
			double distance = 0;
			double baseDistance = Integer.MAX_VALUE;
			for (Entity entities : entity.getNearbyEntities(rayon, rayon, rayon)) {
				if (lookingType.isInstance(entities)) {
					distance = Math.sqrt(Math.pow(entity.getLocation().getX() - entities.getLocation().getX(), 2)
							+ Math.pow(entity.getLocation().getY() - entities.getLocation().getY(), 2)
							+ Math.pow(entity.getLocation().getZ() - entities.getLocation().getZ(), 2));
					if (distance < baseDistance) {
						baseDistance = distance;
						returnEntity = entities;
					}
				}
			}
		}
		return returnEntity;
	}

	public static boolean setFreeze(Entity entity, int ticks) {
		try {
			if (entity instanceof Player && getFreeze(entity) <= 0 && ticks > 0)
				freezeLocation.put((Player) entity, entity.getLocation());
			entity.setMetadata("freezeTicks",
					new FixedMetadataValue(Bukkit.getPluginManager().getPlugin(Reference.PLUGIN_NAME), ticks));

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static int getFreeze(Entity entity) {
		if (entity.hasMetadata("freezeTicks")) {
			if (entity.getMetadata("freezeTicks").size() > 0)
				return entity.getMetadata("freezeTicks").get(0).asInt();
			else
				return 0;
		} else {
			return 0;
		}
	}

	public static void shootArrow(Projectile projectile, double x, double y, double z, float speed, float divergence) {
		Vector vector = (new Vector(x, y, z)).normalize()
				.add(new Vector(random.nextGaussian() * 0.0075 * divergence,
						random.nextGaussian() * 0.0075 * divergence, random.nextGaussian() * 0.0075 * divergence))
				.multiply(speed);
		projectile.setVelocity(projectile.getVelocity().add(vector));
		double horizontalDistance = Math.sqrt(vector.getX() * vector.getX() + vector.getZ() * vector.getZ());
		projectile.setRotation((float) Math.atan2(vector.getX(), vector.getZ()) * (float) (180D / Math.PI),
				(float) Math.atan2(vector.getY(), horizontalDistance) * (float) (180D / Math.PI));
	}

	public static void shootArrowFromRotation(Projectile projectile, float pitch, float yaw, float roll, float speed,
			float divergence) {
		float x = (float) (-Math.sin(yaw * Math.PI / 180F) * Math.cos(pitch * Math.PI / 180));
		float y = (float) -Math.sin((pitch + roll) * Math.PI / 180);
		float z = (float) (Math.cos(yaw * Math.PI / 180) * Math.cos(pitch * Math.PI / 180));
		shootArrow(projectile, x, y, z, speed, divergence);
	}

	/*
	 * public static void modifyMaxStack(net.minecraft.world.item.Item item, int
	 * amount) { try { Field f =
	 * net.minecraft.world.item.Item.class.getDeclaredField("maxStackSize");
	 * f.setAccessible(true); f.setInt(item, amount); } catch (Exception e) {
	 * e.printStackTrace(); } }
	 */
}
