package ua.in.velopatrol.velopatrol.tasks;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.rightutils.rightutils.net.RequestUtils;
import com.rightutils.rightutils.tasks.BaseTask;
import com.rightutils.rightutils.utils.CacheUtils;

import java.util.ArrayList;
import java.util.List;

import ch.boye.httpclientandroidlib.HttpResponse;
import ch.boye.httpclientandroidlib.HttpStatus;
import ch.boye.httpclientandroidlib.NameValuePair;
import ch.boye.httpclientandroidlib.message.BasicNameValuePair;
import ch.boye.httpclientandroidlib.util.EntityUtils;
import ua.in.velopatrol.velopatrol.R;
import ua.in.velopatrol.velopatrol.entities.Cache;
import ua.in.velopatrol.velopatrol.entities.ResponseError;
import ua.in.velopatrol.velopatrol.entities.User;
import ua.in.velopatrol.velopatrol.utils.SystemUtils;

import static com.rightutils.rightutils.utils.RightUtils.isOnline;

public class SignInTask extends BaseTask {

	private static final String TAG = SignInTask.class.getName();
	private String phone;
	private String password;
	private ResponseError error;
	private User user;
	private Cache cache;

	public SignInTask(String phone, String password, Context context, View progressBar) {
		super(context, progressBar);
		this.phone = phone;
		this.password = password;
		SystemUtils.getCache(context, new CacheUtils.CallBack<Cache>() {
			@Override
			public void run(Cache cache) {
				SignInTask.this.cache = cache;
			}
		});
	}

	@Override
	protected Boolean doInBackground(String... params) {
		try {
			if (!isOnline(context)) {
				error = new ResponseError(context.getString(R.string.no_internet_connection));
				return false;
			}
			if (cache.getPushId() == null || cache.getPushId().isEmpty()) {
				error = new ResponseError(context.getString(R.string.wait_5_sec));
				return false;
			}
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("phone", phone));
			nameValuePairs.add(new BasicNameValuePair("password", password));
			nameValuePairs.add(new BasicNameValuePair("token", cache.getPushId()));
			nameValuePairs.add(new BasicNameValuePair("platform", "android"));

			HttpResponse response = RequestUtils.postHttpResponse(SystemUtils.SIGN_IN_URL, nameValuePairs);
			int status = response.getStatusLine().getStatusCode();
			Log.i(TAG, "status code: " + String.valueOf(status));
			if (status == HttpStatus.SC_OK) {
				String result = EntityUtils.toString(response.getEntity());
				Log.i(TAG, result);
				user = SystemUtils.MAPPER.readValue(result, User.class);
				return true;
			} else {
				error = SystemUtils.MAPPER.readValue(response.getEntity().getContent(), ResponseError.class);
			}
		} catch (Exception e) {
			Log.e(TAG, "run", e);
		}
		return false;
	}

	public User getUser() {
		return user;
	}

	public ResponseError getError() {
		return error;
	}
}

