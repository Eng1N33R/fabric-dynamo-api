package io.engi.dynamo.api;

import net.minecraft.util.Identifier;

public class Payload<T> {
	private final T payload;
	private final Identifier type;

	public Payload(T payload, Identifier type) {
		this.payload = payload;
		this.type = type;
	}

	public T getPayload() {
		return payload;
	}

	public Identifier getType() {
		return type;
	}
}
