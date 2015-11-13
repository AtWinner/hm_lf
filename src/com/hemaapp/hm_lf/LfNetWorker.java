package com.hemaapp.hm_lf;

import java.util.HashMap;

import xtom.frame.util.XtomDeviceUuidFactory;
import android.content.Context;

import com.hemaapp.LfConfig;
import com.hemaapp.hm_FrameWork.HemaNetWorker;
import com.hemaapp.hm_lf.nettask.ClientLoginTask;
import com.hemaapp.hm_lf.nettask.CurrentTask;
import com.hemaapp.hm_lf.nettask.InitTask;
/**
 * 网络请求工具类
 * @author Wen
 * @author HuFanglin
 *
 */
public class LfNetWorker extends HemaNetWorker {
	private Context mContext;

	public LfNetWorker(Context mContext) {
		super(mContext);
		this.mContext = mContext;
	}

	@Override
	public void clientLogin() {
	}

	@Override
	public boolean thirdSave() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 系统初始化
	 */
	public void init()
	{
		LfHttpInformation information = LfHttpInformation.INIT;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("lastloginversion", LfUtil.getAppVersion(mContext));// 版本号码(默认：1.0.0)
		params.put("devicetype", String.valueOf(LfConfig.DEVICETYPE));// 登陆所用的系统版本号
		params.put("device_sn", XtomDeviceUuidFactory.get(mContext));// 客户端硬件串号
		LfNetTask task = new InitTask(information, params);
		executeTask(task);
	}
	/**
	 * 登录
	 * @param username 用户名
	 * @param password 密码
	 */
	public void clientLogin(String username, String password) {
		LfHttpInformation information = LfHttpInformation.CLIENT_LOGIN;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);// 用户登录名 手机号或邮箱
		params.put("password", password); // 登陆密码 服务器端存储的是32位的MD5加密串
		params.put("devicetype", "2"); // 用户登录所用手机类型 1：苹果 2：安卓（方便服务器运维统计）
		String version = LfUtil.getAppVersion(mContext);
		params.put("lastloginversion", version);// 登陆所用的系统版本号
		params.put("submit", "提交");
		LfNetTask task = new ClientLoginTask(information, params);
		executeTask(task);
	}

	/**
	 * 验证用户名是否合法
	 * @param username
	 */
	public void clientVerify(String username)
	{
		LfHttpInformation information = LfHttpInformation.CLIENT_VERIFY;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		LfNetTask task = new CurrentTask(information, params);
		executeTask(task);
	}
	/**
	 * 用户退出
	 * @param token
	 */
	public void clientLoginOut(String token)
	{
		LfHttpInformation information = LfHttpInformation.CLIENT_LOGINOUT;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		LfNetTask task = new CurrentTask(information, params);
		executeTask(task);
	}
	

}

