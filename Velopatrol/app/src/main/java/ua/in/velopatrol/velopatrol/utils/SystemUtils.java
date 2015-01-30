package ua.in.velopatrol.velopatrol.utils;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.rightutils.rightutils.utils.CacheUtils;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import ua.in.velopatrol.velopatrol.entities.Cache;

/**
 * Created by Anton on 1/30/2015.
 */
public class SystemUtils {
	private static final String TAG = SystemUtils.class.getSimpleName();
	private static final String BASE_URL = "http://dailyrate.stageserver.org/api/";

	public static final ObjectMapper MAPPER = new ObjectMapper().configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	public static ImageLoader IMAGELOADER;

	public static void getCache(Context context, CacheUtils.CallBack<Cache> callBack) {
		CacheUtils.getCache(MAPPER, Cache.class, context, callBack, true);
	}

	public static String getDeviceId(Context context) {
		final TelephonyManager mTelephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		if (mTelephony.getDeviceId() != null) {
			return mTelephony.getDeviceId(); //*** use for mobiles
		} else {
			return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID); //*** use for tablets
		}
	}
}
