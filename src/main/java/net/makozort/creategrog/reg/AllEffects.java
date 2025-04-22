package net.makozort.creategrog.reg;

import net.makozort.creategrog.CreateGrog;
import net.makozort.creategrog.content.effect.DrunkEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public abstract class AllEffects {
    public static DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(Registries.MOB_EFFECT, CreateGrog.MODID);

    public static final DeferredHolder<MobEffect, MobEffect> DRUNK_EFFECT = MOB_EFFECTS.register("drunk_effect", () -> new DrunkEffect(
            MobEffectCategory.BENEFICIAL,
            0xffffff
    ).addAttributeModifier(
            Attributes.MOVEMENT_SPEED,
            ResourceLocation.withDefaultNamespace("effect.makosmisc.drunk_slow_move"),
            -.05, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(
                    Attributes.ATTACK_SPEED,
                    ResourceLocation.withDefaultNamespace("effect.makosmisc.drunk_slow_attack"),
                    -.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(
                    Attributes.BLOCK_BREAK_SPEED,
                    ResourceLocation.withDefaultNamespace("effect.makosmisc.drunk_slow_mine"),
                    -.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
);



    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }


}
