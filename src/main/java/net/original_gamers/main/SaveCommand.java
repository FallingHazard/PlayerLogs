package net.original_gamers.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SaveCommand implements CommandExecutor{
  private final PlayerDataManager dataManager;

  public SaveCommand(PlayerDataManager manager) {
    dataManager = manager;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command arg1, String arg2,
      String[] arg3) {
    if (sender.isOp()) {
      dataManager.run();
    }
    return true;
  }

}
