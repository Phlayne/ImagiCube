package fr.phlayne.imagicube.util;

import java.util.Random;

import org.bukkit.entity.Projectile;
import org.bukkit.util.Vector;

public class OthersUtil {

	public static Random random = new Random();

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
