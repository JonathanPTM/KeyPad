package me.ptmdevelopment.net.keypad.Listeners;

import me.ptmdevelopment.net.keypad.resources.ItemstackFactory;
import me.ptmdevelopment.net.keypad.resources.inventory.KeyPad;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import static me.ptmdevelopment.net.keypad.KeyPad.colorize;
import static me.ptmdevelopment.net.keypad.resources.inventory.KeyPad.BackUp;
import static me.ptmdevelopment.net.keypad.resources.inventory.KeyPad.RegisterCode;

public class InventoryInteract implements Listener {
    @EventHandler
    private void closeS(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player) {
            Player player = (Player) event.getPlayer();
            if (RegisterCode.contains(event.getPlayer())) {
                KeyPad k = new KeyPad(BackUp.get((Player)event.getPlayer()), (Player)event.getPlayer());
                k.register(Integer.parseInt(KeyPad.Code.get(player)));
                player.sendMessage(colorize("&fThe code of this keypad is: &4"+KeyPad.Code.get(player)+"&f, remember this code."));
                RegisterCode.remove(player);
            }
            if (BackUp.containsKey((Player)event.getPlayer())) {
                BackUp.remove((Player)event.getPlayer());
            }
            if (KeyPad.Code.containsKey((Player)event.getPlayer())) {
                KeyPad.Code.remove((Player)event.getPlayer());
            }
            return;
        }
    }
    @EventHandler
    private void Interactie(InventoryClickEvent event) {
        if (event.getInventory().getType() == InventoryType.DROPPER
                && event.getView().getTitle().equals(colorize("&4Keypad"))) {
            Player player = (Player) event.getWhoClicked();
            Inventory inventory = event.getInventory();
            event.setCancelled(true);
            String ExistingCode = "";
            if (KeyPad.Code.containsKey(player))
                ExistingCode = KeyPad.Code.get(player);
            if (event.getSlot() == 0) {
                KeyPad.Code.put(player,(ExistingCode+"1"));
                player.playSound(BackUp.get(player), Sound.UI_BUTTON_CLICK, 20,1);
                inventory.setItem(0, new ItemstackFactory("88991697469653c9af8352fdf18d0cc9c67763cfe66175c1556aed33246c7", "&f&l1", 1).getItemStack());
            }
            if (event.getSlot() == 1) {
                KeyPad.Code.put(player,(ExistingCode+"2"));
                player.playSound(BackUp.get(player), Sound.UI_BUTTON_CLICK, 20,1);
                inventory.setItem(1, new ItemstackFactory("5496c162d7c9e1bc8cf363f1bfa6f4b2ee5dec6226c228f52eb65d96a4635c", "&f&l2", 2).getItemStack());
            }
            if (event.getSlot() == 2) {
                KeyPad.Code.put(player,(ExistingCode+"3"));
                player.playSound(BackUp.get(player), Sound.UI_BUTTON_CLICK, 20,1);
                inventory.setItem(2, new ItemstackFactory("c4226f2eb64abc86b38b61d1497764cba03d178afc33b7b8023cf48b49311", "&f&l3", 3).getItemStack());
            }
            if (event.getSlot() == 3) {
                KeyPad.Code.put(player,(ExistingCode+"4"));
                player.playSound(BackUp.get(player), Sound.UI_BUTTON_CLICK, 20,1);
                inventory.setItem(3, new ItemstackFactory("f920ecce1c8cde5dbca5938c5384f714e55bee4cca866b7283b95d69eed3d2c", "&f&l4", 4).getItemStack());
            }
            if (event.getSlot() == 4) {
                KeyPad.Code.put(player,(ExistingCode+"5"));
                player.playSound(BackUp.get(player), Sound.UI_BUTTON_CLICK, 20,1);
                inventory.setItem(4, new ItemstackFactory("a2c142af59f29eb35ab29c6a45e33635dcfc2a956dbd4d2e5572b0d38891b354", "&f&l5", 5).getItemStack());
            }
            if (event.getSlot() == 5) {
                KeyPad.Code.put(player,(ExistingCode+"6"));
                player.playSound(BackUp.get(player), Sound.UI_BUTTON_CLICK, 20,1);
                inventory.setItem(5, new ItemstackFactory("24ddb03aa8c584168c63ece337aefb281774377db72337297de258b4cca6fba4", "&f&l6", 6).getItemStack());
            }
            if (event.getSlot() == 6) {
                KeyPad.Code.put(player,(ExistingCode+"7"));
                player.playSound(BackUp.get(player), Sound.UI_BUTTON_CLICK, 20,1);
                inventory.setItem(6, new ItemstackFactory("d7de70b88368ce23a1ac6d1c4ad9131480f2ee205fd4a85ab2417af7f6bd90", "&f&l7", 7).getItemStack());
            }
            if (event.getSlot() == 7) {
                KeyPad.Code.put(player,(ExistingCode+"8"));
                player.playSound(BackUp.get(player), Sound.UI_BUTTON_CLICK, 20,1);
                inventory.setItem(7, new ItemstackFactory("42647ae47b6b51f5a45eb3dcafa9b88f288ede9cebdb52a159e3110e6b8118e", "&f&l8", 8).getItemStack());
            }
            if (event.getSlot() == 8) {
                KeyPad.Code.put(player,(ExistingCode+"9"));
                player.playSound(BackUp.get(player), Sound.UI_BUTTON_CLICK, 20,1);
                inventory.setItem(8, new ItemstackFactory("dae461a4434196d37296ad5adf6d9d5744a0415dc61c475a6dfa6285814052", "&f&l9", 9).getItemStack());
            }
            //player.sendMessage(Code.get(player)+" /\\ " +BackUp.get(player).toString());
            if (KeyPad.Code.containsKey(player)) {
                if (KeyPad.Code.get(player).length() >= 4 && KeyPad.Code.get(player).length() <= 8) {
                    KeyPad k =new KeyPad(BackUp.get(player), player);
                    if (k.getCode() == Integer.parseInt(KeyPad.Code.get(player))) {
                        player.closeInventory();
                        k.open();
                    }
                } else if (KeyPad.Code.get(player).length() > 8) {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BASS, 80, 1);
                    player.closeInventory();
                    player.sendMessage(colorize("&fThe code you entered is to long."));
                    return;
                }
            }
            return;
        }
        if (event.getInventory().getType() == InventoryType.DROPPER
                && event.getView().getTitle().equals(colorize("&4Register code"))) {
            Player player = (Player) event.getWhoClicked();
            if (!RegisterCode.contains(player)) {
                RegisterCode.add(player);
            }
            Inventory inventory = event.getInventory();
            event.setCancelled(true);
            String ExistingCode = "";
            if (KeyPad.Code.containsKey(player))
                ExistingCode = KeyPad.Code.get(player);
            if (event.getSlot() == 0) {
                KeyPad.Code.put(player,(ExistingCode+"1"));
                player.playSound(BackUp.get(player), Sound.UI_BUTTON_CLICK, 20,1);
                inventory.setItem(0, new ItemstackFactory("88991697469653c9af8352fdf18d0cc9c67763cfe66175c1556aed33246c7", "&f&l1", 1).getItemStack());
            }
            if (event.getSlot() == 1) {
                KeyPad.Code.put(player,(ExistingCode+"2"));
                player.playSound(BackUp.get(player), Sound.UI_BUTTON_CLICK, 20,1);
                inventory.setItem(1, new ItemstackFactory("5496c162d7c9e1bc8cf363f1bfa6f4b2ee5dec6226c228f52eb65d96a4635c", "&f&l2", 2).getItemStack());
            }
            if (event.getSlot() == 2) {
                KeyPad.Code.put(player,(ExistingCode+"3"));
                player.playSound(BackUp.get(player), Sound.UI_BUTTON_CLICK, 20,1);
                inventory.setItem(2, new ItemstackFactory("c4226f2eb64abc86b38b61d1497764cba03d178afc33b7b8023cf48b49311", "&f&l3", 3).getItemStack());
            }
            if (event.getSlot() == 3) {
                KeyPad.Code.put(player,(ExistingCode+"4"));
                player.playSound(BackUp.get(player), Sound.UI_BUTTON_CLICK, 20,1);
                inventory.setItem(3, new ItemstackFactory("f920ecce1c8cde5dbca5938c5384f714e55bee4cca866b7283b95d69eed3d2c", "&f&l4", 4).getItemStack());
            }
            if (event.getSlot() == 4) {
                KeyPad.Code.put(player,(ExistingCode+"5"));
                player.playSound(BackUp.get(player), Sound.UI_BUTTON_CLICK, 20,1);
                inventory.setItem(4, new ItemstackFactory("a2c142af59f29eb35ab29c6a45e33635dcfc2a956dbd4d2e5572b0d38891b354", "&f&l5", 5).getItemStack());
            }
            if (event.getSlot() == 5) {
                KeyPad.Code.put(player,(ExistingCode+"6"));
                player.playSound(BackUp.get(player), Sound.UI_BUTTON_CLICK, 20,1);
                inventory.setItem(5, new ItemstackFactory("24ddb03aa8c584168c63ece337aefb281774377db72337297de258b4cca6fba4", "&f&l6", 6).getItemStack());
            }
            if (event.getSlot() == 6) {
                KeyPad.Code.put(player,(ExistingCode+"7"));
                player.playSound(BackUp.get(player), Sound.UI_BUTTON_CLICK, 20,1);
                inventory.setItem(6, new ItemstackFactory("d7de70b88368ce23a1ac6d1c4ad9131480f2ee205fd4a85ab2417af7f6bd90", "&f&l7", 7).getItemStack());
            }
            if (event.getSlot() == 7) {
                KeyPad.Code.put(player,(ExistingCode+"8"));
                player.playSound(BackUp.get(player), Sound.UI_BUTTON_CLICK, 20,1);
                inventory.setItem(7, new ItemstackFactory("42647ae47b6b51f5a45eb3dcafa9b88f288ede9cebdb52a159e3110e6b8118e", "&f&l8", 8).getItemStack());
            }
            if (event.getSlot() == 8) {
                KeyPad.Code.put(player,(ExistingCode+"9"));
                player.playSound(BackUp.get(player), Sound.UI_BUTTON_CLICK, 20,1);
                inventory.setItem(8, new ItemstackFactory("dae461a4434196d37296ad5adf6d9d5744a0415dc61c475a6dfa6285814052", "&f&l9", 9).getItemStack());
            }
            //player.sendMessage(Code.get(player)+" /\\ " +BackUp.get(player).toString());
            if (KeyPad.Code.containsKey(player)) {
                if (KeyPad.Code.get(player).length() > 8) {
                    RegisterCode.remove(player);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BASS, 80, 1);
                    player.closeInventory();
                    player.sendMessage(colorize("&fThe code you entered is to long, keep it under 8."));
                    return;
                }
            }
            return;
        }
    }
}
