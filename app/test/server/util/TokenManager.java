package com.woodpecker.webframework.util;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.woodpecker.webframework.bussiness.user.dao.WTTokenMapper;
import com.woodpecker.webframework.bussiness.user.entity.WTToken;
import com.woodpecker.webframework.bussiness.user.entity.WTTokenExample;

public class TokenManager {
    
    @Autowired
    private SystemConfigUtil systemConfigUtil;
    
	@Autowired
	private WTTokenMapper wtTokenMapper;
    
    /**
     * 生成Token,然后把Token追加到response的Cookie中
     * @param response 响应信息
     * @param userId 用户ID
     * @return token标识
     */
    public WTToken writeTokenToDatabase(HttpServletResponse response, String uuid) {
		// 取得当前时间
        Date now = new Date();
        long nowTime = now.getTime();
    	// 删除旧token值
    	WTTokenExample wtTokenExample = new WTTokenExample();
    	wtTokenExample.createCriteria().andUseridEqualTo(uuid);
    	wtTokenMapper.deleteByExample(wtTokenExample);
    	// 写入新的token值
		WTToken wtToken = new WTToken();
		String token = WoodpeckerUtil.createUUID();
		wtToken.setUid(token);
		wtToken.setUserid(uuid);
		wtToken.setExpitytm(String.valueOf(nowTime + systemConfigUtil.getTokenTimeout()));
		WoodpeckerUtil.generateCreateBy(wtToken);
		wtTokenMapper.insert(wtToken);
		
		return wtToken;
    }
    
    /**
     * 生成Token,然后把Token追加到response的Cookie中
     * @param response 响应信息
     * @param userId 用户ID
     */
    public void cleanTokenFromDatabase(HttpServletResponse response, String uuid) {
    	// 从数据库中删除旧token值
    	WTTokenExample wtTokenExample = new WTTokenExample();
    	wtTokenExample.createCriteria().andUseridEqualTo(uuid);
    	wtTokenMapper.deleteByExample(wtTokenExample);
    }

    /**
     * 取得客户端的token信息
     * @param request 请求信息
     * @return token
     */
    public String getUUId(HttpServletRequest request) {
    	String uuid = null;
    	String token = request.getHeader(systemConfigUtil.getTokenName());
    	if (!StringUtils.isEmpty(token)) {
    		WTTokenExample wtTokenExample = new WTTokenExample();
        	wtTokenExample.createCriteria().andUidEqualTo(token);
        	List<WTToken> wtTokenList = wtTokenMapper.selectByExample(wtTokenExample);
        	if (wtTokenList.size() > 0) {
        		WTToken wtToken = (WTToken)wtTokenList.get(0);
        		if (checkToken(wtToken.getUid())) {
        			uuid = wtToken.getUserid();
        			// 取得当前时间
                    Date now = new Date();
                    long nowTime = now.getTime();
        			wtToken.setExpitytm(String.valueOf(nowTime + systemConfigUtil.getTokenTimeout()));
                	WoodpeckerUtil.generateUpdateBy(wtToken);
                	wtTokenMapper.updateByPrimaryKeySelective(wtToken);
        		} else {
        			// 删除旧token值
                	wtTokenMapper.deleteByExample(wtTokenExample);
                    return uuid;
        		}
        	}
    	}
    	
    	return uuid;
    }
    
    /**
     * 验证客户端传过来的Token是否有效
     * @param token
     * @return 验证结果（true：Token有效   false：Token无效）
     */
    public boolean checkToken(String token) {
    	
    	WTTokenExample wtTokenExample = new WTTokenExample();
    	wtTokenExample.createCriteria().andUidEqualTo(token);
    	List<WTToken> wtTokenList = wtTokenMapper.selectByExample(wtTokenExample);
    	if (wtTokenList.size() > 0) {
    		WTToken wtToken = (WTToken)wtTokenList.get(0);
    		// 取得当前时间
            Date now = new Date();
            long nowTime = now.getTime();
            // 取得token的失效时间
            long checkDate = Long.parseLong(wtToken.getExpitytm());
            // 如果token已经失效，删除token，返回false
            if (nowTime >= checkDate) {
            	return false;
            } else {
            	return true;
            }
    	}
    	return false;
    }
    
    /**
     * 清理token的定时执行处理
     */
    public void tokenCleanTask() {
    	// 从数据库中删除旧token值
    	Date now = new Date();
        long nowTime = now.getTime();
        
    	WTTokenExample wtTokenExample = new WTTokenExample();
    	wtTokenExample.createCriteria().andExpitytmLessThanOrEqualTo(String.valueOf(nowTime));
    	wtTokenMapper.deleteByExample(wtTokenExample);
    }
}
