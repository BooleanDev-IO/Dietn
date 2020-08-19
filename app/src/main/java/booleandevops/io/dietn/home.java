package booleandevops.io.dietn;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ramotion.circlemenu.CircleMenuView;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;


/**
 * A simple {@link Fragment} subclass.
 */
public class home extends Fragment implements Custominterface {
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String BRKTEXT = "brktext";
    public static final String LUNTEXT = "luntext";
    public static final String SNKTEXT = "snktext";
    public static final String DNRTEXT = "dnrtext";
    public static final String EXTEXT = "extext";
    public static final int COUNTER = 0;


    LottieAnimationView lottieAnimationView;
    popup dialogfrag;
    ;
    popup2 popup2frag;
    popup3 popup3frag;
    popup4 popup4frag;
    popup5 popup5frag;
    ImageButton add, remove;
    KenBurnsView kenBurnsView;
    int counter, counter1, counter2, counter3, counter4;
    int total, total1, total2, total3, total4;
    TextView brktextkal, luntextkal, snacktextkal, dnrtextkal, extextkal;
    Custominterface callback;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference, databaseReference1;
    private double number = 0.00;
    private String brk, lun, snk, dnr, ex;

    public home() {
        // Required empty public constructor
    }

    private static int calculateInSamplesize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfheight = height / 2;
            final int halfwidth = width / 2;

            while ((halfheight / inSampleSize) >= reqHeight && (halfwidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromRersource(Resources res, int resId, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        options.inSampleSize = calculateInSamplesize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int getRandomInteger(int maximum, int minimum) {
        return ((int) (Math.random() * (maximum - minimum))) + minimum;
    }

    public void animate(View v) {
        if (lottieAnimationView.isAnimating()) {
            lottieAnimationView.cancelAnimation();

        } else {
            lottieAnimationView.playAnimation();

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        add = rootView.findViewById(R.id.addltr);
        remove = rootView.findViewById(R.id.removeltr);
        callback = this;
        kenBurnsView = rootView.findViewById(R.id.kenBurnsView);
        kenBurnsView.setImageBitmap(decodeSampledBitmapFromRersource(getResources(), R.drawable.back, 500, 500));
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


        final TextView ltr = rootView.findViewById(R.id.ltrtext);
        ltr.setText(number + " ltr");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = number + 0.25;

                ltr.setText(number + " ltr");

            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number > 0.00) {
                    number = number - 0.25;
                    ltr.setText(number + " ltr");
                } else {
                    Toast.makeText(getContext(), "Oops, Drink some water you beautiful and capable dehydrated moreon.", Toast.LENGTH_LONG).show();
                }

            }
        });


        LineChartView chartView = rootView.findViewById(R.id.chart);
        LineChartView chartView1 = rootView.findViewById(R.id.chart1);
        LineChartView chartView2 = rootView.findViewById(R.id.chart2);
        LineChartView chartView3 = rootView.findViewById(R.id.chart3);
        LineChartView chartView4 = rootView.findViewById(R.id.chart4);

        brktextkal = rootView.findViewById(R.id.textView16);
        luntextkal = rootView.findViewById(R.id.textView17);
        snacktextkal = rootView.findViewById(R.id.textView18);
        dnrtextkal = rootView.findViewById(R.id.textView19);
        extextkal = rootView.findViewById(R.id.textView20);

        lottieAnimationView = rootView.findViewById(R.id.lottieview1);


        lottieAnimationView.setAnimation(R.raw.water);
        lottieAnimationView.playAnimation();
        lottieAnimationView.loop(true);


        chartView.setInteractive(false);
        chartView1.setInteractive(false);
        chartView2.setInteractive(false);
        chartView3.setInteractive(false);
        chartView4.setInteractive(false);


        List<PointValue> values = new ArrayList<PointValue>();
        values.add(new PointValue(0, 2));
        values.add(new PointValue(1, 4));
        values.add(new PointValue(2, 3));
        values.add(new PointValue(3, 5));
        values.add(new PointValue(4, 4));

        List<PointValue> values1 = new ArrayList<PointValue>();
        values1.add(new PointValue(0, 2));
        values1.add(new PointValue(1, 3));
        values1.add(new PointValue(2, 1));
        values1.add(new PointValue(3, 5));
        values1.add(new PointValue(4, 3));

        //In most cased you can call data model methods in builder-pattern-like manner.
        Line line = new Line(values).setColor(Color.WHITE).setFilled(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);
        Line line1 = new Line(values1).setColor(Color.WHITE).setFilled(true);
        List<Line> lines1 = new ArrayList<Line>();
        lines1.add(line1);
        LineChartData data = new LineChartData();
        data.setLines(lines);
        LineChartData data1 = new LineChartData();
        data1.setLines(lines1);
        chartView.setLineChartData(data);
        chartView1.setLineChartData(data1);
        chartView2.setLineChartData(data1);
        chartView3.setLineChartData(data);
        chartView4.setLineChartData(data1);
        dialogfrag = popup.newInstance();
        popup2frag = popup2.newInstance();
        popup3frag = popup3.newInstance();
        popup4frag = popup4.newInstance();
        popup5frag = popup5.newInstance();
        final CircleMenuView menu = rootView.findViewById(R.id.circleMenuView);

        menu.setEventListener(new CircleMenuView.EventListener() {
            @Override
            public void onMenuOpenAnimationStart(@NonNull CircleMenuView view) {
                Log.d("D", "onMenuOpenAnimationStart");
            }

            @Override
            public void onMenuOpenAnimationEnd(@NonNull CircleMenuView view) {
                Log.d("D", "onMenuOpenAnimationEnd");
            }

            @Override
            public void onMenuCloseAnimationStart(@NonNull CircleMenuView view) {
                Log.d("D", "onMenuCloseAnimationStart");
            }

            @Override
            public void onMenuCloseAnimationEnd(@NonNull CircleMenuView view) {
                Log.d("D", "onMenuCloseAnimationEnd");
            }

            @Override
            public void onButtonClickAnimationStart(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonClickAnimationStart| index: " + index);

            }

            @Override
            public void onButtonClickAnimationEnd(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonClickAnimationEnd| index: " + index);
                switch (index) {
                    case 0:
                        showBottomSheet(getView()); //brekfst
                        break;
                    case 1:
                        showBottomSheet1(getView()); //lunch
                        break;
                    case 2:
                        showBottomSheet2(getView());//snack
                        break;
                    case 3:
                        showBottomSheet3(getView());//dinner
                        break;
                    case 4:
                        showBottomSheet4(getView());//exercise
                        break;
                }
            }
        });
        loadData();
        updateViews();

        return rootView;


    }

