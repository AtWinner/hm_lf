package com.hemaapp.hm_lf.nettask;

import java.util.HashMap;

import org.json.JSONObject;

import xtom.frame.exception.DataParseException;

import com.hemaapp.hm_lf.LfHttpInformation;
import com.hemaapp.hm_lf.LfNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
/**
 * 通用请求线程（不需要返回结果的，只需要获取是否成功）
 * @author Wen
 * @author HuFanglin
 *
 */
public class CurrentTask extends LfNetTask{

	public CurrentTask(LfHttpInformation information,
			HashMap<String, String> params) {
		super(information, params);
	}

	@Override
	public Object parse(JSONObject jsonObject) throws DataParseException {
		return new HemaBaseResult(jsonObject);
	}

}
