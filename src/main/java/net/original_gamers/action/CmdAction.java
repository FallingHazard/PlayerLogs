package net.original_gamers.action;

import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public final class CmdAction extends PlayerAction {
  private final String description;

  public CmdAction(PlayerCommandPreprocessEvent event) {
    super(event);
    description = "executed Command: " + event.getMessage();
  }

  @Override
  public String getDescription() {
    return description;
  }

}
