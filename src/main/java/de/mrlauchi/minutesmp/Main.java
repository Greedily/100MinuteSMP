package de.mrlauchi.minutesmp;

import de.mrlauchi.minutesmp.commands.ResetTimeCommand;
import de.mrlauchi.minutesmp.commands.SetEndCommand;
import de.mrlauchi.minutesmp.commands.updateScoreboardCommand;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Set;

public final class Main extends JavaPlugin {

    public boolean end;

    public int time;

    private static Main plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        FileConfiguration config = getConfig();

        getCommand("resettime").setExecutor(new ResetTimeCommand());
        getCommand("setend").setExecutor(new SetEndCommand());
        getCommand("setend").setTabCompleter(new SetEndCommand());
        getCommand("updatescoreboard").setExecutor(new updateScoreboardCommand());

        plugin = this;

        new BukkitRunnable() {
            @Override
            public void run() {
                for(Player player : Bukkit.getServer().getOnlinePlayers()) {

                    int oldtime = (int) config.get("time");

                    time = oldtime - 1;

                    config.set("time", time);

                    saveConfig();

                    updateScoreboardForPlayer(player);

                }
            }
        }.runTaskTimer(this, 20*60, 20*60);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public void updateScoreboardForPlayer(Player player) {
        Scoreboard sc = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective scobjective = sc.registerNewObjective("main", "main", "§bWelcome to the 100 Minute SMP");
        scobjective.setDisplaySlot(DisplaySlot.SIDEBAR);

        int scoretime = (int) getConfig().get("time");
        int indivpoints = 0;
        int teampoints = 0;

        if(end == false) {
            scobjective.getScore("§2Time left: " + scoretime + " minutes").setScore(11);

            scobjective.getScore("").setScore(10);
            scobjective.getScore("Tasks: ").setScore(9);
            scobjective.getScore("Task 1").setScore(8);
            scobjective.getScore("Task 2").setScore(7);
            scobjective.getScore("Task 3").setScore(6);
            scobjective.getScore(" ").setScore(5);
        }

        scobjective.getScore("Team Points: " + teampoints).setScore(4);
        scobjective.getScore("Your Points: " + indivpoints).setScore(3);

        scobjective.getScore("  ").setScore(2);
        scobjective.getScore("Go Check Out berrybyte.net").setScore(1);
        scobjective.getScore("Follow @100MinSMP On Twitter").setScore(0);

        player.setScoreboard(sc);
        //Set<Objective> test = sc.getObjectives();
    }

    public void updateScoreboardForAllPlayer() {
        for(Player loopplayer : Bukkit.getServer().getOnlinePlayers()) {
            updateScoreboardForPlayer(loopplayer);
        }
    }

    public static Main getInstance() {
        return plugin;
    }

}
