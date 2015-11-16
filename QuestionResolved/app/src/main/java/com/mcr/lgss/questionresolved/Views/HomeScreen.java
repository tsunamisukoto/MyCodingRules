package com.mcr.lgss.questionresolved.Views;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mcr.lgss.questionresolved.Entities.Person;
import com.mcr.lgss.questionresolved.R;
import com.mcr.lgss.questionresolved.Services.DatabaseHelper;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity implements ViewAllUsersFragment.OnAllUsersFragmentInteractionListener {
    DatabaseHelper dbHelper;
    private String[] mPlanetTitles=new String[]{"Register New User", "Scan For User", "View All Users"};
    private ListView mDrawerList;
    private CharSequence mDrawerTitle="Drawer Title";
    private CharSequence mTitle="Main Title";
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DatabaseHelper(getApplicationContext());
        setContentView(R.layout.activity_home_screen);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, mPlanetTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

      //  mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                (Toolbar) findViewById(R.id.toolbar), R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
//                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
//                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

//        Button btnQRCodeRead=(Button)findViewById(R.id.btnQRCode);
//        btnQRCodeRead.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                scanQR(v);
//            }
//        });

        ArrayList<Person> people=new ArrayList<>();
        people=dbHelper.GetPeople(people);


    }

    @Override
    public void onAllUsersFragmentInteraction(int id) {
        Fragment fragment=null;
        Log.e("TEST", id+"");

        Bundle args = new Bundle();

        fragment  = new ViewUserFragment();
        args.putInt(ViewUserFragment.ARG_USERID,( id));
        fragment.setArguments(args);
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position
    Fragment fragment=null;
        Bundle args = new Bundle();
        switch (position)
        {
            case 0:
//               fragment  = new UserFragment();
//                args.putInt(UserFragment.ARG_PARAM1, position);
//                fragment.setArguments(args);
                break;
            case 1:
                break;
            case 2:
                fragment  = new ViewAllUsersFragment();
                fragment.setArguments(args);
                break;
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);

    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
       // getActionBar().setTitle(mTitle);
    }

}