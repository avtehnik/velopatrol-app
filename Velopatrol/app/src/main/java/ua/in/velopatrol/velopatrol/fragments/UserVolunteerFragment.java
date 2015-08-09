package ua.in.velopatrol.velopatrol.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.rightutils.rightutils.activities.SupportRightActionBarActivity;
import com.rightutils.rightutils.collections.RightList;
import com.rightutils.rightutils.tasks.BaseTask;

import ua.in.velopatrol.velopatrol.R;
import ua.in.velopatrol.velopatrol.activities.ArticleActivity;
import ua.in.velopatrol.velopatrol.activities.VolunteerDetailActivity;
import ua.in.velopatrol.velopatrol.adapters.VolunteersAdapter;
import ua.in.velopatrol.velopatrol.applications.VelopatlorApp;
import ua.in.velopatrol.velopatrol.entities.Article;
import ua.in.velopatrol.velopatrol.entities.ResponseError;
import ua.in.velopatrol.velopatrol.entities.Volunteer;
import ua.in.velopatrol.velopatrol.tasks.BaseTaskMaterial;
import ua.in.velopatrol.velopatrol.tasks.RetrieveVolunteers;
import ua.in.velopatrol.velopatrol.utils.SystemUtils;

/**
 * Created by Anton on 2/1/2015.
 */
public class UserVolunteerFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

	private static final String TAG = UserVolunteerFragment.class.getSimpleName();
	private SwipeRefreshLayout mSwipeRefreshLayout;
	private RightList<Volunteer> volunteers = new RightList<>();
	private ListView listView;
	private VolunteersAdapter adapter;

	@Override
	public void onResume() {
		super.onResume();
		((SupportRightActionBarActivity) getActivity()).getSupportActionBar().setTitle(R.string.volunteer);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_user_volunteers, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
		mSwipeRefreshLayout.setOnRefreshListener(this);

		listView = (ListView) view.findViewById(R.id.list_view);
		volunteers = VelopatlorApp.dbUtils.getVolunteers();
		adapter = new VolunteersAdapter(getActivity(), volunteers);
		listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Volunteer volunteer  =  volunteers.get(position);

                Intent i=new Intent(view.getContext(), VolunteerDetailActivity.class);
                i.putExtra("id", volunteer.getId() );
//                i.putExtra("text",  article.getText() );
//                i.putExtra("date",  article.getDate() );

                startActivity(i);
            }
        });

        updateList(true);
	}


	@Override
	public void onRefresh() {
		mSwipeRefreshLayout.setRefreshing(true);
		updateList(false);
	}

	private void updateList(boolean showProgress) {
		final RetrieveVolunteers task = new RetrieveVolunteers(getActivity(), showProgress);
		task.setCallback(new BaseTaskMaterial.Callback() {
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
					SystemUtils.toast(getActivity(), error.getMessage());
				} else {
					SystemUtils.toast(getActivity(),R.string.something_was_wrong);
				}
				mSwipeRefreshLayout.setRefreshing(false);
			}
		});
		task.execute();
	}
}
