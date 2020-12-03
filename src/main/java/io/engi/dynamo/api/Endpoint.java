package io.engi.dynamo.api;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class Endpoint {
	private final WorldBlockPos worldBlockPos;
	private final Direction direction;

	public Endpoint(WorldBlockPos worldBlockPos, Direction direction) {
		this.worldBlockPos = worldBlockPos;
		this.direction = direction;
	}

	public Endpoint(World world, CompoundTag tag) {
		BlockPos pos = NbtHelper.toBlockPos(tag);
		Direction direction = Direction.values()[tag.getByte("D")];
		this.worldBlockPos = new WorldBlockPos(world, pos);
		this.direction = direction;
	}

	public WorldBlockPos getPos() {
		return worldBlockPos;
	}

	public Direction getDirection() {
		return direction;
	}

	public BlockEntity getEntity() {
		return worldBlockPos.getEntity();
	}

	public CompoundTag toTag() {
		CompoundTag tag = NbtHelper.fromBlockPos(worldBlockPos);
		tag.putByte("D", (byte) direction.ordinal());
		return tag;
	}
}
