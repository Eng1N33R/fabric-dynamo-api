package io.engi.dynamo.api;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import javax.annotation.Nullable;
import java.util.Set;

public interface Connectable {
	//Set<Connection> getConnections();
	Set<Identifier> getPayloadTypes(Direction direction);
	//void onConnectionCreated(Connection connection);
	//void onConnectionDestroyed(Connection connection);
}
