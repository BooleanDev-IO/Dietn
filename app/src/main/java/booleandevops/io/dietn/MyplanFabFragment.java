package booleandevops.io.dietn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nightonke.jellytogglebutton.JellyToggleButton;
import com.nightonke.jellytogglebutton.State;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;


/**
 * Created by krupenghetiya on 23/06/17.
 */

public class MyplanFabFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    public static final String TAG = "ActionBottomDialog";

    String[] mdescrip = {"4 eggs", "2 egg yolk", "1 spoon", "80-100gm", "1 piece",
            "1 bowel of 100gm", "1 capsule", "50 gm", "1 table spoon", "4 roti",
            "70-100gm", "1 plate", "1 bowel 250gm", "1 tumbler", "1 cup",
            "Any Sabzi with 100 gm rice", "100gm", "1 plate"};
    ImageButton img1, img2;
    ListView listView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseUser firebaseAuth;
    ArrayAdapter<String> adapter,adapter1,adapter2,adapter3;
    String name;
    JellyToggleButton jellyToggleButton;
    TextView heading;
    public static MyplanFabFragment newInstance() {
        return new MyplanFabFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.filter_plan_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme);
        listView = getView().findViewById(R.id.listchart);
        jellyToggleButton = view.findViewById(R.id.jtb);
        jellyToggleButton.toggle();
        ImageButton imageButton = view.findViewById(R.id.btn_close);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        img1 = view.findViewById(R.id.brkdiet);
        img2 = view.findViewById(R.id.lunchdiet);
        heading = view.findViewById(R.id.headingfordiet);
        firebaseAuth = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseAuth != null){
          name = firebaseAuth.getDisplayName();
          System.out.println(name);
        }
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Users").child(name).child("body");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue().toString();
                   jellyToggleButton.setOnStateChangeListener(new JellyToggleButton.OnStateChangeListener() {
                       @Override
                       public void onStateChange(float process, State state, JellyToggleButton jtb) {
                           if(state.equals(State.LEFT)){
                               heading.setText("Breakfast");
                               adapter = new  ArrayAdapter<String>(
                                       getContext(),
                                       android.R.layout.simple_list_item_1,
                                       getResources().getStringArray(R.array.flbrkV));
                               listView.setAdapter(adapter);
                               img1.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       heading.setText("Breakfast");
                                       adapter = new  ArrayAdapter<String>(
                                               getContext(),
                                               android.R.layout.simple_list_item_1,
                                               getResources().getStringArray(R.array.flbrkV));
                                       listView.setAdapter(adapter);

                                   }
                               });
                               img2.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       heading.setText("Lunch");
                                       adapter1 = new  ArrayAdapter<String>(
                                               getContext(),
                                               android.R.layout.simple_list_item_1,
                                               getResources().getStringArray(R.array.fllunV));
                                       listView.setAdapter(adapter1);
                                   }
                               });
//

                           }else if (state.equals(State.RIGHT)){
                               heading.setText("Breakfast");
                               adapter = new  ArrayAdapter<String>(
                                       getContext(),
                                       android.R.layout.simple_list_item_1,
                                       getResources().getStringArray(R.array.flbrkN));
                               listView.setAdapter(adapter);
                                img1.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       heading.setText("Breakfast");
                                       adapter = new  ArrayAdapter<String>(
                                               getContext(),
                                               android.R.layout.simple_list_item_1,
                                               getResources().getStringArray(R.array.flbrkN));
                                       listView.setAdapter(adapter);

                                   }
                               });
                               img2.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       heading.setText("Lunch");
                                       adapter1 = new  ArrayAdapter<String>(
                                               getContext(),
                                               android.R.layout.simple_list_item_1,
                                               getResources().getStringArray(R.array.fllunN));
                                       listView.setAdapter(adapter1);
                                   }
                               });
                           }
                       }
                   });
                }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Button buy = view.findViewById(R.id.Buybtn);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Buy option coming soon", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onClick(View v) {

    }
}

