package net.makozort.creategrog.reg;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllFoodProperties {

    public static final FoodProperties BEER = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(1)
            .alwaysEdible()
            .usingConvertsTo(Items.BOWL.asItem())
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST,400,1),1)
            .build();

}
