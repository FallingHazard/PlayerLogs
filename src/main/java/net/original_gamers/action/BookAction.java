package net.original_gamers.action;

import java.util.List;

import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.event.player.PlayerEvent;

public final class BookAction extends PlayerAction {
  private final String description;

  public BookAction(PlayerEditBookEvent bookEvt) {
    super(bookEvt);
    List<String> bookPages = bookEvt.getNewBookMeta().getPages();
    String bookContent = " ";
    
    for (String page : bookPages) {
      bookContent += page + "\n";
    }
    
    description = bookContent;
  }

  @Override
  public String getDescription() {
    return description;
  }

}
