package ua.in.velopatrol.velopatrol.services;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

/**
 * Created by eKreative on 2/26/14.
 */
public class GcmIntentService extends IntentService {

	public static final String TAG = GcmIntentService.class.getName();
	public static final String UPDATE_ID = "updatedId";
	private NotificationManager notificationManager;
	private static final String ACTION = "action";
	private String DATA_ACTION = "ua.in.velopatrol.DATA_ACTION";

	public GcmIntentService() {
		super("GcmIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Bundle extras = intent.getExtras();
		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
		String messageType = gcm.getMessageType(intent);

		if (!extras.isEmpty()) {
			//TODO
			for (String key : extras.keySet()) {
				Object value = extras.get(key);
				Log.d(TAG, String.format("%s %s (%s)", key, value.toString(), value.getClass().getName()));
			}

			if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
				if (extras.containsKey(ACTION) && DATA_ACTION.equals(extras.getString(ACTION))) {
					dataPushMessage(extras);
				} else {
					defaultPushMessage(extras);
				}
			}
		}
		GcmBroadcastReceiver.completeWakefulIntent(intent);
	}

	private void dataPushMessage(Bundle extras) {
//		try {
//			int updateId = Integer.valueOf(extras.getString(UPDATE_ID));
////			UpdateNotificationData data = SystemUtils.MAPPER.readValue(extras.getString(MESSAGE), UpdateNotificationData.class);
//			Log.i(TAG, "" + updateId);
//			startService(new Intent(getApplicationContext(), DataService.class).putExtra(DataService.UPDATE_TYPE, updateId));
//		} catch (Exception e) {
//			Log.e(TAG, "dataPushMessage", e);
//		}
	}

	private void defaultPushMessage(final Bundle extras) {
		try {
//			SystemUtils.getCache(getApplicationContext(), new CacheUtils.CallBack<Cache>() {
//				@Override
//				public void run(Cache cache) {
//					if (cache.getUser() != null) {
//						String type = extras.getString(INVITE_TYPE);
//						if (type.equals("waiting")) {
//							sendNotification(getString(R.string.app_name), "You received a new invite", extras);
//						} else if (type.equals("accept")) {
//							Job job = DailyRateApp.dbUtils.getJobById(Integer.valueOf(extras.getString("jobId")));
//							if (job != null) {
//								String message = String.format("Invite for %s is accepted by %s", job.getTitle(), extras.getString("workerName"));
//								sendNotification(getString(R.string.app_name), message, extras);
//							}
//						} else if (type.equals("reject")) {
//							Job job = DailyRateApp.dbUtils.getJobById(Integer.valueOf(extras.getString("jobId")));
//							if (job != null) {
//								String message = String.format("Invite for %s is rejected by %s", job.getTitle(), extras.getString("workerName"));
//								sendNotification(getString(R.string.app_name), message, extras);
//							}
//						}
//					}
//				}
//			});

		} catch (Exception e) {
			Log.e(TAG, "defaultPushMessage", e);
		}
	}

	private void sendNotification(String title, String message, final Bundle extras) {
//		notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
//
//		PendingIntent contentIntent = PendingIntent.getActivity(this, new Random().nextInt(10000), new Intent(this, PushActivity.class).putExtras(extras), 0);
//
//		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
//				.setSmallIcon(R.drawable.ic_launcher)
//				.setContentTitle(title)
//				.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
//				.setContentText(message)
//				.setAutoCancel(true);
//
//		mBuilder.setContentIntent(contentIntent);
//		notificationManager.notify(new Random().nextInt(10000), mBuilder.build());
	}

}