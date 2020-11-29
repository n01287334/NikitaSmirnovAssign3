package nikita.smirnov.n01287334.nikitalab3;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.preference.CheckBoxPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreference;

public class SettingsFragment extends PreferenceFragmentCompat {

    public SwitchPreference testPref;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

         final View view = super.onCreateView(inflater, container, savedInstanceState);




//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
//        String b = "";
//        if (preferences.getBoolean("portrait", true)) {
//            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//            Log.d("key is ON", b);
//        }else {
//            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
//            Log.d("key is OFF", b);
//        }



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
                if (preferences.getBoolean("portrait", true)) {
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    Log.d("key is ON", b);
                }else {
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                    Log.d("key is OFF", b);
                }





                return true;
            }
        });

        return view;
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("portrait")) {
            boolean portrait = sharedPreferences.getBoolean("portrait", false);
            //Do whatever you want here. This is an example.
            if (portrait) {
                testPref.setSummary("Enabled");
                String on = "on";
                Log.d("key is on", on);
            } else {
                testPref.setSummary("Disabled");
                String off = "off";
                Log.d("key is off", off);
            }
        }
    }






}

