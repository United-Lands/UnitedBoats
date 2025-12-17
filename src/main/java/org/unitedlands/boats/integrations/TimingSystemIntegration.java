package org.unitedlands.boats.integrations;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.plugin.Plugin;
import org.unitedlands.boats.UnitedBoats;

import me.makkuusen.timing.system.api.TimingSystemAPI;

public class TimingSystemIntegration implements Listener {
    private final UnitedBoats plugin;

    public TimingSystemIntegration(UnitedBoats plugin) {
        this.plugin = plugin;
    }

    public void hookSpawner() {
        plugin.getLogger().info("Hooking into TimingSystem...");
        TimingSystemAPI.setBoatSpawner(new BoatSpawnHandler(plugin));
    }


}
