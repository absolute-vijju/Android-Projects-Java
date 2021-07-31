package com.example.mydoctorapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class GetApprovedAppointmentAdapter extends RecyclerView.Adapter<GetApprovedAppointmentAdapter.MyClass> {

    Context context;
    List<GetterSetterApprovedAppointment> approvedAppointments;

    public GetApprovedAppointmentAdapter(Context context, List<GetterSetterApprovedAppointment> approvedAppointments) {
        this.context = context;
        this.approvedAppointments = approvedAppointments;
    }

    @NonNull
    @Override
    public MyClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.approvedappointment, viewGroup, false);
        return new MyClass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyClass myClass, int i) {
        myClass.fname.setText(approvedAppointments.get(i).getDoc_fname());
        myClass.lname.setText(approvedAppointments.get(i).getDoc_lname());
        myClass.tvtime.setText(approvedAppointments.get(i).getTime());
        myClass.tvdate.setText(approvedAppointments.get(i).getDate());
    }

    @Override
    public int getItemCount() {
        return approvedAppointments.size();
    }

    public class MyClass extends RecyclerView.ViewHolder {
        TextView tvdate, tvtime, fname, lname;

        public MyClass(@NonNull View itemView) {
            super(itemView);

            tvdate = itemView.findViewById(R.id.date);
            tvtime = itemView.findViewById(R.id.time);
            fname = itemView.findViewById(R.id.namefname);
            lname = itemView.findViewById(R.id.namelname);
        }
    }
}
