package io.engi.dynamo.impl;

import io.engi.dynamo.api.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.Direction;

public abstract class AbstractSupplierBlockEntity extends BlockEntity implements Supplier {
	public AbstractSupplierBlockEntity(BlockEntityType<?> type) {
		super(type);
	}

	@Override
	public void supply(Direction direction, Payload<?> payload) {
		if (!getPayloadTypes(direction).contains(payload.getType())) return;
		if (world.getBlockState(pos.offset(direction)).getBlock() instanceof Connector) {
			Connector connector = (Connector) world.getBlockState(pos.offset(direction)).getBlock();
			if (connector.canAccept(world, pos.offset(direction), direction.getOpposite(), payload.getType())) {
				connector.transmit(world, pos.offset(direction), direction.getOpposite(), payload);
			}
		}
	}
}
