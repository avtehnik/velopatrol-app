package ua.in.velopatrol.velopatrol.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.rightutils.rightutils.activities.SupportRightActionBarActivity;
import com.rightutils.rightutils.collections.RightList;
import com.rightutils.rightutils.tasks.BaseTask;

import ua.in.velopatrol.velopatrol.R;
import ua.in.velopatrol.velopatrol.activities.ArticleActivity;
import ua.in.velopatrol.velopatrol.activities.UserMainActivity;
import ua.in.velopatrol.velopatrol.adapters.ArticlesAdapter;
import ua.in.velopatrol.velopatrol.applications.VelopatlorApp;
import ua.in.velopatrol.velopatrol.entities.Article;
import ua.in.velopatrol.velopatrol.entities.ResponseError;
import ua.in.velopatrol.velopatrol.tasks.BaseTaskMaterial;
import ua.in.velopatrol.velopatrol.tasks.RetrieveArticles;
import ua.in.velopatrol.velopatrol.utils.SystemUtils;

/**
 * Created by Anton on 2/1/2015.
 */
public class UserArticlesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = UserArticlesFragment.class.getSimpleName();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RightList<Article> articles = new RightList<>();
    private ListView listView;
    private ArticlesAdapter adapter;

    @Override
    public void onResume() {
        super.onResume();
        ((SupportRightActionBarActivity) getActivity()).getSupportActionBar().setTitle(R.string.news);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_articles, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        listView = (ListView) view.findViewById(R.id.list_view);
        articles = VelopatlorApp.dbUtils.getAll(Article.class);
        adapter = new ArticlesAdapter(getActivity(), articles);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Article article  =  articles.get(position);

                Intent i=new Intent(view.getContext(), ArticleActivity.class);
                i.putExtra("title", article.getTitle() );
                i.putExtra("text",  article.getText() );
                i.putExtra("date",  article.getDate() );

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
        final RetrieveArticles task = new RetrieveArticles(getActivity(), showProgress);
        task.setCallback(new BaseTaskMaterial.Callback() {
            @Override
            public void successful() {
                articles.clear();
                articles.addAll(VelopatlorApp.dbUtils.getAll(Article.class));
                adapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
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
}
