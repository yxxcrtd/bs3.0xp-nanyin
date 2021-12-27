package com.joyintech.sso.common;

public class MessageCode {

	public static final MessageCode SUCCESS = new MessageCode("success","成功");
	public static final MessageCode FAIL = new MessageCode("fail","未知异常");
	public static final MessageCode FAIL_USERNAME_NULL = new MessageCode("10000","用户名不能为空");
	public static final MessageCode FAIL_PASSWORD_NULL = new MessageCode("10001","密码不能为空");
	public static final MessageCode FAIL_USERNAME_NOT_EXISTS = new MessageCode("10002","用户名不存在");
	public static final MessageCode FAIL_PASSWORD_ERROR = new MessageCode("10003","密码错误");
	//public static final MessageCode COOKIE_NOT_EXISTS = new MessageCode("10004","您没有权限访问或者cookie已经失效，请重新登录");
	public static final MessageCode FAIL_PLATFORMID_NULL = new MessageCode("10005","平台id不可为空");
	public static final MessageCode PLATFORM_DIABLED = new MessageCode("10006","应用平台已禁用,请重新登录");
	public static final MessageCode PLATFORM_NULL = new MessageCode("10006","用户没有授权应用平台,请授权");
	public static final MessageCode LOGIN_TIMEOUT = new MessageCode("10007","登录已超时,请重新登录");


	public static final MessageCode FAIL_PLATFORM_ID_ERROR = new MessageCode("10011","平台代码不能为空");
	public static final MessageCode FAIL_PLATFORM_NAME_ERROR = new MessageCode("10012","平台名称不能为空");
	public static final MessageCode FAIL_PLATFORM_URL_ERROR = new MessageCode("10013","平台地址不能为空");
	public static final MessageCode FAIL_PLATFORM_WEIGHT_ERROR = new MessageCode("10014","权重不能为空");
	public static final MessageCode FAIL_PLATFORM_STATUS_ERROR = new MessageCode("10015","状态不能为空");
	public static final MessageCode FAIL_PLATFORM_CODE_EXISTS = new MessageCode("10016","平台编码已存在");
	public static final MessageCode FAIL_PLATFORM_USERID_ERROR = new MessageCode("10017","用户代码不能为空");
	public static final MessageCode FAIL_PLATFORM_USERID_EXISTS = new MessageCode("10016","用户授权平台信息已存在");
	public static final MessageCode FAIL_COOKIEKEY_NULL = new MessageCode("10018","身份标识不能为空");
	public static final MessageCode FAIL_PLATFORM_CODE_NULL = new MessageCode("10019","平台编码不能为空");
	public static final MessageCode FAIL_PLATFORM_HOST_ERROR = new MessageCode("100203","平台主机不能为空");
	public static final MessageCode FAIL_PLATFORM_INDEX_ERROR = new MessageCode("10021","主页地址不能为空");

	protected String code;
	protected String name;
	protected MessageCode(String code, String name) {
		this.code = code;
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
