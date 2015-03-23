package ua.in.velopatrol.velopatrol.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.rightutils.rightutils.activities.LoginActivity;
import com.rightutils.rightutils.tasks.BaseTask;
import com.rightutils.rightutils.utils.CacheUtils;

import ua.in.velopatrol.velopatrol.R;
import ua.in.velopatrol.velopatrol.entities.Cache;
import ua.in.velopatrol.velopatrol.entities.ResponseError;
import ua.in.velopatrol.velopatrol.entities.User;
import ua.in.velopatrol.velopatrol.enums.AccountType;
import ua.in.velopatrol.velopatrol.tasks.BaseTaskMaterial;
import ua.in.velopatrol.velopatrol.tasks.SignInTask;
import ua.in.velopatrol.velopatrol.utils.SystemUtils;

/**
 * Created by Anton on 1/30/2015.
 */
public class SignInActivity extends RegIdActivity implements LoginActivity<User>, View.OnClickListener {

	private static final String TAG = SignInActivity.class.getSimpleName();
	private TextView phone, password;
	private Button login, register;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);
		phone = (EditText) findViewById(R.id.f_phone);
		password = (EditText) findViewById(R.id.f_password);
		(login = (Button) findViewById(R.id.btn_login)).setOnClickListener(this);
		(register = (Button) findViewById(R.id.btn_register)).setOnClickListener(this);
		SystemUtils.getCache(SignInActivity.this, new CacheUtils.CallBack<Cache>() {
			@Override
			public void run(Cache cache) {
				if (cache.getUser() != null) {
					doStart(cache.getUser());
				}
			}
		});
	}

	@Override
	public void sendRequest() {
		final SignInTask signInTask = new SignInTask(phone.getText().toString(), password.getText().toString(), SignInActivity.this);
		signInTask.setCallback(new BaseTaskMaterial.Callback() {
			@Override
			public void successful() {
				doStart(signInTask.getUser());
			}

			@Override
			public void failed() {
				ResponseError error = signInTask.getError();
				if (error != null) {
					SystemUtils.toast(SignInActivity.this, error.getMessage());
				} else {
					SystemUtils.toast(SignInActivity.this, R.string.something_was_wrong);
				}
			}
		}).execute();
	}

	@Override
	public void doStart(final User user) {
		SystemUtils.getCache(SignInActivity.this, new CacheUtils.CallBack<Cache>() {
			@Override
			public void run(Cache cache) {
				cache.setUser(user);
			}
		});
		if (AccountType.USER.getValue().equals(user.getUserType())) {
//			((DailyRateApp) getApplication()).startWorker();
			startActivity(new Intent(SignInActivity.this, UserMainActivity.class));
		} else {
//			((DailyRateApp) getApplication()).startBooker();
			startActivity(new Intent(SignInActivity.this, VolunteerMainActivity.class));
		}
		finish();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_login:
				sendRequest();
				break;
			case R.id.btn_register:
				startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
				break;
		}
	}
}
