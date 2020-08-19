package booleandevops.io.dietn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nightonke.jellytogglebutton.JellyToggleButton;
import com.nightonke.jellytogglebutton.JellyTypes.JellyStyle;
import com.nightonke.jellytogglebutton.State;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.lang.reflect.Array;


/**
 * Created by krupenghetiya on 23/06/17.
 */

public class MyScheduleFabFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    public static final String TAG = "ActionBottomDialog";

    String[] mdescrip = {"4 eggs", "2 egg yolk", "1 spoon", "80-100gm", "1 piece",
            "1 bowel of 100gm", "1 capsule", "50 gm", "1 table spoon", "4 roti",
            "70-100gm", "1 plate", "1 bowel 250gm", "1 tumbler", "1 cup",
            "Any Sabzi with 100 gm rice", "100gm", "1 plate"};
    ImageButton img1, img2,img3,img4;
    ListView listView,listView1,listView2,listView3;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseUser firebaseAuth;
    ArrayAdapter<String> adapter,adapter1,adapter2,adapter3;
    String name;
    JellyToggleButton jellyToggleButton;
    TextView heading;
    public static MyScheduleFabFragment newInstance() {
        return new MyScheduleFabFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.filter_shedule_view, container, false);
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
        MaterialCalendarView calendarView = view.findViewById(R.id.calendarView);
        calendarView.setDateSelected(CalendarDay.today(),true);
        img1 = view.findViewById(R.id.brkdiet);
        img2 = view.findViewById(R.id.lunchdiet);
        img3 = view.findViewById(R.id.evediet);
        img4 = view.findViewById(R.id.dnrdiet);
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
                if(value.matches("Normal")){
                   jellyToggleButton.setOnStateChangeListener(new JellyToggleButton.OnStateChangeListener() {
                       @Override
                       public void onStateChange(float process, State state, JellyToggleButton jtb) {
                           if(state.equals(State.LEFT)){
                               heading.setText("Breakfast");
                               adapter = new  ArrayAdapter<String>(
                                       getContext(),
                                       android.R.layout.simple_list_item_1,
                                       getResources().getStringArray(R.array.dietbrkN));
                               listView.setAdapter(adapter);
                               img1.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       heading.setText("Breakfast");
                                       adapter = new  ArrayAdapter<String>(
                                               getContext(),
                                               android.R.layout.simple_list_item_1,
                                               getResources().getStringArray(R.array.dietbrkN));
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
                                               getResources().getStringArray(R.array.dietlunN));
                                       listView.setAdapter(adapter1);
                                   }
                               });
                               img3.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       heading.setText("Snack");
                                       adapter2 = new  ArrayAdapter<String>(
                                               getContext(),
                                               android.R.layout.simple_list_item_1,
                                               getResources().getStringArray(R.array.dietsnkN));
                                       listView.setAdapter(adapter2);
                                   }
                               });
                               img4.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       heading.setText("Dinner");
                                       adapter3 = new  ArrayAdapter<String>(
                                               getContext(),
                                               android.R.layout.simple_list_item_1,
                                               getResources().getStringArray(R.array.dietdnrN));
                                       listView.setAdapter(adapter3);
                                   }
                               });
