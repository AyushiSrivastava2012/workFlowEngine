package com.company.engineering.pojo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Action {

	@Id
	private String actionId;
	private List<ActionEnum> whatToDo;
	/**
	 * @return the actionId
	 */
	public String getActionId() {
		return actionId;
	}
	/**
	 * @param actionId the actionId to set
	 */
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	/**
	 * @return the whatToDo
	 */
	public List<ActionEnum> getWhatToDo() {
		return whatToDo;
	}
	/**
	 * @param whatToDo the whatToDo to set
	 */
	public void setWhatToDo(List<ActionEnum> whatToDo) {
		this.whatToDo = whatToDo;
	}
	/**
	 * @param actionId
	 * @param whatToDo
	 */
	public Action(String actionId, List<ActionEnum> whatToDo) {
		super();
		this.actionId = actionId;
		this.whatToDo = whatToDo;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Action [" + (actionId != null ? "actionId=" + actionId + ", " : "")
				+ (whatToDo != null ? "whatToDo=" + whatToDo : "") + "]";
	}
	
	
	
}
