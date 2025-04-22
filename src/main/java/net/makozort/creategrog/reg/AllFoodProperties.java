package net.makozort.creategrog.reg;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;

public class AllFoodProperties {





    public static final FoodProperties BEER_MUG = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(-5)
            .alwaysEdible()
            .usingConvertsTo(AllItems.BEER_MUG_EMPTY)
            .build();

}
