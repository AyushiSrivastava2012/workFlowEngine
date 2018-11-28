package com.company.engineering;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.company.engineering.State;
@Document
public class GenericPojo {

	@Id
	private String docId;
	private State state;
	private Action action;
	/**
	 * @return the docId
	 */
	public String getDocId() {
		return docId;
	}
	/**
	 * @param docId the docId to set
	 */
	public void setDocId(String docId) {
		this.docId = docId;
	}
	
	/**
	 * @return the action
	 */
	public Action getAction() {
		return action;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(Action action) {
		this.action = action;
	}
	/**
	 * @return the state
	 */
	public State getState() {
		return this.state;
	}
	/**
	 * @param docId
	 * @param state
	 * @param action
	 */
	public GenericPojo(String docId, State state, Action action) {
		super();
		this.docId = docId;
		this.state = state;
		this.action = action;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GenericPojo [" + (docId != null ? "docId=" + docId + ", " : "")
				+ (state != null ? "state=" + state + ", " : "") + (action != null ? "action=" + action : "") + "]";
	}
	
	
	
}
