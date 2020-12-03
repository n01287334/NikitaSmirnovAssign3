//Nikita Smirnov n01287334 RNB
package nikita.smirnov.n01287334.nikitalab3;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class NikitaActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar nikitatoolbar;
    private NavigationView nvDrawer;

    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;

    boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);

    private SeekBar VolumeSeekbar = null;
    private SeekBar BrightnessSeekbar = null;
    private AudioManager audioManager = null;
    private float BackLightValue;
    //Seek bar object
    private SeekBar seekBar;
    //Variable to store brightness value
    private int brightness;
    //Content resolver used as a handle to the system's settings
    private ContentResolver cResolver;
    //Window object, that will store a reference to the current window
    private Window window;
    TextView txtPerc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set a Toolbar to replace the ActionBar.
        nikitatoolbar = (Toolbar) findViewById(R.id.nikitatoolbar);
        setSupportActionBar(nikitatoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // This will display an Up icon (<-), we will replace it with hamburger later
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.nikitadrawer_layout);
        drawerToggle = setupDrawerToggle();

        // Setup toggle to display hamburger icon with nice animation
        setupDrawerToggle().setDrawerIndicatorEnabled(true);
        //drawerToogle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        nvDrawer = (NavigationView) findViewById(R.id.nikitadrawerView);

        // Setup drawer view
        setupDrawerContent(nvDrawer);

        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);

        //setContentView(R.layout.ni_home);
        if(savedInstanceState == null) {
            getSupportFragmentManager().
                    beginTransaction().replace(R.id.nikitadrawerContent,new NiHome()).commit();
        }

        seekBar = (SeekBar) findViewById(R.id.nikitaseek);
        //txtPerc = (TextView) findViewById(R.id.txtPercentage);
        //Get the content resolver
        cResolver =  getContentResolver();
        //Get the current window
        window = getWindow();

//        BrightnessControl();
//        ACTION_MANAGE_WRITE_SETTINGS;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.helpmenu, menu);
        return true;
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid nikitatoolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawer, nikitatoolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass; //=HomeFragment.class;
        switch (menuItem.getItemId()) {
            case R.id.nikita_home:
                fragmentClass = NiHome.class;
                break;
            case R.id.nikita_settings:
                fragmentClass = SmiSet.class;
                break;
            default:
                fragmentClass = NiHome.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        ((FragmentManager) fragmentManager).beginTransaction().replace(R.id.nikitadrawerContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }

    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE 1: Make sure to override the method with only a single `Bundle` argument
    // Note 2: Make sure you implement the correct `onPostCreate(Bundle savedInstanceState)` method.
    // There are 2 signatures and only `onPostCreate(Bundle state)` shows the hamburger icon.
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

@Override
public boolean onOptionsItemSelected(MenuItem item) {
    if (drawerToggle.onOptionsItemSelected(item)) {
        return true;
    }
    switch(item.getItemId()) {
        case R.id.item1:
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=MbU4fPYRvcY&t=1s"));
            startActivity(browserIntent);
    }

    return super.onOptionsItemSelected(item);
}



//    private void BrightnessControl() {
//        //Set the seekbar range between 0 and 255
//        //seek bar settings//
//        //sets the range between 0 and 255
//        seekBar = (SeekBar) findViewById(R.id.nikitaseek);
//
//        seekBar.setMax(255);
//        //set the seek bar progress to 1
//        seekBar.setKeyProgressIncrement(1);
//
//        try
//        {
//            //Get the current system brightness
//            brightness = Settings.System.getInt(cResolver, Settings.System.SCREEN_BRIGHTNESS);
//        }
//        catch (Settings.SettingNotFoundException e)
//        {
//            //Throw an error case it couldn't be retrieved
//            Log.e("Error", "Cannot access system brightness");
//            e.printStackTrace();
//        }
//
//        //Set the progress of the seek bar based on the system's brightness
//        seekBar.setProgress(brightness);
//
//        //Register OnSeekBarChangeListener, so it can actually change values
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
//        {
//            public void onStopTrackingTouch(SeekBar seekBar)
//            {
//                //Set the system brightness using the brightness variable value
//                //Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS, brightness);
//                Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
//                //Get the current window attributes
//                WindowManager.LayoutParams layoutpars = window.getAttributes();
//                //Set the brightness of this window
//                layoutpars.screenBrightness = brightness / (float)255;
//                //Apply attribute changes to this window
//                window.setAttributes(layoutpars);
//            }
//            public void onStartTrackingTouch(SeekBar seekBar)
//            {
//                //Nothing handled here
//            }
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
//            {
//                //Set the minimal brightness level
//                //if seek bar is 20 or any value below
//                if(progress<=20)
//                {
//                    //Set the brightness to 20
//                    brightness=20;
//                }
//                else //brightness is greater than 20
//                {
//                    //Set brightness variable based on the progress bar
//                    brightness = progress;
//                }
//                //Calculate the brightness percentage
//                float perc = (brightness /(float)255)*100;
//                //Set the brightness percentage
//                //txtPerc.setText((int)perc +" %");
//            }
//        });
//    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        IsFinish(getString(R.string.onbackalert));
    }

    public void IsFinish(String alertmessage) {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        android.os.Process.killProcess(android.os.Process.myPid());
                        // This above line close correctly
                        //finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };
        Context context = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(alertmessage)
                .setPositiveButton(getString(R.string.yes), dialogClickListener)
                .setNegativeButton(getString(R.string.no), dialogClickListener).show();

    }



}