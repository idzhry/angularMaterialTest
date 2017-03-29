package com.woodpecker.webframework.exception;

import com.woodpecker.webframework.util.WoodpeckerContextUtil;

/**
 * Custom BusinessException
 * The exception that occurs at the point of service execution,
 * the exception of the correct execution of the user operation can be achieved
 * Business exception message will return to the call side
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private Object[] params;
	
	private String msgCode;

    /**
     * BusinessException constructor
     * @param e sourceException
     * @param msgCode msgCode
     * @param parms message parameter
     */
    public BusinessException(Throwable e,String msgCode,Object... parms) {
    	super(msgCode,e);
    	this.params = parms;
    	this.msgCode = msgCode;
    }

    /**
     * BusinessException constructor
     * @param msgCode msgCode
     * @param parms message parameter
     */
    public BusinessException(String msgCode,Object... parms) {
    	super(msgCode);
    	this.params = parms;
    	this.msgCode = msgCode;
    }
    
    @Override
    public String getMessage() {
    	String message = "";
    	try {
    		message = WoodpeckerContextUtil.getMessage(msgCode, params);
		} catch (Throwable te) {
			// When message fails, msgCode is used as the message content
			message = msgCode;
		}
    	return message;
    }

	/**
	 * @return the msgCode
	 */
	public String getMsgCode() {
		return msgCode;
	}
}
