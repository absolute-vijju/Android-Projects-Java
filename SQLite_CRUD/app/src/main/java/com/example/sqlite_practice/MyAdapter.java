package com.example.sqlite_practice;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyClass> {

    Context context;
    ArrayList<GetterSetter> al;
    String fname, lname;

    public MyAdapter(Context context, ArrayList<GetterSetter> al) {
        this.context = context;
        this.al = al;
    }

    @NonNull
    @Override
    public MyClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.rowfile, viewGroup, false);
        return new MyClass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyClass myClass, final int i) {
        final GetterSetter gs = al.get(i);
        myClass.tvid.setText("Id: " + gs.getId());
        myClass.tvfname.setText("Firstname: " + gs.getFname());
        myClass.tvlname.setText("Lastname: " + gs.getLname());


        myClass.btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog d = new Dialog(context);
                d.setContentView(R.layout.dialogfile);
                d.show();

                Button dbtnupdate = d.findViewById(R.id.dupdate);
                final EditText etfname = d.findViewById(R.id.dfname);
                final EditText etlname = d.findViewById(R.id.dlname);

                final String id = gs.getId();
                fname = gs.getFname();
                lname = gs.getLname();

                etfname.setText(fname);
                etlname.setText(lname);

                dbtnupdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyDatabase myDatabase = new MyDatabase(context);
                        myDatabase.updateData(id, etfname.getText().toString(), etlname.getText().toString());
                        d.dismiss();
                        Intent i = new Intent(context, MainActivity.class);
                        context.startActivity(i);
                        ((Activity) context).finish();
                    }
                });
            }
        });

        myClass.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = gs.getId();
                MyDatabase db = new MyDatabase(context);
                db.deleteData(id);
                Intent i = new Intent(context, MainActivity.class);
                context.startActivity(i);
                ((Activity) context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class MyClass extends RecyclerView.ViewHolder {

        TextView tvid, tvfname, tvlname;
        Button btnupdate, btndelete;

        public MyClass(@NonNull View itemView) {
            super(itemView);

            tvid = itemView.findViewById(R.id.tvid);
            tvfname = itemView.findViewById(R.id.tvfname);
            tvlname = itemView.findViewById(R.id.tvlname);
            btnupdate = itemView.findViewById(R.id.btnupdate);
            btndelete = itemView.findViewById(R.id.btndelete);
        }
    }
}
