package ua.in.velopatrol.velopatrol.tasks;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.rightutils.rightutils.collections.RightList;
import com.rightutils.rightutils.net.RequestUtils;
import com.rightutils.rightutils.tasks.BaseTask;
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
import ua.in.velopatrol.velopatrol.entities.ResponseError;
import ua.in.velopatrol.velopatrol.utils.SystemUtils;

import static com.rightutils.rightutils.utils.RightUtils.isOnline;

/**
 * Created by Anton on 2/3/2015.
 */
public class RetrieveArticles extends BaseTask {

	private static final String TAG = RetrieveArticles.class.getSimpleName();
	private ResponseError error;
	private Cache cache;

	public RetrieveArticles(Context context, View progressBar) {
		super(context, progressBar);
		SystemUtils.getCache(context, new CacheUtils.CallBack<Cache>() {
			@Override
			public void run(Cache cache) {
				RetrieveArticles.this.cache = cache;
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
			//TODO change 0
			HttpResponse response = RequestUtils.getHttpResponse(String.format(SystemUtils.ARTICLES_URL, 0), new BasicHeader("AUTH-KEY", cache.getUser().getAuthToken()));
			int status = response.getStatusLine().getStatusCode();
			Log.i(TAG, "status code: " + String.valueOf(status));
			if (status == HttpStatus.SC_OK) {
				String result = EntityUtils.toString(response.getEntity());
				Log.i(TAG, result);
				RightList<Article> articles = SystemUtils.MAPPER.readValue(result, new TypeReference<RightList<Article>>() {});
				VelopatlorApp.dbUtils.add(articles);
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
