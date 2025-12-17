package org.unitedlands.boats.classes;

import java.util.function.Supplier;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.ChestRaft;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class CollisionlessChestRaft extends ChestRaft {

    public CollisionlessChestRaft(EntityType<? extends ChestRaft> type, Level level, Supplier<Item> dropItem) {
        super(type, level, dropItem);
    }

    // Force all collision checks to fail
    @Override
    public boolean canCollideWith(Entity entity) {
        return false;
    }
}
