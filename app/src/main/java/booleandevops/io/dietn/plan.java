package booleandevops.io.dietn;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class plan extends Fragment {

    LinearLayout l1, l2, l3, g1, g2, g3, d1, d2, d3;

    public plan() {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_plan, container, false);
        l1 = rootView.findViewById(R.id.loss_1);
        l2 = rootView.findViewById(R.id.loss_2);
        l3 = rootView.findViewById(R.id.loss_3);
        g1 = rootView.findViewById(R.id.gain_1);
        g2 = rootView.findViewById(R.id.gain_2);
        g3 = rootView.findViewById(R.id.gain_3);
        d1 = rootView.findViewById(R.id.dia_1);
        d2 = rootView.findViewById(R.id.dia_2);
        d3 = rootView.findViewById(R.id.dia_3);

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheet1(getView());
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheet1(getView());
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheet1(getView());
            }
        });
        g1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheet1(getView());
            }
        });
        g2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheet1(getView());
            }
        });
        g3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheet1(getView());
            }
        });
        d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Diet in making", Toast.LENGTH_SHORT).show();
            }
        });
        d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Diet in making", Toast.LENGTH_SHORT).show();
            }
        });
        d3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Diet in making", Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }

    public void showBottomSheet1(View view) {
        MyplanFabFragment addPhotoBottomDialogFragment1 =
                MyplanFabFragment.newInstance();
        addPhotoBottomDialogFragment1.show(getChildFragmentManager(),
                MyplanFabFragment.TAG);
    }
}