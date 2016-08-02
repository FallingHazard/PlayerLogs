package net.original_gamers.action;

import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public final class QuitAction extends PlayerAction {
  private final String description;

  public QuitAction(PlayerQuitEvent event) {
    super(event);
    description = "quit";
  }

  @Override
  public String getDescription() {
    return description;
  }

}
