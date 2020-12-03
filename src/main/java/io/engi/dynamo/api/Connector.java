package io.engi.dynamo.api;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Set;
import java.util.function.Function;

public interface Connector {
	@Nullable Direction getNextDirection(BlockView world, BlockPos pos, Direction inbound, Identifier type);
	boolean canAccept(BlockView world, BlockPos pos, Direction direction, Identifier type);
	default @Nullable boolean transmit(BlockView world, BlockPos pos, Direction inbound, Payload<?> payload) {
		if (!canAccept(world, pos, inbound, payload.getType())) return false;
		Direction traverseDir = getNextDirection(world, pos, inbound, payload.getType());
		if (traverseDir == null) return false;
		Direction targetInboundSide = traverseDir.getOpposite();
		BlockState targetState = world.getBlockState(pos.offset(traverseDir));
		BlockEntity targetEntity = world.getBlockEntity(pos.offset(traverseDir));
		if (targetState.getBlock() instanceof Connector) {
			Connector targetConnector = (Connector) targetState.getBlock();
			return targetConnector.transmit(world, pos.offset(traverseDir), targetInboundSide, payload);
		} else if (targetEntity instanceof Receiver) {
			Receiver receiver = (Receiver) targetEntity;
			if (receiver.canReceive(targetInboundSide, payload.getType())) {
				receiver.onReceive(targetInboundSide, payload);
			}
		}
		return false;
	}
}
