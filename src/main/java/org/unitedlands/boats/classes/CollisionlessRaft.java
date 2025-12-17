package org.unitedlands.boats.classes;

import java.util.function.Supplier;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Raft;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class CollisionlessRaft extends Raft {

    public CollisionlessRaft(EntityType<? extends Raft> type, Level level, Supplier<Item> dropItem) {
        super(type, level, dropItem);
    }

    // Force all collision checks to fail
    @Override
    public boolean canCollideWith(Entity entity) {
        return false;
    }
}
