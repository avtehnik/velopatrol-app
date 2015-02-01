package ua.in.velopatrol.velopatrol.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import ua.in.velopatrol.velopatrol.R;

/**
 * Created by Anton on 2/1/2015.
 */
public class UserHomeFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

	private static final String TAG = UserHomeFragment.class.getSimpleName();
	private RecyclerView mRecyclerView;
	private RecyclerView.LayoutManager mLayoutManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_user_home, null);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		((RadioGroup)view.findViewById(R.id.challenge_type)).setOnCheckedChangeListener(this);

		mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
		mRecyclerView.setHasFixedSize(true);
		mLayoutManager = new LinearLayoutManager(getActivity());
		mRecyclerView.setLayoutManager(mLayoutManager);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
			case R.id.rbtn_open:
//				updateList(JobStatus.OPEN);
				break;
			case R.id.rbtn_closed:
//				updateList(JobStatus.CLOSED);
				break;
		}
	}
}
