package fr.phlayne.imagicube.craftbehaviour;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;

import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.data.Config;
import fr.phlayne.imagicube.item.Durability;
import fr.phlayne.imagicube.util.NBTUtil;

public class RepairWithMaterialScript implements FuseScript {

	public static final List<Material> PLANKS = Arrays.asList(Material.ACACIA_PLANKS, Material.BIRCH_PLANKS,
			Material.DARK_OAK_PLANKS, Material.JUNGLE_PLANKS, Material.OAK_PLANKS, Material.SPRUCE_PLANKS);

	public FuseResult getResult(NBTItem leftItem, NBTItem rightItem, NBTItem result, String newName) {
		int levelAndItemAmount = repairItem(leftItem, rightItem, result);
		if (levelAndItemAmount > 0)
			return new FuseResult(result.getItem(), levelAndItemAmount, levelAndItemAmount).showResult(true);
		return new FuseResult(result.getItem(), 0, 0);
	}

	// TODO use MineralProperty that stores the name of the material and the
	// matching bukkit material or imagicube material to repair it
	public int repairItem(NBTItem nbti, NBTItem material, NBTItem result) {
		int consumedMaterial = 0;
		boolean repair = false;
		if (material != null && nbti.hasKey(NBTUtil.MATERIAL)) {
			switch (nbti.getString(NBTUtil.MATERIAL)) {
			case "wood":
				if (PLANKS.contains(material.getItem().getType()))
					repair = true;
				break;
			case "oak":
				if (material.getItem().getType().equals(Material.OAK_PLANKS))
					repair = true;
				break;
			case "spruce":
				if (material.getItem().getType().equals(Material.SPRUCE_PLANKS))
					repair = true;
				break;
			case "birch":
				if (material.getItem().getType().equals(Material.BIRCH_PLANKS))
					repair = true;
				break;
			case "jungle":
				if (material.getItem().getType().equals(Material.JUNGLE_PLANKS))
					repair = true;
				break;
			case "acacia":
				if (material.getItem().getType().equals(Material.ACACIA_PLANKS))
					repair = true;
				break;
			case "dark_oak":
				if (material.getItem().getType().equals(Material.DARK_OAK_PLANKS))
					repair = true;
				break;
			case "crimson":
				if (material.getItem().getType().equals(Material.CRIMSON_PLANKS))
					repair = true;
				break;
			case "warped":
				if (material.getItem().getType().equals(Material.WARPED_PLANKS))
					repair = true;
				break;
			case "leather":
				if (material.getItem().getType().equals(Material.LEATHER))
					repair = true;
				break;
			case "stone":
				if (material.getItem().getType().equals(Material.COBBLESTONE))
					repair = true;
				break;
			case "iron":
				if (material.getItem().getType().equals(Material.IRON_INGOT))
					repair = true;
				break;
			case "chainmail":
				if (Config.getConfig().getBoolean("craftable_chainmail_armor_with_chains")
						? material.getItem().getType().equals(Material.CHAIN)
						: material.getItem().getType().equals(Material.IRON_INGOT))
					repair = true;
				break;
			case "gold":
				if (material.getItem().getType().equals(Material.GOLD_INGOT))
					repair = true;
				break;
			case "diamond":
				if (material.getItem().getType().equals(Material.DIAMOND))
					repair = true;
				break;
			case "prismarine":
				if (material.getItem().getType().equals(Material.PRISMARINE_SHARD))
					repair = true;
				break;
			case "turtle":
				if (material.getItem().getType().equals(Material.SCUTE))
					repair = true;
				break;
			case "netherite":
				if (material.getItem().getType().equals(Material.NETHERITE_INGOT))
					repair = true;
				break;
			}
		} else if (nbti.hasKey(NBTUtil.ITEM_TYPE)) {
			if (nbti.getString(NBTUtil.ITEM_TYPE).equals("shield")) {
				if (PLANKS.contains(material.getItem().getType()))
					repair = true;
			} else if (nbti.getString(NBTUtil.ITEM_TYPE).equals("elytra"))
				if (material.getItem().getType().equals(Material.PHANTOM_MEMBRANE))
					repair = true;
		}
		if (repair) {
			int repairAmount = Math.min(4, Math.min((int) Math.ceil((1F - Durability.getPercentDurability(nbti)) * 4F),
					material.getItem().getAmount()));
			int newDurability = nbti.getInteger(NBTUtil.DURABILITY);
			consumedMaterial += repairAmount;
			for (int i = 0; i < repairAmount; i++) {
				newDurability = Math.max(0,
						newDurability - (int) Math.ceil(((float) Durability.getMaxDurability(nbti)) / 4F));
				Durability.setDurability(result, newDurability);
			}
		}
		return consumedMaterial;
	}
}
