package com.example.myapplication;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    Button btn;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_first, container, false);

        btn = v.findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), SecondActivity.class);
                startActivity(i);
            }
        });


        Log.d("mydata", "Fragment --> onCreateView()");
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("mydata", "Fragment --> onAttach()");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("mydata", "Fragment --> onActivityCreated()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("mydata", "Fragment --> onDestroyView()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("mydata", "Fragment --> onDetach()");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("mydata", "Fragment --> onCreate()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("mydata", "Fragment --> onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("mydata", "Fragment --> onResume()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("mydata", "Fragment --> onStop()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("mydata", "Fragment --> onPause()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("mydata", "Fragment --> onDestroy()");
    }
}
