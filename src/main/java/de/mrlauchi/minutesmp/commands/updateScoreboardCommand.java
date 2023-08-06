package de.mrlauchi.minutesmp.commands;

import de.mrlauchi.minutesmp.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class updateScoreboardCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();

            if(player.hasPermission("scoreboard.update")) {
                Main.getInstance().updateScoreboardForAllPlayer();
            }
            else {
                sender.sendMessage("§4No Permissions");
            }
        }
        return false;
    }
}
