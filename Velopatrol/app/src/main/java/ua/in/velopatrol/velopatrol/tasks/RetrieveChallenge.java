package ua.in.velopatrol.velopatrol.tasks;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.afollestad.materialdialogs.MaterialDialog;
import com.rightutils.rightutils.collections.RightList;
import com.rightutils.rightutils.net.RequestUtils;
import com.rightutils.rightutils.utils.CacheUtils;

import org.codehaus.jackson.type.TypeReference;

import ch.boye.httpclientandroidlib.HttpResponse;
import ch.boye.httpclientandroidlib.HttpStatus;
import ch.boye.httpclientandroidlib.message.BasicHeader;
import ch.boye.httpclientandroidlib.util.EntityUtils;
import ua.in.velopatrol.velopatrol.R;
import ua.in.velopatrol.velopatrol.applications.VelopatlorApp;
import ua.in.velopatrol.velopatrol.entities.Article;
import ua.in.velopatrol.velopatrol.entities.Cache;
import ua.in.velopatrol.velopatrol.entities.Challenge;
import ua.in.velopatrol.velopatrol.entities.ResponseError;
import ua.in.velopatrol.velopatrol.enums.RequestOrder;
import ua.in.velopatrol.velopatrol.utils.SystemUtils;

import static com.rightutils.rightutils.utils.RightUtils.isOnline;

/**
 * Created by Anton on 3/30/2015.
 */
public class RetrieveChallenge extends BaseTaskMaterial {

	private static final String TAG = RetrieveChallenge.class.getSimpleName();
	private ResponseError error;
	private Cache cache;
	private RequestOrder order;

	public RetrieveChallenge(Context context, boolean showProgress, RequestOrder order) {
		super(context, showProgress);
		this.order = order;
		SystemUtils.getCache(context, new CacheUtils.CallBack<Cache>() {
			@Override
			public void run(Cache cache) {
				RetrieveChallenge.this.cache = cache;
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
			String url = Uri.parse(SystemUtils.CHALLENGE_LIST).buildUpon()
					.appendQueryParameter("timestamp", String.valueOf(VelopatlorApp.dbUtils.getChallengeTime(order)))
					.appendQueryParameter("order", order.getValue())
					.build().toString();
			Log.i(TAG, url);
			HttpResponse response = RequestUtils.getHttpResponse(url, new BasicHeader("AUTH-KEY", cache.getUser().getAuthToken()));
			int status = response.getStatusLine().getStatusCode();
			Log.i(TAG, "status code: " + String.valueOf(status));
			if (status == HttpStatus.SC_OK) {
				String result = EntityUtils.toString(response.getEntity());
				Log.i(TAG, result);
				RightList<Challenge> challenges = SystemUtils.MAPPER.readValue(result, new TypeReference<RightList<Challenge>>() {});
				VelopatlorApp.dbUtils.add(challenges);
				return true;
			} else {
				error = SystemUtils.MAPPER.readValue(response.getEntity().getContent(), ResponseError.class);
			}
		} catch (Exception e) {
			Log.e(TAG, "run", e);
		}
		return false;
	}

	public ResponseError getError() {
		return error;
	}
}
