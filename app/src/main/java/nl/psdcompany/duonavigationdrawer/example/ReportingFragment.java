package nl.psdcompany.duonavigationdrawer.example;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.plus.PlusOneButton;

import java.io.InputStream;

public class ReportingFragment extends Fragment {

    public ImageView viewById;
    View gif;

    public ReportingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragmentReporting
        View view = inflater.inflate(R.layout.fragment_reporting, container, false);

        gif = view.findViewById(R.id.gif);
        View up = view.findViewById(R.id.up);

        this.viewById = view.findViewById(R.id.IdProf);
        Button btnById = (Button) view.findViewById(R.id.g);
        btnById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent,111);}
        });

        Button photoButton = (Button) view.findViewById(R.id.camera);
        photoButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (getActivity().checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{android.Manifest.permission.CAMERA}, 100);
                }
                else
                {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    getActivity().startActivityForResult(cameraIntent, 1888);
                }
            }
        });

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float density = getActivity().getResources().getDisplayMetrics().density;
                viewById.setPadding((int)(density*70),(int)density*70,(int)density*70,(int)density*70);
                ReportingFragment.this.viewById.setImageResource(R.mipmap.up);

                new CountDownTimer(1000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        gif.setVisibility(View.VISIBLE);
                    }

                    public void onFinish() {
                        gif.setVisibility(View.INVISIBLE);
                        Toast.makeText(getActivity(), "Reported successfully. You can upload more now.", Toast.LENGTH_LONG).show();
                    }
                }.start();
            }
        });

        MobileAds.initialize(getActivity(), "ca-app-pub-3940256099942544~3347511713");
        AdView adView = (AdView)view.findViewById(R.id.adView);
        AdRequest request = new AdRequest.Builder().build();
        adView.loadAd(request);

        return view;
    }
}
