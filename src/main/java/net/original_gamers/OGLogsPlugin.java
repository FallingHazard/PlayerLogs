package net.original_gamers;

import org.bukkit.plugin.java.JavaPlugin;

public class OGLogsPlugin extends JavaPlugin {
  private final FileSystem fileSystem = new FileSystem(this);
  private PlayerDataManager manager;
  
  @Override
  public void onEnable() {
    manager = new PlayerDataManager(this);
    this.getServer()
     .getPluginManager()
      .registerEvents(new EventLogger(manager, this), this);
    manager.runTaskTimer(this, 0L, 36000L);
    
    this.getCommand("logsave").setExecutor(new SaveCommand(manager));
  }
  
  @Override
  public void onDisable() {
    manager.shutDown();
  }
  
}
