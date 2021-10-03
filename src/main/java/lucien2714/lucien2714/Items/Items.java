package lucien2714.lucien2714.Items;

import lucien2714.lucien2714.components.components;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Items {
    public static ItemStack createItem(Material m,String displayName,String description,TextColor a , TextColor b,boolean HIDE_ATTRIBUTES){
        ItemStack i=new ItemStack(m);
        ItemMeta iMeta=i.getItemMeta();
        iMeta.displayName(components.createComponent(displayName, a));
        List<Component> ChallengerDetail=new ArrayList<>();
        ChallengerDetail.add(components.createComponent(description,b));
        iMeta.lore(ChallengerDetail);
        if(HIDE_ATTRIBUTES)
            iMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        i.setItemMeta(iMeta);
        return i;
    }
    public static ItemStack createItem(Material m,String displayName,String description,boolean HIDE_ATTRIBUTES){
        ItemStack i=new ItemStack(m);
        ItemMeta iMeta=i.getItemMeta();
        iMeta.displayName(components.createComponent(displayName, TextColor.color(227, 23, 13)));
        List<Component> ChallengerDetail=new ArrayList<>();
        ChallengerDetail.add(components.createComponent(description,TextColor.color(25, 90, 200)));
        iMeta.lore(ChallengerDetail);
        if(HIDE_ATTRIBUTES)
            iMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        i.setItemMeta(iMeta);
        return i;
    }

}
