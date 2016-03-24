package util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;

import com.ibm.mq.MQException;
import com.ibm.mq.MQMessage;
import com.ibm.mq.constants.CMQCFC;
import com.ibm.mq.pcf.MQCFH;
import com.ibm.mq.pcf.MQCFIN;
import com.ibm.mq.pcf.MQCFST;
import com.ibm.mq.pcf.PCFAgent;
import com.ibm.mq.pcf.PCFParameter;

import util.MQObjectConstants.*;

/**
 * @author daji
 *
 */
public class DefaultMQObjectManager implements MQObjectManager {

	public boolean createMQAliasQueue(String qMr, String channel, int port, HashMap<Object, Object> property) {
		PCFAgent agent = null;
		try {
			agent = new PCFAgent(qMr, port, channel);

			MQMessage[] responses;

			if (property != null && !property.isEmpty()) {

				MQCFST queueName = null, clusterName = null, baseObjectName = null;
				MQCFIN queueType = null, baseObjectType = null;
				if (property.containsKey(MQObjectConstants.QUEUE_ALIAS_CONSTRUCTION_PROPERTIES.QUEUE_ALIAS_NAME)) {
					queueName = new MQCFST(MQObjectConstants.QUEUE_ALIAS_CONSTRUCTION_PROPERTIES.QUEUE_ALIAS_NAME,
							(String) property
									.get(MQObjectConstants.QUEUE_ALIAS_CONSTRUCTION_PROPERTIES.QUEUE_ALIAS_NAME));

				}

				if (property.containsKey(MQObjectConstants.QUEUE_ALIAS_CONSTRUCTION_PROPERTIES.MQ_CLUSTER_NAME)) {
					clusterName = new MQCFST(MQObjectConstants.QUEUE_ALIAS_CONSTRUCTION_PROPERTIES.MQ_CLUSTER_NAME,
							(String) property
									.get(MQObjectConstants.QUEUE_ALIAS_CONSTRUCTION_PROPERTIES.MQ_CLUSTER_NAME));
				}

				if (property.containsKey(MQObjectConstants.QUEUE_ALIAS_CONSTRUCTION_PROPERTIES.BASE_OBJECT_NAME)) {
					baseObjectName = new MQCFST(MQObjectConstants.QUEUE_ALIAS_CONSTRUCTION_PROPERTIES.BASE_OBJECT_NAME,
							(String) property
									.get(MQObjectConstants.QUEUE_ALIAS_CONSTRUCTION_PROPERTIES.BASE_OBJECT_NAME));
				}

				if (property.containsKey(MQObjectConstants.QUEUE_ALIAS_CONSTRUCTION_PROPERTIES.MQ_QUEUE_TYPE)) {

					queueType = new MQCFIN(MQObjectConstants.QUEUE_ALIAS_CONSTRUCTION_PROPERTIES.MQ_QUEUE_TYPE,
							(Integer) property
									.get(MQObjectConstants.QUEUE_ALIAS_CONSTRUCTION_PROPERTIES.MQ_QUEUE_TYPE));
				}

				if (property.containsKey(MQObjectConstants.QUEUE_ALIAS_CONSTRUCTION_PROPERTIES.BASE_OBJECT_TYPE)) {

					baseObjectType = new MQCFIN(MQObjectConstants.QUEUE_ALIAS_CONSTRUCTION_PROPERTIES.BASE_OBJECT_TYPE,
							(Integer) property
									.get(MQObjectConstants.QUEUE_ALIAS_CONSTRUCTION_PROPERTIES.BASE_OBJECT_TYPE));
				}

				PCFParameter[] qaliasConstructionParameters = {

						queueName, queueType, clusterName, baseObjectName, baseObjectType };

				responses = agent.send(CMQCFC.MQCMD_CREATE_Q, qaliasConstructionParameters);
				MQCFH respnseResult;
				respnseResult = new MQCFH(responses[0]);

				if (respnseResult != null && respnseResult.reason == 0) {

					return true;
				} else {

					// add log
					return false;
				}

			}

			agent.disconnect();

		} catch (MQException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (agent != null) {
					agent.disconnect();
				}

			} catch (MQException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;

	}

	public boolean createMQTopic(String qMr, String channel, int port, HashMap<Object, Object> property) {
		PCFAgent agent = null;
		try {
			agent = new PCFAgent(qMr, port, channel);

			MQMessage[] responses;

			if (property != null && !property.isEmpty()) {

				MQCFST topicName = null, topicString = null;

				if (property.containsKey(MQObjectConstants.TOPIC_CONSTRUCTION_PROPERTIES.MQ_TOPIC_NAME)) {
					topicName = new MQCFST(MQObjectConstants.TOPIC_CONSTRUCTION_PROPERTIES.MQ_TOPIC_NAME,
							(String) property.get(MQObjectConstants.TOPIC_CONSTRUCTION_PROPERTIES.MQ_TOPIC_NAME));

				}

				if (property.containsKey(MQObjectConstants.TOPIC_CONSTRUCTION_PROPERTIES.MQ_TOPIC_STRING)) {

					topicString = new MQCFST(MQObjectConstants.TOPIC_CONSTRUCTION_PROPERTIES.MQ_TOPIC_STRING,
							(String) property.get(MQObjectConstants.TOPIC_CONSTRUCTION_PROPERTIES.MQ_TOPIC_STRING));

				}

				PCFParameter[] topicConstructionParameters = { topicName, topicString };

				responses = agent.send(CMQCFC.MQCMD_CREATE_TOPIC, topicConstructionParameters);
				MQCFH respnseResult;
				respnseResult = new MQCFH(responses[0]);

				if (respnseResult != null && respnseResult.reason == 0) {

					return true;
				} else {

					// add log
					return false;
				}

			}

			agent.disconnect();

		} catch (MQException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (agent != null) {
					agent.disconnect();
				}

			} catch (MQException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean checkPropertyValidity(String objectType, HashMap<Object, Object> propertyMap) {

		if (objectType.equals(MQObjectConstants.MQ_OBJECT_QUEUE_ALIAS)) {
			if (propertyMap != null && !propertyMap.isEmpty()) {

				Field[] fields = QUEUE_ALIAS_CONSTRUCTION_PROPERTIES.class.getDeclaredFields();
				for (Field field : fields) {
					try {
						if (!propertyMap.containsKey(field.get(field))) {
							return false;
						}
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
				}
				return true;

			}

		} else if (objectType.equals(MQObjectConstants.MQ_OBJECT_TOPIC)) {

			Field[] fields = TOPIC_CONSTRUCTION_PROPERTIES.class.getDeclaredFields();
			for (Field field : fields) {
				try {
					if (!propertyMap.containsKey(field.get(field))) {
						return false;
					}
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			}
			return true;

		}

		// log : this object type is not supported
		return false;

	}

	public static void main(String[] args) {

		DefaultMQObjectManager test = new DefaultMQObjectManager();
		String qMr = "52.52.52.52";
		String channel = "QM1CHANNEL";
		int port = 7333;
		HashMap<Object, Object> property1 = new HashMap<Object, Object>();
		property1.put(new Integer(2016), "CLUSTERQALIASFEB18/22");
		property1.put(new Integer(20), new Integer(3));
		property1.put(new Integer(2029), "CLUSTER1");
		property1.put(new Integer(2002), "TOPICFEB18/22");
		property1.put(new Integer(193), new Integer(8));

		HashMap<Object, Object> property2 = new HashMap<Object, Object>();
		property2.put(2092, "TOPICFEB18/22");
		property2.put(2094, "TOPICFEB18/22");

		if (test.checkPropertyValidity("TOPIC", property2) && test.createMQTopic(qMr, channel, port, property2))
			System.out.println("topic created");

		if (test.checkPropertyValidity("QUEUEALIAS", property1)
				&& test.createMQAliasQueue(qMr, channel, port, property1))
			System.out.println("queue alias created");

	}
}
