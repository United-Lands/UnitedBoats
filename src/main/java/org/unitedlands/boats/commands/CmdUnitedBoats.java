package org.unitedlands.boats.commands;

import org.bukkit.command.CommandSender;
import org.unitedlands.annotations.UnitedCommand;
import org.unitedlands.registrars.command.UnitedCommandExecutor;

@UnitedCommand (
    name = "unitedboats",
    description = "UnitedBoats commands",
    permission = "united.boats.admin"
)
public class CmdUnitedBoats implements UnitedCommandExecutor {

    @Override
    public void handleCommand(CommandSender sender, String[] args) { }

}
