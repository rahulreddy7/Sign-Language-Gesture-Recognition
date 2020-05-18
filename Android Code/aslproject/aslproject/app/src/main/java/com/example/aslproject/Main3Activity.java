package com.example.aslproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Main3Activity extends AppCompatActivity {
    TableLayout t;
    Button refreshbtn;
    HashMap<String,String> s3data = new HashMap<>();
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        refreshbtn = (Button) findViewById(R.id.button34);
        t = (TableLayout)findViewById(R.id.tablelayout);
        TableRow tr1 = new TableRow(this);
        tr1.setLayoutParams(new TableLayout.LayoutParams( TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        TextView textview = new TextView(this);
        Intent sec = getIntent();
        HashMap<String,String> hmap = (HashMap<String, String>) sec.getSerializableExtra("map");
        s3data.putAll(hmap);
        for (Map.Entry<String,String> entry : hmap.entrySet())
        {
            tr1.addView(textview);
            //GetObjectRequest r = new GetObjectRequest("mcoutputreq", "output.txt");
            //S3Object object = s3Client.getObject(r);//entry.getKey().split(".")[0] + ".txt"));
//            S3Object object = s3Client.getObject("mcoutputreq", "output.txt");
//            InputStream objectData = object.getObjectContent();
//            System.out.println(objectData.toString());

            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            textview.setText(entry.getKey() + entry.getValue());
        }

        //The 'File' class from Java doesn't understand that S3 exists. Here's an example of reading a file from the AWS documentation:

        //AmazonS3 s3Client = new AmazonS3Client(new ProfileCredentialsProvider());





        //textview.setText("Request1 : Pending");
        //textview.getTextColors(R.color.)
        //textview.setTextColor(Color.YELLOW);
        //tr1.addView(textview);
        t.addView(tr1, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));




        refreshbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retdata();
                updatetable();
            }
        });
    }

    private void updatetable() {

        t = (TableLayout)findViewById(R.id.tablelayout);
        t.removeAllViews();
        TableRow tr1 = new TableRow(this);
        tr1.setLayoutParams(new TableLayout.LayoutParams( TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        TextView textview = new TextView(this);
        Intent sec = getIntent();
        //System.out.println();
        for (Map.Entry<String,String> entry : s3data.entrySet())
        {
            tr1.addView(textview);
            //GetObjectRequest r = new GetObjectRequest("mcoutputreq", "output.txt");
            //S3Object object = s3Client.getObject(r);//entry.getKey().split(".")[0] + ".txt"));
//            S3Object object = s3Client.getObject("mcoutputreq", "output.txt");
//            InputStream objectData = object.getObjectContent();
//            System.out.println(objectData.toString());

            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
            textview.setText(entry.getKey() + entry.getValue());
        }

         t.addView(tr1, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));




    }

    private void retdata() {
        //because of the async error
        class retinasync extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //Log.e("checking url",s);

            }

            @SuppressLint("WrongThread")
            @Override
            protected String doInBackground(Void... params) {
                //String result = null;
                try {
                    getdata();
                    //System.out.println(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return "";
            }


        }
        retinasync uv = new retinasync();
        uv.execute();
    }

    private void getdata() throws IOException {
        AmazonS3Client s3Client = new AmazonS3Client(new BasicAWSCredentials("AKIAJ7HUDAMTZBQ7L74A","EJEjcIcPFINALhuo93pWYtsY3xg+8y7lOYIh0cov"));
        S3Object fullObject = null;
        Intent sec = getIntent();
        HashMap<String,String> hmap = (HashMap<String, String>) sec.getSerializableExtra("map");
        Set<String> keys = s3data.keySet();
        //System.out.println(hmap);
//        Iterator<Object> it = keys.iterator();
//        while(it.hasNext()){
//            //System.out.println(it.next());
//            System.out.println(it.next().split("."));
//        }
        for(String s : keys)
        {
            //System.out.println(s);
            //System.out.println(s.split("."));
        }


        for (Map.Entry<String,String> entry : hmap.entrySet())
        {
            String test = null;
            //System.out.println(entry.getKey());
            //System.out.println(entry.getKey().split(":"));
            //System.out.println("im");
            test = entry.getKey();
            String[] arr = test.split(".");
            //System.out.println(arr.length);
            String filename = "output.txt";
            fullObject = s3Client.getObject(new GetObjectRequest("mcoutputreq",filename));
            BufferedReader reader = new BufferedReader(new InputStreamReader(fullObject.getObjectContent()));
            String line = "";
            String output = "";
            while ((line = reader.readLine()) != null) {
                output = output + line;
            }


            s3data.put(entry.getKey(), output);
        }
        //fullObject = s3Client.getObject(new GetObjectRequest("mcoutputreq","output.txt"));

        //return line;
    }
}
