package booleandevops.io.dietn;

import android.app.DatePickerDialog;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Signup extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private static final String TAG = "activity_signup";
    EditText email, pass, name;
    private TextView mdisplayDOB, mdisplaybodytype;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase =  FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        ConstraintLayout imageView = (ConstraintLayout) findViewById(R.id.contraintsign);

        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();

        animationDrawable.setEnterFadeDuration(100);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

        email = (EditText) findViewById(R.id.editText);
        pass = (EditText) findViewById(R.id.editText2);
        name = (EditText) findViewById(R.id.editText3);
        spinner = (Spinner) findViewById(R.id.bodytype);
        ArrayAdapter<String> spinnerCountShoesArrayAdapter = new  ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.bodytype));
        spinner.setAdapter(spinnerCountShoesArrayAdapter);
        mdisplayDOB = (TextView) findViewById(R.id.dob);
        mdisplayDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDatepickerdialog();
            }
        });
        Button signup = (Button) findViewById(R.id.singupbtn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emid = email.getText().toString();
                String password = pass.getText().toString();
                final String crpass = name.getText().toString();
                if (emid != null) {

                    mAuth.createUserWithEmailAndPassword(emid, password)
                            .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "createUserWithEmail:success");
                                        Toast.makeText(Signup.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        String name = crpass;
                                        String email = emid;
                                        String bodytype = spinner.getSelectedItem().toString();
                                        boolean isNew = task.getResult().getAdditionalUserInfo().isNewUser();

                                        userinformation userinformation1 = new userinformation(email,bodytype);

                                        if(isNew == true) {
                                            databaseReference.child("Users").child(name).setValue(userinformation1);
                                            databaseReference.child("Users").child(name).child("data").child("Break").setValue(0);
                                            databaseReference.child("Users").child(name).child("data").child("Lunch").setValue(0);
                                            databaseReference.child("Users").child(name).child("data").child("Snack").setValue(0);
                                            databaseReference.child("Users").child(name).child("data").child("Dinner").setValue(0);
                                            databaseReference.child("Users").child(name).child("data").child("Exercise").setValue(0);
                                            databaseReference.child("Users").child(name).child("weight").setValue(0);
                                        }

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(Signup.this, "Signup failed", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }

            }
        });


    }

    private void showDatepickerdialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + "/" + month + "/" + year;
        mdisplayDOB.setText(date);
    }
}
