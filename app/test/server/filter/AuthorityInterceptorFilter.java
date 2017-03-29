package com.woodpecker.webframework.filter;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.druid.util.StringUtils;
import com.woodpecker.webframework.bussiness.user.entity.WTUser;
import com.woodpecker.webframework.bussiness.user.service.WoodpeckerUserServiceI;
import com.woodpecker.webframework.exception.BusinessException;
import com.woodpecker.webframework.util.TokenManager;

public class AuthorityInterceptorFilter extends HandlerInterceptorAdapter  {

	@Autowired
	private WoodpeckerUserServiceI woodpeckerUserService;
	
	@Autowired
    private TokenManager tokenManager;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		AuthorityInstruction annotation = method.getAnnotation(AuthorityInstruction.class);
		if (annotation == null || OpenStatus.close == annotation.status() || PerType.LOW == annotation.perType()) {
			return true;// 没加注解 或 关闭验证的，认为不需要权限，放行
		}
		
		String uuid = tokenManager.getUUId(request);
		if (!StringUtils.isEmpty(uuid)) {
			
			WTUser wtUser = woodpeckerUserService.getUserInfo(uuid);
			if(wtUser != null){
				if(PerType.MIDDLE == annotation.perType()){
					return true;//只验证登录权限，有登录者放行。
				}
				if(PerType.HIGH == annotation.perType()) {
					boolean hasAuthority = woodpeckerUserService.verifyUserRole(uuid, annotation.ifcode());
					if (!hasAuthority) {
						throw new BusinessException(new Exception(), "msg.common.10009");
					}
					return true;//验证所有权限
				}
			}
		}
		throw new BusinessException(new Exception(), "msg.user.00005");
	}
	
}
