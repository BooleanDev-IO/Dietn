package booleandevops.io.dietn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


/**
 * Created by krupenghetiya on 23/06/17.
 */

public class MyBMIFabFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    public static final String TAG = "ActionBottomDialog";



    public static MyBMIFabFragment newInstance() {
        return new MyBMIFabFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.filter_bmi_view, container, false);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStyle(STYLE_NORMAL, R.style. AppBottomSheetDialogTheme);

        ImageButton imageButton= view.findViewById(R.id.btn_close);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        Spinner weightunit = view.findViewById(R.id.units);
        final Spinner Heightfeet = view.findViewById(R.id.heightfeet);
        final EditText weight = view.findViewById(R.id.editText);
        final TextView bmiresult= view.findViewById(R.id.bmiresult2);
       Button calculate = view.findViewById(R.id.button);

        ArrayAdapter<String> spinnerCountShoesArrayAdapter = new  ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.units));
        ArrayAdapter<String> spinnerCountShoesArrayAdapter1 = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.Heightfeet));


        weightunit.setAdapter(spinnerCountShoesArrayAdapter);
        Heightfeet.setAdapter(spinnerCountShoesArrayAdapter1);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String H1 = Heightfeet.getSelectedItem().toString();
                String W1 = weight.getText().toString();

                float feettometer = 3.2808f;
                float height = Float.parseFloat(H1);
                float weight1 = Float.parseFloat(W1);
                float ww1 = height/feettometer;

                float bmi = weight1/(ww1*ww1);
                String BMI = String.valueOf(bmi);
                bmiresult.setText(BMI);
            }
        });

    }









    @Override
    public void onClick(View v) {

    }
}

