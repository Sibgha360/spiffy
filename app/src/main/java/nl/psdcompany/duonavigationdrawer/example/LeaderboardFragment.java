package nl.psdcompany.duonavigationdrawer.example;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;


public class LeaderboardFragment extends Fragment {
    ListView list;

    String[] maintitle ={
            "Alina K.","Max M.",
            "John T.","Sonia S.",
            "Olive L.",
    };

    String[] subtitle ={
            "500 pts","300 pts",
            "250 pts","10 pts",
            "10 pts",
    };

    Integer[] imgid={
            R.mipmap.download,R.mipmap.download1,
            R.mipmap.download2,R.mipmap.download3,
            R.mipmap.download4,
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lb, container, false);

        ImageView viewById = view.findViewById(R.id.imageView);

        Picasso
                .get()
                .load("https://i.pinimg.com/originals/51/f6/fb/51f6fb256629fc755b8870c801092942.png")
                .into(viewById);

        MyListAdapter adapter=new MyListAdapter(getActivity(), maintitle, subtitle,imgid);
        list=(ListView)view.findViewById(R.id.main_list);
        list.setAdapter(adapter);

        return view;
    }
}
