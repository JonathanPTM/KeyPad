package me.ptmdevelopment.net.keypad.resources;

import me.ptmdevelopment.net.keypad.resources.inventory.KeyPad;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static me.ptmdevelopment.net.keypad.KeyPad.locationRes;

public class BlockGUI {
    private Location location;

    public BlockGUI(Location location) {this.location = location;}

    public void create() {
        API.BlockGui.set(locationRes(this.location), true);
        API.saveMachines();
    }

    public void destroy() {
        API.BlockGui.set(locationRes(this.location), null);
        API.saveMachines();
    }

    public void open(Player player) {
        KeyPad keyPad = new KeyPad(this.location, player);
        keyPad.openInterface();
    }

    public boolean bestaat() {
        if (API.BlockGui.contains(locationRes(this.location))) {
            return API.BlockGui.getBoolean(locationRes(this.location));
        }
        return false;
    }
}
