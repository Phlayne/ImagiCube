package fr.phlayne.imagicube.schedulers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;

public class EntityScheduler extends SchedulerScript {

	@Override
	public void tick() {
		for (World world : Bukkit.getWorlds()) {
			for (Entity entity : world.getEntities()) {
				if (entity instanceof ItemFrame) {
					ItemFrame itemFrame = (ItemFrame) entity;
					if (itemFrame.getTicksLived() % 10 == 0 && !itemFrame.isVisible()
							&& itemFrame.getItem().getType().equals(Material.AIR)) {
						if (itemFrame.getFacing().equals(BlockFace.UP)
								|| itemFrame.getFacing().equals(BlockFace.DOWN)) {
							Location loc0 = new Location(world, itemFrame.getBoundingBox().getMinX(),
									itemFrame.getBoundingBox().getCenterY(), itemFrame.getBoundingBox().getMinZ());
							Location loc1 = new Location(world, itemFrame.getBoundingBox().getMinX(),
									itemFrame.getBoundingBox().getCenterY(), itemFrame.getBoundingBox().getMaxZ());
							Location loc2 = new Location(world, itemFrame.getBoundingBox().getMaxX(),
									itemFrame.getBoundingBox().getCenterY(), itemFrame.getBoundingBox().getMinZ());
							Location loc3 = new Location(world, itemFrame.getBoundingBox().getMaxX(),
									itemFrame.getBoundingBox().getCenterY(), itemFrame.getBoundingBox().getMaxZ());
							world.spawnParticle(Particle.SOUL_FIRE_FLAME, loc0, 1, 0, 0, 0, 0);
							world.spawnParticle(Particle.SOUL_FIRE_FLAME, loc1, 1, 0, 0, 0, 0);
							world.spawnParticle(Particle.SOUL_FIRE_FLAME, loc2, 1, 0, 0, 0, 0);
							world.spawnParticle(Particle.SOUL_FIRE_FLAME, loc3, 1, 0, 0, 0, 0);
						} else if (itemFrame.getFacing().equals(BlockFace.EAST)
								|| itemFrame.getFacing().equals(BlockFace.WEST)) {
							Location loc0 = new Location(world, itemFrame.getBoundingBox().getCenterX(),
									itemFrame.getBoundingBox().getMinY(), itemFrame.getBoundingBox().getMinZ());
							Location loc1 = new Location(world, itemFrame.getBoundingBox().getCenterX(),
									itemFrame.getBoundingBox().getMinY(), itemFrame.getBoundingBox().getMaxZ());
							Location loc2 = new Location(world, itemFrame.getBoundingBox().getCenterX(),
									itemFrame.getBoundingBox().getMaxY(), itemFrame.getBoundingBox().getMinZ());
							Location loc3 = new Location(world, itemFrame.getBoundingBox().getCenterX(),
									itemFrame.getBoundingBox().getMaxY(), itemFrame.getBoundingBox().getMaxZ());
							world.spawnParticle(Particle.SOUL_FIRE_FLAME, loc0, 1, 0, 0, 0, 0);
							world.spawnParticle(Particle.SOUL_FIRE_FLAME, loc1, 1, 0, 0, 0, 0);
							world.spawnParticle(Particle.SOUL_FIRE_FLAME, loc2, 1, 0, 0, 0, 0);
							world.spawnParticle(Particle.SOUL_FIRE_FLAME, loc3, 1, 0, 0, 0, 0);
						} else if (itemFrame.getFacing().equals(BlockFace.NORTH)
								|| itemFrame.getFacing().equals(BlockFace.SOUTH)) {
							Location loc0 = new Location(world, itemFrame.getBoundingBox().getMinX(),
									itemFrame.getBoundingBox().getMinY(), itemFrame.getBoundingBox().getCenterZ());
							Location loc1 = new Location(world, itemFrame.getBoundingBox().getMaxX(),
									itemFrame.getBoundingBox().getMinY(), itemFrame.getBoundingBox().getCenterZ());
							Location loc2 = new Location(world, itemFrame.getBoundingBox().getMinX(),
									itemFrame.getBoundingBox().getMaxY(), itemFrame.getBoundingBox().getCenterZ());
							Location loc3 = new Location(world, itemFrame.getBoundingBox().getMaxX(),
									itemFrame.getBoundingBox().getMaxY(), itemFrame.getBoundingBox().getCenterZ());
							world.spawnParticle(Particle.SOUL_FIRE_FLAME, loc0, 1, 0, 0, 0, 0);
							world.spawnParticle(Particle.SOUL_FIRE_FLAME, loc1, 1, 0, 0, 0, 0);
							world.spawnParticle(Particle.SOUL_FIRE_FLAME, loc2, 1, 0, 0, 0, 0);
							world.spawnParticle(Particle.SOUL_FIRE_FLAME, loc3, 1, 0, 0, 0, 0);
						}
					}
				}
			}
		}
	}

}
