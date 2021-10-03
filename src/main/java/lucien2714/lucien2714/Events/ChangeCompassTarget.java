package lucien2714.lucien2714.Events;

import lucien2714.lucien2714.GameStatus.GameStatus;
import lucien2714.lucien2714.GameStatus.player;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;
import lucien2714.lucien2714.components.components;
public class ChangeCompassTarget implements Listener {
    @EventHandler
    public void change(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (p.getItemInUse().getType() == Material.COMPASS) {
            System.out.println("Changing target player:"+e.getPlayer());
            player pl = null;
            for (player temp : GameStatus.onlinePlayers
            ) {
                if (temp.p == p)
                    pl = temp;
            }
            if (GameStatus.onlinePlayers.size() > 1) {
                GameStatus.getnext(pl);
                ItemMeta compass = p.getItemInUse().getItemMeta();
                compass.displayName(components.createComponent(
                        "正在追踪：<" + GameStatus.onlinePlayers.get(GameStatus.onlinePlayers.indexOf(pl)).compassTarget.p.getName() + ">",
                        TextColor.color(255, 0, 0)));

            } else {
                ItemMeta compass = p.getItemInUse().getItemMeta();
                compass.displayName(components.createComponent(
                        "游戏中只有您一个人！",
                        TextColor.color(255, 0, 0)));

            }

        }
    }

}
