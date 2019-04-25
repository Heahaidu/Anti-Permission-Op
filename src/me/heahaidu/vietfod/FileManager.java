package me.heahaidu.vietfod;

import org.bukkit.configuration.file.*;
import java.io.*;

public class FileManager {
    public static FileConfiguration sfile;
    public static File whitelist;
    
    private static Main pl = Main.getPlugin(Main.class);
    
    public static void save(final Object o, final File f) {
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(o);
            oos.flush();
            oos.close();
        }
        catch (Exception ex) {}
    }
    
    public static Object load(final File f) {
        try {
            final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            final Object result = ois.readObject();
            ois.close();
            return result;
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public static void checkConfig() {
    	FileManager.whitelist = new File(FileManager.pl.getDataFolder(), "whitelist.yml");
        if (!FileManager.whitelist.exists()) {
            FileManager.whitelist.getParentFile().mkdirs();
            copy(FileManager.pl.getResource("whitelist.yml"), FileManager.whitelist);
        }
        FileManager.sfile = (FileConfiguration)YamlConfiguration.loadConfiguration(new File(FileManager.pl.getDataFolder(), "whitelist.yml"));
        
        FileManager.pl.getConfig().options().copyDefaults(true);
        FileManager.pl.saveConfig();
        FileManager.pl.reloadConfig();
    }
    
    public static void copy(final InputStream in, final File file) {
        try {
            final OutputStream out = new FileOutputStream(file);
            final byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
