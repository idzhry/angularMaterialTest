package com.woodpecker.webframework.util;

import java.lang.reflect.Field;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

public class WoodpeckerUtil {
	
	/**
	 * 取得UUID
	 * @return UUID
	 */
	public static String createUUID() {
		String rtn = UUID.randomUUID().toString();
		return rtn;
	}
	
	/**
	 * Get hex string from byte array
	 */
	public static String toHexString(byte[] res) {
		StringBuffer sb = new StringBuffer(res.length << 1);
		for (int i = 0; i < res.length; i++) {
			String digit = Integer.toHexString(0xFF & res[i]);
			if (digit.length() == 1) {
				digit = '0' + digit;
			}
			sb.append(digit);
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * @param object
	 * @return
	 */
	public static void generateCreateBy(Object object) {
		Class<?> reflectClass = object.getClass();
		Field[] fields = reflectClass.getDeclaredFields();
		String fieldName;
//		UserVo loginInfo = null;
//    	loginInfo = FJLoginUserInfo.getUserInfo();
		for(Field f : fields) {
			f.setAccessible(true);
			fieldName = StringUtils.lowerCase(f.getName());
			try {
				if (StringUtils.equals("uid", fieldName)) {
					if (StringUtils.isEmpty((String)f.get(object))) {
						f.set(object, UUID.randomUUID().toString());
					}
				}
				else if (StringUtils.equals("crtdtm", fieldName)) {
					f.set(object, DateUtil.getSysTimestamp());
				}
				else if (StringUtils.equals("crtduser", fieldName)) {
					//todo 注意替换代码
//					if (loginInfo != null) {
//						f.set(object, loginInfo.getUserId());
//					} else {
//						f.set(object, "woodpecker");
//					}
					f.set(object, "woodpecker");
				}
				else if (StringUtils.equals("updttm", fieldName)) {
					f.set(object, DateUtil.getSysTimestamp());
				}
				else if (StringUtils.equals("updtuser", fieldName)) {
					//todo 注意替换代码
//					if (loginInfo != null) {
//						f.set(object, loginInfo.getUserId());
//					} else {
//						f.set(object, "woodpecker");
//					}
					f.set(object, "woodpecker");
				}
				else if (StringUtils.equals("delflg", fieldName)) {
					f.set(object, "0");
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void generateUpdateBy(Object object) {
		Class<?> reflectClass = object.getClass();
		Field[] fields = reflectClass.getDeclaredFields();
		String fieldName;
//		UserVo loginInfo = null;
//    	loginInfo = FJLoginUserInfo.getUserInfo();
		for(Field f : fields) {
			f.setAccessible(true);
			fieldName = StringUtils.lowerCase(f.getName());
			try {
				if (StringUtils.equals("updttm", fieldName)) {
					f.set(object, DateUtil.getSysTimestamp());
				}
				else if (StringUtils.equals("updtuser", fieldName)) {
//					if (loginInfo != null) {
//						f.set(object, loginInfo.getUserId());
//					} else {
//						f.set(object, "woodpecker");
//					}
					f.set(object, "woodpecker");
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
