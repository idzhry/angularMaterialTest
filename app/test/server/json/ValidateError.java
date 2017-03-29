package com.woodpecker.webframework.json;

import java.io.Serializable;

/**
 * 校验错误内容
 *
 */
@SuppressWarnings("serial")
public class ValidateError implements Serializable{

    /** 错误对象 */
    private String objectName;
    
    /** 错误字段 */
    private String field;
    
    /** 错误字段值 */
    private String value;
    
    /** 错误消息内容 */
    private String validateMessage;
    
    /**
     * 构造校验错误内容
     */
    public ValidateError() {
    	
    }

	/**
	 * 构造校验错误内容
	 * @param objectName 错误对象
	 * @param field 错误字段
	 * @param value 错误字段值
	 * @param validateMessage 错误消息内容
	 */
	public ValidateError(String objectName, String field, String value, String validateMessage) {
		super();
		this.objectName = objectName;
		this.field = field;
		this.value = value;
		this.validateMessage = validateMessage;
	}

	/**
	 * @return the objectName
	 */
	public String getObjectName() {
		return objectName;
	}

	/**
	 * @param objectName the objectName to set
	 */
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the validateMessage
	 */
	public String getValidateMessage() {
		return validateMessage;
	}

	/**
	 * @param validateMessage the validateMessage to set
	 */
	public void setValidateMessage(String validateMessage) {
		this.validateMessage = validateMessage;
	}
}