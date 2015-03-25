package ua.in.velopatrol.velopatrol.db;

import android.content.Context;
import com.rightutils.rightutils.collections.RightList;
import com.rightutils.rightutils.db.RightDBUtils;
import ua.in.velopatrol.velopatrol.entities.Volunteer;

/**
 * Created by Anton on 2/3/2015.
 */
public class DBUtils extends RightDBUtils {

	private static final String TAG = DBUtils.class.getSimpleName();

	public static DBUtils newInstance(Context context, String name, int version) {
		DBUtils dbUtils = new DBUtils();
		dbUtils.setDBContext(context, name, version);
		return dbUtils;
	}

	public RightList<Volunteer> getVolunteers() {
		return getAll(Volunteer.class);
	}
}
