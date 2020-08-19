package booleandevops.io.dietn;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class myprof extends Fragment {
    FloatingActionButton BMI, SCHEDULE, SETTINGS;
    MyBMIFabFragment dialogFrag;
    MyScheduleFabFragment myScheduleFabFragment;
    FirebaseAuth mAuth;
    public myprof() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView1 = inflater.inflate(R.layout.fragment_myprof, container, false);
        mAuth =  FirebaseAuth.getInstance();

        TextView username = rootView1.findViewById(R.id.username);
        TextView membersincedate = rootView1.findViewById(R.id.membersince);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            username.setText(personName);
        }


        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        String year1 = String.valueOf(year);
        membersincedate.setText("Member Since " + year1);
        BMI = rootView1.findViewById(R.id.bmi);
       SCHEDULE = rootView1.findViewById(R.id.schedule);
       SETTINGS = rootView1.findViewById(R.id.settings);

       BMI.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              showBottomSheet2(getView());
           }
       });

       SCHEDULE.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              showBottomSheet1(getView());
           }
       });
       SETTINGS.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              showbottomsheet3(getView());
           }
       });


        return rootView1;
    }
    public void showBottomSheet1(View view) {
        MyScheduleFabFragment addPhotoBottomDialogFragment1 =
                MyScheduleFabFragment.newInstance();
        addPhotoBottomDialogFragment1.show(getChildFragmentManager(),
                MyScheduleFabFragment.TAG);
    }
    public void showBottomSheet2(View view) {
        MyBMIFabFragment addPhotoBottomDialogFragment2 =
                MyBMIFabFragment.newInstance();
        addPhotoBottomDialogFragment2.show(getChildFragmentManager(),
                MyBMIFabFragment.TAG);
    }
    public  void showbottomsheet3(View view){
        settingpop addsettingpop = settingpop.newInstance();
        addsettingpop.show(getChildFragmentManager(),settingpop.TAG);
    }
}
