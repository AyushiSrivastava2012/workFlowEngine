package com.company.engineering.events;

import com.company.engineering.pojo.GenericPojo;

public class BaseEvent {
	private GenericPojo pojo;
	
	/**
	 * @return the pojo
	 */
	public GenericPojo getPojo() {
		return pojo;
	}

	/**
	 * @param pojo the pojo to set
	 */
	public void setPojo(GenericPojo pojo) {
		this.pojo = pojo;
	}

	/**
	 * @param pojo
	 */
	public BaseEvent(GenericPojo pojo) {
		this.pojo = pojo;
	}
	
}
