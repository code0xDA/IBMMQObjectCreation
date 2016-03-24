package util;

import java.util.HashMap;

public interface MQObjectManager {

	public boolean createMQAliasQueue(String qMr, String channel, int port, HashMap<Object, Object> property);

	public boolean createMQTopic(String qMr, String channel, int port, HashMap<Object, Object> property);
}
