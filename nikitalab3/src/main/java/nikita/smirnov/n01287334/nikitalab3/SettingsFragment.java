package nikita.smirnov.n01287334.nikitalab3;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.ColorRes;
import androidx.preference.CheckBoxPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View RootView = inflater.inflate(R.layout.root_preferences, container, false);

//        ListPreference listPref = (ListPreference) findPreference("listPref");
//        String value = listPref.getValue().toString();


         View view = super.onCreateView(inflater, container, savedInstanceState);

         final View vIew = view;

//        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
//        String defaultValue = "default"; // assign some meaningful default value
//         String amountPlayers = sharedPref.getString("listPref", defaultValue);
//        final String x=bg(amountPlayers);

       // view.setBackgroundColor(Color.CYAN);
        //getListView().setBackgroundColor(Color.rgb(4, 26, 55));

//        Preference key = findPreference(getString(R.string.cancel));

        ListPreference listPref = (ListPreference) findPreference("listPref");
        String value = listPref.getValue().toString();
        Log.d("value is", value);
        switch(value){
            case "3":
                vIew.setBackgroundColor(Color.WHITE);
                break;
            case "2":
                vIew.setBackgroundColor(Color.GREEN);
                break;
            case "1":
                vIew.setBackgroundColor(Color.BLUE);
                break;
//                    default:
//                        vIew.setBackgroundColor(Color.WHITE);
        }


        Preference button = findPreference(getString(R.string.myCoolButton));
        button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

            @Override
            public boolean onPreferenceClick(Preference preference) {
                //ListPreference color = (ListPreference)RootView.findViewById(R.id.)
//                String key = preference.getKey();
//                Log.d("key is", key);
//                //if (preference == 3)
//                switch(key){
//                    case "3":
//                        vIew.setBackgroundColor(Color.WHITE);
//                        break;
//                    case "2":
//                        //setTheme(R.style.PreferenceTheme2);
//                        vIew.setBackgroundColor(Color.CYAN);
//                        break;
//                    case "1":
//                        vIew.setBackgroundColor(Color.BLUE);
//                        //setTheme(R.style.PreferenceTheme1);
//                        break;
////                    default:
////                        vIew.setBackgroundColor(Color.WHITE);
//                }

                ListPreference listPref = (ListPreference) findPreference("listPref");
                String value = listPref.getValue().toString();
//                //listPref.commit();
//                Log.d("value is", value);
                switch(value){
                    case "1":
                        vIew.setBackgroundColor(Color.rgb(52, 168, 235));
                        break;
                    case "2":
                        vIew.setBackgroundColor(Color.GREEN);
//                        setTheme(R.style.PreferenceTheme2);
                        break;
                    case "3":
                        vIew.setBackgroundColor(Color.WHITE);
                        break;
                    //default:
                    //    vIew.setBackgroundColor(Color.WHITE);
                }

                return true;
            }
        });

        return vIew;

        //return RootView;
    }

//    public String val(){
//        ListPreference listPref = (ListPreference) findPreference("listPref");
//        String value = listPref.getValue().toString();
//                return value;
//    }


    public String bg(){
//        String amountPlayers = sharedPref.getString("listPref", defaultValue);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        String defaultValue = "default"; // assign some meaningful default value
        String amountPlayers = sharedPref.getString("listPref", defaultValue);


        return amountPlayers;
    }

    //setTheme(android.R.style.Theme);
}