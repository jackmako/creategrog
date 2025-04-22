package net.makozort.creategrog.event;

import net.makozort.creategrog.CreateGrog;
import net.makozort.creategrog.handler.DrunkHandler;
import net.makozort.creategrog.reg.AllEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.Random;

public class ModEvents {

    private static final Random random = new Random();

    @SubscribeEvent
    public void playerLeave(PlayerEvent.PlayerLoggedOutEvent event) {
        Player player = event.getEntity();
        DrunkHandler.removePlayer(player);
    }

    @SubscribeEvent
    public void onDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) {
            DrunkHandler.removePlayer(player);
        }
    }

    @SubscribeEvent
    public void onLiving(PlayerTickEvent.Pre event) {
        Player p = event.getEntity();
        //CreateGrog.LOGGER.info(Double.toString(DrunkHandler.getDrunkLevel(p)));

        if (!p.level().isClientSide && DrunkHandler.isDrunk(p)) {
            double current = DrunkHandler.getDrunkLevel(p);
            double decayPerTick = 1.0 / (20 * 180); // ~0.000278
            double newLevel = current - decayPerTick;

            if (newLevel <= 0) {
                DrunkHandler.removePlayer(p);
                return;
            }

            DrunkHandler.setDrunkLevel(p, newLevel);

            // === Apply effects based on drunk level thresholds ===
            if (newLevel >= 0.15 && newLevel < 0.5) {
                applyDrunkEffect(p, 0);
            } else if (newLevel >= 0.5 && newLevel < 0.6) {
                applyDrunkEffect(p, 1);
            } else if (newLevel >= 0.6) {
                applyDrunkEffect(p, 2);

                // === Escalating chance to trigger overdrunk effect ===
                // Example: 0.65 drunk = 5% chance, 1.0 drunk = 40% chance
                double chance = (newLevel - 0.6) * 100; // 0–40%
                if (random.nextDouble() * 100 < chance) {
                    //pass out

                }
            }
        }
    }

    private void applyDrunkEffect(Player player, int level) {
        int duration = 300; // Short duration to allow refreshing per tick
        if (player.hasEffect(AllEffects.DRUNK_EFFECT)) {
            int currentLevel = player.getEffect(AllEffects.DRUNK_EFFECT).getAmplifier();
            if (currentLevel >= level) return; // Don't downgrade effect
        }

        player.addEffect(new MobEffectInstance(AllEffects.DRUNK_EFFECT, duration, level, false, false, true));
    }
}
