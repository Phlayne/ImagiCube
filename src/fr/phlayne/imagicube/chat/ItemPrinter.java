package fr.phlayne.imagicube.chat;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import de.tr7zw.nbtapi.NBTItem;
import fr.phlayne.imagicube.util.NBTUtil;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;

public class ItemPrinter {

	@Deprecated
	public static BaseComponent[] convertItemToText(ItemStack item) {
		BaseComponent[] baseComponent;
		if (item == null || item.getType().equals(Material.AIR)) {
			return new ComponentBuilder(new TranslatableComponent("block.minecraft.air")).bold(false).create();
		} else {
			BaseComponent[] hoverEventComponents = new BaseComponent[] {
					new TextComponent(NBTItem.convertItemtoNBT(item).toString()) };
			HoverEvent event = new HoverEvent(HoverEvent.Action.SHOW_ITEM, hoverEventComponents);
			if (item.hasItemMeta() && item.getItemMeta().hasDisplayName())
				baseComponent = NBTUtil.readName(new NBTItem(item), null).convertToComponents();
			else
				baseComponent = new ComponentBuilder(
						new TranslatableComponent((item.getType().isBlock() ? "block" : "item") + "."
								+ item.getType().getKey().toString().replace(':', '.'))).bold(false).create();
			for (BaseComponent bc : baseComponent)
				bc.setHoverEvent(event);
		}
		return baseComponent;
	}

}
