package org.unitedlands.boats.commands;

import org.bukkit.command.CommandSender;
import org.unitedlands.annotations.UnitedSubCommand;
import org.unitedlands.boats.UnitedBoats;
import org.unitedlands.registrars.command.UnitedCommandExecutor;
import org.unitedlands.utils.United;

@UnitedSubCommand (
    parent = CmdUnitedBoats.class,
    name = "reload",
    description = "UnitedBoats config reload"
)
public class CmdUnitedBoatsReload implements  UnitedCommandExecutor {

    @Override
    public void handleCommand(CommandSender sender, String[] args) {
        UnitedBoats.instance().reloadConfig();
        United.messenger().sendRaw(sender, "<dark_green>Config reloaded</dark_green>");
    }

}
