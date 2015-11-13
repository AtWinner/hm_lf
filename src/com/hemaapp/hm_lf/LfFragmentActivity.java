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
		case 0:// ����������ʧ��
			int error_code = baseResult.getError_code();
			switch (error_code) {
			case 102:// �������
				XtomActivityManager.finishAll();
				//TODO ��¼������ת
//				Intent it = new Intent(mContext, LoginActivity.class);
//				startActivity(it);
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
}
