package com.company.engineering.eventListener;

import org.springframework.stereotype.Component;

import com.company.engineering.events.InsertUserInMongoEvent;
import com.company.engineering.events.L1ApprovalPendingEvent;

@Component
final public class EventType<E> {

	private EventType(){}
    public final static EventType<InsertUserInMongoEvent> InsertUserInMongo = new EventType<InsertUserInMongoEvent>();
	public static final EventType<L1ApprovalPendingEvent> L1ApprovalPendingEventListener =  new EventType<L1ApprovalPendingEvent>();
}
