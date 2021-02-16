package me.ptmdevelopment.net.keypad.resources;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class API {
    public Plugin plugin;
    public static FileConfiguration BlockGui, Keypads;
    public static File BlockGuiFile, KeypadsFile;

    public static void setup(Plugin plugin){
        File dataDir = new File(plugin.getDataFolder() + "");
        if (!dataDir.exists()){
            try {
                dataDir.mkdir();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        KeypadsFile = new File(plugin.getDataFolder(), "database.yml");
        if (!KeypadsFile.exists()){
            try {
                KeypadsFile.createNewFile();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        Keypads = YamlConfiguration.loadConfiguration(KeypadsFile);

        BlockGuiFile = new File(plugin.getDataFolder(), "keypads.yml");
        if (!BlockGuiFile.exists()){
            try {
                BlockGuiFile.createNewFile();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        BlockGui = YamlConfiguration.loadConfiguration(BlockGuiFile);
    }
    public static void saveMachines(){
        try {
            BlockGui.save(BlockGuiFile);
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            Keypads.save(KeypadsFile);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
