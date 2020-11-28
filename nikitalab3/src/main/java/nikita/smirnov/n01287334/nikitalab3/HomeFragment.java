package nikita.smirnov.n01287334.nikitalab3;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.provider.DocumentsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
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
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false);


        View RootView = inflater.inflate(R.layout.fragment_home, container, false);
//        TextView tv = (TextView)RootView.findViewById(R.id.currentdate);
//        tv.setText("HI");

        DateFormat df = new SimpleDateFormat("yyyy-MMM-dd,  HH:mm");
        String dateToday = df.format(Calendar.getInstance().getTime());
        TextView title = (TextView) RootView.findViewById(R.id.currentdate);
        title.setText(dateToday);


        final Spinner spinner = (Spinner) RootView.findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.courses, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        final TextView textView = (TextView)spinner.getSelectedView();
        //final String text = textView.getText().toString();
        final String text = spinner.getSelectedItem().toString();




       final DatePicker picker =(DatePicker)RootView.findViewById(R.id.datePicker1);
//        TextView tvw = null;
//        tvw.setText("Selected Date: "+ picker.getDayOfMonth()+"/"+ (picker.getMonth() + 1)+"/"+picker.getYear());
//        final String tv = tvw.toString();

        ImageButton button = (ImageButton) RootView.findViewById(R.id.imageButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                //showDialog();

                new AlertDialog.Builder(getContext())
                        .setTitle(chosenC(spinner)+ " " +getDateFromDatePicker(picker))
//                        .setMessage(getDateFromDatePicker(picker))
                        //.setMessage(chosenC(spinner)+ " " +getDateFromDatePicker(picker))
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)

                        .show();
            }
        });


        return RootView;


    }

    public String chosenC(Spinner spinner){

//        //Spinner spinner = (Spinner) RootView.findViewById(R.id.spinner);
//// Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
//                R.array.courses, android.R.layout.simple_spinner_item);
//// Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//// Apply the adapter to the spinner
//        spinner.setAdapter(adapter);
//        TextView textView = (TextView)spinner.getSelectedView();
//        //final String text = textView.getText().toString();
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

//    public void showDialog(){

//        new AlertDialog.Builder(getContext())
//                .setTitle("Delete entry")
//                .setMessage(R.string.app_name)
//
//                // Specifying a listener allows you to take an action before dismissing the dialog.
//                // The dialog is automatically dismissed when a dialog button is clicked.
//
//                // A null listener allows the button to dismiss the dialog and take no further action.
//                .setNegativeButton(android.R.string.no, null)
//
//                .show();
//        }
}
 class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {
    //...

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
         parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}

