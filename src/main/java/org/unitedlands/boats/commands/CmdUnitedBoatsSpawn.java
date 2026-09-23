package org.unitedlands.boats.commands;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.unitedlands.annotations.UnitedSubCommand;
import org.unitedlands.boats.UnitedBoats;
import org.unitedlands.registrars.command.UnitedCommandExecutor;
import org.unitedlands.utils.United;

@UnitedSubCommand(parent = CmdUnitedBoats.class, name = "spawn", description = "Spawns a collisionless boat", usage = "/unitedboats spawn <type>")
public class CmdUnitedBoatsSpawn implements UnitedCommandExecutor {

    private List<String> boatTypes = List.of(
            "OAK_BOAT",
            "SPRUCE_BOAT",
            "BIRCH_BOAT",
            "JUNGLE_BOAT",
            "ACACIA_BOAT",
            "DARK_OAK_BOAT",
            "MANGROVE_BOAT",
            "CHERRY_BOAT",
            "BAMBOO_RAFT");

    @Override
    public List<String> handleTab(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return boatTypes;
        }
        return null;
    }

    @Override
    public void handleCommand(CommandSender sender, String[] args) {

        if (args.length != 1) {
            sendUsage(sender);
            return;
        }

        Location spawnLocation = null;
        if (sender instanceof Player player) {
            spawnLocation = player.getLocation();
        } else if (sender instanceof BlockCommandSender blockSender) {
            spawnLocation = blockSender.getBlock().getLocation();
        } else {
            United.messenger().sendRaw(sender, "<red>This command can't be used from console.</red>");
            return;
        }

        if (!boatTypes.contains(args[0])) {
            United.messenger().sendRaw(sender, "<yellow>Invalid boat type.</yellow>");
            return;
        }

        var boat = UnitedBoats.instance().getSpawnHandler().spawnBoat(spawnLocation, args[0]);
        boat.spawnAt(spawnLocation, SpawnReason.CUSTOM);
    }

}
