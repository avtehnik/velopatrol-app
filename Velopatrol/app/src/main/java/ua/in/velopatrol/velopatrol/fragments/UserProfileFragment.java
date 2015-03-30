package ua.in.velopatrol.velopatrol.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.rightutils.rightutils.activities.SupportRightActionBarActivity;
import com.rightutils.rightutils.utils.CacheUtils;

import ua.in.velopatrol.velopatrol.R;
import ua.in.velopatrol.velopatrol.entities.Cache;
import ua.in.velopatrol.velopatrol.utils.SystemUtils;

/**
 * Created by Anton on 3/30/2015.
 */
public class UserProfileFragment extends Fragment implements View.OnClickListener {

	private static final String TAG = UserProfileFragment.class.getSimpleName();
	private EditText name;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_user_profile, container, false);
	}

	@Override
	public void onResume() {
		super.onResume();
		((SupportRightActionBarActivity)getActivity()).getSupportActionBar().setTitle(R.string.profile);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		name = (EditText) view.findViewById(R.id.f_name);
		SystemUtils.getCache(getActivity(), new CacheUtils.CallBack<Cache>() {
			@Override
			public void run(Cache cache) {
				if (cache.getUser() != null) {
					name.setText(cache.getUser().getName());
				}
			}
		});
		view.findViewById(R.id.btn_change_number).setOnClickListener(this);
		view.findViewById(R.id.btn_change_password).setOnClickListener(this);
		view.findViewById(R.id.btn_save).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_change_number:
				//TODO save
				break;
			case R.id.btn_change_password:
				//TODO save
				break;
			case R.id.btn_save:
				//TODO save
				break;
		}
	}
}
