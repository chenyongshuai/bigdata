package com.chenys.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

/** @author:  v_chenyongshuai@:
  * @date:  2018年3月14日 上午10:21:09 
  * @version：   1.0.0
  * @describe:    
  */
public class Publish {
	public static void main(String[] args) {
		Properties props=new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.126.128:9092,192.168.126.128:9093");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, Partitioner.class);
		//1.创建KafkaProducer
		KafkaProducer<String,String> kafkaProducer=new KafkaProducer<String, String>(props);
		//2.构建消息
		ProducerRecord<String,String> record=new ProducerRecord<String, String>("topic01",
		        "user:001","002 张三 男 28 xxx");
		//3.发送 消息给kafka消息队列
		kafkaProducer.send(record);
		//4.清空缓冲
		kafkaProducer.flush();
		//5.关闭producer
		kafkaProducer.close();
		System.out.println("发送截止...");
	}
}
