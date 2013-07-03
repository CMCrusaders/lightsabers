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

		if (player.isOp() || player.hasPermission("lightsabers.use")) {

			if (item != null) {
				
				if (item.hasItemMeta()) {
					
					ItemMeta meta = item.getItemMeta();

					if (meta.hasDisplayName()) {

						if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

							/*
							 * TRANSITION: Stick into lightsaber
							 */

							if (item.getType() == Material.STICK) {

								// Check if stick has name "Handle"
								if (ChatColor.stripColor(meta.getDisplayName()).trim().equalsIgnoreCase("lightsaber handle")) {

									// New lightsaber
									ItemStack lightSaber = new ItemStack(Material.DIAMOND_SWORD, 1);
									lightSaber.addEnchantment(Enchantment.DAMAGE_ALL, 2);

									ItemMeta saberMeta = lightSaber.getItemMeta();
									saberMeta.setDisplayName(ChatColor.AQUA + "LightSaber");
									lightSaber.setItemMeta(saberMeta);

									player.setItemInHand(lightSaber);

									// Transition effects
									player.playSound(player.getLocation(), Sound.BLAZE_BREATH, 1, 5);
									player.playEffect(player.getLocation().add(0.5, 1, 0.5), Effect.SMOKE, 4);

									return;
								}
							}

							/*
							 * TRANSITION: Lightsaber back into stick
							 */

							if (item.getType() == Material.DIAMOND_SWORD) {

								// Check if lightsaber has name "Lightsaber"
								if (ChatColor.stripColor(meta.getDisplayName()).trim().equalsIgnoreCase("lightsaber")) {

									// New handle
									ItemStack handle = new ItemStack(Material.STICK, 1);

									ItemMeta handleMeta = handle.getItemMeta();
									handleMeta.setDisplayName("Lightsaber Handle");
									handle.setItemMeta(handleMeta);

									player.setItemInHand(handle);

									// Transition effects
									player.playSound(player.getLocation(), Sound.BLAZE_BREATH, 1, 5);
									player.playEffect(player.getLocation().add(0.5, 1, 0.5), Effect.SMOKE, 4);
								}
							}
						}

						// Lightsaber sound effect
						else if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {

							if (item.getType() == Material.DIAMOND_SWORD && meta.getDisplayName().trim().equalsIgnoreCase("lightsaber")) {

								player.playSound(player.getLocation(), Sound.BLAZE_BREATH, 10, 5);
							}
						}
					}

				}
			}

		}

	}
}
