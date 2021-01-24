package nl.psdcompany.duonavigationdrawer.example;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements OnMapReadyCallback {

    MapView mapView;
    GoogleMap map;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
        } else {
            String[] permissions = {
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION};
            ActivityCompat.requestPermissions(getActivity(), permissions, 2);
        }


//        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(), "Clicked a button!", Toast.LENGTH_SHORT).show();
//            }
//        });
//        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
//        pager.setAdapter(new PagerAdapter());
        // Gets the MapView from the XML layout and creates it
        mapView =  view.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);


        locationMangaer = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);


        MobileAds.initialize(getActivity(), "ca-app-pub-3940256099942544~3347511713");
        AdView adView = (AdView)view.findViewById(R.id.adView);
        AdRequest request = new AdRequest.Builder().build();
        adView.loadAd(request);

        return view;
    }

    LocationManager locationMangaer;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            map = googleMap;
            map.getUiSettings().setMyLocationButtonEnabled(false);
            map.setMyLocationEnabled(true);
       /*
       //in old Api Needs to call MapsInitializer before doing any CameraUpdateFactory call
        try {
            MapsInitializer.initialize(this.getActivity());
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
       */
        } catch(Throwable t) {
            t.printStackTrace();
        }

        // Updates the location and zoom of the MapView
//        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(43.1, -87.9), 10);
//        map.animateCamera(cameraUpdate);
//        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(43.1, -87.9)));


        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getActivity(), "First enable LOCATION ACCESS in settings.", Toast.LENGTH_LONG).show();
            return  ;
        }

        Location currentLocation = locationMangaer.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if(currentLocation!=null)
        {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();

            LatLng coordinates =  new LatLng(49.8728203d,8.6515366d);
            MarkerOptions marker_in_sydney = new MarkerOptions().position(coordinates).title("Free Wifi 6");
            marker = map.addMarker(marker_in_sydney);
            LatLng l1 = new LatLng(49.8823088d,8.57791d);
            marker1 = map.addMarker(new MarkerOptions().position(l1).title("Free Wifi 1"));
            LatLng l2 = new LatLng(49.8839127,8.6033159);
            marker2 = map.addMarker(new MarkerOptions().position(l2).title("Free Wifi 2"));
            LatLng l3 = new LatLng(49.8728203,8.6345744);
            marker3 = map.addMarker(new MarkerOptions().position(l3).title("Free Wifi 3"));
            LatLng l4 = new LatLng(49.858231,8.6603933);
            marker4 = map.addMarker(new MarkerOptions().position(l4).title("Free Wifi 4"));
            LatLng l5 = new LatLng(49.856231,8.6683933);
            marker5 = map.addMarker(new MarkerOptions().position(l5).title("Free Wifi 5"));

            builder.include(l1);
            builder.include(l2);
            builder.include(l3);
            builder.include(l4);
            builder.include(l5);

            marker.setPosition(coordinates);
            LatLngBounds build = builder.build();


            int width = getResources().getDisplayMetrics().widthPixels;
            int height = getResources().getDisplayMetrics().heightPixels;
            int padding = (int) (width * 0.10); // offset from edges of the map 10% of screen


            map.animateCamera(CameraUpdateFactory.newLatLngBounds(build,width,height,padding));
        }
    }
    Marker marker;
    Marker marker1;
    Marker marker2;
    Marker marker3;
    Marker marker4;
    Marker marker5;

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    private class PagerAdapter extends androidx.viewpager.widget.PagerAdapter {

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            // Create some layout params
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

            // Create some text
            TextView textView = getTextView(container.getContext());
            textView.setText(String.valueOf(position));
            textView.setLayoutParams(layoutParams);

            RelativeLayout layout = new RelativeLayout(container.getContext());
            layout.setBackgroundColor(ContextCompat.getColor(container.getContext(), R.color.colorPrimary));
            layout.setLayoutParams(layoutParams);

            layout.addView(textView);
            container.addView(layout);
            return layout;
        }

        private TextView getTextView(Context context) {
            TextView textView = new TextView(context);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(30);
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            return textView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((RelativeLayout) object);
        }
    }
}
