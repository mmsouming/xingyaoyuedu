package com.fancyfamily.primarylibrary.commentlibrary.util;


import android.content.Context;
import android.content.SharedPreferences;


/**
 * SharePreferences操作工具
 */
public class SharePrefUtil {
	private static String tag = SharePrefUtil.class.getSimpleName();
	private final static String SP_NAME = "config";
	private static SharedPreferences sp;

	public interface KEY {
        String function_is_first_in_app="function_is_first_in_app";//第一次进入app，登录后 提示进入激活璀璨卡界面
		String function_last_login_account = "function_last_login_account" ; //登陆的账号和密码 使用 '-' 分开
		String function_token = "function_token" ; //请求token
    }

	/**
	 * 保存布尔
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveBoolean(Context context, String key, boolean value) {
		if (sp == null){
			sp = context.getSharedPreferences(SP_NAME,0);
		}
		sp.edit().putBoolean(key, value).commit();
	}

	/**
	 * 保存字符
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveString(Context context, String key, String value) {
		if (sp == null){
			sp = context.getSharedPreferences(SP_NAME, 0);
		}
		sp.edit().putString(key, value).commit();
	}
	
	public static void clear(Context context){
		if (sp == null){
			sp = context.getSharedPreferences(SP_NAME, 0);
		}
		sp.edit().clear().commit();
	}

	/**
	 * 保存long
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveLong(Context context, String key, long value) {
		if (sp == null){
			sp = context.getSharedPreferences(SP_NAME, 0);
	    }
		sp.edit().putLong(key, value).commit();
		
	}

	/**
	 * 保存int
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveInt(Context context, String key, int value) {
		if (sp == null){
			sp = context.getSharedPreferences(SP_NAME, 0);
	    }
		sp.edit().putInt(key, value).commit();
	}

	/**
	 * 保存float
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveFloat(Context context, String key, float value) {
		if (sp == null){
			sp = context.getSharedPreferences(SP_NAME, 0);
	    }
		sp.edit().putFloat(key, value).commit();
	}

	/**
	 * 获取字符
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static String getString(Context context, String key, String defValue) {
		if (sp == null){
			sp = context.getSharedPreferences(SP_NAME, 0);
		}
		return sp.getString(key, defValue);
	}

	/**
	 * 获取int
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static int getInt(Context context, String key, int defValue) {
		if (sp == null){
			sp = context.getSharedPreferences(SP_NAME, 0);
	    }
		return sp.getInt(key, defValue);
	}

	/**
	 * 获取long
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static long getLong(Context context, String key, long defValue) {
		if (sp == null){
			sp = context.getSharedPreferences(SP_NAME, 0);
		}
		return sp.getLong(key, defValue);
	}

	/**
	 * 获取float
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static float getFloat(Context context, String key, float defValue) {
		if (sp == null){
			sp = context.getSharedPreferences(SP_NAME, 0);
		}
		return sp.getFloat(key, defValue);
	}

	/**
	 * 获取布尔
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static boolean getBoolean(Context context, String key,
			boolean defValue) {
		if (sp == null){
			sp = context.getSharedPreferences(SP_NAME, 0);
		}
		return sp.getBoolean(key, defValue);
	}

}
