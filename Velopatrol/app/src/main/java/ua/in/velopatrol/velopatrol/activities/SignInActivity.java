package ua.in.velopatrol.velopatrol.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rightutils.rightutils.activities.LoginActivity;

import ua.in.velopatrol.velopatrol.entities.User;

/**
 * Created by Anton on 1/30/2015.
 */
public class SignInActivity extends RegIdActivity implements LoginActivity<User>, View.OnClickListener {

	private static final String TAG = SignInActivity.class.getSimpleName();
	private Button login, register;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void sendRequest() {

	}

	@Override
	public void doStart(User user) {

	}

	@Override
	public void onClick(View v) {

	}
}
