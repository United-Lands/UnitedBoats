package org.unitedlands.boats;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;
import org.unitedlands.boats.handlers.SpawnHandler;
// import org.unitedlands.boats.integrations.TimingSystemIntegration;
import org.unitedlands.utils.United;

@DefaultQualifier(NonNull.class)
public final class UnitedBoats extends JavaPlugin implements Listener {

  private static UnitedBoats instance;
  
  public static UnitedBoats instance() {
    return instance;
  }

  private SpawnHandler spawnHandler;

  @Override
  public void onEnable() {

    instance = this;

    saveDefaultConfig();

    spawnHandler = new SpawnHandler(this);

    United.logger().info("UnitedBoats initialized.");

  }

  public SpawnHandler getSpawnHandler() {
    return spawnHandler;
  }

}
