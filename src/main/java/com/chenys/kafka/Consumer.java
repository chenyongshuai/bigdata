package com.chenys.kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

/** @author:  v_chenyongshuai@:
  * @date:  2018年3月14日 上午10:21:02 
  * @version：   1.0.0
  * @describe:    
  */
public class Consumer {
	public static void main(String[] args) {
		Properties props=new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.126.128:9092,192.168.126.128:9093");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,"group2");
        // 1.创建KafkaConsumer
        KafkaConsumer<String,String> kafkaConsumer=new KafkaConsumer<String, String>(props);
        // 2.订阅topic
        kafkaConsumer.subscribe(Arrays.asList("topic01"));
        while(true){
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(2000);
            for (ConsumerRecord<String, String> record : consumerRecords) {
                String key=record.key();
                String value=record.value();
                long ts=record.timestamp();
                int partition=record.partition();
                long offset=record.offset();
                System.out.println(key+":"+value+" ,ts:"+ts+" part:"+partition+" ,offset"+offset);
            }
        }
	}
}
