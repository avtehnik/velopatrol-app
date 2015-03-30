package ua.in.velopatrol.velopatrol.utils;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.rightutils.rightutils.utils.CacheUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import ua.in.velopatrol.velopatrol.entities.Cache;

/**
 * Created by Anton on 1/30/2015.
 */
public class SystemUtils {
	private static final String TAG = SystemUtils.class.getSimpleName();
	private static final String BASE_URL = "http://velopatrol.in.ua/api/";
	public static final String SIGN_IN_URL = BASE_URL + "v1/auth/login";
	public static final String SIGN_UP_URL = BASE_URL + "v1/auth/register";
	public static final String ARTICLES_URL = BASE_URL + "v1/articles/list?timestamp=%d";
	public static final String CHALLENGE_LIST = BASE_URL + "v1/challenge/list";
	public static final String VOLUNTEERS_URL = BASE_URL + "v1/volunteer/list?timestamp=%d";

	public static Toast systemToast;

	public static final ObjectMapper MAPPER = new ObjectMapper().configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	public static void getCache(Context context, CacheUtils.CallBack<Cache> callBack) {
		CacheUtils.getCache(MAPPER, Cache.class, context, callBack, true);
	}

	public static void toast(Context context, String message) {
		if (systemToast != null) {
			systemToast.cancel();
		}
		systemToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		systemToast.show();
	}

	public static void toast(Context context, int resource) {
		toast(context, context.getResources().getString(resource));
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
