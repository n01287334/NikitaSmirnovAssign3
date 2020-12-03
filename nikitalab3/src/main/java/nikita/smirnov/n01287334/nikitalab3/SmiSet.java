//Nikita Smirnov n01287334 RNB
package nikita.smirnov.n01287334.nikitalab3;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SeekBarPreference;
import androidx.preference.SwitchPreference;

public class SmiSet extends PreferenceFragmentCompat {

    private SeekBar VolumeSeekbar = null;
    private SeekBar BrightnessSeekbar = null;
    private AudioManager audioManager = null;
    private float BackLightValue;
    //Seek bar object
    SeekBarPreference seekBar;
    //Variable to store brightness value
    private int brightness;
    //Content resolver used as a handle to the system's settings
    private ContentResolver cResolver;
    //Window object, that will store a reference to the current window
    private Window window;
    TextView txtPerc;

    public SwitchPreference testPref;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.smi_set, rootKey);

        seekBar = (SeekBarPreference) findPreference("seek");
        String s = seekBar.toString();
        Log.d("seek is", s);
        //txtPerc = (TextView) findViewById(R.id.txtPercentage);
        //Get the content resolver
        cResolver =  getActivity().getContentResolver();
        //Get the current window
        window = getActivity().getWindow();

        //BrightnessControl();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = super.onCreateView(inflater, container, savedInstanceState);

        ListPreference listPref = (ListPreference) findPreference("listPref");
        String value = listPref.getValue().toString();
        Log.d("value is", value);

        switch(value){
            case "1":
                view.setBackgroundColor(Color.rgb(52, 168, 235));
                break;
            case "2":
                view.setBackgroundColor(Color.GREEN);
                break;
            case "3":
                view.setBackgroundColor(Color.WHITE);
                break;
//                    default:
//                        vIew.setBackgroundColor(Color.WHITE);
        }


        Preference button = findPreference(getString(R.string.myCoolButton));
        button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

            @Override
            public boolean onPreferenceClick(Preference preference) {

                ListPreference listPref = (ListPreference) findPreference("listPref");
                String value = listPref.getValue().toString();
                switch(value){
                    case "1":
                        view.setBackgroundColor(Color.rgb(52, 168, 235));
                        break;
                    case "2":
                        view.setBackgroundColor(Color.GREEN);
                        break;
                    case "3":
                        view.setBackgroundColor(Color.WHITE);
                        break;
                    //default:
                    //    vIew.setBackgroundColor(Color.WHITE);
                }

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                String b = "";
                if (preferences.getBoolean("portrait", false)) {
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    Log.d("key is ON", b);
                }else {
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                    Log.d("key is OFF", b);
                }

                return true;
            }
        });

//if(Settings.System.canWrite(getContext())){
//    Log.i("can write", "can write");
//
//        }
//else{
//    writePermission();
//    Log.i("canT write", "canT write");
//}
//
//
//onProgressChanged(seekBar, 10, true);
//        onStartTrackingTouch(seekBar);
//        onStopTrackingTouch(seekBar);

        return view;
    }

    //@Override
    public void onProgressChanged(SeekBarPreference arg0, int arg1, boolean arg2) {
        // TODO Auto-generated method stub
        BackLightValue = (float)arg1/100;

        WindowManager.LayoutParams layoutParams = getActivity().getWindow().getAttributes();
        layoutParams.screenBrightness = BackLightValue;
        getActivity().getWindow().setAttributes(layoutParams);
    }
    //@Override
    public void onStartTrackingTouch(SeekBarPreference arg0) {
    }
    //@Override
    public void onStopTrackingTouch(SeekBarPreference arg0) {

        int SysBackLightValue = (int)(BackLightValue * 255);
        android.provider.Settings.System.putInt(getActivity().getContentResolver(),
                android.provider.Settings.System.SCREEN_BRIGHTNESS,
                SysBackLightValue);
    }


    public void writePermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(getContext().getApplicationContext())) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:" + getContext().getPackageName()));
                startActivityForResult(intent, 200);

            }
        }
    }

//    private void BrightnessControl() {
//        //Set the seekbar range between 0 and 255
//        //seek bar settings//
//        //sets the range between 0 and 255
//        //seekBar = (SeekBar) getActivity().findViewById(R.id.seek);
//        seekBar = (SeekBarPreference) findPreference("seek");
//        String s = seekBar.toString();
//        Log.d("seek is", s);
//        seekBar.setMax(255);
//        //set the seek bar progress to 1
//        seekBar.setKeyProgressIncrement(1);
//
//        try {
//            //Get the current system brightness
//            brightness = Settings.System.getInt(cResolver, Settings.System.SCREEN_BRIGHTNESS);
//        } catch (Settings.SettingNotFoundException e) {
//            //Throw an error case it couldn't be retrieved
//            Log.e("Error", "Cannot access system brightness");
//            e.printStackTrace();
//        }
//
//        //Set the progress of the seek bar based on the system's brightness
//        seekBar.setProgress(brightness);
//
//        //Register OnSeekBarChangeListener, so it can actually change values
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                //Set the system brightness using the brightness variable value
//                //Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS, brightness);
//                Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
//                //Get the current window attributes
//                WindowManager.LayoutParams layoutpars = window.getAttributes();
//                //Set the brightness of this window
//                layoutpars.screenBrightness = brightness / (float) 255;
//                //Apply attribute changes to this window
//                window.setAttributes(layoutpars);
//            }
//
//            public void onStartTrackingTouch(SeekBar seekBar) {
//                //Nothing handled here
//            }
//
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                //Set the minimal brightness level
//                //if seek bar is 20 or any value below
//                if (progress <= 20) {
//                    //Set the brightness to 20
//                    brightness = 20;
//                } else //brightness is greater than 20
//                {
//                    //Set brightness variable based on the progress bar
//                    brightness = progress;
//                }
//                //Calculate the brightness percentage
//                float perc = (brightness / (float) 255) * 100;
//                //Set the brightness percentage
//                //txtPerc.setText((int)perc +" %");
//            }
//        });
//    }

}

