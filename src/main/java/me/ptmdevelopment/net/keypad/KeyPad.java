package me.ptmdevelopment.net.keypad;

import me.ptmdevelopment.net.keypad.Listeners.InteractEvent;
import me.ptmdevelopment.net.keypad.Listeners.InventoryInteract;
import me.ptmdevelopment.net.keypad.commands.BlockGUICommand;
import me.ptmdevelopment.net.keypad.resources.API;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class KeyPad extends JavaPlugin {
    public static KeyPad plugin;
    public static KeyPad getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        API.setup(this);
        getServer().getPluginManager().registerEvents(new InteractEvent(), this);
        getServer().getPluginManager().registerEvents(new InventoryInteract(), this);
        this.getCommand("keypad").setExecutor((CommandExecutor) new BlockGUICommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /*
        Utils
     */
    public static String locationRes(Location location) {
        return location.getBlockX()+"@"+location.getBlockY()+"@"+location.getBlockZ()+"@"+location.getWorld().getName();
    }
    public static Location stringRes(String s) {
        String[] d = s.split("@");
        return new Location(Bukkit.getWorld(d[3]),Integer.parseInt(d[0]),Integer.parseInt(d[1]), Integer.parseInt(d[2]));
    }
    public static void playSoundNear(Location location, Sound sound, float volume, float pitch) {
        for (Player p : location.getWorld().getPlayers()) {
            if (p.getLocation().distanceSquared(location) <= (15)) {
                p.playSound(location, sound, volume, pitch);
            }
        }
    }
    public static void playSoundNear(Location location, Sound sound, float volume, float pitch, int radius) {
        for (Player p : location.getWorld().getPlayers()) {
            if (p.getLocation().distanceSquared(location) <= (radius)) {
                p.playSound(location, sound, volume, pitch);
            }
        }
    }
    public static void playSoundNear(Location location, Sound sound, float pitch, int radius) {
        for (Player p : location.getWorld().getPlayers()) {
            if (p.getLocation().distanceSquared(location) <= (radius)) {
                int volume = (int) ((100 / radius) * p.getLocation().distanceSquared(location));
                p.playSound(location, sound, volume, pitch);
            }
        }
    }
    public static boolean isAnDoor(Block bk) {
        if (bk.getType().equals(Material.IRON_DOOR) || bk.getType().equals(Material.DARK_OAK_DOOR) || bk.getType().equals(Material.ACACIA_DOOR)
                || bk.getType().equals(Material.BIRCH_DOOR) || bk.getType().equals(Material.JUNGLE_DOOR) || bk.getType().equals(Material.SPRUCE_DOOR) || bk.getType().toString().contains("DOOR")) return true;
        return false;
    }
    public static String colorize(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
