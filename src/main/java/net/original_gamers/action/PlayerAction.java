package net.original_gamers.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import lombok.Getter;
import net.original_gamers.main.Utils;

@SuppressWarnings("deprecation")
public abstract class PlayerAction {
  private final String actionDate;
  private final Location actionLocation;
  private final String playerName;
  
  @Getter private final UUID playerUID;
  @Getter private final String playerIp;
  
  public PlayerAction(PlayerEvent event) {
    Player player = event.getPlayer();

    SimpleDateFormat format = new SimpleDateFormat("[MM/dd] [HH:mm:ss]");
    actionDate = format.format(new Date());
    
    actionLocation = player.getLocation();
    
    playerName = player.getName();
    playerIp = player.getAddress().getHostString().toString();
    playerUID = player.getUniqueId();
  }
  
  public abstract String getDescription();
  
  @Override
  public String toString() {
    return String.format("%s Player %s %s at %s", 
                          actionDate, 
                          playerName,  
                          getDescription(), 
                          Utils.locToString(actionLocation));
  }
}
