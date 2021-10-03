package lucien2714.lucien2714.commands;

import lucien2714.lucien2714.GameStatus.GameStatus;
import lucien2714.lucien2714.GameStatus.player;
import lucien2714.lucien2714.HunterGameForCompetition;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class start implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (GameStatus.gameStatus != GameStatus.gameState.Started) {
            String notReady=new String();
            for (player p : GameStatus.onlinePlayers
            ) {
                if (p.ready != true)
                    notReady+=p.p.getName()+"、";
            }
//            if (notReady.isEmpty()) {
//                GameStatus.gameStatus = GameStatus.gameState.Started;
//                for (World w : GameStatus.worlds
//                ) {
//                    w.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
//                    w.setTime(0);
//                    w.setDifficulty(Difficulty.EASY);
//                }
//                GameStatus.gameStatus= GameStatus.gameState.Loading;
//
//            } else {
//                sendMessageToAll(notReady+"还没有选择完职业！！");
//            }
            if(GameStatus.Challenger.size()==5&&GameStatus.Hunter.size()==15){
                GameStatus.gameStatus=GameStatus.gameState.Loading;
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                GameStatus.gameStatus=GameStatus.gameState.Started;
            }
        } else {
            sendMessageToAll("游戏已开始!请勿重新启动!");
        }
        return true;
    }
    void sendMessageToAll(String msg){
        for (player p:GameStatus.onlinePlayers
             ) {
            p.p.sendMessage(msg);
        }
    }
}
