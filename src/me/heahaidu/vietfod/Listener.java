package me.heahaidu.vietfod;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listener implements org.bukkit.event.Listener {
	
	private static Main pl = Main.getPlugin(Main.class);
	public static ArrayList<Player> pj = new ArrayList<Player>();
	

	@EventHandler (priority = EventPriority.MONITOR)
	public void onJoin(PlayerJoinEvent e) {		
		Player p = e.getPlayer();


		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
			public void run() {
				if (pl.getConfig().getBoolean("whitelist")) {	
					if (p.isOp() && !FileManager.sfile.getStringList("whitelist").contains(p.getName())) {
						Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), 
								pl.getConfig().getString("Command").replace("%player%", p.getName()));
						if (!p.isOnline()) {
							return;
						}
					}
					if (!FileManager.sfile.getStringList("whitelist").contains(p.getName())
						&& pl.getConfig().getBoolean("AntiPerm.enable")) {
				        List<String> permission = pl.getConfig().getStringList("AntiPerm.Perm");
				        for (String perm : permission) {
				    		if (p.hasPermission(perm)) {
								Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), 
										pl.getConfig().getString("Command").replace("%player%", p.getName()));
								if (!p.isOnline()) {
									return;
								}
				    		}
				        }
					}
				}
    		}
    	}, 20L, 20L);
	}
}
