package com.example.fragment_recyclerview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends android.app.Fragment {

    RecyclerView rev;

    int img[] = {R.mipmap.ind, R.mipmap.aus, R.mipmap.bd, R.mipmap.can, R.mipmap.ke, R.mipmap.ar, R.mipmap.cn, R.mipmap.sa, R.mipmap.nz};
    String name[] = {"India", "Australia", "Bangladesh", "Canada", "Kenya", "Argentina", "China", "South Africa", "New Zealand"};

    ArrayList<GetterSetter> al = new ArrayList<>();

    public MyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my, container, false);

        rev = v.findViewById(R.id.rev);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rev.setLayoutManager(layoutManager);


        for (int i = 0; i < img.length; i++) {
            GetterSetter gs = new GetterSetter(img[i], name[i]);
            al.add(gs);
        }

        MyAdapter myAdapter = new MyAdapter(getActivity(), al);
        rev.setAdapter(myAdapter);

        return v;
    }

}
