package booleandevops.io.dietn;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class dashboard extends AppCompatActivity {
    private static final long SPLASH_TIME_OUT = 10000;
    FloatingActionButton fab, Signout;
    MySampleFabFragment dialogFrag;
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth.AuthStateListener mAuthlistener;
    home h1;
    String weight;

    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        h1 = new home();
        String w;
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        /* Fullscreen mode */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);



        final AlertDialog.Builder alert = new AlertDialog.Builder(dashboard.this);
        View mview = getLayoutInflater().inflate(R.layout.bodytypedialog_forgoogle, null);
        final Spinner bodytp = (Spinner) mview.findViewById(R.id.bodytp);
        final EditText weight = (EditText) mview.findViewById(R.id.editText4);
        Button accept, cancel;
        accept = (Button) mview.findViewById(R.id.accept);
        cancel = (Button) mview.findViewById(R.id.cancel);
        alert.setView(mview);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        ArrayAdapter<String> spinnerCountShoesArrayAdapter = new ArrayAdapter<String>(
                dashboard.this,
                R.layout.spinner_item,
                getResources().getStringArray(R.array.bodytype));
        bodytp.setAdapter(spinnerCountShoesArrayAdapter);
            alertDialog.setCancelable(false);
            alertDialog.show();

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUser user = mAuth.getCurrentUser();
                String body = bodytp.getSelectedItem().toString();
                databaseReference.child("Users").child(user.getDisplayName()).child("body").setValue(body);
                databaseReference.child("Users").child(user.getDisplayName()).child("weight").setValue(weight.getText().toString());


                alertDialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });


        fab = (FloatingActionButton) findViewById(R.id.fabaaaa);
        Signout = (FloatingActionButton) findViewById(R.id.signout);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheet(v);
            }
        });
        Signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();

                Intent newintent = new Intent(getApplicationContext(), Login.class);
                startActivity(newintent);
                finish();
            }
        });

        final BubbleNavigationLinearView bubbleNavigationLinearView = (BubbleNavigationLinearView) findViewById(R.id.top_navigation_constraint);
        final FrameLayout viewPager = (FrameLayout) findViewById(R.id.VIEWPGR);
        FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction3.replace(R.id.VIEWPGR, h1);
        fragmentTransaction3.commit();

        final myprof myprof1 = new myprof();
        final plan plan1 = new plan();
        bubbleNavigationLinearView.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                bubbleNavigationLinearView.setCurrentActiveItem(position);
                switch (position) {
                    case 0:
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_form_right, R.anim.enter_from_right, R.anim.exit_to_left);
                        fragmentTransaction.replace(R.id.VIEWPGR, h1);
                        fragmentTransaction.commit();
                        break;
                    case 1:
                        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction1.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_right, R.anim.exit_to_left);
                        fragmentTransaction1.replace(R.id.VIEWPGR, myprof1);
                        fragmentTransaction1.commit();
                        break;
                    case 2:
                        FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction2.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_right, R.anim.exit_to_left);
                        fragmentTransaction2.replace(R.id.VIEWPGR, plan1);
                        fragmentTransaction2.commit();
                        break;
                    default:

                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }

            }
        });
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String brk = dataSnapshot.child("Users").child(mAuth.getCurrentUser().getDisplayName().toString()).child("data").child("Break").getValue().toString();
                        String lun = dataSnapshot.child("Users").child(mAuth.getCurrentUser().getDisplayName().toString()).child("data").child("Lunch").getValue().toString();
                        String snk = dataSnapshot.child("Users").child(mAuth.getCurrentUser().getDisplayName().toString()).child("data").child("Snack").getValue().toString();
                        String dnr = dataSnapshot.child("Users").child(mAuth.getCurrentUser().getDisplayName().toString()).child("data").child("Dinner").getValue().toString();
                        String ex = dataSnapshot.child("Users").child(mAuth.getCurrentUser().getDisplayName().toString()).child("data").child("Exercise").getValue().toString();
                        String weight = dataSnapshot.child("Users").child(mAuth.getCurrentUser().getDisplayName().toString()).child("weight").getValue().toString();

                        float wei = Float.parseFloat(weight);
                        float brkvalue = Float.parseFloat(brk);
                        float lunvalue = Float.parseFloat(lun);
                        float snkvalue = Float.parseFloat(snk);
                        float dnrvalue = Float.parseFloat(dnr);
                        float exvalue = Float.parseFloat(ex);
                        float protein = wei / 0.8f;
                        float prokl = (brkvalue + lunvalue + snkvalue + dnrvalue) / protein;
                        //protein progress
                        CircularProgressBar circularProgressBar = findViewById(R.id.circularProgressBar1);
                        circularProgressBar.setProgressMax(protein);
                        circularProgressBar.setProgress(prokl);
                        //kl left progress
                        CircularProgressBar circularProgressBar1 = findViewById(R.id.circularProgressBar);
                        circularProgressBar1.setProgressMax(5400.0f);
                        circularProgressBar1.setProgress(brkvalue + lunvalue + snkvalue + dnrvalue);
                        //kl burn progress
                        CircularProgressBar circularProgressBar2 = findViewById(R.id.circularProgressBar2);
                        circularProgressBar2.setProgressMax(5400.0f);
                        circularProgressBar2.setProgress(exvalue);
                        System.out.println(brkvalue);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        }, SPLASH_TIME_OUT);

    }


    public void showBottomSheet(View view) {
        MySampleFabFragment addPhotoBottomDialogFragment =
                MySampleFabFragment.newInstance();
        addPhotoBottomDialogFragment.show(getSupportFragmentManager(),
                MySampleFabFragment.TAG);
    }

    public void onResult(Object result) {
        Log.d("k9res", "onResult: " + result.toString());
        if (result.toString().equalsIgnoreCase("swiped_down")) {
            //do something or nothing
        } else {
            //handle result
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (dialogFrag.isAdded()) {
            dialogFrag.dismiss();
            dialogFrag.show(getSupportFragmentManager(), dialogFrag.getTag());
        }

    }


}
