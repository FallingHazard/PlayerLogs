package net.original_gamers.action;

import org.bukkit.event.player.PlayerChatEvent;

@SuppressWarnings("deprecation")
public final class ChatAction extends PlayerAction {
  private final String description;

  public ChatAction(PlayerChatEvent event) {
    super(event);
    description = "said: " + event.getMessage();
  }

  @Override
  public String getDescription() {
    return description;
  }

}
