package me.ptmdevelopment.net.keypad.resources;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
public class Cooldown {

    private static Map<String, Cooldown> cooldowns = new HashMap<String, Cooldown>();
    private long start;
    private final int timeInSeconds;
    private final UUID id;
    private final String cooldownName;

    public Cooldown(UUID id, String cooldownName, int timeInSeconds){
        this.id = id;
        this.cooldownName = cooldownName;
        this.timeInSeconds = timeInSeconds;
    }

    public static boolean isInCooldown(UUID id, String cooldownName){
        if(getTimeLeft(id, cooldownName) >= 1){
            return true;
        } else {
            if(getCooldown(id, cooldownName)!=null){
                stop(id, cooldownName);
            }
            return false;
        }
    }

    public static boolean isInCooldown(UUID id, String cooldownName, boolean stop){
        if(getTimeLeft(id, cooldownName) >= 1){
            return true;
        } else {
            if (stop) stop(id, cooldownName);
            return false;
        }
    }

    public static void stop(UUID id, String cooldownName){
        Cooldown.cooldowns.remove(id+cooldownName);
    }

    private static Cooldown getCooldown(UUID id, String cooldownName){
        return cooldowns.get(id.toString()+cooldownName);
    }

    public static int getTimeLeft(UUID id, String cooldownName){
        Cooldown cooldown = getCooldown(id, cooldownName);
        int f = 0;
        if(cooldown!=null){
            long now = System.currentTimeMillis();
            long cooldownTime = cooldown.start;
            int totalTime = cooldown.timeInSeconds;
            int r = (int) (now - cooldownTime) / 1000;
            f = (r - totalTime) * (-1);
        }
        return f;
    }

    public void start(){
        this.start = System.currentTimeMillis();
        cooldowns.put(this.id.toString()+this.cooldownName, this);
    }

}
