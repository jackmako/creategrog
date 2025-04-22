package net.makozort.creategrog.content.block;

import com.mojang.serialization.MapCodec;
import net.makozort.creategrog.reg.AllFoodItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BeerMugBlock extends AbstractGrogBlock {

    private static final MapCodec<BeerMugBlock> CODEC = simpleCodec(BeerMugBlock::new);

    private static final VoxelShape SHAPE = Block.box(2,-2,4,14,10,12);

    public BeerMugBlock(Properties properties) {
        super(properties);
    }


    @Override
    public Item interactItem() {
        return AllFoodItems.BEER_MUG_FULL.asItem();
    }

    @Override
    public SoundEvent pickupSound() {
        return SoundEvents.WOOD_BREAK;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }
}
