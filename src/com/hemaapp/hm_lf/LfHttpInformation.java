package com.hemaapp.hm_lf;

import com.hemaapp.HemaConfig;
import com.hemaapp.LfConfig;
import com.hemaapp.hm_FrameWork.HemaHttpInfomation;
import com.hemaapp.hm_lf.model.SysInitInfo;

/**
 * 网络请求信息枚举类
 */
public enum LfHttpInformation implements HemaHttpInfomation {
	/**
	 * 登录
	 */
	CLIENT_LOGIN(HemaConfig.ID_LOGIN, "client_login", "登录", false),
	// 注意登录接口id必须为HemaConfig.ID_LOGIN
	/**
	 * 后台服务接口根路径
	 */
	SYS_ROOT(0, LfConfig.SYS_ROOT, "后台服务接口根路径", true),
	/**
	 * 系统初始化
	 */
	INIT(1, "index.php/webservice/index/init", "系统初始化", false),
	/**
	 * 验证用户名是否合法
	 */
	CLIENT_VERIFY(2, "client_verify", "验证用户名是否合法", false),
	/**
	 * 申请随机验证码
	 */
	CODE_GET(3, "code_get", "申请随机验证码", false),
	/**
	 * 验证随机码
	 */
	CODE_VERIFY(4, "code_verify", "验证随机码", false),
	/**
	 * 用户注册
	 */
	CLIENT_ADD(5, "client_add", "用户注册", false),
	/**
	 * 上传文件（图片，音频，视频）
	 */
	FILE_UPLOAD(6, "file_upload", "上传文件（图片，音频，视频）", false),
	/**
	 * 重设密码
	 */
	PASSWORD_RESET(7, "password_reset", "重设密码", false),
	/**
	 * 退出登录
	 */
	CLIENT_LOGINOUT(8, "client_loginout", "退出登录", false),

	/**
	 * 获取支付宝交易签名串
	 */
	ALIPAY(9, "OnlinePay/Alipay/alipaysign_get.php", "获取支付宝交易签名串", false),
	/**
	 * 获取银联交易签名串
	 */
	UNIONPAY(10, "OnlinePay/Unionpay/unionpay_get.php", "获取银联交易签名串", false),
	/**
	 * 获取微信交易签名串接口
	 */
	WEIXINPAY_GET(11, "OnlinePay/Weixinpay/weixinpay_get.php", "获取微信交易签名串接口", false),
	
	;

	
	private int id;// 对应NetTask的id
	private String urlPath;// 请求地址
	private String description;// 请求描述
	private boolean isRootPath;// 是否是根路径

	private LfHttpInformation(int id, String urlPath, String description,
			boolean isRootPath) {
		this.id = id;
		this.urlPath = urlPath;
		this.description = description;
		this.isRootPath = isRootPath;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getUrlPath() {
		if (isRootPath)
			return urlPath;

		String path = SYS_ROOT.urlPath + urlPath;

		if (this.equals(INIT))
			return path;

		LfApplication application = LfApplication.getInstance();
		SysInitInfo info = application.getSysInitInfo();
		path = info.getSys_web_service() + urlPath;

		 if (this.equals(ALIPAY))
			 path = info.getSys_plugins() + urlPath;
		
		 if (this.equals(UNIONPAY))
			 path = info.getSys_plugins() + urlPath;
		 
		 if(this.equals(WEIXINPAY_GET))
			 path = info.getSys_plugins() + urlPath;

		return path;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public boolean isRootPath() {
		return isRootPath;
	}

}
