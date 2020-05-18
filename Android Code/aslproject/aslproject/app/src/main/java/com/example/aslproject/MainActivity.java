package com.example.aslproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SyncStatusObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

//import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.io.File;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Button bnew;
    HashMap<String,String> requests = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bnew = (Button) findViewById(R.id.button2);
        addListonBtn();
    }


    private void addListonBtn() {
        bnew = (Button) findViewById(R.id.button2);
        bnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent opencamera = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(opencamera,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent cameraresult) {
        super.onActivityResult(requestCode, resultCode, cameraresult);

        if(requestCode == 1)
        {
            Uri video_temppath = cameraresult.getData();
            //Intent i3 = getIntent();
            //get the videopath

            System.out.println(video_temppath);

            String fileName;
            //Log.e("checkfile","before");
            String result;
            //converting uri temp path to filepath

            Cursor c = getContentResolver().query(video_temppath, null, null, null, null);
            if (c == null)
            {
                result = video_temppath.getPath();
            }
            else
            {
                c.moveToFirst();
                result = c.getString(c.getColumnIndex(MediaStore.Images.ImageColumns.DATA));
                c.close();
            }

            fileName = result;
            System.out.println("Filepath" + fileName);
            Intent i1 = new Intent(this, Main2Activity.class);
            i1.putExtra("filename", fileName);
            i1.putExtra("map",requests);
            startActivity(i1);
        }
    }
}
