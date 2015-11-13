package com.hemaapp.hm_lf;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import xtom.frame.XtomActivityManager;
import xtom.frame.image.cache.XtomImageCache;
import xtom.frame.util.XtomBaseUtil;
import xtom.frame.util.XtomTimeUtil;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;


/**
 * ������
 */
public class LfUtil {

	public static void exit(Context context) {
		XtomActivityManager.finishAll();
	}

	/**
	 * ��ȡ��ǰ�汾��
	 * 
	 * @param context
	 * @return ��ǰ�汾��
	 */
	public static final String getAppVersion(Context context) {
		String version = null;
		try {
			version = XtomBaseUtil.getAppVersionName(context);
		} catch (NameNotFoundException e) {
			version = "1.0.0";
		}
		return version;
	}

	/**
	 * �����Ƿ���ǰ̨����
	 * 
	 * @return
	 */
	public static boolean isAppOnForeground(Context context) {
		// Returns a list of application processes that are running on the
		// device
		ActivityManager activityManager = (ActivityManager) context
				.getApplicationContext().getSystemService(
						Context.ACTIVITY_SERVICE);
		String packageName = context.getApplicationContext().getPackageName();
		List<RunningAppProcessInfo> appProcesses = activityManager
				.getRunningAppProcesses();
		if (appProcesses == null)
			return false;

		for (RunningAppProcessInfo appProcess : appProcesses) {
			// The name of the process that this object is associated with.
			if (appProcess.processName.equals(packageName)
					&& appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
				return true;
			}
		}

		return false;
	}

	public static boolean isNeedUpdate(String current, String service) {
		String[] c = current.split("\\."); // 2.2.3
		String[] s = service.split("\\."); // 2.4.0
		long fc = Long.valueOf(c[0]); // 2
		long fs = Long.valueOf(s[0]); // 2
		if (fc > fs)
			return false;
		else if (fc < fs) {
			return true;
		} else {
			long sc = Long.valueOf(c[1]); // 2
			long ss = Long.valueOf(s[1]); // 4
			if (sc > ss)
				return false;
			else if (sc < ss) {
				return true;
			} else {
				long tc = Long.valueOf(c[2]); // 3
				long ts = Long.valueOf(s[2]); // 0
				if (tc >= ts)
					return false;
				else
					return true;
			}
		}
	}

	/**
	 * ת��ʱ����ʾ��ʽ(�뵱ǰϵͳʱ��Ƚ�),�ڷ������⡢���Ӻ�����ʱʹ��
	 * 
	 * @param time
	 *            ʱ���ַ���
	 * @return String
	 */
	public static String transTime(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.getDefault());
		String current = XtomTimeUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss");
		String dian24 = XtomTimeUtil.TransTime(current, "yyyy-MM-dd")
				+ " 24:00:00";
		String dian00 = XtomTimeUtil.TransTime(current, "yyyy-MM-dd")
				+ " 00:00:00";
		Date now = null;
		Date date = null;
		Date d24 = null;
		Date d00 = null;
		try {
			now = sdf.parse(current); // ����ǰʱ��ת��Ϊ����
			date = sdf.parse(time); // �������ʱ�����ת��Ϊ����
			d24 = sdf.parse(dian24);
			d00 = sdf.parse(dian00);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long diff = now.getTime() - date.getTime(); // ��ȡ����֮���ʱ���ֵ
		long min = diff / (60 * 1000);
		if (min <= 5)
			return "�ո�";
		if (min < 60)
			return min + "����ǰ";

		if (now.getTime() <= d24.getTime() && date.getTime() >= d00.getTime())
			return "����" + XtomTimeUtil.TransTime(time, "HH:mm");

		int sendYear = Integer.valueOf(XtomTimeUtil.TransTime(time, "yyyy"));
		int nowYear = Integer.valueOf(XtomTimeUtil.TransTime(current, "yyyy"));
		if (sendYear < nowYear)
			return XtomTimeUtil.TransTime(time, "yyyy-MM-dd HH:mm");
		else
			return XtomTimeUtil.TransTime(time, "MM-dd HH:mm");
	}

