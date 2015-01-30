package ua.in.velopatrol.velopatrol.activities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.rightutils.rightutils.utils.CacheUtils;

import java.io.IOException;

import ua.in.velopatrol.velopatrol.entities.Cache;
import ua.in.velopatrol.velopatrol.utils.SystemUtils;

/**
 * Created by Anton Maniskevich on 25.07.2014.
 */
public abstract class RegIdActivity extends Activity {

	private static final String TAG = RegIdActivity.class.getSimpleName();
	public static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	public static final String PROPERTY_REG_ID = "registration_id";
	public static final String PROPERTY_APP_VERSION = "appVersion";
	public static String SENDER_ID = "351868960526";

	private GoogleCloudMessaging gcm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (checkPlayServices()) {
			gcm = GoogleCloudMessaging.getInstance(this);
			SystemUtils.getCache(RegIdActivity.this, new CacheUtils.CallBack<Cache>() {
				@Override
				public void run(Cache cache) {
					cache.setPushId(getRegistrationId(RegIdActivity.this));
					if (cache.getPushId().isEmpty()) {
						registerInBackground();
					} else {
						Log.i(TAG, cache.getPushId());
					}
				}
			});

		} else {
			Log.i(TAG, "No valid Google Play Services APK found.");
		}
	}

	private boolean checkPlayServices() {
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		if (resultCode != ConnectionResult.SUCCESS) {
			if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
				GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICES_RESOLUTION_REQUEST).show();
			} else {
				Log.i(TAG, "This device is not supported.");
				finish();
			}
			return false;
		}
		return true;
	}

	private String getRegistrationId(Context context) {
		final SharedPreferences prefs = getGCMPreferences(context);
		String registrationId = prefs.getString(PROPERTY_REG_ID, "");
		if (registrationId.isEmpty()) {
			Log.i(TAG, "Registration not found.");
			return "";
		}
		int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
		int currentVersion = getAppVersion(context);
		if (registeredVersion != currentVersion) {
			Log.i(TAG, "App version changed.");
			return "";
		}
		return registrationId;
	}

	private SharedPreferences getGCMPreferences(Context context) {
		return getSharedPreferences(TAG, Context.MODE_PRIVATE);
	}


	private static int getAppVersion(Context context) {
		try {
			PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (Exception e) {
			// should never happen
			throw new RuntimeException("Could not get package name: " + e);
		}
	}

	private void registerInBackground() {
		new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(Void... params) {
				String msg;
				try {
					if (gcm == null) {
						gcm = GoogleCloudMessaging.getInstance(RegIdActivity.this);
					}
					final String pushId = gcm.register(SENDER_ID);
					SystemUtils.getCache(RegIdActivity.this, new CacheUtils.CallBack<Cache>() {
						@Override
						public void run(Cache cache) {
							cache.setPushId(pushId);
							cache.setDeviceId(SystemUtils.getDeviceId(RegIdActivity.this));
							Log.i(TAG, "deviceId=" + cache.getDeviceId());
						}
					});

					msg = "Device registered, registration ID=" + pushId;

					sendRegistrationIdToBackend();

					storeRegistrationId(RegIdActivity.this, pushId);
				} catch (IOException ex) {
					msg = "Error :" + ex.getMessage();
				}
				return msg;
			}

			@Override
			protected void onPostExecute(String msg) {
				Log.i(TAG, msg);
			}
		}.execute();
	}

	private void sendRegistrationIdToBackend() {
//		new RegisterDeviceTask(RegIdActivity.this).execute();
	}

	private void storeRegistrationId(Context context, String regId) {
		final SharedPreferences prefs = getGCMPreferences(context);
		int appVersion = getAppVersion(context);
		Log.i(TAG, "Saving regId on app version " + appVersion);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(PROPERTY_REG_ID, regId);
		editor.putInt(PROPERTY_APP_VERSION, appVersion);
		editor.commit();
	}
}
