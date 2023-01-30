package dev.iiahmed.disguise;

import dev.iiahmed.disguise.listener.PlayerListener;
import dev.iiahmed.disguise.vs.*;
import dev.iiahmed.disguise.placeholder.PAPIExpantion;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class DisguiseManager {

    private static final DisguiseProvider PROVIDER;
    private static boolean expantionRegistered = false;

    static {
        switch (DisguiseUtil.VERSION) {
            case "1_8_R3":
                PROVIDER = new VS1_8_R3();
                break;
            case "1_9_R2":
                PROVIDER = new VS1_9_R2();
                break;
            case "1_10_R1":
                PROVIDER = new VS1_10_R1();
                break;
            case "1_11_R1":
                PROVIDER = new VS1_11_R1();
                break;
            case "1_12_R1":
                PROVIDER = new VS1_12_R1();
                break;
            case "1_13_R1":
                PROVIDER = new VS1_13_R1();
                break;
            case "1_13_R2":
                PROVIDER = new VS1_13_R2();
                break;
            case "1_14_R1":
                PROVIDER = new VS1_14_R1();
                break;
            case "1_15_R1":
                PROVIDER = new VS1_15_R1();
                break;
            case "1_16_R1":
                PROVIDER = new VS1_16_R1();
                break;
            case "1_16_R2":
                PROVIDER = new VS1_16_R2();
                break;
            case "1_16_R3":
                PROVIDER = new VS1_16_R3();
                break;
            case "1_17_R1":
                PROVIDER = new VS1_17_R1();
                break;
            case "1_18_R1":
                PROVIDER = new VS1_18_R1();
                break;
            case "1_18_R2":
                PROVIDER = new VS1_18_R2();
                break;
            case "1_19_R1":
                PROVIDER = new VS1_19_R1();
                break;
            case "1_19_R2":
                PROVIDER = new VS1_19_R2();
                break;
            default:
                PROVIDER = new VS_Unavailable();
                break;
        }
    }

    /**
     * sets the plugin for the provider and registers the litsners
     */
    @SuppressWarnings("all")
    public static void setPlugin(Plugin plugin) {
        if (PROVIDER.getPlugin() == null) {
            PROVIDER.setPlugin(plugin);
            plugin.getServer().getPluginManager().registerEvents(new PlayerListener(), plugin);
        }
    }

    /**
     * @return the available DisguiseProvider for current version
     */
    @NotNull
    public static DisguiseProvider getProvider() {
        return PROVIDER;
    }

    /**
     * Registers a PlaceholderAPI expantion if PAPI exists
     */
    @SuppressWarnings("unused")
    public static void registerExpantion() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            return;
        }
        if (!expantionRegistered) {
            new PAPIExpantion().register();
            expantionRegistered = true;
        }
    }

}
