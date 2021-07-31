package com.example.mydoctorapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydoctorapp.Patient.SpecificDoctor;

import java.util.ArrayList;
import java.util.List;

public class GetDoctorDetailForPatientDashboardAdapter extends RecyclerView.Adapter<GetDoctorDetailForPatientDashboardAdapter.MyClass> {

    Context context;
    List<GetterSetterDoctor> doctors;
    ArrayList<GetterSetterDoctor> al;
    GetterSetterDoctor gs;
    String pat_id;


    public GetDoctorDetailForPatientDashboardAdapter(Context context, List<GetterSetterDoctor> doctors, String pat_id) {
        this.context = context;
        this.doctors = doctors;
        this.pat_id = pat_id;
    }

    @NonNull
    @Override
    public MyClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.getdoctor, viewGroup, false);
        return new MyClass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyClass myClass, final int i) {
        myClass.tvfname.setText("Dr. " + doctors.get(i).doc_fname);
        myClass.tvlname.setText(doctors.get(i).doc_lname);
        myClass.tvspeciality.setText(doctors.get(i).doc_speciality);
        myClass.tvdegree.setText(doctors.get(i).doc_degree);

        myClass.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String doc_id = doctors.get(i).doc_id;
                String doc_fname = doctors.get(i).doc_fname;
                String doc_lname = doctors.get(i).doc_lname;
                String fullname = (doc_fname + " " + doc_lname);
                Toast.makeText(context, "" + fullname, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, SpecificDoctor.class);
                i.putExtra("doc_id", doc_id);
                i.putExtra("pat_id", pat_id);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    public class MyClass extends RecyclerView.ViewHolder {

        TextView tvfname, tvlname, tvphone, tvspeciality, tvdegree;

        public MyClass(@NonNull View itemView) {
            super(itemView);

            tvfname = itemView.findViewById(R.id.tvfname);
            tvlname = itemView.findViewById(R.id.tvlname);
            tvspeciality = itemView.findViewById(R.id.tvspeciality);
            tvdegree = itemView.findViewById(R.id.tvdegree);
        }
    }
}
