package com.example.mydoctorapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydoctorapp.Doctor.Dashboarddoctor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetPendingAppointmentAdapter extends RecyclerView.Adapter<GetPendingAppointmentAdapter.MyClass> {

    Context context;
    List<GetterSetterPendingAppointment> pendingAppointments;
    String doc_id = "0";
    String fullname;
    ApiInterface apiInterface;

    public GetPendingAppointmentAdapter(Context context, List<GetterSetterPendingAppointment> pendingAppointments, String doc_id, String fullname) {
        this.context = context;
        this.pendingAppointments = pendingAppointments;
        this.doc_id = doc_id;
        this.fullname = fullname;
    }

    @NonNull
    @Override
    public MyClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pendingappointmentfile, viewGroup, false);
        return new MyClass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyClass myClass, int i) {
        final String app_id;
        myClass.fname.setText(pendingAppointments.get(i).getPat_fname());
        myClass.lname.setText(pendingAppointments.get(i).getPat_lname());
        myClass.time.setText(pendingAppointments.get(i).getTime());
        myClass.date.setText(pendingAppointments.get(i).getDate());
        app_id = pendingAppointments.get(i).getApp_id();

        myClass.btnapprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Alert")
                        .setMessage("Are you sure want to Approve this appointment?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                apiInterface = ApiClient.getAliClient().create(ApiInterface.class);
                                Call<GetterSetterUpdate> call = apiInterface.UpdateAppointment(app_id, "Approved");

                                call.enqueue(new Callback<GetterSetterUpdate>() {
                                    @Override
                                    public void onResponse(Call<GetterSetterUpdate> call, Response<GetterSetterUpdate> response) {
                                        Intent i = new Intent(context, Dashboarddoctor.class);
                                        i.putExtra("doc_id", doc_id);
                                        i.putExtra("doc_fullname", fullname);
                                        context.startActivity(i);
                                        Toast.makeText(context, "Appointment Approved Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(context, Dashboarddoctor.class);
                                        context.startActivity(intent);
                                        ((Activity) context).finish();
                                    }

                                    @Override
                                    public void onFailure(Call<GetterSetterUpdate> call, Throwable t) {
                                        Log.d("error", t.getMessage());
                                    }
                                });
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setCancelable(false)
                        .show();
            }
        });

        myClass.btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("app_id", app_id);
                new AlertDialog.Builder(context)
                        .setTitle("Alert")
                        .setMessage("Are you sure want to Remove this appointment?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                apiInterface = ApiClient.getAliClient().create(ApiInterface.class);
                                Call<GetterSetterUpdate> call = apiInterface.UpdateAppointment(app_id, "Cancelled");

                                call.enqueue(new Callback<GetterSetterUpdate>() {
                                    @Override
                                    public void onResponse(Call<GetterSetterUpdate> call, Response<GetterSetterUpdate> response) {
                                        Intent i = new Intent(context, Dashboarddoctor.class);
                                        i.putExtra("doc_id", doc_id);
                                        i.putExtra("doc_fullname", fullname);
                                        context.startActivity(i);
                                        Toast.makeText(context, "Appointment Removed Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(context, Dashboarddoctor.class);
                                        context.startActivity(intent);
                                        ((Activity) context).finish();
                                    }

                                    @Override
                                    public void onFailure(Call<GetterSetterUpdate> call, Throwable t) {
                                        Log.d("error", t.getMessage());
                                    }
                                });
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setCancelable(false)
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return pendingAppointments.size();
    }

    public class MyClass extends RecyclerView.ViewHolder {

        TextView fname, lname, time, date;
        Button btnapprove, btncancel;

        public MyClass(@NonNull View itemView) {
            super(itemView);

            fname = itemView.findViewById(R.id.pentvfname);
            lname = itemView.findViewById(R.id.pentvlname);
            date = itemView.findViewById(R.id.pentvtime);
            time = itemView.findViewById(R.id.pentvdate);
            btnapprove = itemView.findViewById(R.id.penbtnapprove);
            btncancel = itemView.findViewById(R.id.penbtncancel);
        }
    }
}
