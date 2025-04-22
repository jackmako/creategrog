package net.makozort.creategrog.event;

import net.makozort.creategrog.CreateGrog;
import net.makozort.creategrog.reg.AllEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;



@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = CreateGrog.MODID, value = Dist.CLIENT)
public class ClientDrunkHandler {

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;

        if ((player == null || mc.level == null)) return;

        MobEffectInstance effect = player.getEffect(AllEffects.DRUNK_EFFECT); // your drunk effect
        if (effect != null && !mc.isPaused()) {
            applyDrunkEffect(player, effect.getAmplifier());
        }

    }

    private static void applyDrunkEffect(LocalPlayer player, int amplifier) {
        int tick = player.tickCount;

        // === TWEAKABLE DRUNK EFFECT CHANCES (0.0 to 1.0) ===
        double fakeSneakChance = 0.01;

        // === CAMERA DRIFT SCALING ===
        double baseWobble = 0.3 + (amplifier * 0.2);  // more vertical wobble per level
        double baseTwist = 2.0 + (amplifier * 1.5);   // more yaw sway per level
        double wobbleFreq = 0.2 + (amplifier * 0.05); // slightly faster at higher levels
        double twistFreq  = 0.1 + (amplifier * 0.05);

        // === Apply camera wobble (sinusoidal) ===
        float wobble = (float)(Math.sin(tick * wobbleFreq) * baseWobble);
        float twist  = (float)(Math.sin(tick * twistFreq) * baseTwist);

        player.setYRot(player.getYRot() + twist);   // yaw (left/right)
        player.setXRot(player.getXRot() + wobble);  // pitch (up/down)

        // === Random drunk input (crouch/sneak) ===
        if (Math.random() < fakeSneakChance) {
            player.input.shiftKeyDown = true;
        }
    }



}
