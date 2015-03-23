package ua.in.velopatrol.velopatrol.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import ua.in.velopatrol.velopatrol.tasks.SignUpTask;
import ua.in.velopatrol.velopatrol.utils.SystemUtils;

/**
 * Created by Anton on 1/30/2015.
 */
public class SignUpActivity extends RegIdActivity implements LoginActivity<User>, View.OnClickListener {

	private static final String TAG = SignUpActivity.class.getSimpleName();
	private TextView name, phone, password, confirmPassword;
	private Button register;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		name = (EditText) findViewById(R.id.f_name);
		phone = (EditText) findViewById(R.id.f_phone);
		password = (EditText) findViewById(R.id.f_password);
		confirmPassword = (EditText) findViewById(R.id.f_confirm_password);
		(register = (Button) findViewById(R.id.btn_register)).setOnClickListener(this);
	}

	@Override
	public void sendRequest() {
		final SignUpTask signInTask = new SignUpTask(name.getText().toString(), phone.getText().toString(), password.getText().toString(), SignUpActivity.this);
		signInTask.setCallback(new BaseTaskMaterial.Callback() {
			@Override
			public void successful() {
				doStart(signInTask.getUser());
			}

			@Override
			public void failed() {
				ResponseError error = signInTask.getError();
				if (error != null) {
					SystemUtils.toast(SignUpActivity.this, error.getMessage());
				} else {
					SystemUtils.toast(SignUpActivity.this, R.string.something_was_wrong);
				}
			}
		}).execute();
	}

	@Override
	public void doStart(final User user) {
		SystemUtils.getCache(SignUpActivity.this, new CacheUtils.CallBack<Cache>() {
			@Override
			public void run(Cache cache) {
				cache.setUser(user);
			}
		});
//		((DailyRateApp) getApplication()).startWorker();
		startActivity(new Intent(SignUpActivity.this, UserMainActivity.class));
		finish();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_register:
				if (isValid()) {
					sendRequest();
				}
				break;
		}
	}

	private boolean isValid() {
		if (TextUtils.isEmpty(name.getText())) {
			SystemUtils.toast(SignUpActivity.this, R.string.enter_name);
			name.requestFocus();
			return false;
		} else if (TextUtils.isEmpty(phone.getText())) {
			SystemUtils.toast(SignUpActivity.this, R.string.enter_phone);
			phone.requestFocus();
			return false;
		} else if (TextUtils.isEmpty(password.getText())) {
			SystemUtils.toast(SignUpActivity.this, R.string.enter_password);
			password.requestFocus();
			return false;
		} else if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
			SystemUtils.toast(SignUpActivity.this, R.string.passwords_do_not_match);
			password.requestFocus();
			return false;
		}
		return true;
	}
}
