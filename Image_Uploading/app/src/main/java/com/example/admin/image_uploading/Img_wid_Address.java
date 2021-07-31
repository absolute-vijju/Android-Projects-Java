package com.example.admin.image_uploading;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class Img_wid_Address extends AppCompatActivity {


    EditText ed,edadd;
    Button btnselect,btnupload;
    ImageView im;






    ///-------------------------

    String name;
    String storeaddress="403, 4th Floor, Shivam Complex Bhuyangdev Char Rasta,, Sola Rd, Naranpura, Ahmedabad, Gujarat 380013";
    String lat;
    String log;

    Bitmap bitmap;
    boolean check = true;
    ProgressDialog progressDialog ;
    String ImagePath = "path" ;
    String ServerUploadPath ="https://infohappy2help.000webhostapp.com/img_wid_address.php" ;
    ///--------------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_wid__address);


        ed= (EditText) findViewById(R.id.edname_new);
        edadd= (EditText) findViewById(R.id.edaddress_new);
        btnselect= (Button) findViewById(R.id.btnselect_new);
        btnupload= (Button) findViewById(R.id.btnupload_new);
        im= (ImageView) findViewById(R.id.img_new);



        btnselect.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                name =ed.getText().toString();
                storeaddress=edadd.getText().toString();


                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), 1);

                GeocodingLocation locationAddress = new GeocodingLocation();
                locationAddress.getAddressFromLocation(storeaddress, getApplicationContext(), new GeocoderHandler());
            }
        });

        btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                ImageUploadToServerFunction();
            }
        });
    }

    public class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");



                    lat = bundle.getString("l1");
                    log = bundle.getString("l2");

                    Log.d("mydata","l1 : "+lat);
                    Log.d("mydata","l2 : "+log);



                    break;
                default:
                    locationAddress = null;
            }

        }
    }

    @Override
    protected void onActivityResult(int RC, int RQC, Intent I)
    {
        super.onActivityResult(RC, RQC, I);

        if (RC == 1 && RQC == RESULT_OK && I != null && I.getData() != null) {

            Uri uri = I.getData();

            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                im.setImageBitmap(bitmap);

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


    public void ImageUploadToServerFunction()
    {

        ByteArrayOutputStream byteArrayOutputStreamObject ;
        byteArrayOutputStreamObject = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject);
        byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();
        final String ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);
        class AsyncTaskUploadClass extends AsyncTask<Void,Void,String>
        {
            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(Img_wid_Address.this,"Image is Uploading","Please Wait",false,false);
            }
            @Override
            protected void onPostExecute(String string1) {
                super.onPostExecute(string1);
                progressDialog.dismiss();

                Toast.makeText(Img_wid_Address.this,string1,Toast.LENGTH_LONG).show();
                Log.d("data","post execute : "+string1);

                im.setImageResource(android.R.color.transparent);



               /* Intent i=new Intent(NEWAddstore.this,LoginActivity.class);
                finish();
                startActivity(i);*/
            }

            @Override
            protected String doInBackground(Void... params)
            {
                ImageProcessClass imageProcessClass = new ImageProcessClass();
                HashMap hm = new HashMap<String,String>();


                hm.put("name",name);
                hm.put("address",storeaddress);
                hm.put(ImagePath, ConvertImage);
                hm.put("lat",lat);
                hm.put("log",log);

                // Log.d("data","Store data : "+p_category+"-"+p_name+"-"+p_descs+"-"+p_address+"-"+p_cityvalue+"-"+p_statevalue);

                String FinalData = imageProcessClass.ImageHttpRequest(ServerUploadPath, hm);
                return FinalData;
            }
        }
        AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();

        AsyncTaskUploadClassOBJ.execute();
    }

    public class ImageProcessClass{

        public String ImageHttpRequest(String requestURL,HashMap<String, String> PData) {

            StringBuilder stringBuilder = new StringBuilder();

            try {

                URL url;
                HttpURLConnection httpURLConnectionObject ;
                OutputStream OutPutStream;
                BufferedWriter bufferedWriterObject ;
                BufferedReader bufferedReaderObject ;
                int RC ;

                url = new URL(requestURL);

                httpURLConnectionObject = (HttpURLConnection) url.openConnection();

                httpURLConnectionObject.setReadTimeout(19000);

                httpURLConnectionObject.setConnectTimeout(19000);

                httpURLConnectionObject.setRequestMethod("POST");

                httpURLConnectionObject.setDoInput(true);

                httpURLConnectionObject.setDoOutput(true);

                OutPutStream = httpURLConnectionObject.getOutputStream();

                bufferedWriterObject = new BufferedWriter(

                        new OutputStreamWriter(OutPutStream, "UTF-8"));

                bufferedWriterObject.write(bufferedWriterDataFN(PData));

                bufferedWriterObject.flush();

                bufferedWriterObject.close();

                OutPutStream.close();

                RC = httpURLConnectionObject.getResponseCode();

                if (RC == HttpsURLConnection.HTTP_OK) {

                    bufferedReaderObject = new BufferedReader(new InputStreamReader(httpURLConnectionObject.getInputStream()));

                    stringBuilder = new StringBuilder();

                    String RC2;

                    while ((RC2 = bufferedReaderObject.readLine()) != null){

                        stringBuilder.append(RC2);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        private String bufferedWriterDataFN(HashMap<String, String> HashMapParams) throws UnsupportedEncodingException {

            StringBuilder stringBuilderObject;
            stringBuilderObject = new StringBuilder();
            for (Map.Entry<String, String> KEY : HashMapParams.entrySet()) {
                if (check)

                    check = false;
                else
                    stringBuilderObject.append("&");

                stringBuilderObject.append(URLEncoder.encode(KEY.getKey(), "UTF-8"));

                stringBuilderObject.append("=");

                stringBuilderObject.append(URLEncoder.encode(KEY.getValue(), "UTF-8"));
            }

            return stringBuilderObject.toString();
        }

    }
}
/*--------------------------------------------------------------------------------------------

        webservice

        email = info.happy2help.world@gmail.com
        password=tops@123


        table : img_wid_address




        


 */

