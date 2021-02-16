package me.ptmdevelopment.net.keypad.resources.inventory;

import me.ptmdevelopment.net.keypad.resources.API;
import me.ptmdevelopment.net.keypad.resources.ItemstackFactory;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.material.Door;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Openable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static me.ptmdevelopment.net.keypad.KeyPad.*;
import static org.bukkit.Bukkit.getServer;

public class KeyPad {
    private Location location;
    private Player player;
    public static HashMap<Player, String> Code = new HashMap<>();
    public static HashMap<Player, Location> BackUp = new HashMap<>();
    public static ArrayList<Player> RegisterCode = new ArrayList<>();
    //private ArrayList<Player> DontClose = new ArrayList<>();

    public KeyPad(Location loc) {
        this.location = loc;
    }
    public KeyPad(Location loc, Player p) {
        this.location = loc;
        this.player = p;
    }

    private Location getBlockBehind() {
        Location l = this.location;
        Block block = this.location.getBlock();
        if (block.getData() == 2) {
            l.add(0,0,1);
        } else if (block.getData() == 3) {
            l.add(0,0,-1);
        } else if (block.getData() == 4) {
            l.add(1,0,0);
        } else if (block.getData() == 5) {
            l.add(-1,0,0);
        }
        return l;
    }

    public boolean isregistered() {
        return API.Keypads.contains("Pads."+locationRes(location));
    }

    public void openInterface() {
        BackUp.put(player, location);
        if (isregistered() && !RegisterCode.contains(player)) {
            Inventory inventory = Bukkit.createInventory(null, InventoryType.DROPPER, colorize("&4Keypad"));
            inventory.setItem(0, new ItemstackFactory("bf61269735f1e446becff25f9cb3c823679719a15f7f0fbc9a03911a692bdd", "&f&l1", 1).getItemStack());
            inventory.setItem(1, new ItemstackFactory("7d81a32d978f933deb7ea26aa326e4174697595a426eaa9f2ae5f9c2e661290", "&f&l2", 2).getItemStack());
            inventory.setItem(2, new ItemstackFactory("ceadaded81563f1c87769d6c04689dcdb9e8ca01da35281cd8fe251728d2d", "&f&l3", 3).getItemStack());
            inventory.setItem(3, new ItemstackFactory("6c608c2db525d6d77f7de4b961d67e53e9d7bacdaff31d4ca10fbbf92d66", "&f&l4", 4).getItemStack());
            inventory.setItem(4, new ItemstackFactory("1144c5193435199c135bd47d166ef1b4e2d3218383df9d34e3bb20d9f8e593", "&f&l5", 5).getItemStack());
            inventory.setItem(5, new ItemstackFactory("f61f7e38556856eae5566ef1c44a8cc64af8f3a58162b1dd8016a8778c71c", "&f&l6", 6).getItemStack());
            inventory.setItem(6, new ItemstackFactory("6e1cf31c49a24a8f37849fc3c5463ab64cc9bceb6f276a5c44aedd34fdf520", "&f&l7", 7).getItemStack());
            inventory.setItem(7, new ItemstackFactory("61c9c09d52debc465c32542c68be42bda6f6753fe1deba257327ac5a0c3ad", "&f&l8", 8).getItemStack());
            inventory.setItem(8, new ItemstackFactory("2dcf39f4bcd98484b0b479a7992d9270fe3a59b9b1a806d7a64ffb5b551ad", "&f&l9", 9).getItemStack());
            player.openInventory(inventory);
        } else {
            RegisterCode.add(player);
            Inventory inventory = Bukkit.createInventory(null, InventoryType.DROPPER, colorize("&4Register code"));
            inventory.setItem(0, new ItemstackFactory("bf61269735f1e446becff25f9cb3c823679719a15f7f0fbc9a03911a692bdd", "&f&l1", 1).getItemStack());
            inventory.setItem(1, new ItemstackFactory("7d81a32d978f933deb7ea26aa326e4174697595a426eaa9f2ae5f9c2e661290", "&f&l2", 2).getItemStack());
            inventory.setItem(2, new ItemstackFactory("ceadaded81563f1c87769d6c04689dcdb9e8ca01da35281cd8fe251728d2d", "&f&l3", 3).getItemStack());
            inventory.setItem(3, new ItemstackFactory("6c608c2db525d6d77f7de4b961d67e53e9d7bacdaff31d4ca10fbbf92d66", "&f&l4", 4).getItemStack());
            inventory.setItem(4, new ItemstackFactory("1144c5193435199c135bd47d166ef1b4e2d3218383df9d34e3bb20d9f8e593", "&f&l5", 5).getItemStack());
            inventory.setItem(5, new ItemstackFactory("f61f7e38556856eae5566ef1c44a8cc64af8f3a58162b1dd8016a8778c71c", "&f&l6", 6).getItemStack());
            inventory.setItem(6, new ItemstackFactory("6e1cf31c49a24a8f37849fc3c5463ab64cc9bceb6f276a5c44aedd34fdf520", "&f&l7", 7).getItemStack());
            inventory.setItem(7, new ItemstackFactory("61c9c09d52debc465c32542c68be42bda6f6753fe1deba257327ac5a0c3ad", "&f&l8", 8).getItemStack());
            inventory.setItem(8, new ItemstackFactory("2dcf39f4bcd98484b0b479a7992d9270fe3a59b9b1a806d7a64ffb5b551ad", "&f&l9", 9).getItemStack());
            player.openInventory(inventory);
            //player.sendMessage(Language.getMessage("Prefix")+colorize("&fThe code of this keypad is: &4"+register()+"&f, remember this code."));
        }
    }
    public void destroy() {
        API.Keypads.set("Pads."+locationRes(location),null);
        API.saveMachines();
    }
    public Integer register() {
        Random rn = new Random();
        int range = 99999 - 1111 + 1;
        int randomNum =  rn.nextInt(range) + 1111;
        API.Keypads.set("Pads."+locationRes(location), randomNum);
        API.saveMachines();
        return randomNum;
    }
    public void register(Integer i) {
        API.Keypads.set("Pads."+locationRes(location), i);
        API.saveMachines();
        return;
    }

