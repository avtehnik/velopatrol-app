package ua.in.velopatrol.velopatrol.db;

import android.content.Context;

import com.rightutils.rightutils.collections.Mapper;
import com.rightutils.rightutils.collections.Predicate;
import com.rightutils.rightutils.collections.RightList;
import com.rightutils.rightutils.db.RightDBUtils;

import java.io.IOException;

import ua.in.velopatrol.velopatrol.dao.VolunteerDAO;
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
		return getAll(VolunteerDAO.class).map(new Mapper<Volunteer, VolunteerDAO>() {
			@Override
			public Volunteer apply(VolunteerDAO volunteerDAO) {
				try {
					return volunteerDAO.toVolunteer();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		}).filter(new Predicate<Volunteer>() {
			@Override
			public boolean apply(Volunteer volunteer) {
				return volunteer != null;
			}
		});
	}
}
