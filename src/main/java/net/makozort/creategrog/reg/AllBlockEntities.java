package net.makozort.creategrog.reg;


import net.makozort.creategrog.CreateGrog;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class AllBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, CreateGrog.MODID);






    public static void reg(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}