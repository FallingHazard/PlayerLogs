package net.original_gamers.main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.original_gamers.action.BookAction;
import net.original_gamers.action.ChatAction;
import net.original_gamers.action.CmdAction;
import net.original_gamers.action.DropItemAction;
import net.original_gamers.action.JoinAction;
import net.original_gamers.action.PickupItemAction;
import net.original_gamers.action.PlayerAction;
import net.original_gamers.action.QuitAction;

public class EventLogger implements Listener {
  private final PlayerDataManager dataManager;
  public EventLogger(PlayerDataManager theDataManager, OGLogsPlugin plugin) {
    dataManager = theDataManager;
    plugin.getServer();
  }
  
  @EventHandler
  public void onLogin(PlayerJoinEvent event) {
    dataManager.registerPlayerAction(new JoinAction(event));
  }
  
  @EventHandler
  public void onQuit(PlayerQuitEvent event) {
    dataManager.registerPlayerAction(new QuitAction(event));
  }
  
  @EventHandler
  public void onChat(PlayerChatEvent event) {
    dataManager.registerPlayerAction(new ChatAction(event));
  }
  
  @EventHandler
  public void onCommand(PlayerCommandPreprocessEvent event) {
    dataManager.registerPlayerAction(new CmdAction(event));
  }
  
  @EventHandler
  public void onItemDrop(PlayerDropItemEvent event) {
    dataManager.registerPlayerAction(new DropItemAction(event));
  }
  
  @EventHandler
  public void onItemPickUp(PlayerPickupItemEvent event) {
    dataManager.registerPlayerAction(new PickupItemAction(event));
  }
  
  @EventHandler
  public void onBookEdit(PlayerEditBookEvent event) {
    dataManager.registerPlayerAction(new BookAction(event));
  }

}
