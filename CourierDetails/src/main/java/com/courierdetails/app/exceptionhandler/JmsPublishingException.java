package com.courierdetails.app.exceptionhandler;

public class JmsPublishingException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JmsPublishingException(String msg) {
        super(msg);
    }

}
