package me.heahaidu.vietfod;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {
	
	private static Main pl;
	
	@Override
	public void onEnable() {
		pl = this;
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Plugin da duoc kick hoat");
		FileManager.checkConfig();
        getServer().getPluginCommand("antipo");
		getCommand("antipo").setExecutor(new Commands());
		getServer().getPluginManager().registerEvents(new Listener(), this);
	}
	
	@Override
	public void onDisable() {
	}
	
	public static Plugin getInstance() {
		return pl;
	}
	
    public String c(String s) {
        s = ChatColor.translateAlternateColorCodes('&', s);
        return s;
    }

}
