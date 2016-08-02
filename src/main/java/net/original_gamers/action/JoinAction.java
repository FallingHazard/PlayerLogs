package net.original_gamers.action;

import org.bukkit.event.player.PlayerJoinEvent;

public class JoinAction extends PlayerAction {
  private final String description;

  public JoinAction(PlayerJoinEvent event) {
    super(event);
    description = "joined";
  }

  @Override
  public String getDescription() {
    return description;
  }

}
