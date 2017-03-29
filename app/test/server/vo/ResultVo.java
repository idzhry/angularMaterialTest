package com.woodpecker.webframework.vo;

import org.springframework.util.StringUtils;

public class ResultVo {
	/** 是否成功 */
    private boolean success = true;
    /** 消息内容 */
    private String message = "OK";
    
    /**
     * 构造执行结果
     */
    public ResultVo() {
    	
    }

    /**
     * 构造执行结果
     * @param success 是否成功
     */
    public ResultVo(boolean success) {
        this.success = success;
    }

    /**
     * 构造执行结果
     * @param success 是否成功
     * @param message 消息内容
     */
    public ResultVo(boolean success, String message) {
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
		if (!StringUtils.isEmpty(message)) {
			success = false;
		}
		this.message = message;
	}
}
