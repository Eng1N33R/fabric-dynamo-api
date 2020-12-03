package io.engi.dynamo.api;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class Connection {
	private final World world;
	private Direction side;
	private Endpoint target;
	private Identifier type;

	public Connection(World world, Direction side, Endpoint target, Identifier type) {
		this.world = world;
		this.side = side;
		this.target = target;
		this.type = type;
	}

	public Connection(World world, CompoundTag tag) {
		this.world = world;
		target = new Endpoint(world, tag.getCompound("Target"));
		type = Identifier.tryParse(tag.getString("Type"));
		side = Direction.values()[tag.getByte("Side")];
	}

	public boolean push(Payload<?> payload) {
		if (payload.getType() == type) {
			if (target.getEntity() instanceof Receiver) {
				Receiver receiver = (Receiver) target.getEntity();
				if (receiver.canReceive(target.getDirection(), type)) {
					return receiver.onReceive(target.getDirection(), payload);
				}
			}
		}
		return false;
	}

	public World getWorld() {
		return world;
	}

	public Direction getSide() {
		return side;
	}

	public Endpoint getTarget() {
		return target;
	}

	public Identifier getType() {
		return type;
	}

	public void fromTag(CompoundTag tag) {
		target = new Endpoint(world, tag.getCompound("Target"));
		type = Identifier.tryParse(tag.getString("Type"));
	}

	public CompoundTag toTag(CompoundTag tag) {
		tag.put("Target", target.toTag());
		tag.putString("Type", type.toString());
		return tag;
	}
}
