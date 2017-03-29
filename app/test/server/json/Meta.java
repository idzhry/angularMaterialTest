package com.woodpecker.webframework.json;

import java.io.Serializable;

/**
 * Execute Result
 *
 */
@SuppressWarnings("serial")
public class Meta implements Serializable{

	/** success flag */
    private boolean success;

	/** message */
    private String message;
    
    /**
     * constructor
     */
    public Meta() {
    	
    }

    /**
     * constructor
     * @param success success
     */
    public Meta(boolean success) {
        this.success = success;
    }

    /**
     * constructor
     * @param success success
     * @param message message
     */
    public Meta(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * @return the value of success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @return the value of message
     */
    public String getMessage() {
        return message;
    }

	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}