package com.aetherteam.nitrogen.network.packet;

import com.aetherteam.nitrogen.capability.CapabilityUtil;
import com.aetherteam.nitrogen.capability.INBTSynchable;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.LazyOptional;
import org.apache.commons.lang3.tuple.Triple;

/**
 * An abstract packet used by level capabilities for data syncing.
 */
public abstract class SyncLevelPacket<T extends INBTSynchable<CompoundTag>> extends SyncPacket {
    public SyncLevelPacket(Triple<String, INBTSynchable.Type, Object> values) {
        super(values);
    }

    public SyncLevelPacket(String key, INBTSynchable.Type type, Object value) {
        super(key, type, value);
    }

    @Override
    public void execute(Player playerEntity) {
        if (playerEntity != null && playerEntity.getServer() != null && this.value != null) {
            CapabilityUtil.syncLevelCapability(this, playerEntity, this.key, this.value, false);
        } else {
            if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null && this.value != null) {
                CapabilityUtil.syncLevelCapability(this, playerEntity, this.key, this.value, true);
            }
        }
    }

    public abstract LazyOptional<T> getCapability(Level level);
}
