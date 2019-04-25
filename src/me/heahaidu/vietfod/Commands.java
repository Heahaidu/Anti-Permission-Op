package me.heahaidu.vietfod;

import java.io.File;

import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
	
	private static Main pl = Main.getPlugin(Main.class);
	
	@Override
    public boolean onCommand(CommandSender s, Command c, String label, String[] args) {
		if (!(s instanceof Player)) {
	        if (c.getName().equalsIgnoreCase("antipo")) {
	            if (args.length >= 1 && args[0].equals("reload")) {
	            	pl.reloadConfig();
	            	FileManager.sfile = (FileConfiguration)YamlConfiguration.loadConfiguration(new File(pl.getDataFolder(), "whitelist.yml"));
	                s.sendMessage(pl.c(pl.getConfig().getString("Message.Reload")));
	                return true;
	            }
	        }
		}
        return true;
    }
	
}
