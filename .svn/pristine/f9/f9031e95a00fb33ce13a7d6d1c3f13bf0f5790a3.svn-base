package com.hemaapp.hm_lf;

import xtom.frame.XtomConfig;
import xtom.frame.util.XtomLogger;
import xtom.frame.util.XtomSharedPreferencesUtil;

import com.hemaapp.HemaConfig;
import com.hemaapp.LfConfig;
import com.hemaapp.hm_FrameWork.HemaApplication;
import com.hemaapp.hm_lf.db.SysInfoDBHelper;
import com.hemaapp.hm_lf.db.UserDBHelper;
import com.hemaapp.hm_lf.model.SysInitInfo;
import com.hemaapp.hm_lf.model.User;

/**
 * 高铁捎带品自定义Application
 */
public class LfApplication extends HemaApplication {
	private static final String TAG = LfApplication.class.getSimpleName();
	private SysInitInfo sysInitInfo;// 系统初始化信息
	private User user;
	private static LfApplication application;

	public static LfApplication getInstance() {
		return application;
	}

	@Override
	public void onCreate() {
		application = this;
		XtomConfig.LOG = LfConfig.DEBUG;
		XtomConfig.TIMEOUT_READ_HTTP = LfConfig.TIMEOUT_HTTP;
		HemaConfig.UMENG_ENABLE =LfConfig.UMENG_ENABLE;
		String iow = XtomSharedPreferencesUtil.get(this, "imageload_onlywifi");
		XtomConfig.IMAGELOAD_ONLYWIFI = "true".equals(iow);
		XtomLogger.i(TAG, "onCreate");
		super.onCreate();
	}

	/**
	 * @return 当前用户
	 */
	public User getUser() {
		if (user == null) {
			UserDBHelper helper = new UserDBHelper(this);
			String username = XtomSharedPreferencesUtil.get(this, "username");
			user = helper.select(username);
			helper.close();
		}
		return user;
	}

	/**
	 * 设置保存当前用户
	 * 
	 * @param sysInitInfo
	 *            当前用户
	 */
	public void setUser(User user) {
		this.user = user;
		if (user != null) {
			UserDBHelper helper = new UserDBHelper(this);
			helper.insertOrUpdate(user);
			helper.close();
		}
	}

	/**
	 * @return 系统初始化信息
	 */
	public SysInitInfo getSysInitInfo() {
		if (sysInitInfo == null) {
			SysInfoDBHelper helper = new SysInfoDBHelper(this);
			sysInitInfo = helper.select();
			helper.close();
		}
		return sysInitInfo;
	}

	/**
	 * 设置保存系统初始化信息
	 * 
	 * @param sysInitInfo
	 *            系统初始化信息
	 */
	public void setSysInitInfo(SysInitInfo sysInitInfo) {
		this.sysInitInfo = sysInitInfo;
		if (sysInitInfo != null) {
			SysInfoDBHelper helper = new SysInfoDBHelper(this);
			helper.insertOrUpdate(sysInitInfo);
			helper.close();
		}
	}
}
