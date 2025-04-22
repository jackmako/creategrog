package net.makozort.creategrog.handler;

import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;

public class DrunkHandler {

    // Map storing players and their current drunk level
    private static final Map<Player, Double> drunkMap = new HashMap<>();

    // Get a player's drunk level (returns 0 if not tracked)
    public static double getDrunkLevel(Player player) {
        return drunkMap.getOrDefault(player, 0.0);
    }

    // Set a player's drunk level directly
    public static void setDrunkLevel(Player player, double level) {
        if (level <= 0) {
            drunkMap.remove(player); // Auto-remove if no longer drunk
        } else {
            drunkMap.put(player, level);
        }
    }

    // Increase a player's drunk level
    public static void increaseDrunk(Player player, double amount) {
        if (amount <= 0) return; // Ignore invalid input
        double newLevel = getDrunkLevel(player) + amount;
        drunkMap.put(player, newLevel);
    }

    // Decrease a player's drunk level
    public static void decreaseDrunk(Player player, double amount) {
        if (amount <= 0) return; // Ignore invalid input
        double current = getDrunkLevel(player);
        double newLevel = current - amount;
        setDrunkLevel(player, Math.max(newLevel, 0.0)); // Clamp to 0
    }

    // Fully remove a player from tracking
    public static void removePlayer(Player player) {
        drunkMap.remove(player);
    }

    // Check if player is currently drunk (i.e., tracked and > 0)
    public static boolean isDrunk(Player player) {
        return getDrunkLevel(player) > 0.0;
    }

    // Optional: Clear all drunk data (e.g., on server shutdown)
    public static void clearAll() {
        drunkMap.clear();
    }
}
