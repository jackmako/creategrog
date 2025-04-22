package net.makozort.creategrog.content.item;


import net.makozort.creategrog.reg.Allblocks;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;


public class BeerMug extends AbstractDrinkItem {

    public BeerMug(Properties properties) {
        super(properties);
    }

    @Override
    public Block getBlockVersion() {
        return Allblocks.BEER_MUG.get();
    }

    @Override
    public SoundEvent getPlaceSound() {
        return SoundEvents.WOOD_PLACE;
    }


    @Override
    public double getPotency() {
        return .15;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 30;
    }
}