//

                           }else if (state.equals(State.RIGHT)){
                               heading.setText("Breakfast");
                               adapter = new  ArrayAdapter<String>(
                                       getContext(),
                                       android.R.layout.simple_list_item_1,
                                       getResources().getStringArray(R.array.dietbrkNon));
                               listView.setAdapter(adapter);
                                img1.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       heading.setText("Breakfast");
                                       adapter = new  ArrayAdapter<String>(
                                               getContext(),
                                               android.R.layout.simple_list_item_1,
                                               getResources().getStringArray(R.array.dietbrkNon));
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
                                               getResources().getStringArray(R.array.dietlunNon));
                                       listView.setAdapter(adapter1);
                                   }
                               });
                               img3.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       heading.setText("Snack");
                                       adapter2 = new  ArrayAdapter<String>(
                                               getContext(),
                                               android.R.layout.simple_list_item_1,
                                               getResources().getStringArray(R.array.dietsnkNon));
                                       listView.setAdapter(adapter2);
                                   }
                               });
                               img4.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       heading.setText("Dinner");
                                       adapter3 = new  ArrayAdapter<String>(
                                               getContext(),
                                               android.R.layout.simple_list_item_1,
                                               getResources().getStringArray(R.array.dietdnrNon));
                                       listView.setAdapter(adapter3);
                                   }
                               });
                            
                           }
                       }
                   });


                }
                else if(value.matches("Thin")){

                    jellyToggleButton.setOnStateChangeListener(new JellyToggleButton.OnStateChangeListener() {
                        @Override
                        public void onStateChange(float process, State state, JellyToggleButton jtb) {
                            if(state.equals(State.LEFT)){
                                heading.setText("Breakfast");
                                adapter = new  ArrayAdapter<String>(
                                        getContext(),
                                        android.R.layout.simple_list_item_1,
                                        getResources().getStringArray(R.array.mslgbrkV));
                                listView.setAdapter(adapter);
                                img1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        heading.setText("Breakfast");
                                        adapter = new  ArrayAdapter<String>(
                                                getContext(),
                                                android.R.layout.simple_list_item_1,
                                                getResources().getStringArray(R.array.mslgbrkV));
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
                                                getResources().getStringArray(R.array.mslglunV));
                                        listView.setAdapter(adapter1);
                                    }
                                });
                                img3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        heading.setText("Snack");
                                        adapter2 = new  ArrayAdapter<String>(
                                                getContext(),
                                                android.R.layout.simple_list_item_1,
                                                getResources().getStringArray(R.array.mslgsnkV));
                                        listView.setAdapter(adapter2);
                                    }
                                });
                                img4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        heading.setText("Dinner");
                                        adapter3 = new  ArrayAdapter<String>(
                                                getContext(),
                                                android.R.layout.simple_list_item_1,
                                                getResources().getStringArray(R.array.mslgdnrv));
                                        listView.setAdapter(adapter3);
                                    }
                                });
                            }else if(state.equals(State.RIGHT)){
                                heading.setText("Breakfast");
                                adapter = new  ArrayAdapter<String>(
                                        getContext(),
                                        android.R.layout.simple_list_item_1,
                                        getResources().getStringArray(R.array.mslgbrkN));
                                listView.setAdapter(adapter);
                                img1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        heading.setText("Breakfast");
                                        adapter = new  ArrayAdapter<String>(
                                                getContext(),
                                                android.R.layout.simple_list_item_1,
                                                getResources().getStringArray(R.array.mslgbrkN));
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
                                                getResources().getStringArray(R.array.mslglunN));
                                        listView.setAdapter(adapter1);
                                    }
                                });
                                img3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        heading.setText("Snack");
                                        adapter2 = new  ArrayAdapter<String>(
                                                getContext(),
                                                android.R.layout.simple_list_item_1,
                                                getResources().getStringArray(R.array.mslgsnkN));
                                        listView.setAdapter(adapter2);
                                    }
                                });
                                img4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        heading.setText("Dinner");
                                        adapter3 = new  ArrayAdapter<String>(
                                                getContext(),
                                                android.R.layout.simple_list_item_1,
                                                getResources().getStringArray(R.array.mslgdnrN));
                                        listView.setAdapter(adapter3);
                                    }
                                });
                            }
                        }
                    });

                }else if(value.matches("Fat")){

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
                                img3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        heading.setText("Snack");
                                        adapter2 = new  ArrayAdapter<String>(
                                                getContext(),
                                                android.R.layout.simple_list_item_1,
                                                getResources().getStringArray(R.array.flsnkV));
                                        listView.setAdapter(adapter2);
                                    }
                                });
                                img4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        heading.setText("Dinner");
                                        adapter3 = new  ArrayAdapter<String>(
                                                getContext(),
                                                android.R.layout.simple_list_item_1,
                                                getResources().getStringArray(R.array.fldnrV));
                                        listView.setAdapter(adapter3);
                                    }
                                });
                            }else if(state.equals(State.RIGHT)){
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
                                img3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        heading.setText("Snack");
                                        adapter2 = new  ArrayAdapter<String>(
                                                getContext(),
                                                android.R.layout.simple_list_item_1,
                                                getResources().getStringArray(R.array.flsnkN));
                                        listView.setAdapter(adapter2);
                                    }
                                });
                                img4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        heading.setText("Dinner");
                                        adapter3 = new  ArrayAdapter<String>(
                                                getContext(),
                                                android.R.layout.simple_list_item_1,
                                                getResources().getStringArray(R.array.fldnrN));
                                        listView.setAdapter(adapter3);
                                    }
                                });
                            }
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    @Override
    public void onClick(View v) {

    }
}

