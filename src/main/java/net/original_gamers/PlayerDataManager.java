package net.original_gamers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.entity.Player;
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
    Iterator<Entry<UUID, PlayerData>> pDataIterator = playerDataMap.entrySet().iterator();
    while (pDataIterator.hasNext()) {
      Entry<UUID, PlayerData> pDataEntry = pDataIterator.next();
      
      UUID playerUID = pDataEntry.getKey();
      PlayerData playerData = pDataEntry.getValue();
      
      playerData.saveData();
      Player player = plugin.getServer().getPlayer(playerUID);
   
      if (player == null) {
        playerData.close();
        pDataIterator.remove();
      }
    }
  }
  
  public void shutDown() {
    for (PlayerData someData : playerDataMap.values()) {
      someData.close();
    }
  }
}