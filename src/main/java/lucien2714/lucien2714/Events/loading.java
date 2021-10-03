package lucien2714.lucien2714.Events;

import lucien2714.lucien2714.GameStatus.GameStatus;
import lucien2714.lucien2714.GameStatus.player;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class loading implements Listener {
    @EventHandler
    void playerAction(PlayerMoveEvent e) {
        if (GameStatus.gameStatus == GameStatus.gameState.Loading) {
            player p = GameStatus.getPlayer(e.getPlayer());
            if (p.isHunter) {
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    void playerATK(EntityDamageByEntityEvent e) {
        if (GameStatus.gameStatus == GameStatus.gameState.Loading) {
            if (e.getEntityType() == EntityType.PLAYER||e.getDamager().getType()==EntityType.PLAYER) {
                player p1=null;
                player p2=null;
                if(e.getEntityType()==EntityType.PLAYER)
                    p1 = GameStatus.getPlayer((Player) e.getEntity());
                else
                    p2=GameStatus.getPlayer((Player) e.getDamager());
                if (p1!=null) {
                    if(p1.isHunter)
                        e.setCancelled(true);
                }
                else if(p2!=null){
                    if(p2.isHunter)
                        e.setCancelled(true);
                }
            }
        }
    }
}
