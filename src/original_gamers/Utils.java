package net.original_gamers;

import org.bukkit.Location;

public class Utils {
  public static String locToString(Location someLoc) {
    return someLoc.getWorld().getName() 
           + ":" + someLoc.getBlockX() 
           + ":" + someLoc.getBlockY() + someLoc.getBlockZ(); 
  }
}
