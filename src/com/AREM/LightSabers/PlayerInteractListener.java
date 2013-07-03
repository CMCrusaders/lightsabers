package com.AREM.LightSabers;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerInteractListener implements Listener {

	@EventHandler
	public void PlayerInteract(PlayerInteractEvent event) {

		Player player = event.getPlayer();

		ItemStack item = player.getItemInHand();

		ItemMeta meta = item.getItemMeta();

		if (player.isOp() || player.hasPermission("lightsabers")) {

			if (event.getAction() == Action.LEFT_CLICK_BLOCK
					&& item.getType() == Material.DIAMOND_SWORD) {

				if (meta.hasDisplayName()) {
					String name = meta.getDisplayName();
					if (ChatColor.stripColor(name).equalsIgnoreCase(
							"lightsaber")) {

						player.setItemInHand(new ItemStack(280, 1));
						player.playSound(player.getLocation(),
								Sound.BLAZE_BREATH, 1, 7);
						player.playEffect(
								player.getLocation().add(0.5, 1, 0.5),
								Effect.SMOKE, 4);
					}
				}
			}

			if (event.getAction() == Action.RIGHT_CLICK_AIR
					&& item.getType() == Material.STICK) {
				ItemStack lightSaber = new ItemStack(276, 1);
				lightSaber.addEnchantment(Enchantment.DAMAGE_ALL, 2);
				player.setItemInHand(lightSaber);
				player.playSound(player.getLocation(), Sound.BLAZE_BREATH, 1, 5);
				player.playEffect(player.getLocation().add(0.5, 1, 0.5),
						Effect.SMOKE, 4);

				ItemStack Handle = new ItemStack(280, 1);
				String name = meta.getDisplayName();
				if (ChatColor.stripColor(name).equalsIgnoreCase("handle")) {

					if (event.getAction() == Action.LEFT_CLICK_BLOCK
							&& item.getType() == Material.DIAMOND_SWORD) {
						player.setItemInHand(Handle);
						player.playSound(player.getLocation(),
								Sound.BLAZE_BREATH, 1, 5);
						player.playEffect(
								player.getLocation().add(0.5, 1, 0.5),
								Effect.SMOKE, 4);
					}

				}

				if (event.getAction() == Action.LEFT_CLICK_AIR
						&& item.getType() == Material.DIAMOND_SWORD) {
					player.playSound(player.getLocation(), Sound.BLAZE_BREATH,
							10, 5);
				}

			}

		}

	}
}
