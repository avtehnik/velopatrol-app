package ua.in.velopatrol.velopatrol.activities;

import android.os.Bundle;

import com.rightutils.rightutils.activities.SupportRightActionBarActivity;

import ua.in.velopatrol.velopatrol.R;
import ua.in.velopatrol.velopatrol.fragments.PlaceHolderFragment;


public class VolunteerMainActivity extends SupportRightActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		initActivity(R.id.fragment_container, new PlaceHolderFragment());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_volunteer_main);
	}

}
