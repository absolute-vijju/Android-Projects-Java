package com.example.mydoctorapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydoctorapp.Patient.Dashboardpatient;

import java.security.acl.LastOwnerException;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetNestedDoctorDetailForPatientDashboardAdapter extends RecyclerView.Adapter<GetNestedDoctorDetailForPatientDashboardAdapter.MyClass> {

    List<GetterSetterNestedDoctor> nestedDoctorList;
    Context context;
    Calendar calendar = Calendar.getInstance();
    String date = "Null", doc_id, pat_id;
    String montime, tuetime, wedtime, thutime, fritime, sattime;
    ApiInterface apiInterface;
    String review;
    float ratingFromUser;

    public GetNestedDoctorDetailForPatientDashboardAdapter(Context context, List<GetterSetterNestedDoctor> nestedDoctorList, String doc_id, String pat_id) {
        this.context = context;
        this.nestedDoctorList = nestedDoctorList;
        this.doc_id = doc_id;
        this.pat_id = pat_id;
    }

    @NonNull
    @Override
    public MyClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.getnesteddoctor, viewGroup, false);
        return new MyClass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyClass myClass, int i) {
        myClass.tvfname.setText("Dr. " + nestedDoctorList.get(i).getFname());
        myClass.tvlname.setText(nestedDoctorList.get(i).getLname());
        myClass.tvspeciality.setText(nestedDoctorList.get(i).getSpeciality());
        myClass.tvdegree.setText(nestedDoctorList.get(i).getDegree());
        myClass.tvcliaddress.setText(nestedDoctorList.get(i).getCliaddress());
        myClass.rb.setRating((float) 3.5);
        myClass.btnmon.setText(nestedDoctorList.get(i).getMon());
        myClass.btntue.setText(nestedDoctorList.get(i).getTue());
        myClass.btnwed.setText(nestedDoctorList.get(i).getWed());
        myClass.btnthu.setText(nestedDoctorList.get(i).getThu());
        myClass.btnfri.setText(nestedDoctorList.get(i).getFri());
        myClass.btnsat.setText(nestedDoctorList.get(i).getSat());
        myClass.btnreview.setText("Click here to Submit your Rating & Review");

        montime = nestedDoctorList.get(i).getMon();
        tuetime = nestedDoctorList.get(i).getTue();
        wedtime = nestedDoctorList.get(i).getWed();
        thutime = nestedDoctorList.get(i).getThu();
        fritime = nestedDoctorList.get(i).getFri();
        sattime = nestedDoctorList.get(i).getSat();

        myClass.btnmon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Button btnappdate, btnbook, btncancel;
                TextView tvwarning;


                final Dialog d = new Dialog(context);
                d.setContentView(R.layout.bookingfile);
                d.setCancelable(false);
                d.show();

                btnappdate = d.findViewById(R.id.appdate);
                btnbook = d.findViewById(R.id.btnapp);
                tvwarning = d.findViewById(R.id.tvwarning);
                btncancel = d.findViewById(R.id.btncancel);

                /*int dd;
                int mm;
                int yyyy;

                dd = calendar.get(Calendar.DAY_OF_MONTH);
                mm = 1 + calendar.get(Calendar.MONTH);
                yyyy = calendar.get(Calendar.YEAR);

                btnappdate.setText(dd + "/" + mm + "/" + yyyy);*/

                Animation animation = AnimationUtils.loadAnimation(context, R.anim.skip_button);
                btnappdate.startAnimation(animation);

                tvwarning.setText("Warning: Please select only Monday date from the calender otherwise your appointment will be rejected");

                btnappdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                int mon = 1 + month;
                                btnappdate.setText(dayOfMonth + "/" + mon + "/" + year);
                                date = (dayOfMonth + "/" + mon + "/" + year);
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH
                        ), calendar.get(Calendar.DAY_OF_MONTH)).show();
                    }
                });

                btncancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        d.dismiss();
                    }
                });

                btnbook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (date == "Null") {
                            Toast.makeText(context, "Please Select the Date", Toast.LENGTH_SHORT).show();
                        } else {
                            d.dismiss();
                            new AlertDialog.Builder(context)
                                    .setTitle("Confirmation Message")
                                    .setMessage("Are you sure want to book your appointment?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            apiInterface = ApiClient.getAliClient().create(ApiInterface.class);
                                            Call<GetterSetterAppointment> call = apiInterface.addAppointment(doc_id, pat_id, montime, date, "Pending");
                                            call.enqueue(new Callback<GetterSetterAppointment>() {
                                                @Override
                                                public void onResponse(Call<GetterSetterAppointment> call, Response<GetterSetterAppointment> response) {
                                                    GetterSetterAppointment getterSetterAppointment = response.body();
                                                    Toast.makeText(context, "Appointment Booked Successfully Saved", Toast.LENGTH_SHORT).show();
                                                    Intent i = new Intent(context, Dashboardpatient.class);
                                                    context.startActivity(i);
                                                    ((Activity) context).finish();

                                                }

                                                @Override
                                                public void onFailure(Call<GetterSetterAppointment> call, Throwable t) {
                                                    Log.d("mydata", t.getMessage());
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
                    }
                });

            }
        });

        myClass.btntue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Button btnappdate, btnbook, btncancel;
                TextView tvwarning;


                final Dialog d = new Dialog(context);
                d.setContentView(R.layout.bookingfile);
                d.setCancelable(false);
                d.show();

                btnappdate = d.findViewById(R.id.appdate);
                btnbook = d.findViewById(R.id.btnapp);
                tvwarning = d.findViewById(R.id.tvwarning);
                btncancel = d.findViewById(R.id.btncancel);

                Animation animation = AnimationUtils.loadAnimation(context, R.anim.skip_button);
                btnappdate.startAnimation(animation);

                tvwarning.setText("Warning: Please select only Tuesday date from the calender otherwise your appointment will be rejected");

                btnappdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                int mon = 1 + month;
                                btnappdate.setText(dayOfMonth + "/" + mon + "/" + year);
                                date = (dayOfMonth + "/" + mon + "/" + year);
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH
                        ), calendar.get(Calendar.DAY_OF_MONTH)).show();
                    }
                });

                btncancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        d.dismiss();
                    }
                });

                btnbook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (date == "Null") {
                            Toast.makeText(context, "Please Select the Date", Toast.LENGTH_SHORT).show();
                        } else {
                            d.dismiss();
                            new AlertDialog.Builder(context)
                                    .setTitle("Confirmation Message")
                                    .setMessage("Are you sure want to book your appointment?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            apiInterface = ApiClient.getAliClient().create(ApiInterface.class);
                                            Call<GetterSetterAppointment> call = apiInterface.addAppointment(doc_id, pat_id, tuetime, date, "Pending");
                                            call.enqueue(new Callback<GetterSetterAppointment>() {
                                                @Override
                                                public void onResponse(Call<GetterSetterAppointment> call, Response<GetterSetterAppointment> response) {
                                                    GetterSetterAppointment getterSetterAppointment = response.body();
                                                    Toast.makeText(context, "Appointment Booked Successfully Saved", Toast.LENGTH_SHORT).show();
                                                    Intent i = new Intent(context, Dashboardpatient.class);
                                                    context.startActivity(i);
                                                    ((Activity) context).finish();
                                                }

                                                @Override
                                                public void onFailure(Call<GetterSetterAppointment> call, Throwable t) {
                                                    Log.d("mydata", t.getMessage());
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
                    }
                });
            }
        });

        myClass.btnwed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Button btnappdate, btnbook, btncancel;
                TextView tvwarning;


                final Dialog d = new Dialog(context);
                d.setContentView(R.layout.bookingfile);
                d.setCancelable(false);
                d.show();

                btnappdate = d.findViewById(R.id.appdate);
                btnbook = d.findViewById(R.id.btnapp);
                tvwarning = d.findViewById(R.id.tvwarning);
                btncancel = d.findViewById(R.id.btncancel);

                Animation animation = AnimationUtils.loadAnimation(context, R.anim.skip_button);
                btnappdate.startAnimation(animation);

                tvwarning.setText("Warning: Please select only Wednesday date from the calender otherwise your appointment will be rejected");

                btnappdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                int mon = 1 + month;
                                btnappdate.setText(dayOfMonth + "/" + mon + "/" + year);
                                date = (dayOfMonth + "/" + mon + "/" + year);
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH
                        ), calendar.get(Calendar.DAY_OF_MONTH)).show();
                    }
                });

                btncancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        d.dismiss();
                    }
                });

                btnbook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (date == "Null") {
                            Toast.makeText(context, "Please Select the Date", Toast.LENGTH_SHORT).show();
                        } else {
                            d.dismiss();
                            new AlertDialog.Builder(context)
                                    .setTitle("Confirmation Message")
                                    .setMessage("Are you sure want to book your appointment?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            apiInterface = ApiClient.getAliClient().create(ApiInterface.class);
                                            Call<GetterSetterAppointment> call = apiInterface.addAppointment(doc_id, pat_id, wedtime, date, "Pending");
                                            call.enqueue(new Callback<GetterSetterAppointment>() {
                                                @Override
                                                public void onResponse(Call<GetterSetterAppointment> call, Response<GetterSetterAppointment> response) {
                                                    GetterSetterAppointment getterSetterAppointment = response.body();
                                                    Toast.makeText(context, "Appointment Booking Successfully Saved", Toast.LENGTH_SHORT).show();
                                                    Toast.makeText(context, "Appointment Booked Successfully Saved", Toast.LENGTH_SHORT).show();
                                                    Intent i = new Intent(context, Dashboardpatient.class);
                                                    context.startActivity(i);
                                                    ((Activity) context).finish();
                                                }

                                                @Override
                                                public void onFailure(Call<GetterSetterAppointment> call, Throwable t) {
                                                    Log.d("mydata", t.getMessage());
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
                    }
                });
            }
        });

        myClass.btnthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Button btnappdate, btnbook, btncancel;
                TextView tvwarning;


                final Dialog d = new Dialog(context);
                d.setContentView(R.layout.bookingfile);
                d.setCancelable(false);
                d.show();

                btnappdate = d.findViewById(R.id.appdate);
                btnbook = d.findViewById(R.id.btnapp);
                tvwarning = d.findViewById(R.id.tvwarning);
                btncancel = d.findViewById(R.id.btncancel);

                Animation animation = AnimationUtils.loadAnimation(context, R.anim.skip_button);
                btnappdate.startAnimation(animation);

                tvwarning.setText("Warning: Please select only Thursday date from the calender otherwise your appointment will be rejected");

                btnappdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                int mon = 1 + month;
                                btnappdate.setText(dayOfMonth + "/" + mon + "/" + year);
                                date = (dayOfMonth + "/" + mon + "/" + year);
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH
                        ), calendar.get(Calendar.DAY_OF_MONTH)).show();
                    }
                });

                btncancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        d.dismiss();
                    }
                });

                btnbook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (date == "Null") {
                            Toast.makeText(context, "Please Select the Date", Toast.LENGTH_SHORT).show();
                        } else {
                            d.dismiss();
                            new AlertDialog.Builder(context)
                                    .setTitle("Confirmation Message")
                                    .setMessage("Are you sure want to book your appointment?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            apiInterface = ApiClient.getAliClient().create(ApiInterface.class);
                                            Call<GetterSetterAppointment> call = apiInterface.addAppointment(doc_id, pat_id, thutime, date, "Pending");
                                            call.enqueue(new Callback<GetterSetterAppointment>() {
                                                @Override
                                                public void onResponse(Call<GetterSetterAppointment> call, Response<GetterSetterAppointment> response) {
                                                    GetterSetterAppointment getterSetterAppointment = response.body();
                                                    Toast.makeText(context, "Appointment Booked Successfully Saved", Toast.LENGTH_SHORT).show();
                                                    Intent i = new Intent(context, Dashboardpatient.class);
                                                    context.startActivity(i);
                                                    ((Activity) context).finish();
                                                }

                                                @Override
                                                public void onFailure(Call<GetterSetterAppointment> call, Throwable t) {
                                                    Log.d("mydata", t.getMessage());
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
                    }
                });
            }
        });

        myClass.btnfri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Button btnappdate, btnbook, btncancel;
                TextView tvwarning;


                final Dialog d = new Dialog(context);
                d.setContentView(R.layout.bookingfile);
                d.setCancelable(false);
                d.show();

                btnappdate = d.findViewById(R.id.appdate);
                btnbook = d.findViewById(R.id.btnapp);
                tvwarning = d.findViewById(R.id.tvwarning);
                btncancel = d.findViewById(R.id.btncancel);

                Animation animation = AnimationUtils.loadAnimation(context, R.anim.skip_button);
                btnappdate.startAnimation(animation);

                tvwarning.setText("Warning: Please select only Friday date from the calender otherwise your appointment will be rejected");

                btnappdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                int mon = 1 + month;
                                btnappdate.setText(dayOfMonth + "/" + mon + "/" + year);
                                date = (dayOfMonth + "/" + mon + "/" + year);
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH
                        ), calendar.get(Calendar.DAY_OF_MONTH)).show();
                    }
                });

                btncancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        d.dismiss();
                    }
                });

                btnbook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (date == "Null") {
                            Toast.makeText(context, "Please Select the Date", Toast.LENGTH_SHORT).show();
                        } else {
                            d.dismiss();
                            new AlertDialog.Builder(context)
                                    .setTitle("Confirmation Message")
                                    .setMessage("Are you sure want to book your appointment?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            apiInterface = ApiClient.getAliClient().create(ApiInterface.class);
                                            Call<GetterSetterAppointment> call = apiInterface.addAppointment(doc_id, pat_id, fritime, date, "Pending");
                                            call.enqueue(new Callback<GetterSetterAppointment>() {
                                                @Override
                                                public void onResponse(Call<GetterSetterAppointment> call, Response<GetterSetterAppointment> response) {
                                                    GetterSetterAppointment getterSetterAppointment = response.body();
                                                    Toast.makeText(context, "Appointment Booked Successfully Saved", Toast.LENGTH_SHORT).show();
                                                    Intent i = new Intent(context, Dashboardpatient.class);
                                                    context.startActivity(i);
                                                    ((Activity) context).finish();
                                                }

                                                @Override
                                                public void onFailure(Call<GetterSetterAppointment> call, Throwable t) {
                                                    Log.d("mydata", t.getMessage());
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
                    }
                });
            }
        });

        myClass.btnsat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Button btnappdate, btnbook, btncancel;
                TextView tvwarning;


                final Dialog d = new Dialog(context);
                d.setContentView(R.layout.bookingfile);
                d.setCancelable(false);
                d.show();

                btnappdate = d.findViewById(R.id.appdate);
                btnbook = d.findViewById(R.id.btnapp);
                tvwarning = d.findViewById(R.id.tvwarning);
                btncancel = d.findViewById(R.id.btncancel);

                Animation animation = AnimationUtils.loadAnimation(context, R.anim.skip_button);
                btnappdate.startAnimation(animation);

                tvwarning.setText("Warning: Please select only Saturday date from the calender otherwise your appointment will be rejected");

                btnappdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                int mon = 1 + month;
                                btnappdate.setText(dayOfMonth + "/" + mon + "/" + year);
                                date = (dayOfMonth + "/" + mon + "/" + year);
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH
                        ), calendar.get(Calendar.DAY_OF_MONTH)).show();
                    }
                });

                btncancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        d.dismiss();
                    }
                });

                btnbook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (date == "Null") {
                            Toast.makeText(context, "Please Select the Date", Toast.LENGTH_SHORT).show();
                        } else {
                            d.dismiss();
                            new AlertDialog.Builder(context)
                                    .setTitle("Confirmation Message")
                                    .setMessage("Are you sure want to book your appointment?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            apiInterface = ApiClient.getAliClient().create(ApiInterface.class);
                                            Call<GetterSetterAppointment> call = apiInterface.addAppointment(doc_id, pat_id, sattime, date, "Pending");
                                            call.enqueue(new Callback<GetterSetterAppointment>() {
                                                @Override
                                                public void onResponse(Call<GetterSetterAppointment> call, Response<GetterSetterAppointment> response) {
                                                    GetterSetterAppointment getterSetterAppointment = response.body();
                                                    Toast.makeText(context, "Appointment Booked Successfully Saved", Toast.LENGTH_SHORT).show();
                                                    Intent i = new Intent(context, Dashboardpatient.class);
                                                    context.startActivity(i);
                                                    ((Activity) context).finish();
                                                }

                                                @Override
                                                public void onFailure(Call<GetterSetterAppointment> call, Throwable t) {
                                                    Log.d("mydata", t.getMessage());
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
                    }
                });
            }
        });

        myClass.btnreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Button btnsubmit, btncancel;
                RatingBar rbrating;
                final EditText etreview;

                final Dialog d = new Dialog(context);
                d.setContentView(R.layout.reviewfile);
                d.setCancelable(false);
                d.show();

                btnsubmit = d.findViewById(R.id.btnsubmitreview);
                rbrating = d.findViewById(R.id.rbreview);
                etreview = d.findViewById(R.id.etreview);
                btncancel = d.findViewById(R.id.btncancelreview);

                rbrating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        ratingFromUser = rating;
                    }
                });

                btncancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        d.dismiss();
                    }
                });

                btnsubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        review = etreview.getText().toString();
                        ApiInterface apiInterface = ApiClient.getAliClient().create(ApiInterface.class);
                        Call<GetterSetterReview> call = apiInterface.addReview(doc_id, pat_id, ratingFromUser, review);
                        call.enqueue(new Callback<GetterSetterReview>() {
                            @Override
                            public void onResponse(Call<GetterSetterReview> call, Response<GetterSetterReview> response) {
                                GetterSetterReview getterSetterReview = response.body();
                                Toast.makeText(context, "Review Submitted Successfully", Toast.LENGTH_SHORT).show();
                                d.dismiss();
                                Intent i = new Intent(context, Dashboardpatient.class);
                                context.startActivity(i);
                                ((Activity) context).finish();
                            }

                            @Override
                            public void onFailure(Call<GetterSetterReview> call, Throwable t) {
                                Log.d("mydata", t.getMessage());
                            }
                        });
                    }
                });

            }
        });
    }


    @Override
    public int getItemCount() {
        return nestedDoctorList.size();
    }

    public class MyClass extends RecyclerView.ViewHolder {


        TextView tvfname, tvlname, tvspeciality, tvdegree, tvcliaddress;
        Button btnmon, btntue, btnwed, btnthu, btnfri, btnsat, btnreview;
        RatingBar rb;

        public MyClass(@NonNull View itemView) {
            super(itemView);

            tvfname = itemView.findViewById(R.id.tvnesfname);
            tvlname = itemView.findViewById(R.id.tvneslname);
            tvspeciality = itemView.findViewById(R.id.tvnesspeciality);
            tvdegree = itemView.findViewById(R.id.tvnesdegree);
            tvcliaddress = itemView.findViewById(R.id.tvaddress);
            rb = itemView.findViewById(R.id.ratingbar);
            btnmon = itemView.findViewById(R.id.btnmon);
            btntue = itemView.findViewById(R.id.btntue);
            btnwed = itemView.findViewById(R.id.btnwed);
            btnthu = itemView.findViewById(R.id.btnthu);
            btnfri = itemView.findViewById(R.id.btnfri);
            btnsat = itemView.findViewById(R.id.btnsat);
            btnreview = itemView.findViewById(R.id.btnreview);

        }
    }
}

