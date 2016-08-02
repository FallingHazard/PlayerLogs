package net.original_gamers;

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
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import lombok.Getter;

@SuppressWarnings("deprecation")
public class PlayerAction {
  @Getter private final String actionType;
  @Getter private final String actionDate;
  @Getter private final Location actionLocation;
  @Getter private final String playerName;
  @Getter private final String playerIp;
  @Getter private final UUID playerUID;
  
  public PlayerAction(PlayerEvent event) {
    Player player = event.getPlayer();
    
    if (event instanceof PlayerJoinEvent) {
      actionType = "joined";
    } else if (event instanceof PlayerQuitEvent) {
      actionType = "quit";
    } else if (event instanceof PlayerCommandPreprocessEvent) {
      PlayerCommandPreprocessEvent cmdEvent = (PlayerCommandPreprocessEvent) event;
      actionType = "executed Command: " + cmdEvent.getMessage();
    } else if (event instanceof PlayerDropItemEvent) {
      PlayerDropItemEvent dropEvent = (PlayerDropItemEvent) event;
      ItemStack droppedItem = dropEvent.getItemDrop().getItemStack();
      actionType = "dropped Item: " 
                   + droppedItem.getType() 
                   + " name:(" + droppedItem.getItemMeta().getDisplayName() 
                   + ")" + " amount(" + droppedItem.getAmount() + ")";
    } else if (event instanceof PlayerChatEvent) {
      PlayerChatEvent chatEvnt = (PlayerChatEvent) event;
      actionType = "said: " + chatEvnt.getMessage();
    } else if (event instanceof PlayerEditBookEvent) {
      PlayerEditBookEvent bookEvt = (PlayerEditBookEvent) event;
      List<String> bookPages = bookEvt.getNewBookMeta().getPages();
      String bookContent = " ";
      
      for (String page : bookPages) {
        bookContent += page + "\n";
      }
      
      actionType = bookContent;
    } else {
      actionType = "unknown action";
    }
    
    SimpleDateFormat format = new SimpleDateFormat("[MM/dd] [HH:mm:ss]");
    actionDate = format.format(new Date());
    actionLocation = player.getLocation();
    playerName = player.getName();
    playerIp = player.getAddress().toString();
    playerUID = player.getUniqueId();
  }
  
  @Override
  public String toString() {
    return String.format("%s Player %s\\%s %s at %s on %s", 
                          actionDate, 
                          playerName, 
                          playerUID, 
                          actionType, 
                          Utils.locToString(actionLocation), playerIp);
  }
}
