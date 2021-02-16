package me.ptmdevelopment.net.keypad.resources;

import me.ptmdevelopment.net.keypad.resources.skulls.Skull;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;

import static me.ptmdevelopment.net.keypad.KeyPad.colorize;

public class ItemstackFactory {

    public ItemStack itemStack;

    public ItemStack getItemStack(){
        return itemStack;
    }

    public ItemstackFactory(Material material, String displayName, int amount, int itemByte, String... lore){
        ItemStack itemStack = new ItemStack(material, amount, (byte) itemByte);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(colorize(displayName));
        itemMeta.setLore(makeLore(lore));
        itemStack.setItemMeta(itemMeta);
        this.itemStack = itemStack;
    }
    public ItemstackFactory(Material material, String displayName, int amount, int itemByte, boolean unbreakable, String... lore){
        ItemStack itemStack = new ItemStack(material, amount, (byte) itemByte);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(colorize(displayName));
        itemMeta.setLore(makeLore(lore));
        if (unbreakable) {
            itemMeta.setUnbreakable(true);
            itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
            itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        }
        itemStack.setItemMeta(itemMeta);
        this.itemStack = itemStack;
    }
    public ItemstackFactory(String UCode, String displayName, int amount, String... lore){
        ItemStack itemStack = Skull.getCustomSkull("http://textures.minecraft.net/texture/"+UCode);
        itemStack.setAmount(amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(colorize(displayName));
        itemMeta.setLore(makeLore(lore));
        itemStack.setItemMeta(itemMeta);
        this.itemStack = itemStack;
    }
    public ItemstackFactory(Material material, String displayName, int amount, int itemByte, List<String> lore){
        ItemStack itemStack = new ItemStack(material, amount, (byte) itemByte);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(colorize(displayName));
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        this.itemStack = itemStack;
    }

    public ItemstackFactory(Material material, int amount, int itemByte){
        ItemStack itemStack = new ItemStack(material, amount, (byte) itemByte);
        this.itemStack = itemStack;
    }

    public ItemstackFactory(Material material, String displayName){
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(colorize(displayName));
        itemStack.setItemMeta(itemMeta);
        this.itemStack = itemStack;
    }

    public ItemstackFactory(int amount, String displayName, OfflinePlayer owner){
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, amount);
        SkullMeta itemMeta = (SkullMeta) itemStack.getItemMeta();
        itemMeta.setDisplayName(colorize(displayName));
        itemMeta.setOwningPlayer(owner);
        itemStack.setItemMeta(itemMeta);
        this.itemStack = itemStack;
    }
    public ItemstackFactory(int amount, String displayName, OfflinePlayer owner, String... lore){
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, amount);
        SkullMeta itemMeta = (SkullMeta) itemStack.getItemMeta();
        itemMeta.setDisplayName(colorize(displayName));
        itemMeta.setOwningPlayer(owner);
        itemMeta.setLore(makeLore(lore));
        itemStack.setItemMeta(itemMeta);
        this.itemStack = itemStack;
    }

    public ItemstackFactory(int amount, String displayName, Player owner, String... lore){
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, amount);
        SkullMeta itemMeta = (SkullMeta) itemStack.getItemMeta();
        itemMeta.setDisplayName(colorize(displayName));
        itemMeta.setOwningPlayer(owner);
        itemMeta.setLore(makeLore(lore));
        itemStack.setItemMeta(itemMeta);
        this.itemStack = itemStack;
    }

    public ItemstackFactory(Material material, String displayName, int amount, String... lore){
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(colorize(displayName));
        itemMeta.setLore(makeLore(lore));
        itemStack.setItemMeta(itemMeta);
        this.itemStack = itemStack;
    }

    public ItemstackFactory(Material material, int amount, String... lore){
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(makeLore(lore));
        itemStack.setItemMeta(itemMeta);
        this.itemStack = itemStack;
    }

    public ItemstackFactory(Material material, String displayName, int amount){
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(colorize(displayName));
        itemStack.setItemMeta(itemMeta);
        this.itemStack = itemStack;
    }

    private List<String> makeLore(String[] lore){
        for (int i = 0; i < lore.length; i++){
            lore[i] = colorize(lore[i]);
        }
        return Arrays.asList(lore);
    }

}
