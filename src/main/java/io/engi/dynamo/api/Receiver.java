package io.engi.dynamo.api;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public interface Receiver extends Connectable {
	boolean canReceive(Direction direction, Identifier payloadType);
	boolean onReceive(Direction direction, Payload<?> payload);
}
