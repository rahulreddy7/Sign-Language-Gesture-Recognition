package com.example.aslproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;

import java.io.File;
import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {
    Button record;
    Button process;
    Button dashboard;

    HashMap<String,String> hmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        record = (Button) findViewById(R.id.button5);
        process = (Button) findViewById(R.id.button6);
        dashboard = (Button) findViewById(R.id.button7);
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goback();
            }
        });
        button6listener();

        dashboard = (Button) findViewById(R.id.button7);
        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //hmap = (HashMap<String, String>) temp.getSerializableExtra("map");
                Intent b = new Intent(Main2Activity.this, Main3Activity.class);
                b.putExtra("map",hmap);
                startActivity(b);
            }
        });

        //dashboardfunc();
    }

    private void button6listener() {
        process = (Button) findViewById(R.id.button6);
        process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //videoview.start();
                //Toast.makeText(Main2Activity.this, "Uploading video in progress... ",Toast.LENGTH_LONG).show();
                //uploadingcode();
                uploadVideo();
                //httpconnection_code();
                Toast.makeText(Main2Activity.this, "Uploading almost done... ",Toast.LENGTH_LONG).show();
                //lastprocess();
                View b = findViewById(R.id.button7);
                b.setVisibility(View.VISIBLE);


            }
        });
    }


    private void uploadVideo() {
        //because of the async error
        class uploadinasync extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.e("checking url",s);

            }

            @SuppressLint("WrongThread")
            @Override
            protected String doInBackground(Void... params) {
                s3function();

                return "";
            }


        }
        uploadinasync uv = new uploadinasync();
        uv.execute();
    }

    private void s3function() {
        Regions clientRegion = Regions.US_EAST_1;
        //String bucketName = "mcrequests";
        //String stringObjKeyName = "*** String object key name ***";
        String fileObjKeyName = "request";
        //String fileName = result;
            /*
            BasicAWSCredentials credentials = new BasicAWSCredentials("AKIAJ7HUDAMTZBQ7L74A","EJEjcIcPFINALhuo93pWYtsY3xg+8y7lOYIh0cov");
            //AmazonS3 cl = new AmazonS3Client(credentials);
            //cl.setRegion(clientRegion);
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(clientRegion).build();
            String bucketname= "mcrequests";

            s3Client.putObject(bucketname,fileObjKeyName,new File(fileName));
            System.out.println("done");
            */
        Intent filenamei = getIntent();
        String fileName = filenamei.getStringExtra("filename");


        AmazonS3Client s3Client = new AmazonS3Client(new BasicAWSCredentials("AKIAJ7HUDAMTZBQ7L74A","EJEjcIcPFINALhuo93pWYtsY3xg+8y7lOYIh0cov"));
        String bucketname= "mcrequests";

        s3Client.putObject(bucketname,fileName.split("/")[6],new File(fileName));
        System.out.println("done");
        hmap = (HashMap<String, String>) filenamei.getSerializableExtra("map");
        hmap.put("Request:  "+fileName.split("/")[6],"  :  PENDING ");


        //Toast.makeText(Main2Activity.this, "Uploading video in progress... ", Toast.LENGTH_SHORT).show();

        //Toast.makeText(Main2Activity.this, "Uploading done", Toast.LENGTH_LONG).show();
    }

    private void goback() {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("map",hmap);
        startActivity(i);
    }
    
}
