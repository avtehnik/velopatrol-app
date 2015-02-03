package ua.in.velopatrol.velopatrol.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.rightutils.rightutils.activities.SupportRightActionBarActivity;
import com.rightutils.rightutils.collections.RightList;
import com.rightutils.rightutils.tasks.BaseTask;

import ua.in.velopatrol.velopatrol.R;
import ua.in.velopatrol.velopatrol.adapters.ArticlesAdapter;
import ua.in.velopatrol.velopatrol.applications.VelopatlorApp;
import ua.in.velopatrol.velopatrol.entities.Article;
import ua.in.velopatrol.velopatrol.entities.ResponseError;
import ua.in.velopatrol.velopatrol.tasks.RetrieveArticles;

/**
 * Created by Anton on 2/1/2015.
 */
public class UserArticlesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

	private static final String TAG = UserArticlesFragment.class.getSimpleName();
	private SwipeRefreshLayout mSwipeRefreshLayout;
	private Toast errorMsg;
	private View progressView;
	private RightList<Article> articles = new RightList<>();
	private ListView listView;
	private ArticlesAdapter adapter;

	@Override
	public void onResume() {
		super.onResume();
		((SupportRightActionBarActivity)getActivity()).getSupportActionBar().setTitle("Новини");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_user_articles, null);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
		mSwipeRefreshLayout.setOnRefreshListener(this);

		listView = (ListView) view.findViewById(R.id.list_view);
		articles = VelopatlorApp.dbUtils.getAll(Article.class);
		adapter = new ArticlesAdapter(getActivity(), articles);
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
		final RetrieveArticles task = new RetrieveArticles(getActivity(), progressView);
		task.setCallback(new BaseTask.Callback() {
			@Override
			public void successful() {
				articles = VelopatlorApp.dbUtils.getAll(Article.class);
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
