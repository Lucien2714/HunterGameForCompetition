package lucien2714.lucien2714;

import lucien2714.lucien2714.Events.ChangeCompassTarget;
import lucien2714.lucien2714.Events.chooseTeam;
import lucien2714.lucien2714.Events.loading;
import lucien2714.lucien2714.Events.playerJoin;
import lucien2714.lucien2714.GameStatus.GameStatus;
import lucien2714.lucien2714.GameStatus.player;
import lucien2714.lucien2714.Items.Items;
import lucien2714.lucien2714.commands.debug;
import lucien2714.lucien2714.commands.ready;
import lucien2714.lucien2714.commands.start;
import lucien2714.lucien2714.components.components;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Duration;

public final class HunterGameForCompetition extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Runnable reload = new Runnable() {
            @Override
            public void run() {
                if (GameStatus.gameStatus == GameStatus.gameState.Loading) {
                    for (int timer = 5; timer >= 0; timer--) {
                        for (player temp : GameStatus.onlinePlayers
                        ) {
                            if (temp.ready) {
                                if (timer != 0) {
                                    temp.p.showTitle(
                                            Title.title(
                                                    components.createComponent(timer + "..."),
                                                    components.createComponent(""),
                                                    Title.Times.of(
                                                            Duration.ofSeconds(0),
                                                            Duration.ofSeconds(1),
                                                            Duration.ofSeconds(0)
                                                    )));
                                } else {
                                    temp.p.getInventory().clear();
                                    ItemStack compass = new ItemStack(Material.COMPASS);
                                    temp.p.getInventory().addItem(compass);
                                    GameStatus.getnext(temp);
                                    temp.p.teleport(GameStatus.worlds.get(0).getSpawnLocation());
                                    temp.p.setGameMode(GameMode.SURVIVAL);
                                    if (!temp.isHunter)
                                        temp.p.showTitle(
                                                Title.title(
                                                        components.createComponent("游戏开始"),
                                                        components.createComponent("将那翱翔在天的飞龙击落吧！"),
                                                        Title.Times.of(
                                                                Duration.ofSeconds(1),
                                                                Duration.ofSeconds(2),
                                                                Duration.ofSeconds(1)
                                                        )));
                                }
                            }
                        }
                        if (timer != 0)
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                    }
                    for (int timer = 10; timer >= 0; timer--) {
                        for (player hunter : GameStatus.Hunter
                        ) {

                            hunter.p.showTitle(Title.title(
                                    components.createComponent("游戏开始"),
                                    components.createComponent("您将于" + timer + "秒钟之后苏醒"),
                                    Title.Times.of(
                                            Duration.ofSeconds(0),
                                            Duration.ofSeconds(1),
                                            Duration.ofSeconds(0)
                                    )));
                        }
                        if (timer != 0)
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                    }
                    GameStatus.gameStatus = GameStatus.gameState.Started;
                }
            }
        };
        Runnable compassTarget = () -> {
            if (GameStatus.gameStatus == GameStatus.gameState.Started) {
                for (player p : GameStatus.onlinePlayers
                ) {
                    for (ItemStack item : p.p.getInventory().getContents()) {
                        if (item != null) {
                            if (item.getType() == Material.COMPASS) {
                                //System.out.println("founded compass from player:"+p.p.getName());
                                CompassMeta compass = (CompassMeta) item.getItemMeta();
                                compass.setLodestone(p.compassTarget.p.getLocation());
                                compass.setLodestoneTracked(false);
                                compass.displayName(components.createComponent(
                                        "正在追踪：<" + p.compassTarget.p.getName() + ">",
                                        TextColor.color(255, 0, 0)));
                                item.setItemMeta(compass);
                                //System.out.println("target:"+p.compassTarget.p.getName()+"\nlocaiton:"+p.compassTarget.p.getLocation().getX()+","+p.compassTarget.p.getLocation().getY()+","+p.compassTarget.p.getLocation().getZ());
                            }
                            p.p.updateInventory();
                        }
                    }
                }
            }
        };

        GameStatus.worlds = getServer().getWorlds();
        for (World world : GameStatus.worlds
        ) {
            world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
            world.setTime(0);
            world.setDifficulty(Difficulty.PEACEFUL);
        }
        for (Player temp : getServer().getOnlinePlayers()
        ) {
            temp.setGameMode(GameMode.ADVENTURE);
            temp.setWalkSpeed((float) 0.2);
            GameStatus.onlinePlayerAdd(temp);
        }
        addTeams();
        getCommand("ready").setExecutor(new ready());
        getCommand("start").setExecutor(new start());
        getCommand("debug").setExecutor(new debug());
        getServer().getPluginManager().registerEvents(new ChangeCompassTarget(), this);
        getServer().getPluginManager().registerEvents(new chooseTeam(), this);
        getServer().getPluginManager().registerEvents(new playerJoin(), this);
        getServer().getPluginManager().registerEvents(new loading(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    void addTeams() {
        ItemStack TPer = Items.createItem(Material.LEATHER_HELMET, "传送者", "将一名挑战者传送至附近", true);
        LeatherArmorMeta TPerMeta = (LeatherArmorMeta) TPer.getItemMeta();
        TPerMeta.setColor(Color.BLUE);
        TPer.setItemMeta(TPerMeta);
        GameStatus.team[0] = TPer;

        ItemStack Warrior = Items.createItem(Material.LEATHER_HELMET, "战士", "受到攻击后将有3s无敌时间", true);
        LeatherArmorMeta WarriorMeta = (LeatherArmorMeta) Warrior.getItemMeta();
        WarriorMeta.setColor(Color.RED);
        Warrior.setItemMeta(WarriorMeta);
        GameStatus.team[1] = Warrior;

        ItemStack Doctor = Items.createItem(Material.LEATHER_HELMET, "药师", "每分钟会随机获得一瓶增益buff药水", true);
        LeatherArmorMeta DoctorMeta = (LeatherArmorMeta) Doctor.getItemMeta();
        DoctorMeta.setColor(Color.YELLOW);
        Doctor.setItemMeta(DoctorMeta);
        GameStatus.team[2] = Doctor;

        ItemStack Predator = Items.createItem(Material.LEATHER_HELMET, "掠夺者", "击杀非敌对生物会获得额外掉落物", true);
        LeatherArmorMeta PredatorMeta = (LeatherArmorMeta) Predator.getItemMeta();
        PredatorMeta.setColor(Color.PURPLE);
        Predator.setItemMeta(PredatorMeta);
        GameStatus.team[3] = Predator;

        ItemStack Smith = Items.createItem(Material.LEATHER_HELMET, "工匠", "制作的装备自带附魔", true);
        LeatherArmorMeta SmithMeta = (LeatherArmorMeta) Smith.getItemMeta();
        SmithMeta.setColor(Color.GREEN);
        Smith.setItemMeta(SmithMeta);
        GameStatus.team[4] = Smith;
        for(int a=0;a<5;a++){
            GameStatus.careerSelected[a]=1;
        }
    }
}
