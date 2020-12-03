//Nikita Smirnov n01287334 RNB
package nikita.smirnov.n01287334.nikitalab3;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NiHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NiHome extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    //Seek bar object
    private SeekBar seekBar;
    //Variable to store brightness value
    private int brightness;
    //Content resolver used as a handle to the system's settings
    private ContentResolver cResolver;
    //Window object, that will store a reference to the current window
    private Window window;
    TextView txtPerc;
    /** Called when the activity is first created. */

    public NiHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NiHome newInstance(String param1, String param2) {
        NiHome fragment = new NiHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View RootView = inflater.inflate(R.layout.ni_home, container, false);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd,  HH:mm");
        String dateToday = dateFormat.format(Calendar.getInstance().getTime());
        TextView title = (TextView) RootView.findViewById(R.id.nikitacurrentdate);
        title.setText(dateToday);

        final Spinner spinner = (Spinner) RootView.findViewById(R.id.nikitaspinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.courses, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        final DatePicker picker =(DatePicker)RootView.findViewById(R.id.nikitadatePicker1);
        ImageButton button = (ImageButton) RootView.findViewById(R.id.nikitaimageButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                new AlertDialog.Builder(getContext())
                        .setTitle(chosenC(spinner)+ " " +getDateFromDatePicker(picker))
                        //.setMessage(chosenC(spinner)+ " " +getDateFromDatePicker(picker))
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String b = "";
        if (preferences.getBoolean("portrait", true)) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            Log.d("key is ON", b);
        }else {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
            Log.d("key is OFF", b);
        }

        SharedPreferences colors_app = getContext().getSharedPreferences("listPref", Context.MODE_PRIVATE);
        int colorcode2 = colors_app.getInt("listPref", 3);
        String cc2 = Integer.toString(colorcode2);


        String str =getDefaults("listPref", getContext());
        switch (str) {
            case "1":
                RootView.findViewById(R.id.nikitafragment_home).setBackgroundColor(getResources().getColor(R.color.colorBgBlue));
                break;
            case "2":
                RootView.findViewById(R.id.nikitafragment_home).setBackgroundColor(getResources().getColor(R.color.green));
                break;
            case "3":
                RootView.findViewById(R.id.nikitafragment_home).setBackgroundColor(getResources().getColor(R.color.colorBgGreen));
                break;
        }
        //onBackPressed();
        return RootView;
    }

    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    public String chosenC(Spinner spinner){
        String text = spinner.getSelectedItem().toString();
        return text;
    }

    public static String getDateFromDatePicker(DatePicker datePicker){
        DateFormat f = new SimpleDateFormat("MMM-dd-yyyy");
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        //calendar.getTime();
         String a = f.format(calendar.getTime());
        return a;
    }

}

