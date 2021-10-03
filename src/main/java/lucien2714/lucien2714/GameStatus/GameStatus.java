package lucien2714.lucien2714.GameStatus;


import lucien2714.lucien2714.HunterGameForCompetition;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class GameStatus {
    public enum gameState {
        Waiting,
        Loading,
        Started,
        Stopped,
        Ended
    }//check the game status
    public static List<World> worlds = new ArrayList<World>();
    public static gameState gameStatus = gameState.Waiting;
    public static List<player> onlinePlayers = new ArrayList<player>();
    public static List<player> Challenger = new ArrayList<player>();
    public static List<player> Hunter = new ArrayList<player>();
    public static ItemStack[] team=new ItemStack[5];
    public static int[] careerSelected=new int[5];

    public static void onlinePlayerAdd(Player p) {
        player temp = new player();
        temp.p = p;
        temp.career = 0;
        onlinePlayers.add(temp);
    }
    public static void ChangeGameState(gameState state){
        switch (state){
            case Loading:

        }
    }
    public static void getnext(player p) {
        int pIndex = GameStatus.Challenger.indexOf(p);//The player whose target is going to change
        int index=-1;
        if (p.compassTarget == null) {
            for (player temp : GameStatus.Challenger
            ) {
                if (temp != p) {
                    index = GameStatus.Challenger.indexOf(temp);
                    break;
                }
            }
        } else {
            index = GameStatus.Challenger.indexOf(p.compassTarget);//The origin target index
            int OnlinePlayerNum = GameStatus.Challenger.size();
            if (OnlinePlayerNum < 1) {
            } else {
                if (index + 1 == OnlinePlayerNum) {
                    if (pIndex == 0) {
                        index = 1;
                    }
                    else index=0;
                } else {
                    if (index + 1 == pIndex) {
                        if (index + 2 == OnlinePlayerNum) {
                            index = 0;
                        } else
                            index += 2;

                    } else index += 1;
                }
            }
        }
        if(index!=-1)
            GameStatus.Challenger.get(pIndex).compassTarget = GameStatus.Challenger.get(index);
        else{
            GameStatus.Challenger.get(pIndex).compassTarget=null;
        }

    }
    public static player getPlayer(Player p){
        for (player pl:onlinePlayers
             ) {
            if(pl.p==p)
                return pl;
        }
        return null;
    }
}
