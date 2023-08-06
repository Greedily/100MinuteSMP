package de.mrlauchi.minutesmp.commands;

import de.mrlauchi.minutesmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ResetTimeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.hasPermission("time.reset")) {

            FileConfiguration config = Main.getInstance().getConfig();

            config.set("time", 100);
            Main.getInstance().time = 100;

            sender.sendMessage("§aTime has been reset!");

            Main.getInstance().saveConfig();

            Main.getInstance().updateScoreboardForAllPlayer();

        }
        else {
            sender.sendMessage("§4No Permissions");
        }

        return false;
    }
}
