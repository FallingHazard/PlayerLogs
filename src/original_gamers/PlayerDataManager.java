package net.original_gamers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerDataManager extends BukkitRunnable {
  private Map<UUID, PlayerData > playerDataMap;
  private final OGLogsPlugin plugin;
  
  public PlayerDataManager(OGLogsPlugin somePlugin) {
    plugin = somePlugin;
    playerDataMap = new HashMap<UUID, PlayerData>();
  }

  public void registerPlayerAction(PlayerAction action) {
    UUID joinerUID = action.getPlayerUID();
    
    PlayerData joinerData;
    if ((joinerData = playerDataMap.get(joinerUID)) == null) {
      joinerData = new PlayerData(joinerUID, plugin);
    }
    
    joinerData.addNewIp(action.getPlayerIp());
    joinerData.addNewAction(action);
    playerDataMap.put(joinerUID, joinerData);
  }

  public void run() {
    for (PlayerData someData : playerDataMap.values()) {
      someData.saveData();
    }
  }
  
  public void shutDown() {
    for (PlayerData someData : playerDataMap.values()) {
      someData.close();
    }
  }
}
