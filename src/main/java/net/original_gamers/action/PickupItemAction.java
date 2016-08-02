package net.original_gamers.action;

import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public final class PickupItemAction extends PlayerAction {
  private final String description;
  
  public PickupItemAction(PlayerPickupItemEvent pickEvt) {
    super(pickEvt);
    ItemStack pickedItem = pickEvt.getItem().getItemStack();
    description = "picked Item: " 
                 + pickedItem.getType() 
                 + " name:(" + pickedItem.getItemMeta().getDisplayName() 
                 + ")" + " amount(" + pickedItem.getAmount() + ")";
  }

  @Override
  public String getDescription() {
    return description;
  }

}
