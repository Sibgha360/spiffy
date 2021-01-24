package nl.psdcompany.duonavigationdrawer.example;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;


public class NewsFragment extends Fragment {
    ListView list;

    String[] maintitle ={
            "SVD - KSV: Die Top Facts zum Spiel","Lilienkurier zum Kiel-Spiel",
            "Es braucht Erfolgserlebnisse","Der 98er - Gude Helmut Markwort",
            "FCH - SVD: Die Top Facts zum Spiel", "Von Momentum und dem richtigen Zeitpunkt",
    };

    String[] subtitle ={
            "500 pts","300 pts",
            "250 pts","10 pts",
            "10 pts",
    };

    Integer[] imgid={
            R.mipmap.news1,R.mipmap.news2,
            R.mipmap.news3,R.mipmap.news4,
            R.mipmap.news5, R.mipmap.news6,
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmentReporting
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        MobileAds.initialize(getActivity(), "ca-app-pub-3940256099942544~3347511713");
        AdView adView = (AdView)view.findViewById(R.id.adView);
        AdRequest request = new AdRequest.Builder().build();
        adView.loadAd(request);

        NewsAdapter adapter=new NewsAdapter(getActivity(), maintitle, subtitle,imgid);
        list=(ListView)view.findViewById(R.id.news);
        list.setAdapter(adapter);

        return view;
    }
}
