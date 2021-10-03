package lucien2714.lucien2714.commands;

import lucien2714.lucien2714.GameStatus.GameStatus;
import lucien2714.lucien2714.Items.Items;
import lucien2714.lucien2714.components.components;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ready implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Inventory Gui_1 = Bukkit.createInventory(player, 9, components.createComponent("队伍选择"));

            ItemStack Challenger = Items.createItem(Material.DIAMOND_SWORD, "挑战者", "在3h内击杀末影龙即为胜利！", true);
            ItemStack Defender = Items.createItem(Material.SHIELD, "守卫者", "阻止挑战者!", true);
            if(GameStatus.Challenger.size()<5)
                Gui_1.addItem(Challenger);
            Gui_1.addItem(Defender);
            player.openInventory(Gui_1);

        }

        return true;
    }
}
