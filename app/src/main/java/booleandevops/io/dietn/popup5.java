package booleandevops.io.dietn;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class popup5 extends BottomSheetDialogFragment  implements View.OnClickListener {

    public static final String TAG = "ActionBottomDialog";
    public popup5(Custominterface callback) {
        this.callback =callback;
    }
    public popup5(){

    }

    public static popup5 newInstance() {
        return new popup5();
    }
    Custominterface callback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.popupbottomslide, container, false);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView title = view.findViewById(R.id.title);
        ImageView back = view.findViewById(R.id.imageView4);
        title.setText("Exercise");
        back.setBackgroundResource(R.drawable.purplepanel1);
        LottieAnimationView lottieAnimationView = view.findViewById(R.id.logomeal);
        lottieAnimationView.setAnimation(R.raw.exercise);
        lottieAnimationView.playAnimation();
        lottieAnimationView.loop(true);
        Spinner spinner = view.findViewById(R.id.Foodtype);
        ArrayAdapter<String> spinnerfoodAdapter = new  ArrayAdapter<String>(
                getContext(),
                R.layout.spinner_itemfood,
                getResources().getStringArray(R.array.exercise));
        spinner.setAdapter(spinnerfoodAdapter);
        Button cancel = view.findViewById(R.id.button2);
        Button add = view.findViewById(R.id.button3);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 5;
                callback.callbackmethod(i);
            }
        });
    }


    @Override
    public void onClick(View v) {

    }
}





