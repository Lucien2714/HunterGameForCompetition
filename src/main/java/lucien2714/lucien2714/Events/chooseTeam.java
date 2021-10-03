package lucien2714.lucien2714.Events;

import lucien2714.lucien2714.GameStatus.GameStatus;
import lucien2714.lucien2714.GameStatus.player;
import lucien2714.lucien2714.Items.Items;
import lucien2714.lucien2714.components.components;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class chooseTeam implements Listener {
    @EventHandler
    void GuiOptions(InventoryClickEvent e) {//gets the item player chose
        player player = GameStatus.getPlayer((Player) e.getWhoClicked());

        if (e.getView().title().equals(Component.text("队伍选择"))) {
            switch (Objects.requireNonNull(e.getCurrentItem()).getType()) {
                case DIAMOND_SWORD:
                    if (GameStatus.Challenger.size() < 5) {
                        player.isHunter = false;
                        Inventory Gui_2 = Bukkit.createInventory(player.p, 9, components.createComponent("职业选择"));
                        for (int a = 0; a < 5; a++) {
                            if (GameStatus.careerSelected[a] == 1) {
                                Gui_2.setItem(a, GameStatus.team[a]);
                            }
                        }
                        player.p.openInventory(Gui_2);
                    }
                    break;
                case SHIELD:
                    if (GameStatus.Hunter.size() < 15) {
                        if (!GameStatus.Hunter.contains(player)) {
                            if (GameStatus.Challenger.contains(player)) {
                                GameStatus.Challenger.remove(player);
                                GameStatus.careerSelected[player.career] = 1;
                            }
                            player.career = -1;
                            player.isHunter = true;
                            GameStatus.Hunter.add(player);
                            player.ready = true;
                            player.p.closeInventory();
                            System.out.println(player.p.getName() + "选择了防卫者");
                            break;
                        }
                        else player.p.closeInventory();
                    }
            }
        } else if (e.getView().title().equals(Component.text("职业选择"))) {
            //ItemStack item=e.getCurrentItem();
            int temp = e.getSlot();
            if (0 <= temp && temp <= 4) {
                if (GameStatus.careerSelected[temp]==1) {
                    player.p.closeInventory();
                    if(GameStatus.Challenger.contains(player)){
                        GameStatus.careerSelected[player.career]=1;
                        GameStatus.Challenger.remove(player);
                    }
                    GameStatus.Challenger.add(player);
                    player.career = temp;
                    GameStatus.Hunter.remove(player);
                    GameStatus.careerSelected[temp] = 0;
                    player.p.sendMessage(components.createComponent("您已选择 ").append(GameStatus.team[temp].getItemMeta().displayName()).append(components.createComponent("职业")));
                    player.ready = true;
                } else {
                    player.p.closeInventory();
                    player.p.sendMessage("您选的职业已被抢,请输入/ready来重新选择");
                }
            }
        }
    }
}
