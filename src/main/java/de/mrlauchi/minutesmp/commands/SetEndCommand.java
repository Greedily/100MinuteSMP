package de.mrlauchi.minutesmp.commands;

import de.mrlauchi.minutesmp.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SetEndCommand implements CommandExecutor , TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();

            if(player.hasPermission("end.state")) {
                if(args.length == 1) {
                    FileConfiguration config = Main.getInstance().getConfig();
                    config.set("end", Boolean.valueOf(args[0]));
                    Main.getInstance().end = Boolean.parseBoolean(args[0]);

                    Main.getInstance().saveConfig();

                    Main.getInstance().updateScoreboardForAllPlayer();

                    player.sendMessage(String.valueOf(Main.getInstance().end));
                    player.sendMessage(String.valueOf(config.get("end")));
                }
            }
            else {
                sender.sendMessage("§4No Permissions");
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> tabComplete = new ArrayList<String>();
        tabComplete.add("true");
        tabComplete.add("false");


        return tabComplete;
    }
}
