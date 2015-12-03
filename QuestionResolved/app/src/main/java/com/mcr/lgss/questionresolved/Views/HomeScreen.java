package com.mcr.lgss.questionresolved.Views;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
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

public class HomeScreen extends AppCompatActivity implements ViewAllUsersFragment.OnAllUsersFragmentInteractionListener  ,ViewUserFragment.OnViewUserFragmentInteractionListener,EditUserFragment.OnEditUserFragmentInteractionListener{
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
        setContentView(R.layout.activity_home_screen);

        dbHelper = new DatabaseHelper(getApplicationContext());
//        dbHelper.onCreate(  dbHelper.getWritableDatabase());
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
    public void onAllUsersFragmentInteraction(ViewAllUsersFragment.Operation op,int id) {

        Fragment fragment=null;

        FragmentManager fragmentManager = getFragmentManager();
        Bundle args = new Bundle();

        FragmentTransaction man = fragmentManager.beginTransaction();
        switch ( op)
        {

            case Add:

                fragment  = new EditUserFragment();
                args.putInt(EditUserFragment.ARG_USERID, (id));
                fragment.setArguments(args);
                // Insert the fragment by replacing any existing fragment
                man.setCustomAnimations(R.transition.activity_slide, R.transition.activity_slideout, R.transition.activity_slidereverse,R.transition.activity_slideoutreverse);
                man.replace(R.id.content_frame, fragment).addToBackStack( null );
                man.commit();
                break;
            case View:

                fragment  = new ViewUserFragment();
                args.putInt(ViewUserFragment.ARG_USERID, (id));
                fragment.setArguments(args);
                // Insert the fragment by replacing any existing fragment

                man.setCustomAnimations(R.transition.activity_slide, R.transition.activity_slideout, R.transition.activity_slidereverse,R.transition.activity_slideoutreverse);
                man.replace(R.id.content_frame, fragment).addToBackStack( null );
                man.commit();
                break;
        }
    }

    @Override
    public void onViewUserFragmentInteraction(int id) {
        Fragment fragment=null;

        FragmentManager fragmentManager = getFragmentManager();
        Bundle args = new Bundle();

        FragmentTransaction man = fragmentManager.beginTransaction();
        fragment  = new EditUserFragment();
        args.putInt(EditUserFragment.ARG_USERID, (id));
        fragment.setArguments(args);
        // Insert the fragment by replacing any existing fragment

        man.setCustomAnimations(R.transition.activity_slide, R.transition.activity_slideout, R.transition.activity_slidereverse,R.transition.activity_slideoutreverse);
        man.replace(R.id.content_frame, fragment).addToBackStack( null );
        man.commit();
    }

    @Override
    public void onEditUserFragmentInteraction(int id) {
        Fragment fragment=null;
        Log.e("DSDSFSDFSDFSDFSDFDSNIN","DFSIDNFIUSDIUFSDNIFSD");
        FragmentManager fragmentManager = getFragmentManager();
        Bundle args = new Bundle();

        FragmentTransaction man = fragmentManager.beginTransaction();
        fragment  = new ViewUserFragment();
        args.putInt(EditUserFragment.ARG_USERID, (id));
        fragment.setArguments(args);
        // Insert the fragment by replacing any existing fragment

     //   man.setCustomAnimations(R.transition.activity_slide, R.transition.activity_slideout, R.transition.activity_slidereverse,R.transition.activity_slideoutreverse);
        fragmentManager.popBackStack();
     //   man.replace(R.id.content_frame, fragment).addToBackStack( null );
        man.commit();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position,view    );
        }
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position, View view) {
        // Create a new fragment and specify the planet to show based on position
    Fragment fragment=null;
        Bundle args = new Bundle();
        FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();

        switch (position)
        {
            case 0:

                FragmentTransaction man = fragmentManager;
                fragment  = new EditUserFragment();
                args.putInt(ViewUserFragment.ARG_USERID, (-1));
                fragment.setArguments(args);
                // Insert the fragment by replacing any existing fragment

                man.setCustomAnimations(R.transition.activity_slide, R.transition.activity_slideout, R.transition.activity_slidereverse,R.transition.activity_slideoutreverse);
                man.replace(R.id.content_frame, fragment).addToBackStack( null );
                man.commit();
                break;
            case 1:
                scanQR(view);
                break;
            case 2:
                fragment  = new ViewAllUsersFragment();
                fragment.setArguments(args);
                fragmentManager.setCustomAnimations(R.transition.activity_slide, R.transition.activity_slideout, R.transition.activity_slidereverse,R.transition.activity_slideoutreverse);
                fragmentManager
                        .replace(R.id.content_frame, fragment).addToBackStack( null )
                        .commit();
                break;
        }
        // Insert the fragment by replacing any existing fragment


        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);

    }

    @Override
    public void onBackPressed() {
        // (if you called previously to addToBackStack() in your transaction)
        if (getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().popBackStack();
        }
        // Default action on back pressed
        else
            super.onBackPressed();
    }

    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {

            if (resultCode == RESULT_OK) {
                Fragment fragment=null;
                Bundle args = new Bundle();
                //get the extras that are returned from the intent

                String contents = intent.getStringExtra("SCAN_RESULT");

                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");

                fragment  = new ViewUserFragment();


                args.putInt(ViewUserFragment.ARG_USERID, Integer.parseInt(contents));


                fragment.setArguments(args);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment).addToBackStack(null )
                        .commit();
            }
        }
    }

    public void scanQR(View v) {
        try {
            //start the scanning activity from the com.google.zxing.client.android.SCAN intent
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {

            //on catch, show the download dialog

            showDialog(HomeScreen.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();

        }
    }

    private static AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {

        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);

        downloadDialog.setTitle(title);

        downloadDialog.setMessage(message);

        downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int i) {

                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);

                try {

                    act.startActivity(intent);

                } catch (ActivityNotFoundException anfe) {

                }
            }
        });
        downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        return downloadDialog.show();
    }
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
       // getActionBar().setTitle(mTitle);
    }

}
