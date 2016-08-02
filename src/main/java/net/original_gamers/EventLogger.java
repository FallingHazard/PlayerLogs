package net.original_gamers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventLogger implements Listener {
  private final PlayerDataManager dataManager;
  public EventLogger(PlayerDataManager theDataManager, OGLogsPlugin plugin) {
    dataManager = theDataManager;
    plugin.getServer();
  }
  
  @EventHandler
  public void onLogin(PlayerJoinEvent event) {
    dataManager.registerPlayerAction(new PlayerAction(event));
  }
  
  @EventHandler
  public void onQuit(PlayerQuitEvent event) {
    dataManager.registerPlayerAction(new PlayerAction(event));
  }
  
  @EventHandler
  public void onChat(PlayerChatEvent event) {
    dataManager.registerPlayerAction(new PlayerAction(event));
  }
  
  @EventHandler
  public void onCommand(PlayerCommandPreprocessEvent event) {
    dataManager.registerPlayerAction(new PlayerAction(event));
  }
  
  @EventHandler
  public void onItemDrop(PlayerDropItemEvent event) {
    dataManager.registerPlayerAction(new PlayerAction(event));
  }
  
  @EventHandler
  public void onItemPickUp(PlayerPickupItemEvent event) {
    dataManager.registerPlayerAction(new PlayerAction(event));
  }
  
  @EventHandler
  public void onBookEdit(PlayerEditBookEvent event) {
    dataManager.registerPlayerAction(new PlayerAction(event));
  }

}
