package ua.in.velopatrol.velopatrol.applications;

import android.app.Application;

import ua.in.velopatrol.velopatrol.db.DBUtils;

/**
 * Created by Anton on 1/30/2015.
 */
public class VelopatlorApp extends Application {

	public static DBUtils dbUtils;

	@Override
	public void onCreate() {
		super.onCreate();
		dbUtils = DBUtils.newInstance(getApplicationContext(), "velopatrol.db", 1);
	}
}
