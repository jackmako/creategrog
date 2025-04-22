package net.makozort.creategrog.content.item;

import net.makozort.creategrog.handler.DrunkHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public abstract class AbstractDrinkItem extends Item {
    public AbstractDrinkItem(Properties properties) {
        super(properties);
    }

    public abstract Block getBlockVersion();

    public abstract SoundEvent getPlaceSound();

    @Override
    public  InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        Direction clickedFace = context.getClickedFace();
        BlockPos targetPos = clickedPos.above(); // We're only checking for top placement

        if (!clickedFace.equals(Direction.UP)) {
            return InteractionResult.PASS; // Only do it on top face
        }

        if (!level.isClientSide()) {
            BlockState targetState = level.getBlockState(targetPos);
            if (targetState.isAir() || targetState.canBeReplaced()) {
                level.setBlockAndUpdate(targetPos, this.getBlockVersion().defaultBlockState());
                context.getItemInHand().shrink(1);
                level.playSound(null, targetPos, this.getPlaceSound(), SoundSource.BLOCKS, 1.0f, 1.0f);
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.FAIL;
    }


    public abstract double getPotency();

    @Override
    public final UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public abstract int getUseDuration(ItemStack stack, LivingEntity entity);


    @Override
    public final ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide && entity instanceof Player player) {
            DrunkHandler.increaseDrunk(player, this.getPotency()); // Only apply once
        }

        return super.finishUsingItem(stack, level, entity);
    }
}
