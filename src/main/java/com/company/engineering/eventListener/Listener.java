package com.company.engineering.eventListener;

import com.company.engineering.events.BaseEvent;

public interface Listener<E extends BaseEvent>{
	 void handle(E event);
}
