package util;

import com.ibm.mq.constants.*;

public final class MQObjectConstants {

	public static String MQ_OBJECT_QUEUE_ALIAS = "QUEUEALIAS";
	public static String MQ_OBJECT_TOPIC = "TOPIC";
	
	public static int MQ_CLUSTER_QUEUE_TYPE = CMQC.MQIA_CLUSTER_Q_TYPE;
	
	public static int MQ_QUEUE_TYPE_LOCAL_CODE = CMQC.MQQT_LOCAL;
	public static int MQ_QUEUE_TYPE_QCLUSTER_CODE = CMQC.MQQT_CLUSTER;
	public static int MQ_TOPIC_TYPE_CODE = CMQC.MQOT_TOPIC;
	public static int QUEUE_TYPE_ALIAS_CODE = CMQC.MQQT_ALIAS;
	
	
	public static class MQ_COMMAND {
		public static int MQ_COMMAND_CREATE_TOPIC = CMQCFC.MQCMD_CREATE_TOPIC;
		public static int MQ_COMMAND_CREATE_Q = CMQCFC.MQCMD_CREATE_Q;
	}

	public static class QUEUE_ALIAS_CONSTRUCTION_PROPERTIES {
		public static int MQ_QUEUE_TYPE = CMQC.MQIA_Q_TYPE;
		public static int QUEUE_ALIAS_NAME = CMQC.MQCA_Q_NAME;
		public static int MQ_CLUSTER_NAME = CMQC.MQCA_CLUSTER_NAME;
		public static int BASE_OBJECT_NAME = CMQC.MQCA_BASE_Q_NAME;
		public static int BASE_OBJECT_TYPE = CMQC.MQIA_BASE_TYPE;
	
	}

	public static class TOPIC_CONSTRUCTION_PROPERTIES {
		public static int MQ_TOPIC_NAME = CMQC.MQCA_TOPIC_NAME;
		public static int MQ_TOPIC_STRING = CMQC.MQCA_TOPIC_STRING;

	}
	


}
