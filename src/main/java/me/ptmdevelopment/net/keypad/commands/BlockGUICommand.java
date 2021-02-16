package me.ptmdevelopment.net.keypad.commands;

import me.ptmdevelopment.net.keypad.resources.BlockGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

import static me.ptmdevelopment.net.keypad.KeyPad.colorize;

public class BlockGUICommand implements CommandExecutor {
    public static HashMap<Player, Boolean> BlockGUICreate = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("rlw.keypad")) {
                if (args.length == 0) {
                    player.sendMessage(colorize("&fUse: /keypad (type)"));
                    player.sendMessage(colorize("&fUse: /keypad tlist"));
                } else {
                    BlockGUICreate.put(player, true);
                    player.sendMessage(colorize("&fRight click now the block to connect."));
                    return true;
                }
            } else {
                sender.sendMessage("No perms.");
            }
        } else {
            sender.sendMessage("Only players can run this command");
        }
        return false;
    }
}
