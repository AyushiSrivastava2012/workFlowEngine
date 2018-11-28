/**
 * 
 */
package com.company.engineering;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * @author Ayushi Srivastava
 *
 */

@Component
public class KafkaConsumer {

	@Autowired
	KafkaProducer kafkaProducer;
	
	
	@KafkaListener(topics = "workFlow")
    public void receiveWorkFlow(ConsumerRecord<?, ?> consumerRecord) throws JsonParseException, JsonMappingException, IOException {
       
		String[] a = consumerRecord.value().toString().split(",");
		List<ActionEnum> list = new ArrayList<ActionEnum>();
		list.add(ActionEnum.valueOf(a[2]));
		GenericPojo obj = new GenericPojo(a[0],State.valueOf(a[1]),new Action("1",list));
        ActionEnum en = obj.getAction().getWhatToDo().get(0);
        System.out.println("En   "+en);
        System.out.println("Receiver on topic1: "+consumerRecord.toString());
        switch(en){
        case CreateUser:
        	//write business logic here
        	//send event to queue
        	kafkaProducer.send("workFlow", a[0]+","+State.CreateUser+","+ActionEnum.L1ApprovalPending);
        	break;
        	
        case L1ApprovalPending:
        	//write business logic here
        	//send event to queue
        	kafkaProducer.send("workFlow", a[0]+","+State.L1Approval+","+ActionEnum.L2ApprovalPending);
        	break;
        }
    }

}
