package com.aetherteam.nitrogen.item.block;

import com.aetherteam.nitrogen.client.renderer.NitrogenRenderers;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.common.util.NonNullSupplier;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Used for {@link BlockItem}s that have a {@link BlockEntityWithoutLevelRenderer} attached.
 */
public class EntityBlockItem extends BlockItem {
    private final Optional<? extends BlockEntity> blockEntity;

    public <B extends Block> EntityBlockItem(B block, NonNullSupplier<? extends BlockEntity> blockEntity, Properties properties) {
        super(block, properties);
        this.blockEntity = Optional.of(blockEntity.get());
    }

    public Optional<? extends BlockEntity> getBlockEntity() {
        return this.blockEntity;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(NitrogenRenderers.entityBlockItemRenderProperties);
    }
}