    public void showBottomSheet(View view) {
        popup addPhotoBottomDialogFragment = new popup(callback);
        addPhotoBottomDialogFragment.show(getChildFragmentManager(),
                popup.TAG);
    }

    public void showBottomSheet1(View view) {
        popup2 addPhotoBottomDialogFragment =
                new popup2(callback);
        addPhotoBottomDialogFragment.show(getChildFragmentManager(),
                popup2.TAG);
    }

    public void showBottomSheet2(View view) {
        popup3 addPhotoBottomDialogFragment =
                new popup3(callback);
        addPhotoBottomDialogFragment.show(getChildFragmentManager(),
                popup3.TAG);
    }

    public void showBottomSheet3(View view) {
        popup4 addPhotoBottomDialogFragment =
                new popup4(callback);
        addPhotoBottomDialogFragment.show(getChildFragmentManager(),
                popup4.TAG);
    }

    public void showBottomSheet4(View view) {
        popup5 addPhotoBottomDialogFragment =
                new popup5(callback);
        addPhotoBottomDialogFragment.show(getChildFragmentManager(),
                popup5.TAG);
    }

    @Override
    public void callbackmethod(int value) {
        int a = value;

        if (a == 1) {

            counter++;
            if(total<=1600) {
                total = counter * 100;
                brktextkal.setText(String.valueOf(total + " kl"));
                databaseReference.child("Users").child(mAuth.getCurrentUser().getDisplayName().toString()).child("data").child("Break").setValue(total);
                if(total == 1600){
                    counter = 0;
                    Toast.makeText(getContext(), "You are Over eating", Toast.LENGTH_SHORT).show();
                }
            }
        }
        if (a == 2) {
            counter1++;
            if(total1 <= 1600) {
                total1 = counter1 * 100;
                luntextkal.setText(String.valueOf(total1 + " kl"));
                databaseReference.child("Users").child(mAuth.getCurrentUser().getDisplayName().toString()).child("data").child("Lunch").setValue(total1);
                if(total1 == 1600){
                    counter1 = 0;
                    Toast.makeText(getContext(), "You are Over eating", Toast.LENGTH_SHORT).show();
                }
            }
        }
        if (a == 3) {
            counter2++;
            if(total2 <= 600) {
                total2 = counter2 * 100;
                snacktextkal.setText(String.valueOf(total2 + " kl"));
                databaseReference.child("Users").child(mAuth.getCurrentUser().getDisplayName().toString()).child("data").child("Snack").setValue(total2);
            if(total2 == 600){
                counter2 = 0;
                Toast.makeText(getContext(), "You are Over eating", Toast.LENGTH_SHORT).show();
            }
            }


        }
        if (a == 4) {
            counter3++;
            if(total3 <= 1600) {
                total3 = counter3 * 100;
                dnrtextkal.setText(String.valueOf(total3 + " kl"));
                databaseReference.child("Users").child(mAuth.getCurrentUser().getDisplayName().toString()).child("data").child("Dinner").setValue(total3);
                if(total3 == 1600){
                    counter3 = 0;
                    Toast.makeText(getContext(), "Stop eating to much bitch , you are gonna explode", Toast.LENGTH_SHORT).show();
                }
            }


        }
        if (a == 5) {
            counter4++;

            total4 = counter4 * 100;
            extextkal.setText(String.valueOf(total4 + " kl burn"));
            databaseReference.child("Users").child(mAuth.getCurrentUser().getDisplayName().toString()).child("data").child("Exercise").setValue(total4);

        }
        saveData();
        loadData();
        updateViews();

        System.out.println(a);
    }

    public void saveData() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(BRKTEXT, brktextkal.getText().toString());
        editor.putString(LUNTEXT, luntextkal.getText().toString());
        editor.putString(SNKTEXT, snacktextkal.getText().toString());
        editor.putString(DNRTEXT, dnrtextkal.getText().toString());
        editor.putString(EXTEXT, extextkal.getText().toString());
        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        brk = sharedPreferences.getString(BRKTEXT, "0 kl");
        lun = sharedPreferences.getString(LUNTEXT, "0 kl");
        snk = sharedPreferences.getString(SNKTEXT, "0 kl");
        dnr = sharedPreferences.getString(DNRTEXT, "0 kl");
        ex = sharedPreferences.getString(EXTEXT, "0 kl burn");

    }

    public void updateViews() {
        brktextkal.setText(brk);
        luntextkal.setText(lun);
        snacktextkal.setText(snk);
        dnrtextkal.setText(dnr);
        extextkal.setText(ex);
    }

}