	/**
	 * ת��ʱ����ʾ��ʽ(�뵱ǰϵͳʱ��Ƚ�),����ʾ��ʱ�����ʱ��ʱʹ��
	 * 
	 * @param time
	 *            ʱ���ַ���
	 * @return String
	 */
	public static String transTimeChat(String time) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
					Locale.getDefault());
			String current = XtomTimeUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss");
			String dian24 = XtomTimeUtil.TransTime(current, "yyyy-MM-dd")
					+ " 24:00:00";
			String dian00 = XtomTimeUtil.TransTime(current, "yyyy-MM-dd")
					+ " 00:00:00";
			Date now = null;
			Date date = null;
			Date d24 = null;
			Date d00 = null;
			try {
				now = sdf.parse(current); // ����ǰʱ��ת��Ϊ����
				date = sdf.parse(time); // �������ʱ�����ת��Ϊ����
				d24 = sdf.parse(dian24);
				d00 = sdf.parse(dian00);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			long diff = now.getTime() - date.getTime(); // ��ȡ����֮���ʱ���ֵ
			long min = diff / (60 * 1000);
			if (min <= 5)
				return "�ո�";
			if (min < 60)
				return min + "����ǰ";

			if (now.getTime() <= d24.getTime()
					&& date.getTime() >= d00.getTime())
				return "����" + XtomTimeUtil.TransTime(time, "HH:mm");

			int sendYear = Integer
					.valueOf(XtomTimeUtil.TransTime(time, "yyyy"));
			int nowYear = Integer.valueOf(XtomTimeUtil.TransTime(current,
					"yyyy"));
			if (sendYear < nowYear)
				return XtomTimeUtil.TransTime(time, "yyyy-MM-dd HH:mm");
			else
				return XtomTimeUtil.TransTime(time, "MM-dd HH:mm");
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * �����ֻ��ź�������ʾ
	 * 
	 * @param old
	 *            ��Ҫ���ص��ֻ��Ż�����
	 * @param keytype
	 *            1�ֻ�2����
	 * @return
	 */
	public static String hide(String old, String keytype) {
		try {
			if ("1".equals(keytype))
				return old.substring(0, 3) + "****" + old.substring(7, 11);
			else {
				StringBuilder sb = new StringBuilder();
				String[] s = old.split("@");
				int l = s[0].length();
				int z = l / 3;
				sb.append(s[0].substring(0, z));
				int y = l % 3;
				for (int i = 0; i < z + y; i++)
					sb.append("*");
				sb.append(s[0].substring(z * 2 + y, l));
				sb.append("@");
				if (s[1] == null) {

				}
				sb.append(s[1]);
				return sb.toString();
			}
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * ʹ���������ʽ��֤������ֻ����Ƿ�Ϸ�
	 * @param phoneNumber �ֻ���
	 * @return �Ϸ�:true;�Ƿ�false
	 */
	public static boolean checkPhoneNumber(String phoneNumber)
	{
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
		Matcher m = p.matcher(phoneNumber);  
		Log.e("��֤�ֻ���", String.valueOf(m.matches()));
		return m.matches();
	}
	
	/**
	 * ʹ���������ʽ��֤�����ַ�Ƿ�Ϸ�
	 * @param emailAddress �����ַ
	 * @return �Ϸ�:true;�Ƿ�false
	 */
	public static boolean checkEmail(String emailAddress)
	{
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Matcher m = pattern.matcher(emailAddress);
		Log.e("��֤�����Ƿ�Ϸ�", String.valueOf(m.matches()));
		return m.matches();
	}
	
	 /** 
     * �����ֻ��ķֱ��ʴ� dp �ĵ�λ ת��Ϊ px(����) 
     */  
    public static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
  
    /** 
     * �����ֻ��ķֱ��ʴ� px(����) �ĵ�λ ת��Ϊ dp 
     */  
    public static int px2dip(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    }  
    
    /**
     * ��ȡ�ߵµ�ͼ������Ϣ
     * @param Code
     * @return
     */
    public static String getAMapErrorString(int rCode)
	{
		switch(rCode)
		{
		case 21:
			return "IO �����쳣";
		case 22:
			return "���Ӵ����쳣�����������Ƿ�ͨ��";
		case 23:
			return "���ӳ�ʱ";
		case 24:
			return "��Ч�Ĳ���";
		case 25:
			return "��ָ���쳣";
		case 26:
			return "url �쳣";
		case 27:
			return "δ֪������";
		case 28:
			return "���ӷ�����ʧ��";
		case 29:
			return "ͨ��Э���������";
		case 30:
			return "http ����ʧ��";
		case 31:
			return "�������쳣";
		case 32:
			return "key ��Ȩ��֤ʧ�ܣ�����key�󶨵�sha1ֵ��packageName��apk�Ƿ��Ӧ";
		case 33:
			return "���񷵻���Ϣʧ��";
		case 34:
			return "����ά���У����Ժ�";
		case 35:
			return "��ǰIP��������������";
		case 36:
			return "�������������ο�����ָ�ϵ�������";
		}
		return "���ݻ�ȡ�ɹ�";
	}
    
    /**
     * ��ȡassets�е�ͼƬ��Դ
     * @param fileName
     * @param context
     * @return
     */
    public static Bitmap getImageFromAssetsFile(String fileName, Context context)  
    {  
        Bitmap image = null;  
        AssetManager am = context.getResources().getAssets();  
        try  
        {  
            InputStream is = am.open(fileName);  
            image = BitmapFactory.decodeStream(is);  
            is.close();  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
        return image;  
    
    }  
    /**
     * ���ݾ����ȡ�ߵµ�ͼ��Ӧ�����ż��� ��Χ3~16��3���
     * @param Distance ��λ����
     * @return
     */
    public static int getZoomTo(double Distance)
    {
    	Distance = Math.abs(Distance);
    	double Temp = 4000000;
    	if(Distance > 4000000)
    		return 3;
    	if(Distance <= 488)
    		return 16;
    	int State = 2;
    	while(true)
    	{
        	if(Distance >= Temp)
        		return State + 1;
    		Temp /= 2.0;
    		State++;
    	}
    }
    /**
     * ��ȡ��Ļ����
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context)
    {
    	WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
    	DisplayMetrics dm = new DisplayMetrics();
    	wm.getDefaultDisplay().getMetrics(dm);
    	return dm.widthPixels;
    }
    /**
     * ��ȡ��Ļ�߶�
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context)
    {
    	WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
    	DisplayMetrics dm = new DisplayMetrics();
    	wm.getDefaultDisplay().getMetrics(dm);
    	return dm.heightPixels;
    }
    
    public static String getCacheSize(Activity mContext)
	{
		double CacheSize = XtomImageCache.getInstance(mContext).getCacheSize();
		int i =0;
		while(CacheSize > 1024)
		{
			CacheSize /= 1024.0;
			i++;
		}
		String CacheSizeStr;
		DecimalFormat dcmFmt = new DecimalFormat("0.0");
		switch (i) {
		case 0:
			CacheSizeStr = dcmFmt.format(CacheSize) + "B";
			break;
		case 1:
			CacheSizeStr = dcmFmt.format(CacheSize) + "KB";
			break;
		case 2:
			CacheSizeStr = dcmFmt.format(CacheSize) + "MB";
			break;
		default:
			CacheSizeStr = dcmFmt.format(CacheSize) + "GB";
			break;
		}
		return CacheSizeStr;
	}
}