package io.engi.dynamo.api;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import java.util.Objects;

public class WorldBlockPos extends BlockPos {
	private final World world;

	public WorldBlockPos(World world, BlockPos pos) {
		super(pos.getX(), pos.getY(), pos.getZ());
		this.world = world;
	}

	public WorldBlockPos(World world, int i, int j, int k) {
		super(i, j, k);
		this.world = world;
	}

	public WorldBlockPos(World world, double d, double e, double f) {
		super(d, e, f);
		this.world = world;
	}

	public WorldBlockPos(World world, Vec3d pos) {
		super(pos);
		this.world = world;
	}

	public WorldBlockPos(World world, Position pos) {
		super(pos);
		this.world = world;
	}

	public WorldBlockPos(World world, Vec3i pos) {
		super(pos);
		this.world = world;
	}

	public WorldBlockPos inWorld(World world) {
		return new WorldBlockPos(world, getX(), getY() ,getZ());
	}

	public World getWorld() {
		return world;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		WorldBlockPos that = (WorldBlockPos) o;
		return world.equals(that.world);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), world);
	}

	public BlockEntity getEntity() {
		return world.getBlockEntity(this);
	}

	public BlockState getBlockState() {
		return world.getBlockState(this);
	}

	public Block getBlock() {
		return getBlockState().getBlock();
	}

	public boolean isConnector() {
		return getBlockState().getBlock() instanceof Connector;
	}
}
