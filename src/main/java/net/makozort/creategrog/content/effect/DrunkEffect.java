package net.makozort.creategrog.content.effect;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class DrunkEffect extends MobEffect {
    public DrunkEffect(MobEffectCategory category, int color) {
        super(category, color);
    }


    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (amplifier >= 2 && !livingEntity.hasEffect(MobEffects.CONFUSION)) {
            // Apply vanilla nausea for 3 seconds
            livingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0, true, false));
        }
        return super.applyEffectTick(livingEntity, amplifier);
    }


    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }


}
