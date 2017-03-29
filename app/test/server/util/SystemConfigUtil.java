package com.woodpecker.webframework.util;

import org.springframework.beans.factory.annotation.Value;

/**
 * Spring的工具类，用来获得properties配置文件中的property信息
 */
public class SystemConfigUtil {

	/** 权限 存放token的request请求名 */
    @Value("${system.authority.request.header.token}")
    private String tokenName = null;

    /**
     * @return the tokenName
     */
    public String getTokenName() {
        return tokenName;
    }

    /** token 超时时间 */
    @Value("${system.authority.request.header.token.timeout}")
    private long tokenTimeout = 0;

    /**
     * @return the tokenTimeout
     */
    public long getTokenTimeout() {
        return tokenTimeout;
    }
    
    /** 系统功能点详细连接串 */
    @Value("${system.func.detail}")
    private String systemFuncDetail = null;

    /**
     * @return the systemFuncDetail
     */
    public String getSystemFuncDetail() {
        return systemFuncDetail;
    }
}