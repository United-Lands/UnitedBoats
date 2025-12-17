package org.unitedlands.boats.integrations;

import org.bukkit.Location;
import org.bukkit.entity.Boat;
import org.unitedlands.boats.UnitedBoats;

import me.makkuusen.timing.system.boat.BoatSpawner;

public class BoatSpawnHandler implements BoatSpawner {

    private final UnitedBoats plugin;

    public BoatSpawnHandler(UnitedBoats plugin) {
        this.plugin = plugin;
    }

    @Override
    public Boat spawnBoat(Location location, String woodType, Boolean chestBoat) {

        plugin.getLogger().info("Spawning collisionless boat for TimingSystem...");
        
        String boatType = null;
        
        switch (woodType) {
            case "ACACIA" -> {
                boatType = chestBoat ? "ACACIA_CHEST_BOAT" : "ACACIA_BOAT";
            }
            case "BIRCH" -> {
                boatType = chestBoat ? "BIRCH_CHEST_BOAT" : "BIRCH_BOAT";
            }
            case "DARK_OAK" -> {
                boatType = chestBoat ? "DARK_OAK_CHEST_BOAT" : "DARK_OAK_BOAT";
            }
            case "SPRUCE" -> {
                boatType = chestBoat ? "SPRUCE_CHEST_BOAT" : "SPRUCE_BOAT";
            }
            case "JUNGLE" -> {
                boatType = chestBoat ? "JUNGLE_CHEST_BOAT" : "JUNGLE_BOAT";
            }
            case "MANGROVE" -> {
                boatType = chestBoat ? "MANGROVE_CHEST_BOAT" : "MANGROVE_BOAT";
            }
            case "CHERRY" -> {
                boatType = chestBoat ? "CHERRY_CHEST_BOAT" : "CHERRY_BOAT";
            }
            case "BAMBOO" -> {
                boatType = chestBoat ? "BAMBOO_CHEST_RAFT" : "BAMBOO_RAFT";
            }
            default -> {
                boatType = chestBoat ? "OAK_CHEST_BOAT" : "OAK_BOAT";
            }
        }

        return plugin.getSpawnHandler().spawnBoat(location, boatType);
    }

}
