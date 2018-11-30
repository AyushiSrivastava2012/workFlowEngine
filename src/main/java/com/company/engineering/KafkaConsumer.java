/**
 * 
 */
package com.company.engineering;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.company.engineering.eventListener.EventSource;
import com.company.engineering.eventListener.EventType;
import com.company.engineering.eventListener.Listener;
import com.company.engineering.events.BaseEvent;
import com.company.engineering.events.InsertUserInMongoEvent;
import com.company.engineering.events.L1ApprovalPendingEvent;
import com.company.engineering.mongo.GenericPojoRepository;
import com.company.engineering.mongo.TestUserRepository;
import com.company.engineering.pojo.Action;
import com.company.engineering.pojo.ActionEnum;
import com.company.engineering.pojo.GenericPojo;
import com.company.engineering.pojo.State;
import com.company.engineering.pojo.TestUser;
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

	@Autowired 
	EventSource eventSource;
	
	@Autowired
	GenericPojoRepository repo;
	
	@Autowired
	TestUserRepository userRepo;

	@KafkaListener(topics = "workFlowRetry")
	public void receiveWorkFlow(ConsumerRecord<?, ?> consumerRecord) throws JsonParseException, JsonMappingException, IOException {
		//docId,current state,next event
		String[] a = consumerRecord.value().toString().split(",");
		List<ActionEnum> list = new ArrayList<ActionEnum>();
		list.add(ActionEnum.valueOf(a[2]));
		GenericPojo obj = new GenericPojo(a[0],State.valueOf(a[1]),new Action("1",list));
		ActionEnum en = obj.getAction().getWhatToDo().get(0);
		
		addListeners();

		List<GenericPojo> fetchedObjList = repo.findByDocIdAndState(obj.getDocId(),obj.getState());
		
		Predicate<GenericPojo> capStartFilter = (x) ->  x.getAction().getWhatToDo().contains(obj.getAction().getWhatToDo().get(0));
		
		List<GenericPojo> fetchedObjListFiltered = fetchedObjList.stream()
				.filter(capStartFilter)	
				.collect(Collectors.toList());
		
		if(fetchedObjListFiltered.isEmpty()){
			repo.save(obj);
			fetchedObjListFiltered.add(obj);
		}
		fetchedObjListFiltered.get(0).getAction().getWhatToDo().forEach(x -> {
			switch(x){
			case InsertUserInMongo:
				eventSource.fire(EventType.InsertUserInMongo, new InsertUserInMongoEvent(obj));
				break;
			case L1ApprovalPending:
				eventSource.fire(EventType.L1ApprovalPendingEventListener, new L1ApprovalPendingEvent(obj));
				break;
			}
		});
	}

	private void addListeners() {
		
		eventSource.addListener(EventType.InsertUserInMongo, new Listener<InsertUserInMongoEvent>() {
			@Override
			public void handle(InsertUserInMongoEvent event) {
				String id = UUID.randomUUID().toString();
				System.out.println("CreateUserEventListener handled!"+id);
				GenericPojo obj =  event.getPojo();
				//execute actions
				TestUser user = new TestUser(id,"Ayushi");
				userRepo.save(user);
				obj.setState(State.CreateUser);
				repo.save(obj);
				kafkaProducer.send("workFlow", obj.getDocId()+","+State.CreateUser+","+ActionEnum.L1ApprovalPending);				
			}
		});	
		
		eventSource.addListener(EventType.L1ApprovalPendingEventListener, new Listener<L1ApprovalPendingEvent>() {
			@Override
			public void handle(L1ApprovalPendingEvent event) {
				System.out.println("L1ApprovalPendingEvent handled!");
				
			}
		});
	}

}
