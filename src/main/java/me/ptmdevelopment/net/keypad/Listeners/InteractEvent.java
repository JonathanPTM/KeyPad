package me.ptmdevelopment.net.keypad.Listeners;

import me.ptmdevelopment.net.keypad.resources.BlockGUI;
import me.ptmdevelopment.net.keypad.resources.Cooldown;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import static me.ptmdevelopment.net.keypad.commands.BlockGUICommand.BlockGUICreate;
import static me.ptmdevelopment.net.keypad.resources.Cooldown.isInCooldown;

public class InteractEvent implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        if ((action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.LEFT_CLICK_BLOCK)) && BlockGUICreate.containsKey(player)) {
            BlockGUI blockGUI = new BlockGUI(event.getClickedBlock().getLocation());
            blockGUI.create();
            BlockGUICreate.remove(player);
            player.sendMessage(" Created KeyPad!");
            event.setCancelled(true);
            return;
        }
        if ((action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_AIR)) && BlockGUICreate.containsKey(player)) {
            BlockGUICreate.remove(player);
            player.sendMessage(" Canceled KeyPad!");
            return;
        }

        if ((action.equals(Action.RIGHT_CLICK_BLOCK) || (action.equals(Action.LEFT_CLICK_BLOCK) && !player.getGameMode().equals(GameMode.CREATIVE))) && new BlockGUI(event.getClickedBlock().getLocation()).bestaat()) {
            if (isInCooldown(player.getUniqueId(), "BlockGUIS")) {
                event.setCancelled(true);
                return;
            }
            Cooldown c = new Cooldown(player.getUniqueId(), "BlockGUIS", 1);
            event.setCancelled(true);
            new BlockGUI(event.getClickedBlock().getLocation()).open(player);
            c.start();
            return;
        }
    }
}
