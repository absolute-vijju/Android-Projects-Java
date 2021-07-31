package com.example.swipe_delete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView rev;
    Retrofit retrofit;
    ApiInterface apiInterface;
    List<NestedSliderData> nestedSliderData = new ArrayList<>();
    MyAdapter myAdapter;
    ProgressDialog progressDialog;
    Paint p = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rev = findViewById(R.id.rev);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        rev.setLayoutManager(layoutManager);

        progressDialog = ProgressDialog.show(MainActivity.this, "Loading Data", "Please wait...", false, false);

        loadData();

        enableSwipe();
    }

    private void loadData() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://birbalbaba.sumayinfotech.com/Rest/Api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        apiInterface = retrofit.create(ApiInterface.class);

        Call<SliderData> call = apiInterface.getSlider();

        call.enqueue(new Callback<SliderData>() {
            @Override
            public void onResponse(Call<SliderData> call, Response<SliderData> response) {
                SliderData sliderData = response.body();

                nestedSliderData = sliderData.getNestedSliderData();
/*                Log.d("mydata", sliderData.getPath());

                for (int i = 0; i < nestedSliderData.size(); i++) {
                    Log.d("mydata", nestedSliderData.get(i).getId());
                    Log.d("mydata", nestedSliderData.get(i).getName());
                    Log.d("mydata", nestedSliderData.get(i).getImageurl());
                }*/
                progressDialog.dismiss();

                myAdapter = new MyAdapter(MainActivity.this, nestedSliderData, sliderData.getPath());
                rev.setAdapter(myAdapter);

                Toast.makeText(MainActivity.this, "Data Loaded Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SliderData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        /*new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                String name = nestedSliderData.get(viewHolder.getAdapterPosition()).getName();
                nestedSliderData.remove(viewHolder.getAdapterPosition());
                final Snackbar snackbar = Snackbar.make(rev, name + " removed from cart", Snackbar.LENGTH_SHORT);
                myAdapter.notifyDataSetChanged();

                if (nestedSliderData.size() == 0) {
                    setContentView(R.layout.emty_layout);
                }

                snackbar.setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar.make(v, "Undo Successful", Snackbar.LENGTH_SHORT).show();
                    }
                });
                snackbar.show();
            }
        }).attachToRecyclerView(rev);*/

    }

    private void enableSwipe() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT) {
                    final NestedSliderData deletedModel = nestedSliderData.get(position);
                    final int deletedPosition = position;
                    myAdapter.removeItem(position);
                    Snackbar snackbar = Snackbar.make(rev, " removed from Recyclerview!", Snackbar.LENGTH_LONG);
                    snackbar.setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // undo is selected, restore the deleted item
                            myAdapter.restoreItem(deletedModel, deletedPosition);
                        }
                    });
                    snackbar.setActionTextColor(Color.YELLOW);
                    snackbar.show();
                    if (nestedSliderData.size() == 0) {
                        setContentView(R.layout.emty_layout);
                    }
                } else {
                    final NestedSliderData deletedModel = nestedSliderData.get(position);
                    final int deletedPosition = position;
                    myAdapter.removeItem(position);
                    Snackbar snackbar = Snackbar.make(rev, " removed from Recyclerview!", Snackbar.LENGTH_LONG);
                    snackbar.setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // undo is selected, restore the deleted item
                            myAdapter.restoreItem(deletedModel, deletedPosition);
                        }
                    });
                    snackbar.setActionTextColor(Color.YELLOW);
                    snackbar.show();
                    if (nestedSliderData.size() == 0) {
                        setContentView(R.layout.emty_layout);
                    }
                }
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Bitmap icon = null;

                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    View itemview = viewHolder.itemView;
                    float height = (float) itemview.getBottom() - (float) itemview.getTop();
                    float height2 = itemview.getHeight();
                    float width = height / 3;

                    /*Log.d("mydata", "Height 1:" + String.valueOf(height));
                    Log.d("mydata", "Height 2:" + String.valueOf(height2));*/

                    if (dX > 0) {
                        p.setColor(Color.parseColor("#388E3C"));
                        RectF background = new RectF((float) itemview.getLeft(), (float) itemview.getTop(), dX, (float) itemview.getBottom());
                        c.drawRect(background, p);
                        /*icon=BitmapFactory.decodeResource(getResources(), R.drawable.deletevector);*/
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.delete);
                        RectF icon_dest = new RectF((float) itemview.getLeft() + width, (float) itemview.getTop() + width, (float) itemview.getLeft() + 2 * width, (float) itemview.getBottom() - width);
                        c.drawBitmap(icon, null, icon_dest, p);
                    } else {
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemview.getRight() + dX, (float) itemview.getTop(), (float) itemview.getRight(), (float) itemview.getBottom());
                        c.drawRect(background, p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.delete);
                        RectF icon_dest = new RectF((float) itemview.getRight() - 2 * width, (float) itemview.getTop() + width, (float) itemview.getRight() - width, (float) itemview.getBottom() - width);
                        c.drawBitmap(icon, null, icon_dest, p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rev);
    }
}
