package net.original_gamers.action;

import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public final class DropItemAction extends PlayerAction {
  private final String description;

  public DropItemAction(PlayerDropItemEvent dropEvent) {
    super(dropEvent);
    ItemStack droppedItem = dropEvent.getItemDrop().getItemStack();
    description = "dropped Item: " 
        + droppedItem.getType() 
        + " name:(" + droppedItem.getItemMeta().getDisplayName() 
        + ")" + " amount(" + droppedItem.getAmount() + ")";
  }

  @Override
  public String getDescription() {
    return description;
  }

}
