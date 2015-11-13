package com.hemaapp.hm_lf;

import xtom.frame.XtomActivityManager;
import xtom.frame.net.XtomNetWorker;
import xtom.frame.util.XtomSharedPreferencesUtil;

import com.hemaapp.hm_FrameWork.HemaFragmentActivity;
import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.HemaNetWorker;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_lf.model.User;

public abstract class LfFragmentActivity extends HemaFragmentActivity {

	@Override
	protected HemaNetWorker initNetWorker() {
		return new LfNetWorker(mContext);
	}

	@Override
	public LfNetWorker getNetWorker() {
		return (LfNetWorker)super.getNetWorker();
	}
	
	@Override
	public LfApplication getApplicationContext() {
		return (LfApplication) super.getApplicationContext();
	}
	
	@Override
	public boolean onAutoLoginFailed(HemaNetWorker netWorker,
			HemaNetTask netTask, int failedType, HemaBaseResult baseResult) {
		switch (failedType) {
		case 0:// 服务器处理失败
			int error_code = baseResult.getError_code();
			switch (error_code) {
			case 102:// 密码错误
				XtomActivityManager.finishAll();
				//TODO 登录错误跳转
//				Intent it = new Intent(mContext, LoginActivity.class);
//				startActivity(it);
				return true;
			default:
				break;
			}
		case XtomNetWorker.FAILED_HTTP:// 网络异常
		case XtomNetWorker.FAILED_DATAPARSE:// 数据异常
		case XtomNetWorker.FAILED_NONETWORK:// 无网络
			break;
		}
		return false;
	}

	// ------------------------下面填充项目自定义方法---------------------------
	/**
	 * 获取系统类别
	 * 
	 * @return
	 */
	public String getSystype() {
		LfApplication application = LfApplication.getInstance();
		User user = application.getUser();
		if (user != null) {
			String key = "systype_" + user.getId();
			return XtomSharedPreferencesUtil.get(mContext, key);
		}
		return null;
	}

	/**
	 * 保存系统类别
	 * 
	 * @param systype
	 */
	public void saveSystype(String systype) {
		LfApplication application = LfApplication.getInstance();
		User user = application.getUser();
		if (user != null) {
			String key = "systype_" + user.getId();
			XtomSharedPreferencesUtil.save(mContext, key, systype);
		}
	}
}
