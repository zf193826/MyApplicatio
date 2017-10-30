package lianxi.com.yuekao_b.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * sharedPreference的工具类
 * @author Administrator
 *
 */
public class SharedUtil {
	public static String CONFIG  = "config";//保存的文件的名字
	public static SharedPreferences preferences;
	
	/**
	 * 保存布尔类型的数据
	 * @param context
	 * @param key 键
	 * @param value 存的值
	 */
	public static void saveBooleanData(Context context,String key,boolean value){
		if (preferences == null) {
			preferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
		}
		//存值
		preferences.edit().putBoolean(key, value).commit();
	}
	/**
	 * 取值................boolean数据
	 * @param context
	 * @param key
	 * @param defValue默认值
	 * @return
	 */
	public static boolean getBooleanData(Context context,String key,boolean defValue){
		if (preferences == null) {
			preferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
		}
		
		return preferences.getBoolean(key, defValue);
	}
	///存string类型的数据
	public static void saveStringData(Context context,String key,String value){
		if (preferences == null) {
			preferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
		}
		//存值
		preferences.edit().putString(key, value).commit();
	}
	//获取string的数据
	public static String getStringData(Context context,String key,String defValue){
		if (preferences == null) {
			preferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
		}
		
		return preferences.getString(key, defValue);
	}
}
