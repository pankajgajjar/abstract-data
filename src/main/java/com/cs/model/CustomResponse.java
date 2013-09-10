package com.cs.model;


/**
 * The Class CustomResponse.
 */
public class CustomResponse {

	/** The html. */
	private String html;
	
	/** The events. */
	private String events;
	
	/** The elements. */
	private String elements;

	/**
	 * Instantiates a new custom response.
	 */
	public CustomResponse() {

	}

	/**
	 * Instantiates a new custom response.
	 *
	 * @param html the html
	 * @param events the events
	 * @param elements the elements
	 */
	public CustomResponse(String html, String events, String elements) {
		super();
		this.html = html;
		this.events = events;
		this.elements = elements;
	}

	/**
	 * Gets the html.
	 *
	 * @return the html
	 */
	public String getHtml() {
		return html;
	}

	/**
	 * Sets the html.
	 *
	 * @param html the new html
	 */
	public void setHtml(String html) {
		this.html = html;
	}

	/**
	 * Gets the events.
	 *
	 * @return the events
	 */
	public String getEvents() {
		return events;
	}

	/**
	 * Sets the events.
	 *
	 * @param events the new events
	 */
	public void setEvents(String events) {
		this.events = events;
	}

	/**
	 * Gets the elements.
	 *
	 * @return the elements
	 */
	public String getElements() {
		return elements;
	}

	/**
	 * Sets the elements.
	 *
	 * @param elemnts the new elements
	 */
	public void setElements(String elemnts) {
		this.elements = elemnts;
	}

}
