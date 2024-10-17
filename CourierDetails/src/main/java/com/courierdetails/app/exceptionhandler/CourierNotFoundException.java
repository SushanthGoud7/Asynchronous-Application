package com.courierdetails.app.exceptionhandler;

public class CourierNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CourierNotFoundException(String msg) {
        super(msg);
    }

}
