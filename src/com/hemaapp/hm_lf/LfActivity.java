package com.hemaapp.hm_lf;

import xtom.frame.XtomActivityManager;
import xtom.frame.net.XtomNetWorker;
import xtom.frame.util.XtomSharedPreferencesUtil;
import android.content.Intent;

import com.hemaapp.hm_FrameWork.HemaActivity;
import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.HemaNetWorker;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_lf.activity.LoginActivity;
import com.hemaapp.hm_lf.model.User;

/**
 * ��дactivity
 * @author Wen
 * @author HuFanglin
 *
 */
public abstract class LfActivity extends HemaActivity {

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
		case 0:// ����������ʧ��
			int error_code = baseResult.getError_code();
			switch (error_code) {
			case 102:// �������
				XtomActivityManager.finishAll();
				//TODO ��¼������ת
				Intent it = new Intent(mContext, LoginActivity.class);
				startActivity(it);
				return true;
			default:
				break;
			}
		case XtomNetWorker.FAILED_HTTP:// �����쳣
		case XtomNetWorker.FAILED_DATAPARSE:// �����쳣
		case XtomNetWorker.FAILED_NONETWORK:// ������
			break;
		}
		return false;
	}

	// ------------------------���������Ŀ�Զ��巽��---------------------------
	/**
	 * ��ȡϵͳ���
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
	 * ����ϵͳ���
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

	// ------------------------���������Ŀ�Զ��巽��---------------------------
	
		/**
		 * �����������
		 * 
		 * @param cityName
		 */
		public void saveCityName(String cityName) {
			XtomSharedPreferencesUtil.save(this, "city_name", cityName);
		}

		/**
		 * @return ��ȡ��������
		 */
		public String getCityName() {
			return XtomSharedPreferencesUtil.get(this, "city_name");
		}

		/**
		 * �������id
		 * 
		 * @param cityId
		 */
		public void saveCityId(String cityId) {
			XtomSharedPreferencesUtil.save(this, "city_id", cityId);
		}

		/**
		 * @return ��ȡ����id
		 */
		public String getCityId() {
			return XtomSharedPreferencesUtil.get(this, "city_id");
		}
	
}