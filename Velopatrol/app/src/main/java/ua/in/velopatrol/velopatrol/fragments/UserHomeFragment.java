package ua.in.velopatrol.velopatrol.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.rightutils.rightutils.activities.SupportRightActionBarActivity;

import ua.in.velopatrol.velopatrol.R;
import ua.in.velopatrol.velopatrol.adapters.UserChallengeAdapter;
import ua.in.velopatrol.velopatrol.applications.VelopatlorApp;
import ua.in.velopatrol.velopatrol.entities.Article;
import ua.in.velopatrol.velopatrol.entities.ResponseError;
import ua.in.velopatrol.velopatrol.enums.ChallengeState;
import ua.in.velopatrol.velopatrol.enums.RequestOrder;
import ua.in.velopatrol.velopatrol.tasks.BaseTaskMaterial;
import ua.in.velopatrol.velopatrol.tasks.RetrieveArticles;
import ua.in.velopatrol.velopatrol.tasks.RetrieveChallenge;
import ua.in.velopatrol.velopatrol.utils.SystemUtils;

/**
 * Created by Anton on 2/1/2015.
 */
public class UserHomeFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, SwipeRefreshLayout.OnRefreshListener {

	private static final String TAG = UserHomeFragment.class.getSimpleName();
	private RecyclerView mRecyclerView;
	private SwipeRefreshLayout mSwipeRefreshLayout;
	private LinearLayoutManager mLayoutManager;
	private UserChallengeAdapter adapter;
	private boolean loading = true;

	@Override
	public void onResume() {
		super.onResume();
		((SupportRightActionBarActivity)getActivity()).getSupportActionBar().setTitle(R.string.invoke);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_user_home, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
		mSwipeRefreshLayout.setOnRefreshListener(this);
		((RadioGroup)view.findViewById(R.id.challenge_type)).setOnCheckedChangeListener(this);

		mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
		mRecyclerView.setHasFixedSize(true);
		mLayoutManager = new LinearLayoutManager(getActivity());
		mRecyclerView.setLayoutManager(mLayoutManager);
		adapter = new UserChallengeAdapter(getActivity());
		mRecyclerView.setAdapter(adapter);
		adapter.getFilter().filter(ChallengeState.OPEN.getValue());

		mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
				int visibleItemCount = mLayoutManager.getChildCount();
				int totalItemCount = mLayoutManager.getItemCount();
				int pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

				if (loading) {
					if ( (visibleItemCount+pastVisiblesItems) >= totalItemCount) {
						loading = false;
						Log.i(TAG, "Last Item Wow !");
						updateList(true, RequestOrder.ASC);
					}
				}
			}
		});
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
			case R.id.rbtn_open:
				adapter.getFilter().filter(ChallengeState.OPEN.getValue());
				break;
			case R.id.rbtn_closed:
				adapter.getFilter().filter(ChallengeState.CLOSED.getValue());
				break;
		}
	}

	private void updateList(boolean showProgress, RequestOrder order) {
		final RetrieveChallenge task = new RetrieveChallenge(getActivity(), showProgress, order);
		task.setCallback(new BaseTaskMaterial.Callback() {
			@Override
			public void successful() {
				mSwipeRefreshLayout.setRefreshing(false);
				loading = true;
			}

			@Override
			public void failed() {
				ResponseError error = task.getError();
				if (error != null) {
					SystemUtils.toast(getActivity(), error.getMessage());
				} else {
					SystemUtils.toast(getActivity(), R.string.something_was_wrong);
				}
				mSwipeRefreshLayout.setRefreshing(false);
			}
		});
		task.execute();
	}

	@Override
	public void onRefresh() {
		mSwipeRefreshLayout.setRefreshing(true);
		updateList(false, RequestOrder.DESC);
	}
}
