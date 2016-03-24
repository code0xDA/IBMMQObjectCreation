# IBMMQObjectCreation
Utilize IBM MQ library to create MQ topic and MQ queue
## API
-	public boolean createMQAliasQueue(String qMr, String channel, int port, HashMap<Object, Object> property);

-	public boolean createMQTopic(String qMr, String channel, int port, HashMap<Object, Object> property);
## Quick Example





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
			

	
