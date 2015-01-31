package ua.in.velopatrol.velopatrol.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.rightutils.rightutils.activities.SupportRightActionBarActivity;
import com.rightutils.rightutils.collections.RightList;
import com.rightutils.rightutils.utils.CacheUtils;
import ua.in.velopatrol.velopatrol.R;
import ua.in.velopatrol.velopatrol.adapters.MainMenuAdapter;
import ua.in.velopatrol.velopatrol.entities.Cache;
import ua.in.velopatrol.velopatrol.entities.MenuEntity;
import ua.in.velopatrol.velopatrol.fragments.PlaceHolderFragment;
import ua.in.velopatrol.velopatrol.utils.SystemUtils;

public class UserMainActivity extends SupportRightActionBarActivity implements View.OnClickListener {

	private static final String TAG = UserMainActivity.class.getSimpleName();
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawerLayout;
	private RightList<MenuEntity> menuEntities;
	private LinearLayout drawerMenu;
	private ListView listMenu;
	private ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		initActivity(R.id.fragment_container, new PlaceHolderFragment());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_main);

		setUpDrawer();
		setUpFragments();
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.isDrawerIndicatorEnabled()) {
			if (mDrawerToggle.onOptionsItemSelected(item)) {
				return true;
			}
		} else {
			onBackPressed();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		Fragment lastFragment = getLastFragment();
		if (lastFragment != null) {
			Log.i(TAG, "selectMenuItem " + lastFragment.getClass().getSimpleName());
			selectMenuItem(lastFragment);
		}
		super.onResume();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	private void setUpDrawer() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerMenu = (LinearLayout) findViewById(R.id.drawer_menu);
		listMenu = (ListView) findViewById(R.id.list_drawer);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);

//		actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_bg));
//		actionBar.setCustomView(R.layout.actionbar_home);
		actionBar.setDisplayShowCustomEnabled(true);
		if (mDrawerToggle != null) {
//			mDrawerToggle.setDrawerIndicatorEnabled(getSupportFragmentManager().getBackStackEntryCount() <= 1);
			mDrawerToggle.setDrawerIndicatorEnabled(true);
		}

		findViewById(R.id.logout_container).setOnClickListener(this);
	}

	private void setUpFragments() {
		menuEntities = RightList.asRightList(
				new MenuEntity(PlaceHolderFragment.class, "Виклики"),
				new MenuEntity(PlaceHolderFragment.class, "Волонтери"),
				new MenuEntity(PlaceHolderFragment.class, "Новини"),
				new MenuEntity(PlaceHolderFragment.class, "Профіль")
		);
		final MainMenuAdapter adapter = new MainMenuAdapter(this, menuEntities);
		listMenu.setAdapter(adapter);
		listMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				try {
					pushFragment((Fragment) adapter.getItem(position).getFragmentClass().newInstance());
				} catch (Exception e) {
					e.printStackTrace();
				}
				mDrawerLayout.closeDrawer(Gravity.LEFT);
			}
		});
		listMenu.post(new Runnable() {
			@Override
			public void run() {
				listMenu.setItemChecked(0, true);
			}
		});
	}

	@Override
	public void pushFragment(Fragment fragment) {
		Fragment lastFragment = getLastFragment();
		if (lastFragment != null && fragment.getClass() == lastFragment.getClass()) {
			return;
		}
		super.pushFragment(fragment);
		selectMenuItem(fragment);
	}

	@Override
	public void onBackPressed() {
		if (mDrawerLayout.isDrawerOpen(drawerMenu)) {
			mDrawerLayout.closeDrawer(Gravity.LEFT);
		} else {
			super.onBackPressed();
			selectMenuItem(getLastFragment());
		}
	}

	public void selectMenuItem(Fragment fragment) {
		final MenuEntity menuEntity = new MenuEntity(fragment.getClass());
		if (menuEntities != null && menuEntities.contains(menuEntity)) {
			listMenu.post(new Runnable() {
				@Override
				public void run() {
					Log.i(TAG, "setActiveItem=" + (menuEntities.indexOf(menuEntity)));
					listMenu.setItemChecked(menuEntities.indexOf(menuEntity), true);
				}
			});
//			listMenu.setItemChecked(menuEntities.indexOf(menuEntity) + 1, true);
		} else {
			if (listMenu != null) {
				listMenu.setItemChecked(-1, true);
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.logout_container:
				SystemUtils.getCache(UserMainActivity.this, new CacheUtils.CallBack<Cache>() {
					@Override
					public void run(Cache cache) {
						cache.setUser(null);
					}
				});
				finish();
				break;
		}
	}
}
