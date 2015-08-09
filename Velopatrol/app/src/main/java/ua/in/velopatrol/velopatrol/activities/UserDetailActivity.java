package ua.in.velopatrol.velopatrol.activities;

import android.app.Activity;
import android.content.Intent;
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
import ua.in.velopatrol.velopatrol.fragments.UserArticlesFragment;
import ua.in.velopatrol.velopatrol.fragments.UserHomeFragment;
import ua.in.velopatrol.velopatrol.fragments.UserProfileFragment;
import ua.in.velopatrol.velopatrol.fragments.UserVolunteerFragment;
import ua.in.velopatrol.velopatrol.utils.SystemUtils;

public class UserDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);


    }
}
