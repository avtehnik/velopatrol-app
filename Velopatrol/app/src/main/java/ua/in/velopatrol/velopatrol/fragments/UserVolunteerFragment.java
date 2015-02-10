package ua.in.velopatrol.velopatrol.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.rightutils.rightutils.activities.SupportRightActionBarActivity;
import com.rightutils.rightutils.collections.RightList;
import com.rightutils.rightutils.tasks.BaseTask;

import ua.in.velopatrol.velopatrol.R;
import ua.in.velopatrol.velopatrol.adapters.VolunteersAdapter;
import ua.in.velopatrol.velopatrol.applications.VelopatlorApp;
import ua.in.velopatrol.velopatrol.entities.ResponseError;
import ua.in.velopatrol.velopatrol.entities.Volunteer;
import ua.in.velopatrol.velopatrol.tasks.RetrieveVolunteers;

/**
 * Created by Anton on 2/1/2015.
 */
public class UserVolunteerFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

	private static final String TAG = UserVolunteerFragment.class.getSimpleName();
	private SwipeRefreshLayout mSwipeRefreshLayout;
	private Toast errorMsg;
	private View progressView;
	private RightList<Volunteer> volunteers = new RightList<>();
	private ListView listView;
	private VolunteersAdapter adapter;

	@Override
	public void onResume() {
		super.onResume();
		((SupportRightActionBarActivity) getActivity()).getSupportActionBar().setTitle("Волонтери");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_user_volunteers, null);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
		mSwipeRefreshLayout.setOnRefreshListener(this);

		listView = (ListView) view.findViewById(R.id.list_view);
		volunteers = VelopatlorApp.dbUtils.getVolunteers();
		adapter = new VolunteersAdapter(getActivity(), volunteers);
		listView.setAdapter(adapter);
		errorMsg = Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT);
		progressView = view.findViewById(R.id.progress_view);
		updateList(progressView);
	}


	@Override
	public void onRefresh() {
		mSwipeRefreshLayout.setRefreshing(true);
		updateList(null);
	}

	private void updateList(View progressView) {
		final RetrieveVolunteers task = new RetrieveVolunteers(getActivity(), progressView);
		task.setCallback(new BaseTask.Callback() {
			@Override
			public void successful() {
				volunteers.clear();
				volunteers.addAll(VelopatlorApp.dbUtils.getVolunteers());
				adapter.notifyDataSetChanged();
				mSwipeRefreshLayout.setRefreshing(false);
			}

			@Override
			public void failed() {
				ResponseError error = task.getError();
				if (error != null) {
					errorMsg.setText(error.getMessage());
					errorMsg.show();
				} else {
					errorMsg.setText(R.string.something_was_wrong);
					errorMsg.show();
				}
				mSwipeRefreshLayout.setRefreshing(false);
			}
		});
		task.execute();
	}
}
