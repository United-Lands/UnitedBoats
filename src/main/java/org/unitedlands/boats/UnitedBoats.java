package org.unitedlands.boats;

import net.minecraft.SharedConstants;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.AbstractBoat;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftBoat;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPlaceEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;
import org.unitedlands.boats.handlers.SpawnHandler;
import org.unitedlands.boats.integrations.TimingSystemIntegration;

@DefaultQualifier(NonNull.class)
public final class UnitedBoats extends JavaPlugin implements Listener {

  private SpawnHandler spawnHandler;

  @Override
  public void onEnable() {

    this.getServer().getPluginManager().registerEvents(this, this);
    this.getLogger().info(SharedConstants.getCurrentVersion().id());

    saveDefaultConfig();

    spawnHandler = new SpawnHandler(this);

    getLogger().info("UnitedBoats initialized.");

  }

  @EventHandler
  private void onServerLoad(ServerLoadEvent event) {
    getLogger().info("Server loaded, checking for integrations...");
    Plugin timingSystem = Bukkit.getPluginManager().getPlugin("TimingSystem");
    if (timingSystem != null && timingSystem.isEnabled()) {
      getLogger().info("TimingSystem found...");
      TimingSystemIntegration tsi = new TimingSystemIntegration(this);
      tsi.hookSpawner();
    }
  }

  // Cancel the placement of boats, and instead spawn one of our Collisionless
  // ones.
  @EventHandler
  public void onEntityPlace(EntityPlaceEvent entityPlaceEvent) {
    if (entityPlaceEvent.getEntity() instanceof CraftBoat) {
      Boat boat = (Boat) entityPlaceEvent.getEntity();

      AbstractBoat abstractBoat = ((CraftBoat) boat).getHandle();

      EntityType<?> type = abstractBoat.getType();

      spawnHandler.spawnBoat(boat.getLocation(), type);

      Player player = entityPlaceEvent.getPlayer();
      assert player != null;
      PlayerInventory inventory = player.getInventory();

      if (player.getGameMode() != GameMode.CREATIVE) {
        if (entityPlaceEvent.getHand() == EquipmentSlot.HAND) {
          inventory.setItemInMainHand(null);
        } else if (entityPlaceEvent.getHand() == EquipmentSlot.OFF_HAND) {
          inventory.setItemInOffHand(null);
        }
      }

      // Prevent the real boat from spawning
      entityPlaceEvent.setCancelled(true);
    }
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (command.getName().equalsIgnoreCase("noboatlag")) {
      if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
        reloadConfig();
        sender.sendMessage("noboatlag: Configuration reloaded.");
        return true;
      } else {
        sender.sendMessage("Usage: /noboatlag reload");
        return true;
      }
    }

    return false;
  }

  public SpawnHandler getSpawnHandler() {
    return spawnHandler;
  }

}
