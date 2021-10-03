package lucien2714.lucien2714.commands;

import lucien2714.lucien2714.GameStatus.GameStatus;
import lucien2714.lucien2714.GameStatus.player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class debug implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        String hunters="";
        for (player p: GameStatus.Hunter
             ) {
            if(!hunters.isEmpty())
                hunters+=",";
            hunters+=p.p.getName();
        }
        System.out.println("Hunter:"+hunters);
        String challenger="";
        for (player p: GameStatus.Challenger
        ) {
            if(!challenger.isEmpty())
                challenger+=",";
            challenger+=p.p.getName();
        }
        System.out.println("Challenger:"+challenger);
        return true;
    }
}
