package lucien2714.lucien2714.Events;

import lucien2714.lucien2714.GameStatus.GameStatus;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class playerJoin implements Listener {
    @EventHandler
    void playerJoin(PlayerJoinEvent p){
        p.getPlayer().setGameMode(GameMode.ADVENTURE);
        GameStatus.onlinePlayerAdd(p.getPlayer());
    }
}