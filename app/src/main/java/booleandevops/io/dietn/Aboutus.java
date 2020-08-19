package booleandevops.io.dietn;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.fonts.Font;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class Aboutus extends AppCompatActivity {

    LinearLayout container;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.aboutus);
        container = findViewById(R.id.container);
//        toolbar =  findViewById(R.id.toolbar11);
//        setSupportActionBar(toolbar);
        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.logo)
                .setDescription(getResources().getString(R.string.large_text))
                .addGroup(getResources().getString(R.string.extraL_text))
                .addGroup(getResources().getString(R.string.norm_text))
                .addItem(new Element("Version :- 1.0.0 prealpha",R.drawable.ic_perm_device_information_black_24dp).setIconTint(R.color.colorPrimary))
                .addItem(new Element("Our Team",R.drawable.ic_baseline_developer_mode_24).setIconTint(R.color.colorPrimary).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Aboutus.this, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
                        builder.setTitle("Our Team");
                        builder.setMessage(" 1.\tPiyush Chopra\n 2.\tTejvir Singh\n 3.\tSaksham Kaushik\n 4.\tKarandeep Singh")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // FIRE ZE MISSILES!
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // User cancelled the dialog
                                    }
                                });
                        final AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }))
                .addItem(new Element( "Our Website",R.drawable.ic_baseline_web_24).setIconTint(R.color.colorPrimary).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = "https://programmingworld98.wixsite.com/dietnpreviw";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                }))

                .create();

        container.addView(aboutPage, 0);
    }


}