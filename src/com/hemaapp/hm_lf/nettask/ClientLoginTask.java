/*
 * Copyright (C) 2014 The Android Client Of Demo Project
 * 
 *     The BeiJing PingChuanJiaHeng Technology Co., Ltd.
 * 
 * Author:Yang ZiTian
 * You Can Contact QQ:646172820 Or Email:mail_yzt@163.com
 */
package com.hemaapp.hm_lf.nettask;

import java.util.HashMap;

import org.json.JSONObject;

import com.hemaapp.hm_lf.LfHttpInformation;
import com.hemaapp.hm_lf.LfNetTask;
import com.hemaapp.hm_lf.model.User;
import com.hemaapp.hm_FrameWork.result.HemaArrayResult;

import xtom.frame.exception.DataParseException;

/**
 * µÇÂ¼
 */
public class ClientLoginTask extends LfNetTask {

	public ClientLoginTask(LfHttpInformation information,
			HashMap<String, String> params) {
		super(information, params);
	}

	public ClientLoginTask(LfHttpInformation information,
			HashMap<String, String> params, HashMap<String, String> files) {
		super(information, params, files);
	}

	@Override
	public Object parse(JSONObject jsonObject) throws DataParseException {
		return new Result(jsonObject);
	}

	private class Result extends HemaArrayResult<User> {

		public Result(JSONObject jsonObject) throws DataParseException {
			super(jsonObject);
		}

		@Override
		public User parse(JSONObject jsonObject) throws DataParseException {
			return new User(jsonObject);
		}
	}
}