    public Integer getCode() {
        if (isregistered()) {
            return API.Keypads.getInt("Pads."+locationRes(location));
        }
        return null;
    }

    public void open() {
        List<Block> l = getNearbyBlocks(getBlockBehind(),1);
        for (Block block : l) {
            if (isAnDoor(block)) {
                try {
                    if (openBlock(block)) {
                        playSoundNear(block.getLocation(), Sound.BLOCK_IRON_DOOR_OPEN, 1,7);
                        playSoundNear(block.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 1,7);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                getServer().getScheduler().scheduleSyncDelayedTask(getPlugin(), new Runnable() {
                    public void run() {
                        try {
                            if (closeBlock(block)) {
                                playSoundNear(block.getLocation(), Sound.BLOCK_IRON_DOOR_CLOSE, 1,7);
                                playSoundNear(block.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 1,7);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 20*2);
            }
        }
    }
    public boolean openBlock(Block b) {
        Openable d;
        BlockState s = b.getState();
        if (s.getData() instanceof Door && ((Door)s.getData()).isTopHalf()) {
            s = b.getRelative(BlockFace.DOWN).getState();
        }
        d = (Openable)s.getData();
        if (!d.isOpen()) {
            d.setOpen(true);
        }
        s.setData((MaterialData)d);
        s.update();
        return true;
    }
    public boolean closeBlock(Block b) {
        Openable d;
        BlockState s = b.getState();
        if (s.getData() instanceof Door && ((Door)s.getData()).isTopHalf()) {
            s = b.getRelative(BlockFace.DOWN).getState();
        }
        d = (Openable)s.getData();
        if (d.isOpen()) {
            d.setOpen(false);
        }
        s.setData((MaterialData)d);
        s.update();
        return true;
    }

    public static List<Block> getNearbyBlocks(Location location, int radius) {
        List<Block> blocks = new ArrayList<Block>();
        for(int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
            for(int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
                for(int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                    blocks.add(location.getWorld().getBlockAt(x, y, z));
                }
            }
        }
        return blocks;
    }
}
