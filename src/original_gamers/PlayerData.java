package net.original_gamers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.scheduler.BukkitRunnable;

public class PlayerData {
  private final List<String> actions;
  private final Set<String> ipHistory;
  private final UUID playerUID;
  
  private final File ipFile;
  private final File actionFile;
  
  private PrintWriter ipWriter;
  private PrintWriter actionWriter;
  
  private final OGLogsPlugin thePlugin;
  
  public PlayerData(UUID somePlayerUID, OGLogsPlugin plugin) {
    playerUID = somePlayerUID;
    actions = new ArrayList<String>();
    ipHistory = new HashSet<String>();
    
    thePlugin = plugin;
    
    File playerFolder = new File(plugin.getDataFolder(), playerUID.toString());
    if (!playerFolder.exists()) {
      playerFolder.mkdir();
    }
    
    ipFile = new File(playerFolder, "ip-history.txt");
    actionFile = new File(playerFolder, "player-log.txt");
    
    try {
      if (!ipFile.exists()) {
        ipFile.createNewFile();
      }
      if(!actionFile.exists()) {
        actionFile.createNewFile();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    BufferedReader ipReader = null;
    
    try {
      ipReader = new BufferedReader(new FileReader(ipFile));
      actionWriter = new PrintWriter(new FileWriter(actionFile, true));
      ipWriter = new PrintWriter(new FileWriter(ipFile));
      
      String input;
      while ((input = ipReader.readLine()) != null) {
        ipHistory.add(input);
      }
    } catch (IOException e) {
      ipWriter = null;
      actionWriter = null;
      e.printStackTrace();
    }
    finally {
      try {
        if (ipReader != null) {
          ipReader.close();
        }
      }
      catch (IOException exception) {
        System.err.println("Failed to close IP reader.");
      }
    }
  }
  
  public Set<String> addNewIp(String newIp) {
    ipHistory.add(newIp);
    return ipHistory;
  }
  
  public List<String> addNewAction(PlayerAction newAction) {
    actions.add(newAction.toString());
    return actions;
  }
  
  @SuppressWarnings("deprecation")
  public void saveData() {
    for (String action : actions) {
      actionWriter.println(action);
    }
    actions.clear();
  
    for (String ip : ipHistory) {
      ipWriter.println(ip);
    }
    
    thePlugin.getServer().getScheduler().scheduleAsyncDelayedTask(thePlugin, 
                                                                  new BukkitRunnable() {
      
      public void run() {
        synchronized (actionWriter) {
         actionWriter.flush(); 
        }
        
        synchronized (ipWriter) {
         ipWriter.flush(); 
        }
      }
    }, 1L);
  }
  
  public void close() {
      if (ipWriter != null) ipWriter.close();
      if (actionWriter != null) actionWriter.close();
  }
  
}
