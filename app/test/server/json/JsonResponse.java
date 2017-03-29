package com.woodpecker.webframework.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.woodpecker.webframework.vo.ResultVo;

/**
 * JSON OBJECT
 *
 */
@SuppressWarnings("serial")
public class JsonResponse implements Serializable{

    private static final String OK = "ok";
    private static final String ERROR = "error";

    /** execute result */
    private Meta meta;
    
    /** result data */
    private Object data;
    
    /** validate List */
    private List<ValidateError> validateErrorList = new ArrayList<>();
    
    /**
     * success
     * @return JSON OBJECT
     */
    public JsonResponse success() {
        this.meta = new Meta(true, OK);
        return this;
    }

    /**
     * success
     * @param data result data
     * @return JSON OBJECT
     */
    public JsonResponse success(Object data) {
        this.meta = new Meta(true, OK);
        this.data = data;
        return this;
    }
    
    /**
     * success
     * @param result 结果Vo
     * @param data result data
     * @return
     */
    public JsonResponse success(ResultVo result, Object data) {
        this.meta = new Meta(result.isSuccess()
        		           , result.getMessage());
        this.data = data;
        return this;
    }

    /**
     * failure
     * @return JSON OBJECT
     */
    public JsonResponse failure() {
        this.meta = new Meta(false, ERROR);
        return this;
    }

    /**
     * failure
     * @param message message
     * @return JSON OBJECT
     */
    public JsonResponse failure(String message) {
        this.meta = new Meta(false, message);
        return this;
    }

    /**
     * failure
     * @param message message
     * @param validateErrorList validateErrorList
     * @return JSON OBJECT
     */
    public JsonResponse failure(String message,List<ValidateError> validateErrorList) {
        this.meta = new Meta(false, message);
        this.validateErrorList = validateErrorList;
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public Object getData() {
        return data;
    }

	/**
	 * @param meta the meta to set
	 */
	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the validateErrorList
	 */
	public List<ValidateError> getValidateErrorList() {
		return validateErrorList;
	}

	/**
	 * @param validateErrorList the validateErrorList to set
	 */
	public void setValidateErrorList(List<ValidateError> validateErrorList) {
		this.validateErrorList = validateErrorList;
	}
    
}