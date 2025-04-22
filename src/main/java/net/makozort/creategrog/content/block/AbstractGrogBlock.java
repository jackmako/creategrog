package net.makozort.creategrog.content.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractGrogBlock extends HorizontalDirectionalBlock {


    public AbstractGrogBlock(Properties properties) {
        super(properties);
    }


    public abstract Item interactItem();

    public abstract SoundEvent pickupSound();

    @Override
    protected abstract VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context);

    @Override
    protected abstract MapCodec<? extends HorizontalDirectionalBlock> codec();

    @Override
    public final @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected final void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            ItemStack stack = new ItemStack(interactItem());

            // Try to give it to the player
            boolean success = player.getInventory().add(stack);
            if (!success) {
                level.playSound(null, pos, this.pickupSound(), SoundSource.BLOCKS, 1.0f, 1.0f);
                popResource(level, pos, stack);
            }

            // Remove the block
            level.removeBlock(pos, false);
        }
        level.playSound(null, pos, this.pickupSound(), SoundSource.BLOCKS, 1.0f, 1.0f);
        return InteractionResult.SUCCESS;
    }


}
