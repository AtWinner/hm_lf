package com.hemaapp.hm_lf.nettask;

import java.util.HashMap;

import org.json.JSONObject;

import com.hemaapp.hm_FrameWork.result.HemaArrayResult;
import com.hemaapp.hm_lf.LfHttpInformation;
import com.hemaapp.hm_lf.LfNetTask;
import com.hemaapp.hm_lf.model.SysInitInfo;

import xtom.frame.exception.DataParseException;

/**
 * 系统初始化
 */
public class InitTask extends LfNetTask {

	public InitTask(LfHttpInformation information,
			HashMap<String, String> params) {
		super(information, params);
	}

	public InitTask(LfHttpInformation information,
			HashMap<String, String> params, HashMap<String, String> files) {
		super(information, params, files);
	}

	@Override
	public Object parse(JSONObject jsonObject) throws DataParseException {
		return new Result(jsonObject);
	}

	private class Result extends HemaArrayResult<SysInitInfo> {

		public Result(JSONObject jsonObject) throws DataParseException {
			super(jsonObject);
		}

		@Override
		public SysInitInfo parse(JSONObject jsonObject)
				throws DataParseException {
			return new SysInitInfo(jsonObject);
		}

	}
}
