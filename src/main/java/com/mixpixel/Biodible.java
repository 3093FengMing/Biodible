package com.mixpixel;

import com.mixpixel.config.ConfigData;
import com.mixpixel.config.Recipe;
import com.mixpixel.gui.LevelUpGuiListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Biodible extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        System.out.println("Biodible for MixPixel has loaded successfully.");
        Bukkit.getPluginCommand("biodible").setExecutor(new BiodibleCommand()); // config.yml已经配置，断言非null
        Bukkit.getPluginManager().registerEvents(new LevelUpGuiListener(), this);
        //以下为意义不明部分：导入Config.yml
        //System.out.println("已导入的配方原料：(For Debug Use)"+ConfigData.nameList);
        //System.out.println("已导入的配方产物：(For Debug Use)"+ConfigData.winItemNameList);
        //System.out.println("已导入的产物名称：(For Debug Use)"+ConfigData.winItemLoreList);
        FileConfiguration config = getConfig();
        loadConfig(config);
    }

    public void reloadConfiguration() {
        this.reloadConfig();
        loadConfig(getConfig());
    }

    private void loadConfig(FileConfiguration config) {
        // placeholder
        ConfigData.placeHolderList = getConfig().getIntegerList("PlaceholderSlots");

        // recipes
        for (String key : getConfig().getKeys(false)) {
            ConfigurationSection section = config.getConfigurationSection(key);
            ConfigData.recipeList.add(new Recipe(
                    section.getString("Name"), section.getBoolean("UseShard"),
                    section.getString("UseShardName"), section.getInt("UseShardAmount"),
                    section.getBoolean("UseProtection"), section.getString("UseProtectionName"),
                    section.getInt("UseProtectionAmount"), section.getInt("WinProbability"),
                    section.getString("WinItemName"), section.getStringList("WinItemLore")
                    ));

        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("""
                Thank you for using Biodible.
                Build 0.3.0(92a)
                By Lettuce
                With help from OPenAI's ChatGPT
                On 30 Aug '23
                Bye!""");
    }
}
