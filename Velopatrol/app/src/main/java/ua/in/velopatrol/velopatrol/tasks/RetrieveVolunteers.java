package ua.in.velopatrol.velopatrol.tasks;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.rightutils.rightutils.collections.Mapper;
import com.rightutils.rightutils.collections.Predicate;
import com.rightutils.rightutils.collections.RightList;
import com.rightutils.rightutils.net.RequestUtils;
import com.rightutils.rightutils.tasks.BaseTask;
import com.rightutils.rightutils.utils.CacheUtils;

import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;

import ch.boye.httpclientandroidlib.HttpResponse;
import ch.boye.httpclientandroidlib.HttpStatus;
import ch.boye.httpclientandroidlib.message.BasicHeader;
import ch.boye.httpclientandroidlib.util.EntityUtils;
import ua.in.velopatrol.velopatrol.R;
import ua.in.velopatrol.velopatrol.applications.VelopatlorApp;
import ua.in.velopatrol.velopatrol.dao.VolunteerDAO;
import ua.in.velopatrol.velopatrol.entities.Article;
import ua.in.velopatrol.velopatrol.entities.Cache;
import ua.in.velopatrol.velopatrol.entities.ResponseError;
import ua.in.velopatrol.velopatrol.entities.Volunteer;
import ua.in.velopatrol.velopatrol.utils.SystemUtils;

import static com.rightutils.rightutils.utils.RightUtils.isOnline;

/**
 * Created by Anton on 2/3/2015.
 */
public class RetrieveVolunteers extends BaseTaskMaterial {

	private static final String TAG = RetrieveVolunteers.class.getSimpleName();
	private ResponseError error;
	private Cache cache;

	public RetrieveVolunteers(Context context, boolean showProgress) {
		super(context, showProgress);
		SystemUtils.getCache(context, new CacheUtils.CallBack<Cache>() {
			@Override
			public void run(Cache cache) {
				RetrieveVolunteers.this.cache = cache;
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
			HttpResponse response = RequestUtils.getHttpResponse(String.format(SystemUtils.VOLUNTEERS_URL, 0), new BasicHeader("AUTH-KEY", cache.getUser().getAuthToken()));
			int status = response.getStatusLine().getStatusCode();
			Log.i(TAG, "status code: " + String.valueOf(status));
			if (status == HttpStatus.SC_OK) {
				String result = EntityUtils.toString(response.getEntity());
				Log.i(TAG, result);
				RightList<Volunteer> volunteers = SystemUtils.MAPPER.readValue(result, new TypeReference<RightList<Volunteer>>() {});
				VelopatlorApp.dbUtils.add(volunteers.map(new Mapper<VolunteerDAO, Volunteer>() {
					@Override
					public VolunteerDAO apply(Volunteer volunteer) {
						try {
							return VolunteerDAO.newInstance(volunteer);
						} catch (IOException e) {
							e.printStackTrace();
						}
						return null;
					}
				}).filter(new Predicate<VolunteerDAO>() {
					@Override
					public boolean apply(VolunteerDAO volunteerDAO) {
						return volunteerDAO != null;
					}
				}));
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
