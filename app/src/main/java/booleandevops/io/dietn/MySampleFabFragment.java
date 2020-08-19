package booleandevops.io.dietn;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.varunest.sparkbutton.SparkButton;
import com.varunest.sparkbutton.SparkEventListener;


/**
 * Created by krupenghetiya on 23/06/17.
 */

public class MySampleFabFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    public static final String TAG = "ActionBottomDialog";



    public static MySampleFabFragment newInstance() {
        return new MySampleFabFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.filter_sample_view, container, false);
    }

    @Override public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStyle(STYLE_NORMAL, R.style. AppBottomSheetDialogTheme);

        ImageButton imageButton= view.findViewById(R.id.btn_close);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        SparkButton sparkButton = view.findViewById(R.id.spark_button);
SparkButton helpline = view.findViewById(R.id.spark_button1);

        sparkButton.setEventListener(new SparkEventListener() {
            @Override
            public void onEvent(ImageView button, boolean buttonState) {

            }

            @Override
            public void onEventAnimationEnd(ImageView button, boolean buttonState) {
                        onClick(view);
            }

            @Override
            public void onEventAnimationStart(ImageView button, boolean buttonState) {

            }
        });
        helpline.setEventListener(new SparkEventListener() {
            @Override
            public void onEvent(ImageView button, boolean buttonState) {

            }

            @Override
            public void onEventAnimationEnd(ImageView button, boolean buttonState) {
                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_SUBJECT, "I need help regarding");
                    email.putExtra(Intent.EXTRA_TEXT, "Copy the email address in the 'TO' section (dietn_helpline@gmail.com).\nType your querry here!");
                    email.setType("text/plain");
                    startActivity(Intent.createChooser(email,"Choose an application:"));
            }

            @Override
            public void onEventAnimationStart(ImageView button, boolean buttonState) {

            }
        });
    }









    @Override
    public void onClick(View v) {
            Intent intent = new Intent(getContext(),Aboutus.class);
            startActivity(intent);
    }

}

