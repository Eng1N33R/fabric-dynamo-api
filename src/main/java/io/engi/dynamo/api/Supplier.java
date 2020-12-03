package io.engi.dynamo.api;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public interface Supplier extends Connectable {
	void supply(Direction direction, Payload<?> payload);
	default boolean onRequest(Direction direction, Payload<?> payload) {
		return false;
	}
}
